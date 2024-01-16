/*
 * @lc app=leetcode.cn id=2719 lang=java
 *
 * [2719] 统计整数数目
 *
 * https://leetcode.cn/problems/count-of-integers/description/
 *
 * algorithms
 * Hard (47.75%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    4.8K
 * Total Submissions: 9K
 * Testcase Example:  '"1"\n"12"\n1\n8'
 *
 * 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。如果一个整数 x
 * 满足以下条件，我们称它是一个好整数：
 * 
 * 
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * 
 * 
 * 请你返回好整数的数目。答案可能很大，请返回答案对 10^9 + 7 取余后的结果。
 * 
 * 注意，digit_sum(x) 表示 x 各位数字之和。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：num1 = "1", num2 = "12", min_num = 1, max_num = 8
 * 输出：11
 * 解释：总共有 11 个整数的数位和在 1 到 8 之间，分别是 1,2,3,4,5,6,7,8,10,11 和 12 。所以我们返回 11 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：num1 = "1", num2 = "5", min_num = 1, max_num = 5
 * 输出：5
 * 解释：数位和在 1 到 5 之间的 5 个整数分别为 1,2,3,4 和 5 。所以我们返回 5 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= num1 <= num2 <= 10^22
 * 1 <= min_sum <= max_sum <= 400
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        int result = calculate(num2, min_sum, max_sum) - calculate(num1, min_sum, max_sum) + MOD;

        int sum = 0;
        for (int i = 0; i < num1.length(); i++)
            sum += num1.charAt(i) - '0';

        return (result + (sum >= min_sum && sum <= max_sum ? 1 : 0)) % MOD;
    }

    private int calculate(String s, int minSum, int maxSum) {
        int[][] memos = new int[s.length()][maxSum + 1];
        for (int[] memo : memos)
            Arrays.fill(memo, -1);

        return dfs(0, 0, true, s, minSum, maxSum, memos);
    }

    private int dfs(int index, int sum, boolean limited, String s, int minSum, int maxSum, int[][] memos) {
        if (sum > maxSum)
            return 0;

        if (index == s.length())
            return sum >= minSum ? 1 : 0;

        if (!limited && memos[index][sum] != -1)
            return memos[index][sum];

        int result = 0, upper = limited ? s.charAt(index) - '0' : 9;
        for (int i = 0; i <= upper; i++) {
            result += dfs(index + 1, sum + i, limited && (i == upper), s, minSum, maxSum, memos);
            result %= MOD;
        }

        if (!limited)
            memos[index][sum] = result;

        return result;
    }
}
// @lc code=end
