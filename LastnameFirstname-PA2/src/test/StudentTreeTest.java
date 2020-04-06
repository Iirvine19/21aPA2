package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.WorkEntrySearchNode;

class StudentTreeTest {

	@Test
	void testInsert() {
		WorkEntrySearchNode root = new WorkEntrySearchNode("grading");
		WorkEntrySearchNode node = new WorkEntrySearchNode("proctoring");
		root.insert(node);
		assertTrue(root.parent.equals(node));
		assertTrue(node.left.equals(root));
	}

}
