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

import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PathTest {

  private final FileSystem fs = FileSystem.getInstance();
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(output));
    Directory dir1 = new Directory("dir");
    dir1.addChild(new File("file"));
    Directory dir2 = new Directory("dir");
    dir2.addChild(new File("file"));
    Directory workingDir = new Directory("home");
    workingDir.addChild(new File("file"));
    workingDir.addChild(dir1);
    fs.getRootDir().addChild(dir2);
    fs.getRootDir().addChild(workingDir);
    fs.setWorkingDir(workingDir);
    fs.getRootDir().addChild(new File("file"));
  }

  @After
  public void tearDown() {
    System.setOut(System.out);
    fs.reset();
  }

  @Test
  public void testFromStringFilesList1() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    assertEquals("Empty string is the minimal relative path", expected,
        new Path("").getFilesList());
  }

  @Test
  public void testFromStringFilesList2() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    assertEquals("/ is the minimal absolute path", expected,
        new Path("/").getFilesList());
  }

  @Test
  public void testFromStringFilesList3() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    expected.add(fs.getWorkingDir());
    assertEquals(". is relative path to the working dir", expected,
        new Path(".").getFilesList());
  }

  @Test
  public void testFromStringFilesList4() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    expected.add(fs.getWorkingDir().getParent());
    assertEquals(".. is relative path to the parent dir", expected,
        new Path("").getFilesList());
  }

  @Test
  public void testFromStringFilesList5() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    assertEquals("Multiple /'s", expected, new Path("///").getFilesList());
  }

  @Test
  public void testFromStringFilesList6() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    expected.add(fs.getRootDir());
    expected.add(fs.getRootDir());
    assertEquals("Absolute path with multiple .'s", expected,
        new Path("/././").getFilesList());
  }

  @Test
  public void testFromStringFilesList7() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    expected.add(fs.getWorkingDir());
    expected.add(fs.getWorkingDir());
    assertEquals("Relative path with multiple .'s", expected,
        new Path("././").getFilesList());
  }

  @Test
  public void testFromStringFilesList8() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    expected.add(fs.getWorkingDir().getChildByName("file"));
    assertEquals("Relative path to file in working dir", expected,
        new Path("file").getFilesList());
  }

  @Test
  public void testFromStringFilesList9() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    expected.add(fs.getRootDir().getChildByName("file"));
    assertEquals("Absolute path to file in root dir", expected,
        new Path("/file").getFilesList());
  }

  @Test
  public void testFromStringFilesList10() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    expected.add(fs.getWorkingDir().getChildByName("dir"));
    expected.add(null);
    assertEquals("Relative non existent file, but existent parent", expected,
        new Path("dir/file2").getFilesList());
  }


  @Test
  public void testFromStringFilesList11() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    expected.add(fs.getRootDir().getChildByName("dir"));
    expected.add(null);
    assertEquals("Absolute non existent file, but existent parent", expected,
        new Path("/dir/file2").getFilesList());
  }


  @Test
  public void testFromStringFilesList12() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getWorkingDir());
    expected.add(null);
    expected.add(null);
    assertEquals("Relative non existent file and parent", expected,
        new Path("dir2/file").getFilesList());
  }

  @Test
  public void testFromStringFilesList13() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    expected.add(null);
    expected.add(null);
    assertEquals("Absolute non existent file and parent", expected,
        new Path("/dir2/file").getFilesList());
  }

  @Test
  public void testFromFileFilesList1() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    assertEquals("Root directory", expected,
        new Path(fs.getRootDir()).getFilesList());
  }

  @Test
  public void testFromFileFilesList2() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    expected.add(fs.getRootDir().getChildByName("dir"));
    assertEquals("Child of root directory", expected,
        new Path(fs.getRootDir().getChildByName("dir")).getFilesList());
  }


  @Test
  public void testFromFileFilesList3() {
    List<General> expected = new LinkedList<>();
    expected.add(fs.getRootDir());
    expected.add(fs.getRootDir().getChildByName("dir"));
    expected.add(((Directory) fs.getRootDir().getChildByName("dir"))
        .getChildByName("file"));
    assertEquals("Child of child of root directory", expected, new Path(
        ((Directory) fs.getRootDir().getChildByName("dir"))
            .getChildByName("file")).getFilesList());
  }


  @Test
  public void testGetFile1() {
    General file = fs.getRootDir();
    assertEquals("Root directory", file, new Path(file).getFile());
  }

  @Test
  public void testGetFile2() {
    General file = fs.getRootDir().getChildByName("dir");
    assertEquals("Child of root directory", file, new Path(file).getFile());
  }

  @Test
  public void testGetFile3() {
    General file = ((Directory) fs.getRootDir().getChildByName("dir"))
        .getChildByName("file");
    assertEquals("Child of child of root directory", file,
        new Path(file).getFile());
  }

  @Test
  public void testGetParent1() {
    General file = fs.getRootDir();
    assertEquals("Root directory", file.getParent(),
        new Path(file).getParent());
  }

  @Test
  public void testGetParent2() {
    General file = fs.getRootDir().getChildByName("dir");
    assertEquals("Child of root directory", file.getParent(),
        new Path(file).getParent());
  }

  @Test
  public void testGetParent3() {
    General file = ((Directory) fs.getRootDir().getChildByName("dir"))
        .getChildByName("file");
    assertEquals("Child of child of root directory", file.getParent(),
        new Path(file).getParent());
  }
}