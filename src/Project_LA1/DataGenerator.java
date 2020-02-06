package Project_LA1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) throws IOException {
        String PATH = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/generator2.txt";
        FileWriter fwClear  = new FileWriter(PATH);
        fwClear.write("");
        fwClear.close();
        FileWriter fw  = new FileWriter(PATH,true);
        for(int i=0;i<50000;i++){
            String str = getRandomString(10);
            fw.write(str);
            fw.write("\n");
        }
        fw.close();
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
