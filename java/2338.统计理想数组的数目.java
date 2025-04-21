/*
 * @lc app=leetcode.cn id=2338 lang=java
 *
 * [2338] 统计理想数组的数目
 *
 * https://leetcode.cn/problems/count-the-number-of-ideal-arrays/description/
 *
 * algorithms
 * Hard (31.47%)
 * Likes:    60
 * Dislikes: 0
 * Total Accepted:    4.2K
 * Total Submissions: 12.1K
 * Testcase Example:  '2\n5'
 *
 * 给你两个整数 n 和 maxValue ，用于描述一个 理想数组 。
 * 
 * 对于下标从 0 开始、长度为 n 的整数数组 arr ，如果满足以下条件，则认为该数组是一个 理想数组 ：
 * 
 * 
 * 每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。
 * 每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。
 * 
 * 
 * 返回长度为 n 的 不同 理想数组的数目。由于答案可能很大，返回对 10^9 + 7 取余的结果。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 2, maxValue = 5
 * 输出：10
 * 解释：存在以下理想数组：
 * - 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
 * - 以 2 开头的数组（2 个）：[2,2]、[2,4]
 * - 以 3 开头的数组（1 个）：[3,3]
 * - 以 4 开头的数组（1 个）：[4,4]
 * - 以 5 开头的数组（1 个）：[5,5]
 * 共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 5, maxValue = 3
 * 输出：11
 * 解释：存在以下理想数组：
 * - 以 1 开头的数组（9 个）：
 * ⁠  - 不含其他不同值（1 个）：[1,1,1,1,1] 
 * ⁠  - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
 * ⁠  - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
 * - 以 2 开头的数组（1 个）：[2,2,2,2,2]
 * - 以 3 开头的数组（1 个）：[3,3,3,3,3]
 * 共计 9 + 1 + 1 = 11 个不同理想数组。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= n <= 10^4
 * 1 <= maxValue <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int MODE = 1000000007;
    private int[][] combinations;

    public int idealArrays(int n, int maxValue) {
        int temp = maxValue;
        int maxPrimesCount = 0;

        while (temp > 1) {
            maxPrimesCount++;
            temp /= 2;
        }

        combinations = new int[n + maxPrimesCount + 1][maxPrimesCount + 1];
        combinations[0][0] = 1;

        for (int i = 1; i <= n + maxPrimesCount; i++) {
            combinations[i][0] = 1;
            if (i <= maxPrimesCount) {
                combinations[i][i] = 1;
            }

            int bound = Math.min(i - 1, maxPrimesCount);
            for (int j = 1; j <= bound; j++) {
                combinations[i][j] = (combinations[i - 1][j - 1] + combinations[i - 1][j]) % MODE;
            }
        }

        int ideal = 0;
        for (int num = 1; num <= maxValue; num++) {
            int count = countIdeal(n, num);
            ideal = (ideal + count) % MODE;
        }

        return ideal;
    }

    private int countIdeal(int n, int num) {
        long total = 1;
        for (int i = 2; i <= num; i = next(i, num)) {
            int exponential = 0;
            while (num % i == 0) {
                exponential++;
                num /= i;
            }

            if (exponential > 0) {
                int count = combinations[exponential + n - 1][exponential];
                total = total * count % MODE;
            }
        }

        return (int) total;
    }

    private int next(int current, int num) {
        if (current * current > num && current < num)
            return num;
        else
            return current == 2 ? current + 1 : current + 2;
    }
}
// @lc code=end
