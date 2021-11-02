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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Represents a directory
 */
public class Directory extends General implements Iterable<General> {

  /**
   * Set of children, ordered by child name
   */
  private TreeSet<General> children = new TreeSet<>(
      Comparator.comparing(General::getName));

  /**
   * Create a named directory
   *
   * @param name Name of directory
   */
  public Directory(String name) {
    super(name);
  }

  /**
   * Create a directory with no name
   */
  public Directory() {
    super();
  }

  /**
   * Add a general file to the directory
   *
   * @param file The file to add
   */
  public void addChild(General file) {
    children.add(file);
    file.setParent(this);
  }

  /**
   * Get a child by name
   *
   * @param name The name
   * @return General The child, or null if no such child of name
   */
  public General getChildByName(String name) {
    switch (name) {
      // . means this directory
      case ".":
        return this;
      // .. means parent directory
      case "..":
        return getParent();
      default:
        General key = new General(name);
        General max = children.ceiling(key);
        General min = children.floor(key);
        return min == max ? min : null;
    }
  }

  /**
   * Get children by name recursively
   *
   * @param name The name
   * @return List List of files with matching name
   */
  public List<General> getChildrenByNameRec(String name) {
    List<General> retList = new LinkedList<>();
    for (General file : getChildrenRec()) {
      if (file.getName().equals(name)) {
        retList.add(file);
      }
    }
    return retList;
  }

  /**
   * Get all files recursively
   *
   * @return List List of files
   */
  public List<File> getFilesRec() {
    List<File> retList = new ArrayList<>();
    for (General child : getChildrenRec()) {
      if (child instanceof File) {
        retList.add((File) child);
      }
    }
    return retList;
  }

  /**
   * Check if a general file is a child of directory, recursively
   *
   * @param file The file to check
   * @return boolean Whether or not file is a child
   */
  public boolean hasChildRec(General file) {
    for (General child : getChildrenRec()) {
      if (child == file) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get all children recursively
   *
   * @return List List of children
   */
  private List<General> getChildrenRec() {
    List<General> retList = new LinkedList<>();
    Queue<Directory> queue = new LinkedList<>();
    queue.add(this);
    do {
      Directory dir = queue.remove();
      for (General file : dir) {
        retList.add(file);
        if (file instanceof Directory) {
          queue.add((Directory) file);
        }
      }
    } while (!queue.isEmpty());
    return retList;
  }

  /**
   * Delete a general file from directory
   *
   * @param file File to remove
   */
  public void removeChild(General file) {
    if (file != null) {
      children.remove(file);
      file.setParent(null);
    }
  }

  @Override
  public Iterator<General> iterator() {
    return children.iterator();
  }

  @Override
  public Directory clone() {
    Directory clone = new Directory(this.getName());
    for (General child : this) {
      clone.addChild(child.clone());
    }
    return clone;
  }
}
