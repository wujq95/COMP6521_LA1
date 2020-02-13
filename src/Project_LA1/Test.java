package Project_LA1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        int sum = 0;
        FileReader fr2 = new FileReader(Configuration.OUTPUT_PATH);
        BufferedReader br2 = new BufferedReader(fr2);
        while((br2.readLine())!=null){
            sum++;
        }
        System.out.println(sum);
    }
}
