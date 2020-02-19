package Project_LA1;

import java.io.*;
import java.util.Date;

public class Main {
    /**
     * entry point
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Phase1 phase1 = new Phase1();
        Phase2 phase2 = new Phase2();

        //get memory size
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        System.out.println ("total memory:"+ totalMemory/1024/1024+"M");

        Date date1 = new Date();
        //step1
        phase1.start(totalMemory);
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        Phase1.timeFlag=1;
        phase1.start(totalMemory);
        Date date2 = new Date();
        long fileTime1;
        fileTime1 = date2.getTime()-date1.getTime();
        System.out.println("phase1 time:"+fileTime1);

        //step2
        Date date3 = new Date();
        phase2.start(totalMemory);
        Date date4 = new Date();
        long fileTime2;
        fileTime2 = date4.getTime()-date3.getTime();
        System.out.println("phase2 time:"+ fileTime2);

        //conclusion
        long totalTime = fileTime1 + fileTime2;
        int lines = getLineNum();
        int blockNum = Phase1.blockNum;
        deleteTemp();

        System.out.println("total time:"+ totalTime);
        System.out.println("total lines:"+ lines);
        System.out.println("total blocks:"+ blockNum);
        System.out.println("io times:"+ blockNum*4);
    }

    /**
     * get line num
     * @return
     * @throws IOException
     */
    public static int getLineNum() throws IOException{
        FileReader fr = new FileReader(Configuration.PHASE2_OUTPUT);
        BufferedReader br = new BufferedReader(fr);
        int lines = 0;
        while (br.readLine() != null){
            lines += 1;
        }
        return lines;
    }

    /**
     * delete useless files
     * @throws IOException
     */
    public static void deleteTemp() throws  IOException{
        File file = new File(Configuration.TEMP_CONTENT);
        File[] content = file.listFiles();
        for (File temp : content){
            temp.delete();
        }
    }
}
