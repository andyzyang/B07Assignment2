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

/**
 * Represents the file system
 */
public class FileSystem {

  /**
   * The root directory
   */
  private Directory rootDir = new Directory();
  /**
   * The working directory
   */
  private Directory workingDir = rootDir;

  /**
   * Get the root directory
   *
   * @return Directory The root directory
   */
  public Directory getRootDir() {
    return rootDir;
  }

  /**
   * Get the working directory
   *
   * @return Directory The working directory
   */
  public Directory getWorkingDir() {
    return workingDir;
  }

  /**
   * Set the working directory
   *
   * @param workingDir New working directory
   */
  public void setWorkingDir(Directory workingDir) {
    this.workingDir = workingDir;
  }

  /**
   * Check if a string is a valid file name
   *
   * @param str The string to check
   * @return boolean Whether or not str is a valid filename
   */
  public static boolean isValidFileName(String str) {
    return str.matches("[A-Za-z0-9]+") || (str.contains(".") && str
        .matches("[A-Za-z0-9]*.[A-Za-z0-9]*") && str.length() > 1);
  }

}
