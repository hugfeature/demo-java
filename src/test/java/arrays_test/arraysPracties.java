package arrays_test;

import java.util.Arrays;

public class arraysPracties {
    public  static void main(String[] args){
        String str = "asdfghjkl";
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        for (int i = chars.length - 1; i >= 0 ; i--){
            System.out.println(chars[i]);
        }
    }
}
