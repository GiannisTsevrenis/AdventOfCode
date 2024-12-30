# Part 1

Pretty straightforward puzzle, you can either use a **Stack** to add the numbers as you create the list and then iterate the list and pop from stack to replace the dots '.' or use **two pointers** (I used two pointers approach). note: when a swap happens move the 2nd pointer until it points to a number and not a dot.

Use BigInteger class for the sum because it will be larger than 2B.

# Part 2

We have to further modify the function that fills the empty spaces. There are a few things to keep in mind this time. My logic was the following:
<br>

1. Go to the end of the ArrayList and find the last positive number. ( in my solution '.' were represented by -1).

2. For each file ID, count how many times it appears in the list and its first index (starting from the end).<br>
   For example in $[0..111....22222]$ we will get:
   $fileID=2, fileIDCount=5, firstIndex=10$
3. Then iterate over the Arraylist until the first index ( because if first index is reached it means there is not enough space before to fit all the group) and count the free memory. If we find enough room to fit all the appearances of current ID file we make the swap and break loop. Else we go to the next ID and start over again.
