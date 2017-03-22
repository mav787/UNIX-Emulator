package synchronization;

public abstract class BoundedBuffer {
   final Object[] items = new Object[100];
   int count = 0;   // how many elements currently in the buffer
   int putptr = 0;  // the position of the next put in the buffer
   int takeptr = 0; // the position of the next take in the buffer

   public abstract void put(Object x) throws InterruptedException;
   public abstract Object take() throws InterruptedException ;
 }