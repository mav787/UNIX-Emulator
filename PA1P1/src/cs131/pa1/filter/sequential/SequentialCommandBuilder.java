package cs131.pa1.filter.sequential;

import java.util.*;
import cs131.pa1.*;

public class SequentialCommandBuilder {
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){		// without input and output

		List<SequentialFilter> SFList = new ArrayList<SequentialFilter>();
		
		String[] subs = command.split("\\|");		// divide into sub-commands
		for (String s:subs){
			s = s.trim();
			SequentialFilter sf = CommandFactory.getFactory().create(s);
			List<SequentialFilter> unitList = ((SubCommand)sf).parse();
			
			
			
			SFList.addAll(unitList);
			}
		
		//array index out of bound here
		if (SFList.get(SFList.size() - 1) instanceof REDIRECTIONCommand){
			
		}
		else{
		SFList.add(new DefaultFilter(""));
		}
		// link the filters
		SequentialCommandBuilder.linkFilters(SFList);
		
		return SFList;
	}
	
	/*private static SequentialFilter determineFinalFilter(String command){
		String[] subs = command.split(">|\\|");
		SequentialFilter last = CommandFactory.getFactory().create(subs[subs.length - 1]);
		return last;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		return null;
	}
	
	*/
	

	private static void linkFilters(List<SequentialFilter> filters){
		// to link, call the setNext method.
		int num = filters.size();
		for (int i = 0; i < num - 1; i++){
			filters.get(i).setNextFilter(filters.get(i+1));
		}
		
	}
}
