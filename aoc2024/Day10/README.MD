# Part 1

### Intuition: need to path(hike) from a point to another or find all paths or find if a trail is complete e.t.c = DFS/BFS

I used Depth First Search, but either one should be fine.

# Part 2

The second part of the puzzle requires minimum changes. Just move
<br>`visited[i][j] = true;`
after termination condition. This way we allow other paths to reach the same '9' while starting with the same '0' .
Also, before normal case return make it make `visited[i][j] = false;` again to enable re-visiting this node. And that's it!<br>
Essentially what we did was to mark it as visited until we finish exploring all neighbors, so we don't get stuck in loops.
Then mark it false again to enable exploration from other paths.
