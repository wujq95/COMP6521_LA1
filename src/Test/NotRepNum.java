package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class NotRepNum {

    public static void main(String[] args) throws IOException {
        HashSet<String> set = new HashSet<>();
        String path1 = "src/Data_Files/sample1.txt";
        String path2 = "src/Data_Files/sample2.txt";
        String path3 = "src/Data_Files/phase2_output.txt";

        FileReader fr1 = new FileReader(path1);
        BufferedReader br1  = new BufferedReader(fr1);
        String line = "";
        while((line= br1.readLine())!=null){
            set.add(line.substring(0,8));
        }
        br1.close();
        fr1.close();
        FileReader fr2 = new FileReader(path2);
        BufferedReader br2  = new BufferedReader(fr2);
        while((line= br2.readLine())!=null){
            set.add(line.substring(0,8));
        }
        br2.close();
        fr2.close();
        System.out.println(set.size());

        HashSet<String> set3 = new HashSet<>();
        FileReader fr3 = new FileReader(path3);
        BufferedReader br3  = new BufferedReader(fr3);
        String line3 = "";
        while((line3= br3.readLine())!=null){
            set3.add(line3.substring(0,8));
        }
        br1.close();
        fr1.close();

        System.out.println(set3.size());
        assert set.size() == set3.size();
    }
}
