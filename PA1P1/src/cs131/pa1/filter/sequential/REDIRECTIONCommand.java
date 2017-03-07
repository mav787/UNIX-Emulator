package cs131.pa1.filter.sequential;

import java.util.*;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

import java.io.*;

class REDIRECTIONCommand extends SubCommand{

	//private String command;
	private String argument;

	 
	 REDIRECTIONCommand(String subcommand){
		 super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();;
		 this.in = true;
		 this.out = false;
		 
	 }
	 
	
	 public void process(){
		 //System.out.println("This is a redirection command");
		 if (this.prev == null){
		 //if (input.isEmpty()){
			 System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
		 }
		 
		 
		 
		 if (this.next != null){
			 System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(command));
			 this.valid = false;
			 return;
		 }
		 if (command.length() <= 1){
			 System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
			 return;
		 }
		 
		 
		 for (String s:input){
			 output.offer(s);
		 }
		 
		 argument = command.substring(2);
		 //output = input;
		 //argument is the route specified 
		 // do the translation : input (Queue) ---> output (file)
		 String route = SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + this.argument;
		 
		 
		 File file = new File(route);
		
		 if (!file.exists()){
			 try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 
		 while (!input.isEmpty()){
				String line = input.poll();
				processLine(line,file);
			
			}	

		  
		 
	 }
	 
	 
	 
	 
	 
	 protected void processLine(String line, File file){
		 try{
		 FileWriter fw = new FileWriter(file.getName(),true);
		 BufferedWriter bw = new BufferedWriter(fw);
		 bw.write(line + "\n");
		 bw.close();
		 
		 }
		 catch (IOException e){
			 e.printStackTrace();
		 }
		}
	

protected String processLine(String line){
		return null;
	}
}
