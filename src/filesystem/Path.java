// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: yangzon7
// UT Student #: 1004453423
// Author: Zongye Yang
//
// Student2:
// UTORID user_name: langyu1
// UT Student #: 1004026895
// Author: Yu Qiang Lang
//
// Student3:
// UTORID user_name: makgabri
// UT Student #: 1003591400
// Author: Gabrian Mak
//
// Student4:
// UTORID user_name: taojia5
// UT Student #: 1004512486
// Author: Jia Qi Tao
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a path, immutable
 */
public class Path {

  private FileSystem fs;

  /**
   * The string representation
   */
  private String string;
  /**
   * The name of the file specified by the path
   */
  private String fileName;
  /**
   * List of all files that occur in the path, starting with the root or working
   * directory depending on whether the path is absolute or relative
   */
  private List<General> filesList = new ArrayList<>();

  /**
   * Create a path given its string representation
   *
   * @param fs The file system
   * @param string The path string
   */
  public Path(FileSystem fs, String string) {
    this.fs = fs;
    // set the string representation
    this.string = string;
    // set the filename
    String[] names = string.split("/+");
    fileName = names.length > 0 ? names[names.length - 1] : "";
  }

  /**
   * Update the files list based on string
   */
  private void updateFiles() {
    // get list of filenames from path string
    String[] names = string.split("/+");
    names = names.length > 0 && names[0].equals("") ? Arrays
        .copyOfRange(names, 1, names.length) : names;
    // start the path, from root if absolute, working dir if relative
    General curDir =
        string.length() > 0 && string.charAt(0) == '/' ? fs.getRootDir()
            : fs.getWorkingDir();
    filesList.clear();
    filesList.add(curDir);
    // for each file name, add the specified file to the files list, or null
    int i = 0;
    while (i < names.length) {
      curDir = curDir instanceof Directory ? ((Directory) curDir)
          .getChildByName(names[i]) : null;
      filesList.add(curDir);
      i++;
    }
    fileName = filesList.get(filesList.size() - 1) == null ? fileName
        : filesList.get(filesList.size() - 1).getName();
  }

  /**
   * Get a list of all files specified in a path, and null for specified files
   * that do not exist
   *
   * @return List List of each file in the path
   */
  public List<General> getFilesList() {
    updateFiles();
    return new ArrayList<>(filesList); // return a copy of list for immutability
  }

  /**
   * Get name of the file (whether file exists or not) specified by the path
   *
   * @return String The filename
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Get the file specified by the path, or null if no such file
   *
   * @return General The file specified by the path
   */
  public General getFile() {
    updateFiles();
    return filesList.get(filesList.size() - 1);
  }

  /**
   * Get the parent of the specified file in the path, or null if no such
   * parent
   *
   * @return General The parent of the specified file in the path
   */
  public General getParent() {
    updateFiles();
    return filesList.size() > 1 ? filesList.get(filesList.size() - 2) : null;
  }

  @Override
  public String toString() {
    return string;
  }
}
