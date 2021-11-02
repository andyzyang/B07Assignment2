package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Tree;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class TreeTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Tree tree;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    tree = new Tree(fs);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testArgs() {
    List<String> args = Arrays.asList("arg");
    tree.execute(args, out, err);
  }

  @Test
  public void testEmptyFs() {
    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals("\\" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleEmptyDir() {
    fs.getRootDir().addChild(new Directory("dir"));
    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String.join(System.lineSeparator(), "\\", "\tdir", ""),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleFile() {
    fs.getRootDir().addChild(new File("file"));
    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String.join(System.lineSeparator(), "\\", "\tfile", ""),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleDirWithChildren() {
    Directory dir = new Directory("dir");
    dir.addChild(new File("childFile"));
    dir.addChild(new Directory("childDir"));
    fs.getRootDir().addChild(dir);
    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "\\", "\tdir", "\t\tchildDir",
            "\t\tchildFile", ""), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleDirsWithChildren() {
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

    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "\\", "\tdir1", "\t\tchildDir1",
            "\t\tchildFile1", "\tdir2", "\t\tchildDir2", "\t\tchildFile2",
            "\tdir3", "\t\tchildDir3", "\t\tchildFile3", ""), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleFilesOnly() {
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));

    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "\\", "\tfile1", "\tfile2", "\tfile3",
            ""), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleDirsOnly() {
    fs.getRootDir().addChild(new Directory("file1"));
    fs.getRootDir().addChild(new Directory("file2"));
    fs.getRootDir().addChild(new Directory("file3"));
    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "\\", "\tfile1", "\tfile2", "\tfile3",
            ""), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testFilesAndEmptyDirs() {
    fs.getRootDir().addChild(new Directory("file1"));
    fs.getRootDir().addChild(new Directory("file2"));
    fs.getRootDir().addChild(new Directory("file3"));

    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "\\", "\tfile1", "\tfile2", "\tfile3",
            ""), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testFilesAndEmptyAndNonEmptyDirs() {
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

    List<String> args = Arrays.asList();
    assertFalse(tree.execute(args, out, err));
    assertEquals(String
        .join(System.lineSeparator(), "\\", "\tdir1", "\t\tchildDir1",
            "\t\tchildFile1", "\tdir2", "\t\tchildDir2", "\t\tchildFile2",
            "\tdir3", "\t\tchildDir3", "\t\tchildFile3", "\temptyDir",
            "\tfile1", "\tfile2", "\tfile3", ""), out.toString());
    assertEquals("", err.toString());
  }
}