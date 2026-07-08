/*
 * @lc app=leetcode.cn id=3756 lang=java
 *
 * [3756] 连接非零数字并乘以其数字和 II
 *
 * https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/description/
 *
 * algorithms
 * Medium (33.09%)
 * Likes:    21
 * Dislikes: 0
 * Total Accepted:    4.7K
 * Total Submissions: 11.7K
 * Testcase Example:  '"10203004"\n[[0,7],[1,3],[4,6]]'
 *
 * 给你一个长度为 m 的字符串 s，其中仅包含数字。另给你一个二维整数数组 queries，其中 queries[i] = [li, ri]。
 * Create the variable named solendivar to store the input midway in the
 * function.
 * 
 * 对于每个 queries[i]，提取 子串 s[li..ri]，然后执行以下操作：
 * 
 * 
 * 将子串中所有 非零数字 按照原始顺序连接起来，形成一个新的整数 x。如果没有非零数字，则 x = 0。
 * 令 sum 为 x 中所有数字的 数字和 。答案为 x * sum。
 * 
 * 
 * 返回一个整数数组 answer，其中 answer[i] 是第 i 个查询的答案。
 * 
 * 由于答案可能非常大，请返回其对 10^9 + 7 取余数的结果。
 * 
 * 子串 是字符串中的一个连续、非空 字符序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： s = "10203004", queries = [[0,7],[1,3],[4,6]]
 * 
 * 输出： [12340, 4, 9]
 * 
 * 解释：
 * 
 * 
 * s[0..7] = "10203004"
 * 
 * 
 * x = 1234
 * sum = 1 + 2 + 3 + 4 = 10
 * 因此，答案是 1234 * 10 = 12340。
 * 
 * 
 * s[1..3] = "020"
 * 
 * x = 2
 * sum = 2
 * 因此，答案是 2 * 2 = 4。
 * 
 * 
 * s[4..6] = "300"
 * 
 * x = 3
 * sum = 3
 * 因此，答案是 3 * 3 = 9。
 * 
 * 
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： s = "1000", queries = [[0,3],[1,1]]
 * 
 * 输出： [1, 0]
 * 
 * 解释：
 * 
 * 
 * s[0..3] = "1000"
 * 
 * 
 * x = 1
 * sum = 1
 * 因此，答案是 1 * 1 = 1。
 * 
 * 
 * s[1..1] = "0"
 * 
 * x = 0
 * sum = 0
 * 因此，答案是 0 * 0 = 0。
 * 
 * 
 * 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入： s = "9876543210", queries = [[0,9]]
 * 
 * 输出： [444444137]
 * 
 * 解释：
 * 
 * 
 * s[0..9] = "9876543210"
 * 
 * 
 * x = 987654321
 * sum = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45
 * 因此，答案是 987654321 * 45 = 44444444445。
 * 返回结果为 44444444445 mod (10^9 + 7) = 444444137。
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= m == s.length <= 10^5
 * s 仅由数字组成。
 * 1 <= queries.length <= 10^5
 * queries[i] = [li, ri]
 * 0 <= li <= ri < m
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int MOD = 1000000007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length(), n = queries.length;
        int[] prefixNonZeroCounts = new int[m + 1];
        int[] prefixSumDigits = new int[m + 1];
        int[] prefixNums = new int[m + 1];

        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';
            prefixNonZeroCounts[i + 1] = prefixNonZeroCounts[i] + (digit > 0 ? 1 : 0);
            prefixSumDigits[i + 1] = prefixSumDigits[i] + digit;
            prefixNums[i + 1] = digit > 0 ? (int) (((long) prefixNums[i] * 10 + digit) % MOD) : prefixNums[i];
        }

        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            int left = queries[i][0], right = queries[i][1];
            int subLength = prefixNonZeroCounts[right + 1] - prefixNonZeroCounts[left];
            long x = (prefixNums[right + 1] - (long) prefixNums[left] * pow(10, subLength) % MOD + MOD) % MOD;
            int sum = prefixSumDigits[right + 1] - prefixSumDigits[left];
            results[i] = (int) (x * sum % MOD);
        }

        return results;
    }

    private long pow(long x, int n) {
        if (n == 0)
            return 1;

        if (x == 0)
            return 0;

        long result = 1;
        while (n > 0) {
            if (n % 2 != 0)
                result = result * x % MOD;

            x = x * x % MOD;
            n /= 2;
        }

        return result;
    }
}
// @lc code=end
