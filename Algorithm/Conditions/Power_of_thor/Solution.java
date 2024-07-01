public class Solution {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTx = in.nextInt(); // Thor's starting X position
        int initialTy = in.nextInt(); // Thor's starting Y position
        int[] directions = new int[4]; // E - W - S - N
        
        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.
            directions = new int[4];
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            if (initialTx < lightX) {
                directions[0] = 1;
            } else if (initialTx > lightX) {
                directions[1] = 1;
            }
            if (initialTy < lightY) {
                directions[2] = 1;
            } else if (initialTy > lightY) {
                directions[3] = 1;
            }
            StringBuilder sb = new StringBuilder();
            if (directions[2] == 1) {
                sb.append("S");
                initialTy++;
            }
            if (directions[3] == 1){
                sb.append("N");
                initialTy--;
            }
            if (directions[0] == 1) {
                sb.append("E");
                initialTx++;
            }
            if (directions[1] == 1){
                sb.append("W");
                initialTx--;
            }
            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(sb.toString());
        }
    }
}