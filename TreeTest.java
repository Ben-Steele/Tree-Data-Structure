import org.junit.*;
import static org.junit.Assert.*;

public class TreeTest {
	
	@Test
	public void test_add1() {
		System.out.println("Test add/contains 1 element");
		Tree<Integer> s = new Tree<Integer>();
		s.add(1);
		assertTrue(s.checkContains(1) == true);
	}

	@Test
	public void test_add2() {
		System.out.println("Test add/contains 2 elements");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(1);
		assertTrue(s.checkContains(1) == true && s.checkContains(2) == true);
	}

	@Test
	public void test_add3() {
		System.out.println("Test add/contains 3 elements");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(1);
		s.add(3);
		assertTrue(s.checkContains(3) == true);
	}

	@Test
	public void test_Balanced1() {
		System.out.println("Test balanced 7 elements");
		Tree<Integer> s = new Tree<Integer>();
		s.add(5);
		s.add(2);
		s.add(7);
		s.add(1);
		s.add(3);
		s.add(6);
		s.add(8);
		assertTrue(s.checkBalanced() == true);
	}

	@Test
	public void test_Balanced2() {
		System.out.println("Test unbalanced 9 elements");
		Tree<Integer> s = new Tree<Integer>();
		s.add(5);
		s.add(2);
		s.add(7);
		s.add(1);
		s.add(3);
		s.add(6);
		s.add(0);
		s.add(-1);
		assertTrue(s.checkBalanced() == false);
	}

	@Test
	public void test_Balanced3() {
		System.out.println("Test unbalanced 8 elements");
		Tree<Integer> s = new Tree<Integer>();
		s.add(5);
		s.add(2);
		s.add(7);
		s.add(1);
		s.add(3);
		s.add(6);
		s.add(0);
		assertTrue(s.checkBalanced() == true);
	}

	@Test
	public void test_Balanced4() {
		System.out.println("Test unbalanced 9 elements");
		Tree<Integer> s = new Tree<Integer>();
		s.add(5);
		s.add(2);
		s.add(7);
		s.add(1);
		s.add(3);
		s.add(6);
		s.add(0);
		s.add(-1);
		s.remove(-1);
		assertTrue(s.checkBalanced() == true);
	}

	@Test
	public void test_remove1() {
		System.out.println("Test remove root 1 element");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.remove(2);
		assertTrue(s.checkEmpty() == true);
	}

	@Test
	public void test_remove2() {
		System.out.println("Test remove root with left child");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(1);
		s.remove(2);
		assertTrue(s.checkContains(1) == true && s.checkContains(2) == false);
	}

	@Test
	public void test_remove3() {
		System.out.println("Test remove left of root");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(1);
		s.remove(1);
		assertTrue(s.checkContains(2) == true && s.checkContains(1) == false);
	}

	@Test
	public void test_remove4() {
		System.out.println("Test remove root with right child");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(3);
		s.remove(2);
		assertTrue(s.checkContains(3) == true && s.checkContains(2) == false);
	}

	@Test
	public void test_remove5() {
		System.out.println("Test remove root with left child");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(1);
		s.add(3);
		s.remove(1);
		assertTrue(s.checkContains(2) == true && s.checkContains(1) == false && s.checkContains(3) == true);
	}

	@Test
	public void test_remove6() {
		System.out.println("Test first left of right child");
		Tree<Integer> s = new Tree<Integer>();
		s.add(4);
		s.add(1);
		s.add(3);
		s.add(2);
		s.remove(1);
		assertTrue(s.checkContains(2) == true && s.checkContains(1) == false && s.checkContains(3) == true && s.checkContains(4) == true);
	}

	@Test
	public void test_remove7() {
		System.out.println("Test average remove");
		Tree<Integer> s = new Tree<Integer>();
		s.add(5);
		s.add(1);
		s.add(4);
		s.add(3);
		s.add(2);
		s.remove(1);
		assertTrue(s.checkContains(2) == true && s.checkContains(1) == false && s.checkContains(5) == true && s.checkContains(4) == true && s.checkContains(3) == true);
	}

	@Test
	public void test_remove8() {
		System.out.println("Test remove root with 2 children");
		Tree<Integer> s = new Tree<Integer>();
		s.add(2);
		s.add(1);
		s.add(3);
		s.remove(2);
		assertTrue(s.checkContains(1) == true && s.checkContains(2) == false && s.checkContains(3) == true);
	}

	@Test
	public void test_remove9() {
		System.out.println("Test first left of right child");
		Tree<Integer> s = new Tree<Integer>();
		s.add(4);
		s.add(2);
		s.add(3);
		s.add(1);
		s.add(6);
		s.add(7);
		s.remove(4);
		assertTrue(s.checkContains(2) == true && s.checkContains(4) == false && s.checkContains(3) == true && s.checkContains(1) == true && s.checkContains(6) == true && s.checkContains(7) == true);
	}

	@Test
	public void test_maxheight1() {
		System.out.println("check max height after adding 0");
		Tree<Integer> s = new Tree<Integer>();
		assertTrue(s.getHeight() == 0);
	}

	@Test
	public void test_maxheight2() {
		System.out.println("check max height after adding 1");
		Tree<Integer> s = new Tree<Integer>();
		s.add(1);
		assertTrue(s.getHeight() == 1);
	}

	@Test
	public void test_maxheight3() {
		System.out.println("check max height after adding 2");
		Tree<Integer> s = new Tree<Integer>();
		s.add(1);
		s.add(0);
		assertTrue(s.getHeight() == 2);
	}

	@Test
	public void test_maxheight4() {
		System.out.println("check max height after adding 3");
		Tree<Integer> s = new Tree<Integer>();
		s.add(1);
		s.add(0);
		s.add(2);
		assertTrue(s.getHeight() == 2);
	}

	@Test
	public void test_maxheight5() {
		System.out.println("check max height after removing");
		Tree<Integer> s = new Tree<Integer>();
		s.add(1);
		s.remove(1);
		assertTrue(s.getHeight() == 0);
	}

	@Test
	public void test_maxheight6() {
		System.out.println("check max height after removing");
		Tree<Integer> s = new Tree<Integer>();
		s.add(1);
		s.remove(1);
		assertTrue(s.getHeight() == 0);
	}

}