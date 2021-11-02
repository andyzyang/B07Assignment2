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
import filesystem.Directory;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.util.List;
import java.util.Stack;
import output.Output;

/**
 * Represents the pushd command
 */
public class Pushd extends Executable {

  private FileSystem fs;

  private Stack<Directory> dirStack;

  /**
   * Create an instance
   *
   * @param fs The file system
   * @param dirStack The directory stack
   */
  public Pushd(FileSystem fs, Stack<Directory> dirStack) {
    this.fs = fs;
    this.dirStack = dirStack;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    pushd - Push the working directory into the directory stack.",
        "SYNOPSIS", "    pushd [DIR]", "DESCRIPTION",
        "    Pushes the working directory into the directory stack and",
        "    changes the new current working directory to the directory given",
        "EXAMPLES",
        "    pushd dir - push the working directory to the directory stack "
            + "and change the working directory to dir");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // pushd currently only supports 1 directory argument
    if (args.size() != 1) {
      throw new InvalidArgumentException();
    }
    // gets the argument as a general file first
    General dir = new Path(fs, args.get(0)).getFile();
    // if it is a directory
    if (dir instanceof Directory) {
      // push the directory into the stack using its method
      dirStack.push(fs.getWorkingDir());
      // set the new directory as the one specified
      fs.setWorkingDir((Directory) dir);
    } else {
      // if the directory doesn't exist
      err.sendln(args.get(0) + ": no such directory");
    }
    return false;
  }
}
