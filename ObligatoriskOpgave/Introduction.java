import java.io.*;
import java.util.*;

public class Introduction
{
  // ##################################################
  // # You do not need to modify anything below here. #
  // ##################################################

  public static void main(String[] args) throws IOException {
    new Introduction().run();
  }

  private void run() throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    int n_personer = in.readLine().split(" ").length;
    int n_venskaber = 0;

    String line = "";
    while( (line = in.readLine()) != null ){
      String[] tokens = line.split("\\s");
      if (tokens.length == 1){
        break;
      } else {
        n_venskaber++;
      }
    }
      


    System.out.println(n_personer + " " + n_venskaber);
  }
}