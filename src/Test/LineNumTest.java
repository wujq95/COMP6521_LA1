package Test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineNumTest {

    String input = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/generator4.txt";
    String output = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/output.txt";
    @Test
    public void lineNumTest() throws IOException {
        int sum = 0;
        FileReader fr  = new FileReader(output);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line=br.readLine())!=null){
            sum++;
        }
        System.out.println(sum);
        br.close();
        fr.close();
    }
}
