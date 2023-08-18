import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static int r, c;
    static char[][] maps;
    static int ans = 0;
    static Set<Character> alphas = new HashSet<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        r = scanner.nextInt();
        c = scanner.nextInt();
        maps = new char[r][c];
        for (int i = 0; i < r; i++) {
            maps[i] = scanner.next().toCharArray();
        }
        scanner.close();

        alphas.add(maps[0][0]);
        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int count) {
        ans = Math.max(ans, count);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !alphas.contains(maps[nx][ny])) {
                alphas.add(maps[nx][ny]);
                dfs(nx, ny, count + 1);
                alphas.remove(maps[nx][ny]);
            }
        }
    }
}