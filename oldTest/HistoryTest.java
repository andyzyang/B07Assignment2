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
import executables.History;
import input.Argument;
import input.UserInput;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HistoryTest {

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
  public void testempty() {
    Environment env = new Environment();
    new History(env).execute(new Argument(""));
    assertEquals("Empty string", "", output.toString());
  }

  @Test
  public void testnoarg() {
    Environment env = new Environment();
    env.getInputHistory().add(new UserInput("tree"));
    env.getInputHistory().add(new UserInput("exit"));
    new History(env).execute(new Argument(""));
    assertEquals("Two commands",
        "1. tree" + System.lineSeparator() + "2. exit" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testnumarg() {
    Environment env = new Environment();
    env.getInputHistory().add(new UserInput("tree"));
    env.getInputHistory().add(new UserInput("exit"));
    env.getInputHistory().add(new UserInput("history"));
    env.getInputHistory().add(new UserInput("ls"));
    new History(env).execute(new Argument("2"));
    assertEquals("Two commands",
        "3. history" + System.lineSeparator() + "4. ls" + System
            .lineSeparator(), output.toString());
  }

  @Test
  public void testnumarggreater() {
    Environment env = new Environment();
    env.getInputHistory().add(new UserInput("tree"));
    env.getInputHistory().add(new UserInput("exit"));
    env.getInputHistory().add(new UserInput("history"));
    env.getInputHistory().add(new UserInput("ls"));
    new History(env).execute(new Argument("4"));
    assertEquals("Four commands",
        "1. tree" + System.lineSeparator() + "2. exit" + System.lineSeparator()
            + "3. history" + System.lineSeparator() + "4. ls" + System
            .lineSeparator(), output.toString());
  }

  @Test
  public void testusersyntaxerror() {
    Environment env = new Environment();
    env.getInputHistory().add(new UserInput("cmd   arg"));
    env.getInputHistory().add(new UserInput("   cmd   arg"));
    env.getInputHistory().add(new UserInput("cmd   arg   "));
    env.getInputHistory().add(new UserInput("cmd arg"));
    new History(env).execute(new Argument("4"));
    assertEquals("Four commands",
        "1. cmd   arg" + System.lineSeparator() + "2.    cmd   arg" + System
            .lineSeparator() + "3. cmd   arg   " + System.lineSeparator()
            + "4. cmd arg" + System.lineSeparator(), output.toString());
  }

  @Test(expected = InvalidArgumentException.class)
  public void testerror() {
    new History(new Environment()).execute(new Argument("5 6"));
  }
}
