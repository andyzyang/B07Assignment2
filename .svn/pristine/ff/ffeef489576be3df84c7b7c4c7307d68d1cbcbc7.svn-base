package test;

import static junit.framework.TestCase.assertTrue;

import exceptions.InvalidArgumentException;
import executables.Exit;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class ExitTest {

  private Output out;
  private Output err;
  private Exit exit;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    exit = new Exit();
  }

  @Test
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    assertTrue(exit.execute(args, out, err));
  }

  @Test(expected = InvalidArgumentException.class)
  public void test1Args() {
    List<String> args = Arrays.asList("arg1");
    exit.execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void test3Args() {
    List<String> args = Arrays.asList("arg1", "arg2", "arg3");
    exit.execute(args, out, err);
  }

}