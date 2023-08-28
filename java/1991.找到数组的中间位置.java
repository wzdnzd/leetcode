/*
 * @lc app=leetcode.cn id=1991 lang=java
 *
 * [1991] 找到数组的中间位置
 *
 * https://leetcode.cn/problems/find-the-middle-index-in-array/description/
 *
 * algorithms
 * Easy (63.69%)
 * Likes:    65
 * Dislikes: 0
 * Total Accepted:    23.1K
 * Total Submissions: 36.3K
 * Testcase Example:  '[2,3,-1,8,4]'
 *
 * 给你一个下标从 0 开始的整数数组 nums ，请你找到 最左边 的中间位置 middleIndex （也就是所有可能中间位置下标最小的一个）。
 * 
 * 中间位置 middleIndex 是满足 nums[0] + nums[1] + ... + nums[middleIndex-1] ==
 * nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1]
 * 的数组下标。
 * 
 * 如果 middleIndex == 0 ，左边部分的和定义为 0 。类似的，如果 middleIndex == nums.length - 1
 * ，右边部分的和定义为 0 。
 * 
 * 请你返回满足上述条件 最左边 的 middleIndex ，如果不存在这样的中间位置，请你返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,3,-1,8,4]
 * 输出：3
 * 解释：
 * 下标 3 之前的数字和为：2 + 3 + -1 = 4
 * 下标 3 之后的数字和为：4 = 4
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,-1,4]
 * 输出：2
 * 解释：
 * 下标 2 之前的数字和为：1 + -1 = 0
 * 下标 2 之后的数字和为：0
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [2,5]
 * 输出：-1
 * 解释：
 * 不存在符合要求的 middleIndex 。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：nums = [1]
 * 输出：0
 * 解释：
 * 下标 0 之前的数字和为：0
 * 下标 0 之后的数字和为：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * -1000 <= nums[i] <= 1000
 * 
 * 
 * 
 * 
 * 注意：本题与主站 724 题相同：https://leetcode-cn.com/problems/find-pivot-index/
 * 
 */

// @lc code=start
class Solution {
    public int findMiddleIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int left = 0, right = total;
        for (int i = 0; i < nums.length; i++) {
            right -= nums[i];
            if (left == right)
                return i;

            left += nums[i];
        }
        
        return -1;
    }
}
// @lc code=end

