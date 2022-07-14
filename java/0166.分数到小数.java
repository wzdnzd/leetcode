import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=166 lang=java
 *
 * [166] 分数到小数
 *
 * https://leetcode.cn/problems/fraction-to-recurring-decimal/description/
 *
 * algorithms
 * Medium (33.37%)
 * Likes:    393
 * Dislikes: 0
 * Total Accepted:    53.6K
 * Total Submissions: 160.5K
 * Testcase Example:  '1\n2'
 *
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 
 * 如果存在多个答案，只需返回 任意一个 。
 * 
 * 对于所有给定的输入，保证 答案字符串的长度小于 10^4 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 * 
 * 
 */

// @lc code=start
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {

        // 转 long 计算，防止溢出
        long x = numerator, y = denominator;

        // 如果本身能够整除，直接返回计算结果
        if (x % y == 0)
            return String.valueOf(x / y);

        StringBuilder sb = new StringBuilder();

        // 如果其一为负数，先追加负号
        if (x * y < 0)
            sb.append('-');

        x = Math.abs(x);
        y = Math.abs(y);

        // 计算小数点前的部分，并将余数赋值给 a
        sb.append(String.valueOf(x / y) + ".");

        x %= y;
        Map<Long, Integer> map = new HashMap<>();
        while (x != 0) {
            // 记录当前余数所在答案的位置，并继续模拟除法运算
            map.put(x, sb.length());
            x *= 10;
            sb.append(x / y);
            x %= y;

            // 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
            if (map.containsKey(x)) {
                int index = map.get(x);
                return String.format("%s(%s)", sb.substring(0, index), sb.substring(index));
            }
        }
        return sb.toString();
    }
}
// @lc code=end
