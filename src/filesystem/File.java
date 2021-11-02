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
 * Represents a regular file
 */
public class File extends General {

  /**
   * Content of the file
   */
  private String content;

  /**
   * Create a named file with content
   *
   * @param name The name
   * @param content The content
   */
  public File(String name, String content) {
    super(name);
    this.content = content;
  }

  /**
   * Create a blank file with name
   *
   * @param name The name
   */
  public File(String name) {
    this(name, "");
  }

  /**
   * Get the content
   *
   * @return String The content
   */
  public String getContent() {
    return content;
  }

  /**
   * Set the content
   *
   * @param content New content
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Append to the content
   *
   * @param content Content to append
   */
  public void appendContent(String content) {
    this.content += content;
  }

  @Override
  public File clone() {
    return new File(this.getName(), this.content);
  }
}
