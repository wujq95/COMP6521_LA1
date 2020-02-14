package Project_LA1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;

public class Maximum {
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

    public String Disk_Line(int[] disk_index, int index,int sublists_size) throws IOException{
        FileReader fr = new FileReader(Configuration.PHASE2_ORIGINAL);
        BufferedReader br = new BufferedReader(fr);
        String disk_line = "";
        int index_num = disk_index[index];
        if (index_num + 1 <= (index+1)*sublists_size){
            int lines = 0;
            String txt = br.readLine();
            while (txt != null){
                if (lines == index_num){
                    disk_line = txt;
                    break;
                }
                txt = br.readLine();
                lines ++;
            }
        }
        return disk_line;
    }

    public long[] Disk_Index (int sublists_num,int[] disk_index) throws IOException{
        long[] Anchor = new long[sublists_num];
        RandomAccessFile br = new RandomAccessFile(Configuration.PHASE2_ORIGINAL, "r");
        int lines = 0;
        int index = 0;
        String txt = "";
        while (txt != null){
//            System.out.printf("%d,%d,%d\n",lines,disk_index[index],index);
            if (lines == disk_index[index]){
                Anchor[index] = br.getFilePointer();
                index += 1;
                if (index == sublists_num){ break; }
                }
            txt = br.readLine();
            lines ++;
        }
        return Anchor;
    }

    public int[] Last_Index (int sublists_num,int sublists_size,int memory_sublists_size) throws IOException{
        int[] Last_Index = new int[sublists_num];
        for (int i=0;i<sublists_num;i++){
            Last_Index[i] = sublists_size - memory_sublists_size;
        }
        return Last_Index;
    }

    public String[] Disk_Line (int max_index,long[] Anchor,int[] Last_Index) throws IOException {
        RandomAccessFile br = new RandomAccessFile(Configuration.PHASE2_ORIGINAL, "r");
        String[] disk_point = new String[2];
        String disk_line = null;
        long point = Anchor[max_index];
        if (Last_Index[max_index] > 0){
            br.seek(Anchor[max_index]);
            String txt = br.readLine();
//            System.out.println(txt);
            disk_line = txt;
            point = br.getFilePointer();
        }
        disk_point[0] = disk_line;
        disk_point[1] = String.valueOf(point);
        return disk_point;
    }

    public void OutputFile(List<String> subList) throws IOException {
        FileWriter fw  = new FileWriter(Configuration.PHASE2_OUTPUT,true);
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
