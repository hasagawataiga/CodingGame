package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Player {
    static List<Human> humans = new ArrayList<>();
    static List<Zombie> zombies = new ArrayList<>();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Player player = new Player();
        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            Coordinate ash = new Coordinate(x, y);
            int humanCount = in.nextInt();
            humans = new ArrayList<>();
            zombies = new ArrayList<>();
            for (int i = 0; i < humanCount; i++) {
                int humanId = in.nextInt();
                int humanX = in.nextInt();
                int humanY = in.nextInt();
                player.updateHumans(humanId, humanX, humanY);
            }
            int zombieCount = in.nextInt();
            for (int i = 0; i < zombieCount; i++) {
                int zombieId = in.nextInt();
                int zombieX = in.nextInt();
                int zombieY = in.nextInt();
                int zombieXNext = in.nextInt();
                int zombieYNext = in.nextInt();
                player.updateZombies(zombieId, zombieX, zombieY, zombieXNext, zombieYNext, ash);
            }

            Collections.sort(zombies);
            List<Zombie> potentialZombies = new ArrayList<>();
            for (Zombie zombie : zombies) {
                if (zombie.isReachableBy(ash)) {
                    potentialZombies.add(zombie);
                }
            }
            Collections.sort(potentialZombies);

            if (!potentialZombies.isEmpty()) {
                System.out.println(potentialZombies.get(0).nextCoor.x + " " + potentialZombies.get(0).nextCoor.y);
            } else {
                System.out.println(ash.x + " " + ash.y);
            }
        }
    }
    private void updateHumans(int humanId, int humanX, int humanY) {
        humans.add(new Human(humanId, humanX, humanY));
    }
    private void updateZombies(int zombieId, int zombieX, int zombieY, int zombieXNext, int zombieYNext, Coordinate ash) {
        Zombie zombie = new Zombie(zombieId, zombieX, zombieY, zombieXNext, zombieYNext, ash);
        zombie.targetOnHuman(humans);
        zombies.add(zombie);
    }
}

class Human{
    int id;
    Coordinate coordinate;

    Human(int id, int x, int y) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
    }
}

class Zombie implements Comparable<Zombie>{
    int id;
    Coordinate currentCoor;
    Coordinate nextCoor;
    int distanceFromAsh;
    int targetOn = -1;
    int turnReachTarget = 100;
    boolean isReachable = false;
    Coordinate destination;

    Zombie(int id, int currentX, int currentY, int nextX, int nextY, Coordinate ash) {
        this.id = id;
        this.currentCoor = new Coordinate(currentX, currentY);
        this.nextCoor = new Coordinate(nextX, nextY);
        this.distanceFromAsh = currentCoor.getDistance(ash);
    }    

    public int targetOnHuman(List<Human> humans) {
        int minDistance = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < humans.size(); i++) {
            int distance = currentCoor.getDistance(humans.get(i).coordinate);
            if (distance < minDistance) {
                minDistance = distance;
                targetOn = humans.get(i).id;
                index = i;
            }
        }
        turnReachTarget = (int) Math.ceil(minDistance / 400);
        computeLocationWhileMeetTarget(humans.get(index).coordinate);
        return this.targetOn;
    }
    
    private void computeLocationWhileMeetTarget(Coordinate target) {
        int newX = currentCoor.x + turnReachTarget * (nextCoor.x - currentCoor.x);
        int newY = currentCoor.y + turnReachTarget * (nextCoor.y - currentCoor.y);
        this.destination = new Coordinate(newX, newY);
    }

    public boolean isReachableBy(Coordinate ash) {
        int distanceBetweenThem = destination.getDistance(ash);
        if (distanceBetweenThem < (turnReachTarget * 1000 + 2000)) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Zombie other) {
        return this.turnReachTarget - other.turnReachTarget;
    }
}

class Coordinate {
    int x;
    int y;
    Coordinate() {}
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getDistance(Coordinate target) {
        double powX = Math.pow(x - target.x, 2);
        double powY = Math.pow(y - target.y, 2);
        return (int)Math.sqrt(powX + powY);        
    }
}