package cn.com.zte.searcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * 基于广度优先搜索的路径搜索算法
 * @author Liqi
 *
 */
public class BfsPathSearcher extends AbstractPathSearcher {
	
	/**
	 * 搜索备用路径
	 * @param source	源点
	 * @param dest		终点
	 * @param g			图
	 * @return
	 */
	@Override
	public Path<String> findBackupPath(String source, String dest, Graph g) {
		if(masterPath == null || searchStrategy == null)return null;
		return searchStrategy.search(source, dest, g, masterPath);
	}
	
	/**
	 * 搜索主路径(即最短路径)<br />
	 * 基于广度优先搜索的主路径搜索算法<br />
	 * @param source
	 * @param dest
	 * @param g
	 * @return
	 */
	public Path<String> findMasterPath(String source, String dest, Graph g){
		Queue<Node<String>> queue = new LinkedList<Node<String>>();
		queue.offer(new Node<String>(null, source, 0));
		
		Set<String> visited = new HashSet<String>();
		
		while(!queue.isEmpty()){
			Node<String> cur = queue.poll();
			if(cur.name.equals(dest)){
				this.masterPath = Node.generatePath(cur);
				this.masterPath.reverse();
				break;
			}else{
				Iterator<String> itr = g.getNeighborsIterator(cur.name);
				while(itr.hasNext()){
					String adjNode = itr.next();
					if(!visited.contains(adjNode)){
						visited.add(adjNode);
						queue.offer(new Node<String>(cur, adjNode, cur.level + 1));
					}
				}
			}
		}
		return masterPath;
	}
}
