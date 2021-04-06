package com.company;
import java.util.*;
public class Main {
    public static int answer = 0;
    public static int length;
    public static Set<String> set = new HashSet<String>();

    public static void dfs(int current,String s,int[] a){
        int[] check = new int[length];
        if (current == length){
            String tempStr ="";
            for (int i=0;i<length;i++){
                tempStr = tempStr + s.charAt(a[i]);
            }
            if (set.add(tempStr)){
                answer ++;
            }
        }
        for (int i =0;i<current;i++)
            check[a[i]] = 1;
        for (int i=0;i<length;i++){
            if (check[i]==0){
                a[current] = i;
                dfs(current+1,s,a);
            }

        }
    }
    public static void main(String[] args) {
        //算法 全排列
        //查重 HashMa
        //字符串重排
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        length = s.length();
        int[] a = new int[length];
        dfs(0,s,a);
        System.out.println(answer);
        for (String str: set){
            System.out.println(str);
        }
    }
}
