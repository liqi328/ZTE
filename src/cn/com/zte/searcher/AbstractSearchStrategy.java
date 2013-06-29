package cn.com.zte.searcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * 备用路径搜索策略：<br />
 * 	1：和主用路径没有相同的中间节点, NoCommonNodeSearchStrategy.class<br /> 
 *  2：和主用路径没有相同的边, NoCommonEdgeSearchStrategy<br />
 *  <br />
 *  抽象策略类：AbstractSearchStrategy.class<br />
 *  具体策略类：NoCommonNodeSearchStrategy.class 和 NoCommonEdgeSearchStrategy.class<br />
 * @author Liqi
 *
 */
public abstract class AbstractSearchStrategy {

	/**
	 * 搜索源点到终点的备用路径<br />
	 * 基于广度优先搜索的备用路径搜索算法<br />
	 * <br />
	 * 采用模板方法设计模式
	 * 
	 * @param source		源点
	 * @param dest			终点
	 * @param g				图
	 * @param masterPath	主路径
	 * @return
	 */
	public final Path<String> search(String source, String dest, Graph g, Path<String> masterPath){
		Queue<Node<String>> queue = new LinkedList<Node<String>>();
		queue.offer(new Node<String>(null, source, 0));
		
		Set<String> visited = new HashSet<String>();
		Path<String> backupPath = null;
		
		while(!queue.isEmpty()){
			Node<String> cur = queue.poll();
			if(cur.name.equals(dest)){
				backupPath = Node.generatePath(cur);
				backupPath.reverse();
				break;
			}else{
				Iterator<String> itr = g.getNeighborsIterator(cur.name);
				while(itr.hasNext()){
					String adjNode = itr.next();
					if(isCanVisited(visited, masterPath, adjNode, cur, dest)){
						visited.add(adjNode);
						queue.offer(new Node<String>(cur, adjNode, cur.level + 1));
					}
				}
			}
		}
		return backupPath;
	}
	
	/**
	 * 判断当前结点curNode是否可以访问
	 * 
	 * @param visited		已访问的结点集合
	 * @param masterPath	主路径
	 * @param curNode		当前结点
	 * @param preNode		当前结点的前一个被访问的节点
	 * @param dest			终点
	 * @return
	 */
	protected abstract boolean isCanVisited(Set<String> visited, Path<String> masterPath,
			String curNode, Node<String> preNode, String dest);
}
