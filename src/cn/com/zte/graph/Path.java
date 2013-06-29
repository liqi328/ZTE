package cn.com.zte.graph;

import java.util.ArrayList;
import java.util.List;

public class Path<E>{
	private List<E> path = new ArrayList<E>();
	
	public void add(E node){
		path.add(node);
	}
	
	public void remove(){
		path.remove(path.size() - 1);
	}
	
	/**
	 * 求路径的长度(边的数目 = 结点数目 - 1)
	 * @return 
	 */
	public int size(){
		return path.size() - 1;
	}
	
	public boolean contains(E node){
		return path.contains(node);
	}
	
	public void reverse(){
		List<E> pathTmp = new ArrayList<E>();
		for(int i = path.size() - 1; i >= 0; --i){
			pathTmp.add(path.get(i));
		}
		this.path = pathTmp;
	}
	
	public Path<E> clone(){
		Path<E> newPath = new Path<E>();
		for(E e : path){
			newPath.add(e);
		}
		return newPath;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < path.size(); ++i){
			sb.append(path.get(i));
			if(i != path.size() - 1){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
}