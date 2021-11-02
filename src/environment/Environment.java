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
package environment;

import executables.Executable;
import filesystem.FileSystem;
import input.UserInput;
import java.util.List;
import java.util.Map;

/**
 * Represents the shell environment, has the map of available commands,
 * directory stack, input history
 */
public interface Environment {

  /**
   * Get the command map
   *
   * @return Map The command map
   */
  Map<String, Executable> getCommandMap();

  /**
   * Get the input history
   *
   * @return List The input history
   */
  List<UserInput> getInputHistory();

  /**
   * Get the file system
   *
   * @return FileSystem The  file system
   */
  FileSystem getFs();

}
