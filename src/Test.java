
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Date;
import Tree.*;
import Tree.Tree.Node;

public class Test {

    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();
        int nodes = 0;
        
        /* Hele denne try/catch-regla henter ut informasjon fra fila */
        try {
            String kantfil = "src/kanter.txt";
            FileReader fileReader = new FileReader(new File(kantfil));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            /* Noder */
            nodes = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = 0; i < nodes; i++) {
                tree.addNode(i);
            }
            System.out.println("Noder: " + nodes);

            /* Kanter */
            int edges = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println("Kanter: " + edges);
            for (int i = 0; i < edges; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                tree.addEdge(
                        Integer.toString(i),
                        tree.getNode(stringTokenizer.nextToken()),
                        tree.getNode(stringTokenizer.nextToken()),
                        Integer.parseInt(stringTokenizer.nextToken()));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("*** " + e + " **");
        }
        /* ----------------------------------------------------------------------------- */

        System.out.println("");
        
        for (int i = 0; i < nodes; i++) {
            MinSpenntre a = new MinSpenntre(tree);
            Node source = tree.getNode(Integer.toString(i));
            System.out.println("Node: " + a.execute(source));
            System.out.println("Vekt: " + a.getWeightList() + " = " + a.getTotalWeight());
            System.out.println("");
        }
    }
}