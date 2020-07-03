import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<Pair<Integer, Integer>>> edges;
    private static final int INF = 2147483647;

    private void addEdge(int v, Pair<Integer, Integer> edge){
        if(edges.size() < Math.max(v, edge.getFirst())){
            for (int i = 0; i < Math.max(v, edge.getFirst()) - edges.size() + 3; i++){
                edges.add(new ArrayList<Pair<Integer, Integer>>());
                //System.out.println(v + " - " + edge.getFirst());
                //System.out.println(edges.size());
            }
        }
        edges.get(v).add(edge);
        //System.out.println(edges.size());
    }

    public Graph(String pathToExcel){
        this.edges = new ArrayList<>();
        ArrayList<String> info = ExcelParser.getInfo(pathToExcel);
        for (int i = 0; i < info.size(); i++){
            int firstV = Integer.parseInt(info.get(i)) - 1;
            i++;
            int secondV = Integer.parseInt(info.get(i)) - 1;
            i++;
            int coast = Integer.parseInt(info.get(i));
            this.addEdge(firstV, new Pair<Integer, Integer>(secondV,coast));
            //System.out.println(firstV + " " + secondV + " " + coast);
        }
    }

    public void findPath(int firstV, int secondV){
        firstV--; // к индексам
        secondV--;

        int n = edges.size();

        int[] coasts = new int[n];
        int[] parents = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            coasts[i] = INF;
            used[i] = false;
        }
        coasts[firstV] = 0;

        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (v == -1 || coasts[j] < coasts[v])){ // из неиспользованных выб самую деш
                    v = j;
                }
            }
            if (coasts[v] == INF) {
                break;
            }
            used[v] = true;
            for (int j = 0; j < edges.get(v).size(); j++) {
                int to = edges.get(v).get(j).getFirst(); // релаксация
                int coast = edges.get(v).get(j).getSecond();
                if (coasts[v] + coast < coasts[to]) {
                    coasts[to] = coasts[v] + coast;
                    parents[to] = v;
                }
            }
        }
        if (coasts[secondV] == INF) {
            System.out.println("Can't find path");
        } else {
            System.out.println("Coast between " + (firstV + 1) +
                    " " + (secondV + 1) + " is " + coasts[secondV]);
            ArrayList<Integer> path = new ArrayList<>();
            for (int i = secondV; i != firstV; i = parents[i]) {
                path.add(i);
            }
            path.add(firstV);
            System.out.println("path: ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.println("  " + (path.get(i) + 1));
            }
        }
    }
}