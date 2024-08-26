import java.util.Scanner;

public class Solution4 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x % 2 == 0) {
                sum += x;
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sum);
    }
}
