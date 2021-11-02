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
package environment;

import executables.Cat;
import executables.Cd;
import executables.Cp;
import executables.Curl;
import executables.Echo;
import executables.Executable;
import executables.Exit;
import executables.Find;
import executables.Grep;
import executables.History;
import executables.Ls;
import executables.Man;
import executables.Mkdir;
import executables.Mv;
import executables.Popd;
import executables.Pushd;
import executables.Pwd;
import executables.Tree;
import filesystem.Directory;
import filesystem.FileSystem;
import input.UserInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Represents the shell environment, has the map of available commands,
 * directory stack, input history
 */
public class RealEnvironment implements Environment {

  /**
   * Map of available commands
   */
  private Map<String, Executable> commandMap = new HashMap<>();
  /**
   * The directory stack
   */
  private Stack<Directory> dirStack = new Stack<>();
  /**
   * The input history
   */
  private List<UserInput> inputHistory = new ArrayList<>();
  /**
   * The file system
   */
  private FileSystem fs;

  /**
   * Create an execution environment
   *
   * @param fs The file system
   */
  public RealEnvironment(FileSystem fs) {
    this.fs = fs;
    commandMap.put("exit", new Exit());
    commandMap.put("mkdir", new Mkdir(fs));
    commandMap.put("cd", new Cd(fs));
    commandMap.put("ls", new Ls(fs));
    commandMap.put("pwd", new Pwd(fs));
    commandMap.put("pushd", new Pushd(fs, dirStack));
    commandMap.put("popd", new Popd(fs, dirStack));
    commandMap.put("history", new History(inputHistory));
    commandMap.put("cat", new Cat(fs));
    commandMap.put("cp", new Cp(fs));
    commandMap.put("echo", new Echo());
    commandMap.put("man", new Man(commandMap));
    commandMap.put("tree", new Tree(fs));
    commandMap.put("find", new Find(fs));
    commandMap.put("curl", new Curl(fs));
    commandMap.put("grep", new Grep(fs));
    commandMap.put("mv", new Mv(fs));
  }

  @Override
  public Map<String, Executable> getCommandMap() {
    return commandMap;
  }

  @Override
  public List<UserInput> getInputHistory() {
    return inputHistory;
  }

  @Override
  public FileSystem getFs() {
    return fs;
  }

}
