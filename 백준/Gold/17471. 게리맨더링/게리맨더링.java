import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] adjList;
    static int[] populations;
    static boolean[] visited;
    static StringTokenizer st;
    static int answer=Integer.MAX_VALUE;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        populations = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            populations[i] = Integer.parseInt(st.nextToken());

        adjList = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int to = Integer.parseInt(st.nextToken()) - 1;
                adjList[i][to] = 1;
                adjList[to][i] = 1;
            }
        }
        comb();
        if(answer==Integer.MAX_VALUE)System.out.print(-1);
        else System.out.print(answer);

    }

    private static void comb() {
        
        
        for (int R = 1; R < N ; R++) {
            int[] p = new int[N];
            int cnt = 0;
            while (++cnt <= R)p[N - cnt] = 1;

            do {
                answer=Math.min(answer,cal_diff(p));
            } while (np(p));
        }
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

    private static int cal_diff(int[] p){
        List<Integer> a= new ArrayList<>();
        List<Integer> b= new ArrayList<>();
        for(int i=0;i<p.length;i++){
            if(p[i]!=0)a.add(i);
            else b.add(i);
        }

        if(isconnected(a)&& isconnected(b)){
            int cnta=0;
            for(int v:a)cnta+=populations[v];
            int cntb=0;
            for(int v:b)cntb+=populations[v];
            return Math.abs(cntb-cnta);
        }
        return Integer.MAX_VALUE;
    }
    private static boolean isconnected(List<Integer> list){
        boolean[] visited= new boolean[N];
        Queue<Integer> q= new ArrayDeque<>();
        int first=list.get(0);
        q.add(first);
        visited[first]=true;
        while(!q.isEmpty()){
            int now = q.poll();
            for(int next=0;next<N;next++){
                int possible=adjList[now][next];
                if(possible==0)continue;
                if(visited[next])continue;
                if(list.contains(next)){
                        q.add(next);
                        visited[next]=true;
                    }
                
            }
        }

        for(int i:list)if(!visited[i])return false;
        return true;
    }
    public static void printarr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
