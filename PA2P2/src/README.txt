This file indicates that the work submitted is completed by myself.

The implementation of PA2 part 2 is done by using Java locks and condition variables. Each client waiting in a specific queue of its basic server is assigned to a condition variable, and it is used to call await() when the client is not able to connect, and signalAll() when it successfully connects or disconnects.
The call of lock() is followed by a try/catch block, which contains the unlock() method call, as is done in the code BoundedBufferConditions.java provided.

The code passes all the JUnit tests.
Haotian Sun