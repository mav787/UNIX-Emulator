package synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * BoundedBuffer implementation using ReentrantLock and Conditions to check number of elements in buffer
 */
public class BoundedBufferConditions extends BoundedBuffer{
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition();
   final Condition notEmpty = lock.newCondition();

   public void put(Object x) throws InterruptedException {
	 int localCount;
     lock.lock();
     try {
       while (count == items.length)
         notFull.await();
       localCount = count;
       items[putptr] = x;
       if (++putptr == items.length)
    	   putptr = 0;
       if (localCount != count)
    	   System.err.println("Oh no, somebody changed counter!!");
       ++count;
       notEmpty.signal();
     } finally {
       lock.unlock();
     }
   }

   public Object take() throws InterruptedException {
	 int localCount;
     lock.lock();
     try {
       while (count == 0)
         notEmpty.await();
       localCount = count;
       Object x = items[takeptr];
       if (++takeptr == items.length)
    	   takeptr = 0;
       if (localCount != count)
    	   System.err.println("Oh no, somebody changed counter!!");
       --count;
       notFull.signal();
       return x;
     } finally {
       lock.unlock();
     }
   }
 }