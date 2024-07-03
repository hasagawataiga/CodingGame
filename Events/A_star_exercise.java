package Events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
        int[][] values = new int[N][2]; // [g-value, h-value]
        for (int i = 0; i < N; i++) {
            values[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < N; i++) {
            int hValue = in.nextInt();
            values[i][1] = hValue; // heuristic value
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

        // Use a priority queue to process nodes based on the smallest f-value first
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0, values[S][1])); // Start with the initial node
        values[S][0] = 0; // g-value of start node is 0

        boolean[] visited = new boolean[N];

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.id]) continue;
            visited[current.id] = true;

            System.out.println(current.id + " " + current.f);

            if (current.id == G) break; // Stop when the goal is reached

            for (Destinate neighbor : ways.getOrDefault(current.id, new ArrayList<>())) {
                if (visited[neighbor.target]) continue;

                int tentativeG = values[current.id][0] + neighbor.distance;
                if (tentativeG < values[neighbor.target][0]) {
                    values[neighbor.target][0] = tentativeG; // Update g-value
                    int fValue = tentativeG + values[neighbor.target][1]; // f = g + h
                    pq.add(new Node(neighbor.target, tentativeG, fValue));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int id;
    int g;
    int f;

    Node(int id, int g, int f) {
        this.id = id;
        this.g = g;
        this.f = f;
    }

	@Override
	public int compareTo(Node node) {
		return this.f - node.f;
	}
}

class Destinate {
    int target;
    int distance;
    
    Destinate(int target, int distance) {
        this.target = target;
        this.distance = distance;
    }
}