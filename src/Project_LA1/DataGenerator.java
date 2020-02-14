package Project_LA1;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * data generator class
 */
public class DataGenerator {

    /**
     * data generator
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String PATH = "/Users/wujiaqi/IdeaProjects/COMP6521_LA1/generator3.txt";
        FileWriter fwClear  = new FileWriter(PATH);
        fwClear.write("");
        fwClear.close();
        FileWriter fw  = new FileWriter(PATH,true);
        for(int i=0;i<500000;i++){
            String str = stringGenerator();
            fw.write(str);
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * data generator
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String stringGenerator(){
        StringBuffer sb = new StringBuffer();
        String str="0123456789";
        List<String> monthList = new ArrayList<>();
        String[] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        monthList = Arrays.asList(month);
        List<String> dayList = new ArrayList<>();
        String[] day = {"01","02","03","04","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
        dayList = Arrays.asList(day);
        Random random=new Random();
        for(int i=0;i<8;i++){
            int idNum=random.nextInt(str.length());
            sb.append(str.charAt(idNum));
        }
        sb.append("201");
        sb.append(str.charAt(random.nextInt(str.length())));
        sb.append("-");
        Integer monthId = random.nextInt(month.length);
        sb.append(monthList.get(monthId));
        sb.append("-");
        Integer dayId = random.nextInt(day.length);
        sb.append(dayList.get(dayId));
        String[] firstName  = new String[]{"Ann","Peter","Bob","Nick","Dick","Linda","Maya","Lynn","Sam","Tim","Angle","Laya","Will"};
        String[] lastName  = new String[]{"Ann","Peter","Bob","Nick","Dick","Linda","Maya","Lynn","Sam","Tim","Angle","Laya","Will"};
        int firstNameId = random.nextInt(firstName.length);
        int lastNameId = random.nextInt(lastName.length);
        sb.append(firstName[firstNameId]);
        sb.append("   ");
        sb.append(lastName[lastNameId]);
        for(int i=0;i<25-firstName[firstNameId].length()-lastName[lastNameId].length()-3;i++){
            sb.append(" ");
        }
        int t=5;
        while(t>0){
            sb.append(" ");
            t--;
        }
        String genderStr="01";
        sb.append(genderStr.charAt(random.nextInt(2)));
        String dptStr="0123456789";
        for(int i=0;i<3;i++){
            sb.append(dptStr.charAt(random.nextInt(10)));
        }
        String sinStr="0123456789";
        for(int i=0;i<9;i++){
            sb.append(sinStr.charAt(random.nextInt(10)));
        }
        sb.append(" ");
        String[] street = new String[]{"Tochgelly WV 25866 South",
                                        "Lilburn GA 30048 South",
                                        "New York City NY",
                                        "Kinsman IL 60437 Midwest",
                                        "Punxsutawney PA 15767",
                                        "Mebane NC 27302 South",
                                        "Gorman TX 76454 South"};
        int streetId  =random.nextInt(street.length);
        String streetName = street[streetId];
        sb.append(streetName);
        for(int i=0;i<35-streetName.length();i++){
            sb.append(" ");
        }
        return sb.toString();
    }
}
