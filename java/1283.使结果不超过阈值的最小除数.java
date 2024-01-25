/*
 * @lc app=leetcode.cn id=1283 lang=java
 *
 * [1283] 使结果不超过阈值的最小除数
 *
 * https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/description/
 *
 * algorithms
 * Medium (50.28%)
 * Likes:    105
 * Dislikes: 0
 * Total Accepted:    16.5K
 * Total Submissions: 32.8K
 * Testcase Example:  '[1,2,5,9]\n6'
 *
 * 给你一个整数数组 nums 和一个正整数 threshold  ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。
 * 
 * 请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。
 * 
 * 每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。
 * 
 * 题目保证一定有解。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,5,9], threshold = 6
 * 输出：5
 * 解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
 * 如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,3,5,7,11], threshold = 11
 * 输出：3
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [19], threshold = 5
 * 输出：4
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <= threshold <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int upper = 0;
        for (int num : nums)
            upper = Math.max(num, upper);

        int result = upper;
        int left = 1, right = upper;

        while (left <= right) {
            int mid = (left + right) >> 1;
            int total = 0;

            for (int i = 0; i < nums.length; i++)
                total += nums[i] % mid == 0 ? nums[i] / mid : (nums[i] / mid + 1);

            if (total <= threshold) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else if (total > threshold)
                left = mid + 1;
            else
                return mid;
        }

        return result;
    }
}
// @lc code=end
