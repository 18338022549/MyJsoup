package com.example.myjsoup;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jax
 * @Date: 2024/03/28/下午 05:59:04
 * @Description:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组
 * 是数组中的一个连续部分。
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2： 输入：nums = [1]  输出：1
 * 示例 3： 输入：nums = [5,4,-1,7,8] 输出：23
*/
public class MaxListNum {
    static int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

    public static void main(String[] args) {
        merge(intervals);
    }

    public static int[][] merge(int[][] intervals) {
        int[][] noINtervals = {};
        for (int i = 0; i < intervals.length; i++) {
            System.out.println("_______");
            for (int j = 0; j < intervals[i].length; j++) {
//                if()
                int L = intervals[i][j];
                int R = intervals[i][j];
//                System.out.println("L"+L);
                System.out.println("R"+R);
            }
        }
        return noINtervals;
    }


}
