import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=698 lang=java
 *
 * [698] 划分为k个相等的子集
 *
 * https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/description/
 *
 * algorithms
 * Medium (41.36%)
 * Likes:    831
 * Dislikes: 0
 * Total Accepted:    87.3K
 * Total Submissions: 205K
 * Testcase Example:  '[4,3,2,3,5,2,1]\n4'
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 
 * 示例 2:
 * 
 * 
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 * 
 * 
 */

// @lc code=start
class Solution {
    // public boolean canPartitionKSubsets(int[] nums, int k) {
    // if (nums.length < k)
    // return false;
    // int sum = 0;
    // for (int num : nums)
    // sum += num;

    // if (sum % k != 0)
    // return false;

    // int[] buckets = new int[k];
    // return backtrace(nums, buckets, sum / k, 0);
    // }

    // private boolean backtrace(int[] nums, int[] buckets, int target, int index) {
    // if (index == nums.length) {
    // for (int i = 0; i < buckets.length; i++) {
    // if (buckets[i] != target)
    // return false;
    // }

    // return true;
    // }

    // for (int i = 0; i < buckets.length; i++) {
    // if (buckets[i] + nums[index] > target)
    // continue;
    // buckets[i] += nums[index];
    // if (backtrace(nums, buckets, target, index + 1))
    // return true;
    // buckets[i] -= nums[index];
    // }

    // return false;
    // }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k)
            return false;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;

        Map<Integer, Boolean> memo = new HashMap<>();
        return backtrace(nums, 0, k, memo, 0, sum / k, 0);
    }

    private boolean backtrace(int[] nums, int bucket, int k,
            Map<Integer, Boolean> memo, int start, int target, int used) {
        if (k == 0)
            return true;
        if (bucket == target) {
            boolean ans = backtrace(nums, 0, k - 1, memo, 0, target, used);
            memo.put(used, ans);
            return ans;
        }
        if (memo.containsKey(used))
            return memo.get(used);
        for (int i = start; i < nums.length; i++) {
            // 判断第 i 位是否是 1，为1表示该数值已经被其他组占用
            if (((used >>> i) & 1) == 1)
                continue;
            if (bucket + nums[i] > target)
                continue;

            used |= 1 << i;
            bucket += nums[i];
            if (backtrace(nums, bucket, k, memo, i + 1, target, used))
                return true;
            used ^= 1 << i;
            bucket -= nums[i];
        }
        return false;
    }
}
// @lc code=end
