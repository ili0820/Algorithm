import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	static int n,m;
	static int map[][];
	static int answer;
	static ArrayList<pointAndNum> cam;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		map = new int[n][m];
		answer= Integer.MAX_VALUE;
		cam = new ArrayList<pointAndNum>();
		for (int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] !=0 ) {
					if(map[i][j] == 6) continue;
					cam.add(new pointAndNum(new Point(j,i), map[i][j]));
				}
			}
		}
		
		find(0,map);
		System.out.println(answer);
		
	}
	private static void find(int cnt, int[][] data) {
		if(cnt == cam.size()) {
			int temp = mapCount(data);

			if(answer > temp) answer = Math.min(answer, temp);
			return;
		}
		pointAndNum pointAndNum = cam.get(cnt);
		switch(pointAndNum.num) {
		case 1:
			for (int i = 0; i < 4; i++) { //0 위 //1 아래 2왼쪽 3 오른쪽
				find(cnt+1,case1(i,data,pointAndNum.getLocation()));
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) { //0 위아래 //가로
				find(cnt+1,case2(i,data,pointAndNum.getLocation()));
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				find(cnt+1,case3(i,data,pointAndNum.getLocation()));
			}
			break;
		case 4: 
			for (int i = 0; i < 4; i++) {
				find(cnt+1,case4(i,data,pointAndNum.getLocation()));
			}
			break;
		case 5:
			find(cnt+1,case5(data,pointAndNum.getLocation()));
			break;
		}
		
		
		
	}
	private static int[][] case5(int[][] data,Point p) {
		int temp[][] = CopyMap(data);
		int x = p.x;
		int y = p.y;
		for (int i = x-1; i >=0; i--) {
			if(temp[y][i] == 6) break;
			else {
				temp[y][i] = -1;
			}
		}
		for (int i = y-1; i >=0; i--) {
			if(temp[i][x] == 6) break;
			else {
				temp[i][x] = -1;
			}
		}
		for (int i = x+1; i <m; i++) {
			if(temp[y][i] == 6) break;
			else {
				temp[y][i] = -1;
			}
		}
		for (int i = y+1; i <n; i++) {
			if(temp[i][x] == 6) break;
			else {
				temp[i][x] = -1;
			}
		}
		return temp;
	}
	private static int[][] case4(int d,int[][] data,Point p) {
		int temp[][] = CopyMap(data);
		int x = p.x;
		int y = p.y;
		if(d==0) {// < ^ >
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		if(d==1) { // ^> 아래
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		if(d==2) { //^< 아래
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		if(d==3) {
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
		}
		return temp;
	}
	private static int[][] case3(int d,int[][] data,Point p) {
		int temp[][] = CopyMap(data);
		int x = p.x;
		int y = p.y;
		if(d==0) { //<- ^
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
		}
		if(d==1) {// ^ >
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		if(d==2) { // < 아래
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		if(d==3) {
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
		}
		return temp;
	}
	private static int[][] case2(int d,int[][] data,Point p) {
		int temp[][] = CopyMap(data);
		int x = p.x;
		int y = p.y;
		if(d ==0) {
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
		}
		if(d ==1) {
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		return temp;
	}
	private static int[][] case1(int d,int[][] data,Point p) {
		int temp[][] = CopyMap(data);
		int x = p.x;
		int y = p.y;
		if(d ==0) {
			for (int i = y-1; i >=0; i--) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
		}
		if(d ==1) {
			for (int i = y+1; i <n; i++) {
				if(temp[i][x] == 6) break;
				else {
					temp[i][x] = -1;
				}
			}
		}
		if(d ==2) {
			for (int i = x-1; i >=0; i--) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		if(d ==3) {
			for (int i = x+1; i <m; i++) {
				if(temp[y][i] == 6) break;
				else {
					temp[y][i] = -1;
				}
			}
		}
		
		return temp;
	}
	public static int[][] CopyMap(int data[][]){
		int tempMap[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tempMap[i][j] = data[i][j];
			}
		}
		return tempMap;
	}
	
	public static int mapCount(int data[][]) {
		int a = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(data[i][j] == 0) a+=1;
			}
		}
		return a;
	}

	public static class pointAndNum extends Point{
		int num;

		public pointAndNum(Point p ,int num) {
			super(p);
			this.num = num;
		}
		@Override
		public String toString() {
			return super.toString()+"pointAndNum [num=" + num + "]";
		}
		
	}
//	public static void Print(int [][] data) {
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(data[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
