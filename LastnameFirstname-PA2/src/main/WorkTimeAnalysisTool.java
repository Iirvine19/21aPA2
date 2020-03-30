package main;

public class WorkTimeAnalysisTool {
	
	public WorkTimeAnalysisTool(WorkEntry[] entries) {
		throw new UnsupportedOperationException();
	}
	
	
	public String parse(String cmd) {
		if (cmd == null) {
			return null;
		}
		if (cmd.equals("list l")) {
			
		}
		if(cmd.equals("list r")) {
			
		}
		if (cmd.contains("del")) {
			String[] cmdArr = cmd.split(" ");
			int i = Integer.parseInt(cmdArr[1]);
		}
		if (cmd.contains("search")) {
			String[] cmdArr = cmd.split(" ");
			cmd = cmdArr[1];
			
		}
	}
	
	
	
}
