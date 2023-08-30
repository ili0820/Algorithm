import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
	static class PointNum extends Point {
		int kcnt, answer;

		public PointNum(Point p, int kcnt, int answer) {
			super(p);
			this.kcnt = kcnt;
			this.answer = answer;
		}

	}

	static boolean check(int x, int y) {
		if (x >= 0 && x < w && y >= 0 && y < h) {
			return true;

		}
		return false;
	}

	static int w, h, k;
	static int map[][];
	static ArrayDeque<PointNum> monkey;
	static int answer;
	static int hx[] = { -1, 1, -2, 2, -1, 1, -2, 2 }, hy[] = { -2, -2, -1, -1, 2, 2, 1, 1 }; // 말뛰기
	static int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		h = Integer.parseInt(s[1]);
		w = Integer.parseInt(s[0]);
		answer = Integer.MAX_VALUE;
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		boolean visited[][][] = new boolean[h][w][k+1];
		visited[0][0][k] = true;
		
		monkey = new ArrayDeque<PointNum>();
		monkey.add(new PointNum(new Point(0, 0), k, 0));
		while (!monkey.isEmpty()) {
			
			PointNum poll = monkey.poll();
			
			if (poll.x == w-1 && poll.y == h-1) {
				answer = poll.answer;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int x = dx[i] + poll.x;
				int y = dy[i] + poll.y;
				if (check(x, y) && map[y][x] == 0 && !visited[y][x][poll.kcnt]) {
					visited[y][x][poll.kcnt] = true;
					monkey.add(new PointNum(new Point(x, y), poll.kcnt, poll.answer + 1));

				}
			}
			if (poll.kcnt > 0) {
				for (int i = 0; i < 8; i++) {
					int x = hx[i] + poll.x;
					int y = hy[i] + poll.y;
					if (check(x, y) && map[y][x] == 0  && !visited[y][x][poll.kcnt-1]) {
						visited[y][x][poll.kcnt-1] = true;
						monkey.add(new PointNum(new Point(x, y), poll.kcnt - 1, poll.answer + 1));
					}
				}
			}
		}
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

}
