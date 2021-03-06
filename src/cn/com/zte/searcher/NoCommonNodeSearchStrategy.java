package cn.com.zte.searcher;

import java.util.Set;

import cn.com.zte.graph.Path;

/**
 * 备用路径搜索策略：和主用路径没有相同的中间节点
 * 
 * @author Liqi
 *
 */
public class NoCommonNodeSearchStrategy extends AbstractSearchStrategy {
	/**
	 * 判断当前结点curNode是否可以访问<br />
	 * 判定规则：1) curNode 未被访问<br /> 2) 除源点和终点外,curNode未出现在masterPath中
	 * 
	 * @param visited		已访问的结点集合
	 * @param masterPath	主路径
	 * @param curNode		当前结点
	 * @param preNode		当前结点的前一个被访问的节点
	 * @param dest			终点
	 * @return
	 */
	@Override
	protected boolean isCanVisited(Set<String> visited, Path<String> masterPath,
			String curNode, Node<String> preNode, String dest) {
		return !visited.contains(curNode) && (curNode.equals(dest) || !masterPath.contains(curNode));
	}

}
