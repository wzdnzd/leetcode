/*
 * @lc app=leetcode.cn id=3116 lang=java
 *
 * [3116] 单面值组合的第 K 小金额
 *
 * https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination/description/
 *
 * algorithms
 * Hard (25.26%)
 * Likes:    21
 * Dislikes: 0
 * Total Accepted:    5.8K
 * Total Submissions: 19.3K
 * Testcase Example:  '[3,6,9]\n3'
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给你一个整数 k 。
 * 
 * 你有无限量的每种面额的硬币。但是，你 不能 组合使用不同面额的硬币。
 * 
 * 返回使用这些硬币能制造的 第 k^th 小 金额。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： coins = [3,6,9], k = 3
 * 
 * 输出： 9
 * 
 * 解释：给定的硬币可以制造以下金额：
 * 3元硬币产生3的倍数：3, 6, 9, 12, 15等。
 * 6元硬币产生6的倍数：6, 12, 18, 24等。
 * 9元硬币产生9的倍数：9, 18, 27, 36等。
 * 所有硬币合起来可以产生：3, 6, 9, 12, 15等。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：coins = [5,2], k = 7
 * 
 * 输出：12
 * 
 * 解释：给定的硬币可以制造以下金额：
 * 5元硬币产生5的倍数：5, 10, 15, 20等。
 * 2元硬币产生2的倍数：2, 4, 6, 8, 10, 12等。
 * 所有硬币合起来可以产生：2, 4, 5, 6, 8, 10, 12, 14, 15等。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= coins.length <= 15
 * 1 <= coins[i] <= 25
 * 1 <= k <= 2 * 10^9
 * coins 包含两两不同的整数。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long maxCoin = Arrays.stream(coins).max().getAsInt();
        long low = k, high = maxCoin * k;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long rank = getRank(coins, mid);

            if (rank >= k)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private long getRank(int[] coins, long amount) {
        long rank = 0;
        int length = coins.length;
        int total = 1 << length;

        for (int i = 1; i < total; i++) {
            long multiplier = 1;
            for (int j = 0; j < length && multiplier <= amount; j++) {
                if ((i & (1 << j)) != 0)
                    multiplier = lcm(multiplier, coins[j]);
            }

            int subsetSize = Integer.bitCount(i);
            if (subsetSize % 2 == 1)
                rank += amount / multiplier;
            else
                rank -= amount / multiplier;
        }

        return rank;
    }

    private long lcm(long x, long y) {
        return x * y / gcd(x, y);
    }

    private long gcd(long x, long y) {
        while (y != 0) {
            long temp = x;
            x = y;
            y = temp % y;
        }

        return x;
    }
}
// @lc code=end
