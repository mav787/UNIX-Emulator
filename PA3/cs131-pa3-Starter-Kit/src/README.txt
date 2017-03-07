This file indicates that the work submitted is completed by myself.

The implementation of PA3 is done by completing the Bitwise.java by bit manipulations to fix the free map, and constructing new indirect block objects and make disk operations (read and write) to support the indirection (disk operations are used to modify the contents get stored in indirectBlockObject.ptr[] and save the modified indirectBlock on disk). FreeMap.find() and save() methods are called to acquire new spaces on disk.
Three cases (single, double and triple indirection) are determined by the range of blockNum, then three methods (singleIndirect(),doubleIndirect() and tripleIndirect(), parameters not included) are called to deal with the corresponding issue to compute the logical address. 

The implementation of PA3 passes all tests, but the process is quite slow (It takes me 146 seconds to pass all 53 tests on my Macbook Air).
Haotian Sun