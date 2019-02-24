package cc.catface.base.utils.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TNumber {

    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static int[] getRandoms(int min, int max, int count) {
        int[] randoms = new int[count];
        List<Integer> listRandom = new ArrayList<>();

        if (count > (max - min + 1)) {
            return null;
        }
        for (int i = min; i <= max; i++) {
            listRandom.add(i);
        }
        for (int i = 0; i < count; i++) {
            int index = getRandom(0, listRandom.size() - 1);
            randoms[i] = listRandom.get(index);
            listRandom.remove(index);
        }

        return randoms;
    }
}
