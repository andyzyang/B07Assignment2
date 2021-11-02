package test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import filesystem.Directory;
import filesystem.File;
import filesystem.General;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class DirectoryTest {

  private Directory dir;

  @Before
  public void setUp() {
    dir = new Directory();
  }

  @Test
  public void testAddThenRetrieveChildByName() {
    General file = new General("child");
    dir.addChild(file);
    assertEquals(file, dir.getChildByName("child"));
  }

  @Test
  public void testAddMultipleChildThenRetrieveByName() {
    General child1 = new General("child1");
    General child2 = new General("child2");
    General child3 = new General("child3");
    dir.addChild(child1);
    dir.addChild(child2);
    dir.addChild(child3);
    assertEquals(child1, dir.getChildByName("child1"));
    assertEquals(child2, dir.getChildByName("child2"));
    assertEquals(child3, dir.getChildByName("child3"));
  }

  @Test
  public void testRetrieveSelfByName() {
    assertEquals(dir, dir.getChildByName("."));
  }

  @Test
  public void testRetrieveParentByName() {
    Directory parent = new Directory("parent");
    parent.addChild(dir);
    assertEquals(parent, dir.getChildByName(".."));
  }

  @Test
  public void testRemoveFile() {
    General file = new General("child");
    dir.addChild(file);
    dir.removeChild(file);
    assertNull(file.getParent());
    assertNull(dir.getChildByName("child"));
  }

  @Test
  public void testGetByNameNonExistentName() {
    assertNull(dir.getChildByName("file"));
  }

  @Test
  public void testGetByNameRecNonExistentName() {
    assertEquals(Arrays.asList(), dir.getChildrenByNameRec("file"));
  }

  @Test
  public void testGetByNameRecSingleFileInDir() {
    General file = new General("file");
    dir.addChild(file);
    assertEquals(Arrays.asList(file), dir.getChildrenByNameRec("file"));
  }

  @Test
  public void testGetByNameRecSingleFileNested1Deep() {
    General file = new General("file");
    Directory dir1 = new Directory("dir1");
    dir1.addChild(file);
    dir.addChild(dir1);
    assertEquals(Arrays.asList(file), dir.getChildrenByNameRec("file"));
  }

  @Test
  public void testGetByNameRecSingleFileNested3Deep() {
    General file = new General("file");
    dir.addChild(file);
    assertEquals(Arrays.asList(file), dir.getChildrenByNameRec("file"));
  }

  @Test
  public void testGetByNameRec3FilesNested1DeepInDifferentSubDirs() {
    General file1 = new General("file");
    General file2 = new General("file");
    General file3 = new General("file");
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");
    Directory dir3 = new Directory("dir3");
    dir1.addChild(file1);
    dir2.addChild(file2);
    dir3.addChild(file3);
    dir.addChild(dir1);
    dir.addChild(dir2);
    dir.addChild(dir3);
    assertEquals(Arrays.asList(file1, file2, file3),
        dir.getChildrenByNameRec("file"));
  }

  @Test
  public void testGetByNameRec3FilesNestedUpTo3DeepInDifferentSubDirs() {
    General file1 = new General("file");
    General file2 = new General("file");
    General file3 = new General("file");
    Directory dir1 = new Directory("dir1");
    Directory dir11 = new Directory("dir11");
    Directory dir2 = new Directory("dir2");
    Directory dir21 = new Directory("dir21");
    Directory dir22 = new Directory("dir22");
    Directory dir3 = new Directory("dir3");
    Directory dir31 = new Directory("dir31");
    Directory dir32 = new Directory("dir32");
    dir1.addChild(dir11);
    dir2.addChild(dir21);
    dir21.addChild(dir22);
    dir3.addChild(dir31);
    dir31.addChild(dir32);
    dir11.addChild(file1);
    dir22.addChild(file2);
    dir32.addChild(file3);
    dir.addChild(dir1);
    dir.addChild(dir2);
    dir.addChild(dir3);
    assertEquals(Arrays.asList(file1, file2, file3),
        dir.getChildrenByNameRec("file"));
  }

  @Test
  public void testGetByChildrenRecNonExistentName() {
    assertEquals(Arrays.asList(), dir.getChildrenByNameRec("file"));
  }


  @Test
  public void testFilesRecNonExistent() {
    General file = new File("file");
    assertEquals(Arrays.asList(), dir.getFilesRec());
  }

  @Test
  public void testFilesRecSingleFileInDir() {
    General file = new File("file");
    dir.addChild(file);
    assertEquals(Arrays.asList(file), dir.getFilesRec());
  }

  @Test
  public void testFilesRecSingleFileNested1Deep() {
    General file = new File("file");
    Directory dir1 = new Directory("dir1");
    dir1.addChild(file);
    dir.addChild(dir1);
    assertEquals(Arrays.asList(file), dir.getFilesRec());
  }

  @Test
  public void testFilesRecSingleFileNested3Deep() {
    General file = new File("file");
    dir.addChild(file);
    assertEquals(Arrays.asList(file), dir.getFilesRec());
  }

  @Test
  public void testFilesRec3FilesNested1DeepInDifferentSubDirs() {
    General file1 = new File("file");
    General file2 = new File("file");
    General file3 = new File("file");
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");
    Directory dir3 = new Directory("dir3");
    dir1.addChild(file1);
    dir2.addChild(file2);
    dir3.addChild(file3);
    dir.addChild(dir1);
    dir.addChild(dir2);
    dir.addChild(dir3);
    assertEquals(Arrays.asList(file1, file2, file3),
        dir.getFilesRec());
  }

  @Test
  public void testFilesRec3FilesNestedUpTo3DeepInDifferentSubDirs() {
    General file1 = new File("file");
    General file2 = new File("file");
    General file3 = new File("file");
    Directory dir1 = new Directory("dir1");
    Directory dir11 = new Directory("dir11");
    Directory dir2 = new Directory("dir2");
    Directory dir21 = new Directory("dir21");
    Directory dir22 = new Directory("dir22");
    Directory dir3 = new Directory("dir3");
    Directory dir31 = new Directory("dir31");
    Directory dir32 = new Directory("dir32");
    dir1.addChild(dir11);
    dir2.addChild(dir21);
    dir21.addChild(dir22);
    dir3.addChild(dir31);
    dir31.addChild(dir32);
    dir11.addChild(file1);
    dir22.addChild(file2);
    dir32.addChild(file3);
    dir.addChild(dir1);
    dir.addChild(dir2);
    dir.addChild(dir3);
    assertEquals(Arrays.asList(file1, file2, file3),
        dir.getFilesRec());
  }

  @Test
  public void testHasChildRecNonExistent() {
    General file = new File("file");
    assertFalse(dir.hasChildRec(file));
  }

  @Test
  public void testHasChildRecSingleFileInDir() {
    General file = new File("file");
    dir.addChild(file);
    assertTrue(dir.hasChildRec(file));
  }

  @Test
  public void testHasChildRecSingleFileNested1Deep() {
    General file = new File("file");
    Directory dir1 = new Directory("dir1");
    dir1.addChild(file);
    dir.addChild(dir1);
    assertTrue(dir.hasChildRec(file));
  }

  @Test
  public void testHasChildRecSingleFileNested3Deep() {
    General file = new File("file");
    dir.addChild(file);
    assertTrue(dir.hasChildRec(file));
  }

  @Test
  public void testHasChildRec3FilesNested1DeepInDifferentSubDirs() {
    General file1 = new File("file");
    General file2 = new File("file");
    General file3 = new File("file");
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");
    Directory dir3 = new Directory("dir3");
    dir1.addChild(file1);
    dir2.addChild(file2);
    dir3.addChild(file3);
    dir.addChild(dir1);
    dir.addChild(dir2);
    dir.addChild(dir3);
    assertTrue(dir.hasChildRec(file1));
  }

  @Test
  public void testHasChildRec3FilesNestedUpTo3DeepInDifferentSubDirs() {
    General file1 = new File("file");
    General file2 = new File("file");
    General file3 = new File("file");
    Directory dir1 = new Directory("dir1");
    Directory dir11 = new Directory("dir11");
    Directory dir2 = new Directory("dir2");
    Directory dir21 = new Directory("dir21");
    Directory dir22 = new Directory("dir22");
    Directory dir3 = new Directory("dir3");
    Directory dir31 = new Directory("dir31");
    Directory dir32 = new Directory("dir32");
    dir1.addChild(dir11);
    dir2.addChild(dir21);
    dir21.addChild(dir22);
    dir3.addChild(dir31);
    dir31.addChild(dir32);
    dir11.addChild(file1);
    dir22.addChild(file2);
    dir32.addChild(file3);
    dir.addChild(dir1);
    dir.addChild(dir2);
    dir.addChild(dir3);
    assertTrue(dir.hasChildRec(file1));
  }

  @Test
  public void testCloneEmpty() {
    assertNotSame(dir, dir.clone());
  }

  @Test
  public void testCloneFilesOnly() {
    General file = new File("child");
    General file2 = new File("child");
    dir.addChild(file);
    dir.addChild(file2);
    assertNotSame(dir, dir.clone());
  }

  @Test
  public void testCloneFilesAndDirectories() {
    General file = new File("child");
    General file2 = new File("child");
    Directory dir1 = new Directory("dir");
    dir.addChild(dir1);
    dir1.addChild(file);
    dir.addChild(file2);
    assertNotSame(dir, dir.clone());
  }

}