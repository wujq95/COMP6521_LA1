package Project_LA1;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Phase1 {

    static int timeFlag = 0;

    /**
     * start the phase1 for sorting the data
     */
    public void start() throws IOException {

        FileReader fr = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br = new BufferedReader(fr);
        Phase1 phase = new Phase1();
        Sort sort  = new Sort();

        //calculate the size of the file
        //need to be optimized(if memory is full then deal with the data and clear the buffer, then deal with new data)
        int sum = 0;
        FileReader fr2 = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br2 = new BufferedReader(fr2);
        while((br2.readLine())!=null){
            sum++;
        }
        Configuration.sumNum = sum;
        fr2.close();
        int times = sum%Configuration.TUPLE_NUM==0?sum/Configuration.TUPLE_NUM:sum/Configuration.TUPLE_NUM+1;
        Configuration.timeNum = times;

        //add the data into the sublist
        String line = "";
        for(int i=0;i<times;i++){
            List<String> subList = new ArrayList<>();
            for(int j=0;j<Configuration.TUPLE_NUM;j++){
                if((line = br.readLine())!=null){
                    subList.add(line);
                }else{
                    break;
                }
            }
            if(subList.size()==Configuration.TUPLE_NUM||timeFlag!=0){
                sort.quickSort(subList,0,subList.size()-1);
                phase.OutputFile(subList);
            }else{
                FileWriter fwl  = new FileWriter(Configuration.TEXT2_PATH,true);
                PrintWriter pwl = new PrintWriter(fwl);
                for(String str:subList){
                    pwl.println(str);
                    pwl.flush();
                }
                pwl.close();
                fwl.close();
            }
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
        pw.close();
        fw.close();
    }
}
