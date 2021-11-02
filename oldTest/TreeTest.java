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
import executables.Tree;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {

  private FileSystem fs = FileSystem.getInstance();
  private ByteArrayOutputStream output;

  @Before
  public void setUp() {
    output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
  }

  @After
  public void tearDown() {
    System.setOut(System.out);
    fs.reset();
  }

  @Test
  public void test1() {
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Empty file system", "\\" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void test2() {
    fs.getRootDir().addChild(new Directory("dir"));
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Single empty dir",
        String.join(System.lineSeparator(), "\\", "\tdir", ""),
        output.toString());
  }

  @Test
  public void test3() {
    fs.getRootDir().addChild(new File("file"));
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Single file",
        String.join(System.lineSeparator(), "\\", "\tfile", ""),
        output.toString());
  }

  @Test
  public void test4() {
    Directory dir = new Directory("dir");
    dir.addChild(new File("childFile"));
    dir.addChild(new Directory("childDir"));
    fs.getRootDir().addChild(dir);
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Single dir with child dir and file",
        String.join(System.lineSeparator(),
            "\\",
            "\tdir",
            "\t\tchildDir",
            "\t\tchildFile",
            ""), output.toString());
  }

  @Test
  public void test5() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("childFile1"));
    dir1.addChild(new Directory("childDir1"));
    Directory dir2 = new Directory("dir2");
    dir2.addChild(new File("childFile2"));
    dir2.addChild(new Directory("childDir2"));
    Directory dir3 = new Directory("dir3");
    dir3.addChild(new File("childFile3"));
    dir3.addChild(new Directory("childDir3"));

    fs.getRootDir().addChild(dir1);
    fs.getRootDir().addChild(dir2);
    fs.getRootDir().addChild(dir3);
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Multiple dirs with child dir and file",
        String.join(System.lineSeparator(),
            "\\",
            "\tdir1",
            "\t\tchildDir1",
            "\t\tchildFile1",
            "\tdir2",
            "\t\tchildDir2",
            "\t\tchildFile2",
            "\tdir3",
            "\t\tchildDir3",
            "\t\tchildFile3",
            ""), output.toString());
  }

  @Test
  public void test6() {
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Files only",
        String.join(System.lineSeparator(),
            "\\",
            "\tfile1",
            "\tfile2",
            "\tfile3",
            ""), output.toString());
  }

  @Test
  public void test7() {
    fs.getRootDir().addChild(new Directory("file1"));
    fs.getRootDir().addChild(new Directory("file2"));
    fs.getRootDir().addChild(new Directory("file3"));
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Directories only",
        String.join(System.lineSeparator(),
            "\\",
            "\tfile1",
            "\tfile2",
            "\tfile3",
            ""), output.toString());
  }

  @Test
  public void test8() {
    fs.getRootDir().addChild(new Directory("file1"));
    fs.getRootDir().addChild(new Directory("file2"));
    fs.getRootDir().addChild(new Directory("file3"));
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Files and empty directories",
        String.join(System.lineSeparator(),
            "\\",
            "\tfile1",
            "\tfile2",
            "\tfile3",
            ""), output.toString());
  }

  @Test
  public void test9() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("childFile1"));
    dir1.addChild(new Directory("childDir1"));
    Directory dir2 = new Directory("dir2");
    dir2.addChild(new File("childFile2"));
    dir2.addChild(new Directory("childDir2"));
    Directory dir3 = new Directory("dir3");
    dir3.addChild(new File("childFile3"));
    dir3.addChild(new Directory("childDir3"));

    fs.getRootDir().addChild(dir1);
    fs.getRootDir().addChild(dir2);
    fs.getRootDir().addChild(dir3);
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));
    fs.getRootDir().addChild(new Directory("emptyDir"));
    new Tree(new Environment()).execute(new Argument(""));
    assertEquals("Files and empty and non-empty directories",
        String.join(System.lineSeparator(),
            "\\",
            "\tdir1",
            "\t\tchildDir1",
            "\t\tchildFile1",
            "\tdir2",
            "\t\tchildDir2",
            "\t\tchildFile2",
            "\tdir3",
            "\t\tchildDir3",
            "\t\tchildFile3",
            "\temptyDir",
            "\tfile1",
            "\tfile2",
            "\tfile3",
            ""), output.toString());
  }


}
