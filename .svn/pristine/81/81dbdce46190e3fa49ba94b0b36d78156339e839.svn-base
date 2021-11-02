package test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Executable;
import executables.Man;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class ManTest {

  private Output out;
  private Output err;
  Map<String, Executable> commandMap;
  private Man man;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    commandMap = new HashMap<>();
    commandMap.put("cmd1", new MockExecutable("Mock manual 1"));
    commandMap.put("cmd2", new MockExecutable("Mock manual 2"));
    commandMap.put("cmd3", new MockExecutable("Mock manual 3"));
    man = new Man(commandMap);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    man.execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testInvalidCommand() {
    List<String> args = Arrays.asList("invalidcmd");
    man.execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void test2ValidCommands() {
    List<String> args = Arrays.asList("cmd1", "cmd2");
    man.execute(args, out, err);
  }

  @Test
  public void testValidCommand1() {
    List<String> args = Arrays.asList("cmd1");
    assertFalse(man.execute(args, out, err));
    assertEquals(commandMap.get("cmd1").getManual() + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testValidCommand2() {
    List<String> args = Arrays.asList("cmd2");
    assertFalse(man.execute(args, out, err));
    assertEquals(commandMap.get("cmd2").getManual() + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testValidCommand3() {
    List<String> args = Arrays.asList("cmd3");
    assertFalse(man.execute(args, out, err));
    assertEquals(commandMap.get("cmd3").getManual() + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }
}