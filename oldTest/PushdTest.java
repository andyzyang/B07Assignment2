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
import executables.Mkdir;
import executables.Pushd;
import executables.Pwd;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PushdTest {

  private final FileSystem fs = FileSystem.getInstance();
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(output));
  }

  @After
  public void tearDown() {
    System.setOut(System.out);
    fs.reset();
  }

  @Test
  public void testpushrootdirectoryonstack() {
    Environment env = new Environment();
    new Pushd(env).execute(new Argument("/"));
    new Pwd(env).execute(new Argument(""));
    assertEquals(fs.getRootDir(), env.getDirStack().pop());
    assertEquals("/" + System.lineSeparator(), output.toString());
  }

  @Test
  public void testpushnewdirectoryonstack() {
    Environment env = new Environment();
    new Mkdir(env).execute(new Argument("dir1"));
    new Pushd(env).execute(new Argument("dir1"));
    new Pwd(env).execute(new Argument(""));
    assertEquals(fs.getRootDir(), env.getDirStack().pop());
    assertEquals("/dir1" + System.lineSeparator(), output.toString());
  }
}
