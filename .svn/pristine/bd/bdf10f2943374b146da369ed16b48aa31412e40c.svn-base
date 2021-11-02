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
import executables.Pwd;
import filesystem.Directory;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PwdTest {

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
  public void testRootDirectory() {
    new Pwd(new Environment()).execute(new Argument(""));
    assertEquals("Empty Root Directory", "/" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testPathOfDirectory() {
    Directory dir1 = new Directory("dir1");
    fs.getRootDir().addChild(dir1);
    fs.setWorkingDir(dir1);
    new Pwd(new Environment()).execute(new Argument(""));
    assertEquals("Path of Single Directory", "/dir1" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testPathOfDirectories() {
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");
    Directory dir3 = new Directory("dir3");
    Directory dir4 = new Directory("dir4");
    dir3.addChild(dir4);
    dir2.addChild(dir3);
    dir1.addChild(dir2);
    fs.getRootDir().addChild(dir1);
    fs.setWorkingDir(dir4);
    new Pwd(new Environment()).execute(new Argument(""));
    assertEquals("Path of Multiple Directory", "/dir1/dir2/dir3/dir4" +
        System.lineSeparator(), output.toString());
  }
}
