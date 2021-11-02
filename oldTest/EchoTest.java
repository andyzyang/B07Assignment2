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
import executables.Echo;
import filesystem.File;
import filesystem.FileSystem;
import input.Argument;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EchoTest {

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
  public void testNoRedirection1() {
    new Echo(new Environment()).execute(new Argument("\"\""));
    assertEquals("Empty string", "" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection2() {
    new Echo(new Environment()).execute(new Argument("\" \""));
    assertEquals("Single space", " " + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection3() {
    new Echo(new Environment()).execute(new Argument("\"   \""));
    assertEquals("Multiple spaces", "   " + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection4() {
    new Echo(new Environment()).execute(new Argument("\"a\""));
    assertEquals("Single character", "a" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection5() {
    new Echo(new Environment()).execute(new Argument("\"text\""));
    assertEquals("Single word", "text" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection6() {
    new Echo(new Environment()).execute(new Argument("\"  text\""));
    assertEquals("Word with spaces in front", "  text" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection7() {
    new Echo(new Environment()).execute(new Argument("\"  text  \""));
    assertEquals("Word with spaces around", "  text  " + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection8() {
    new Echo(new Environment())
        .execute(new Argument("\"word1 word2 word3\""));
    assertEquals("Words", "word1 word2 word3" + System.lineSeparator(),
        output.toString());
  }

  @Test
  public void testNoRedirection9() {
    new Echo(new Environment())
        .execute(new Argument("\"  word1 word2 word3\""));
    assertEquals("Words with spaces in front",
        "  word1 word2 word3" + System.lineSeparator(), output.toString());
  }

  @Test
  public void testNoRedirection10() {
    new Echo(new Environment())
        .execute(new Argument("\"  word1 word2 word3  \""));
    assertEquals("Words with spaces around",
        "  word1 word2 word3  " + System.lineSeparator(), output.toString());
  }

  @Test
  public void testSetContent1() {
    new Echo(new Environment()).execute(new Argument("\"\" > file"));
    assertEquals("Empty string to new file", "" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testSetContent2() {
    new Echo(new Environment()).execute(new Argument("\"text\" > file"));
    assertEquals("Word to new file", "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testSetContent3() {
    fs.getRootDir().addChild(new File("file", "content"));
    new Echo(new Environment()).execute(new Argument("\"text\" > file"));
    assertEquals("Word to existing file", "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testSetContent4() {
    new Echo(new Environment()).execute(new Argument("\"text\" > file"));
    assertEquals("Words to new file", "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testSetContent5() {
    fs.getRootDir().addChild(new File("file", "content"));
    new Echo(new Environment()).execute(new Argument("\"text\" > file"));
    assertEquals("Words to existing file", "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testSetContent6() {
    fs.getRootDir().addChild(new File("file", "content"));
    new Echo(new Environment()).execute(new Argument("\"\" > file"));
    assertEquals("Empty string to existing file", "" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testAppendContent1() {
    new Echo(new Environment()).execute(new Argument("\"\" >> file"));
    assertEquals("Empty string to new file", "" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testAppendContent2() {
    new Echo(new Environment()).execute(new Argument("\"text\" >> file"));
    assertEquals("Word to new file", "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testAppendContent3() {
    fs.getRootDir()
        .addChild(new File("file", "content" + System.lineSeparator()));
    new Echo(new Environment()).execute(new Argument("\"text\" >> file"));
    assertEquals("Word to existing file",
        "content" + System.lineSeparator() + "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testAppendContent4() {
    new Echo(new Environment()).execute(new Argument("\"text\" >> file"));
    assertEquals("Words to new file", "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testAppendContent5() {
    fs.getRootDir()
        .addChild(new File("file", "content" + System.lineSeparator()));
    new Echo(new Environment()).execute(new Argument("\"text\" >> file"));
    assertEquals("Words to existing file",
        "content" + System.lineSeparator() + "text" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }

  @Test
  public void testAppendContent6() {
    fs.getRootDir().addChild(new File("file", "content"));
    new Echo(new Environment()).execute(new Argument("\"\" >> file"));
    assertEquals("Empty string to existing file",
        "content" + System.lineSeparator(),
        ((File) fs.getRootDir().getChildByName("file")).getContent());
  }
}
