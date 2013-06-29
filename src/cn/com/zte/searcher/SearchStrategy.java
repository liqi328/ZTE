package cn.com.zte.searcher;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;

/**
 * 备用路径搜索策略：<br />
 * 	1：和主用路径没有相同的中间节点, NoCommonNodeSearchStrategy.class<br /> 
 *  2：和主用路径没有相同的边, NoCommonEdgeSearchStrategy
 *  
 * @author Liqi
 *
 */
public interface SearchStrategy {

	/**
	 * 搜索源点到终点的备用路径
	 * 
	 * @param source		源点
	 * @param dest			终点
	 * @param g				图
	 * @param masterPath	主路径
	 * @return
	 */
	public Path<String> search(String source, String dest, Graph g, Path<String> masterPath);
}
