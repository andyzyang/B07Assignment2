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
import output.Output;

/**
 * Represents the cd command
 */
public class Cd extends Executable {

  private FileSystem fs;

  /**
   * Create an instance given execution environment
   *
   * @param fs The file system
   */
  public Cd(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(),
        "NAME",
        "    cd - Change the shell's working directory.",
        "SYNOPSIS",
        "    cd DIR",
        "DESCRIPTION",
        "    Change the shell's working directory to DIR, where DIR is a path",
        "    to a directory",
        "EXAMPLES",
        "    cd dir - changes the working directory to dir",
        "    cd /path/to/dir - changes the working directory to /path/to/dir");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // Inform user if the number of arguments is not 1
    if (args.size() != 1) {
      throw new InvalidArgumentException();
    }
    General dir = new Path(fs, args.get(0)).getFile();
    if (dir instanceof Directory) {
      // Change current directory to argument if the directory exists
      fs.setWorkingDir((Directory) dir);
    } else {
      // Inform user if directory doesn't exist
      err.sendln(args.get(0) + ": no such directory");
    }
    // Return false as cd does not exit the shell
    return false;
  }
}
