package Project_LA1;

public class Configuration {

    //configure the size of block, memory and others
    public final static int K = 1000;
    public final static int M = 1000*K;
    public final static int TUPLE_SIZE = 100;
    public final static int BLOCK_SIZE = 4000;
    public final static int MEMORY_SIZE = 10 * M;
    public final static int BLOCK_NUM = BLOCK_SIZE/TUPLE_SIZE;  //1个block放n个tuple 40
    public final static int ONCE_DEAL_NUM = MEMORY_SIZE%BLOCK_SIZE==0?MEMORY_SIZE/BLOCK_SIZE:MEMORY_SIZE/BLOCK_SIZE+1;
    public final static int TUPLE_NUM = BLOCK_NUM*ONCE_DEAL_NUM;  //memory能放n个tuple     m/t
    public static int sumNum = 0;
    public static int timeNum = 0;

    //configure the input and output file path
    public static String TEXT1_PATH = "src/Data_Files/sample1.txt";
    public static String TEXT2_PATH = "src/Data_Files/sample2.txt";
    public static String TEMP_PATH = "src/Data_Files/Temp/phase2_original";
    public static String TEMP_CONTENT = "src/Data_Files/Temp";
    public static String OUTPUT_PATH = "src/Data_Files/phase2_original.txt";
    public static String PHASE2_ORIGINAL = "src/Data_Files/phase2_original.txt";
    public static String PHASE2_OUTPUT = "src/Data_Files/phase2_output.txt";
    
}
