package cn.com.zte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.com.zte.graph.Graph;
import cn.com.zte.graph.GraphReader;
import cn.com.zte.graph.Path;
import cn.com.zte.searcher.AbstractPathSearcher;
import cn.com.zte.searcher.AbstractSearchStrategy;
import cn.com.zte.searcher.BfsPathSearcher;
import cn.com.zte.searcher.NoCommonEdgeSearchStrategy;
import cn.com.zte.searcher.NoCommonNodeSearchStrategy;

public class Main {
	/**
	 * 程序支持命令行参数:<br />
	 * /f后表示拓扑图文件<br />
	 * /s 表示源节点<br />
	 * /d 表示目的节点<br />
	 * /c 表示条件（取值1-2，满足上面两个约束条件之一）<br />
	 * /o 表示输出结果文件<br />
	 * 如 java Main  /ftopolink_example01.txt  /s20  /d32  /c2 /otopolink_result01.txt <br />
	 * 表示根据拓扑图文件topolink_example01.txt，计算节点20和节点32之间的主用和备用路径，<br />
	 * 备用路径要求满足约束条件2。<br />
	 * 输出结果文件topolink_result01.txt内容可能的为:<br />
	 * main:  20, 21 ,22, 23, 24, 32<br />
	 * backup: 20,29,37, 46,47,48, 40,32<br />
	 * */
	public static void main(String[] args){
		InputArgument arg = new InputArgument(args);
		run(arg);
	}
	
	private static void run(InputArgument arg){
		Graph g = GraphReader.read(arg.graphFilename);
		
		AbstractPathSearcher searcher = new BfsPathSearcher();
		
		AbstractSearchStrategy[] searchStrategy = new AbstractSearchStrategy[2];
		searchStrategy[0] = new NoCommonNodeSearchStrategy();
		searchStrategy[1] = new NoCommonEdgeSearchStrategy();
		searcher.setSearchStrategy(searchStrategy[arg.strategy - 1]);

		Path<String> masterPath = searcher.findMasterPath(arg.source, arg.dest, g);
		Path<String> backupPath = searcher.findBackupPath(arg.source, arg.dest, g);
		
		StringBuffer sb = new StringBuffer();
		sb.append("main: ").append(masterPath.toString()).append("\r\n");
		sb.append("backup: ").append(backupPath.toString()).append("\r\n");
		
		write(arg.outputFilename, sb.toString());
		
		System.out.println(sb.toString());
	}
	
	private static void write(String filename, String content){
		File file = new File(filename);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static class InputArgument{
		public String graphFilename;	// /f 后表示拓扑图文件
		public String source;			// /s 表示源节点
		public String dest;				// /d 表示目的节点
		public int strategy;			// /c 表示条件（取值1-2，满足上面两个约束条件之一）
		public String outputFilename;	// /o 表示输出结果文件
		
		public InputArgument(String[] args){
			parseArgument(args);
		}
		
		private void parseArgument(String[] args){
			if(args.length != 5){
				System.out.println("Invalid arguments. " + args[0]+", " +args.length);
			}
			
			for(int i = 0; i < args.length; ++i){
				parseOneArgument(args[i]);
			}
		}
		
		private void parseOneArgument(String arg){
			if(arg.startsWith("/f")){
				this.graphFilename = arg.substring(2);
			}else if(arg.startsWith("/s")){
				this.source = arg.substring(2);
			}else if(arg.startsWith("/d")){
				this.dest = arg.substring(2);
			}else if(arg.startsWith("/c")){
				this.strategy = Integer.parseInt(arg.substring(2));
			}else if(arg.startsWith("/o")){
				this.outputFilename = arg.substring(2);
			}
		}
	}
}
