package test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Cd;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class CdTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Cd cd;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    cd = new Cd(fs);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    cd.execute(args, out, err);
  }

  @Test
  public void testRelativePathToWorkingDir() {
    Directory expected = fs.getWorkingDir();
    List<String> args = Arrays.asList(".");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testRelativePathToDir() {
    Directory expected = new Directory("dir");
    fs.getWorkingDir().addChild(expected);
    List<String> args = Arrays.asList("dir");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testAbsolutePathToDir() {
    Directory expected = new Directory("dir");
    fs.getRootDir().addChild(expected);
    List<String> args = Arrays.asList("/dir");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testRelativePathToParent() {
    Directory expected = fs.getWorkingDir();
    Directory workingDir = new Directory("dir");
    expected.addChild(workingDir);
    fs.setWorkingDir(workingDir);
    List<String> args = Arrays.asList("..");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testRelativePathToFile() {
    Directory expected = fs.getWorkingDir();
    expected.addChild(new File("file"));
    List<String> args = Arrays.asList("file");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("file: no such directory" + System.lineSeparator(),
        err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testAbsolutePathToFile() {
    Directory expected = fs.getWorkingDir();
    expected.addChild(new File("file"));
    List<String> args = Arrays.asList("/file");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("/file: no such directory" + System.lineSeparator(),
        err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testRelativePathToNonExistentFile() {
    Directory expected = fs.getWorkingDir();
    List<String> args = Arrays.asList("file");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("file: no such directory" + System.lineSeparator(),
        err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }

  @Test
  public void testAbsolutePathToNonExistentFile() {
    Directory expected = fs.getWorkingDir();
    List<String> args = Arrays.asList("/file");
    assertFalse(cd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("/file: no such directory" + System.lineSeparator(),
        err.toString());
    assertEquals(expected, fs.getWorkingDir());
  }
}
