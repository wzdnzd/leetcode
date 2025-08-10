/*
 * @lc app=leetcode.cn id=869 lang=java
 *
 * [869] 重新排序得到 2 的幂
 *
 * https://leetcode.cn/problems/reordered-power-of-2/description/
 *
 * algorithms
 * Medium (63.39%)
 * Likes:    203
 * Dislikes: 0
 * Total Accepted:    48.8K
 * Total Submissions: 75.2K
 * Testcase Example:  '1'
 *
 * 给定正整数 n ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 1
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 10
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.HashSet;
import java.util.Set;

class Solution {
    private Set<String> powerOf2Digits = new HashSet<>();

    public boolean reorderedPowerOf2(int n) {
        init();
        return powerOf2Digits.contains(countDigits(n));
    }

    private void init() {
        for (int n = 1; n <= 1e9; n <<= 1) {
            powerOf2Digits.add(countDigits(n));
        }
    }

    private String countDigits(int n) {
        char[] chars = new char[10];
        while (n > 0) {
            ++chars[n % 10];
            n /= 10;
        }

        return new String(chars);
    }
}
// @lc code=end
