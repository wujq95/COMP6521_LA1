package Project_LA1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phase2_Operation {

    public int[] IO_time (int sublists_size,int memory_sublists_size,int sublists_num) throws IOException{
        int[] io_time = new int[sublists_num];
        for (int i=0;i<sublists_num;i++){
            io_time[i] = sublists_size / memory_sublists_size +1;
        }
        return  io_time;
    }

    public  String[] File_Address(int sublists_num) throws IOException{
        String[] file_address = new String[sublists_num];
        for (int i=1;i<=sublists_num;i++){
            file_address[i-1] = Configuration.TEMP_PATH + i + ".txt";
        }
        return  file_address;
    }

    public BufferedReader[] Buffer_Init(int sublists_num,String[] file_address) throws IOException{
        BufferedReader[] br_init = new BufferedReader[sublists_num];
        for (int i=0;i<sublists_num;i++){
            FileReader fr = new FileReader(file_address[i]);
            BufferedReader br = new BufferedReader(fr);
            br_init[i] = br;
        }
        return br_init;
    }

    public List <List<String>> init(int sublits_num) throws IOException{
        List <List<String>> memory_sublists_list = new ArrayList<>();
        List<String> init_arr = new ArrayList<>();
        for (int i=0; i<sublits_num; i++){
            memory_sublists_list.add(init_arr);
        }
        return memory_sublists_list;
    }

    public Map<List<String>,BufferedReader[]> Fetch_Sublist(String[] file_address, int index, BufferedReader[] br_pointer, int fetch_num) throws IOException {
        Map<List<String>,BufferedReader[]> map= new HashMap<>();
        List<String> fetch_sublist = new ArrayList<>();
        String file_add = file_address[index];
        BufferedReader br = br_pointer[index];
        String txt = "";
        for (int lines = 0; lines< fetch_num; lines++){
            txt = br.readLine();
            if (txt == null){
                br.close();
                fetch_sublist.add("99999999");
                break;
            }
            else{
                fetch_sublist.add(txt);
            }

        }
        br_pointer[index] = br;
        map.put(fetch_sublist,br_pointer);
        return  map;
    }

    public int Max_Index (List<String> first_line,int sublists_num) throws IOException {
        int max_index = 0;
        int[] myList = new int[sublists_num];       //定义一维数组
        for (int i=0; i<sublists_num;i++){
            myList[i] = Integer.parseInt(first_line.get(i).substring(0,8));
        }
        int num = myList[0];                        //0为第一个数组下标
        for (int i = 1; i < myList.length; i++) {   //开始循环一维数组
            if (myList[i] < num) {                  //循环判断数组元素
                num = myList[i];
                max_index = i;

            }
        }
//        System.out.println(num);
        if (num == 99999999){
            return -1;
        }
        return max_index;
    }

    public List <String> Buffer_Process(List <String> buffer_list,String max_line,int memory_sublists_size) throws IOException{
        Phase2_Operation po = new Phase2_Operation();
        int buffer_size = buffer_list.size();
        if (buffer_size==0){
            buffer_list.add(max_line);
        }
        else if (buffer_size < memory_sublists_size){
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
        if (buffer_size+1 == memory_sublists_size){
            po.OutputFile(buffer_list);
            buffer_list = new ArrayList<String>();
        }
        return buffer_list;
    }





    public void OutputFile(List<String> subList) throws IOException {
        FileWriter fw  = new FileWriter(Configuration.PHASE2_OUTPUT,true);
        PrintWriter pw = new PrintWriter(fw);
        //output every element in the sublist
        for(String str:subList){
            pw.println(str);
//            pw.flush();
        }
        pw.close();
        fw.close();
    }





}

















