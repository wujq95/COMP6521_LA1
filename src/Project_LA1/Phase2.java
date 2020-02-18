package Project_LA1;

import java.io.*;
import java.util.*;

public class Phase2 {
    public void start(long totalMemory) throws IOException{
        Phase2 phase2 = new Phase2();
        Phase2_Operation po = new Phase2_Operation();
        po.File_Init(); //初始化文件
        int[] param = phase2.Config(totalMemory);

        int sublists_num = param[0];            //sublist数量
        int sublists_size = param[1];           //sublist大小
        int memory_sublists_size = param[2];     //内存中每个sublist存放数量

//        int[] io_time = po.IO_time( sublists_size, memory_sublists_size, sublists_num);     //需要循环多少次
        String[] file_address = po.File_Address(sublists_num);                             //文件地址初始化
        BufferedReader[] br_init = po.Buffer_Init(sublists_num,file_address);             //指针初始化

        phase2.Duplict_Insert(sublists_num,sublists_size,memory_sublists_size,file_address,br_init);

    }


    public void Duplict_Insert( int sublists_num,int sublists_size, int memory_sublists_size, String[] file_address, BufferedReader[] br_pointer) throws IOException{
        Phase2_Operation po = new Phase2_Operation();
        List <List<String>> memory_sublists_list = po.init(sublists_num);   //内存中n个sublist 初始化
        List <String> buffer_list = new ArrayList<>();                      //内存中的缓冲list
        List <String> first_line = new ArrayList<>();

        while (true){
            //向内存中添加数据
            for (int index=0;index<sublists_num;index++){
                if (memory_sublists_list.get(index).size() == 0){
                    Map<List<String>,BufferedReader[]> map = po.Fetch_Sublist(file_address,index,br_pointer,memory_sublists_size);
                    Map.Entry<List<String>,BufferedReader[]> result = map.entrySet().iterator().next();
                    memory_sublists_list.set(index,result.getKey());
                    br_pointer = result.getValue();
                }
//                System.out.println(br_pointer[index].readLine());
            }

            // 构造第一行
            first_line = new ArrayList<>();
            for (int i=0;i<sublists_num;i++){
                first_line.add(memory_sublists_list.get(i).get(0));
            }

            //获取最大值得index
            int max_index;
            max_index = po.Max_Index(first_line,sublists_num);
            if (max_index == -1){
                break;
            }
//            System.out.println(max_index);

            //获取最大的那行
            String max_line;
            max_line = memory_sublists_list.get(max_index).get(0);

            //处理buffer_list
            buffer_list = po.Buffer_Process(buffer_list,max_line,memory_sublists_size);

            //移掉内存sublist中最大的line
            memory_sublists_list.get(max_index).remove(0);
        }

        //处理最后一个sublist
        if (buffer_list != null){
            po.OutputFile(buffer_list);
        }


    }

    public int[] Config(long totalMemory) throws IOException{

        Phase2_Operation po = new Phase2_Operation();
        int sublists_size = po.Sublist_size();

        int[] param = new int[3];
        String[] content = new File(Configuration.TEMP_CONTENT).list();

        assert content != null;
        param[0] = Phase1.fileNum - 1;               //sublists_num
        param[1] = sublists_size;                   //sublists_size
        param[2] = (int) (totalMemory/Configuration.TUPLE_SIZE / (param[0]*5));    //memory_sublists_size (memory里sublist+ buffereader + buffer_list+计算等等)
//        System.out.println(Arrays.toString(param));
        return param;
    }
    public static void main(String[] args) throws IOException {
        Phase2 s = new Phase2();
        Date date1 = new Date();
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        s.start(totalMemory);
        Date date2 = new Date();
        System.out.println(date2.getTime()-date1.getTime());
    }
}
