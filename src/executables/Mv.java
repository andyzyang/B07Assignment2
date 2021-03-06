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

import java.util.List;
import exceptions.InvalidArgumentException;
import exceptions.ShellException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import output.Output;

/**
 * Represents the mv command
 */
public class Mv extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Mv(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(),
        "NAME",
        "    mv - move an item into a new name or directory.",
        "SYNOPSIS",
        "    mv OLDPATH NEWPATH",
        "DESCRIPTION",
        "    Move item OLDPATH to NEWPATH, if NEWPATH is a directory,",
        "    move the item into the directory and overwrite the file",
        "    in NEWPATH if it's of the same type. If NEWPATH does not",
        "    exist but its parent does, move OLDPATH to NEWPATH's parent",
        "    and rename OLDPATH to NEWPATH. If NEWPATH and OLDPATH are files",
        "    move OLDPATH to NEWPATH, overwriting NEWPATH",
        "EXAMPLES",
        "    mv path newpath - copies the item with a different name",
        "    mv path /dir - copies the item into another directory");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws ShellException {
    if (args.size() != 2) {
      throw new InvalidArgumentException();
    }
    Path src = new Path(fs, args.get(0));
    Path dest = new Path(fs, args.get(1));
    General srcFile = src.getFile();
    General destFile = dest.getFile();
    if (src.getFile() == null) {
      // no source file, error
      err.sendln(src + ": no such file or directory");
    } else if (srcFile instanceof Directory && (srcFile == destFile
        || ((Directory) srcFile).hasChildRec(destFile))) {
      // cannot move a directory into a subdirectory of itself, or itself
      err.sendln(dest + ": cannot move a directory into itself");
    } else if (destFile instanceof File) {
      if (srcFile instanceof File) {
        // destination and source are files, rename and overwrite
        move(srcFile, (Directory) dest.getParent(), destFile.getName());
      } else {
        // destination is a file, but source is a directory, error
        err.sendln(dest + ": cannot overwrite file with directory");
      }
    } else if (destFile instanceof Directory) {
      // destination is a directory
      General conflict =
          ((Directory) destFile).getChildByName(src.getFile().getName());
      if (conflict != null && conflict.getClass() != srcFile.getClass()) {
        err.sendln(dest + ": cannot resolve name conflict in destination");
      } else {
        move(srcFile, (Directory) destFile);
      }
    } else if (dest.getParent() instanceof Directory) {
      // destination does not exist but its parent does, move file and rename
      move(srcFile, (Directory) dest.getParent(), dest.getFileName());
    } else {
      // destination's parent does not exist
      err.sendln(dest + ": no such parent directory");
    }
    return false;
  }

  /**
   * Move a file to a directory, overwrites file of same name in the directory
   * if necessary
   *
   * @param file The file to move
   * @param dir Where to move to
   */
  private void move(General file, Directory dir) {
    if (file.getParent() != null) {
      file.getParent().removeChild(file);
    }
    dir.removeChild(dir.getChildByName(file.getName()));
    dir.addChild(file);
  }

  /**
   * Move a file to a directory, overwrites file of same name in the directory
   * if necessary
   *
   * @param file The file to move
   * @param dir Where to move to
   */
  private void move(General file, Directory dir, String name) {
    if (file.getParent() != null) {
      file.getParent().removeChild(file);
    }
    file.setName(name);
    dir.removeChild(dir.getChildByName(name));
    dir.addChild(file);
  }
}
