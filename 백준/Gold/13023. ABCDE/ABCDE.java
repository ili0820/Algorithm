
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

	static int n, m;
	static int answer;
	static ArrayList<Integer>[] lst;
	static boolean [] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);

		answer = 0;
		lst = new ArrayList[n+1];
		visited = new boolean[n+1];
		for (int i = 0; i <= n; i++) {
			lst[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			lst[a].add(b);
			lst[b].add(a);
	
		}

		for (int i = 0; i < n; i++) {
			visited[i] = true; 
			find(0,i);
			visited[i] = false;
			 if(answer ==1 ) break;
		}
		System.out.println(answer);
	}
	private static void find(int cnt, int node) {
		if(cnt >= 4) {
			answer = 1;
			return;
		}
		if(answer == 1) return;
		for (int es : lst[node]) {
			if(visited[es]) continue;
			visited[es] = true;
			find(cnt+1,es);
			visited[es] = false;
		}
	}
}

