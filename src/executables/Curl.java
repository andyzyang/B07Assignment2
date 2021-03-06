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
import filesystem.File;
import filesystem.FileSystem;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import output.Output;

/**
 * Represents the curl command
 */
public class Curl extends Executable {

  private FileSystem fs;

  /**
   * Create an instance
   *
   * @param fs The file system
   */
  public Curl(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(),
        "NAME",
        "    curl - Download file from url.",
        "SYNOPSIS",
        "    curl URL",
        "DESCRIPTION",
        "    Download file from URL to the working directory.",
        "EXAMPLES",
        "    curl http://site.com/a.txt - save a.txt to working directory");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    if (args.size() != 1) {
      throw new InvalidArgumentException();
    }
    String url = args.get(0);
    String name = url.substring(url.lastIndexOf('/') + 1);
    if (fs.getWorkingDir().getChildByName(name) != null) {
      err.sendln(name + ": file or directory already exists");
    } else {
      try {
        fs.getWorkingDir().addChild(new File(name,
            new Scanner(new URL(url).openStream()).useDelimiter("\\A").next()));
      } catch (MalformedURLException e) {
        throw new InvalidArgumentException();
      } catch (IOException e) {
        throw new ShellException("error retrieving file from URL");
      }
    }
    return false;
  }
}
