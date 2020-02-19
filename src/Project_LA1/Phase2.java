package Project_LA1;

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
        Phase2_Operation po = new Phase2_Operation();
        //init file
        po.File_Init();
        int[] param = phase2.Config(totalMemory);

        //sublist number
        int sublists_num = param[0];
        //sublist size
        int sublists_size = param[1];
        // one block size
        int memory_sublists_size = param[2];

        //init file address
        String[] file_address = po.File_Address(sublists_num);
        //init pointer
        BufferedReader[] br_init = po.Buffer_Init(sublists_num,file_address);

        phase2.Duplict_Insert(sublists_num,memory_sublists_size,file_address,br_init);

    }


    /**
     * Remove duplicated data and insert new data
     * @param sublists_num
     * @param memory_sublists_size
     * @param file_address
     * @param br_pointer
     * @throws IOException
     */
    public void Duplict_Insert( int sublists_num, int memory_sublists_size, String[] file_address, BufferedReader[] br_pointer) throws IOException{
        Phase2_Operation po = new Phase2_Operation();
        //init n blocks in memory
        List <List<String>> memory_sublists_list = po.init(sublists_num);
        //buffer list in memory
        List <String> buffer_list = new ArrayList<>();
        List <String> first_line = new ArrayList<>();

        while (true){
            //add data to memory
            for (int index=0;index<sublists_num;index++){
                if (memory_sublists_list.get(index).size() == 0){
                    Map<List<String>,BufferedReader[]> map = po.Fetch_Sublist(file_address,index,br_pointer,memory_sublists_size);
                    Map.Entry<List<String>,BufferedReader[]> result = map.entrySet().iterator().next();
                    memory_sublists_list.set(index,result.getKey());
                    br_pointer = result.getValue();
                }
            }

            // create the first line to compare
            first_line = new ArrayList<>();
            for (int i=0;i<sublists_num;i++){
                first_line.add(memory_sublists_list.get(i).get(0));
            }

            //get the index of biggest value
            int max_index;
            max_index = po.Max_Index(first_line,sublists_num);
            if (max_index == -1){
                break;
            }

            //get the line of biggest value
            String max_line;
            max_line = memory_sublists_list.get(max_index).get(0);

            //process buffer_list
            buffer_list = po.Buffer_Process(buffer_list,max_line,memory_sublists_size);

            //remove the biggest line in block of memory
            memory_sublists_list.get(max_index).remove(0);
        }
        //process the last sublist
        if (buffer_list != null){
            po.OutputFile(buffer_list);
        }
    }

    /**
     *Define sublists_num, sublists_size and memory_sublists_size
     * @param totalMemory
     * @return
     * @throws IOException
     */
    public int[] Config(long totalMemory) throws IOException{

        Phase2_Operation po = new Phase2_Operation();
        int sublists_size = po.Sublist_size();

        int[] param = new int[3];
        String[] content = new File(Configuration.TEMP_CONTENT).list();

        assert content != null;
        //sublists_num
        param[0] = Phase1.fileNum - 1;
        //sublists_size
        param[1] = sublists_size;
        //memory_sublists_size(sublist, buffereader, buffer_list and others in memeory)
        param[2] = (int) (totalMemory/Configuration.TUPLE_SIZE / (param[0]*5));
        return param;
    }
}
