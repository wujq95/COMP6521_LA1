package Test;

import Project_LA1.Configuration;
import Project_LA1.Main;
import Project_LA1.Phase1;
import Project_LA1.Phase2;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class NotRepNumTest {

    /**
     * get the number of not repeated lines
     * @throws IOException
     */
    @Test
    public void notRepNumTest() throws IOException {
        Phase1 phase1 = new Phase1();
        Phase2 phase2 = new Phase2();
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();

        String address = Configuration.TEXT1_PATH;

        phase1.start(totalMemory);
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        Phase1.timeFlag=1;
        phase1.start(totalMemory);
        phase2.start(totalMemory);
        Configuration.TEXT1_PATH = address;

        HashSet<String> set = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        getSetSize(Configuration.TEXT1_PATH,set);
        getSetSize(Configuration.TEXT2_PATH,set);
        getSetSize(Configuration.PHASE2_OUTPUT,res);
        System.out.println(set.size());
        Assert.assertEquals(set.size(),res.size());
    }

    /**
     * put not repeated lines in the set
     * @param path
     * @return
     * @throws IOException
     */
    public static void getSetSize(String path, HashSet<String> set) throws IOException {
        FileReader fr1 = new FileReader(path);
        BufferedReader br1  = new BufferedReader(fr1);
        String line;
        while((line= br1.readLine())!=null){
            set.add(line.substring(0,8));
        }
        br1.close();
        fr1.close();
    }
}
