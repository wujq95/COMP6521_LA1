# COMP6521_LA1

## Introduction
This is the first lab project of course COMP6521. The purpose is to merge and deduplicate two files with the same format and analyze the time and io number of the process. The line number of files is about 1000,000 and 500,000. They need to be put in the file `/src/Data_Files`.

## Configuration
1. Language: Java
2. Algorithm: Two_Phase Multiway Merge Sort(TPMMS)
3. Test Framework: JUnit4

## Implementation
#### Phase 1
Sort main memory-sized partitions to produce the sorted sublists.
1. compute how many tuples a block can hold. 
2. compute how many blocks the main memory can hold (B)
3. compute how many times(n) these data should be sort in memory and divide the file to n sublists. 
4. read every sublist to memory to sort by quicksort.
5. write the sorted sublists to a new file in the disk.

#### Phase 2
Deduplicating and Merging data
1. Put all sublists into memory and compute how many tuples a sublist should hold.
2. compute the smallest element s and its sublist l from the first element of these sublists.
3. check whether the last element of the buffer list (A) equals this element or not:  
   if equal:  
   &nbsp;&nbsp;&nbsp;&nbsp; compute the newest date n then substitute the last element as the newest data.  
   &nbsp;&nbsp;&nbsp;&nbsp;#A[-1] = n  
   else:  
   &nbsp;&nbsp;&nbsp;&nbsp; append this element to the buffer list.  
   &nbsp;&nbsp;&nbsp;&nbsp;#A.append(s)
4. pop the first element from l and append a new element from disk.  
   l.pop(0)  
   l.append(new_element)
5. if the buffer list (A) is full, save it in the disk and create a new empty buffer list until all sublists have been sorted.

