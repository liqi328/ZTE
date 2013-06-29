package cn.com.zte.searcher;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.Path;


/**
 * ��һ�����������У�����֧����ǧ����Ĺ�ģ��������˫��ģ�����֮�������һ���ߣ�
 * ���бߵľ�����ȣ�Ҳ����Ȩ��Ϊ1��������Դ��Ŀ�������㣬��Ҫ�ҳ�����������·����
 * 1���ҳ�Դ��Ŀ��֮���һ������·����
 * 2���ҳ�Դ��Ŀ��֮���һ������·���� ����·��������·��������һ�����߲���ͬ��
 * 	���ڱ���·��������������Լ����
 * 		1��������·��û����ͬ���м�ڵ㡣
 * 		2��������·��û����ͬ�ıߡ�
 * 
 * @author Liqi
 *
 */
public abstract class AbstractPathSearcher {
	protected Path<String> masterPath = null;
	protected AbstractSearchStrategy searchStrategy = null;
	
	public void setSearchStrategy(AbstractSearchStrategy searchStrategy){
		this.searchStrategy = searchStrategy;
	} 
	
	/**
	 * ��������·��
	 * @param source	Դ��
	 * @param dest		�յ�
	 * @param g			ͼ
	 * @return
	 */
	public abstract Path<String> findBackupPath(String source, String dest, Graph g);
	
	/**
	 * ������·��(�����·��)
	 * @param source	Դ��
	 * @param dest		�յ�
	 * @param g			ͼ
	 * @return
	 */
	public abstract Path<String> findMasterPath(String source, String dest, Graph g);
}

class Node<E> {
    public Node<E> parent;
	public int level;
    public E name;
    
    public Node(Node<E> parent, E value, int level){  
        this.parent = parent;  
        this.name = value;
        this.level = level;
    } 
    
    public static Path<String> generatePath(Node<String> n){
        Path<String> path = new Path<String>();
        Node<String> p = n;
        while(p != null){
            path.add(p.name);
            p = p.parent; 
        }
        return path;
    }
}
