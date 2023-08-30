
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;



public class Main {
	static class Pi extends Point{
		int m;

		public Pi(Point p,int m) {
			super(p);
			this.m = m;
		}
		
	}
	private static boolean check(int x,int y) {
		if(x >=0 && x<N && y>=0 && y<N) {
			return true;
		}
		return false;
	}
	static int N;
	static int map[][];
	static int dx0[]= {1,1}, dy0[] = {0,1} , dx1 []= {0,1} ,dy1[] = {1,1},dx2[]= {0,1,1},dy2[]= {1,0 ,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] s =br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if(s[j].equals("1")) map[i][j]=1;
			}
		}
		if(map[N-1][N-1] == 1) {
			System.out.println(0);
			return;
		}

		ArrayDeque<Pi> pipe = new ArrayDeque<Pi>();
		pipe.add(new Pi(new Point(1,0),0)); //0은가로
		int cnt = 0;
		while(!pipe.isEmpty()) {
			Pi poll = pipe.poll();
			if(poll.x == N-1 && poll.y == N-1) {
				cnt+=1;
				continue;
			}
			switch (poll.m) {
			case 0:
				for (int i = 0; i < dx0.length; i++) {
					int x = poll.x + dx0[i];
					int y= poll.y +dy0[i];
					if(check(x, y)) {
						if(i==0 && map[y][x] ==0) {
							pipe.add(new Pi(new Point(x,y), 0));
						}
						else if(i==1 && map[y][x] ==0 && map[y-1][x]==0 && map[y][x-1] == 0) {
							pipe.add(new Pi(new Point(x,y), 2));
						}
					}
				}
				break;
			case 1:
				for (int i = 0; i < dx1.length; i++) {
					int x = poll.x + dx1[i];
					int y= poll.y +dy1[i];
					if(check(x, y)) {
						if(i==0 && map[y][x] ==0) {
							pipe.add(new Pi(new Point(x,y), 1));
						}
						else if(i==1 && map[y][x] ==0 && map[y-1][x]==0 && map[y][x-1] == 0) {
							pipe.add(new Pi(new Point(x,y), 2));
						}
					}
				}
				break;
			case 2:
				for (int i = 0; i < dx2.length; i++) {
					int x = poll.x + dx2[i];
					int y= poll.y +dy2[i];
					if(check(x, y)) {
						if(i==0 && map[y][x] ==0) {
							pipe.add(new Pi(new Point(x,y), 1));
						}
						else if(i==1 && map[y][x] ==0) {
							pipe.add(new Pi(new Point(x,y), 0));
						}
						else if(i==2 && map[y][x] ==0 && map[y-1][x]==0 && map[y][x-1] == 0) {
							pipe.add(new Pi(new Point(x,y), 2));
						}
					}
				}
				break;
			}
		}
		System.out.println(cnt);
	}

}

