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
        boolean isFirstTurn = true;
        int[] ordinalHumans = new int[1];
        int currentHuman = 0;
        int prevHuman = -1;
        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int humanCount = in.nextInt();
            humans = new ArrayList<>();
            zombies = new ArrayList<>();
            for (int i = 0; i < humanCount; i++) {
                int humanId = in.nextInt();
                int humanX = in.nextInt();
                int humanY = in.nextInt();
                player.updateHumans(humanId, humanX, humanY, x, y);
            }
            int zombieCount = in.nextInt();
            for (int i = 0; i < zombieCount; i++) {
                int zombieId = in.nextInt();
                int zombieX = in.nextInt();
                int zombieY = in.nextInt();
                int zombieXNext = in.nextInt();
                int zombieYNext = in.nextInt();
                player.updateZombies(zombieId, zombieX, zombieY, zombieXNext, zombieYNext, x, y);
            }
            Collections.sort(humans);
            Collections.sort(zombies);
            printDebug();
            if (isFirstTurn) {
                ordinalHumans = new int[humanCount];
                for (int i = 0; i < humanCount; i++) {
                    ordinalHumans[i] = humans.get(i).getId();
                }
                isFirstTurn = false;
            }
            if (humans.get(0).getId() == prevHuman && humans.size() > 1) {
                currentHuman = humans.get(1).getId();
            } else {
                currentHuman = humans.get(0).getId();
            }
            System.out.println(zombies.get(0).getNextCoor().getX() + " " + zombies.get(0).getNextCoor().getY());
            prevHuman = currentHuman;
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // System.out.println("0 0"); // Your destination coordinates
        }
    }
    private void updateHumans(int humanId, int humanX, int humanY, int x, int y) {
        humans.add(new Human(humanId, humanY, humanY, x, y));
    }
    private void updateZombies(int zombieId, int zombieX, int zombieY, int zombieXNext, int zombieYNext, int x, int y) {
        zombies.add(new Zombie(zombieId, zombieX, zombieY, zombieXNext, zombieYNext, x, y));
        
    }
    private static void printDebug() {
        System.err.println("Humans:");
        for (Human human : humans) {
            System.err.println(human.getId() + ": " + human.getCoordinate().getX() + ", " + human.getCoordinate().getY());
        }
        System.err.println("Zombies:");
        for (Zombie zombie : zombies) {
            System.err.println(zombie.getId() + ": " + zombie.getCurrentCoor().getX() + ", " + zombie.getCurrentCoor().getY());
        }
    }
}

class Human implements Comparable<Human>{
    int id;
    Coordinate coordinate;
    int distance;
    Human(int id, int x, int y, int targetX, int targetY) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
        getDistance(new Coordinate(targetX, targetY));
    }
    Human(int id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
    }
    Human(int id, int x, int y) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
    }
    private void getDistance(Coordinate targetCoordinate) {
        double powX = Math.pow(coordinate.getX() - targetCoordinate.getX(), 2);
        double powY = Math.pow(coordinate.getY() - targetCoordinate.getY(), 2);
        this.distance = (int)Math.sqrt(powX + powY);
    }
    @Override
    public int compareTo(Human other) {
        return this.distance - other.distance;
    }
    public int getId() {return this.id;}
    public Coordinate getCoordinate() {return this.coordinate;}
    public void setId(int id) {this.id = id;}
    public void setCoordinate(Coordinate coordinate) {this.coordinate = coordinate;}
}

class Zombie implements Comparable<Zombie>{
    int id;
    Coordinate currentCoor;
    Coordinate nextCoor;
    int distance;
    Zombie() {}
    Zombie(int id, Coordinate currentCoor, Coordinate nextCoor) {
        this.id = id;
        this.currentCoor = currentCoor;
        this.nextCoor = nextCoor;
    }
    Zombie(int id, int currentX, int currentY, int nextX, int nextY, int targetX, int targetY) {
        this.id = id;
        this.currentCoor = new Coordinate(currentX, currentY);
        this.nextCoor = new Coordinate(nextX, nextY);
        getDistance(new Coordinate(targetX, targetY));
    }
    private void getDistance(Coordinate targetCoordinate) {
        double powX = Math.pow(currentCoor.getX() - targetCoordinate.getX(), 2);
        double powY = Math.pow(currentCoor.getY() - targetCoordinate.getY(), 2);
        this.distance = (int)Math.sqrt(powX + powY);
    }
    private int targetOnHuman(List<Human> humans) {
        int id = -1;
        int currentX = currentCoor.getX();
        int currentY = currentCoor.getY();
        for (Human human : humans) {
            int humanX = human.getCoordinate().getX();
            int humanY = human.getCoordinate().getY();
            if (currentX * humanY == currentY * humanX
            && ((humanX > nextCoor.getX() && nextCoor.getX() > currentX) || (currentX > nextCoor.getX() && nextCoor.getX() > humanX))) {
                return human.getId();
            }
        }
        return id;
    }
    @Override
    public int compareTo(Zombie other) {
        return this.distance - other.distance;
    }
    public int getId() {return this.id;}
    public Coordinate getCurrentCoor() {return this.currentCoor;}
    public Coordinate getNextCoor() {return this.nextCoor;}
    public void setId(int id) {this.id = id;}
    public void setCurrentCoor(Coordinate coordinate) {this.currentCoor = coordinate;}
    public void setNextCoor(Coordinate coordinate) {this.nextCoor = coordinate;}
}

class Coordinate {
    int x;
    int y;
    Coordinate() {}
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
}