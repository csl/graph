package com.graph;

import java.util.*;
import java.io.*;	

public class GraphAlgorithm
{
	int N; // number of vertices in the graph
	boolean[][] G; // the graph as an adjacency matrix
					// G[i][j] is true if there is an edge from i to j

	TreeMap<String,Integer> node;
	ArrayList<String> nodelist;

	ArrayList<String> DFSlist;
	ArrayList<String> BFSlist;
	
	GraphAlgorithm(int NodeNum) throws Exception
	{
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
	
	void SetNode(String Node, int NodeNum)
	{
		nodelist.add(Node);
		node.put(Node, NodeNum);			
	}
	
	void SetEdge(int startNode, int EndNode)
	{
		G[startNode][EndNode] = true;
		G[EndNode][startNode] = true;	
	}
	
	ArrayList<String> getDFSArrayList()
	{
		return DFSlist;
	}

	ArrayList<String> getBFSArrayList()
	{
		return BFSlist;
	}
	
	// perform a DFS on the graph G
	void DFS()
	{
		boolean[] V=new boolean[N]; // a visited array to mark which vertices have been visited while doing the DFS
		
		int numComponets=0; // the number of components in the graph
		
		// do the DFS from each node not already visited
		for (int i=0; i<N; ++i)
			if (!V[i])
			{
				++numComponets;
				System.out.printf("Starting a DFS for component %d starting at node %s%n",numComponets,nodelist.get(i));
				
				DFS(i,V, 0);
			}
		
		System.out.println();
		System.out.printf("Finished with DFS - found %d components.%n", numComponets);
	}
	
	// perform a DFS starting at node at (works recursively)
	int DFS(int at, boolean[] V, int endnode)
	{
		System.out.printf("At node %s in the DFS%n",nodelist.get(at));
		DFSlist.add(nodelist.get(at));
		
		// mark that we are visiting this node
		V[at]=true;
		
		// recursively visit every node connected to this that we have not already visited
		for (int i=0; i<N; ++i)
		{
			if (G[at][i] && !V[i])
			{
				System.out.printf("Going to node %s...",nodelist.get(i));
				if (DFS(i,V,endnode) == 1) return 1;
			}
		}
		
		System.out.printf("Done processing node %s%n", nodelist.get(at));
		if (nodelist.get(at).equals(Integer.toString(endnode)))
		{
			return 1;
		}
		else
		{
			DFSlist.remove(DFSlist.size()-1);
		}
		return 0;
	}
	
	// perform a BFS on the graph G 
	void BFS()
	{
		boolean[] V=new boolean[N]; // a visited array to mark which vertices have been visited while doing the BFS
		
		int numComponets=0; // the number of components in the graph
		
		// do the BFS from each node not already visited
		for (int i=0; i<N; ++i)
			if (!V[i])
			{
				++numComponets;
				System.out.printf("Starting a BFS for component %d starting at node %s%n",numComponets,nodelist.get(i));
				
				BFS(i,V,0);
			}
		
		System.out.println();
		System.out.printf("Finished with BFS - found %d components.%n", numComponets);
	}
	
	// perform a BFS starting at node start
	void BFS(int start, boolean[] V, int endnode)
	{
		Queue<Integer> Q=new LinkedList<Integer>(); // A queue to process nodes
		
		// start with only the start node in the queue and mark it as visited
		Q.offer(start);
		V[start]=true;
				
		// continue searching the graph while still nodes in the queue
		while (!Q.isEmpty())
		{
			int at=Q.poll(); // get the head of the queue
			System.out.printf("At node %s in the BFS%n",nodelist.get(at));
			
			// add any unseen nodes to the queue to process, then mark them as visited so they don't get re-added
			for (int i=0; i<N; ++i)
				if (G[at][i] && !V[i])
				{
					Q.offer(i);
					V[i]=true;
					
					System.out.printf("Adding node %s to the queue in the BFS%n", nodelist.get(i));
				}
			
			System.out.printf("Done processing node %s%n", nodelist.get(at));
			BFSlist.add(nodelist.get(at));
			if (nodelist.get(at).equals(Integer.toString(endnode)))
			{
				return;
			}
		}
		
		System.out.printf("Finished with the BFS from start node %s%n", nodelist.get(start));
	}

}
