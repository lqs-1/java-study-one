import java.util.Arrays;

public class Basc {

    public static void main(String[] args) {
        int[] num1 = new int[100];
        for(int i=0; i<100; i++ ){
            num1[i] = i;
        }
//        String s = Arrays.toString(strings);
//        System.out.println(s);
//        System.out.println(strings);
        // 深拷贝
        int[] num2 = Arrays.copyOf(num1,num1.length);
        // 浅拷贝
//        int[] num2 = num1;
        num2[0] = 1221;
        System.out.println(num1[0]);
        System.out.println(num2[0]);

        /*
        * 拷贝：
        * 深拷贝和浅拷贝
        *
        *
        * */



    }





}
