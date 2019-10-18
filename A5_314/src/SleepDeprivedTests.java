import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class SleepDeprivedTests {

	public static void main(String[] args) {

		LinkedList<String> list = new LinkedList<>();
		Object[] actual;
		Object[] expected;

		// tests [1, 3] - add(E item)
		System.out.println("\nTest 1 - add(E item) to empty list");
		list.add("Z");
		list.add("Y");
		actual = toArray(list);
		if (actual[0].equals("Z") && actual[1].equals("Y"))
			System.out.println("Passed test 1");
		else
			System.out.println("Failed test 1");

		list = new LinkedList<>();
		expected = new Object[100];
		System.out.println("\nTest 2 - add(E item) to a list 100x");
		for (int i = 0; i < 100; i++) {
			list.add("" + i);
			expected[i] = "" + i;
		}
		actual = toArray(list);
		if (arraysSame(expected, actual))
			System.out.println("Passed test 2");
		else
			System.out.println("Failed test 2");

		LinkedList<Integer> list1 = new LinkedList<>();
		System.out.println("\nTest 3- add(E item) a non-String object to a list");
		list1.add(3);
		if (list1.get(0).equals(3))
			System.out.println("Passed test 3");
		else
			System.out.println("Failed test 3");

		// tests [4, 7] - equals(Object other)
		System.out.println("\nTest 4 - equals(Object other) w/ 2 blank lists");
		list = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		if (list.equals(list2))
			System.out.println("Passed test 4");
		else
			System.out.println("Failed test 4");

		System.out.println("\nTest 5 - equals(Object other) w/ 2 identical lists");
		list.add("A");
		list.add("B");
		list2.add("A");
		list2.add("B");
		if (list.equals(list2))
			System.out.println("Passed test 5");
		else
			System.out.println("Failed test 5");

		System.out.println("\nTest 6 - equals(Object other) w/ 2 lists of different sizes");
		list = new LinkedList<>();
		list2 = new LinkedList<>();
		list.add("A");
		list.add("B");
		list2.add("A");
		list2.add("B");
		list2.add("C");
		if (!list.equals(list2))
			System.out.println("Passed test 6");
		else
			System.out.println("Failed test 6");

		System.out.println("\nTest 7 - equals(Object other) w/ an Object array");
		list = new LinkedList<>();
		Object[] falsePositive = { "A", "B", "C" };
		list.add("A");
		list.add("B");
		list.add("C");
		if (!list.equals(falsePositive))
			System.out.println("Passed test 7");
		else
			System.out.println("Failed test 7");

		System.out.println("\nTest 8 - equals(Object other) w/ a different parameterized list");
		list = new LinkedList<>();
		LinkedList<Object> falsePosList = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		falsePosList.add("A");
		falsePosList.add("B");
		falsePosList.add("C");
		if (list.equals(falsePosList))
			System.out.println("Passed test 8");
		else
			System.out.println("Failed test 8");

		// tests [9, 11] - get(int pos)
		System.out.println("\nTest 9 - get(int pos) on a 1-element list");
		list = new LinkedList<>();
		list.add("C");
		if (list.get(0).equals("C"))
			System.out.println("Passed test 9");
		else
			System.out.println("Failed test 9");

		System.out.println("\nTest 10 - get(int pos) on a normal list");
		list = new LinkedList<>();
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("B");
		list.add("A");
		if (list.get(3).equals("B"))
			System.out.println("Passed test 10");
		else
			System.out.println("Failed test 10");

		System.out.println("\nTest 11 - get(int pos) on a list containing itself");
		LinkedList<LinkedList> listWeird = new LinkedList<>();
		listWeird.add(listWeird);
		if (listWeird == listWeird.get(0))
			System.out.println("Passed test 11");
		else
			System.out.println("Failed test 11");

		System.out.println("\nTest BONUS! Stress Test - get(int pos) on a LOOOOOOOOONG list but a lucky spot");
		list = new LinkedList<>();
		for (int i = 0; i < 100000; i++) {
			list.add("");
		}
		list.add("A");
		System.out.println("The time between this line and the result of this test should be very short.");
		if (list.get(100000).equals("A"))
			System.out.println("Passed test BONUS");
		else
			System.out.println("Failed test BONUS");

		// tests [12, 14] - getSubList(int start, int stop)
		System.out.println("\nTest 12 - getSubList(int start, int stop) on an empty list");
		list = new LinkedList<>();
		if (list.getSubList(0, 0).equals(list))
			System.out.println("Passed test 12");
		else
			System.out.println("Failed test 12");

		System.out.println("\nTest 13 - getSubList(int start, int stop) on a 1-element list");
		list = new LinkedList<>();
		list2 = new LinkedList<>();
		list.add("A");
		if (list.getSubList(0, 0).equals(list2) && list.getSubList(0, 1).equals(list))
			System.out.println("Passed test 13");
		else
			System.out.println("Failed test 13");

		System.out.println("\nTest 14 - getSubList(int start, int stop) on a sized list");
		list = new LinkedList<>();
		list2 = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list2.add("B");
		list2.add("C");
		list2.add("D");
		if (list.getSubList(1, 4).equals(list2))
			System.out.println("Passed test 14");
		else
			System.out.println("Failed test 14");

		// tests [15, 17] - indexOf(E item)
		System.out.println("\nTest 15 - indexOf(E item) on a 1-element list");
		list = new LinkedList<>();
		list.add("A");
		if (list.indexOf("A") == 0)
			System.out.println("Passed test 15");
		else
			System.out.println("Failed test 15");

		System.out.println("\nTest 16 - indexOf(E item) on the last element in the list");
		list = new LinkedList<>();
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("B");
		if (list.indexOf("B") == 4)
			System.out.println("Passed test 16");
		else
			System.out.println("Failed test 16");

		System.out.println("\nTest 17 - indexOf(E item) on a non-existant element in the list");
		list = new LinkedList<>();
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("B");
		if (list.indexOf("C") == -1)
			System.out.println("Passed test 17");
		else
			System.out.println("Failed test 17");

		// tests [18, 20] - indexOf(E item, int pos)
		System.out.println("\nTest 18 - gindexOf(E item, int pos) on a 1-element list from the start");
		list = new LinkedList<>();
		list.add("A");
		if (list.indexOf("A", 0) == 0)
			System.out.println("Passed test 18");
		else
			System.out.println("Failed test 18");

		System.out.println("\nTest 19 - indexOf(E item, int pos) on a 3-element list from a point pased the element");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		if (list.indexOf("A", 1) == -1)
			System.out.println("Passed test 19");
		else
			System.out.println("Failed test 19");

		System.out.println("\nTest 20 - indexOf(E item, int pos) on a 3-element list from a point before the element");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		if (list.indexOf("C", 2) == 2)
			System.out.println("Passed test 20");
		else
			System.out.println("Failed test 20");

		// tests [21, 23] - insert(int pos, E item)
		System.out.println("\nTest 21 - insert(int pos, E item) on an empty list");
		list = new LinkedList<>();
		list.insert(0, "A");
		if (list.get(0).equals("A"))
			System.out.println("Passed test 21");
		else
			System.out.println("Failed test 21");

		System.out.println("\nTest 22 - insert(int pos, E item) on a normal list");
		list = new LinkedList<>();
		list.insert(0, "C");
		list.insert(0, "B");
		list.insert(0, "A");
		if (list.get(0).equals("A") && list.get(1).equals("B") && list.get(2).equals("C"))
			System.out.println("Passed test 22");
		else
			System.out.println("Failed test 22");

		System.out.println("\nTest 23 - insert(int pos, E item) on  the last element in a list");
		list = new LinkedList<>();
		list.insert(0, "A");
		list.insert(0, "A");
		list.insert(0, "A");
		list.insert(3, "B");
		if (list.get(3).equals("B"))
			System.out.println("Passed test 23");
		else
			System.out.println("Failed test 23");

		// BEGIN ITERATOR TESTS
		//
		// tests [25, 28] - hasNext()
		System.out.println("\nTest 25 - hasNext() on an empty list");
		list = new LinkedList<>();
		Iterator<String> it = list.iterator();
		if (!it.hasNext())
			System.out.println("Passed test 25");
		else
			System.out.println("Failed test 25");

		System.out.println("\nTest 26 - hasNext() on a non-empty list");
		list.add("A");
		it = list.iterator();
		if (it.hasNext())
			System.out.println("Passed test 26");
		else
			System.out.println("Failed test 26");

		System.out.println("\nTest 27 - hasNext() on a non-empty list cause I can't think of any more edge cases");
		list = new LinkedList<>();
		list.insert(0, "");
		it = list.iterator();
		if (it.hasNext())
			System.out.println("Passed test 27");
		else
			System.out.println("Failed test 27");

		// tests [28, 30] - next()
		System.out.println("\nTest 28 - next() on a 1-element list");
		list = new LinkedList<>();
		list.add("");
		it = list.iterator();
		if (it.next().equals(""))
			System.out.println("Passed test 28");
		else
			System.out.println("Failed test 28");

		System.out.println("\nTest 29 - next() on a homogeneous list");
		list = new LinkedList<>();
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		it = list.iterator();
		if (it.next().equals("") && it.next().equals("") && it.next().equals("") && it.next().equals(""))
			System.out.println("Passed test 29");
		else
			System.out.println("Failed test 29");

		System.out.println("\nTest 30 - next() on a varied list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		it = list.iterator();
		if (it.next().equals("A") && it.next().equals("B") && it.next().equals("C") && it.next().equals("D"))
			System.out.println("Passed test 30");
		else
			System.out.println("Failed test 30");

		// tests [31, 33] - remove()
		System.out.println("\nTest 31 - remove() on a 1 element list");
		list = new LinkedList<>();
		list.add("A");
		it = list.iterator();
		it.next();
		it.remove();
		if (list.equals(new LinkedList<>()))
			System.out.println("Passed test 31");
		else
			System.out.println("Failed test 31");

		System.out.println("\nTest 32 - remove() on a single element in a list");
		list = new LinkedList<>();
		list2 = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list2.add("A");
		list2.add("C");
		it = list.iterator();
		it.next();
		it.next();
		it.remove();
		it.next();
		if (list.equals(list2))
			System.out.println("Passed test 32");
		else
			System.out.println("Failed test 32");

		System.out.println("\nTest 33 - remove() on all of a list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		it = list.iterator();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		if (list.equals(new LinkedList<>()))
			System.out.println("Passed test 33");
		else
			System.out.println("Failed test 33");
		// END ITERATOR TESTS

		// tests [34, 36] - makeEmpty()
		System.out.println("\nTest 34 - makeEmpty() on an empty list");
		list = new LinkedList<>();
		list.makeEmpty();
		if (list.equals(new LinkedList<>()))
			System.out.println("Passed test 34");
		else
			System.out.println("Failed test 34");

		System.out.println("\nTest 35 - makeEmpty() on an non-empty small list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.makeEmpty();
		list.add("C");
		if (list.get(0).equals("C"))
			System.out.println("Passed test 35");
		else
			System.out.println("Failed test 35");

		System.out.println("\nTest 36 - makeEmpty() on an non-empty large list");
		list = new LinkedList<>();
		for (int i = 0; i < 10000; i++) {
			list.add("");
		}
		list.makeEmpty();
		list.add("A");
		if (list.get(0).equals("A"))
			System.out.println("Passed test 36");
		else
			System.out.println("Failed test 36");

		// tests [37, 39] - remove(E obj)
		System.out.println("\nTest 37 - remove(E obj) on a empty large list");
		list = new LinkedList<>();
		if (!list.remove(""))
			System.out.println("Passed test 37");
		else
			System.out.println("Failed test 37");

		System.out.println("\nTest 38 - remove(E obj) on a 1-element list");
		list = new LinkedList<>();
		list.add("");
		if (list.remove("") && list.equals(new LinkedList<>()))
			System.out.println("Passed test 38");
		else
			System.out.println("Failed test 38");

		System.out.println("\nTest 39 - remove(E obj) on an element that repeats list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("A");
		list2 = new LinkedList<>();
		list2.add("B");
		list2.add("A");
		if (list.remove("A") && list.equals(list2))
			System.out.println("Passed test 39");
		else
			System.out.println("Failed test 39");

		// tests [40, 42] - remove(int pos)
		System.out.println("\nTest 40 - remove(int pos) on a 1-element list");
		list = new LinkedList<>();
		list.add("");
		String rem = list.remove(0);
		if (rem.equals("") && list.equals(new LinkedList<>()))
			System.out.println("Passed test 40");
		else
			System.out.println("Failed test 40");

		System.out.println("\nTest 41 - remove(E obj) on the last element in a list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		rem = list.remove(2);
		list2 = new LinkedList<>();
		list2.add("A");
		list2.add("B");
		if (rem.equals("C") && list.equals(list2))
			System.out.println("Passed test 41");
		else
			System.out.println("Failed test 41");

		System.out.println("\nTest 42 - remove(E obj) on the middle element in a list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		rem = list.remove(1);
		list2 = new LinkedList<>();
		list2.add("A");
		list2.add("C");
		if (rem.equals("B") && list.equals(list2))
			System.out.println("Passed test 42");
		else
			System.out.println("Failed test 42");

		// tests [43, 45] - removeRange(int start, int stop)
		System.out.println("\nTest 43 - removeRange(E obj) 0 width on a 1-element list");
		list = new LinkedList<>();
		list.add("A");
		list2 = new LinkedList<>();
		list2.add("A");
		list.removeRange(0, 0);
		if (list.equals(list2))
			System.out.println("Passed test 43");
		else
			System.out.println("Failed test 43");

		System.out.println("\nTest 44 - removeRange(E obj) the middle of the list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.removeRange(1, 3);
		list2 = new LinkedList<>();
		list2.add("A");
		list2.add("D");
		if (list.equals(list2))
			System.out.println("Passed test 44");
		else
			System.out.println("Failed test 44");

		System.out.println("\nTest 45 - removeRange(E obj) the end of the list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.removeRange(3, 4);
		list2 = new LinkedList<>();
		list2.add("A");
		list2.add("B");
		list2.add("C");
		if (list.equals(list2))
			System.out.println("Passed test 45");
		else
			System.out.println("Failed test 45");

		// tests [46, 48] - set(int pos, E item)
		System.out.println("\nTest 46 - set(int pos, E item) on a 1-element list");
		list = new LinkedList<>();
		list.add("A");
		list.set(0, "B");
		if (list.get(0).equals("B"))
			System.out.println("Passed test 46");
		else
			System.out.println("Failed test 46");

		System.out.println("\nTest 47 - set(int pos, E item) on multiple elements in a list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.set(0, "B");
		list.set(2, "B");
		if (list.get(0).equals("B") && list.get(1).equals("B") && list.get(2).equals("B"))
			System.out.println("Passed test 47");
		else
			System.out.println("Failed test 47");

		System.out.println("\nTest 48 - set(int pos, E item) on the same element in a list");
		list = new LinkedList<>();
		list.add("A");
		list.set(0, "B");
		list.set(0, "C");
		if (list.get(0).equals("C"))
			System.out.println("Passed test 48");
		else
			System.out.println("Failed test 48");

		// tests [49, 51] - set(int pos, E item)
		System.out.println("\nTest 49 - size() on an empty list");
		list = new LinkedList<>();
		if (list.size() == 0)
			System.out.println("Passed test 49");
		else
			System.out.println("Failed test 49");

		System.out.println("\nTest 50 - size() on a list with removed elements");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.remove(0);
		if (list.size() == 1)
			System.out.println("Passed test 50");
		else
			System.out.println("Failed test 50");

		System.out.println("\nTest 51 - size() on a long list");
		list = new LinkedList<>();
		for (int i = 0; i < 10000; i++) {
			list.add("");
		}
		for (int i = 9999; i > 0; i--) {
			list.remove(i);
		}
		if (list.size() == 1)
			System.out.println("Passed test 51");
		else
			System.out.println("Failed test 51");

		// tests [52, 54] - toString()
		System.out.println("\nTest 52 - toString() on an empty list");
		list = new LinkedList<>();
		if (list.toString().equals("[]"))
			System.out.println("Passed test 52");
		else
			System.out.println("Failed test 52");

		System.out.println("\nTest 53 - toString() on a small list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		if (list.toString().equals("[A, B, C]"))
			System.out.println("Passed test 53");
		else
			System.out.println("Failed test 53");

		System.out.println("\nTest 54 - toString() on a small list");
		list = new LinkedList<>();
		for (int i = 0; i < 10000; i++) {
			list.add("A");
		}
		if (list.toString().length() == 30000)
			System.out.println("Passed test 54");
		else
			System.out.println("Failed test 54");

		// tests [55, 57] - addFirst(E item)
		System.out.println("\nTest 55 - addFirst(E item) on an empty list");
		list = new LinkedList<>();
		list.addFirst("A");
		if (list.get(0).equals("A"))
			System.out.println("Passed test 55");
		else
			System.out.println("Failed test 55");

		System.out.println("\nTest 56 - addFirst(E item) many times");
		list = new LinkedList<>();
		for (int i = 0; i < 1000; i++) {
			list.addFirst(i + "");
		}
		if (list.get(700).equals("299"))
			System.out.println("Passed test 56");
		else
			System.out.println("Failed test 56");

		System.out.println("\nTest 57 - addFirst(E item) to an exisiting list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.addFirst("D");
		list2 = new LinkedList<>();
		list2.add("D");
		list2.add("A");
		list2.add("B");
		list2.add("C");
		if (list.equals(list2))
			System.out.println("Passed test 57");
		else
			System.out.println("Failed test 57");

		// tests [58, 60] - addLast(E item)
		System.out.println("\nTest 58 - addFirst(E item) to an empty list");
		list = new LinkedList<>();
		list.addLast("A");
		if (list.get(0).equals("A"))
			System.out.println("Passed test 58");
		else
			System.out.println("Failed test 58");

		System.out.println("\nTest 59 - addLast(E item) many times");
		list = new LinkedList<>();
		for (int i = 0; i < 1000; i++) {
			list.addLast(i + "");
		}
		if (list.get(300).equals("300"))
			System.out.println("Passed test 59");
		else
			System.out.println("Failed test 59");

		System.out.println("\nTest 60 - addLast(E item) to an existing list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.addLast("C");
		list2 = new LinkedList<>();
		list2.add("A");
		list2.add("B");
		list2.add("C");
		if (list.equals(list2))
			System.out.println("Passed test 60");
		else
			System.out.println("Failed test 60");

		// tests [61, 63] - removeFirst()
		System.out.println("\nTest 61 - removeFirst() on a 1-element list");
		list = new LinkedList<>();
		list.add("A");
		list.removeFirst();
		if (list.equals(new LinkedList<>()))
			System.out.println("Passed test 61");
		else
			System.out.println("Failed test 61");

		System.out.println("\nTest 62 - removeFirst() multiple times on a list");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.removeFirst();
		list.removeFirst();
		list2 = new LinkedList<>();
		list2.add("C");
		list2.add("D");
		if (list.equals(list2))
			System.out.println("Passed test 62");
		else
			System.out.println("Failed test 62");

		System.out.println("\nTest 63 - removeFirst() while adding to the front");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.removeFirst();
		list.add("C");
		list.removeFirst();
		list.add("D");
		if (list.equals(list2))
			System.out.println("Passed test 63");
		else
			System.out.println("Failed test 63");

		// tests [64, 66] - removeLast()
		System.out.println("\nTest 64 - removeFirst() on a 1-element list");
		list = new LinkedList<>();
		list.add("A");
		list.removeLast();
		if (list.equals(new LinkedList<>()))
			System.out.println("Passed test 64");
		else
			System.out.println("Failed test 64");

		System.out.println("\nTest 65 - removeLast() multiple times on a list");
		list = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i + "");
		}
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0)
				list.removeLast();
		}
		list2 = new LinkedList<>();
		list2.add("0");
		list2.add("1");
		list2.add("2");
		list2.add("3");
		list2.add("4");
		if (list.equals(list2))
			System.out.println("Passed test 65");
		else
			System.out.println("Failed test 65");

		System.out.println("\nTest 66 - removeLast() while adding to the front");
		list = new LinkedList<>();
		list.add("A");
		list.add("B");
		list.removeLast();
		list.add("C");
		list.removeLast();
		list.add("D");
		list2 = new LinkedList<>();
		list2.add("A");
		list2.add("D");
		if (list.equals(list2))
			System.out.println("Passed test 66");
		else
			System.out.println("Failed test 66");
	}

	private static Object[] toArray(LinkedList<String> list) {
		Object[] result = new Object[list.size()];
		Iterator<String> it = list.iterator();
		int index = 0;
		while (it.hasNext()) {
			result[index] = it.next();
			index++;
		}
		return result;
	}

	// pre: none
	private static boolean arraysSame(Object[] one, Object[] two) {
		boolean same;
		if (one == null || two == null) {
			same = (one == two);
		} else {
			// neither one or two are null
			assert one != null && two != null;
			same = one.length == two.length;
			if (same) {
				int index = 0;
				while (index < one.length && same) {
					same = (one[index].equals(two[index]));
					index++;
				}
			}
		}
		return same;
	}
}
