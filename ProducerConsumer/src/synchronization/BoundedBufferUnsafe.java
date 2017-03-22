package synchronization;

/*
 * Sample class for BoundedBuffer without any synchronization.
 * Uses constant checking of count in a loop.
 * Used only to demonstrate the difference between synchronization approaches
 * It is incorrect, not thread safe and should NOT be used in real life applications!
 * 
*/
public class BoundedBufferUnsafe extends BoundedBuffer{

   public void put(Object x) throws InterruptedException {
       int localCount;
	   while(count == items.length){
    	   System.out.println("Buffer is full, must retry!");
       }
	   localCount = count;
       // other threads might change count at this point! It could become items.length again, which would violate the above condition!
	   items[putptr] = x;
       if (++putptr == items.length)
    	   putptr = 0;
       if (localCount != count)
    	   System.err.println("put(): Oh no, somebody changed counter!!");
       ++count;
   }

   public Object take() throws InterruptedException {
	   int localCount;
       while (count == 0){
         System.out.println("Buffer is empty, must retry!");
       }
       localCount = count;
       // other threads might change count at this point! It could become 0 again, which would violate the above condition!
       Object x = items[takeptr];
       if (++takeptr == items.length)
    	   takeptr = 0;
       if (localCount != count)
    	   System.err.println("take(): Oh no, somebody changed counter!!");
       --count;
       return x;
   }
 }