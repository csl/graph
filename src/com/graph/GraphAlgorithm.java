package com.graph;

import java.util.*;

//Graph for Algorithm
public class GraphAlgorithm
{
	int N; //number of vertices in the graph
	boolean[][] G; // the graph as an adjacency matrix
					// G[i][j] is true if there is an edge from i to j

	//��Ƶ��c for tree
	TreeMap<String,Integer> node;
	ArrayList<String> nodelist;

	ArrayList<String> DFSlist;
	ArrayList<String> BFSlist;
	
	GraphAlgorithm(int NodeNum) throws Exception
	{
		//��l�Ƹ�Ƶ��c
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
	
	//���Ѥ�����Main�ӳ]���X��Node
	void SetNode(String Node, int NodeNum)
	{
		nodelist.add(Node);
		node.put(Node, NodeNum);			
	}
	
	//���Ѥ�����Main�ӳ]Edge
	void SetEdge(int startNode, int EndNode)
	{
		G[startNode][EndNode] = true;
		G[EndNode][startNode] = true;	
	}
	
	//�^�ǵ��G��Main
	ArrayList<String> getDFSArrayList()
	{
		return DFSlist;
	}

	//�^�ǵ��G��Main
	ArrayList<String> getBFSArrayList()
	{
		return BFSlist;
	}

	//��@DFS��k, �z�L�ۤvCALL�ۤv�覡����|
	int DFS(int at, boolean[] rec_path, int endnode)
	{
		DFSlist.add(nodelist.get(at));
		
		//�аO���L�F�F
		rec_path[at]=true;
		
		for (int i=0; i<N; ++i)
		{
			//Path�O�������Lrecord rec_path array
			if (G[at][i] && !rec_path[i])
			{
				if (DFS(i,rec_path,endnode) == 1) return 1;
			}
		}
		
		//���F
		if (nodelist.get(at).equals(Integer.toString(endnode)))
		{
			return 1;
		}
		else
		{
			//�S���Nremove�o����
			DFSlist.remove(DFSlist.size()-1);
		}
		return 0;
	}
	
	//��@BFS��k
	void BFS(int start, boolean[] rec_path, int endnode)
	{
		// �� queue�ӳB�znodes
		Queue<Integer> Q=new LinkedList<Integer>();
		
		Q.offer(start);
		rec_path[start]=true;
				
		while (!Q.isEmpty())
		{
			int at=Q.poll(); // get the head of the queue
			
			for (int i=0; i<N; ++i)
			{
				//Path�O�������Lrecord rec_path array
				if (G[at][i] && !rec_path[i])
				{
					Q.offer(i);
					//�Ш��L�F
					rec_path[i]=true;
				}
			}
			
			//�g�L���I�O�U��
			BFSlist.add(nodelist.get(at));
			if (nodelist.get(at).equals(Integer.toString(endnode)))
			{
				return;
			}
		}
	}

}
