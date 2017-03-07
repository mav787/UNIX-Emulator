package edu.Brandeis.cs131.Common.HaotianSun;

import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;

public class BasicClient extends MyClient {
	protected final static String type = "BASIC";
	
	public static String getType(){
		return BasicClient.type;
	}

	public BasicClient(String name, Industry industry){
		super(name,industry);			
	}
	public String toString() {
        //return String.format("%s CLIENT %s", this.industry, this.name);
		return this.getIndustry() + " " + BasicClient.getType() + " " + this.getName();
    }
}
