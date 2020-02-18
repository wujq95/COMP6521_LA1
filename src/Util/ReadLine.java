package Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadLine {

    /**
     * check the number of lines of file and check duplication
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int num = 0;
        for(int i=1;i<23;i++){
            String path = "src/Data_Files/Temp/phase2_original"+i+".txt";
            FileReader fw = new FileReader(path);
            BufferedReader br = new BufferedReader(fw);
            String line;
            String lline = "000000000";
            boolean flag = false;
            while((line=br.readLine())!=null){
                if(lline.compareTo(line)>0){
                    flag=true;
                }
                lline = line;
                num++;
            }
            System.out.println(flag);
        }
        System.out.println(num);
    }
}
