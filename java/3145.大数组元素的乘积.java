/*
 * @lc app=leetcode.cn id=3145 lang=java
 *
 * [3145] 大数组元素的乘积
 *
 * https://leetcode.cn/problems/find-products-of-elements-of-big-array/description/
 *
 * algorithms
 * Hard (46.24%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    5.6K
 * Total Submissions: 8.3K
 * Testcase Example:  '[[1,3,7]]'
 *
 * 一个非负整数 x 的 强数组 指的是满足元素为 2 的幂且元素总和为 x 的最短有序数组。下表说明了如何确定 强数组 的示例。可以证明，x
 * 对应的强数组是独一无二的。
 * 
 * 
 * 
 * 
 * 数字
 * 二进制表示
 * 强数组
 * 
 * 
 * 1
 * 00001
 * [1]
 * 
 * 
 * 8
 * 01000
 * [8]
 * 
 * 
 * 10
 * 01010
 * [2, 8]
 * 
 * 
 * 13
 * 01101
 * [1, 4, 8]
 * 
 * 
 * 23
 * 10111
 * [1, 2, 4, 16]
 * 
 * 
 * 
 * 
 * 
 * 
 * 我们将每一个升序的正整数 i （即1，2，3等等）的 强数组 连接得到数组 big_nums ，big_nums 开始部分为 [1, 2, 1, 2,
 * 4, 1, 4, 2, 4, 1, 2, 4, 8, ...] 。
 * 
 * 给你一个二维整数数组 queries ，其中 queries[i] = [fromi, toi, modi] ，你需要计算
 * (big_nums[fromi] * big_nums[fromi + 1] * ... * big_nums[toi]) % modi 。
 * 
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：queries = [[1,3,7]]
 * 
 * 输出：[4]
 * 
 * 解释：
 * 
 * 只有一个查询。
 * 
 * big_nums[1..3] = [2,1,2] 。它们的乘积为 4。结果为 4 % 7 = 4。
 * 
 * 示例 2：
 * 
 * 输入：queries = [[2,5,3],[7,7,4]]
 * 
 * 输出：[2,2]
 * 
 * 解释：
 * 
 * 有两个查询。
 * 
 * 第一个查询：big_nums[2..5] = [1,2,4,1] 。它们的乘积为 8 。结果为  8 % 3 = 2。
 * 
 * 第二个查询：big_nums[7] = 2 。结果为 2 % 4 = 2。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= queries.length <= 500
 * queries[i].length == 3
 * 0 <= queries[i][0] <= queries[i][1] <= 10^15
 * 1 <= queries[i][2] <= 10^5
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findProductsOfElements(long[][] queries) {
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long[] query = queries[i];

            long er = sumE(query[1] + 1);
            long el = sumE(query[0]);

            result[i] = pow(2, er - el, query[2]);
        }

        return result;
    }

    private long sumE(long k) {
        long result = 0L, n = 0L, count = 0L, sumI = 0L;
        for (long i = 63 - Long.numberOfLeadingZeros(k + 1); i >= 0; i--) {
            long c = (count << i) + (i << i >> 1);
            if (c <= k) {
                k -= c;
                result += (sumI << i) + ((i * (i - 1) / 2) << i >> 1);
                sumI += i;
                count++;
                n |= 1L << i;
            }
        }

        while (k-- > 0) {
            result += Long.numberOfTrailingZeros(n);
            n &= n - 1;
        }

        return result;
    }

    private int pow(long x, long n, long mod) {
        long result = 1 % mod;
        for (; n > 0; n /= 2) {
            if (n % 2 == 1)
                result = result * x % mod;

            x = x * x % mod;
        }

        return (int) result;
    }
}

// @lc code=end
