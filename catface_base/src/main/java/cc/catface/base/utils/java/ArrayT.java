package cc.catface.base.utils.java;

import java.util.Random;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class ArrayT {

    public static int[] getRandomFromArray(int[] array, int count) {
        // ArrayList<Integer>arrayList =null;
        int[] a = array;
        int[] result = new int[count];
        boolean r[] = new boolean[array.length];
        Random random = new Random();
        int m = count; // 要随机取的元素个数
        if (m > a.length || m < 0) return a;
        int n = 0;
        while (true) {
            int temp = random.nextInt(a.length);
            if (!r[temp]) {
                if (n == m) // 取到足量随机数后退出循环
                    break;
                n++;
                System.out.println("得到的第" + n + "个随机数为：" + a[temp]);
                result[n - 1] = a[temp];
                r[temp] = true;
            }
        }
        return result;
    }

    public static String[] getRandomFromArray(String[] array, int count) {
        // ArrayList<Integer>arrayList =null;
        String[] a = array;
        String[] result = new String[count];
        boolean r[] = new boolean[array.length];
        Random random = new Random();
        int m = count; // 要随机取的元素个数
        if (m > a.length || m < 0) return a;
        int n = 0;
        while (true) {
            int temp = random.nextInt(a.length);
            if (!r[temp]) {
                if (n == m) // 取到足量随机数后退出循环
                    break;
                n++;
                System.out.println("得到的第" + n + "个随机数为：" + a[temp]);
                result[n - 1] = a[temp];
                r[temp] = true;
            }
        }
        return result;
    }


}
