package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.Echo;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class EchoTest {

  private Output out;
  private Output err;
  private Echo echo;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
    echo = new Echo();
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNoArgs() {
    List<String> args = Arrays.asList();
    echo.execute(args, out, err);
  }

  @Test
  public void testEmptyString() {
    List<String> args = Arrays.asList("");
    assertFalse(echo.execute(args, out, err));
    assertEquals("" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void test1Word() {
    List<String> args = Arrays.asList("word");
    assertFalse(echo.execute(args, out, err));
    assertEquals("word" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void test1Sentence() {
    List<String> args = Arrays.asList("abc abcd a");
    assertFalse(echo.execute(args, out, err));
    assertEquals("abc abcd a" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test(expected = InvalidArgumentException.class)
  public void test2Words() {
    List<String> args = Arrays.asList("ab", "abc");
    echo.execute(args, out, err);
  }
}