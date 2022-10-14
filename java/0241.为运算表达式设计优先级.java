import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=241 lang=java
 *
 * [241] 为运算表达式设计优先级
 *
 * https://leetcode.cn/problems/different-ways-to-add-parentheses/description/
 *
 * algorithms
 * Medium (75.71%)
 * Likes:    759
 * Dislikes: 0
 * Total Accepted:    69.4K
 * Total Submissions: 91.7K
 * Testcase Example:  '"2-1-1"'
 *
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * 
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10^4 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0 
 * (2-(1-1)) = 2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34 
 * ((2*3)-(4*5)) = -14 
 * ((2*(3-4))*5) = -10 
 * (2*((3-4)*5)) = -10 
 * (((2*3)-4)*5) = 10
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99] 
 * 
 * 
 */

// @lc code=start
class Solution {
    private Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();
        if ("".equals(expression))
            return result;

        if (memo.containsKey(expression))
            return memo.get(expression);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if ('+' == c || '-' == c || '*' == c) {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, expression.length()));

                for (Integer x : left) {
                    for (Integer y : right) {
                        if ('+' == c)
                            result.add(x + y);
                        if ('-' == c)
                            result.add(x - y);
                        if ('*' == c)
                            result.add(x * y);
                    }
                }
            }
        }

        if (result.isEmpty())
            result.add(Integer.parseInt(expression));

        memo.put(expression, result);
        return result;
    }
}
// @lc code=end
