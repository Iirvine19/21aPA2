package main;

public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {
	
	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC
	
	private String key;
	
	private WorkEntry[] entries;
	private int numEntries;
	
	private boolean isRoot;
/**
 * 
 * @param activity
 */
	public WorkEntrySearchNode(String activity) {
		key = activity;
		left = null;
		right = null;
		parent = null;
		entries = new WorkEntry[10];
		numEntries = 0;
		
	}
	
	/**
	 * 
	 */
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
		WorkEntrySearchNode tmp = this;
		while(!(tmp.key.equals(node.key)) && !(tmp.left == null && tmp.right == null)) {
			if(tmp.compareTo(node) >= 0) {
				if(tmp.left != null) {
					tmp = tmp.left;
				}else if(tmp.right != null) {
					break;
				}
			}else {
				if(tmp.right != null) {
					tmp = tmp.right;
				}else if(tmp.left != null) {
					break;
				}
			}
		}
		return splay(tmp);
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		WorkEntrySearchNode tmp = this;
		do {
			if(tmp.compareTo(node) >= 0) {
				if(tmp.left == null) {
					tmp.left = node;
					node.parent = tmp;
				}
				tmp = tmp.left;
			}else {
				if(tmp.right == null) {
					tmp.right = node;
					node.parent = tmp;
				}
				tmp = tmp.right;
			}
		}while(tmp != node);
		return splay(tmp);
	}
	
	/**
	 * 
	 */
	public String toString() {
		return inOrderSeparated(this);
		
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private String inOrderSeparated(WorkEntrySearchNode node) {
		String ret ="";
		if(node.left != null) {
			ret += inOrderSeparated(node.left);
		}
		ret += node.key + "/n";
		if(node.right != null) {
			ret += inOrderSeparated(node.right) ;
		}
		return ret;
	}
	private String inOrderTogether(WorkEntrySearchNode node) {
		String ret ="(";
		if(node.left != null) {
			ret += inOrderTogether(node.left);
		}
		ret += node.key;
		if(node.right != null) {
			ret += inOrderTogether(node.right);
		}
		return ret + ")";
	}
	/**
	 * 
	 * @return
	 */
	public String getStructure() {
		return inOrderTogether(this);
		
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
		if(entries[i] != null) {
			entries[i] = null;
			adjustEntries(i);
			numEntries--;
			if(numEntries == 0) {
				return this.remove();
			}
			return this;
		}else {
			throw new IndexOutOfBoundsException("There is not entry in that slot");
		}
	}
	
	private WorkEntrySearchNode remove() {
		if(this.left == null && this.right == null) {
			if(this.equals(this.parent.right)) {
				this.parent.right = null;
			}else {
				this.parent.left = null;
			}
			return null;
		}else if(this.left == null){
			WorkEntrySearchNode tmp = splay(max(this.left));
			tmp.right = this.right;
			if(this.equals(this.parent.right)) {
				this.parent.right = tmp;
			}else {
				this.parent.left = tmp;
			}
			return tmp;
		}else if (this.right == null) {
			if(this.equals(this.parent.right)) {
				this.parent.right = this.left;
			}else {
				this.parent.left = this.left;
			}
		}else {
			
		}
		return null;
	}

	private WorkEntrySearchNode max(WorkEntrySearchNode v) {
		while(v.right != null) {
			v = v.right;
		}
		return v;
	}

	private void adjustEntries(int i) {
		while (i < numEntries) {
			entries[i] = entries[i + 1];
			i++;
		}
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
		Q.enqueue(root);
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
		return ret.toString();
	}
	
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		return other.getClass().equals(this.getClass()) && ((WorkEntrySearchNode)other).key.equals(this.key);
	}
	
	private WorkEntrySearchNode splay(WorkEntrySearchNode node) {
			while(node.parent != null) {
			//Zig
			if(node.parent.parent == null) {
				if(node.equals(node.parent.right)) {
					rotateLeft(node.parent);
				}else {
					rotateRight(node.parent);
				}
			}
			//Zig Zag RL
			else if(node.parent == node.parent.parent.left && node == node.parent.right) {
				rotateLeft(node.parent);
				rotateRight(node.parent);
			}
			
			//Zig Zag LR
			else if(node.parent == node.parent.parent.right && node == node.parent.left) {
				rotateRight(node.parent);
				rotateLeft(node.parent);
			}
			//Zig Zig LL
			else if(node.parent == node.parent.parent.left) {
				rotateRight(node.parent.parent);
				rotateRight(node.parent);
			}
			//Zig Zig RR
			else if(node.parent == node.parent.parent.right) {
				rotateLeft(node.parent.parent);
				rotateLeft(node.parent);
			}
		}
		return node;
	}
	

	private void rotateLeft(WorkEntrySearchNode node) {
		if(node.parent != null) {
			if(node.equals(node.parent.left)) {
				node.parent.left = node.right;
			}else {
				node.parent.right = node.right;
			}
		}
		
		node.right.parent = node.parent;
		node.parent = node.right;
		node.right = node.right.left;
		if(node.right != null) {
			node.right.parent = node;
		}
		node.parent.left = node;
	}

	private void rotateRight(WorkEntrySearchNode node) {
		if (node.parent != null) {
			if(node.equals(node.parent.left)) {
				node.parent.left = node.left;
			}else {
				node.parent.right = node.left;
			}
		}
		node.left.parent = node.parent;
		node.parent = node.left;
		node.left = node.left.right;
		if(node.left != null) {
			node.left.parent = node;
		}
		node.parent.right = node;
	}
}
