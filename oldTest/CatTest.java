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
import executables.Cat;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CatTest {

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
  public void testNoFile() {
    new Cat(new Environment()).execute(new Argument("Bob"));
    assertEquals("Invalid File", "Bob: no such file" + System.lineSeparator(),
        output.toString());
  }

  public void testInvalidEntry() {
    new Cat(new Environment()).execute(new Argument("Bob"));
    assertEquals("Invalid Entry", "Bob: no such file" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testEmptyFile() {
    fs.getRootDir().addChild(new File("file1"));
    new Cat(new Environment()).execute(new Argument("file1"));
    assertEquals("Empty File", "", output.toString());
  }

  @Test
  public void testSingleLineFile() {
    fs.getRootDir().addChild(new File("file1", "I love CSCB07"));
    new Cat(new Environment()).execute(new Argument("file1"));
    assertEquals("Single Line File", "I love CSCB07", output.toString());
  }

  @Test
  public void testMultipleLineFile() {
    fs.getRootDir().addChild(new File("file1",
        "I love CSCB07" + System.lineSeparator() + "CS Program"));
    new Cat(new Environment()).execute(new Argument("file1"));
    assertEquals("Multiple Line File",
        String.join(System.lineSeparator(), "I love CSCB07", "CS Program"),
        output.toString());
  }

  @Test
  public void testMultipleEmptyFiles() {
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));
    new Cat(new Environment()).execute(new Argument("file1 file2 file3"));
    assertEquals("Multiple Empty Files", "", output.toString());
  }

  @Test
  public void testSingleLineFiles() {
    fs.getRootDir().addChild(new File("file1", "I love CSCB07"));
    fs.getRootDir().addChild(new File("file2", "I am a hungry hippo"));
    fs.getRootDir().addChild(new File("file3", "I ate Computer Science"));
    new Cat(new Environment()).execute(new Argument("file1 file2 file3"));
    assertEquals("Multiple Single Line Files",
        "I love CSCB07" + "I am a hungry hippo" + "I ate Computer Science",
        output.toString());
  }

  @Test
  public void testEmptyFileInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1"));
    fs.getRootDir().addChild(dir1);
    new Cat(new Environment()).execute(new Argument("dir1/file1"));
    assertEquals("Empty File in Directory", "", output.toString());
  }

  @Test
  public void testSingleLineFileInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1", "I love CSCB07"));
    fs.getRootDir().addChild(dir1);
    new Cat(new Environment()).execute(new Argument("dir1/file1"));
    assertEquals("Single Line File in Dir", "I love CSCB07", output.toString());
  }

  @Test
  public void testEmptyFilesInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1"));
    dir1.addChild(new File("file2"));
    dir1.addChild(new File("file3"));
    fs.getRootDir().addChild(dir1);
    new Cat(new Environment()).execute(new Argument("dir1/file1"));
    assertEquals("Empty Files in Directory", "", output.toString());
  }

  @Test
  public void testMultipleLineCombinations() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1", "Apple Bees"));
    dir1.addChild(new File("file2"));
    dir1.addChild(new File("file3",
        "wow wow Nice apple Bees" + System.lineSeparator() + "Pie"));
    fs.getRootDir().addChild(dir1);
    new Cat(new Environment())
        .execute(new Argument("dir1/file1 " + "dir1/file2 dir1/file3"));
    assertEquals("Combinations in a Directory",
        "Apple Beeswow wow Nice" + " apple Bees" + System.lineSeparator()
            + "Pie", output.toString());
  }
}
