package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.Message;

class GREPCommand extends SubCommand {
	 
	//private String command;
	private String argument;
	
	 
	 GREPCommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = true;
		 this.out = true;
	 }
	 
	 //this.argument = subcommand.substring(5);		// string index out of range
	// argument = argument.trim();
	 
	 public void process(){
		 if (command.length() <= 4){
			 this.argument = null;
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
				this.valid = false;
				return;
		 }
		 this.argument = command.substring(5);
		 
		 if (this.prev != null){
			 if (((SubCommand)this.prev).valid == false){
				 this.valid = false;
				 return;
			 }
		 if (((SubCommand)this.prev).out == false){
			 System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
			 this.valid = false;
		 	}
		 }
		 if (this.prev == null){
			 System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
			 this.valid = false;
		 }
			while (!input.isEmpty()){
				String line = input.poll();
				String processedLine = processLine(line);
				if (processedLine != null){
					output.add(processedLine);
				}
				
				
			}	
		}
	
	
	protected String processLine(String line){
		if (line.contains(argument)){
			return line;
		} 
		else return null;
	}

}
