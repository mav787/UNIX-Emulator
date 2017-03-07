package edu.Brandeis.cs131.Common.HaotianSun;

import java.util.HashMap;
import java.util.LinkedList;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;
import edu.Brandeis.cs131.Common.Abstract.Server;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MasterServer extends Server {

    private final Map<Integer, List<Client>> mapQueues = new HashMap<Integer, List<Client>>();
    private final Map<Integer, Server> mapServers = new HashMap<Integer, Server>();
    private final Map<Integer, Condition> mapConditions = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    

    public MasterServer(String name, Collection<Server> servers, Log log) {
        super(name, log);
        Iterator<Server> iter = servers.iterator();
        while (iter.hasNext()) {
            this.addServer(iter.next());
        }
    }

    public void addServer(Server server) {
        int location = mapQueues.size();
        this.mapServers.put(location, server);
        this.mapQueues.put(location, new LinkedList<Client>());
        this.mapConditions.put(location, lock.newCondition());
    }

    @Override
    public boolean connectInner(Client client) {
        // TODO Auto-generated method stub
    	int key = getKey(client);		// compute the key
    	BasicServer bs = (BasicServer) mapServers.get(key);		// get the corresponding server
    	Queue<Client> queue = (LinkedList<Client>) mapQueues.get(key);	// and its corresponding queue
    	Condition condition = mapConditions.get(key);			// and the corresponding condition variable
    	
        lock.lock();
        try{
	        
	        // 1. getKey, and append itself to the corresponding queue.
	        // 2. determine if the head is runnable (call the basicServer.connect()?)
	        // 3. if connected successfully, remove itself from the queue & inform the new head to request
	    	
	    	// if is not empty??？
	    	queue.add(client);
	    	
	    	//Client head = queue.peek();
	    	while (queue.peek() != client){			// client is not head of queue
	    		condition.await();
	    	}
	    	
	    	while (!bs.connect(client)){			// connect is rejected
	    		condition.await();
	    	}
	    	
	    	// connection success
	    	queue.poll();
	    	condition.signalAll();
	    	return true;
        }
        catch (InterruptedException ex){
        	ex.printStackTrace();
        	return false;
        }
        finally{
        	lock.unlock();
        }
    }

    @Override
    public void disconnectInner(Client client) {
        // TODO Auto-generated method stub
    	// call the basicServer.disconnect() 
    	int key = getKey(client);		// compute the key
    	BasicServer bs = (BasicServer) mapServers.get(key);		// get the corresponding server
    	//Queue<Client> queue = (LinkedList<Client>) mapQueues.get(key);	// and its corresponding queue
    	Condition condition = mapConditions.get(key);			// and the corresponding condition variable
    	
        lock.lock();
        try{
        	bs.disconnect(client);
        	condition.signalAll();
        }
        finally{
        	lock.unlock();
        }
    }

	//returns a number from 0- mapServers.size -1
    // MUST be used when calling get() on mapServers or mapQueues
    // that means mapQueue.get(masterServer.getKey(client))
    private int getKey(Client client) {
        return client.getSpeed() % mapServers.size();
    }
}
