package synchronization;

/*
 * BoundedBuffer implementation using synchronized methods
 */
public class BoundedBufferSynchronized extends BoundedBuffer{

   public synchronized void put(Object x) throws InterruptedException {
	   int localCount;
       while (count == items.length)
    	   this.wait();
       localCount = count;
       items[putptr] = x;
       if (++putptr == items.length)
    	   putptr = 0;
       if (localCount != count)
    	   System.err.println("Oh no, somebody changed counter!!");
       ++count;
       this.notifyAll();
   }

   public synchronized Object take() throws InterruptedException {
	   int localCount;
       while (count == 0)
    	   this.wait();
       localCount = count;
       Object x = items[takeptr];
       if (++takeptr == items.length)
    	   takeptr = 0;
       if (localCount != count)
    	   System.err.println("Oh no, somebody changed counter!!");
       --count;
       this.notifyAll();
       return x;
   }
 }
