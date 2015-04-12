import java.io.*;
import java.util.*;

public class Opgave2
{
  // ##################################################
  // # You do not need to modify anything below here. #
  // ##################################################

  public static void main(String[] args) throws IOException {
    new Opgave2().run();
  }

  private void run() throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    String line = in.readLine();
    String[] names = line.split("\\s");
    int[][] graph = new int[names.length][names.length];

    while ( (line = in.readLine()) != null ){
      String[] tokens = line.split("\\s");
      if (tokens[0].equals("taetvenskab")){
        int[] checklist = new int[tokens.length-1];
        for (int i = 1; i < tokens.length; i++){
          checklist[i-1] = Integer.parseInt(tokens[i]);
        }
        for (int i = 0; i < checklist.length; i++){
          for (int j = i+1; j < checklist.length; j++){
            if (graph[checklist[i]][checklist[j]] == 0){
              System.out.println("nej");
              System.exit(0);
            }
          }
        }
        System.out.println("ja");
      } else {
        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[1]);
        graph[a][b] = 1;
        graph[b][a] = 1;
      }
    }

  }
}