package Project_LA1;

import java.util.List;
import java.util.Random;

/**
 * sort the data in the sublist(in the future could try heap, merge and other sort methods)
 */
public class Sort {
    /**
     * sort the data in the subList by bubble sort
     * @param subList
     */
    public void sortData(List<String> subList){
        for(int i=0;i<subList.size();i++){
            for(int j=0;j<subList.size()-i-1;j++){
                if(subList.get(j).compareTo(subList.get(j+1))>0){
                    String temp = subList.get(i);
                    subList.set(j,subList.get(j+1));
                    subList.set(j+1,temp);
                }
            }
        }
    }
    /**
     * sort the data in the sublist by quick sort
     * @param subList
     */
    public void quickSort(List<String> subList,int left,int right){
        if(left>=right){
            return ;
        }
        int i=left;
        int j=right;
        String temp = "";
        String key=subList.get(left);
        while(i<j){
            while(i<j&&(subList.get(j).compareTo(key)>=0)){
                j--;
            }
            temp = subList.get(i);
            subList.set(i,subList.get(j));
            subList.set(j,temp);
            while(i<j&&(subList.get(i).compareTo(key)<=0)){
                i++;
            }
            temp = subList.get(i);
            subList.set(i,subList.get(j));
            subList.set(j,temp);
        }
        subList.set(i,key);
        quickSort(subList,left,i-1);
        quickSort(subList,i+1,right);
    }












    /**
     * 采用随机选取枢纽元素策略的一次快排后的划分索引
     * @param a 待排序数组
     * @param p 区间左端
     * @param r 区间右端
     * @return 采用随机选取枢纽元素策略的一次快排后的划分索引
     */
    @SuppressWarnings("rawtypes")
    public static int randomizedPartition(List<String> a,int p,int r){
        int i=random(p,r);
        swap(a,i,p);//将枢纽元素交换到区间最左端
        return partition(a,p,r);
    }

    /**
     * 采用随机选取枢纽元素策略的快速排序
     * @param a 待排序数组
     * @param p 指定区间的左端
     * @param r 指定区间的右端
     */
    @SuppressWarnings({ "unused", "rawtypes" })
    public  void randomizedQuickSort(List<String> a,int p,int r){
        if(p<r){
            int q=randomizedPartition(a,p,r);
            randomizedQuickSort(a,p,q-1);
            randomizedQuickSort(a,q+1,r);
        }
    }

    /**
     * 取i-j之间的一个随机数
     * @param i 区间左端
     * @param j 区间右端
     * @return 指定区间间的一个随机数
     */
    public static int random(int i,int j){
        Random r=new Random();
        return r.nextInt(j-i)+i;
    }

    /**
     * 求划分索引
     * @param a 待排序数组
     * @param p 区间左端
     * @param r 区间右端
     * @return 划分索引
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static int partition(List<String> a,int p,int r){
        int i=p,j=r+1;
        String x = a.get(p);
        while(true){
            while(a.get(++i).compareTo(x)<0&&i<r);
            while(a.get(--j).compareTo(x)>0);
            if(i>=j)break;
            swap(a,i,j);
        }
        a.set(p,a.get(j));
        a.set(j,x);
        return j;
    }

    /**
     *
     * @param a
     * @param i
     * @param j
     */
    @SuppressWarnings("rawtypes")
    public static void swap(List<String> a,int i,int j){
        String temp=a.get(i);
        a.set(i,a.get(j));
        a.set(j,temp);
    }

}
