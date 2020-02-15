package Project_LA1;

public class Configuration {

    //configure the size of block, memory and others
    public final static int K = 1000;
    final static int M = 1000*K;
    final static int TUPLE_SIZE = 100;
    final static int BLOCK_SIZE = 4000;
    final static int MEMORY_SIZE = 10 * M;
    final static int BLOCK_NUM = BLOCK_SIZE/TUPLE_SIZE;  //1个block放n个tuple 40
    final static int ONCE_DEAL_NUM = MEMORY_SIZE%BLOCK_SIZE==0?MEMORY_SIZE/BLOCK_SIZE:MEMORY_SIZE/BLOCK_SIZE+1;
    final static int TUPLE_NUM = BLOCK_NUM*ONCE_DEAL_NUM;  //memory能放n个tuple
    static int sumNum = 0;
    static int timeNum = 0;

    //configure the input and output file path
    static String TEXT1_PATH = "src/Data_Files/sample1.txt";
    static String TEXT2_PATH = "src/Data_Files/sample2.txt";
    static String TEMP_PATH = "src/Data_Files/Temp/phase2_original";
    static String OUTPUT_PATH = "src/Data_Files/phase2_original.txt";
    static String PHASE2_ORIGINAL = "src/Data_Files/phase2_original.txt";
    static String PHASE2_OUTPUT = "src/Data_Files/phase2_output.txt";
}
