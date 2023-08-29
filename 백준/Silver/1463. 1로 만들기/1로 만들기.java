

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static int data[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		data = new int[N+1];
		data[1] = 0;
		for (int i = 2; i <= N; i++) {
			data[i] = data[i-1]+1;
			if(i%2 ==0) data[i] = Math.min(data[i], data[i/2]+1);
			if(i%3 ==0) data[i] = Math.min(data[i], data[i/3]+1);
		}
		System.out.println(data[N]);
	}
}
