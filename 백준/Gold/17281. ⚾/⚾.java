
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] records;
    static int[] input;
    static int answer=Integer.MIN_VALUE;

    static int outs = 0;
    static int player = 0;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        records = new int[N][9];
        input = new int[9];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++)
                records[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 9; i++)
            input[i] = i;
        Arrays.sort(input);

        do {
            if (input[3] == 0){
                int total=0;
                outs = 0;
                player = 0;
                for(int inning=0; inning<N;inning++){
                    total+=playgame(input,records[inning]);
                    outs=0;
                }
                answer=answer<total?total:answer;
            }
        } while (np(input));
        System.out.println(answer);
    }

    private static int playgame(int[] hitters,int[] results) {//results는 타순
        boolean[] field = new boolean[3];
        int score = 0;
        while (outs < 3) {
            switch (results[hitters[player]]) {
                case 0: {
                    outs += 1;
                    player = (player + 1) % 9;
                    break;
                }
                case 1: {
                    //3루에 사람이 있으면 점수 추가
                    if (field[2])
                        score += 1;
                    //한 명씩 밀기
                    for(int i=2;i>0;i--)field[i]=field[i-1];
                    //1루에 사람 한명 추가
                    field[0]=true;

                    player = (player + 1) % 9;
                    break;
                }
                case 2: {
                    //3루에 사람이 있으면 점수 추가
                    if (field[2])
                        score += 1;
                    //2루에 사람이 있으면 점수 추가
                    if (field[1])
                        score += 1;
                    //3루에 1루에 있던 사람 옮기기
                    field[2]=field[0];
                    //2루에 사람 한명 추가
                    field[1]=true;
                    //1루 사람 제거
                    field[0]=false;
                    player = (player + 1) % 9;
                    break;
                }
                case 3: {
                    //3루에 사람이 있으면 점수 추가
                    if (field[2])
                        score += 1;
                    //2루에 사람이 있으면 점수 추가
                    if (field[1])
                        score += 1;
                    //1루에 사람이 있으면 점수 추가
                    if (field[0])
                        score += 1;
                    //3루에 사람추가
                    field[2]=true;
                    field[1]=false;field[0]=false;//다 제거
                    player = (player + 1) % 9;
                    break;
                }
                case 4: {
                    for(int i=0;i<3;i++)
                    if(field[i]){
                        score+=1;
                        field[i]=false;
                    }
                    //본인 점수
                    score+=1;
                    player = (player + 1) % 9;
                    break;
                }

            }

        }
        return score;

    }

    private static boolean np(int[] p) {// p 다음 순열을 원하는 기존 순열의 배열
        // 1. 맨뒤쪽부터 탐색하며 꼭대기 찾기
        int N = p.length;

        ///////////////////////////////////////////////////////////////////
        int i = N - 1;
        while (i > 0 && p[i - 1] >= p[i])
            --i;
        if (i == 0)
            return false;// 다음 순열을 없음(가장 큰 순열의 형태)

        ///////////////////////////////////////////////////////////////////
        // 2 꼭대기 직전(i-1) 위치에 교환할 한단계 큰 수 뒤쪽부터 찾기
        int j = N - 1;
        while (p[i - 1] >= p[j])
            --j;

        // 3 꼭대기 직전(i-1) 위치의 수와 한단계 큰 수를 교환하기
        swap(p, i - 1, j);
        ///////////////////////////////////////////////////////////////////
        // 4 꼭대기자리부터 맨뒤까지의 수를 오름차순의 형태로 바꿈
        int k = N - 1;
        while (i < k) {
            swap(p, i++, k--);
        }

        return true;
    }

    private static void swap(int[] p, int a, int b) {
        int temp = p[a];
        p[a] = p[b];
        p[b] = temp;
    }
}
