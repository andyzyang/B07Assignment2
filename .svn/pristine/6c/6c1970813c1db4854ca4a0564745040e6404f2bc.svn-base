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
package input;

import exceptions.InvalidArgumentException;
import exceptions.ParsingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user input, immutable
 */
public class UserInput implements Input {

  /**
   * The raw input string
   */
  private String string;
  /**
   * The command string
   */
  private String command = "";
  /**
   * The argument list
   */
  private List<String> args = new ArrayList<>();
  /**
   * The redirection argument list
   */
  private List<String> redirection = new ArrayList<>();

  /**
   * Create a user input from command line input
   *
   * @param input The command line input
   */
  public UserInput(String input) throws ParsingException {
    string = input;
    input = input.trim() + " ";
    int i = 0;
    int lastI = 0;
    boolean inArg, inStr, inRedirect;
    inArg = inStr = inRedirect = false;
    // loop through input
    while (i < input.length()) {
      // if space not inside a string
      if (input.charAt(i) == ' ' && !inStr) {
        // if there is an arg between spaces, add to arg to list
        if (!input.substring(lastI, i).equals(" ")) {
          String arg = input.substring(lastI, i);
          inRedirect = inRedirect || arg.equals(">>") || arg.equals(">");
          (inRedirect ? redirection : args).add(arg);
          inArg = false;
        }
        lastI = i;
      } else if (input.charAt(i) == '"') {
        if (inStr && (i + 1 == input.length() || input.charAt(i + 1) == ' ')) {
          // if " in string and is followed by a space or the end, valid string
          (inRedirect ? redirection : args)
              .add(input.substring(lastI + 1, i++));
          lastI = i;
          inArg = inStr = false;
        } else if (!inArg) {
          // if " not in string and not in arg, then we are in a string now
          lastI = i;
          inArg = inStr = true;
        } else {
          // else, " while already in an arg or " inside a string is invalid
          throw new ParsingException();
        }
      } else if (!inArg) {
        // encounter any character other than space or " means we're in an arg
        inArg = true;
        lastI = i;
      }
      i++;
    }
    if (inStr) {  // unclosed string is invalid
      throw new ParsingException();
    } else if (args.size() > 0) {
      command = args.get(0);
      args = args.subList(1, args.size());
    }
  }

  /**
   * Get the command name
   *
   * @return String The command name
   */
  public String getCommand() {
    return command;
  }

  /**
   * Get the argument list
   *
   * @return List The argument list
   */
  public List<String> getArgs() {
    return args;
  }

  /**
   * Get the redirection list
   *
   * @return List The redirection list
   */
  public List<String> getRedirection() {
    return redirection;
  }

  @Override
  public String toString() {
    return string;
  }
}
