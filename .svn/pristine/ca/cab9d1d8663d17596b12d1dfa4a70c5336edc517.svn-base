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
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.util.List;
import output.Output;

/**
 * Represents the cat command
 */
public class Cat extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Cat(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(),
        "NAME",
        "    cat - Concatenate and print to standard output.",
        "SYNOPSIS",
        "    cat FILE1 [FILE2 ...]",
        "DESCRIPTION",
        "    Contatenate and Display the content of FILE(s) FILE1",
        "    FILE2 ... to standard output.",
        "EXAMPLES",
        "    cat file - prints the content of file",
        "    cat file1 file2 - prints the content of file1 concatenate file2");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    // Inform user if there is no argument
    if (args.size() < 1) {
      throw new InvalidArgumentException();
    }
    // Loop through each argument
    for (String path : args) {
      General file = new Path(fs, path).getFile();
      if (file instanceof File) {
        // If the argument is a valid file, print it's content
        out.send(((File) file).getContent());
      } else {
        // else inform use that argument is an invalid file
        err.sendln(path + ": no such file");
      }
    }
    // As command does not exit the shell, return false
    return false;
  }
}
