package com.graph;

import java.util.*;

//Graph for Algorithm
public class GraphAlgorithm
{
	int N; //number of vertices in the graph
	boolean[][] G; // the graph as an adjacency matrix
					// G[i][j] is true if there is an edge from i to j

	//資料結構 for tree
	TreeMap<String,Integer> node;
	ArrayList<String> nodelist;

	ArrayList<String> DFSlist;
	ArrayList<String> BFSlist;
	
	GraphAlgorithm(int NodeNum) throws Exception
	{
		//初始化資料結構
		N = NodeNum+1;
		G = new boolean[N][N];
		nodelist = new ArrayList<String>();
		node = new TreeMap<String,Integer>();

		DFSlist = new ArrayList<String>();
		BFSlist = new ArrayList<String>();
		
		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)
			{
				G[i][j] = false;
			}
		}
	}
	
	//提供介面讓Main來設有幾個Node
	void SetNode(String Node, int NodeNum)
	{
		nodelist.add(Node);
		node.put(Node, NodeNum);			
	}
	
	//提供介面讓Main來設Edge
	void SetEdge(int startNode, int EndNode)
	{
		G[startNode][EndNode] = true;
		G[EndNode][startNode] = true;	
	}
	
	//回傳結果給Main
	ArrayList<String> getDFSArrayList()
	{
		return DFSlist;
	}

	//回傳結果給Main
	ArrayList<String> getBFSArrayList()
	{
		return BFSlist;
	}

	//實作DFS方法, 透過自己CALL自己方式把路徑
	int DFS(int at, boolean[] rec_path, int endnode)
	{
		DFSlist.add(nodelist.get(at));
		
		//標記走過了了
		rec_path[at]=true;
		
		for (int i=0; i<N; ++i)
		{
			//Path是不有走過record rec_path array
			if (G[at][i] && !rec_path[i])
			{
				if (DFS(i,rec_path,endnode) == 1) return 1;
			}
		}
		
		//找到了
		if (nodelist.get(at).equals(Integer.toString(endnode)))
		{
			return 1;
		}
		else
		{
			//沒找到就remove這條路
			DFSlist.remove(DFSlist.size()-1);
		}
		return 0;
	}
	
	//實作BFS方法
	void BFS(int start, boolean[] rec_path, int endnode)
	{
		// 用 queue來處理nodes
		Queue<Integer> Q=new LinkedList<Integer>();
		
		Q.offer(start);
		rec_path[start]=true;
				
		while (!Q.isEmpty())
		{
			int at=Q.poll(); // get the head of the queue
			
			for (int i=0; i<N; ++i)
			{
				//Path是不有走過record rec_path array
				if (G[at][i] && !rec_path[i])
				{
					Q.offer(i);
					//標走過了
					rec_path[i]=true;
				}
			}
			
			//經過的點記下來
			BFSlist.add(nodelist.get(at));
			if (nodelist.get(at).equals(Integer.toString(endnode)))
			{
				return;
			}
		}
	}

}
