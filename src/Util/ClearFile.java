package Util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ClearFile {

    /**
     * clear file
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileWriter fw  = new FileWriter("src/Data_Files/Temp/phase2_original1.txt");
        PrintWriter pw=  new PrintWriter(fw);
        pw.print("");
        pw.close();
        fw.close();
    }
}
