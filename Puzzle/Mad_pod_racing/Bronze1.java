package Puzzle.Mad_pod_racing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bronze1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<Checkpoint> checkpoints = new ArrayList<>();
        Coordinate[] checkPoints = new Coordinate[3];
        int id = 1;
        int checkpointHasLongestDist = 0;
        int longestDist = 0;
        boolean isBoostRemain = true;
        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int nextCheckpointX = in.nextInt(); // x position of the next check point
            int nextCheckpointY = in.nextInt(); // y position of the next check point
            int nextCheckpointDist = in.nextInt(); // distance to the next checkpoint
            int nextCheckpointAngle = in.nextInt(); // angle between your pod orientation and the direction of the next checkpoint
            int opponentX = in.nextInt();
            int opponentY = in.nextInt();
            Checkpoint currentCheckpoint;
            int checkpointId = 0;
            if (!checkpoints.contains(new Checkpoint(nextCheckpointX, nextCheckpointY))) {
                currentCheckpoint = new Checkpoint(id, nextCheckpointX, nextCheckpointY, nextCheckpointDist);
                checkpoints.add(currentCheckpoint);
                if (longestDist < currentCheckpoint.distanceFromPrevCheckpoint) {
                    longestDist = currentCheckpoint.distanceFromPrevCheckpoint;
                    checkpointHasLongestDist = id;
                }
                checkpointId = id;
                id++;                
            } else {
                checkpointId = checkpoints.indexOf(new Checkpoint(nextCheckpointX, nextCheckpointY));
            }

            // System.err.print(checkpoints.size() + ": ");
            // for (Checkpoint checkpoint : checkpoints) {
            //     System.err.print(" " + checkpoint.id + " (" + checkpoint.coordinate.x + " " + checkpoint.coordinate.y + "), ");
            // }
            System.err.print(nextCheckpointAngle + "*: " + nextCheckpointDist);

            if (checkpoints.size() == 4 && isBoostRemain && checkpointId == checkpointHasLongestDist && nextCheckpointAngle < 20 && nextCheckpointAngle > - 20) {
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " BOOST");
            } else if (nextCheckpointAngle > 90 || nextCheckpointAngle < - 90) {
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " 0");
            // } else if (nextCheckpointAngle > 45 || nextCheckpointAngle < - 45) {
            //     System.out.println(nextCheckpointX + " " + nextCheckpointY + " 50");
            // } else if (nextCheckpointDist < 1000) {
            //     System.out.println(nextCheckpointX + " " + nextCheckpointY + " " + nextCheckpointDist / 1000 * 50); 
            } else if (nextCheckpointDist < 500 && nextCheckpointAngle < 30 && nextCheckpointAngle > - 30) {
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " 0");
            } else {
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " 100");
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // You have to output the target position
            // followed by the power (0 <= thrust <= 100)
            // i.e.: "x y thrust"
        }
    }
}

class Checkpoint {
    int id;
    Coordinate coordinate;
    int distanceFromPrevCheckpoint;
    Checkpoint(int x, int y) {
        coordinate = new Coordinate(x, y);
    }
    Checkpoint(int id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
    }
    Checkpoint(int id, int x, int y, Checkpoint prevCheckpoint) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
        distanceFromPrevCheckpoint = this.coordinate.getDistance(prevCheckpoint.coordinate);
    }
    Checkpoint(int id, Coordinate coordinate, Coordinate prevCheckpoint) {
        this.id = id;
        this.coordinate = coordinate;
        distanceFromPrevCheckpoint = this.coordinate.getDistance(prevCheckpoint);
    }
    Checkpoint(int id, int x, int y, int dist) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
        this.distanceFromPrevCheckpoint = dist;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Checkpoint other = (Checkpoint) obj;
        return coordinate.x == other.coordinate.x && coordinate.y == other.coordinate.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(coordinate.x, coordinate.y);
    }    
}

class Coordinate {
    int x;
    int y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getDistance(Coordinate target) {
        double powX = Math.pow(x - target.x, 2);
        double powY = Math.pow(y - target.y, 2);
        return (int)Math.sqrt(powX + powY);        
    }
} Bronze1 {
    
}
