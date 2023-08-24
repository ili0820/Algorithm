
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int N,M;
	static int popul[];
	static ArrayList<Integer>[] list;
	static int answer;
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		popul = new int[N];
		String[] s = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			popul[i] = Integer.parseInt(s[i]);
		}

		list = new ArrayList[N];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for(int cIdx = 0; cIdx < N; cIdx++) {
			s = br.readLine().split(" ");
			M = Integer.parseInt(s[0]);
			for (int i = 1,pidx=0; i <=M; i++,pidx++) {
				int a = Integer.parseInt(s[i]) -1;
				list[cIdx].add(a);
			}
		}
		visited = new boolean[N];
		answer = Integer.MAX_VALUE;
		find(0);
		if(answer== Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(answer);
	}
	private static void find(int cnt) {
		if(cnt == N) {
			ArrayList<Integer> aList = new ArrayList<Integer>();
			ArrayList<Integer> bList = new ArrayList<Integer>();
			for (int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					aList.add(i);
				}else {
					bList.add(i);
				}
			}
			if(aList.size() == 0 || bList.size() ==0) return;
			if(check(aList) && check(bList)) {
				count(visited);
			}
			
			return;
		}
		visited[cnt] = true;
		find(cnt+1);
		visited[cnt] = false;
		find(cnt+1);
		
	}
	private static void count(boolean visited[]) {
		int a = 0;
		int b = 0;
		for (int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				a+= popul[i];
			}else {
				b+= popul[i];
			}
		}
		if(answer > Math.abs(a -b)) {
			answer = Math.min(answer, Math.abs(a -b));
			
		}
	}
	private static boolean check(ArrayList<Integer> list2) {
		ArrayDeque<Integer> dq= new ArrayDeque<Integer>();
		boolean [] visited = new boolean[N];
		visited[list2.get(0)] = true;
		dq.offer(list2.get(0));
		int cnt = 1;
		
		while(!dq.isEmpty()) {
			Integer v = dq.poll();
			for (int i = 0; i < list[v].size(); i++) {
				int cur = list[v].get(i);
				if(!visited[cur] && list2.contains(cur)) {
					cnt++;
					visited[cur] = true;
					dq.offer(cur);
				}
			}
		}
		if(list2.size() == cnt) {
			return true;
		}
		return false;
	}

}
