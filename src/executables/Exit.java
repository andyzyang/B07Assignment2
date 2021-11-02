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
import java.util.List;
import output.Output;

/**
 * Represents the exit command
 */
public class Exit extends Executable {

  @Override
  public String getManual() {
    // return a string for the manual
    return String.join(System.lineSeparator(), "NAME",
        "    exit - Exits the shell without error.", "SYNOPSIS", "    exit",
        "DESCRIPTION",
        "    Terminates the current shell process without error code",
        "EXAMPLES", "    exit - exits the shell");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // Inform user if number of arguments are more than 0
    if (args.size() > 0) {
      throw new InvalidArgumentException();
    }
    // Returning true is the condition for the program to cease
    return true;
  }
}
