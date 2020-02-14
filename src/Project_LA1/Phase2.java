package Project_LA1;
import java.awt.desktop.SystemSleepEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Phase2 {
    public void start() throws IOException{
        Phase2 phase2 = new Phase2();
        int[] param = phase2.Config();
        FileReader fr = new FileReader(Configuration.PHASE2_ORIGINAL);
        BufferedReader br = new BufferedReader(fr);
//        int sublists_num = param[0];
        int sublists_num = 100;
//        int sublists_size = param[1];
        int sublists_size = 200;
        int memory_sublists_size = 150;     //内存中每个sublist存放数量
//        int memory_sublists_size = 50;
//        System.out.printf("%d,%d,%d",sublists_num,sublists_size,memory_sublists_size);
        List <String> sublist_list = new ArrayList<>();
        List <List<String>> memory_sublists_list = new ArrayList<List<String>>();
        int[] disk_index = new int[sublists_num];
        for (int i=0;i<sublists_num;i++){
            disk_index[i] = memory_sublists_size + sublists_size*i;
        }
        int[][] memory_buffer = new int[sublists_num][2];
        for (int i=0;i<sublists_num;i++){
            memory_buffer[i][0] = sublists_size * i;
            memory_buffer[i][1] = sublists_size * i + memory_sublists_size-1;
//            System.out.println(memory_buffer[i][0]);
        }
        int lines = 0;
        String txt = br.readLine();
        int index=0;
        while (txt != null){
            if (lines == memory_buffer[index][0]){
                sublist_list = new ArrayList<>();
                for (int line = memory_buffer[index][0]; line <= memory_buffer[index][1]; line++){
                    sublist_list.add(txt);
                    txt = br.readLine();
                    lines++;
                }
                memory_sublists_list.add(sublist_list);
                index++;
                if (index == sublists_num){
                    break;
                }
            }
            else {
                txt = br.readLine();
                lines++;
            }


        }

        phase2.Duplict_Insert(memory_sublists_list, disk_index,sublists_num,sublists_size,memory_sublists_size);
//        return memory_sublists_list;
    }


    public void Duplict_Insert(List<List<String>> memory_sublists_list, int[] disk_index, int sublists_num,int sublists_size, int memory_sublists_size) throws IOException{
        Maximum max = new Maximum();
        Phase2 phase2 = new Phase2();
        int max_index;
        String max_line;
        List <String> buffer_list = new ArrayList<String>();
        long[] Anchor = max.Disk_Index(sublists_num,disk_index);
        int[] Last_Index = max.Last_Index(sublists_num,sublists_size,memory_sublists_size);
        System.out.println(Arrays.toString(Anchor));
        System.out.println(Arrays.toString(disk_index));
        System.out.println(Arrays.toString(Last_Index));
        while (memory_sublists_list != null){
            List <String> first_line = new ArrayList<String>();
            for (int i=0;i<sublists_num;i++){
                if (memory_sublists_list.get(i).size() != 0){
                    first_line.add(memory_sublists_list.get(i).get(0));
                }
                else{
                    first_line.add("99999999");
                }
            }
            max_index = max.Max_Index(first_line,sublists_num);
            if (max_index == -1){
                break;
            }
//            System.out.println(max_index);
            max_line = memory_sublists_list.get(max_index).get(0);
//            System.out.println(max_line);
            int buffer_size = buffer_list.size();
            if (buffer_size==0){
                buffer_list.add(max_line);
            }
            else if (buffer_size < sublists_size){
                String curr_line = buffer_list.get(buffer_size-1);
                if (max_line.substring(0,8).equals(curr_line.substring(0,8))){
                    if (max_line.substring(8,18).compareTo(curr_line.substring(8,18)) > 0 ){
                        buffer_list.set(buffer_size-1,max_line);
                    }
                }
                else {
                    buffer_list.add(max_line);
                }
            }
            if (buffer_size+1 == sublists_size){
                System.out.println(buffer_list);
                max.OutputFile(buffer_list);
                buffer_list = new ArrayList<String>();
            }

            memory_sublists_list.get(max_index).remove(0);

            String[] disk_point = max.Disk_Line(max_index,Anchor,Last_Index);
            String disk_line = disk_point[0];
            long point = Long.parseLong(disk_point[1]);
            if (disk_line != null){
                Anchor[max_index] = point;
                Last_Index[max_index] -= 1;
                memory_sublists_list.get(max_index).add(disk_line);
            }
//            System.out.println(memory_sublists_list.get(0));
//            System.out.println(Arrays.toString(Anchor));
//            System.out.println(Arrays.toString(Last_Index));

//           String disk_line = max.Disk_Line(disk_index,max_index,sublists_size);
//            if (disk_line.length() > 0){
////                System.out.println(disk_index[max_index]);
//                disk_index[max_index] += 1;
//                memory_sublists_list.get(max_index).add(disk_line);
//            }
        }
        if (buffer_list != null){
            System.out.println(buffer_list);
            max.OutputFile(buffer_list);
        }

    }

    public int[] Config() throws IOException{
        int[] param = new int[3];
        int sum = 0;
        FileReader fr2 = new FileReader(Configuration.PHASE2_ORIGINAL);
        BufferedReader br2 = new BufferedReader(fr2);
        while((br2.readLine())!=null){
            sum++;
        }
        fr2.close();
        System.out.println(Configuration.TUPLE_NUM);
        int sublists_num = sum%Configuration.TUPLE_NUM==0?sum/Configuration.TUPLE_NUM:sum/Configuration.TUPLE_NUM+1;
        int sublists_size = sum / sublists_num;
        int memory_sublists_size = Configuration.TUPLE_NUM;
        param[0] = sublists_num;
        param[1] = sublists_size;
        param[2] = memory_sublists_size;
        return param;
    }
    public static void main(String[] args) throws IOException {
        Phase2 s = new Phase2();
        s.start();
    }
}
