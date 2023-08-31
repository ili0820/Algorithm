//package 김다인;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine());
		
		adjMatrix = new int[n][n];
		
		for(int i=0;i<n;++i) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;++j) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = Integer.MAX_VALUE;
		
		for(int i=0;i<n;++i) {
			visited = new boolean[n];
			visited[i] = true;
			dfs(i, i, 1, 0);
		}
		
		System.out.println(result);
	}
	
	static void dfs(int start, int cur, int cnt, int cost) {
		if(cnt==n) {
			int fcost;
			
			//n개 다 돌았고, 처음으로 돌아가야 함
			if(adjMatrix[cur][start]!=0) fcost = cost + adjMatrix[cur][start];
			else return;
			
			if(result>fcost) result=fcost;
			return;
		}
		
		for(int i=0;i<n;++i) {
			if(!visited[i] && adjMatrix[cur][i]!=0) {
				visited[i]=true;
				dfs(start, i, cnt+1, cost+adjMatrix[cur][i]);
				visited[i]=false;
			}
		}
	}
}
