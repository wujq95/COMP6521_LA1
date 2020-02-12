package Project_LA1;
import java.io.*;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;

public class Phase2 {
    public void start() throws IOException{
        FileReader fr = new FileReader(Configuration.OUTPUT_PATH);
        BufferedReader br = new BufferedReader(fr);
        int sublists_num = Configuration.timeNum;
        int sub_buff_num = sublists_num + 1;
        int sublists_size = Configuration.sumNum / sublists_num;
        int memory_sublists_size = Configuration.MEMORY_SIZE / sub_buff_num / Configuration.TUPLE_SIZE;
        List <String> memory_sublists_list = new ArrayList<String>();
        List <String> buffer_list = new ArrayList<String>();
        int[][] memory_buffer = new int[sublists_num][2];
        for (int i=0;i<sublists_num;i++){
            memory_buffer[i][0] = sublists_size * i;
            memory_buffer[i][1] = sublists_size * i + memory_sublists_size;
        }
        int lines = 0;
        String txt = br.readLine();
        int index=0;
        while (txt != null){
            if (lines == memory_buffer[index][0]){
                for (int line = memory_buffer[index][0]; line <= memory_buffer[index][1]; line++){
                    memory_sublists_list.add(txt);
                }
                index++;
            }
            lines++;
            txt = br.readLine();

        }
    }
}
