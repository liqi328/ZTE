ZTE
===

中兴捧月杯 程序设计大赛

一：题目
在一个网络拓扑中（可以支持数千个点的规模），边是双向的，两点之间最多有一条边，所有边的距离相等（也就是权重为1），给出源和目的两个点，需要找出满足条件的路径。

1。找出源和目的之间的一条主用路径。

2。找出源和目的之间的一条备用路径。 备用路径和主用路径至少有一个点或边不相同。

 关于备用路径可能满足下列约束：
 
 1）和主用路径没有相同的中间节点。
 
 2）和主用路径没有相同的边。
 
二：算法说明

1.	找出源和目的之间的一条主用路径：

   主用路径可以理解为源点和目的的之间的最短路径，因此，将此问题转化为求两点之间的最短路径问题。由于输入的图是无向图，权值为1，因此可用广度优先搜索算法(BFS)，找出两点之间的最短路径。
   
   经典的广度优先搜索算法中,需要一个队列。
   
        从队列中取出一个节点V： 
        
        1）：若V等于目的节点，则找到一条主用路径，输出路径，结束。
        
        2）：遍历V的每个邻居节点U，若U还未访问，则将U置入队列中。


2.	找出源和目的之间的一条备用路径：

   a）	和主用路径没有相同的中间节点。

        算法思想：广度优先搜索算法(BFS)
        
        从队列中取出一个节点V：
        
        1）：若V等于目的节点，则找到一条备用路径，输出路径，结束。
        
        2）：遍历V的每个邻居节点U，若U还未访问；U不是源点，也不是终点；U未出现在主用路径中，则将U置入队列中。

   b）	和主用路径没有相同的边：

        算法思想：广度优先搜索算法(BFS)
        
        从队列中取出一个节点V：
        
        1）：若V等于目的节点，则找到一条备用路径，输出路径，结束。
        
        2）：遍历V的每个邻居节点U，若U还未访问；边VU未出现在主用路径中，则将U置入队列中。


