package test;

import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import driver.Executor;
import filesystem.FileSystem;

public class ExecutorTest {

  private Executor exe;

  @Before
  public void setUp() {
    exe = new Executor(new MockEnvironment(new FileSystem()));
  }

  @Test
  public void testExecutorExecute() {
    assertFalse(exe.execute("MockExecutable"));
  }

  @Test
  public void testExecutorExecuteInvalidCommand() {
    assertFalse(exe.execute("Blah"));
  }

}
