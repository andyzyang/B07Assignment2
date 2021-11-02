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
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import exceptions.InvalidRedirectionException;
import exceptions.ParsingException;
import exceptions.ShellException;
import filesystem.FileSystem;
import filesystem.Path;
import input.UserInput;
import java.util.List;
import output.Console;
import output.FileWriter;
import output.Output;

/**
 * Executes user input in a given environment
 */
public class Executor {

  /**
   * The environment to execute in
   */
  private Environment env;

  /**
   * Create an executor for an environment
   *
   * @param env The environment
   */
  public Executor(Environment env) {
    this.env = env;
  }

  /**
   * Executes raw user input
   *
   * @param string The user input string
   * @return boolean Whether or not to exit the shell
   */
  public boolean execute(String string) {
    boolean exit = false;
    Output err = new Console();
    try {
      UserInput input = new UserInput(string);
      try {
        if (input.getCommand().length() > 0) {
          // replace input from history if needed
          input = processReplacement(input);
          // log the input
          env.getInputHistory().add(input);
          // make sure command is valid
          if (!env.getCommandMap().containsKey(input.getCommand())) {
            throw new InvalidCommandException();
          }
          // set where to output normal output based on redirection argument
          Output out = getStandardOutput(input.getRedirection());
          // execute the command
          exit = env.getCommandMap().get(input.getCommand())
              .execute(input.getArgs(), out, err);
        }
      } catch (ShellException e) {
        err.sendln(input.getCommand() + ": " + e.getMessage());
      }
    } catch (ParsingException e) {
      err.sendln("JShell: error parsing input");
    }
    return exit;
  }

  /**
   * Get the output device based on redirection argument
   *
   * @param redirection The redirection argument list
   * @return Output The output device
   */
  private Output getStandardOutput(List<String> redirection) {
    if (redirection.size() == 0) {
      return new Console();
    } else if (redirection.size() == 2) {
      Path path = new Path(env.getFs(), redirection.get(1));
      if (!FileSystem.isValidFileName(path.getFileName())) {
        throw new InvalidRedirectionException();
      }
      return new FileWriter(path, redirection.get(0).equals(">"));
    }
    throw new InvalidRedirectionException();
  }

  /**
   * Process replacement for user input when using !num
   *
   * @param input The user input
   * @return UserInput The user input with replacement processed
   */
  private UserInput processReplacement(UserInput input) {
    if (input.getCommand().charAt(0) == '!') {
      // check that ! is followed by a valid integer, and args is empty
      String numStr = input.getCommand()
          .substring(1, input.getCommand().length());
      if (numStr.matches("[0-9]+") && Integer.parseInt(numStr) > 0
          && Integer.parseInt(numStr) <= env.getInputHistory().size() && input
          .getArgs().isEmpty()) {
        // replace input with an input from history
        return env.getInputHistory().get(Integer.parseInt(numStr) - 1);
      } else {
        throw new ShellException("invalid history number argument");
      }
    }
    return input;
  }
}
