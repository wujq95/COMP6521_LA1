package Test;

import Project_LA1.Configuration;
import Project_LA1.DataGenerator;
import Project_LA1.Phase1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class FileTest {

    @Before
    public void before() throws IOException {
        FileWriter fileWriter  = new FileWriter(Configuration.OUTPUT_PATH);
        fileWriter.write("");
        fileWriter.close();
    }


    /**
     * file write test
     * @throws IOException
     */
    @Test
    public void fileWriteTest() throws IOException {
        DataGenerator dg = new DataGenerator();
        int NUM = 1000000;
        Assert.assertEquals(0,getLineNum(Configuration.OUTPUT_PATH));
        FileWriter fw = new FileWriter(Configuration.OUTPUT_PATH);
        PrintWriter pw = new PrintWriter(fw);
        dg.generate(NUM,Configuration.OUTPUT_PATH);
        pw.close();
        fw.close();
        Assert.assertEquals(NUM,getLineNum(Configuration.OUTPUT_PATH));
    }

    /**
     * check main function process successfully
     * @throws IOException
     */
    @Test
    public void mainFunctionTest() throws IOException {
        int input1Num = getLineNum(Configuration.TEXT1_PATH);
        int input2Num = getLineNum(Configuration.TEXT2_PATH);
        Phase1 phase1 = new Phase1();
        phase1.start();
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        Phase1.timeFlag=1;
        phase1.start();
        int outputNum = getLineNum(Configuration.OUTPUT_PATH);
        Assert.assertEquals(outputNum,input1Num+input2Num);
    }

    /**
     * get line number by the file address
     * @param str
     * @return
     * @throws IOException
     */
    public int getLineNum(String str)  throws IOException {
        int sum = 0;
        FileReader fr  = new FileReader(str);
        BufferedReader br = new BufferedReader(fr);
        while(br.readLine()!=null){
            sum++;
        }
        br.close();
        fr.close();
        return sum;
    }
}
