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
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.util.List;
import output.Output;


/**
 * Represents the find command
 */
public class Find extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Find(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    // returns a string for the manual
    return String.join(System.lineSeparator(), "NAME",
        "    find - find a file or directory.", "SYNOPSIS",
        "    find PATH ... -type [f|d] -name EXPRESSION", "DESCRIPTION",
        "    Finds all files or directories whose name matches EXPRESSION",
        "    where EXPRESSION is any characters surrounded by double quotes.",
        "    If -type d, find directories only. If -type f, find files only.",
        "EXAMPLES",
        "    find . -type d -name \"a\" - print the path of all directories "
            + "named a in .",
        "    find dir1 path/dir2 /dir3 -type f -name \"a\" - print the path "
            + "of all files named a in dir1, path/dir2, and /dir3");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    if (args.size() < 5 || !args.get(args.size() - 4).equals("-type") || !args
        .get(args.size() - 3).matches("[f|d]") || !args.get(args.size() - 2)
        .equals("-name")) {
      throw new InvalidArgumentException();
    }
    for (int i = 0; i < args.size() - 4; i++) {
      Path path = new Path(fs, args.get(i));
      if (path.getFile() instanceof Directory) {
        List<General> foundFiles = ((Directory) path.getFile())
            .getChildrenByNameRec(args.get(args.size() - 1));
        for (General file : foundFiles) {
          if ((args.get(args.size() - 3).equals("f") && file instanceof File)
              || (args.get(args.size() - 3).equals("d")
              && file instanceof Directory)) {
            out.sendln(file.getPath());
          }
        }
        if (foundFiles.size() == 0) {
          err.sendln(path + ": nothing found");
        }
      } else {
        err.sendln(path + ": not a directory");
      }
    }
    return false;
  }
}
