package Project_LA1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Phase1 {

    public static int timeFlag = 0;
    public static int fileNum = 1;

    /**
     * start the phase1 for sorting the data
     */
    public void start() throws IOException {

        FileReader fr = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw  = new FileWriter(Configuration.OUTPUT_PATH,true);
        PrintWriter pw = new PrintWriter(fw);
        Phase1 phase = new Phase1();
        Sort sort  = new Sort();

        //add the data into the sublist
        String line = "";
        List<String> subList = new ArrayList<>();
        while((line = br.readLine())!=null){
            subList.add(line);
            if(subList.size()==Configuration.TUPLE_NUM / 2){
                sort.quickSort(subList,0,subList.size()-1);
                phase.OutputFile(subList,pw);
                subList = new ArrayList<>();
            }
        }
        if(subList.size()>0){
            if(timeFlag==0){
                FileWriter fwl  = new FileWriter(Configuration.TEXT2_PATH,true);
                PrintWriter pwl = new PrintWriter(fwl);
                for(String str:subList){
                    pwl.println(str);
                }
                pwl.close();
                fwl.close();
            }else{
                sort.quickSort(subList,0,subList.size()-1);
                phase.OutputFile(subList,pw);
            }
        }
        br.close();
        fr.close();
        pw.close();
        fw.close();
    }

    /**
     * start the phase1 for sorting the data（store data in different files）
     */
    public void start2() throws IOException {

        FileReader fr = new FileReader(Configuration.TEXT1_PATH);
        BufferedReader br = new BufferedReader(fr);
        Phase1 phase = new Phase1();
        Sort sort  = new Sort();


        //add the data into the sublist
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        int sublist_size = (int) totalMemory / Configuration.TUPLE_SIZE / 2;
        String line = "";
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
            if(timeFlag==0){
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
                //fileNum++;
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
    public void OutputFile(List<String> subList,PrintWriter pw) throws IOException {
        //output every element in the sublist
        for(String str:subList){
            pw.println(str);
        }
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
     * get line number by the file adress
     * @param str
     * @return
     * @throws IOException
     */
    public int getLineNum(String str)  throws IOException {
        int sum = 0;
        FileReader fr  = new FileReader(str);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line=br.readLine())!=null){
            sum++;
        }
        br.close();
        fr.close();
        return sum;
    }
}
