/*
 * @lc app=leetcode.cn id=2200 lang=java
 *
 * [2200] 找出数组中的所有 K 近邻下标
 *
 * https://leetcode.cn/problems/find-all-k-distant-indices-in-an-array/description/
 *
 * algorithms
 * Easy (54.28%)
 * Likes:    20
 * Dislikes: 0
 * Total Accepted:    12.9K
 * Total Submissions: 23.8K
 * Testcase Example:  '[3,4,9,1,3,9,5]\n9\n1'
 *
 * 给你一个下标从 0 开始的整数数组 nums 和两个整数 key 和 k 。K 近邻下标 是 nums 中的一个下标 i ，并满足至少存在一个下标 j
 * 使得 |i - j| <= k 且 nums[j] == key 。
 * 
 * 以列表形式返回按 递增顺序 排序的所有 K 近邻下标。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3,4,9,1,3,9,5], key = 9, k = 1
 * 输出：[1,2,3,4,5,6]
 * 解释：因此，nums[2] == key 且 nums[5] == key 。
 * - 对下标 0 ，|0 - 2| > k 且 |0 - 5| > k ，所以不存在 j 使得 |0 - j| <= k 且 nums[j] == key
 * 。所以 0 不是一个 K 近邻下标。
 * - 对下标 1 ，|1 - 2| <= k 且 nums[2] == key ，所以 1 是一个 K 近邻下标。
 * - 对下标 2 ，|2 - 2| <= k 且 nums[2] == key ，所以 2 是一个 K 近邻下标。
 * - 对下标 3 ，|3 - 2| <= k 且 nums[2] == key ，所以 3 是一个 K 近邻下标。
 * - 对下标 4 ，|4 - 5| <= k 且 nums[5] == key ，所以 4 是一个 K 近邻下标。
 * - 对下标 5 ，|5 - 5| <= k 且 nums[5] == key ，所以 5 是一个 K 近邻下标。
 * - 对下标 6 ，|6 - 5| <= k 且 nums[5] == key ，所以 6 是一个 K 近邻下标。
 * 因此，按递增顺序返回 [1,2,3,4,5,6] 。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,2,2,2,2], key = 2, k = 2
 * 输出：[0,1,2,3,4]
 * 解释：对 nums 的所有下标 i ，总存在某个下标 j 使得 |i - j| <= k 且 nums[j] == key ，所以每个下标都是一个 K
 * 近邻下标。 
 * 因此，返回 [0,1,2,3,4] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * key 是数组 nums 中的一个整数
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length, left = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == key) {
                left = Math.max(left, right - k);
                int end = Math.min(right + k, n - 1);
                while (left <= end)
                    result.add(left++);
            }
        }

        return result;
    }
}
// @lc code=end
