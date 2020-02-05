package Project_LA1;

import java.util.List;

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
}
