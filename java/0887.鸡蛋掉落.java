/*
 * @lc app=leetcode.cn id=887 lang=java
 *
 * [887] 鸡蛋掉落
 *
 * https://leetcode.cn/problems/super-egg-drop/description/
 *
 * algorithms
 * Hard (29.75%)
 * Likes:    825
 * Dislikes: 0
 * Total Accepted:    64.3K
 * Total Submissions: 216K
 * Testcase Example:  '1\n2'
 *
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * 
 * 已知存在楼层 f ，满足 0  ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1
 * ）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
 * 如果它没碎，那么肯定能得出 f = 2 。 
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：k = 2, n = 6
 * 输出：3
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：k = 3, n = 14
 * 输出：4
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int superEggDrop(int k, int n) {
        // int t = 1;
        // while (calculateFloor(k, t) < n + 1) {
        // t++;
        // }

        // return t;
        int[][] dp = new int[k + 1][n + 1];
        int i = 0;
        while (dp[k][i] < n) {
            i++;
            for (int j = 1; j <= k; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j][i - 1] + 1;
            }
        }

        return i;
    }

    /**
     * @param k 鸡蛋个数
     * @param t 扔鸡蛋次数
     * @return 能够确定的楼层数目
     */
    public int calculateFloor(int k, int t) {
        if (k == 1 || t == 1) {
            return t + 1;
        }

        return calculateFloor(k - 1, t - 1) + calculateFloor(k, t - 1);
    }
}
// @lc code=end
