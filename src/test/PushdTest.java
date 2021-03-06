package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Pushd;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class PushdTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Stack<Directory> dirStack;
  private Pushd pushd;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    dirStack = new Stack<>();
    pushd = new Pushd(fs, dirStack);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    pushd.execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void test2Args() {
    List<String> args = Arrays.asList("arg", "arg2");
    pushd.execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void test3Args() {
    List<String> args = Arrays.asList("arg", "arg2", "arg3");
    pushd.execute(args, out, err);
  }

  @Test
  public void testNonExistentFile() {
    List<String> args = Arrays.asList("arg");
    assertFalse(pushd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("arg: no such directory" + System.lineSeparator(),
        err.toString());
  }

  @Test
  public void testExistentFile() {
    fs.getWorkingDir().addChild(new File("arg"));
    List<String> args = Arrays.asList("arg");
    assertFalse(pushd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("arg: no such directory" + System.lineSeparator(),
        err.toString());
  }

  @Test
  public void testExistentDirectory() {
    Directory expectedDir = new Directory("arg");
    fs.getWorkingDir().addChild(expectedDir);
    List<String> args = Arrays.asList("arg");
    assertFalse(pushd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expectedDir, fs.getWorkingDir());
  }

  @Test
  public void testAbsolutePathExistenDirectory() {
    Directory expectedDir = new Directory("arg");
    fs.getWorkingDir().addChild(expectedDir);
    List<String> args = Arrays.asList("/arg");
    assertFalse(pushd.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
    assertEquals(expectedDir, fs.getWorkingDir());
  }

}
