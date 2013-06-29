package cn.com.zte.searcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * ����·���������ԣ�<br />
 * 	1��������·��û����ͬ���м�ڵ�, NoCommonNodeSearchStrategy.class<br /> 
 *  2��������·��û����ͬ�ı�, NoCommonEdgeSearchStrategy<br />
 *  <br />
 *  ��������ࣺAbstractSearchStrategy.class<br />
 *  ��������ࣺNoCommonNodeSearchStrategy.class �� NoCommonEdgeSearchStrategy.class<br />
 * @author Liqi
 *
 */
public abstract class AbstractSearchStrategy {

	/**
	 * ����Դ�㵽�յ�ı���·��<br />
	 * ���ڹ�����������ı���·�������㷨<br />
	 * <br />
	 * ����ģ�巽�����ģʽ
	 * 
	 * @param source		Դ��
	 * @param dest			�յ�
	 * @param g				ͼ
	 * @param masterPath	��·��
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
	 * �жϵ�ǰ���curNode�Ƿ���Է���
	 * 
	 * @param visited		�ѷ��ʵĽ�㼯��
	 * @param masterPath	��·��
	 * @param curNode		��ǰ���
	 * @param preNode		��ǰ����ǰһ�������ʵĽڵ�
	 * @param dest			�յ�
	 * @return
	 */
	protected abstract boolean isCanVisited(Set<String> visited, Path<String> masterPath,
			String curNode, Node<String> preNode, String dest);
}
