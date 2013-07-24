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
	 * ��һ�����������У�����֧����ǧ����Ĺ�ģ��������˫��ģ�����֮�������һ���ߣ�
	 * ���бߵľ�����ȣ�Ҳ����Ȩ��Ϊ1��������Դ��Ŀ�������㣬��Ҫ�ҳ�����������·����
	 * 1���ҳ�Դ��Ŀ��֮���һ������·����
	 * 2���ҳ�Դ��Ŀ��֮���һ������·���� ����·��������·��������һ�����߲���ͬ��
	 * 	���ڱ���·��������������Լ����
	 * 		1��������·��û����ͬ���м�ڵ㡣
	 * 		2��������·��û����ͬ�ıߡ�
	 * 
	 * @param source	Դ��
	 * @param dest		�յ�
	 * @param g			ͼ
	 * @return
	 */
	public Path<String> findBackupPath(String source, String dest, Graph g){
		shortestPathLength = this.calculateShortPathLength(source, dest, g);
		System.out.println("Shortest Path length = " + shortestPathLength);
		
		findPath_0(source, source, dest, g);
		return pathes.get(0);
	}
	
	/**
	 * ����ͼ�д� Դ�� �� �յ� �����·���ĳ���(�ߵ���Ŀ)
	 * @param source	Դ��
	 * @param dest		�յ�
	 * @param g			ͼ
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
