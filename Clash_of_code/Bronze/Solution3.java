import java.util.Scanner;

public class Solution3 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String name = in.nextLine();
            boolean ans = true;
            for (char ch : name.toCharArray()) {
                if (ch < 65 || ch > 90) {
                    ans = false;
                }
            }
            if (ans) {
                System.out.println("VALID");
            } else {
                System.out.println("INVALID");
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        // System.out.println("VALID/INVALID");
    }
}
