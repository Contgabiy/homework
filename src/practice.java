import java.util.Scanner;

public class practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        tool tool = new tool();
        tool.dfs(i,1);
    }
}

class tool {
    int[] arr = new int[10100];

    public void count(int n) {
        for (int i = 1; i <= n; i++) {
            if (i == 1)
                System.out.printf("%d", arr[i]);
            else
                System.out.printf("+%d", arr[i]);
        }
        System.out.println();
    }

    public void dfs(int N, int ct) {
        for (int i = 1; i <= N / 2; i++) {
            if (i >= arr[ct - 1]) {
                arr[ct] = i;
                arr[ct + 1] = N - i;
                count(ct + 1);
                dfs(N - i, ct + 1);
            }
        }
    }
}
