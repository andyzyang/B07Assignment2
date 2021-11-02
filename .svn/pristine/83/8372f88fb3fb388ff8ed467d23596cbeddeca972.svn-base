package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import environment.Environment;
import executables.Executable;
import filesystem.Directory;
import filesystem.FileSystem;
import input.UserInput;

public class MockEnvironment implements Environment {
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

  public MockEnvironment(FileSystem fs) {
    this.fs = fs;
    commandMap.put("MockExecutable", new MockExecutable(null));
  }

  @Override
  public Map<String, Executable> getCommandMap() {
    // TODO Auto-generated method stub
    return commandMap;
  }

  @Override
  public List<UserInput> getInputHistory() {
    // TODO Auto-generated method stub
    return inputHistory;
  }

  @Override
  public FileSystem getFs() {
    // TODO Auto-generated method stub
    return fs;
  }

}
