package Test;

import Project_LA1.Configuration;
import Util.DataGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class FileWriteTest {

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
