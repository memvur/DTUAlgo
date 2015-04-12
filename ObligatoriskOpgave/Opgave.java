import java.io.*;
import java.util.*;

public class Opgave
{
  // ##################################################
  // # You do not need to modify anything below here. #
  // ##################################################


  public static void main(String[] args) throws IOException {
    new Opgave().run();
  }

  private void taet_venskab(String names, String[] relations, String gruppe_string){
    // convert group of people into a list of integers
    String[] venskabs_kaede = gruppe_string.split("\\s");
    int[] kaede = null;
    if( venskabs_kaede.length > 2 ){
      kaede = new int[venskabs_kaede.length-1];
      for (int i = 1; i < venskabs_kaede.length; i++){
        kaede[i-1] = Integer.parseInt(venskabs_kaede[i]);
      }
    } else if (venskabs_kaede.length == 2) {
      // A single persom is assumed to be friend
      // with him/her-self
      System.out.println("ja");
      return;
    } else {
      // No friend to check.
      // nej is assumed to be default
      System.out.println("nej");
      return;
    }

    // Build the graph with relations
    int n_pers = names.split("\\s").length;
    int[][] graph_matrix = new int[n_pers][n_pers];
    for (int i = 0; i < relations.length; i++){
      String[] tokens = relations[i].split("\\s");
      int a = Integer.parseInt(tokens[0]);
      int b = Integer.parseInt(tokens[1]);
      if (a <= n_pers && b <= n_pers){
        graph_matrix[a][b] = 1;
        graph_matrix[b][a] = 1;
      }
    }

    for (int i = 0; i < kaede.length-1; i++){
      for (int j = i+1; j < kaede.length; j++){
        if (kaede[i] <= n_pers && kaede[j] <= n_pers){
          if (graph_matrix[kaede[i]][kaede[j]] == 0){
            System.out.println("nej");
            return;
          }
        }
      }
    }
    System.out.println("ja");
    return;

  }

  private void calc_size(String names, String[] relations){
    String[] name_list = names.split("\\s");
    System.out.println(name_list.length + " " + relations.length);
  }


  private void run() throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    

    /*
      Read and prepae data
    */
    ArrayList<String> inputLines = new ArrayList<String>();
    String line = "";
    while ( (line = in.readLine()) != null ){
      inputLines.add(line);
    }
    String name_line = "";
    String[] relations = new String[] {};
    String opgave = "";
    if (inputLines.size() > 0){
      name_line = inputLines.get(0);
    }
    if (inputLines.size() > 2){
       relations = new String[inputLines.size()-2];
      for (int i = 1; i < inputLines.size()-1; i++){
        relations[i-1] = inputLines.get(i);
      }
    }
    opgave = inputLines.get(inputLines.size()-1);
    String[] opg_tokens = opgave.split("\\s");
    if (opg_tokens[0].equals("stoerrelse")){
      calc_size(name_line, relations);
    } else if (opg_tokens[0].equals("taetvenskab")){
      taet_venskab(name_line, relations, opgave);
    } 
    

  }
}