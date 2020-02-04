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
