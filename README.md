# Doubly Linked List Implementation
Author: Trung Le

## Implementations
- Normal implementation from Professor Rebelsky
- Implementation using circular linked list with dummy node

### Circular linked list with dummy node
Using a circular linked list with a dummy node offers several advantages that can make it a preferable choice for implementing data structures like lists. 

One of the primary benefits is that it simplifies the manipulation of nodes, particularly at the boundaries of the list, such as the head and the tail. In standard doubly linked lists, operations like insertion and deletion at these positions often require conditional logic to handle special cases—such as inserting or removing at the beginning or end of the list—which can complicate the code and increase the risk of errors. 

With a dummy node, these operations are streamlined because the dummy node acts as a sentinel that always exists, reducing the need for edge-case handling. This means that every node always has a predecessor and a successor, simplifying the algorithms for adding and removing nodes by eliminating the need to check for null references.

## Acknowledgement: 
Professor Rebelsky