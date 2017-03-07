package cs131.pa1.filter.sequential;

import java.util.*;

 class SubCommand extends SequentialFilter{
	
	 protected String command;
	  boolean in,out;
	  boolean valid;
	 SubCommand(String s){
		 this.command = s;
		 this.valid = true;
	 }
	
	 List<SequentialFilter> parse(){
		 List<SequentialFilter> list = new ArrayList<SequentialFilter>();
		 
		 
			 int index = this.command.indexOf(">");
			 if (index < 0){
				 list.add(CommandFactory.getFactory().create(command));
			 }
			 else{
				 if (index > 0){
					 String front = command.substring(0,index);
					 list.add(CommandFactory.getFactory().create(front));
				 }
			 String back = command.substring(index);
			 list.add(new REDIRECTIONCommand(back));
		 }
		 
		 
		 return list;
	 }
	
	protected String processLine(String line){
		return null;
	}
}
