import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int list[][];
	static boolean flag;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new int[6][3];
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 4; t++) {
			String[] s = br.readLine().split(" ");
			flag = false;
			int jIdx = 0;
			for (int i = 0; i < 6; i++) {
				for (int k = jIdx, idx = 0; k < jIdx + 3; k++, idx++) {
					list[i][idx] = Integer.parseInt(s[k]);
				}
				jIdx += 3;
			}
			find(0, 1);
			sb.append(flag ? 1:0).append(" ");
		}

		System.out.println(sb);

	}

	private static void find(int teamA, int teamB) {
		if(teamA == 6) {
			if(check()) {
				flag = true;
			}
			return;
		}
		if(teamB == 6) {
			find(teamA + 1, teamA + 2);
			return;
		}

		if(list[teamA][0] > 0 && list[teamB][2] > 0 ) {
			list[teamA][0]--;
			list[teamB][2]--;
			find(teamA, teamB + 1);
			list[teamA][0]++;
			list[teamB][2]++;
		}
		
		if(list[teamA][1] > 0 && list[teamB][1] > 0 ) {
			list[teamA][1]--;
			list[teamB][1]--;
			find(teamA, teamB + 1);
			list[teamA][1]++;
			list[teamB][1]++;
		}
		
		if(list[teamA][2] > 0 && list[teamB][0] > 0 ) {
			list[teamA][2]--;
			list[teamB][0]--;
			find(teamA, teamB + 1);
			list[teamA][2]++;
			list[teamB][0]++;
		}

	}

	private static boolean check() {
		boolean flag = true;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if(list[i][j] != 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

//	private static void Print() {
//		for (int i = 0; i < 6; i++) {
//			for (int j = 0; j < 3; j++) {
//				System.out.print(list[i][j]+ " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}