package cn.com.zte.searcher;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;


/**
 * 在一个网络拓扑中（可以支持数千个点的规模），边是双向的，两点之间最多有一条边，
 * 所有边的距离相等（也就是权重为1），给出源和目的两个点，需要找出满足条件的路径。
 * 1：找出源和目的之间的一条主用路径。
 * 2：找出源和目的之间的一条备用路径。 备用路径和主用路径至少有一个点或边不相同。
 * 	关于备用路径可能满足下列约束：
 * 		1）和主用路径没有相同的中间节点。
 * 		2）和主用路径没有相同的边。
 * 
 * @author Liqi
 *
 */
public abstract class AbstractPathSearcher {
	protected Path<String> masterPath = null;
	protected AbstractSearchStrategy searchStrategy = null;
	
	public void setSearchStrategy(AbstractSearchStrategy searchStrategy){
		this.searchStrategy = searchStrategy;
	} 
	
	/**
	 * 搜索备用路径
	 * @param source	源点
	 * @param dest		终点
	 * @param g			图
	 * @return
	 */
	public abstract Path<String> findBackupPath(String source, String dest, Graph g);
	
	/**
	 * 搜索主路径(即最短路径)
	 * @param source	源点
	 * @param dest		终点
	 * @param g			图
	 * @return
	 */
	public abstract Path<String> findMasterPath(String source, String dest, Graph g);
}

class Node<E> {
    public Node<E> parent;
	public int level;
    public E name;
    
    public Node(Node<E> parent, E value, int level){  
        this.parent = parent;  
        this.name = value;
        this.level = level;
    } 
    
    public static Path<String> generatePath(Node<String> n){
        Path<String> path = new Path<String>();
        Node<String> p = n;
        while(p != null){
            path.add(p.name);
            p = p.parent; 
        }
        return path;
    }
}
