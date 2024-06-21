import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            int num = in.nextInt();
            array[i] = num;
        }
        Arrays.sort(array);
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if (array.length == 1) {
            System.out.println(array[0]);
        } else {
            System.out.println(array[array.length - 2]);
        }
    }
}