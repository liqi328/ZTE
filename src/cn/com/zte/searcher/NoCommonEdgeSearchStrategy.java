package cn.com.zte.searcher;

import java.util.Set;

import cn.com.zte.graph.Path;

/**
 * ����·���������ԣ�������·��û����ͬ�ı�
 * 
 * @author Liqi
 *
 */
public class NoCommonEdgeSearchStrategy extends AbstractSearchStrategy {
	/**
	 * �жϵ�ǰ���curNode�Ƿ���Է���<br />
	 * �ж�����1) curNode δ������<br /> 2) curNode��preNode���ɵı�, δ������masterPath��,
	 * ��curNode �� preNode û��ͬʱ������masterPath��
	 * 
	 * @param visited		�ѷ��ʵĽ�㼯��
	 * @param masterPath	��·��
	 * @param curNode		��ǰ���
	 * @param preNode		��ǰ����ǰһ�������ʵĽڵ�
	 * @param dest			�յ�
	 * @return
	 */
	@Override
	protected boolean isCanVisited(Set<String> visited,
			Path<String> masterPath, String curNode, Node<String> preNode, String dest) {
		return !visited.contains(curNode) && !(masterPath.contains(preNode.name) && masterPath.contains(curNode));
	}

}
