package test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

import executables.Ls;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class LsTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Ls ls;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    ls = new Ls(fs);
  }

  @Test
  public void testInvalidEntry() {
    List<String> args = Arrays.asList("Bob");
    assertFalse(ls.execute(args, out, err));
    assertEquals("Bob: no such file or directory" + System.lineSeparator(),
        err.toString());
    assertEquals("", out.toString());
  }

  @Test
  public void testEmptyCurrentDirectory() {
    List<String> args = Arrays.asList();
    assertFalse(ls.execute(args, out, err));
    assertEquals("Empty Current Directory", "", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testOneDirectory() {
    List<String> args = Arrays.asList();
    fs.getRootDir().addChild(new Directory("dir1"));
    assertFalse(ls.execute(args, out, err));
    assertEquals("dir1" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleDirectories() {
    List<String> args = Arrays.asList();
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new Directory("dir2"));
    fs.getRootDir().addChild(new Directory("dir3"));
    assertFalse(ls.execute(args, out, err));
    assertEquals(
        String.join(System.lineSeparator(), "dir1", "dir2", "dir3") + System
            .lineSeparator(), out.toString());

    assertEquals("", err.toString());
  }

  @Test
  public void testOneFile() {
    List<String> args = Arrays.asList();
    fs.getRootDir().addChild(new File("file1"));
    assertFalse(ls.execute(args, out, err));
    assertEquals("file1" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleFiles() {
    List<String> args = Arrays.asList();
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));
    assertFalse(ls.execute(args, out, err));
    assertEquals(
        String.join(System.lineSeparator(), "file1", "file2", "file3") + System
            .lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testOneFileOneDirectory() {
    List<String> args = Arrays.asList();
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new File("file1"));
    assertFalse(ls.execute(args, out, err));
    assertEquals(String.join(System.lineSeparator(), "dir1", "file1") + System
        .lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleFileMultipleDirectories() {
    List<String> args = Arrays.asList();
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new Directory("dir2"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new Directory("dir3"));
    fs.getRootDir().addChild(new File("file3"));
    assertFalse(ls.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "dir1", "dir2", "dir3", "file1", "file2",
            "file3") + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testEmptyDirectoryofDirectory() {
    List<String> args = Arrays.asList("dir1");
    fs.getRootDir().addChild(new Directory("dir1"));
    assertFalse(ls.execute(args, out, err));
    assertEquals("/dir1:" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testEmptyDirectoryofDirectories() {
    List<String> args = Arrays.asList("dir1", "dir2", "dir3");
    fs.getRootDir().addChild(new Directory("dir1"));
    fs.getRootDir().addChild(new Directory("dir2"));
    fs.getRootDir().addChild(new Directory("dir3"));
    assertFalse(ls.execute(args, out, err));
    assertEquals(
        String.join(System.lineSeparator(), "/dir1:", "/dir2:", "/dir3:")
            + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleFileSingleDirectoryofDirectory() {
    List<String> args = Arrays.asList("dir1");
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("childFile1"));
    dir1.addChild(new Directory("childDir1"));

    fs.getRootDir().addChild(dir1);
    assertFalse(ls.execute(args, out, err));
    assertEquals("/dir1: childDir1 childFile1" + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleFileSingleDirectoryofDirectories() {
    List<String> args = Arrays.asList("dir1", "dir2");
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("childFile1"));
    dir1.addChild(new Directory("childDir1"));
    Directory dir2 = new Directory("dir2");
    dir2.addChild(new File("childFile2"));
    dir2.addChild(new Directory("childDir2"));

    fs.getRootDir().addChild(dir1);
    fs.getRootDir().addChild(dir2);
    assertFalse(ls.execute(args, out, err));
    assertEquals(String
            .join(System.lineSeparator(), "/dir1: childDir1 childFile1",
                "/dir2: childDir2 childFile2") + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testEmptyDirectoryofDirectoryofDirectory() {
    List<String> args = Arrays.asList("dir1/dir2/dir3");
    Directory dir2 = new Directory("dir2");
    dir2.addChild(new Directory("dir3"));
    Directory dir1 = new Directory("dir1");
    dir1.addChild(dir2);

    fs.getRootDir().addChild(dir1);
    assertFalse(ls.execute(args, out, err));
    assertEquals("/dir1/dir2/dir3:" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }
}
