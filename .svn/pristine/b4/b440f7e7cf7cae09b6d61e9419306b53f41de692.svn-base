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

import exceptions.EmptyDirectoryStackException;
import exceptions.InvalidArgumentException;
import filesystem.Directory;
import filesystem.FileSystem;
import java.util.List;
import java.util.Stack;
import output.Output;

/**
 * Represents the popd command
 */
public class Popd extends Executable {

  private FileSystem fs;

  private Stack<Directory> dirStack;

  /**
   * Create an instance
   *
   * @param fs The file system
   * @param dirStack The directory stack
   */
  public Popd(FileSystem fs, Stack<Directory> dirStack) {
    this.fs = fs;
    this.dirStack = dirStack;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    popd - Pop the directory stack, then change the working",
        "    directory to it.", "SYNOPSIS", "    popd", "DESCRIPTION",
        "    removes the top entry from the directory stack, and it becomes",
        "    the current working directory", "EXAMPLES",
        "    popd - pop from the directory stack and change the working "
            + "directory to it");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws EmptyDirectoryStackException, InvalidArgumentException {
    // Popd should have no arguments after it or throw exception
    if (args.size() != 0) {
      throw new InvalidArgumentException();
    }
    // the directory stack should not be empty when popd is called, otherwise,
    // throw exception
    if (dirStack.empty()) {
      throw new EmptyDirectoryStackException();
    }
    // If no errors, pop the directory from the stack using its method and
    // change working directory into that
    fs.setWorkingDir(dirStack.pop());
    return false;
  }
}
