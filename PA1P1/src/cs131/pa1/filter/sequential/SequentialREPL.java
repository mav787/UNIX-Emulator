package cs131.pa1.filter.sequential;

import java.util.*;
import cs131.pa1.filter.Message;





public class SequentialREPL {

	
	
	//fields:
	public static String currentWorkingDirectory;

	
	//methods:
	
	//constructor
	public SequentialREPL(){
		currentWorkingDirectory = System.getProperty("user.dir");
		System.out.print(Message.WELCOME);	//Message.NEWCOMMAND + "" + Message.WELCOME
	}
	
	
	//looping function
	private void loop(Scanner scan){
		
		//currentWorkingDirectory checking:
		//System.out.println("current working directory is: " + SequentialREPL.currentWorkingDirectory);
		
		System.out.print(Message.NEWCOMMAND);
		String command = scan.nextLine();
		
		while(!command.equals("exit")){
			
			//parse first
			List<SequentialFilter> list = SequentialCommandBuilder.createFiltersFromCommand(command);	
			
			// error free command?
			boolean valid = true;
			
			for (SequentialFilter sf:list){
				if (sf instanceof NotACommand){
					valid = false;
					System.out.print(sf.processLine(""));
					break;
				}
			}
			
			//execute
			if (valid){
			for (SequentialFilter sf:list){
					sf.process();
				}
			}

			//complete the cycle

			System.out.print(Message.NEWCOMMAND);
			command = scan.nextLine();
		}
		// input.equals("exit")
			System.out.print(Message.GOODBYE);
	}
	
		
	
	// main entry
	public static void main(String[] args){
		
		SequentialREPL repl = new SequentialREPL();
		repl.loop(new Scanner(System.in));
	}

}
