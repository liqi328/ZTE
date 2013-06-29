package cn.com.zte;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.GraphReader;
import cn.com.zte.graph.Path;
import cn.com.zte.searcher.AbstractPathSearcher;
import cn.com.zte.searcher.BfsPathSearcher;
import cn.com.zte.searcher.DfsPathSearcher;
import cn.com.zte.searcher.NoCommonEdgeSearchStrategy;
import cn.com.zte.searcher.NoCommonNodeSearchStrategy;
import cn.com.zte.searcher.AbstractSearchStrategy;

public class TestZTE {
	public static void main(String[] args){
		testSmall();
		testBig();
	}
	
	private static void testSmall(){
		System.out.println("------------------ test Small data ------------------------");
		//String graphFilename = "E:/MyCode/ZTE/inputdata/graph.txt";
		String graphFilename = "E:/MyCode/ZTE/inputdata/test.txt";
		Graph g = GraphReader.read(graphFilename);
		String source = "12";
		String dest = "10";
		
		//run_dfs(source, dest, g);
		run_bfs(source, dest, g);
	}
	
	private static void testBig(){
		System.out.println("------------------ test Big data ------------------------");
		String graphFilename = "E:/2013¼²²¡ÑÐ¾¿/À¼Î°/tissue_specific_PPI.txt";
		
		Graph g = GraphReader.read(graphFilename);
		String source = "1967";
		String dest = "5797";
		
		//run_dfs(source, dest, g);
		run_bfs(source, dest, g);
	}
	
	private static void run_dfs(String source, String dest, Graph g){
		System.out.println("Nodes: " + g.getNodeNum() + ", Edges: " + g.getEdgeNum());
		AbstractPathSearcher searcher = new DfsPathSearcher();
		
		Path<String> path = searcher.findBackupPath(source, dest, g);
		
		System.out.println(path);
	}
	
	private static void run_bfs(String source, String dest, Graph g){
		System.out.println("Nodes: " + g.getNodeNum() + ", Edges: " + g.getEdgeNum());
		AbstractPathSearcher searcher = new BfsPathSearcher();
		AbstractSearchStrategy searchStrategy = new NoCommonNodeSearchStrategy();
		searcher.setSearchStrategy(searchStrategy);
		
		Path<String> masterPath = searcher.findMasterPath(source, dest, g);
		Path<String> backupPath = searcher.findBackupPath(source, dest, g);
		
		System.out.println("main: " + masterPath);
		System.out.println("backup: " + backupPath);
		
		searchStrategy = new NoCommonEdgeSearchStrategy();
		searcher.setSearchStrategy(searchStrategy);
		backupPath = searcher.findBackupPath(source, dest, g);
		System.out.println("backup: " + backupPath);
	}
}
