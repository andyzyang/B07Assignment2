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
import filesystem.Path;
import java.util.List;
import java.util.regex.Pattern;
import output.Output;


/**
 * Represents the grep command
 */
public class Grep extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Grep(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(),
        "NAME",
        "    grep - Find lines from files.",
        "SYNOPSIS",
        "    grep [-R] REGEX PATH ...",
        "DESCRIPTION",
        "    If -R, for each PATH, if PATH is a file print all lines matching",
        "    REGEX, if PATH is a directory, print all lines of all files in",
        "    PATH matching REGEX recursively. If -R not given, PATH must be",
        "    files.",
        "EXAMPLES",
        "    grep a a.txt - find all lines matching a in a.txt",
        "    grep -R a dir - find all lines matching a in dir recursively");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    if (args.size() < 2 || (args.get(0).equals("-R") && args.size() < 3)) {
      throw new InvalidArgumentException();
    }
    if (args.get(0).equals("-R")) {
      // get the regex
      Pattern pattern = Pattern.compile(args.get(1));
      // for each given path
      for (String pathStr : args.subList(2, args.size())) {
        Path path = new Path(fs, pathStr);
        // check that path points to a directory
        if (path.getFile() instanceof Directory) {
          // loop through files in directory, recursively
          for (File file : ((Directory) path.getFile()).getFilesRec()) {
            // print all matching lines, include path
            findLines(file, pattern, true, out, err);
          }
        } else if (path.getFile() instanceof File) {
          findLines((File) path.getFile(), pattern, true, out, err);
        } else {
          err.sendln(path + ": no such file or directory");
        }
      }
    } else {
      // get the regex
      Pattern pattern = Pattern.compile(args.get(0));
      // for each given path
      for (String pathStr : args.subList(1, args.size())) {
        Path path = new Path(fs, pathStr);
        // check that path points to a file
        if (path.getFile() instanceof File) {
          // print all matching lines
          findLines((File) path.getFile(), pattern, args.size() > 2, out, err);
        } else {
          err.sendln(path + ": no such file");
        }
      }
    }
    return false;
  }

  /**
   * Find lines matching a pattern in a file
   *
   * @param file The file to search
   * @param pattern The pattern to match
   * @param includePath Whether or not to include the path in output
   * @param out Where to output result
   * @param err Where to output errors
   */
  private void findLines(File file, Pattern pattern, boolean includePath,
      Output out, Output err) {
    for (String ln : file.getContent().split(System.lineSeparator())) {
      if (pattern.matcher(ln).find()) {
        out.sendln((includePath ? file.getPath() + ":" : "") + ln);
      }
    }
  }
}
