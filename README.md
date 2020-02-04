# COMP6521_LA1

### config
1. language: java
2. algorithm: TPMMS

### TPMMS
#### Phase 1
Sort main memory-sized partitions to produce the sorted sublists.
1. compute how many tuples a block can hold. 
2. compute how many blocks main memory can hold (B)
3. compute how many times(n) these data should be sort in memory and devide the file to n sublists. 
4. read every sublist to memory to sort by quick sort.
5. write the sorted sublists to a new file in disk.

#### Phase 2
Deduplicating and Merging data
1. Put all sublists into memory and compute how many tuples a sublists should hold.
2. compute the smallest element s and its sublist l from the first element of these sublists.
3. check whether the last element of the buffer list (A) equal this element or not:  
   if equal:  
   &nbsp;&nbsp;&nbsp;&nbsp; compute the newest date n then substitude the last element as the newest data. #A[-1] = n  
   else:  
   &nbsp;&nbsp;&nbsp;&nbsp; append this element to buffer list. #A.append(s)
4. pop the first element from l and append a new element from disk.  
   l.pop(0)  
   l.append(new_element)
5. if the buffer list (A) is full, save it in disk and creat a new empty buffer list until all sublists have been sorted.

