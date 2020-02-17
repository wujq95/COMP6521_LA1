package Test;

import Project_LA1.Configuration;
import Project_LA1.DataGenerator;
import Project_LA1.Phase1;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataGeneratorTest {

    /**
     * data generator test
     * @throws IOException
     */
    @Test
    public void DataGeneratorTest() throws IOException {
        int NUM = 1000000;
        Phase1 phase1 = new Phase1();
        DataGenerator dg = new DataGenerator();
        FileWriter fileWriter  = new FileWriter(Configuration.TEXT1_PATH);
        fileWriter.write("");
        fileWriter.close();
        Assert.assertEquals(0,phase1.getLineNum(Configuration.TEXT1_PATH));
        dg.generate(NUM,Configuration.TEXT1_PATH);
        Assert.assertEquals(NUM,phase1.getLineNum(Configuration.TEXT1_PATH));
    }
}
