/* ------------- Implementation Description for an In-Memory File System ---------------- */

/*
  An in-memory file system is used to create a file-system that won't necessarily be persisted to
  disk as when the memory is cleared, the file system is lost.

  The file system should support things like:
  * Creating Files
  * Creating Folders
  * Reading Files
  * Writing to Files
  * Deleting Files/Folders
  * Listing contents of a directory
  * Renaming files or folders

  To create this filesystem, we can think of it as a set of linked folders. We have the option
  to think of folders as a special type of file but, for the purposes of this description, we will
  keep folders as a separate type.

  Each folder would keep two maps of path strings; one that maps files and one that maps to folders.
  Because we are using two maps, we have to ensure that the same path name is not added to both
  folders and files, or that will cause some confusion.

  Below is a description of a simple directory structure that could be used for an in-memory file system:
 */

/**
 * A VERY Basic Folder Node for an in-memory file system. This ignores essential file system requirements
 * such as access security and synchronization.
 *
 * @property parent a link to the parent of this folder structure or null if this folder node is the root folder
 */
class FolderStructure(
    val parent: FolderStructure? = null
) {
    /**
     * Mutable collection of folders, modified internally
     */
    private val _folders = mutableMapOf<String, FolderStructure>()

    /**
     * Mutable collection of files, modified internally
     */
    private val _files = mutableMapOf<String, Any>()

    /**
     * A non-mutable reference to folders, if needed
     */
    val folders: Map<String, FolderStructure> = _folders

    /**
     * A non-mutable reference to files, if needed
     */
    val files: Map<String, Any> = _files

    /**
     * Prints to the console all the folder names followed by the file names.
     * Does not modify any folders of files
     */
    fun ls() {
        folders.keys.forEach {
            TODO("Print folder name")
        }
        files.keys.forEach {
            TODO("Print file name")
        }
    }

    /**
     * Creates a file with the given name. This will throw an [IllegalArgumentException]
     * if [name] already exists in this directory as a file OR folder
     */
    fun createFile(name: String, contents: Any) {
        checkUniqueName(name)
        _files[name] = contents
    }

    /**
     * Get the contents of the file specified by [name]. Throws an [IllegalArgumentException]
     * if a file with the given [name] does not exist.
     */
    fun getFile(name: String): Any {
        checkFileExists(name)
        return files[name]
    }

    /**
     * Updates the given file to the new contents, discarding any old contents. Throws an [IllegalArgumentException]
     * if a file with the given [name] does not exist
     */
    fun updateFile(name: String, newContents: Any) {
        checkFileExists(name)
        _files[name] = newContents
    }

    /**
     * Deletes the file with the given name.  Throws an [IllegalArgumentException]
     * if a file with the given [name] does not exist
     */
    fun deleteFile(name: String) {
        checkFileExists(name)
        _files.remove(name)
    }

    /**
     * Creates a folder with the given name. This will throw an [IllegalArgumentException]
     * if [name] already exists in this directory as a file OR folder
     *
     * @return the created folder
     */
    fun createFolder(name: String): FolderStructure {
        checkUniqueName(name)
        _folders[name] = FolderStructure(this)
        return _folders[name]
    }

    /**
     * Get the folder specified by [name]. Throws an [IllegalArgumentException]
     * if a file with the given [name] does not exist.
     */
    fun getFolder(name: String): FolderStructure {
        checkFolderExists(name)
        return _folders[name]
    }

    /**
     * Resolve the path relative to this folder to the file
     */
    fun resolveFile(path: String): Any {
        // Separate the path into groups
        // /path/to/file.txt becomes [ "path", "to", "file.txt" ]
        val pathSplit = path.split('/')
        // Resolve the folder chain to the folder containing the file
        val folder = resolveFolder(pathSplit.dropLast(1))
        // Get the folder from the file
        return folder.getFile(pathSplit.last())
    }


    /**
     * Resolve the path relative to this folder to the folder
     */
    fun resolveFolder(path: String): FolderStructure {
        // Separate the path into groups
        // /path/to/folder becomes [ "path", "to", "folder" ]
        val pathSplit = path.split('/')
        return resolveFolder(pathSplit)
    }

    private fun resolveFolder(pathSplit: List<String>): FolderStructure {
        return pathSplit.fold(this) { folder, path -> folder.getFolder(path) }
    }

    /**
     * Throws an [IllegalArgumentException] if the given [name] is already in use
     */
    private fun checkUniqueName(name: String) {
        if (folders.containsKey(name) || files.containsKey(name)) {
            throw IllegalArgumentException("Name \"$name\" is already in use.")
        }
    }

    /**
     * Throws an [IllegalArgumentException] if the given [name] is not a name of a file in this folder
     */
    private fun checkFileExists(name: String) {
        if (!files.containsKey(name)) {
            throw IllegalArgumentException("File with name \"$name\" does not exist.")
        }
    }


    /**
     * Throws an [IllegalArgumentException] if the given [name] is not a name of a folder in this folder
     */
    private fun checkFolderExists(name: String) {
        if (!folders.containsKey(name)) {
            throw IllegalArgumentException("Folder with name \"$name\" does not exist.")
        }
    }
}

/*
  This in no way completes a file system, but is the basic idea on where to start.
 */