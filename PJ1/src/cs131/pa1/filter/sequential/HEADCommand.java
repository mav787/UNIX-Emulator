package cs131.pa1.filter.sequential;

import java.util.*;

import java.io.*;

import cs131.pa1.filter.*;


class HEADCommand extends SubCommand{
	
	//private String command;
	private String argument;
	private int option;
	private static int deflt = 10;
	
	HEADCommand(String subcommand){
		super(subcommand);
		 this.input = new LinkedList<String>();
		 this.output = new LinkedList<String>();
		 this.in = false;
		 this.out = true;
	}

	
	 
	public void process(){
		if (this.prev != null){
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(command));
			return;
		}
		
		//analysis
		if (this.command.length() <= 4){		// 1.no argument
			this.argument = null;
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
			return;
		}
		
		// with argument, substring
		argument = command.substring(5);	
		argument = argument.trim();
		int index = argument.indexOf(" ");
		
		// 2.no option & no white space
		if (index <= 0){
			option = deflt;
			this.doJob();
			}
		
		else{
			
			//3.with white space
			//with options
			if (argument.startsWith("-")){
				if (index == 1){
					System.out.print(Message.INVALID_PARAMETER.with_parameter(command));
					return;
					//print something??
				}
				else{
				try{
					option = Integer.parseInt(argument.substring(1, index));	//leave the "-"
					argument = argument.substring(index); 
					argument = argument.trim();
					if (argument == null || argument.length() == 0){
						System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
						return;
					}
					this.doJob();
				}
				catch (NumberFormatException e){
					System.out.print(Message.INVALID_PARAMETER.with_parameter(command));
				}
				
				}
			}
			else{
				// 4.without options but with white space
				// further : ""needed?
				option = deflt;
				this.doJob();
			}
		}
	}
		
		/////////////////////////////////////////////////////////////
	
	private void doJob(){
		if (argument == null || argument.length() == 0){
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
			
			this.valid = false;
			
			return;
		}
		if (argument.startsWith("-")){
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(command));
			
			this.valid = false;
			
			return;
		}
		argument = argument.trim();
		File file = new File(SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + argument);
		if (!file.exists()){
		System.out.print(Message.FILE_NOT_FOUND.with_parameter(command));
		
		this.valid = false;
		
		}
		else{
			try {
				Scanner scan = new Scanner(file);
				for (int i = 0; i < option && scan.hasNextLine(); i++){
					//System.out.println(output.offer(scan.nextLine()));
					output.offer(scan.nextLine());
				}
				
				// if it is the last filter
				//for (String s:output){
				//	System.out.println(s);
				//}
				scan.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}		
		}
	}
		
		
	protected String processLine(String line){
		return null;
	}
}
