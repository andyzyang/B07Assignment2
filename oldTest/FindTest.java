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
import executables.Find;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FindTest {

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
  public void testFileInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("ComputerScience"));
    fs.getRootDir().addChild(dir1);
    new Find(new Environment()).execute(new Argument("./" +
        " -type f -name \"ComputerScience\""));
    assertEquals("FileInDirectory", "/dir1/ComputerScience" +
        System.lineSeparator(), output.toString());
  }

  @Test
  public void testDirectoryInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new Directory("dir2"));
    fs.getRootDir().addChild(dir1);
    new Find(new Environment()).execute(new Argument("./" +
        " -type d -name \"dir2\""));
    assertEquals("FileInDirectory", "/dir1/dir2" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testWrongTypeInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new Directory("dir2"));
    fs.getRootDir().addChild(dir1);
    new Find(new Environment()).execute(new Argument("./" +
        " -type f -name \"dir2\""));
    assertEquals("FileInDirectory", "", output.toString());
  }

  @Test
  public void testMultipleDirectoriesForFile() {
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");
    Directory dir3 = new Directory("dir3");
    Directory dir4 = new Directory("dir4");
    dir1.addChild(new File("file1"));
    dir1.addChild(new File("file2"));
    dir2.addChild(new File("file3"));
    dir3.addChild(new File("file4"));
    dir3.addChild(new File("file5"));
    dir4.addChild(new File("file6"));
    dir1.addChild(dir2);
    fs.getRootDir().addChild(dir1);
    fs.getRootDir().addChild(dir3);
    fs.getRootDir().addChild(dir4);
    new Find(new Environment()).execute(new Argument("./" +
        " -type f -name \"file2\""));
    new Find(new Environment()).execute(new Argument("./" +
        " -type d -name \"dir2\""));
    new Find(new Environment()).execute(new Argument("./" +
        " -type f -name \"file3\""));
    new Find(new Environment()).execute(new Argument("./" +
        " -type f -name \"file6\""));
    new Find(new Environment()).execute(new Argument("./" +
        " -type f -name \"file5\""));
    assertEquals("FileInDirectories",
        "/dir1/file2" + System.lineSeparator() +
            "/dir1/dir2" + System.lineSeparator() +
            "/dir1/dir2/file3" + System.lineSeparator() +
            "/dir4/file6" + System.lineSeparator() +
            "/dir3/file5" + System.lineSeparator(),
        output.toString());
  }

}
