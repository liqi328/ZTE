package cn.com.zte.searcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * 备用路径搜索策略：和主用路径没有相同的中间节点
 * 
 * @author Liqi
 *
 */
public class NoCommonNodeSearchStrategy extends AbstractSearchStrategy {
	/** 
	 * 基于广度优先搜索的备用路径搜索算法
	 */
	@Override
	public Path<String> search(String source, String dest, Graph g, Path<String> masterPath) {
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
					if(!visited.contains(adjNode) && (adjNode.equals(dest) || !masterPath.contains(adjNode))){
						visited.add(adjNode);
						queue.offer(new Node<String>(cur, adjNode, cur.level + 1));
					}
				}
			}
		}
		return backupPath;
	}

}
