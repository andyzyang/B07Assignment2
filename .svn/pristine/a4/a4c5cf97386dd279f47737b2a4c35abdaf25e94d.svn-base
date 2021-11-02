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

import filesystem.File;
import org.junit.Test;

public class FileTest {

  private final File FILE = new File("file", "content");

  @Test
  public void testfilename() {
    assertEquals("file", FILE.getName());
  }

  @Test
  public void testfilecontent() {
    assertEquals("content", FILE.getContent());
  }

  @Test
  public void testsetfilename() {
    FILE.setName("new");
    assertEquals("new", FILE.getName());
  }

  @Test
  public void testsetfilecontent() {
    FILE.setContent("boop");
    assertEquals("boop", FILE.getContent());
  }

  @Test
  public void testappendfilecontent() {
    FILE.appendContent("yeet");
    assertEquals("contentyeet", FILE.getContent());
  }
}
