package cn.com.zte.searcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;


public class DfsPathSearcher extends AbstractPathSearcher {
	private List<Path<String>> pathes = new ArrayList<Path<String>>();
	private Path<String> stack = new Path<String>();
	private int shortestPathLength = 0;
	
	/**
	 * 在一个网络拓扑中（可以支持数千个点的规模），边是双向的，两点之间最多有一条边，
	 * 所有边的距离相等（也就是权重为1），给出源和目的两个点，需要找出满足条件的路径。
	 * 1：找出源和目的之间的一条主用路径。
	 * 2：找出源和目的之间的一条备用路径。 备用路径和主用路径至少有一个点或边不相同。
	 * 	关于备用路径可能满足下列约束：
	 * 		1）和主用路径没有相同的中间节点。
	 * 		2）和主用路径没有相同的边。
	 * 
	 * @param source	源点
	 * @param dest		终点
	 * @param g			图
	 * @return
	 */
	public Path<String> findBackupPath(String source, String dest, Graph g){
		shortestPathLength = this.calculateShortPathLength(source, dest, g);
		System.out.println("Shortest Path length = " + shortestPathLength);
		
		findPath_0(source, source, dest, g);
		return pathes.get(0);
	}
	
	/**
	 * 计算图中从 源点 到 终点 的最短路径的长度(边的数目)
	 * @param source	源点
	 * @param dest		终点
	 * @param g			图
	 * @return
	 */
	private int calculateShortPathLength(String source, String dest, Graph g){
		Map<String, Integer> visited = new HashMap<String, Integer>();
		Queue<String> queue = new LinkedList<String>();
		
		queue.offer(source);
		visited.put(source, 0);
		
		while(!queue.isEmpty()){
			String cur = queue.poll();
			if(cur.equals(dest)){
				return visited.get(cur);
			}else{
				Iterator<String> itr = g.getNeighborsIterator(cur);
				while(itr.hasNext()){
					String adjNode = itr.next();
					if(!visited.containsKey(adjNode)){
						visited.put(adjNode, visited.get(cur) + 1);
						queue.offer(adjNode);
					}
				}
			}
		}
		return 0;
	}
	
	private boolean findPath_0(String cur, String start, String end, Graph g){
		if(cur.equals(end)){
			stack.add(cur);
			if(pathes.size() < 2){
				pathes.add((Path<String>)stack.clone());
			}
			stack.remove();
			if(pathes.size() < 2){
				return false;
			}
			return true;
		}
		if(stack.contains(cur)){
			return false;
		}
		stack.add(cur);
		Iterator<String> itr = g.getNeighborsIterator(cur);
		while(itr.hasNext()){
			String adjNode = itr.next();
			if(!stack.contains(adjNode)){
				if(findPath_0(adjNode, start, end, g)){
					return true;
				};
			}
		}
		stack.remove();
		return false;
	}

	@Override
	public Path<String> findMasterPath(String source, String dest, Graph g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSearchStrategy(AbstractSearchStrategy searchStrategy) {
		// TODO Auto-generated method stub
		
	}
}
