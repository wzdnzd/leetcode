/*
 * @lc app=leetcode.cn id=2826 lang=java
 *
 * [2826] 将三个组排序
 *
 * https://leetcode.cn/problems/sorting-three-groups/description/
 *
 * algorithms
 * Medium (53.07%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    3.7K
 * Total Submissions: 7K
 * Testcase Example:  '[2,1,3,2,1]'
 *
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 。
 * 
 * 从 0 到 n - 1 的数字被分为编号从 1 到 3 的三个组，数字 i 属于组 nums[i] 。注意，有的组可能是 空的 。
 * 
 * 你可以执行以下操作任意次：
 * 
 * 
 * 选择数字 x 并改变它的组。更正式的，你可以将 nums[x] 改为数字 1 到 3 中的任意一个。
 * 
 * 
 * 你将按照以下过程构建一个新的数组 res ：
 * 
 * 
 * 将每个组中的数字分别排序。
 * 将组 1 ，2 和 3 中的元素 依次 连接以得到 res 。
 * 
 * 
 * 如果得到的 res 是 非递减顺序的，那么我们称数组 nums 是 美丽数组 。
 * 
 * 请你返回将 nums 变为 美丽数组 需要的最少步数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,1,3,2,1]
 * 输出：3
 * 解释：以下三步操作是最优方案：
 * 1. 将 nums[0] 变为 1 。
 * 2. 将 nums[2] 变为 1 。
 * 3. 将 nums[3] 变为 1 。
 * 执行以上操作后，将每组中的数字排序，组 1 为 [0,1,2,3,4] ，组 2 和组 3 都为空。所以 res 等于 [0,1,2,3,4]
 * ，它是非递减顺序的。
 * 三步操作是最少需要的步数。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,3,2,1,3,3]
 * 输出：2
 * 解释：以下两步操作是最优方案：
 * 1. 将 nums[1] 变为 1 。
 * 2. 将 nums[2] 变为 1 。
 * 执行以上操作后，将每组中的数字排序，组 1 为 [0,1,2,3] ，组 2 为空，组 3 为 [4,5] 。所以 res 等于
 * [0,1,2,3,4,5] ，它是非递减顺序的。
 * 两步操作是最少需要的步数。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [2,2,2,2,3,3]
 * 输出：0
 * 解释：不需要执行任何操作。
 * 组 1 为空，组 2 为 [0,1,2,3] ，组 3 为 [4,5] 。所以 res 等于 [0,1,2,3,4,5] ，它是非递减顺序的。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 3
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumOperations(List<Integer> nums) {
        int[] dp = new int[4];
        for (int x : nums) {
            dp[x]++;
            dp[2] = Math.max(dp[2], dp[1]);
            dp[3] = Math.max(dp[3], dp[2]);
        }

        return nums.size() - Math.max(Math.max(dp[1], dp[2]), dp[3]);
    }
}
// @lc code=end
