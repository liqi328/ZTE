package cn.com.zte.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Graph{
	private Map<String, Set<String>> edges = new HashMap<String, Set<String>>();
	
	public void addEdge(String from, String to){
		if(edges.get(from) == null){
			edges.put(from, new HashSet<String>());
		}
		if(edges.get(to) == null){
			edges.put(to, new HashSet<String>());
		}
		edges.get(from).add(to);
		edges.get(to).add(from);
	}
	
	public Iterator<String> getNeighborsIterator(String node){
		if(edges.get(node) == null)return new HashSet<String>().iterator();
		return edges.get(node).iterator();
	}
	
	public int getNodeNum(){
		return edges.keySet().size();
	}
	public int getEdgeNum(){
		int num = 0;
		for(Set<String> s : edges.values()){
			num += s.size();
		}
		return num >> 1;
	}
}
