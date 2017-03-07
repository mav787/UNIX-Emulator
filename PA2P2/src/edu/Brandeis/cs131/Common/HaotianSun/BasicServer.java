package edu.Brandeis.cs131.Common.HaotianSun;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Server;
import edu.Brandeis.cs131.Common.Abstract.Industry;

public class BasicServer extends Server {
	protected int numberOfBasic, numberOfShared;
	// to memorize the industries of shared clients
	protected List<Industry> notesOfSharedIndustries;
	

	public BasicServer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		numberOfBasic = 0;
		numberOfShared = 0;
		notesOfSharedIndustries = new ArrayList<>();
		
	}

	@Override
	public synchronized boolean connectInner(Client client) {
		// TODO Auto-generated method stub
		if (client instanceof BasicClient){			// 1. client is BasicClient
			if (this.numberOfShared >= 1){
				return false;
			}
			else if (this.numberOfBasic >= 1){
				return false;
			}
			else{
				this.numberOfBasic++;
				return true;
			}
		}
		else if(client instanceof SharedClient){	// 2. client is SharedClient
				if (this.numberOfBasic >= 1){
					return false;
				}
				else if (this.numberOfShared >= 2){			//else number < 2 (0 or 1)
					return false;
				}
				else if (this.numberOfShared == 0){
					this.numberOfShared++;
					this.notesOfSharedIndustries.add(client.getIndustry());
					return true;
				}
				
				else{									// numberOfShared == 1
					if (!notesOfSharedIndustries.contains(client.getIndustry())){
					this.numberOfShared++;
					notesOfSharedIndustries.add(client.getIndustry());
					return true;
					}
				else{									// same industry!!
					return false;
					}
				} 
			}
		return false;
	}

	@Override
	public synchronized void disconnectInner(Client client) {
		// TODO Auto-generated method stub
		if (client instanceof BasicClient){
			this.numberOfBasic--;
		}
		else if (client instanceof SharedClient){
			this.numberOfShared--;
			// have to update the notes here!!
			notesOfSharedIndustries.remove(client.getIndustry());
			
		}
	}
}
