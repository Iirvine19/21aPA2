package main;

public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {
	
	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC
	
	private String key;
	
	private WorkEntry[] entries;
	private int numEntries;
	
	private boolean isRoot;

	public WorkEntrySearchNode(String activity) {
		key = activity;
		left = null;
		right = null;
		parent = null;
		entries = new WorkEntry[10];
		numEntries = 0;
		
	}
	
	public int compareTo(WorkEntrySearchNode other) {
		int i = 0;
		if(this.key.equals(other.key)) {
			return 0;
		}
		while (this.key.charAt(i) == other.key.charAt(i)) {
			i++;
		}
		if(this.key.charAt(i) > other.key.charAt(i)) {
			return 1;
		}
		return -1;
	}
	//This is going to be an inOrder traversal of 
	//the tree rooted at this node. Returns result or
	//last node
	public WorkEntrySearchNode search(WorkEntrySearchNode node) {
		if(this.equals(node)) {
			return splay(this);
		}
		if(this.compareTo(node) >= 0) {
			if(this.left != null) {
				return this.left.search(node);
			}
		}else if(this.right != null) {
				return this.right.search(node);
			}
		return splay(this);
	}
	
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		WorkEntrySearchNode tmp = this;
		do {
			if(tmp.compareTo(node) >= 0) {
				if(tmp.left == null) {
					tmp.left = node;
					tmp.left.parent = tmp;
				}
				tmp = tmp.left;
			}else {
				if(tmp.right == null) {
					tmp.right = node;
					tmp.right.parent = tmp;
				}
				tmp = tmp.right;
			}
		}while(tmp.left != null || tmp.right != null);
		return splay(tmp);
	}
	
	public String toString() {
		throw new UnsupportedOperationException();
	}
	
	public String getStructure() {
		throw new UnsupportedOperationException();
	}
	
	public void add(WorkEntry e) {
		if(numEntries >= entries.length) {
			WorkEntry[] tmp = new WorkEntry[entries.length * 2];
			for(int i = 0; i < entries.length; i++) {
				tmp[i] = entries[i];
			}
			entries = tmp;
		}
		entries[numEntries] = e;
		numEntries++;
	}
	
	public WorkEntrySearchNode del(int i) {
		throw new UnsupportedOperationException();
	}
	
	public String getEntryData() {
		String ret = "";
		int total = 0;
		for (WorkEntry w : entries) {
			ret += w.toString() + System.lineSeparator();
			total += w.getTimeSpent();
		}
		return ret + (total + " h");
	}
	
	public String getByRecent() {
		Queue<WorkEntrySearchNode> Q = new Queue<WorkEntrySearchNode>();
		WorkEntrySearchNode root = this;
		while (root.parent != null) {
			root = root.parent;
		}
		StringBuilder ret = new StringBuilder();
		while(!Q.isEmpty()) {
			WorkEntrySearchNode v = Q.dequeue();
			ret.append(v.key + System.lineSeparator());
			if(v.left != null) {
				Q.enqueue(v.left);
			}
			if(v.right != null) {
				Q.enqueue(v.right);
			}
		}
	}
	
	public boolean equals(Object other) {
		return other.getClass().equals(this.getClass()) && ((WorkEntrySearchNode)other).key.equals(this.key);
	}
	
	private WorkEntrySearchNode splay(WorkEntrySearchNode node) {
		while(node.parent != null) {
			//Zig
			if(node.parent.parent == null) {
				if(node.parent.right.compareTo(node) < 0) {
					node.rotateLeft();
				}else {
					node.rotateRight();
				}
			}
			//Zig Zag RL
			else if(node.parent == node.parent.parent.left && node == node.parent.right) {
				node.rotateLeft();
				node.rotateRight();
			}
			
			//Zig Zag LR
			else if(node.parent == node.parent.parent.right && node == node.parent.left) {
				node.rotateRight();
				node.rotateLeft();
			}
			//Zig Zig LL
			else if(node.parent == node.parent.left) {
				node.parent.rotateRight();
				node.rotateRight();
			}
			//Zig Zig RR
			else if(node.parent == node.parent.right) {
				node.parent.rotateLeft();
				node.rotateLeft();
			}
		}
		
		return node;
	}
	
	private void rotateLeft() {
		WorkEntrySearchNode tmp = this.right;
		this.right = tmp.left;
		if(tmp.left != null) {
			tmp.left.parent = this;
		}
		tmp.parent = this.parent;
		if (this.isRoot == true) {
			this.isRoot = false;
			tmp.isRoot = true;
		}else if(this == this.parent.right) {
			this.parent.right = tmp;
		}else {
			this.parent.left = tmp;
		}
		tmp.left = this;
		this.parent = tmp;
	}
	
	private void rotateRight() {
		WorkEntrySearchNode tmp = this.left;
		this.left = tmp.right;
		if(tmp.right != null) {
			tmp.right.parent = this;
		}
		tmp.parent = this.parent;
		if (this.isRoot == true) {
			this.isRoot = false;
			tmp.isRoot = true;
		}else if(this == this.parent.right) {
			this.parent.right = tmp;
		}else {
			this.parent.left = tmp;
		}
		tmp.right = this;
		this.parent = tmp;
	}
}
