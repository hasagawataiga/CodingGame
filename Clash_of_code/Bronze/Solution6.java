import java.util.Scanner;

public class Solution6 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String ingre = in.nextLine();
        System.err.println(ingre);
        int P = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < P; i++) {
            String order = in.nextLine();
            System.err.println("order: " + order);
            String[] orderArray = order.split(" ");
            for (String element : orderArray) {
                if (!ingre.contains(element)) {
                    System.out.println("NO");
                    return;
                }
                ingre = ingre.replaceFirst(element, "");
                System.err.println(ingre);
            }
        } 
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("YES");
    }    
}
