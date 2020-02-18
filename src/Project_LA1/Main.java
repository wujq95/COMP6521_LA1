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
        //问题：
        //1、block有没有用
        //2、内存占满之后如何处理其他的东西
        //3、可不可以第一个文档的剩余数据存储下来，然后第二次打开文档直接处理

        Phase1 phase1 = new Phase1();
        Phase2 phase2 = new Phase2();

        //内存大小
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        System.out.println ("total memory:"+ totalMemory/1024/1024+"M");


        Date date1 = new Date();
        //clear the file
        FileWriter fileWriter  = new FileWriter(Configuration.OUTPUT_PATH);
        fileWriter.write("");
        fileWriter.close();
        phase1.start2(totalMemory);
        Configuration.TEXT1_PATH = Configuration.TEXT2_PATH;
        Phase1.timeFlag=1;
        phase1.start2(totalMemory);
        Date date2 = new Date();
        long file_time1;
        file_time1 = date2.getTime()-date1.getTime();
        System.out.println("phase1 time:"+file_time1);
        //第二步
        Phase2 s = new Phase2();
        Date date3 = new Date();
        s.start(totalMemory);
        Date date4 = new Date();
        long file_time2;
        file_time2 = date4.getTime()-date3.getTime();
        System.out.println("phase2 time:"+ file_time2);

        //conclusion
        long total_time = file_time1 + file_time2;
        int lines = Get_Line();
        int block_num = Phase2.BLOCK_NUM;
        int io_time = Phase2.IO_TIME;
        Delete_Temp();


        System.out.println("total time:"+ total_time);
        System.out.println("total lines:"+ lines);
        System.out.println("total blocks:"+ block_num);
        System.out.println("io times:"+ io_time);
    }

    public static int Get_Line() throws IOException{
        FileReader fr = new FileReader(Configuration.PHASE2_OUTPUT);
        BufferedReader br = new BufferedReader(fr);
        int lines = 0;
        while (br.readLine() != null){
            lines += 1;
        }
        return lines;
    }
    public static long Get_Block() throws IOException{
        File f= new File(Configuration.PHASE2_OUTPUT);
        long file_size = f.length();
        long block_num = file_size / Configuration.BLOCK_SIZE;
        return block_num;
    }

    public static void Delete_Temp() throws  IOException{
        File file = new File(Configuration.TEMP_CONTENT);
        File[] content = file.listFiles();
        for (File temp : content){
            temp.delete();
        }
    }

}
