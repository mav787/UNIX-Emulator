package cs131.pa1.filter.sequential;

import java.util.LinkedList;

import cs131.pa1.filter.*;

import java.io.*;

class LSCommand extends SubCommand{
	//private String command;
	//private String argument;
	 
	 LSCommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = false;
		 this.out = true;
	 }
	
	 //this.argument = subcommand.substring(3);
	 //argument = argument.trim();
	 
	public void process(){
		if (this.prev != null){
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			return;
		}
		File current = new File(SequentialREPL.currentWorkingDirectory);
		if (!current.exists()){
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(command));
		}
		File[] content = current.listFiles();
		
		if (content == null || content.length == 0){
			return;
		}
		else{
		for (File f:content){
			//System.out.println(f.getName());
			output.offer(f.getName());
		}
		}
	}
	
	protected String processLine(String line){
		return null;
	}
}
