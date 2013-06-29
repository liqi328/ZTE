package cn.com.zte.searcher;

import java.util.Set;

import cn.com.zte.graph.Path;

/**
 * ����·���������ԣ�������·��û����ͬ���м�ڵ�
 * 
 * @author Liqi
 *
 */
public class NoCommonNodeSearchStrategy extends AbstractSearchStrategy {
	/**
	 * �жϵ�ǰ���curNode�Ƿ���Է���<br />
	 * �ж�����1) curNode δ������<br /> 2) ��Դ����յ���,curNodeδ������masterPath��
	 * 
	 * @param visited		�ѷ��ʵĽ�㼯��
	 * @param masterPath	��·��
	 * @param curNode		��ǰ���
	 * @param preNode		��ǰ����ǰһ�������ʵĽڵ�
	 * @param dest			�յ�
	 * @return
	 */
	@Override
	protected boolean isCanVisited(Set<String> visited, Path<String> masterPath,
			String curNode, Node<String> preNode, String dest) {
		return !visited.contains(curNode) && (curNode.equals(dest) || !masterPath.contains(curNode));
	}

}
