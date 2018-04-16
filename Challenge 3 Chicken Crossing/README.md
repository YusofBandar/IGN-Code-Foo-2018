# Finding Paths
I found all the valid path by using Depth First Search and recursion, meaning it runs in Θ(|V| + |E|). I also used dynamic programming to improve the efficiency of the algorithm.

##### Algorithm
The algorithm worked by checking if the chicken could go down up or right, for each cell, once it reached a valid path that path was stored to be remembered later.

![Image](https://github.com/YusofBandar/IGN-Code-Foo-2018/blob/master/Challenge%202%20Geomags/More%20Detail/Images/Algorithm.PNG)

****

##### Dynamic Programming 
If i reached a cell that that had been previously stored, I knew there was valid path. To improve efficiency there was no need to compute that path as I have already stored it.

![Image](https://github.com/YusofBandar/IGN-Code-Foo-2018/blob/master/Challenge%202%20Geomags/More%20Detail/Images/Dynamic-Programming.PNG)

****

##### Larger Grids
The algorithm would work for larger grids especially using dynamic programming would decrease computation time, the main issue for larger grids in memory usage. 

My solution uses recursion meaning that for each call the memory stack increase, larger grids will have exponentially larger number of paths this could easily lead to stack overflow.




























