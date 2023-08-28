import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static int t,N,c,answer;
	static ArrayList<Point> cores;
	static int [][] map;
	static int dx[] = {0,0,-1,1} , dy[] = {-1,1,0,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			N= Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores  = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(i !=0 && j != 0 && j!=N-1 && i!=N-1) {
						if(map[i][j] == 1)cores.add(new Point(j,i));
					}
				}
			}
			c=0;
			answer = 0;
			find(0,0,0);
			System.out.println("#" + test_case +" "+ answer);
			
		}
		
	}
	private static void find(int cnt, int coreCnt , int sum) {
		if(cnt == cores.size()) {
			if(c < coreCnt) {
				c = coreCnt;
				answer = sum;
			}
			else if(c == coreCnt) {
				answer = Math.min(answer, sum);
			}
			return;
		}
		Point p = cores.get(cnt);
		for (int i = 0; i < 4; i++) {
			int realx = p.x;
			int realy = p.y;
			int count =0;
			int tempx = p.x;
			int tempy = p.y;
		
			while(tempx >0 && tempx <N-1 && tempy>0 && tempy<N-1) {
				tempx += dx[i];
				tempy += dy[i];
				if(map[tempy][tempx] != 0) {
					count =0;
					break;
				}
				count++;
			}
			if(count == 0 ) {
				find(cnt+1,coreCnt,sum);
			}
			else {
				for (int j = 0; j < count; j++) {
					realx += dx[i];
					realy += dy[i];
					map[realy][realx] = 2;
				}
				find(cnt+1,coreCnt+1,sum+count);
				 realx = p.x;
				 realy = p.y;
				for (int j = 0; j < count; j++) {
					realx += dx[i];
					realy += dy[i];
					map[realy][realx] = 0;
				}
			}
		}
		
	}
}
