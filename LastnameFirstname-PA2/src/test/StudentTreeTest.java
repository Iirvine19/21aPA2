package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.WorkEntrySearchNode;

class StudentTreeTest {

	@Test
	void testInsert() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("d");
		WorkEntrySearchNode node2 = new WorkEntrySearchNode("a");
		WorkEntrySearchNode node3 = new WorkEntrySearchNode("b");
		WorkEntrySearchNode node4 = new WorkEntrySearchNode("c");
		WorkEntrySearchNode node5 = new WorkEntrySearchNode("i");
		WorkEntrySearchNode node6 = new WorkEntrySearchNode("e");
		WorkEntrySearchNode node7 = new WorkEntrySearchNode("f");
		WorkEntrySearchNode node8 = new WorkEntrySearchNode("g");
		WorkEntrySearchNode node9 = new WorkEntrySearchNode("h");
		root = root.insert(node4); //3
		root = root.insert(node3); //2
		root = root.insert(node2); //1
		root = root.insert(node9); //8
		root = root.insert(node7); //6
		root = root.insert(node8); //7
		root = root.insert(node6); //5
		System.out.print(root.toString());
		System.out.print(root.getByRecent());
		root = root.insert(node5);
		assertTrue(root.equals(node5));
		assertEquals(root.parent, null);
		System.out.print(root.getStructure());
		System.out.print(root.getByRecent());
	}

	@Test
	void testSearch() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("d");
		WorkEntrySearchNode node2 = new WorkEntrySearchNode("a");
		WorkEntrySearchNode node3 = new WorkEntrySearchNode("b");
		WorkEntrySearchNode node4 = new WorkEntrySearchNode("c");
		root = root.insert(node2); //3
		root = root.insert(node3); //2
		root = root.insert(node4); //1
		root = node4.search(node2);
		assertEquals(node2, root);
	}
}
