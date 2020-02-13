package Test;

import Project_LA1.Configuration;
import Project_LA1.Phase1;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
    /**
     * file write success test
     * @throws IOException
     */
    @Test
    public void fileWriteTest() throws IOException {
        fileClear();
        Phase1 phase1 = new Phase1();
        Assert.assertEquals(fileWrite(),0);
        phase1.start();
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        phase1.start();
        Assert.assertEquals(fileWrite(),1500000);
    }

    /**
     * write data in the file
     * @return
     * @throws IOException
     */
    public int fileWrite() throws IOException {
        int sum = 0;
        FileReader fr = new FileReader(Configuration.OUTPUT_PATH);
        BufferedReader br = new BufferedReader(fr);
        while((br.readLine())!=null){
            sum++;
        }
        br.close();
        fr.close();
        return sum;
    }

    /**
     * clear the tested file
     * @throws IOException
     */
    public void fileClear() throws IOException {
        FileWriter fileWriter = new FileWriter(Configuration.OUTPUT_PATH);
        fileWriter.write("");
        fileWriter.close();
    }
}
