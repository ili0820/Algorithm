

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Main {
	static class Edge{
		int no,weight;

		public Edge(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [no=" + no + ", weight=" + weight + "]";
		}
		
	}
	
	static int V, E,start;
	static ArrayList<Edge>[] edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		V = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);
		start = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			edges[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			int weight = Integer.parseInt(s[2]);
			
			edges[u].add(new Edge(v,weight));
		}
		
		dj(start);
		
	
	}

	private static void dj(int start) {
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});
		q.add(new Edge(start, 0));
		int dis[] = new int[V+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			int no = cur.no;
			int weight = cur.weight;

			if(dis[no] < weight) continue;
			
			for (int i = 0; i < edges[no].size(); i++) {
				int cost = edges[no].get(i).weight + dis[no];
				if(cost < dis[edges[no].get(i).no]) {
					dis[edges[no].get(i).no] = cost;

					q.add(new Edge(edges[no].get(i).no, cost));
				}
			}
		}
		for (int i = 1; i < dis.length; i++) {
			if(dis[i] != Integer.MAX_VALUE) System.out.println(dis[i]);
			else System.out.println("INF");
		}
	}

}
