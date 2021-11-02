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
package test;

import static org.junit.Assert.assertEquals;

import environment.Environment;
import exceptions.InvalidArgumentException;
import executables.Man;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManTest {

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(output));
  }

  @After
  public void tearDown() {
    System.setOut(System.out);
  }

  @Test
  public void testcommand() {
    Environment env = new Environment();
    new Man(env).execute(new Argument("exit"));
    assertEquals("Manual of exit", String.join(System.lineSeparator(),
        "NAME",
        "    exit - Exits the shell without error.",
        "SYNOPSIS",
        "    exit",
        "DESCRIPTION",
        "    Terminates the current shell process without error code",
        "EXAMPLES",
        "    exit - exits the shell") + System.lineSeparator(),
        output.toString());
  }

  @Test(expected = InvalidArgumentException.class)
  public void testerror() {
    new Man(new Environment()).execute(new Argument("find exit"));
  }
}
