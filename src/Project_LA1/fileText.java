package Project_LA1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileText {

    static int sum = 0;
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br = new BufferedReader(fr);
        while((br.readLine())!=null){
            sum++;
        }
    }
}
