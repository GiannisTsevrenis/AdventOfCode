# Part 1

### Intuition: the antinode created from $A$ to $B$ is: $B$ + $\vec{AB}$ (displacement vector).

Explanation: if for example we have point $A = (0,0)$ and point $B = (5,5)$ we can easily see that the antinodes will be at $(10,10)$ and $(-5,-5)$.

### Calculate the **displacement vectors** $\vec{AB}$ and $\vec{BA}$:

$$
\vec{AB} = \left( x_2 - x_1, y_2 - y_1 \right)
$$

$$
(\vec{BA} = -\vec{ΑΒ})
$$

### Find the antinodes:

$$
Antinode(A,B) = B + \vec{ΑΒ} = (x_2 + y_2) + ( x_2 - x_1, y_2 - y_1) = (2x_2 -x_1, 2y_2 -y_1).
$$

Similarly, $Antinode(B,A) = (2x_1 -x_2, 2y_1 -y_2)$.

and add them to a map if they are within bounds.

The rest is straighforward: Iterate the matrix, if we see a new char, add it to a map and save its coordinates.
If the char already exists in the map, it means we have seen it before one or more times.
So we loop over the already encoutered points who have the same symbol and create the antinodes with our current position.

# Part 2

Things are pretty much the same, we just have to tweak one function: instead of returning just 2 antinodes, we have to check every point in the line of those two points.
<br> <br>
Practically when we have a new char which is already in the map, we start from point $(B+\vec{AB})$ just apply $+\vec{AB}$ repeatedly until we get out of bounds. Then do the same thing from point B to A. Start from $(A+\vec{BA})$ just apply $+\vec{BA}$ until we are no longer within bounds. Don't forget to also include the points A and B in the result.
