import java.util.Scanner;

public class Solution5 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[][] matrix = new String[5][5];
        boolean blankCellFound = false;
        int sum = 0;
        int blankCellRow = -1;
        for (int i = 0; i < 5; i++) {
            String line = in.nextLine();
            matrix[i] = line.split(" ");
            if (!blankCellFound && !line.contains("-1")) {
                for (String num : matrix[i]) {
                    sum += Integer.valueOf(num);
                }
                blankCellFound = !blankCellFound;
            }
            if (line.contains("-1")) {
                blankCellRow = i;
            }
        }
        System.err.println(sum);
        // int res = -1;
        for (int i = 0; i < 5; i++) {
            if (matrix[blankCellRow][i].equals("-1")) {
                continue;
            }
            sum -= Integer.valueOf(matrix[blankCellRow][i]);
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sum);
    }    
}
