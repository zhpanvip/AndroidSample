package com.zhpan.sample.binder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpan
 * @date 2021/7/20
 */
public class StudentMap {

    private static Map<String, Integer> map;

    public static int getStudentGrade(String name) {
        if (map == null) {
            map = new HashMap<>();
            map.put("Andy", 96);
            map.put("Frank", 89);
            map.put("Anna", 92);
        }
        if (map.get(name) == null) {
            return 0;
        }
        return map.get(name);
    }
}
