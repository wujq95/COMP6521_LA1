package Project_LA1;

public class Configuration {

    //configure the size of block, memory and others
    public final static int K = 1000;
    final static int M = 1000*K;
    final static int TUPLE_SIZE = 100;
    final static int BLOCK_SIZE = 4000;
    final static int MEMORY_SIZE = 10 * M;
    final static int BLOCK_NUM = BLOCK_SIZE/TUPLE_SIZE;
    final static int ONCE_DEAL_NUM = MEMORY_SIZE%BLOCK_SIZE==0?MEMORY_SIZE/BLOCK_SIZE:MEMORY_SIZE/BLOCK_SIZE+1;
    final static int TUPLENUM = BLOCK_NUM*ONCE_DEAL_NUM;
    static int sumNum = 0;
    static int timeNum = 0;

    //configure the input and output file path
    static String TEXT1_PATH = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/sample.txt";
    static String TEXT2_PATH = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/generator.txt";
    static String OUTPUT_PATH = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/output.txt";
}
