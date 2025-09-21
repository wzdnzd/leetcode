/*
 * @lc app=leetcode.cn id=3005 lang=java
 *
 * [3005] 最大频率元素计数
 *
 * https://leetcode.cn/problems/count-elements-with-maximum-frequency/description/
 *
 * algorithms
 * Easy (70.62%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    12.2K
 * Total Submissions: 16.9K
 * Testcase Example:  '[1,2,2,3,1,4]'
 *
 * 给你一个由 正整数 组成的数组 nums 。
 * 
 * 返回数组 nums 中所有具有 最大 频率的元素的 总频率 。
 * 
 * 元素的 频率 是指该元素在数组中出现的次数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,2,3,1,4]
 * 输出：4
 * 解释：元素 1 和 2 的频率为 2 ，是数组中的最大频率。
 * 因此具有最大频率的元素在数组中的数量是 4 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3,4,5]
 * 输出：5
 * 解释：数组中的所有元素的频率都为 1 ，是最大频率。
 * 因此具有最大频率的元素在数组中的数量是 5 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int maxFreq = 0;
        for (int v : map.values())
            maxFreq = v > maxFreq ? v : maxFreq;

        int sum = 0;
        for (int v : map.values()) {
            if (v == maxFreq)
                sum += v;
        }

        return sum;
    }
}
// @lc code=end
