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

import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.util.ArrayList;
import java.util.List;
import output.Output;

/**
 * Represents the ls command
 */
public class Ls extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Ls(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    // returns a string for the manual
    return String.join(System.lineSeparator(), "NAME",
        "    ls - List directory contents.", "SYNOPSIS", "    ls [PATH ...]",
        "DESCRIPTION",
        "    Prints names of files in the working directory if no path given.",
        "    Else, for each path, if path points to file, print its name,",
        "    if path points to directory, print the directory's name followed"
            + " by its content",
        "    else, print to error that the path is invalid", "EXAMPLES",
        "    ls - prints all files (any type) in the working directory",
        "    ls a b - prints a if a is a regular file, else a: followed by "
            + "files in a, do the same for b");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err) {
    if (args.size() == 0) {
      for (General file : fs.getWorkingDir()) {
        out.sendln(file.getName());
      }
    } else {
      boolean recursive = args.get(0).equals("-R");
      List<String> pathStrs = recursive ? args.subList(1, args.size()) : args;
      for (String pathStr : pathStrs) {
        General file = new Path(fs, pathStr).getFile();
        if (file != null) {
          listContent(file, recursive, out, err);
        } else {
          err.sendln(pathStr + ": no such file or directory");
        }
      }
    }
    return false;
  }

  /**
   * List content of a general file
   *
   * @param file The general file
   * @param recursive Whether or not to list recursively
   * @param out Where to output result
   * @param err Where to output errors
   */
  private void listContent(General file, boolean recursive, Output out,
      Output err) {
    if (file instanceof File) {
      out.sendln(file.getPath());
    } else if (file instanceof Directory) {
      List<Directory> childDirs = new ArrayList<>();
      out.send(file.getPath() + ":");
      for (General child : ((Directory) file)) {
        if (child instanceof Directory) {
          childDirs.add((Directory) child);
        }
        out.send(" " + child.getName());
      }
      out.sendln();
      if (recursive) {
        for (Directory childDir : childDirs) {
          listContent(childDir, true, out, err);
        }
      }
    }
  }
}
