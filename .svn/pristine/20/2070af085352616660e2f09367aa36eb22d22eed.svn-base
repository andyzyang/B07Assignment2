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
import java.util.List;
import output.Output;

/**
 * Represents the echo command
 */
public class Echo extends Executable {

  @Override
  public String getManual() {
    return String.join(System.lineSeparator(), "NAME",
        "    echo - Print a string to standard output, or a file.", "SYNOPSIS",
        "    echo STRING [{>, >>} FILE]", "DESCRIPTION",
        "    STRING is a string of characters surrounded by quotation marks.",
        "    FILE is either the path to a file, or specifies a file whose",
        "    parent directory exists. If only STRING is given, print it.",
        "    If > is given, the content of FILE is replaced by STRING",
        "    If >> is given, the string is appended to the file.", "EXAMPLES",
        "    echo \"text\" - prints text",
        "    echo \"text\" > file - set content of file to text, creating it "
            + "if necessary",
        "    echo \"text\" >> file - append text to file, creating it if "
            + "necessary");
  }

  @Override
  public boolean execute(List<String> args, Output out, Output err)
      throws InvalidArgumentException {
    if (args.size() != 1) {
      throw new InvalidArgumentException();
    }
    out.sendln(args.get(0));
    return false;
  }
}
