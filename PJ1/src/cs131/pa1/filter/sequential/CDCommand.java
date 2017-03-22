package cs131.pa1.filter.sequential;

import java.util.*;
import java.io.*;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

 class CDCommand extends SubCommand{
	//argument with options
	 
	 //private String command;

	 
	 CDCommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = false;
		 this.out = false;
		 
	 }
	 
	
	 //this.argument = this.command.substring(3);  // string index out of bound
	// argument = argument.trim();
	
	
	public void process(){
		
		
		
		if (this.prev != null){
			if (((SubCommand)this.prev).valid == false){
				return;
			}
		
		if (((SubCommand)this.prev).out == true){
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			this.valid = false;
			return;
		}
		
	}
		
		if (!(this.next instanceof DefaultFilter)){
		if (((SubCommand)this.next).in == true){
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(command));
			this.valid = false;
			return;
			}
		}
		
		if (command.length() <= 2){
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
			//SequentialREPL.currentWorkingDirectory = System.getProperty("user.dir");
		}
		else{
		String route = command.substring(3);
		route = route.trim();
		String real = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + route;
		
		
		File file = new File(real);
			if (!file.exists()){
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(command));
				
			}
			
			//file exists
			else if (route.equals("..")){
				int loc = SequentialREPL.currentWorkingDirectory.lastIndexOf(Filter.FILE_SEPARATOR);
				if (loc <= 0){
					
				}
				else
				SequentialREPL.currentWorkingDirectory = SequentialREPL.currentWorkingDirectory.substring(0, loc);
			}
			
			else if (route.equals(".")){
				// this means what?
			}
			
			else{
				SequentialREPL.currentWorkingDirectory = real;
			}
		
		}
		
	}
		protected String processLine(String line){
		return null;
	}
}
