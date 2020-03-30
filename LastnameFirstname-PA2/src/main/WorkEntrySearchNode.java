package main;

public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {
	
	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 

	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC
	
	private String key;
	
	private boolean isRoot;

	public WorkEntrySearchNode(String activity) {
		key = activity;
		left = null;
		right = null;
		parent = null;
		
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
			return this;
		}
		if(this.left != null) {
			WorkEntrySearch
		}
	}
	
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		throw new UnsupportedOperationException();
	}
	
	public String getStructure() {
		throw new UnsupportedOperationException();
	}
	
	public void add(WorkEntry e) {
		throw new UnsupportedOperationException();
	}
	
	public WorkEntrySearchNode del(int i) {
		throw new UnsupportedOperationException();
	}
	
	public String getEntryData() {
		throw new UnsupportedOperationException();
	}
	
	public String getByRecent() {
		throw new UnsupportedOperationException();
	}
	
	private WorkEntrySearchNode splay(WorkEntrySearchNode root, WorkEntrySearchNode target) {
		// Base cases: root is null or 
	    // key is present at root  
	    if (root == null || root.key.equals(target.key));  
	        return root;  
	  
	    // Key lies in left subtree  
	    if (root.compareTo(target) < 0)  
	    {  
	        // Key is not in tree, we are done  
	        if (root.left == null) return root;  
	  
	        // Zig-Zig (Left Left)  
	        if (root.left.compareTo(target) > 0)  
	        {  
	            // First recursively bring the 
	            // key as root of left-left  
	            root.left.left = splay(root.left.left, target);  
	  
	            // Do first rotation for root,  
	            // second rotation is done after else  
	            root.rotateRight();
	        }  
	        else if (root.left.compareTo(target) < 0) // Zig-Zag (Left Right)  
	        {  
	            // First recursively bring 
	            // the key as root of left-right  
	            root.left.right = splay(root.left.right, target);  
	  
	            // Do first rotation for root.left  
	            if (root.left.right != null)  
	                root.left.rotateLeft();  
	        }  
	  
	        // Do second rotation for root
	        if(root.left != null) {
	        	root.rotateLeft();
	        }
	        return root;
	    }  
	    else // Key lies in right subtree  
	    {  
	        // Key is not in tree, we are done  
	        if (root.right == null) return root;  
	  
	        // Zag-Zig (Right Left)  
	        if (root.right.key > key)  
	        {  
	            // Bring the key as root of right-left  
	            root.right.left = splay(root.right.left, key);  
	  
	            // Do first rotation for root.right  
	            if (root.right.left != null)  
	                root.right.rotateRight();  
	        }  
	        else if (root.right.compareTo(target) < 0)// Zag-Zag (Right Right)  
	        {  
	            // Bring the key as root of  
	            // right-right and do first rotation  
	            root.right.right = splay(root.right.right, target);  
	            root.rotateLeft();  
	        }  
	  
	        // Do second rotation for root  
	       if (root.right != null){
	    	   root.rotateRight();
	    } 
	       return root;
	}  
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
