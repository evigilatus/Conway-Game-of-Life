# Conway Game of Life Simulator

### About

This project is a simple Spring Boot application that provides a REST endpoint for a simulation of
the [Conway Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) over N generations.

The REST API receives a number of generations for which to perform the simulation and a starting state of the grid. The
output is the number of generations passed and the end state of the grid.

### Prerequisites

Java 11, Spring Boot

### Proposed solution

The current solution is basically the simplest possible in terms of implementation, however its complexity is ***O(x*y)** and requires two separate 2D arrays. It will not perform well with very large input grids.

All the transformations occur only around the live cells (1s) so if we have a sparse matrix, we can only focus on the 1s
and their neighbours instead of the whole matrix. Even if we start with a matrix with mostly 1s, over 1-2 generations
most of the 1s will be transformed to 0s (wil die by overpopulation). So we don't need to traverse the whole 2D matrix
on each generation and could focus only on the areas where the 1s and their neighbours are located. However, we still
need to preserve their coordinates in the initial 2D array.

A possible better option would be to use a *Trie* data structure, e.g.:

1. Only on the first generation, iterate through the whole 2D array to find the 1s. *O(x*y)
2. Save all 1s and their neighbours into a Trie.
3. On each subsequent generation, traverse only the Trie structure (which doesn't have all the 0s)
   to find if a cell should be transformed and get its coordinates. *O(k)* where *'k'* is the number of 1s.
4. Flip the value of the identified cells that should be transformed based on their coordinates. If we can get the
   initial coordinates from the Trie, finding the elements in the array will be *O(1)*.
5. Return the modified array.

**Pros**:

1. The Trie is more compact than the 2d array and for sparse matrices the complexity would be lower.
2. No need for two separate 2d arrays

**Cons:**

1. More complex solution, harder to read.
2. Does not guarantee better performance in the first generations because the whole grid could consist of mostly live
   cells.
3. No standard implementation of a Trie in Java (only org.apache.commons.collections4.trie.PatriciaTrie)