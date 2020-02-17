package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompareFile {


    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/Data_Files/phase2_output.txt");
        FileReader fr2 = new FileReader("src/Data_Files/phase2_output2.txt");
        BufferedReader br = new BufferedReader(fr);
        BufferedReader br2 = new BufferedReader(fr2);
        String line1 = br.readLine();
        String line2 = br2.readLine();

        int sum  = 0;
        while (line1.compareTo(line2)==0&&line1!=null&&line2!=null){
            line1 = br.readLine();
            line2 = br2.readLine();
            sum++;
        }

        System.out.println(sum);
        System.out.println(line1);
        System.out.println(line2);

        br.close();
        br2.close();
        fr.close();
        fr2.close();
    }
}
