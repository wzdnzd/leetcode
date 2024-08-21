/*
 * @lc app=leetcode.cn id=3007 lang=java
 *
 * [3007] 价值和小于等于 K 的最大数字
 *
 * https://leetcode.cn/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/description/
 *
 * algorithms
 * Medium (41.43%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 11.3K
 * Testcase Example:  '9\n1'
 *
 * 给你一个整数 k 和一个整数 x 。整数 num 的价值是它的二进制表示中在 x，2x，3x 等位置处 设置位
 * 的数目（从最低有效位开始）。下面的表格包含了如何计算价值的例子。
 * 
 * 
 * 
 * 
 * x
 * num
 * Binary Representation
 * Price
 * 
 * 
 * 1
 * 13
 * 000001101
 * 3
 * 
 * 
 * 2
 * 13
 * 000001101
 * 1
 * 
 * 
 * 2
 * 233
 * 011101001
 * 3
 * 
 * 
 * 3
 * 13
 * 000001101
 * 1
 * 
 * 
 * 3
 * 362
 * 101101010
 * 2
 * 
 * 
 * 
 * 
 * 
 * 
 * num 的 累加价值 是从 1 到 num 的数字的 总 价值。如果 num 的累加价值小于或等于 k 则被认为是 廉价 的。
 * 
 * 请你返回 最大 的廉价数字。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：k = 9, x = 1
 * 输出：6
 * 解释：由下表所示，6 是最大的廉价数字。
 * 
 * 
 * 
 * 
 * 
 * x
 * num
 * Binary Representation
 * Price
 * Accumulated Price
 * 
 * 
 * 1
 * 1
 * 001
 * 1
 * 1
 * 
 * 
 * 1
 * 2
 * 010
 * 1
 * 2
 * 
 * 
 * 1
 * 3
 * 011
 * 2
 * 4
 * 
 * 
 * 1
 * 4
 * 100
 * 1
 * 5
 * 
 * 
 * 1
 * 5
 * 101
 * 2
 * 7
 * 
 * 
 * 1
 * 6
 * 110
 * 2
 * 9
 * 
 * 
 * 1
 * 7
 * 111
 * 3
 * 12
 * 
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：k = 7, x = 2
 * 输出：9
 * 解释：由下表所示，9 是最大的廉价数字。
 * 
 * 
 * 
 * 
 * 
 * x
 * num
 * Binary Representation
 * Price
 * Accumulated Price
 * 
 * 
 * 2
 * 1
 * 0001
 * 0
 * 0
 * 
 * 
 * 2
 * 2
 * 0010
 * 1
 * 1
 * 
 * 
 * 2
 * 3
 * 0011
 * 1
 * 2
 * 
 * 
 * 2
 * 4
 * 0100
 * 0
 * 2
 * 
 * 
 * 2
 * 5
 * 0101
 * 0
 * 2
 * 
 * 
 * 2
 * 6
 * 0110
 * 1
 * 3
 * 
 * 
 * 2
 * 7
 * 0111
 * 1
 * 4
 * 
 * 
 * 2
 * 8
 * 1000
 * 1
 * 5
 * 
 * 
 * 2
 * 9
 * 1001
 * 1
 * 6
 * 
 * 
 * 2
 * 10
 * 1010
 * 2
 * 8
 * 
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= k <= 10^15
 * 1 <= x <= 8
 * 
 * 
 */

// @lc code=start
class Solution {
    public long findMaximumNumber(long k, int x) {
        long l = 1, r = (k + 1) << x;

        while (l < r) {
            long m = (l + r + 1) / 2;

            if (accumulatedPrice(x, m) > k)
                r = m - 1;
            else
                l = m;
        }

        return l;
    }

    public long accumulatedPrice(int x, long num) {
        long result = 0;
        int length = 64 - Long.numberOfLeadingZeros(num);

        for (int i = x; i <= length; i += x)
            result += accumulatedBitPrice(i, num);

        return result;
    }

    public long accumulatedBitPrice(int x, long num) {
        long period = 1L << x;
        long result = period / 2 * (num / period);

        if (num % period >= period / 2)
            result += num % period - (period / 2 - 1);

        return result;
    }
}
// @lc code=end
