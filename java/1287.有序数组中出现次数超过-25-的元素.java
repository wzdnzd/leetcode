/*
 * @lc app=leetcode.cn id=1287 lang=java
 *
 * [1287] 有序数组中出现次数超过25%的元素
 *
 * https://leetcode.cn/problems/element-appearing-more-than-25-in-sorted-array/description/
 *
 * algorithms
 * Easy (58.54%)
 * Likes:    76
 * Dislikes: 0
 * Total Accepted:    25.6K
 * Total Submissions: 43.7K
 * Testcase Example:  '[1,2,2,6,6,6,6,7,10]'
 *
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * 
 * 请你找到并返回这个整数
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = (int) (arr.length * 0.25);
        int ans = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == ans)
                count++;
            else {
                ans = arr[i];
                count = 1;
            }

            if (count > n)
                break;
        }

        return ans;
    }
}
// @lc code=end
