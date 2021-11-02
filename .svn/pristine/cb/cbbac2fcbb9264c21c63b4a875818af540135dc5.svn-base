package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyDirectoryStackException;
import exceptions.InvalidArgumentException;
import executables.Popd;
import filesystem.Directory;
import filesystem.FileSystem;
import output.Output;

public class PopdTest {
	
	private Output out;
	private Output err;
	private FileSystem fs;
	private Stack<Directory> dirStack;
	private Popd popd;

	@Before
	public void setUp() {
	    out = new MockOutput();
	    err = new MockOutput();
		fs = new FileSystem();
		dirStack = new Stack<>();
		popd = new Popd(fs, dirStack);
	}

	@Test(expected = EmptyDirectoryStackException.class)
	public void testEmptyStack() {
	    List<String> args = Arrays.asList();
	    popd.execute(args, out, err);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testArgs() {
	    List<String> args = Arrays.asList("arg");
	    popd.execute(args, out, err);
	}
	
	@Test
	public void testSingleItemInStack() {
		Directory dir = new Directory("dir");
		fs.getWorkingDir().addChild(dir);
		dirStack.add(dir);
	    List<String> args = Arrays.asList();
	    assertFalse(popd.execute(args, out, err));
	    assertEquals("", out.toString());
	    assertEquals("", err.toString());
	    assertEquals(dir, fs.getWorkingDir());
	}
	
	@Test
	public void test3ItemsInStack() {
		Directory dir1 = new Directory("dir1");
		Directory dir2 = new Directory("dir2");
		Directory dir3 = new Directory("dir3");
		fs.getWorkingDir().addChild(dir1);
		fs.getWorkingDir().addChild(dir2);
		fs.getWorkingDir().addChild(dir3);
		dirStack.add(dir1);
		dirStack.add(dir2);
		dirStack.add(dir3);
	    List<String> args = Arrays.asList();
	    assertFalse(popd.execute(args, out, err));
	    assertEquals("", out.toString());
	    assertEquals("", err.toString());
	    assertEquals(dir3, fs.getWorkingDir());
	}
}
