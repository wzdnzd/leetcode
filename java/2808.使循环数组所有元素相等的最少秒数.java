/*
 * @lc app=leetcode.cn id=2808 lang=java
 *
 * [2808] 使循环数组所有元素相等的最少秒数
 *
 * https://leetcode.cn/problems/minimum-seconds-to-equalize-a-circular-array/description/
 *
 * algorithms
 * Medium (36.78%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    7K
 * Total Submissions: 14.4K
 * Testcase Example:  '[1,2,1,2]'
 *
 * 给你一个下标从 0 开始长度为 n 的数组 nums 。
 * 
 * 每一秒，你可以对数组执行以下操作：
 * 
 * 
 * 对于范围在 [0, n - 1] 内的每一个下标 i ，将 nums[i] 替换成 nums[i] ，nums[(i - 1 + n) % n] 或者
 * nums[(i + 1) % n] 三者之一。
 * 
 * 
 * 注意，所有元素会被同时替换。
 * 
 * 请你返回将数组 nums 中所有元素变成相等元素所需要的 最少 秒数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,2,1,2]
 * 输出：1
 * 解释：我们可以在 1 秒内将数组变成相等元素：
 * - 第 1 秒，将每个位置的元素分别变为 [nums[3],nums[1],nums[3],nums[3]] 。变化后，nums = [2,2,2,2]
 * 。
 * 1 秒是将数组变成相等元素所需要的最少秒数。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [2,1,3,3,2]
 * 输出：2
 * 解释：我们可以在 2 秒内将数组变成相等元素：
 * - 第 1 秒，将每个位置的元素分别变为 [nums[0],nums[2],nums[2],nums[2],nums[3]] 。变化后，nums =
 * [2,3,3,3,3] 。
 * - 第 2 秒，将每个位置的元素分别变为 [nums[1],nums[1],nums[2],nums[3],nums[4]] 。变化后，nums =
 * [3,3,3,3,3] 。
 * 2 秒是将数组变成相等元素所需要的最少秒数。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [5,5,5,5]
 * 输出：0
 * 解释：不需要执行任何操作，因为一开始数组中的元素已经全部相等。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size(), result = n;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++)
            map.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);

        for (List<Integer> positions : map.values()) {
            int gap = positions.get(0) - positions.get(positions.size() - 1) + n;
            for (int i = 1; i < positions.size(); i++)
                gap = Math.max(gap, positions.get(i) - positions.get(i - 1));

            result = Math.min(result, gap / 2);
        }

        return result;
    }
}
// @lc code=end
