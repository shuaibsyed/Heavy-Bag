import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Some test cases distributed as part of the project.
 */
public class StudentTests {


	/**
	 * Utility function Given a String, return a list consisting of one
	 * character Strings
	 */
	public static List<String> makeListOfCharacters(String s) {
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++)
			lst.add(s.substring(i, i + 1));
		return lst;
	}

	/**
	 * Test adding to a Bag
	 * 
	 */
	@Test
	public void testBagAddSizeUniqueElements() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());
	}

	/**
	 * Test checking if a Bag contains a key, and the count for each element
	 * 
	 */
	@Test
	public void testBagContainsAndCount() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());
		assertTrue(b.contains("a"));
		assertTrue(b.contains("b"));
		assertTrue(b.contains("c"));
		assertFalse(b.contains("d"));
		assertEquals(3, b.getCount("a"));
		assertEquals(2, b.getCount("b"));
		assertEquals(1, b.getCount("c"));
		assertEquals(0, b.getCount("d"));
	}

	@Test
	public void testIterate() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());
		Iterator it = b.iterator();
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
	}

	@Test
	public void testEquals() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());

		List<String> second = makeListOfCharacters("aaabbc");
		HeavyBag<String> a = new HeavyBag<String>();
		a.addAll(second);
		assertEquals(6, a.size());
		assertEquals(3, a.uniqueElements().size());

		List<String> third = makeListOfCharacters("abbbbbbabbc");
		HeavyBag<String> c = new HeavyBag<String>();
		c.addAll(third);
		assertEquals(11, c.size());
		assertEquals(3, c.uniqueElements().size());

		assertFalse(b.equals(c));
		assertFalse(a.equals(c));
		assertTrue(b.equals(a));
	}

	@Test
	public void testAddMany() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());
		b.addMany("c", 10);
		assertEquals(16, b.size());
		try {
			b.addMany("d", -1);
		} catch(IllegalArgumentException e) {

		}
		try {
			b.addMany("d", 2000000000);
		} catch(IllegalArgumentException e) {

		}
		b.addMany("d", 100000000);
		assertEquals(100000016, b.size());

	}

	@Test
	public void testRemove() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		HeavyBag<String> c = new HeavyBag<String>();
		c.addAll(lst);

		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());
		assertEquals(6, c.size());
		assertEquals(3, c.uniqueElements().size());
		b.remove("a");

		assertEquals(5, b.size());
		assertEquals(3, b.uniqueElements().size());
		assertEquals(false, b.equals(c));

		b.add("c");
		System.out.println("B is : " + b.toString());
		System.out.println("C is : " + c.toString());
		assertEquals(false, b.equals(c));

		c.remove("c");
		assertFalse(c.contains("c"));
		System.out.println();
		System.out.println("after remove c, C is: ");
		for(String el: c){
			System.out.print(el);
		}
		System.out.println();

		c.remove("c");

		c.remove("b");
		System.out.println(" After remove b, C is: ");
		for(String el: c){
			System.out.print(el);
		}
		System.out.println();

	}

	@Test
	public void testChooseRandom() {
		List<String> lst = makeListOfCharacters("aaabbc");
		HeavyBag<String> b = new HeavyBag<String>();
		b.addAll(lst);
		assertEquals(6, b.size());
		assertEquals(3, b.uniqueElements().size());
		Random r = new Random();
		int aCount = 0, bCount = 0, cCount = 0;
		int count = 0;
		while(count < 100){
			String t = b.choose(r);
			if(t.equals("a")){
				aCount++;
			}else if(t.equals("b")){
				bCount++;
			}else if(t.equals("c")){
				cCount++;
			}
			count++;
		}
		System.out.println();
		System.out.print("The random element is: " + b.choose(r));
		System.out.println();
		System.out.println(" a : " + aCount);
		System.out.println(" b : " + bCount);
		System.out.println(" c : " + cCount);
	}

}