/*
 * @lc app=leetcode.cn id=3134 lang=java
 *
 * [3134] 找出唯一性数组的中位数
 *
 * https://leetcode.cn/problems/find-the-median-of-the-uniqueness-array/description/
 *
 * algorithms
 * Hard (43.30%)
 * Likes:    16
 * Dislikes: 0
 * Total Accepted:    3K
 * Total Submissions: 7K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个整数数组 nums 。数组 nums 的 唯一性数组 是一个按元素从小到大排序的数组，包含了 nums 的所有非空子数组中不同元素的个数。
 * 
 * 换句话说，这是由所有 0 <= i <= j < nums.length 的 distinct(nums[i..j]) 组成的递增数组。
 * 
 * 其中，distinct(nums[i..j]) 表示从下标 i 到下标 j 的子数组中不同元素的数量。
 * 
 * 返回 nums 唯一性数组 的 中位数 。
 * 
 * 注意，数组的 中位数 定义为有序数组的中间元素。如果有两个中间元素，则取值较小的那个。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 
 * 输出：1
 * 
 * 解释：
 * 
 * nums 的唯一性数组为 [distinct(nums[0..0]), distinct(nums[1..1]),
 * distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]),
 * distinct(nums[0..2])]，即 [1, 1, 1, 2, 2, 3] 。唯一性数组的中位数为 1 ，因此答案是 1 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [3,4,3,4,5]
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * nums 的唯一性数组为 [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3] 。唯一性数组的中位数为 2
 * ，因此答案是 2 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [4,3,5,4]
 * 
 * 输出：2
 * 
 * 解释：
 * 
 * nums 的唯一性数组为 [1, 1, 1, 1, 2, 2, 2, 3, 3, 3] 。唯一性数组的中位数为 2 ，因此答案是 2 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        long totalSubarrays = (long) n * (n + 1) / 2;
        long medianIndex = (totalSubarrays + 1) / 2;

        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (checkUniqueness(nums, mid, medianIndex))
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    public boolean checkUniqueness(int[] nums, int maxUniqueness, long medianIndex) {
        long count = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        int start = 0, end = 0;
        int n = nums.length;

        while (end < n && count < medianIndex) {
            counts.put(nums[end], counts.getOrDefault(nums[end], 0) + 1);
            while (counts.size() > maxUniqueness) {
                counts.put(nums[start], counts.get(nums[start]) - 1);
                if (counts.get(nums[start]) == 0)
                    counts.remove(nums[start]);

                start++;
            }

            count += end - start + 1;
            end++;
        }

        return count >= medianIndex;
    }
}

// @lc code=end
