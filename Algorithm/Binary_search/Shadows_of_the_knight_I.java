package Algorithm.Binary_search;

import java.util.Scanner;

public class Shadows_of_the_knight_I {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        int upY = 0;
        int botY = H - 1;
        int leftX = 0;
        int rightX = W - 1;
        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            int targetX = X0;
            int targetY = Y0;
            if (bombDir.contains("U")) {
                // upY = 0;
                botY = Y0 - 1;
            } else if (bombDir.contains("D")){
                upY = Y0 + 1;
                // botY = H - 1;
            } else {
                upY = Y0;
                botY = Y0;
            }
            if (bombDir.contains("R")) {
                leftX = X0 + 1;
                // rightX = W - 1;
            } else if (bombDir.contains("L")){
                // leftX = 0;
                rightX = X0 - 1;
            } else {
                leftX = X0;
                rightX = X0;
            }
            targetX = leftX + Math.abs(leftX - rightX) / 2;
            targetY = upY + Math.abs(upY - botY) / 2;
            System.out.println(targetX + " " + targetY);
            X0 = targetX;
            Y0 = targetY;
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // the location of the next window Batman should jump to.
            // System.out.println("0 0");
        }
    }
}
