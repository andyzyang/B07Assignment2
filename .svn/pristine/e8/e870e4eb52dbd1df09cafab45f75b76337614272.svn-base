package test;

import static org.junit.Assert.assertEquals;

import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import filesystem.General;
import filesystem.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PathTest {

  private FileSystem fs;

  @Before
  public void setUp() {
    fs = new FileSystem();
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

  @Test
  public void testRootPath() {
    Path path = new Path(fs, "/");
    List<General> filesList = Arrays.asList(fs.getRootDir());
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getRootDir().getName(), path.getFileName());
    assertEquals(null, path.getParent());
    assertEquals(fs.getRootDir(), path.getFile());
  }

  @Test
  public void testRelativePathToWorkingDir() {
    Path path = new Path(fs, ".");
    List<General> filesList = Arrays
        .asList(fs.getWorkingDir(), fs.getWorkingDir());
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getWorkingDir().getName(), path.getFileName());
    assertEquals(fs.getWorkingDir(), path.getParent());
    assertEquals(fs.getWorkingDir(), path.getFile());
  }

  @Test
  public void testRelativePathToParentOfWorkingDir() {
    Path path = new Path(fs, "..");
    List<General> filesList = Arrays
        .asList(fs.getWorkingDir(), fs.getWorkingDir().getParent());
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getWorkingDir().getParent().getName(), path.getFileName());
    assertEquals(fs.getWorkingDir(), path.getParent());
    assertEquals(fs.getWorkingDir().getParent(), path.getFile());
  }

  @Test
  public void testExtraneousForwardSlashRootDir() {
    Path path = new Path(fs, "///");
    List<General> filesList = Arrays.asList(fs.getRootDir());
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getRootDir().getName(), path.getFileName());
    assertEquals(null, path.getParent());
    assertEquals(fs.getRootDir(), path.getFile());
  }

  @Test
  public void testAbsolutePathToRootWithRepeatedCurrentDirDot() {
    Path path = new Path(fs, "/././");
    List<General> filesList = Arrays
        .asList(fs.getRootDir(), fs.getRootDir(), fs.getRootDir());
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getRootDir().getName(), path.getFileName());
    assertEquals(fs.getRootDir(), path.getParent());
    assertEquals(fs.getRootDir(), path.getFile());
  }

  @Test
  public void testRelativePathToWorkingDirWithRepeatedCurrentDirDot() {
    Path path = new Path(fs, "././");
    List<General> filesList = Arrays
        .asList(fs.getWorkingDir(), fs.getWorkingDir(), fs.getWorkingDir());
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getWorkingDir().getName(), path.getFileName());
    assertEquals(fs.getWorkingDir(), path.getParent());
    assertEquals(fs.getWorkingDir(), path.getFile());
  }

  @Test
  public void testRelativePathToFileInWorkingDir() {
    Path path = new Path(fs, "file");
    List<General> filesList = Arrays
        .asList(fs.getWorkingDir(), fs.getWorkingDir().getChildByName("file"));
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getWorkingDir().getChildByName("file").getName(),
        path.getFileName());
    assertEquals(fs.getWorkingDir(), path.getParent());
    assertEquals(fs.getWorkingDir().getChildByName("file"), path.getFile());
  }

  @Test
  public void testAbsolutePathToFileInRootDir() {
    Path path = new Path(fs, "/file");
    List<General> filesList = Arrays
        .asList(fs.getRootDir(), fs.getRootDir().getChildByName("file"));
    assertEquals(filesList, path.getFilesList());
    assertEquals(fs.getRootDir().getChildByName("file").getName(),
        path.getFileName());
    assertEquals(fs.getRootDir(), path.getParent());
    assertEquals(fs.getRootDir().getChildByName("file"), path.getFile());
  }

  @Test
  public void testRelativePathToNonExistentFileInExistentDir() {
    Path path = new Path(fs, "dir/file2");
    List<General> filesList = Arrays
        .asList(fs.getWorkingDir(), fs.getWorkingDir().getChildByName("dir"),
            null);
    assertEquals(filesList, path.getFilesList());
    assertEquals("file2", path.getFileName());
    assertEquals(fs.getWorkingDir().getChildByName("dir"), path.getParent());
    assertEquals(null, path.getFile());
  }


  @Test
  public void testAbsolutePathToNonExistentFileInExistentDir() {
    Path path = new Path(fs, "/dir/file2");
    List<General> filesList = Arrays
        .asList(fs.getRootDir(), fs.getRootDir().getChildByName("dir"), null);
    assertEquals(filesList, path.getFilesList());
    assertEquals("file2", path.getFileName());
    assertEquals(fs.getRootDir().getChildByName("dir"), path.getParent());
    assertEquals(null, path.getFile());
  }


  @Test
  public void testRelativePathToNonExistentFileInNonExistentDir() {
    Path path = new Path(fs, "dir2/file");
    List<General> filesList = Arrays.asList(fs.getWorkingDir(), null, null);
    assertEquals(filesList, path.getFilesList());
    assertEquals("file", path.getFileName());
    assertEquals(null, path.getParent());
    assertEquals(null, path.getFile());
  }

  @Test
  public void testAbsolutePathToNonExistentFileInNonExistentParent() {
    Path path = new Path(fs, "/dir2/file");
    List<General> filesList = Arrays.asList(fs.getRootDir(), null, null);
    assertEquals(filesList, path.getFilesList());
    assertEquals("file", path.getFileName());
    assertEquals(null, path.getParent());
    assertEquals(null, path.getFile());
  }
}
