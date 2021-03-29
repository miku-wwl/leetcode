import java.util.*;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m, n;
        m = scan.nextInt();
        n = scan.nextInt();
        int[][] matrix = new int[m + 1][n + 1];

        int[] x = new int[m+1];
        int[] y = new int[n+1];

        // setValue(T);
        // getValue();

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = scan.nextInt();
            }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] == 0) {
                    x[i] = 1;
                    y[j] = 1;
                }
            }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (x[i] == 1 || y[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}