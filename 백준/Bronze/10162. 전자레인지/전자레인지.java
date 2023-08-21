import java.util.Scanner;

public class Main {
	public static void main(String[] args) { //1번 문제 베이킹 
		Scanner sc = new Scanner(System.in); //스캐너를 통한 입력숫자 하나 받기
		int T = sc.nextInt(); //베이킹 값 받기
		int cnt = 0; //300초일 때
		int cnt2 =0; //60초일떄
		int cnt3 = 0; //10초일 떄 카운트
		boolean flag = false;
		while(T>0) { //0보다 클 때
			if(T -300 >= 0) { //큰값먼저 뺴주기 그리디
				T -= 300;
				cnt+=1;
			}
			else if(T-60 >= 0) { //60초 뺴주기
				T -=60;
				cnt2+=1;
			}
			else if(T-10 >= 0) { //10초 빼주기
				T -= 10;
				cnt3+=1;
			}
			else if(T <10) { //그래도 남은 시간이 10초 미만이 있으면 break;
				flag = true;
				break;
			}
		}
		//남은 시간 있으면 -1
		if(flag)System.out.println(-1); 
		// 없으면 300 60 10 초 출력
		else System.out.println(cnt+ " "+cnt2 + " "+cnt3); 

	}
}
