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
package filesystem;

/**
 * Represents a general file
 */
public class General implements Cloneable {

  /**
   * The filename
   */
  private String name;
  /**
   * The parent of the file
   */
  private Directory parent;

  /**
   * Create a general file with name
   *
   * @param name Name of file
   */
  public General(String name) {
    this.name = name;
  }

  /**
   * Create a general file with no name
   */
  public General() {
    this("");
  }

  /**
   * Get the name
   *
   * @return String The name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name
   *
   * @param name The name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the parent
   *
   * @return Directory The parent, or null if no parent
   */
  public Directory getParent() {
    return parent;
  }

  /**
   * Set the parent
   *
   * @param parent New parent
   * @see Directory#addChild(General)
   */
  void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * Return a path to the location of this file
   *
   * @return String The string representing the path to this file
   */
  public String getPath() {
    String ret = getName();
    Directory curDir = getParent();
    while (curDir != null && curDir.getParent() != null) {
      ret = curDir.getName() + "/" + ret;
      curDir = curDir.getParent();
    }
    ret = "/" + ret;
    return ret;
  }

  @Override
  public General clone() {
    return new General(this.name);
  }
}
