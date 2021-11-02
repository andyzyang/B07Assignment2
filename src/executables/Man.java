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
import java.util.Map;
import output.Output;

/**
 * Represents the man command
 */
public class Man extends Executable {

  private Map<String, Executable> commandMap;

  /**
   * Create an instance
   *
   * @param commandMap The map mapping strings to instance of commands
   */
  public Man(Map<String, Executable> commandMap) {
    this.commandMap = commandMap;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    man - Print command manuals.", "SYNOPSIS", "    man cmd",
        "DESCRIPTION", "    Prints the manual for a specific command cmd",
        "EXAMPLES", "    man man - print this manual",
        "    man ls - print the manual for the ls command");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // Inform user if number of arguments is not one or invalid command
    if (args.size() != 1 || !commandMap.containsKey(args.get(0))) {
      throw new InvalidArgumentException();
    }
    // Print to console the manual of the command
    out.sendln(commandMap.get(args.get(0)).getManual());
    return false;
  }
}
