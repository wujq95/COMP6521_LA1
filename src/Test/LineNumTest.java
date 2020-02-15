package Test;

import Project_LA1.Configuration;
import Project_LA1.Phase1;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LineNumTest {

    String input1 = Configuration.TEXT1_PATH;
    String input2 = Configuration.TEXT2_PATH;
    String output = Configuration.OUTPUT_PATH;

    /**
     * check two file write process successfully
     * @throws IOException
     */
    @Test
    public void lineNumTest() throws IOException {
        int input1Num = getLineNum(input1);
        int input2Num = getLineNum(input2);

        Phase1 phase1 = new Phase1();
        FileWriter fileWriter  = new FileWriter(Configuration.OUTPUT_PATH);
        fileWriter.write("");
        fileWriter.close();
        phase1.start();
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        Phase1.timeFlag=1;
        phase1.start();
        int outputNum = getLineNum(output);
        Assert.assertEquals(outputNum,input1Num+input2Num);
    }

    /**
     * get line number by the file adress
     * @param str
     * @return
     * @throws IOException
     */
    public int getLineNum(String str)  throws IOException {
        int sum = 0;
        FileReader fr  = new FileReader(str);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line=br.readLine())!=null){
            sum++;
        }
        br.close();
        fr.close();
        return sum;
    }
}
