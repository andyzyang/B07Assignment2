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

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import exceptions.InvalidArgumentException;
import executables.Mv;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import output.Output;

public class MvTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Mv mv;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    mv = new Mv(fs);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    mv.execute(args, out, err);
  }

  @Test
  public void testMovingFileToDir() {
    Directory expected = new Directory("dir");
    File file = new File("file");
    fs.getWorkingDir().addChild(expected);
    fs.getWorkingDir().addChild(file);
    List<String> args = Arrays.asList("file", "dir");
    assertFalse(mv.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expected, file.getParent());
  }

  @Test
  public void testMovingFileToNewFile() {
    File file = new File("file");
    fs.getWorkingDir().addChild(file);
    List<String> args = Arrays.asList("file", "file1");
    assertFalse(mv.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals("file1", file.getName());
  }

  @Test
  public void testMovingFileToExistingFile() {
    File file = new File("file");
    File file1 = new File("file1");
    fs.getWorkingDir().addChild(file);
    fs.getWorkingDir().addChild(file1);
    List<String> args = Arrays.asList("file", "file1");
    assertFalse(mv.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals("file1", file.getName());
  }

  @Test
  public void testMovingDirtoDir() {
    Directory dir = new Directory("dir");
    fs.getWorkingDir().addChild(dir);
    List<String> args = Arrays.asList("dir", "dir1");
    assertFalse(mv.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals("dir1", dir.getName());
  }

  @Test
  public void testMovingDirtoExistingFile() {
    Directory dir = new Directory("dir");
    File file = new File("file");
    fs.getWorkingDir().addChild(dir);
    fs.getWorkingDir().addChild(file);
    List<String> args = Arrays.asList("dir", "file");
    assertFalse(mv.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals(
        "file: cannot overwrite file with directory" + System.lineSeparator(),
        err.toString());
  }
}
