package Util;

import Project_LA1.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CheckRepu {
    /**
     * check the file have continuous different lines
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String path = Configuration.PHASE2_OUTPUT;
        HashSet<String> set = new HashSet<>();
        FileReader fr = new FileReader(path);
        BufferedReader br  = new BufferedReader(fr);
        String line = "";
        while((line= br.readLine())!=null){
            if(set.contains(line)){
                System.out.println("99999");
            }else{
                set.add(line);
            }
        }
        br.close();
        fr.close();

    }
}
