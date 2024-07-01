package Algorithm.Lists;

import java.util.Scanner;

public class There_is_no_spoon_I {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int[][][] ans = new int[width][height][3];  // Use 3-dimension array which the int[][][0] is the cell, int[][][1] is the nearest right power-cell, int[][][2] is the nearest bottom power-cell
        for (int j = 0; j < height; j++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            for (int i = 0; i < width; i++) {
                // Ignore the empty cell
                if (line.charAt(i) == '.') {
                    continue;
                }
                // Mark the power-cell with '1' value
                ans[i][j][0] = 1;
                // Check all the cells in the same row whether there is any of them still not have any 'nearest right power-cell' yet, if yes binding the current cell to those 'previous' cell as their 'nearest power-cell'
                for (int k = i - 1; k >= 0; k--) {
                    if (ans[k][j][0] == 0) {
                        continue;
                    }
                    if (ans[k][j][1] == 0) {
                        ans[k][j][1] = i;
                    } else {
                        break;
                    }
                }
                // Check all the cells in the same col whether there is any of them still not have any 'nearest bottom power-cell' yet, if yes binding the current cell to those 'previous' cell as their 'nearest power-cell'
                for (int k = j - 1; k >= 0; k--) {
                    if (ans[i][k][0] == 0) {
                        continue;
                    }
                    if (ans[i][k][2] == 0) {
                        ans[i][k][2] = j;
                    } else {
                        break;
                    }
                }
            }
        }
        // Print the ans to the console
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int x2 = -1;
                int y2 = -1;
                int x3 = -1;
                int y3 = -1;
                if (ans[i][j][0] == 0) {
                    continue;
                }
                if (ans[i][j][1] != 0) {
                    x2 = ans[i][j][1];
                    y2 = j;
                }
                if (ans[i][j][2] != 0){
                    x3 = i;
                    y3 = ans[i][j][2];
                }
                System.out.println(i + " " + j
                + " " + x2 + " " + y2
                + " " + x3 + " " + y3);
            }
        }
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // Three coordinates: a node, its right neighbor, its bottom neighbor
        // System.out.println("0 0 1 0 0 1");
    }
}
