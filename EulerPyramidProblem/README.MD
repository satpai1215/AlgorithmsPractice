Problem 1:

Consider the triangle of numbers below:

   4
  8 7
 1 3 5
6 9 3 4

Starting at the top of the triangle and moving only to adjacent numbers below,
we can arrive at a sum of the values along our route. For example, traversing
from 4->7->5->3 results in a sum of 4+7+5+3 = 19. The maximum sum achievable
from top to bottom in this example is 24 (achieved by going from 4->8->3->9).

The provided tri.txt file has a similar triangle of numbers. However, its
triangle has 1,000 rows. Each line in the file, from top to bottom, has the
numbers corresponding to the rows in the triangle, from top to bottom.

Write a command-line utility in Python to find the maximum sum from top to
bottom for the triangle of numbers in a file whose filename (with path if
needed) is given as the one and only command-line argument.

Then run that utility for tri.txt and provide the answer for that file.