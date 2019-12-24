package cc.catface.ctool.java;

import java.util.List;

/**
 * Created by catfaceWYH --> tel|wechat|qq 130 128 92925
 */
public class TList {

    @SuppressWarnings("all") public static void clearAddAll(List srcList, List addList) {
        if (null == srcList || null == addList) return;
        srcList.clear();
        srcList.addAll(addList);
    }

}
