import java.io.*;
import java.util.*;

public class Opgave
{

  public static void main(String[] args) throws IOException {
    new Opgave().run();
  }

  private void svagestevenskab(String name_line, String[] relations, int p1, int p2){
    String[] personer = name_line.split("\\s");

    int n_pers = personer.length;

    PriorityQueue<Integer> P = new PriorityQueue<Integer>();
    int[] pi_list = new int[n_pers];
    int[] d_list  = new int[n_pers];
    boolean taken[] = new boolean[n_pers];

    ArrayList< ArrayList<Integer> > graph_list = new ArrayList< ArrayList<Integer> >(n_pers);
    for (int i = 0; i < n_pers; i++) {
      graph_list.add(new ArrayList<Integer>());
      P.insert(i, Integer.MAX_VALUE);
      d_list[i] = Integer.MAX_VALUE;
      pi_list[i] = -1; // for 'NULL'
    }
    for (int i = 0; i < relations.length; i++){
      String[] tokens = relations[i].split("\\s");
      int a = Integer.parseInt(tokens[0]);
      int b = Integer.parseInt(tokens[1]);
      int w = Integer.parseInt(tokens[2]);
      if (a <= n_pers && b <= n_pers){
        graph_list.get(a).add(b);
        graph_list.get(a).add(w);
        graph_list.get(b).add(a);
        graph_list.get(b).add(w);
      }
    }

    P.changeKey(p1, 0);
    d_list[p1] = 0;

    while (!P.isEmpty()){
      int u = P.extractMin();
      taken[u] = true; 
      for(int i = 0; (i*2)+1 < graph_list.get(u).size(); i++){
        // Relaxing
        int v = graph_list.get(u).get(i*2);
        if ( d_list[v] > d_list[u] + (graph_list.get(u).get((i*2)+1))){
          d_list[v] = d_list[u] + (graph_list.get(u).get((i*2)+1));
          pi_list[v] = u;
          if (!taken[v]){
            P.changeKey(v, d_list[v]);
          }
        }
      }
    }

    ArrayList<Integer> path = new ArrayList<Integer>();
    path.add(p2);
    int next_node;
    while ( (next_node = pi_list[path.get(path.size()-1)]) != -1 ){
      path.add(pi_list[path.get(path.size()-1)]);
    }
    for(int i = path.size()-1; i > 0; i--){
      System.out.print(personer[path.get(i)] + " ");
    }
    System.out.print(personer[path.get(0)] + "\n");

  }


  private void tvenner(String name_line, String[] relations, int ini, int p){
    String[] personer = name_line.split("\\s");

    int n_pers = personer.length;
    ArrayList< ArrayList<Integer> > graph_list = new ArrayList< ArrayList<Integer> >(n_pers);
    for (int i = 0; i < n_pers; i++) {
      graph_list.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < relations.length; i++){
      String[] tokens = relations[i].split("\\s");
      int a = Integer.parseInt(tokens[0]);
      int b = Integer.parseInt(tokens[1]);
      if (a <= n_pers && b <= n_pers){
        graph_list.get(a).add(b);
        graph_list.get(b).add(a);
      }
    }

    boolean[] visited = new boolean[personer.length]; 
    ArrayList<Integer> kaede = new ArrayList<Integer>();
    ArrayList<Integer> next  = new ArrayList<Integer>();
    next.add(ini);
    for (int i = 0; i <= p && next.size() > 0; i++){
      ArrayList<Integer> tmp_next = new ArrayList<Integer>();
      for (int j = 0; j < next.size(); j++){
        if (!visited[next.get(j)]) {
          kaede.add(next.get(j));
          visited[next.get(j)] = true;
          for(int k = 0; k < graph_list.get(next.get(j)).size(); k++){
            tmp_next.add(graph_list.get(next.get(j)).get(k));
          }
        }
      }
      next = tmp_next;
    }

    for (int i = 0; i < kaede.size(); i++){
      String appended = i < kaede.size() - 1 ? " " : "\n";
      System.out.print( personer[kaede.get(i)] + appended);
      
    }

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

    int n_pers = names.split("\\s").length;
    ArrayList< ArrayList<Integer> > graph_list = new ArrayList< ArrayList<Integer> >(n_pers);
    for (int i = 0; i < n_pers; i++) {
      graph_list.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < relations.length; i++){
      String[] tokens = relations[i].split("\\s");
      int a = Integer.parseInt(tokens[0]);
      int b = Integer.parseInt(tokens[1]);
      if (a <= n_pers && b <= n_pers){
        graph_list.get(a).add(b);
        graph_list.get(b).add(a);
      }
    }

    // Actual algorithm
    for (int i = 0; i < kaede.length-1; i++){
      for (int j = i+1; j < kaede.length; j++){
        if (kaede[i] <= n_pers && kaede[j] <= n_pers){
          if (!graph_list.get(kaede[i]).contains(kaede[j])){
            System.out.println("nej");
            return;
          }
        }
      }
    }
    System.out.println("ja");
    return;
    // End of algorithm

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
    } else if (opg_tokens[0].equals("tvenner")){
      int i = Integer.parseInt(opg_tokens[1]);
      int p = Integer.parseInt(opg_tokens[2]);
      tvenner(name_line, relations, i, p);
    } else if (opg_tokens[0].equals("svagestevenskab")){
      int p1 = Integer.parseInt(opg_tokens[1]);
      int p2 = Integer.parseInt(opg_tokens[2]);
      svagestevenskab(name_line, relations, p1, p2);
    } 
    

  }


}








