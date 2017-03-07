package cs131.pa1.filter.sequential;



public class CommandFactory {
	
	private static CommandFactory commandFactory;
	
	public static CommandFactory getFactory() {
		return commandFactory == null ? new CommandFactory() : commandFactory;
	}
	
	public SequentialFilter create(String command) {
		String[] element = command.split(" ");
		for (String s:element){
			s = s.trim();
		}
		//if (!command.contains(">")){
		switch (element[0]){
		case "cd":
			return new CDCommand(command);
		case "grep":
			return new GREPCommand(command);
		case "head":
			return new HEADCommand(command);	
		case "ls":
			return new LSCommand(command);
		case "pwd":
			return new PWDCommand(command);
		case "wc":
			return new WCCommand(command);
		
			
		default:
			return new NotACommand(command);
		}
	
		//else}
	}
}
