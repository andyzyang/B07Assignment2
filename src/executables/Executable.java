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
package executables;

import exceptions.ShellException;
import java.util.List;
import output.Output;

/**
 * Represents an executable
 */
public abstract class Executable {

  /**
   * Get the command's manual
   *
   * @return String The manual
   */
  public abstract String getManual();

  /**
   * Execute the command given user input
   *
   * @param args The arguments supplied
   * @param out The output to send normal output to
   * @param err The output to send error output to
   * @return boolean Whether or not to exit the shell
   * @throws ShellException When an executable encounters an error
   */
  public abstract boolean execute(List<String> args, Output out, Output err)
      throws ShellException;
}
