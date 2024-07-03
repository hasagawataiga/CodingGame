package Events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    static Map<Integer, List<Destinate>> ways;
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int E = in.nextInt();
        int S = in.nextInt();
        int G = in.nextInt();
        ways = new HashMap<>();
        int[][] values = new int[N][2];
        // for (int i = 0; i < N; i++) {
        //     Arrays.fill(values[i], Integer.MAX_VALUE);
        // }
        for (int i = 0; i < N; i++) {
            int node = in.nextInt();
            values[i][0] = node;
            values[i][1] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < E; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = in.nextInt();
            ways.putIfAbsent(x, new ArrayList<>());
            ways.putIfAbsent(y, new ArrayList<>());
            ways.get(x).add(new Destinate(y, c));
            ways.get(y).add(new Destinate(x, c));
        }
        printWays();
        // Find the g-value for all nodes
        BFS(ways, values, new boolean[N], S, G);
        // boolean[][] isChecked = new boolean[N][N];
        // for (Map.Entry<Integer, List<Destinate>> entry : ways.entrySet()) {
        //     List<Destinate> list = entry.getValue();
        //     for (Destinate destinate : list) {
        //         BFS(ways, values, isChecked, destinate.distance, 0, destinate.target);
        //     }
        // }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.err.println(Arrays.deepToString(values));
        for (int i = 0; i < N; i++) {
            System.out.println(i + " " + (values[i][0] + values[i][1]));
        }
    } 

    private static void BFS(Map<Integer, List<Destinate>> map, int[][] values, boolean[] visited, int startNode, int goalNode) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(startNode, 0));
        visited[startNode] = true;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (visited[currentNode.id]) {
                continue;
            } else {
                visited[currentNode.id] = true;
            }
            if (currentNode.id == goalNode) {
                break;
            }
            for (Destinate destinate : map.getOrDefault(currentNode.id, new ArrayList<>())) {
                if (visited[destinate.target]) {
                    continue;
                }
                int newDistance = destinate.distance + currentNode.gValue;
                if (newDistance < values[destinate.target][1]) {
                    values[destinate.target][1] = newDistance;
                    queue.offer(new Node(destinate.target, values[destinate.target][1]));
                }
            }
        }
    }

    private static void printWays() {
        for (Map.Entry<Integer, List<Destinate>> entry : ways.entrySet()) {
            Collections.sort(entry.getValue());
            System.err.print(entry.getKey() + ": ");
            entry.getValue().stream().forEach(System.err::print);
            System.err.println();
        }
    }
}
class Node implements Comparable<Node>{
    int id;
    int gValue;
    Node(int id, int gValue) {
        this.id = id;
        this.gValue = gValue;
    }
    @Override
    public int compareTo(Node node) {
        return this.gValue - node.gValue;
    }
}
class Destinate implements Comparable<Destinate>{
    int target;
    int distance;
    Destinate(int target, int distance) {
        this.target = target;
        this.distance = distance;
    }
    @Override
    public int compareTo(Destinate destinate) {
        return this.target - destinate.target;
    }
    @Override
    public String toString() {
        return target + "-" + distance + ", ";
    }
}