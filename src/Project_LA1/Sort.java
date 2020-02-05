package Project_LA1;

import java.util.List;

public class Sort {
    /**
     * sort the data in the subList
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
}
