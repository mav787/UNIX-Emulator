package edu.Brandeis.cs131.Common.HaotianSun;

import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;

public class SharedClient extends MyClient {
	protected final static String type = "SHARED";
	
	public static String getType(){
		return SharedClient.type;
	}


	public SharedClient(String name, Industry industry){
		super(name,industry);			
	}
	public String toString() {
        //return String.format("%s CLIENT %s", this.industry, this.name);
		return this.getIndustry() + " " + SharedClient.getType() + " " + this.getName();
    }

}
