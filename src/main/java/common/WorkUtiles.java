package common;

import java.util.Random;

public class WorkUtiles {
    public static String getcode(int a){
        String code;
        code =  String.format("%4d" ,a).replace(" ", "0");
        return code;
    }

    public static String getGrade(){
        String grade ;
        Random random = new Random((long) 1.0);
        long rangdom1 = random.nextLong();
        grade = String.valueOf(rangdom1);
        return grade;
    }
}
