package main;

public class WorkTimeAnalysisTool {
	
	private WorkEntrySearchNode root;


	public WorkTimeAnalysisTool(WorkEntry[] entries) {
		root = new WorkEntrySearchNode(entries[0].getActivity());
		root.add(entries[0]);
		WorkEntrySearchNode tmp = new WorkEntrySearchNode(null);
		for (int i = 1; i < entries.length; i++) {
			tmp = new WorkEntrySearchNode(entries[i].getActivity());
			root = root.search(tmp);
			if(tmp.compareTo(root) != 0) {
				tmp.add(entries[i]);
				root = root.insert(tmp);
			}else {
				root.add(entries[i]);
			}
		}
	}
	
	
	public String parse(String cmd) {
		if (cmd == null) {
			return null;
		}
		if (cmd.equals("list l")) {
			return root.toString();
		}
		if(cmd.equals("list r")) {
			return root.getByRecent();
		}
		if (cmd.contains("del")) {
			String[] cmdArr = cmd.split(" ");
			int i = Integer.parseInt(cmdArr[1]);
			try{
				root = root.del(i);
				return null;
			}catch(Exception e) {
				return "[del] command recieved an invalid argument";
			}
		}
		if (cmd.contains("search")) {
			String[] cmdArr = cmd.split(" ");
			cmd = cmdArr[1];
			WorkEntrySearchNode cmdNode = new WorkEntrySearchNode(cmd);
			root = root.search(cmdNode);
			if (root.equals(cmdNode)) {
				return root.getEntryData();
			}else {
				return "Does not contain" + " " + cmd;
			}
		}
		return "Invalid Command: " + cmd;
	}
	
	
	
}
