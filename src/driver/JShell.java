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
package driver;

import environment.Environment;
import environment.RealEnvironment;
import filesystem.FileSystem;
import input.UserInput;
import java.util.Scanner;
import output.Console;
import output.Output;

/**
 * Represents the JShell
 */
public class JShell {

  /**
   * Start JShell
   *
   * @param args Ignored
   */
  public static void main(String[] args) {
    // create a file system
    FileSystem fs = new FileSystem();
    // create a new environment using file system
    Environment env = new RealEnvironment(fs);
    // create an executor for the environment
    Executor executor = new Executor(env);
    // io
    Scanner in = new Scanner(System.in);
    Output out = new Console();
    do {
      // Send the current path to output
      out.send(fs.getWorkingDir().getPath() + "#: ");
      // Execute command and read exit status
    } while (!executor.execute(in.nextLine()));
  }
}
