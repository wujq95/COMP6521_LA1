package Project_LA1;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Phase2 {

    /**
     * phase2 start method
     * @param totalMemory
     * @throws IOException
     */
    public void start(long totalMemory) throws IOException{
        Phase2 phase2 = new Phase2();
        Phase2Operation po = new Phase2Operation();
        //init file
        po.fileInit();
        int[] param = phase2.config(totalMemory);

        //sublist number
        int subListsNum = param[0];
        //sublist size
        int subListsSize = param[1];
        // one block size
        int memorySubListsSize = param[2];

        //init file address
        String[] fileAddress = po.fileAddress(subListsNum);
        //init pointer
        BufferedReader[] brInit = po.bufferInit(subListsNum,fileAddress);

        phase2.duplicateInsert(subListsNum,memorySubListsSize,brInit);

    }


    /**
     * Remove duplicated data and insert new data
     * @param subListsNum
     * @param memorySubListsSize

     * @param brPointer
     * @throws IOException
     */
    public void duplicateInsert( int subListsNum, int memorySubListsSize, BufferedReader[] brPointer) throws IOException{
        Phase2Operation po = new Phase2Operation();
        //init n blocks in memory
        List <List<String>> memorySubListsList = po.init(subListsNum);
        //buffer list in memory
        List <String> bufferList = new ArrayList<>();
        List <String> firstLine = new ArrayList<>();

        while (true){
            //add data to memory
            for (int index=0;index<subListsNum;index++){
                if (memorySubListsList.get(index).size() == 0){
                    BufferedReader br = brPointer[index];
                    Pair<BufferedReader,List<String>> pair  =  po.fetchSublist(br,memorySubListsSize);
                    BufferedReader br2 =pair.getKey();
                    brPointer[index] = br2;
                    memorySubListsList.set(index,pair.getValue());
                }
            }

            // create the first line to compare
            firstLine = new ArrayList<>();
            for (int i=0;i<subListsNum;i++){
                firstLine.add(memorySubListsList.get(i).get(0));
            }

            //get the index of biggest value
            int maxIndex;
            maxIndex = po.maxIndex(firstLine,subListsNum);
            if (maxIndex == -1){
                break;
            }

            //get the line of biggest value
            String maxLine;
            maxLine = memorySubListsList.get(maxIndex).get(0);

            //process bufferList
            bufferList = po.bufferProcess(bufferList,maxLine,memorySubListsSize);

            //remove the biggest line in block of memory
            memorySubListsList.get(maxIndex).remove(0);
        }
        //process the last sublist
        if (bufferList != null){
            po.outputFile(bufferList);
        }
    }

    /**
     *Define subListsNum, subListsSize and subListsSize
     * @param totalMemory
     * @return
     * @throws IOException
     */
    public int[] config(long totalMemory) throws IOException{

        Phase2Operation po = new Phase2Operation();
        int sublistSize = po.sublistSize();

        int[] param = new int[3];
        String[] content = new File(Configuration.TEMP_CONTENT).list();

        assert content != null;
        //subListsNum
        param[0] = Phase1.fileNum - 1;
        //subListsSize
        param[1] = sublistSize;
        //subListsSize(sublist, bufferReader, bufferList and others in memory)
        param[2] = (int) (totalMemory/Configuration.TUPLE_SIZE / (param[0]*5));
        return param;
    }
}