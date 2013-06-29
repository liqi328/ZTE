package cn.com.zte.searcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * ���ڹ������������·�������㷨
 * @author Liqi
 *
 */
public class BfsPathSearcher extends AbstractPathSearcher {
	
	/**
	 * ��������·��
	 * @param source	Դ��
	 * @param dest		�յ�
	 * @param g			ͼ
	 * @return
	 */
	@Override
	public Path<String> findBackupPath(String source, String dest, Graph g) {
		if(masterPath == null || searchStrategy == null)return null;
		return searchStrategy.search(source, dest, g, masterPath);
	}
	
	/**
	 * ������·��(�����·��)<br />
	 * ���ڹ��������������·�������㷨<br />
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
