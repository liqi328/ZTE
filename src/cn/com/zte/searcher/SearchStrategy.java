package cn.com.zte.searcher;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * ����·���������ԣ�<br />
 * 	1��������·��û����ͬ���м�ڵ�, NoCommonNodeSearchStrategy.class<br /> 
 *  2��������·��û����ͬ�ı�, NoCommonEdgeSearchStrategy
 *  
 * @author Liqi
 *
 */
public interface SearchStrategy {

	/**
	 * ����Դ�㵽�յ�ı���·��
	 * 
	 * @param source		Դ��
	 * @param dest			�յ�
	 * @param g				ͼ
	 * @param masterPath	��·��
	 * @return
	 */
	public Path<String> search(String source, String dest, Graph g, Path<String> masterPath);
}
