package test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import filesystem.Directory;
import filesystem.FileSystem;
import org.junit.Before;
import org.junit.Test;

public class FileSystemTest {

  private FileSystem fs;

  @Before
  public void setUp() {
    fs = new FileSystem();
  }

  @Test
  public void testValidateFileNameEmptyString() {
    assertFalse(FileSystem.isValidFileName(""));
  }

  @Test
  public void testValidateFileNameSingleDot() {
    assertFalse(FileSystem.isValidFileName("."));
  }

  @Test
  public void testValidateFileNameDoubleDot() {
    assertFalse(FileSystem.isValidFileName(".."));
  }

  @Test
  public void testValidateFileNameTripleDot() {
    assertFalse(FileSystem.isValidFileName("..."));
  }

  @Test
  public void testValidateFileNameMinimum() {
    assertTrue(FileSystem.isValidFileName("a"));
  }

  @Test
  public void testValidateFileNameNoDots() {
    assertTrue(FileSystem.isValidFileName("abc"));
  }

  @Test
  public void testValidateFileNameDotAtEnd() {
    assertTrue(FileSystem.isValidFileName("abc."));
  }

  @Test
  public void testValidateFileNameDotInFront() {
    assertTrue(FileSystem.isValidFileName(".abc"));
  }

  @Test
  public void testValidateFileNameDotWithin() {
    assertTrue(FileSystem.isValidFileName("a.bc"));
  }

  @Test
  public void testValidateFileNameSpacesOnly() {
    assertFalse(FileSystem.isValidFileName("  "));
  }

  @Test
  public void testValidateFileNameWithSpaces() {
    assertFalse(FileSystem.isValidFileName("a  b"));
  }

  @Test
  public void testSetWorkingDir() {
    Directory dir = new Directory();
    fs.getRootDir().addChild(dir);
    fs.setWorkingDir(dir);
    assertEquals(dir, fs.getWorkingDir());
  }

}