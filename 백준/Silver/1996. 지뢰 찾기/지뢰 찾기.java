import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				if (str.charAt(j) == '.') {
					map[i][j] = 0;
				} else {
					map[i][j] = Integer.parseInt(str.substring(j, j+1));
				}
			}
		}
		
		int[] dx = {1,-1,0,0,1,1,-1,-1};
		int[] dy = {0,0,-1,1,1,-1,-1,1};
		
		String[][] result = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0) {
					result[i][j] = "*";
					continue;
				}
				
				int cnt = 0;
				for (int k = 0; k < 8; k++) {
					int nr = i+dx[k];
					int nc = j+dy[k];
					
					if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
						cnt+=map[nr][nc];
					}
				}
				
				if (cnt >= 10) {
					result[i][j] = "M";
				} else {
					result[i][j] = String.valueOf(cnt);
				}
				
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				bw.append(result[i][j]);
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}
}