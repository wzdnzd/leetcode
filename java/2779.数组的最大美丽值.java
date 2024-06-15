/*
 * @lc app=leetcode.cn id=2779 lang=java
 *
 * [2779] 数组的最大美丽值
 *
 * https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation/description/
 *
 * algorithms
 * Medium (40.28%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    8.6K
 * Total Submissions: 19.7K
 * Testcase Example:  '[4,6,1,2]\n2'
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个 非负 整数 k 。
 * 
 * 在一步操作中，你可以执行下述指令：
 * 
 * 
 * 在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i 。
 * 将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
 * 
 * 
 * 数组的 美丽值 定义为数组中由相等元素组成的最长子序列的长度。
 * 
 * 对数组 nums 执行上述操作任意次后，返回数组可能取得的 最大 美丽值。
 * 
 * 注意：你 只 能对每个下标执行 一次 此操作。
 * 
 * 数组的 子序列 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [4,6,1,2], k = 2
 * 输出：3
 * 解释：在这个示例中，我们执行下述操作：
 * - 选择下标 1 ，将其替换为 4（从范围 [4,8] 中选出），此时 nums = [4,4,1,2] 。
 * - 选择下标 3 ，将其替换为 4（从范围 [0,4] 中选出），此时 nums = [4,4,1,4] 。
 * 执行上述操作后，数组的美丽值是 3（子序列由下标 0 、1 、3 对应的元素组成）。
 * 可以证明 3 是我们可以得到的由相等元素组成的最长子序列长度。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,1,1,1], k = 10
 * 输出：4
 * 解释：在这个示例中，我们无需执行任何操作。
 * 数组 nums 的美丽值是 4（整个数组）。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i], k <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0, result = 0;
        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            while (x - nums[left] > k * 2)
                left++;

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
// @lc code=end
