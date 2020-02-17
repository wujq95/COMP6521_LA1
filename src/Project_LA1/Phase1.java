package Project_LA1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Phase1 {

    public static int timeFlag = 0;
    public static int fileNum = 1;

    /**
     * start the phase1 for sorting the data（store data in different files）
     */
    public void start2(long totalMemory) throws IOException {

        FileReader fr = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br = new BufferedReader(fr);
        Phase1 phase = new Phase1();
        Sort sort  = new Sort();

        //内存大小
        Runtime rt = Runtime.getRuntime();
        long freeMemory = rt.freeMemory();


        //add the data into the sublist
        String line = "";
        int sublist_size = (int) (totalMemory / Configuration.TUPLE_SIZE) / 4;
        List<String> subList = new ArrayList<>();
        while((line = br.readLine())!=null){
            subList.add(line);
            if(subList.size()==sublist_size){
                sort.quickSort(subList,0,subList.size()-1);
                String addStr = Configuration.TEMP_PATH+fileNum+".txt";
                phase.OutputDiffFiles(subList,addStr);
                subList = new ArrayList<>();
                fileNum++;
            }
        }
        if(subList.size()>0){
            sort.quickSort(subList,0,subList.size()-1);
            String addStr = Configuration.TEMP_PATH+fileNum+".txt";
            phase.OutputDiffFiles(subList,addStr);
            fileNum++;
            /*if(timeFlag==0){
                FileWriter fwl  = new FileWriter(Configuration.TEXT2_PATH,true);
                PrintWriter pwl = new PrintWriter(fwl);
                for(String str:subList){
                    pwl.println(str);
                }
                pwl.close();
                fwl.close();
            }else{
                sort.quickSort(subList,0,subList.size()-1);
                String addStr = Configuration.TEMP_PATH+fileNum+".txt";
                phase.OutputDiffFiles(subList,addStr);
            }*/
        }
        br.close();
        fr.close();
    }

    /**
     *output sublists in different files
     * @param subList
     * @param address
     */
    public void OutputDiffFiles(List<String> subList,String address) throws IOException {
        FileWriter fw = new FileWriter(address);
        PrintWriter pw = new PrintWriter(fw);
        for(String str:subList){
            pw.println(str);
        }
        pw.close();
        fw.close();
    }

    /**
     * get line number by the file address
     * @param str
     * @return
     * @throws IOException
     */
    public int getLineNum(String str)  throws IOException {
        int sum = 0;
        FileReader fr  = new FileReader(str);
        BufferedReader br = new BufferedReader(fr);
        while((br.readLine())!=null){
            sum++;
        }
        br.close();
        fr.close();
        return sum;
    }
}
