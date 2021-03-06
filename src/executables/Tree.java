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
import java.util.List;
import output.Output;

/**
 * Represents the tree command
 */
public class Tree extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Tree(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    // return a string for the manual
    return String.join(System.lineSeparator(), "NAME",
        "    tree - Print the filesystem as a tree.", "SYNOPSIS", "    tree",
        "DESCRIPTION",
        "    Prints the tree for the entire file system, with the leaves as",
        "    the directories and files of the filesystem.", "EXAMPLES",
        "    tree - prints the whole file system as a tree");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // tree should have no arguments or error
    if (args.size() > 0) {
      throw new InvalidArgumentException();
    }
    out.send("\\");
    // call the drawtree command to print the file system tree
    drawTree(fs.getRootDir(), "", out);
    // shell doesn't exit
    return false;
  }

  /**
   * Print the file system as a tree
   *
   * @param file The root of the tree
   * @param head Tabs appended to the filename, tabs added per depth of tree
   * @param out Where to send output
   */
  private void drawTree(General file, String head, Output out) {
    // draw the head first, then the file
    out.sendln(head + file.getName());
    if (file instanceof Directory) {
      // for each child of parent directory, draw its own tree
      for (General child : ((Directory) file)) {
        // draw the tree recursively
        drawTree(child, head + "\t", out);
      }
    }
  }
}
