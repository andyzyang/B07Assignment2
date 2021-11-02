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
import executables.Mkdir;
import filesystem.Directory;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MkdirTest {

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
  public void testcreatefile() {
    Environment env = new Environment();
    new Mkdir(env).execute(new Argument(" dir1"));
    General file = new Path("/").getFile();
    for (General child : ((Directory) file).getChildren()) {
      env.getConsole().sendOut(" " + child.getName());
    }
    assertEquals("One file", " dir1", output.toString());
  }

  @Test
  public void testcreatefiles() {
    Environment env = new Environment();
    new Mkdir(env).execute(new Argument(" dir1 dir2"));
    General file = new Path("/").getFile();
    for (General child : ((Directory) file).getChildren()) {
      env.getConsole().sendOut(" " + child.getName());
    }
    assertEquals("Two files", " dir1 dir2", output.toString());
  }

  @Test
  public void testcreatefilewithpath() {
    Environment env = new Environment();
    new Mkdir(env).execute(new Argument(" dir1"));
    new Mkdir(env).execute(new Argument(" dir1/dir2"));
    General file = new Path("/dir1").getFile();
    for (General child : ((Directory) file).getChildren()) {
      env.getConsole().sendOut(" " + child.getName());
    }
    assertEquals("One file", " dir2", output.toString());
  }

  @Test
  public void test4() {
    Environment env = new Environment();
    new Mkdir(env).execute(new Argument(" dir1"));
    new Mkdir(env).execute(new Argument(" dir1"));
    assertEquals("Error message dir already exists",
        "dir1: file or directory already exists" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void test6() {
    Environment env = new Environment();
    new Mkdir(env).execute(new Argument(" dir1"));
    new Mkdir(env).execute(new Argument(" dir1/dir2"));
    new Mkdir(env).execute(new Argument(" /dir1/dir2/dir3"));
    General file = new Path("/dir1/dir2").getFile();
    for (General child : ((Directory) file).getChildren()) {
      env.getConsole().sendOut(" " + child.getName());
    }
    assertEquals("One file", " dir3", output.toString());
  }

  @Test(expected = InvalidArgumentException.class)
  public void test5() {
    new Mkdir(new Environment()).execute(new Argument(""));
  }
}
