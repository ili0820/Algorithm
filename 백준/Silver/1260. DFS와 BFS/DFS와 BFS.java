import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {
	static int N , M;
	static ArrayList<Integer>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		int V = Integer.parseInt(s[2]);
	
		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b =Integer.parseInt(s[1]);
			
			graph[a].add(b);
			graph[b].add(a);
			
		}
	
		for (int i = 1; i <=N ; i++) {
			Collections.sort(graph[i]);
		}
		dfs(V,new boolean[N+1]);
		System.out.println();
		bfs(V);
		
	
	}
	private static void bfs(int v) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N+1];
		visited[v] = true;
		q.offer(v);
		System.out.print(v+ " ");
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int node : graph[cur]) {
				if(!visited[node]) {
					System.out.print(node+ " ");
					visited[node] = true;
					q.offer(node);
				} 
			}
		}
		
	}
	private static void dfs(int v,boolean [] visited) {
		visited[v] = true;
		System.out.print(v+ " ");
		for (int node : graph[v]) {
			if(visited[node]) continue;
			visited[node] = true;
			dfs(node,visited);
		}
		
	}

}
