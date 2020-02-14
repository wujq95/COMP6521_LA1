package Project_LA1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    /**
     * entry point
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //问题：
        //1、block有没有用
        //2、内存占满之后如何处理其他的东西

        Phase1 phase1 = new Phase1();
        Date date1 = new Date();
        //clear the file
        FileWriter fileWriter  = new FileWriter(Configuration.OUTPUT_PATH);
        fileWriter.write("");
        fileWriter.close();
        phase1.start();
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        Phase1.timeFlag=1;
        phase1.start();
        Date date2 = new Date();
        System.out.println(date2.getTime()-date1.getTime());
    }
}
