
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Date;
import Graph.*;
import Graph.Graph.Node;

public class Test {

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();

        /* Hele denne try/catch-regla henter ut informasjon fra filene */
        try {
            /* Noder */
            String nodefil = "src/noder.txt";
            FileReader fileReader = new FileReader(new File(nodefil));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken()); // Lese antall linjer fra et tall øverst i txt. Denne funker tydeligvis ikke med 400k linjer i en txt.. funka med 200k

            // Antall linjer i dokumentet
//            int i = 0;
//            while (bufferedReader.readLine() != null) {
//                i++;
//            }

//            fileReader = new FileReader(new File(nodefil));
//            bufferedReader = new BufferedReader(fileReader);
//            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < n; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                graph.addNode(stringTokenizer.nextToken());
            }

            /* Kanter */
            String kantfil = "src/kanter.txt";
            fileReader = new FileReader(new File(kantfil));
            bufferedReader = new BufferedReader(fileReader);
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            // Antall linjer i dokumentet
//            int k = 0;
//            while (bufferedReader.readLine() != null) {
//                k++;
//            }

//            fileReader = new FileReader(new File(kantfil));
//            bufferedReader = new BufferedReader(fileReader);
//            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int l = 0; l < k; l++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                graph.addEdge(
                        Integer.toString(l),
                        graph.getNode(stringTokenizer.nextToken()),
                        graph.getNode(stringTokenizer.nextToken()),
                        Integer.parseInt(stringTokenizer.nextToken()));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            // System.out.println("*** " + e + " **"); // Viser en nullpointer, dunno wai
        }
        /* ----------------------------------------------------------------------------- */
        
        /* INPUT */
        Node source = graph.getNode("0");
        if (source == null) {
            throw new NullPointerException("Finnes ingen node ");
        }

        MinSpenntre a = new MinSpenntre(graph);

        Date start = new Date();
        a.execute(source);  // EXECUTE
        Date stop = new Date();

        /* OUTPUT */
        //a.dump(); // Skriver ut alle nodene, don't do it if you don't mean it
        double time = stop.getTime() - start.getTime();
//        System.out.println("Gjennomsøkte noder: " + a.settledNodes.size());
//        System.out.println("Gjennomløpstid: " + time + " ms");
    }
}