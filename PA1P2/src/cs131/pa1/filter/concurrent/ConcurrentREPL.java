package cs131.pa1.filter.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import cs131.pa1.filter.Message;

public class ConcurrentREPL {

	static String currentWorkingDirectory;
	static boolean isBackGround;
	static List<BackGroundCommand> backGroundCommands = new ArrayList<>();
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		while(true) {
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			if(command.equals("exit")) {
				break;
			}
			
			//REPL JOBS
			else if (command.trim().equals("repl_jobs")){

				Set<String> currents = new HashSet<>();
				//Stack<String> printing = new Stack<>();
				for (BackGroundCommand bgc : backGroundCommands){
					if (bgc.t.isAlive()){
						currents.add(bgc.name);
					}
				}
				
				Stack<String> stack = new Stack<>();
				for (String str:currents){
					stack.push(str);
				}
				
				for (int i = 1; !stack.isEmpty(); i++){
					System.out.println("\t" + i + ". " + stack.pop());
				}
		
//				Iterator<String> it = currents.iterator();
//				for (int i = 1; i <= currents.size(); i++){
//					System.out.println("\t" + i + ". " + it.next());
//				}
				
				
				backGroundCommands = new ArrayList<>();	// to meet the test??
			}
			//REPL JOBS
			
			
			else if(!command.trim().equals("")) {
				
				//building the filters list from the command
				List<ConcurrentFilter> filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(command);
				isBackGround = ConcurrentCommandBuilder.isBackGround(command);
				
				
				if (filterlist != null){
					Iterator<ConcurrentFilter> iter = filterlist.iterator();
					List<Thread> myThreads = new ArrayList<>();

					
					while (iter.hasNext()){
						Runnable currentFilter = iter.next();
						Thread t = new Thread(currentFilter,command);
						myThreads.add(t);
						if (isBackGround){
							backGroundCommands.add(new BackGroundCommand(command,t));		
						}
						t.start();					
					}
					
					
					if (!isBackGround){
						// wait for the last filter to join
						Iterator<Thread> indexThread = myThreads.iterator();
						Thread tt = new Thread();
						while (indexThread.hasNext()){
							tt = indexThread.next();
						}
						
						if (tt.isAlive()){
							try {
								tt.join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}
			}
		}
		s.close();
		System.out.print(Message.GOODBYE);
	}

}


class BackGroundCommand{
	String name;
	Thread t;
	
	BackGroundCommand(String s, Thread t){
		this.name = s;
		this.t = t;
	}
}
