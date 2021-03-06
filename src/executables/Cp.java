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
import exceptions.ShellException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.util.List;
import output.Output;

/**
 * Represents the cp command
 */
public class Cp extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Cp(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    cp - copy an item into a new name or directory.", "SYNOPSIS",
        "    cp OLDPATH NEWPATH",
        "DESCRIPTION",
        "    Copy item OLDPATH to NEWPATH, if NEWPATH is a directory,",
        "    copy the item into the directory and overwrite the file",
        "    in NEWPATH if it's of the same type. If NEWPATH does not",
        "    exist but its parent does, move OLDPATH to NEWPATH's parent",
        "    and rename OLDPATH to NEWPATH. If NEWPATH and OLDPATH are files",
        "    move OLDPATH to NEWPATH, overwriting NEWPATH",
        "EXAMPLES",
        "    cp path newpath - copies the item with a different name",
        "    cp path /dir - copies the item into another directory");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws ShellException {
    // Inform user if the number of arguments is not 1
    if (args.size() != 2) {
      throw new InvalidArgumentException();
    }
    Path src = new Path(fs, args.get(0));
    Path dest = new Path(fs, args.get(1));
    if (src.getFile() == null) {
      // no source file, error
      err.sendln(src + ": no such file or directory");
    } else if (dest.getFile() instanceof File) {
      if (src.getFile() instanceof File) {
        // destination and source are files, rename them overwrite
        copy(src.getFile(), (Directory) dest.getParent(),
            dest.getFile().getName());
      } else {
        // destination is a file, but source is a directory, error
        err.sendln(dest + ": cannot overwrite file with directory");
      }
    } else if (dest.getFile() instanceof Directory) {
      // destination is a directory, check for conflict in destination
      General conflict = ((Directory) dest.getFile())
          .getChildByName(src.getFile().getName());
      if (conflict != null && conflict.getClass() != src.getFile().getClass()) {
        err.sendln(dest + ": cannot resolve name conflict in destination");
      } else {
        copy(src.getFile(), (Directory) dest.getFile());
      }
    } else if (dest.getParent() instanceof Directory) {
      // destination does not exist but its parent does, copy file and rename
      copy(src.getFile(), (Directory) dest.getParent(),
          dest.getFileName());
    } else {
      // destination's parent does not exist
      err.sendln(dest + ": no such parent directory");
    }
    return false;
  }

  /**
   * Copy a file to a directory, overwrites file of same name in the directory
   * if necessary
   *
   * @param file The file to copy
   * @param dir Where to copy to
   */
  private void copy(General file, Directory dir) {
    dir.removeChild(dir.getChildByName(file.getName()));
    dir.addChild(file.clone());
  }

  /**
   * Copy a file to a directory under a new filename, overwrites file of same
   * name in the directory if necessary
   *
   * @param file The file to copy
   * @param dir Where to copy to
   */
  private void copy(General file, Directory dir, String name) {
    dir.removeChild(dir.getChildByName(name));
    General clone = file.clone();
    clone.setName(name);
    dir.addChild(clone);
  }
}
