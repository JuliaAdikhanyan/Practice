public class Main {

    public static void main(String[] args) {

        Graph ex = new Graph("D:\\example.xlsx", false);
        // directive == true -> (1 -> 20) != (20 -> 1)
        // directive == false -> (1 -> 20) == (20 -> 1)
        ex.findPath(1, 18);
    }
}