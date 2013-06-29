package cn.com.zte.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {
	
	public static Graph read(String filename){
		Graph g = new Graph();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
			String line = in.readLine();
			String[] cols = null;
			while((line = in.readLine()) != null){
				cols = line.split("\t| |,");
				g.addEdge(cols[0], cols[1]);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
}
