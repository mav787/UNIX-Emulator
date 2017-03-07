package edu.Brandeis.cs131.Common.HaotianSun;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;

public abstract class MyClient extends Client {

    public MyClient(String label,Industry industry) {
    	
        super(label, industry, (int) (Math.random() * 10), 3);		// default requestLevel = 3
    }
}
