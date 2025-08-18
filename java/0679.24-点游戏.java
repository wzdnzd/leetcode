/*
 * @lc app=leetcode.cn id=679 lang=java
 *
 * [679] 24 点游戏
 *
 * https://leetcode.cn/problems/24-game/description/
 *
 * algorithms
 * Hard (53.90%)
 * Likes:    502
 * Dislikes: 0
 * Total Accepted:    53.7K
 * Total Submissions: 97K
 * Testcase Example:  '[4,1,8,7]'
 *
 * 给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。您应该使用运算符 ['+', '-',
 * '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 * 
 * 你须遵守以下规则:
 * 
 * 
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 
 * 
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 
 * 
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 
 * 
 * 你不能把数字串在一起
 * 
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 
 * 
 * 
 * 
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: cards = [4, 1, 8, 7]
 * 输出: true
 * 解释: (8-4) * (7-1) = 24
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: cards = [1, 2, 1, 2]
 * 输出: false
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * cards.length == 4
 * 1 <= cards[i] <= 9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int TARGET = 24;
    private static final double EPSILON = 1e-6;
    private static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums)
            list.add((double) num);

        return solve(list);
    }

    public boolean solve(List<Double> list) {
        if (list.size() == 0)
            return false;

        if (list.size() == 1)
            return Math.abs(list.get(0) - TARGET) < EPSILON;

        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> candidates = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j)
                            candidates.add(list.get(k));
                    }

                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j)
                            continue;

                        if (k == ADD)
                            candidates.add(list.get(i) + list.get(j));
                        else if (k == MULTIPLY)
                            candidates.add(list.get(i) * list.get(j));
                        else if (k == SUBTRACT)
                            candidates.add(list.get(i) - list.get(j));
                        else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON)
                                continue;
                            else
                                candidates.add(list.get(i) / list.get(j));
                        }

                        if (solve(candidates))
                            return true;

                        candidates.remove(candidates.size() - 1);
                    }
                }
            }
        }

        return false;
    }
}
// @lc code=end
