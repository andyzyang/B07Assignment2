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

import exceptions.InvalidArgumentException;
import filesystem.FileSystem;
import java.util.List;
import output.Output;

/**
 * Represents the pwd command
 */
public class Pwd extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Pwd(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    pwd - Print the working directory path.", "SYNOPSIS", "    pwd",
        "DESCRIPTION",
        "    Print the current working directory as an absolute path",
        "EXAMPLES",
        "    pwd - print an absolute path to the working directory");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // pwd should have no arguments or error occurs
    if (args.size() > 0) {
      throw new InvalidArgumentException();
    }
    // if no error, send out input to the console
    out.sendln(fs.getWorkingDir().getPath());
    // the program doesn't exit
    return false;
  }
}
