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
import executables.Ls;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LsTest {

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
  public void testInvalidEntry() {
    new Ls(new Environment()).execute(new Argument("Bob"));
    assertEquals("Invalid Entry",
        "Bob: no such file or directory" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testEmptyCurrentDirectory() {
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("Empty Current Directory", "", output.toString());
  }

  @Test
  public void testOneDirectory() {
    fs.getRootDir().addChild(new Directory("dir1"));
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("Single Directory", "dir1" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testMultipleDirectories() {
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new Directory("dir2"));
    fs.getRootDir().addChild(new Directory("dir3"));
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("Multiple Directory",
        String.join(System.lineSeparator(), "dir1", "dir2", "dir3") + System
            .lineSeparator(), output.toString());

  }

  @Test
  public void testOneFile() {
    fs.getRootDir().addChild(new File("file1"));
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("Single File", "file1" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testMultipleFiles() {
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("Multiple Files",
        String.join(System.lineSeparator(), "file1", "file2", "file3") + System
            .lineSeparator(), output.toString());
  }

  @Test
  public void testOneFileOneDirectory() {
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new File("file1"));
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("One File and One Directory",
        String.join(System.lineSeparator(), "dir1", "file1") + System
            .lineSeparator(), output.toString());
  }

  @Test
  public void testMultipleFileMultipleDirectories() {
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new Directory("dir2"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new Directory("dir3"));
    fs.getRootDir().addChild(new File("file3"));
    new Ls(new Environment()).execute(new Argument(""));
    assertEquals("Multiple File and Multiple Directory", String
        .join(System.lineSeparator(), "dir1", "dir2", "dir3", "file1", "file2",
            "file3") + System.lineSeparator(), output.toString());
  }

  @Test
  public void testEmptyDirectoryofDirectory() {
    fs.getRootDir().addChild(new Directory("dir1"));
    new Ls(new Environment()).execute(new Argument("dir1"));
    assertEquals("Ls of empty directory", "dir1:" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testEmptyDirectoryofDirectories() {
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new Directory("dir2"));
    fs.getRootDir().addChild(new Directory("dir3"));
    new Ls(new Environment()).execute(new Argument("dir1 dir2 dir3"));
    assertEquals("Ls of empty directories",
        String.join(System.lineSeparator(), "dir1:", "dir2:", "dir3:") + System
            .lineSeparator(), output.toString());
  }

  @Test
  public void testSingleFileSingleDirectoryofDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("childFile1"));
    dir1.addChild(new Directory("childDir1"));

    fs.getRootDir().addChild(dir1);
    new Ls(new Environment()).execute(new Argument("dir1"));
    assertEquals("Ls of directory with a file and directory",
        "dir1: childDir1 childFile1" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testSingleFileSingleDirectoryofDirectories() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("childFile1"));
    dir1.addChild(new Directory("childDir1"));
    Directory dir2 = new Directory("dir2");
    dir2.addChild(new File("childFile2"));
    dir2.addChild(new Directory("childDir2"));

    fs.getRootDir().addChild(dir1);
    fs.getRootDir().addChild(dir2);
    new Ls(new Environment()).execute(new Argument("dir1 dir2"));
    assertEquals("Ls of directories with a file and directory", String
            .join(System.lineSeparator(), "dir1: childDir1 childFile1",
                "dir2: childDir2 childFile2") + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testEmptyDirectoryofDirectoryofDirectory() {
    Directory dir2 = new Directory("dir2");
    dir2.addChild(new Directory("dir3"));
    Directory dir1 = new Directory("dir1");
    dir1.addChild(dir2);

    fs.getRootDir().addChild(dir1);
    new Ls(new Environment()).execute(new Argument("dir1/dir2/dir3"));
    assertEquals("Ls of directory of directory",
        "dir1/dir2/dir3:" + System.lineSeparator(), output.toString());
  }
}