

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int map[][], N;
	static String data;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		sb = new StringBuilder();
		
		
		find(0,0,N);
		System.out.println(sb);
		
	}
	private static void find(int i, int j, int size) {
		int temp = 0;
		for (int k = i; k < i+size; k++) {
			for (int z = j; z < j+size; z++) {
				temp+=map[k][z];
			}
		}
		if(temp == 0) {
			sb.append(0);

		}
		else if(temp == size*size) {
			sb.append(1);
		}
		else {
			sb.append("(");
			int half = size/2;
			find(i,j,half);
			find(i,j+half,half);
			find(i+half,j,half);
			find(i+half,j+half,half);
			sb.append(")");
		}

		
	}

}
