package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Pwd;
import filesystem.Directory;
import filesystem.FileSystem;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class PwdTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Pwd pwd;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    pwd = new Pwd(fs);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testArgs() {
    List<String> args = Arrays.asList("arg");
    pwd.execute(args, out, err);
  }

  @Test
  public void testEmptyFs() {
    List<String> args = Arrays.asList();
    assertFalse(pwd.execute(args, out, err));
    assertEquals("/" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testRoot() {
    fs.getWorkingDir().addChild(new Directory("dir"));
    List<String> args = Arrays.asList();
    assertFalse(pwd.execute(args, out, err));
    assertEquals("/" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testDepth1() {
    Directory dir = new Directory("dir");
    fs.getWorkingDir().addChild(dir);
    fs.setWorkingDir(dir);
    List<String> args = Arrays.asList();
    assertFalse(pwd.execute(args, out, err));
    assertEquals("/dir" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testDepth3() {
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");
    Directory dir3 = new Directory("dir3");
    dir1.addChild(dir2);
    dir2.addChild(dir3);
    fs.getWorkingDir().addChild(dir1);
    fs.setWorkingDir(dir3);
    List<String> args = Arrays.asList();
    assertFalse(pwd.execute(args, out, err));
    assertEquals("/dir1/dir2/dir3" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }
}