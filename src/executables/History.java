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
import input.Input;
import java.util.List;
import output.Output;

/**
 * Represents the history command
 */
public class History extends Executable {

  private List<? extends Input> inputHistory;

  /**
   * Create an instance
   *
   * @param inputHistory The input history
   */
  public History(List<? extends Input> inputHistory) {
    this.inputHistory = inputHistory;
  }

  @Override
  public String getManual() {
    // return a string for the manual
    return String.join(System.lineSeparator(), "NAME",
        "    history - Print recent shell inputs.", "SYNOPSIS",
        "    history [num]", "DESCRIPTION",
        "    Lists past commands to standard output.",
        "    If num is given, print num history, if num is not given, print "
            + "entire history.", "EXAMPLE",
        "    history - prints all past inputs",
        "    history 10 - prints last 10 inputs");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    int index;
    if (args.size() == 0) {
      // if no argument is given, print the whole history list
      index = 0;
    } else if (args.size() == 1 && args.get(0).matches("\\d+")) {
      // if num is given, print num entries, or all entries if num > entries
      index = Integer
          .max(0, inputHistory.size() - Integer.parseInt(args.get(0)));
    } else {
      throw new InvalidArgumentException();
    }
    // print from index to last entry of history
    while (index < inputHistory.size()) {
      out.sendln((index + 1) + ". " + inputHistory.get(index));
      index++;
    }
    return false;
  }
}
