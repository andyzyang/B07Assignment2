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
import filesystem.Path;
import java.util.List;
import output.Output;

/**
 * Represents the mkdir command
 */
public class Mkdir extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Mkdir(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    mkdir - Create directories.", "SYNOPSIS", "    mkdir PATH ...",
        "DESCRIPTION", "    Create directories given path(s).", "EXAMPLES",
        "    mkdir a - create a directory named a in the working directory",
        "    mkdir a b c - create the directories a, b, c in the working "
            + "directory");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    if (args.size() < 1) {
      throw new InvalidArgumentException();
    }
    // for each path given
    for (String pathStr : args) {
      Path path = new Path(fs, pathStr);
      if (!FileSystem.isValidFileName(path.getFileName())) {
        // if filename is invalid
        err.sendln(path + ": invalid directory name");
      } else if (path.getFile() != null) {
        // if file specified by path already exists
        err.sendln(path + ": file or directory already exists");
      } else if (!(path.getParent() instanceof Directory)) {
        // if parent of file specified is not a directory
        err.sendln(path + ": no such parent directory");
      } else {
        // else, create the specified file as a directory
        ((Directory) path.getParent())
            .addChild(new Directory(path.getFileName()));
      }
    }
    return false;
  }
}
