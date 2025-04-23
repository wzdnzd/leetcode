/*
 * @lc app=leetcode.cn id=2799 lang=java
 *
 * [2799] 统计完全子数组的数目
 *
 * https://leetcode.cn/problems/count-complete-subarrays-in-an-array/description/
 *
 * algorithms
 * Medium (66.22%)
 * Likes:    52
 * Dislikes: 0
 * Total Accepted:    16.8K
 * Total Submissions: 24.4K
 * Testcase Example:  '[1,3,1,2,2]'
 *
 * 给你一个由 正 整数组成的数组 nums 。
 * 
 * 如果数组中的某个子数组满足下述条件，则称之为 完全子数组 ：
 * 
 * 
 * 子数组中 不同 元素的数目等于整个数组不同元素的数目。
 * 
 * 
 * 返回数组中 完全子数组 的数目。
 * 
 * 子数组 是数组中的一个连续非空序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,3,1,2,2]
 * 输出：4
 * 解释：完全子数组有：[1,3,1,2]、[1,3,1,2,2]、[3,1,2] 和 [3,1,2,2] 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [5,5,5,5]
 * 输出：10
 * 解释：数组仅由整数 5 组成，所以任意子数组都满足完全子数组的条件。子数组的总数为 10 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2000
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int start = 0, end = 0;
        int distinct = set.size(), count = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int n = nums.length;
        while (end < n) {
            int num = nums[end];
            map.put(num, map.getOrDefault(num, 0) + 1);

            while (map.size() == distinct) {
                int prev = nums[start];
                map.put(prev, map.get(prev) - 1);
                if (map.get(prev) == 0)
                    map.remove(prev);

                start++;
            }

            end++;
            count += start;
        }

        return count;
    }
}
// @lc code=end
