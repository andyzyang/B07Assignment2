package test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Cat;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class CatTest {

  private Output out;
  private Output err;
  private FileSystem fs;
  private Cat cat;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    fs = new FileSystem();
    cat = new Cat(fs);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    cat.execute(args, out, err);
  }

  @Test
  public void testNonExistentFile() {
    List<String> args = Arrays.asList("Bob");
    assertFalse(cat.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("Bob: no such file" + System.lineSeparator(), err.toString());
  }

  @Test
  public void testInvalidEntry() {
    List<String> args = Arrays.asList("Bob");
    assertFalse(cat.execute(args, out, err));
    assertEquals("Bob: no such file" + System.lineSeparator(), err.toString());
    assertEquals("", out.toString());
  }

  @Test
  public void testEmptyFile() {
    fs.getRootDir().addChild(new File("file1"));
    List<String> args = Arrays.asList("file1");
    assertFalse(cat.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleLineFile() {
    fs.getRootDir().addChild(new File("file1", "I love CSCB07"));
    List<String> args = Arrays.asList("file1");
    assertFalse(cat.execute(args, out, err));
    assertEquals("I love CSCB07", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleLineFile() {
    fs.getRootDir().addChild(new File("file1",
        "I love CSCB07" + System.lineSeparator() + "CS Program"));
    List<String> args = Arrays.asList("file1");
    assertFalse(cat.execute(args, out, err));
    assertEquals(
        String.join(System.lineSeparator(), "I love CSCB07", "CS Program"),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleEmptyFiles() {
    fs.getRootDir().addChild(new File("file1"));
    fs.getRootDir().addChild(new File("file2"));
    fs.getRootDir().addChild(new File("file3"));
    List<String> args = Arrays.asList("file1", "file2", "file3");
    assertFalse(cat.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleLineFiles() {
    fs.getRootDir().addChild(new File("file1", "I love CSCB07"));
    fs.getRootDir().addChild(new File("file2", "I am a hungry hippo"));
    fs.getRootDir().addChild(new File("file3", "I ate Computer Science"));
    List<String> args = Arrays.asList("file1", "file2", "file3");
    assertFalse(cat.execute(args, out, err));
    assertEquals(
        "I love CSCB07" + "I am a hungry hippo" + "I ate Computer Science",
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testEmptyFileInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1"));
    fs.getRootDir().addChild(dir1);
    List<String> args = Arrays.asList("dir1/file1");
    assertFalse(cat.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testSingleLineFileInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1", "I love CSCB07"));
    fs.getRootDir().addChild(dir1);
    List<String> args = Arrays.asList("dir1/file1");
    assertFalse(cat.execute(args, out, err));
    assertEquals("I love CSCB07", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testEmptyFilesInDirectory() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1"));
    dir1.addChild(new File("file2"));
    dir1.addChild(new File("file3"));
    fs.getRootDir().addChild(dir1);
    List<String> args = Arrays.asList("dir1/file1");
    assertFalse(cat.execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testMultipleLineCombinations() {
    Directory dir1 = new Directory("dir1");
    dir1.addChild(new File("file1", "Apple Bees"));
    dir1.addChild(new File("file2"));
    dir1.addChild(new File("file3",
        "wow wow Nice apple Bees" + System.lineSeparator() + "Pie"));
    fs.getRootDir().addChild(dir1);
    List<String> args = Arrays.asList("dir1/file1", "dir1/file2", "dir1/file3");
    assertFalse(cat.execute(args, out, err));
    assertEquals(
        "Apple Beeswow wow Nice apple Bees" + System.lineSeparator() + "Pie",
        out.toString());
    assertEquals("", err.toString());
  }
}
