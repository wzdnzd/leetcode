/*
 * @lc app=leetcode.cn id=2376 lang=java
 *
 * [2376] 统计特殊整数
 *
 * https://leetcode.cn/problems/count-special-integers/description/
 *
 * algorithms
 * Hard (57.20%)
 * Likes:    94
 * Dislikes: 0
 * Total Accepted:    14K
 * Total Submissions: 24.1K
 * Testcase Example:  '20'
 *
 * 如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
 * 
 * 给你一个 正 整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 20
 * 输出：19
 * 解释：1 到 20 之间所有整数除了 11 以外都是特殊整数。所以总共有 19 个特殊整数。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 5
 * 输出：5
 * 解释：1 到 5 所有整数都是特殊整数。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 135
 * 输出：110
 * 解释：从 1 到 135 总共有 110 个整数是特殊整数。
 * 不特殊的部分数字为：22 ，114 和 131 。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 2 * 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int countSpecialNumbers(int n) {
        char[] cs = Integer.toString(n).toCharArray();

        int[][] memo = new int[cs.length][1 << 10];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dfs(0, 0, true, false, cs, memo);
    }

    private int dfs(int i, int mask, boolean isLimit, boolean isNum, char[] cs, int[][] memo) {
        if (i == cs.length)
            return isNum ? 1 : 0;

        if (!isLimit && isNum && memo[i][mask] != -1)
            return memo[i][mask];

        int count = 0;
        if (!isNum)
            count = dfs(i + 1, mask, false, false, cs, memo);

        int up = isLimit ? cs[i] - '0' : 9;
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            if ((mask >> d & 1) == 0)
                count += dfs(i + 1, mask | (1 << d), isLimit && d == up, true, cs, memo);
        }

        if (!isLimit && isNum)
            memo[i][mask] = count;

        return count;
    }
}
// @lc code=end
