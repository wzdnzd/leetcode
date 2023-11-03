/*
 * @lc app=leetcode.cn id=2843 lang=java
 *
 * [2843] 统计对称整数的数目
 *
 * https://leetcode.cn/problems/count-symmetric-integers/description/
 *
 * algorithms
 * Easy (70.86%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    7.2K
 * Total Submissions: 10.2K
 * Testcase Example:  '1\n100'
 *
 * 给你两个正整数 low 和 high 。
 * 
 * 对于一个由 2 * n 位数字组成的整数 x ，如果其前 n 位数字之和与后 n 位数字之和相等，则认为这个数字是一个对称整数。
 * 
 * 返回在 [low, high] 范围内的 对称整数的数目 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：low = 1, high = 100
 * 输出：9
 * 解释：在 1 到 100 范围内共有 9 个对称整数：11、22、33、44、55、66、77、88 和 99 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：low = 1200, high = 1230
 * 输出：4
 * 解释：在 1200 到 1230 范围内共有 4 个对称整数：1203、1212、1221 和 1230 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= low <= high <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            char[] chars = Integer.toString(i).toCharArray();
            int n = chars.length;

            if (n % 2 > 0)
                continue;

            int sum = 0;
            for (int j = 0; j < n / 2; j++)
                sum += chars[j];

            for (int j = n / 2; j < n; j++)
                sum -= chars[j];

            if (sum == 0)
                count++;
        }

        return count;
    }
}
// @lc code=end
