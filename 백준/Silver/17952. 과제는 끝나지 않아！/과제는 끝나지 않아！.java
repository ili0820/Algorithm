import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main { //2번 문제 업무 평가
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 받기 위해 버퍼드 리더 사용 
		ArrayDeque<Point> p = new ArrayDeque<Point>(); //포인터 클래스를 이용해 데크에 저장 x 값은 남은시간 y 값은 점수
		int answer = 0; //정답을 출력하기 위해 사용
		int T = Integer.parseInt(br.readLine()); // T 입력 받기 업무량
		for (int i = 0; i <T ; i++) { //업무량만큼 돌면서
			String[] s = br.readLine().split(" "); //입력받기
			if(s[0].equals("1")) { //1 업무가 주어지면
				int time = Integer.parseInt(s[2])-1; //현재 시간에 -1
				int score = Integer.parseInt(s[1]); //점수를 입력받는다.
				if(time == 0) { //현재시간 -1 이 0이면 하자마자 업무 끝나니깐 
					answer += score; //점수 더해주기
				}else { //현재시간 -1이 0이 아니면 업무 안끝나니깐
					p.addFirst(new Point(time,score)); //데크에 추가
				}

			}
			else { //0 업무가 없을 경우
				if(!p.isEmpty()) { // 업무가 비어있지 않다면
					Point data = p.pollFirst(); //꺼내기
					if(data.x - 1 == 0) { //업무시간 -1 ==0 이면 업무 끝나니깐
						answer+= data.y; //정답에 더해주기
					}
					else { //업무시간 -1 != 0 일 경우
						p.addFirst(new Point(data.x-1,data.y)); //업무시간 -1 한 값을 다시 큐에 넣어주기
					}
				}
			}
		}
		System.out.println(answer); // 정답출력
	
	}
}
