/*
 * @lc app=leetcode.cn id=1870 lang=java
 *
 * [1870] 准时到达的列车最小时速
 *
 * https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/description/
 *
 * algorithms
 * Medium (42.35%)
 * Likes:    74
 * Dislikes: 0
 * Total Accepted:    19K
 * Total Submissions: 43.8K
 * Testcase Example:  '[1,3,2]\n6'
 *
 * 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist
 * ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
 * 
 * 每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
 * 
 * 
 * 例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
 * 
 * 
 * 返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
 * 
 * 生成的测试用例保证答案不超过 10^7 ，且 hour 的 小数点后最多存在两位数字 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：dist = [1,3,2], hour = 6
 * 输出：1
 * 解释：速度为 1 时：
 * - 第 1 趟列车运行需要 1/1 = 1 小时。
 * - 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。
 * - 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。
 * - 你将会恰好在第 6 小时到达。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：dist = [1,3,2], hour = 2.7
 * 输出：3
 * 解释：速度为 3 时：
 * - 第 1 趟列车运行需要 1/3 = 0.33333 小时。
 * - 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。
 * - 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。
 * - 你将会在第 2.66667 小时到达。
 * 
 * 示例 3：
 * 
 * 
 * 输入：dist = [1,3,2], hour = 1.9
 * 输出：-1
 * 解释：不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == dist.length
 * 1 
 * 1 
 * 1 
 * hours 中，小数点后最多存在两位数字
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        long upper = Math.round(hour * 100);
        if (upper <= (n - 1) * 100)
            return -1;

        int left = 1, right = 10000000;
        while (left < right) {
            int mid = left + (right - left) / 2;

            long cost = 0;
            for (int i = 0; i < n - 1; ++i)
                cost += (dist[i] - 1) / mid + 1;

            cost *= mid;
            cost += dist[n - 1];
            if (cost * 100 <= upper * mid)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}
// @lc code=end
