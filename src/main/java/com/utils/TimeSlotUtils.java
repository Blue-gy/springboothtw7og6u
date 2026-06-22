package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 场地预约时间段工具类
 * 定义15个时段：10小节 + 5大节
 * @author
 * @date 2026-06-10
 */
public class TimeSlotUtils {

    // 10个小节
    public static final String[] INDIVIDUAL_PERIODS = {
        "第1小节(08:00-08:45)",
        "第2小节(08:55-09:40)",
        "第3小节(10:00-10:45)",
        "第4小节(10:55-11:40)",
        "第5小节(14:00-14:45)",
        "第6小节(14:55-15:40)",
        "第7小节(16:00-16:45)",
        "第8小节(16:55-17:40)",
        "第9小节(19:00-19:45)",
        "第10小节(19:55-20:40)"
    };

    // 5个大节（每个大节对应2个小节）
    public static final String[] MAJOR_PERIODS = {
        "第一大节(08:00-09:40)",   // 第1小节 + 第2小节
        "第二大节(10:00-11:40)",   // 第3小节 + 第4小节
        "第三大节(14:00-15:40)",   // 第5小节 + 第6小节
        "第四大节(16:00-17:40)",   // 第7小节 + 第8小节
        "第五大节(19:00-20:40)"    // 第9小节 + 第10小节
    };

    // 大节到小节的映射关系
    private static final Map<String, String[]> MAJOR_TO_INDIVIDUAL = new LinkedHashMap<>();
    static {
        MAJOR_TO_INDIVIDUAL.put(MAJOR_PERIODS[0], new String[]{INDIVIDUAL_PERIODS[0], INDIVIDUAL_PERIODS[1]});
        MAJOR_TO_INDIVIDUAL.put(MAJOR_PERIODS[1], new String[]{INDIVIDUAL_PERIODS[2], INDIVIDUAL_PERIODS[3]});
        MAJOR_TO_INDIVIDUAL.put(MAJOR_PERIODS[2], new String[]{INDIVIDUAL_PERIODS[4], INDIVIDUAL_PERIODS[5]});
        MAJOR_TO_INDIVIDUAL.put(MAJOR_PERIODS[3], new String[]{INDIVIDUAL_PERIODS[6], INDIVIDUAL_PERIODS[7]});
        MAJOR_TO_INDIVIDUAL.put(MAJOR_PERIODS[4], new String[]{INDIVIDUAL_PERIODS[8], INDIVIDUAL_PERIODS[9]});
    }

    /**
     * 获取所有15个时段选项（10小节 + 5大节）
     */
    public static List<String> getAllTimeSlots() {
        List<String> slots = new ArrayList<>();
        slots.addAll(Arrays.asList(INDIVIDUAL_PERIODS));
        slots.addAll(Arrays.asList(MAJOR_PERIODS));
        return slots;
    }

    /**
     * 将大节展开为多个小节
     * 如果输入是小节，返回包含该小节的单元素列表
     * 如果输入是大节，返回对应的2个小节列表
     */
    public static List<String> expandToIndividualPeriods(String shijianduan) {
        List<String> result = new ArrayList<>();
        if (shijianduan == null || shijianduan.trim().isEmpty()) {
            return result;
        }

        // 检查是否为大节
        String[] expanded = MAJOR_TO_INDIVIDUAL.get(shijianduan);
        if (expanded != null) {
            result.addAll(Arrays.asList(expanded));
        } else {
            // 单独小节，直接返回
            result.add(shijianduan);
        }
        return result;
    }

    /**
     * 判断给定时间段是否为大节
     */
    public static boolean isMajorPeriod(String shijianduan) {
        return MAJOR_TO_INDIVIDUAL.containsKey(shijianduan);
    }

    /**
     * 获取大节对应的小节列表（用于前端展示映射关系）
     */
    public static Map<String, String[]> getMajorToIndividualMap() {
        return new LinkedHashMap<>(MAJOR_TO_INDIVIDUAL);
    }
}
