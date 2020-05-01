package Project_LA1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phase2Operation {

    /**
     * get file address
     * @param subListsNum
     * @return
     * @throws IOException
     */
    public  String[] fileAddress(int subListsNum) throws IOException{
        String[] fileAddress = new String[subListsNum];
        for (int i=1;i<=subListsNum;i++){
            fileAddress[i-1] = Configuration.TEMP_PATH + i + ".txt";
        }
        return  fileAddress;
    }

    /**
     * init bufferReader
     * @param subListsNum
     * @param fileAddress
     * @return
     * @throws IOException
     */
    public BufferedReader[] bufferInit(int subListsNum,String[] fileAddress) throws IOException{
        BufferedReader[] brInit = new BufferedReader[subListsNum];
        for (int i=0;i<subListsNum;i++){
            FileReader fr = new FileReader(fileAddress[i]);
            BufferedReader br = new BufferedReader(fr);
            brInit[i] = br;
        }
        return brInit;
    }

    /**
     * init memorySubListsList
     * @param subListsNum
     * @return
     * @throws IOException
     */
    public List <List<String>> init(int subListsNum) throws IOException{
        List <List<String>> memorySubListsList = new ArrayList<>();
        List<String> initArr = new ArrayList<>();
        for (int i=0; i<subListsNum; i++){
            memorySubListsList.add(initArr);
        }
        return memorySubListsList;
    }

    /**
     * read new sublist from disk to memory and compute the position of br pointer
     * @param fileAddress
     * @param index
     * @param brPointer
     * @param fetchNum
     * @return
     * @throws IOException
     */
    public Map<List<String>,BufferedReader[]> fetchSublist(String[] fileAddress, int index, BufferedReader[] brPointer, int fetchNum) throws IOException {
        Map<List<String>,BufferedReader[]> map= new HashMap<>();
        List<String> fetchSubList = new ArrayList<>();
        BufferedReader br = brPointer[index];
        String txt = "";
        for (int lines = 0; lines< fetchNum; lines++){
            txt = br.readLine();
            if (txt == null){
                br.close();
                fetchSubList.add("99999999");
                break;
            }
            else{
                fetchSubList.add(txt);
            }

        }
        brPointer[index] = br;
        map.put(fetchSubList,brPointer);
        return  map;
    }

    /**
     * compute the max sublist index
     * @param firstLine
     * @param subListsNum
     * @return
     * @throws IOException
     */
    public int maxIndex (List<String> firstLine,int subListsNum) throws IOException {
        int maxIndex = 0;
        int[] myList = new int[subListsNum];
        for (int i=0; i<subListsNum;i++){
            myList[i] = Integer.parseInt(firstLine.get(i).substring(0,8));
        }
        int num = myList[0];
        for (int i = 1; i < myList.length; i++) {
            if (myList[i] < num) {
                num = myList[i];
                maxIndex = i;

            }
        }
        if(num == 99999999){
            return -1;
        }
        return maxIndex;
    }

    /**
     * remove duplicate and add new line to buffer list then save it to disk
     * @param bufferList
     * @param maxLine
     * @param memorySubListsSize
     * @return
     * @throws IOException
     */
    public List <String> bufferProcess(List <String> bufferList,String maxLine,int memorySubListsSize) throws IOException{
        Phase2Operation po = new Phase2Operation();
        int bufferSize1 = bufferList.size();
        int bufferSize2;

        if (bufferSize1==0){
            bufferList.add(maxLine);
        }
        else if (bufferSize1 <= memorySubListsSize){
            String currLine = bufferList.get(bufferSize1-1);
            if (maxLine.substring(0,8).equals(currLine.substring(0,8))){
                if (maxLine.substring(8,18).compareTo(currLine.substring(8,18)) > 0 ){
                    bufferList.set(bufferSize1-1,maxLine);
                }
            }
            else {
                bufferList.add(maxLine);
            }
        }
        bufferSize2 = bufferList.size();
        if (bufferSize2 == memorySubListsSize + 1){
            String lastLine = bufferList.get(bufferSize2-1);
            bufferList.remove(bufferSize2-1);
            po.outputFile(bufferList);

            bufferList = new ArrayList<String>();
            bufferList.add(lastLine);
        }
        return bufferList;
    }

    /**
     * save sublist to disk
     * @param subList
     * @throws IOException
     */
    public void outputFile(List<String> subList) throws IOException {
        FileWriter fw  = new FileWriter(Configuration.PHASE2_OUTPUT,true);
        PrintWriter pw = new PrintWriter(fw);
        //output every element in the sublist
        for(String str:subList){
            pw.println(str);
        }
        pw.close();
        fw.close();
    }

    /**
     * init output file
     * @throws IOException
     */
    public void fileInit() throws IOException {
        FileWriter fw  = new FileWriter(Configuration.PHASE2_OUTPUT);
        PrintWriter pw = new PrintWriter(fw);
        //pw.println("");
        pw.close();
        fw.close();
    }

    /**
     * compute the size of every sublist
     * @return
     * @throws IOException
     */
    public int sublistSize() throws IOException {
        String fileAddress = Configuration.TEMP_PATH+"1.txt";
        FileReader fr = new FileReader(fileAddress);
        BufferedReader br = new BufferedReader(fr);
        int lines = 0;
        while (br.readLine() != null){
            lines += 1;
        }
        return lines;
    }
}

















