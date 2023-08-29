
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, marking[],table[][],dp[][];
	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            int r = Integer.parseInt(s[0]);
            int g = Integer.parseInt(s[1]);
            int b = Integer.parseInt(s[2]);
            table[i][0] = r;
            table[i][1] = g;
            table[i][2] = b;
        }
        dp = new int[N][3];


        dp[0][0] = table[0][0]; // 첫째빨
        dp[0][1] = table[0][1]; // 첫재 그
        dp[0][2] = table[0][2]; // 첫째 파
	
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int value = Integer.MAX_VALUE;
				for (int z = 0; z < 3; z++) {
					if(j == z) continue;
					if(value > dp[i-1][z]) {
						value = dp[i-1][z];
					}
					dp[i][j] = value+table[i][j];
				}
			}
		}
		System.out.println(Arrays.stream(dp[N-1]).min().getAsInt());
	}

}
