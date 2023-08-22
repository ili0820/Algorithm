
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	static int l,c ;
	static char data[];

	static char answer[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		l = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		data = new char[c];
		answer= new char[l];
		String [] da = br.readLine().split(" ");
		for (int i = 0; i < c; i++) {
			data[i] = da[i].charAt(0);
		}
		Arrays.sort(data);
		find(0,0,0,0);//자음,모음 카운트, arr, visited, 

	}

	private static void find(int jaCnt, int moCnt,int cnt,int start) {
		if(cnt == l) {

			if(jaCnt >= 2 && moCnt >=1) {
				System.out.println(answer);
			}
			return;
		}
		for (int i = start; i < c; i++) {
			answer[cnt] = data[i];
			if(answer[cnt] == 'a' || answer[cnt] == 'e' || answer[cnt] == 'i' || answer[cnt] =='o' || answer[cnt] =='u') {
				find(jaCnt,moCnt+1,cnt+1,i+1);
			}
			else {
				find(jaCnt+1,moCnt,cnt+1,i+1);
			}
	
		}
		
	}

}
