package Project_LA1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Phase1 {

    /**
     * start the phase1 for sorting the data
     */
    public void start() throws IOException {

        FileReader fr = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br = new BufferedReader(fr);
        Phase1 phase = new Phase1();
        Sort sort  = new Sort();

        //calculate the size of the file
        int sum = 0;
        FileReader fr2 = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br2 = new BufferedReader(fr2);
        while((br2.readLine())!=null){
            sum++;
        }

        //calculate the memory dealing times according to the size of block and memory
        int blockNum = Configuration.BLOCK_SIZE/Configuration.TUPLE_SIZE;
        int onceDealNum = Configuration.Memory_SIZE%Configuration.BLOCK_SIZE==0?Configuration.Memory_SIZE/Configuration.BLOCK_SIZE:Configuration.Memory_SIZE/Configuration.BLOCK_SIZE+1;
        int times = sum%onceDealNum==0?sum/onceDealNum:sum/onceDealNum+1;


        //clear the file
        FileWriter fileWriter  = new FileWriter(Configuration.OUTPUT_PATH);
        fileWriter.write("");

        //add the data into the sublist
        String line = "";
        for(int i=0;i<times;i++){
            List<String> subList = new ArrayList<>();
            for(int j=0;j<onceDealNum;j++){
                if((line = br.readLine())!=null){
                    subList.add(line);
                }
            }
            sort.sortData(subList);
            phase.OutputFile(subList);
        }
        br.close();
        fr.close();
    }

    /**
     * output the sorted data
     * @param subList
     * @throws IOException
     */
    public void OutputFile(List<String> subList) throws IOException {
        FileWriter fw  = new FileWriter(Configuration.OUTPUT_PATH,true);
        PrintWriter pw = new PrintWriter(fw);
        //output every element in the sublist
        for(String str:subList){
            pw.println(str);
            pw.flush();
        }
        int t=10;
        while(t>0){
            pw.println("---------");
            t--;
        }
        pw.close();
        fw.close();
    }
}
