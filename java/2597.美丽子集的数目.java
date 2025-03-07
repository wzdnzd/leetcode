/*
 * @lc app=leetcode.cn id=2597 lang=java
 *
 * [2597] 美丽子集的数目
 *
 * https://leetcode.cn/problems/the-number-of-beautiful-subsets/description/
 *
 * algorithms
 * Medium (37.75%)
 * Likes:    59
 * Dislikes: 0
 * Total Accepted:    10.6K
 * Total Submissions: 26.3K
 * Testcase Example:  '[2,4,6]\n2'
 *
 * 给你一个由正整数组成的数组 nums 和一个 正 整数 k 。
 * 
 * 如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。
 * 
 * 返回数组 nums 中 非空 且 美丽 的子集数目。
 * 
 * nums 的子集定义为：可以经由 nums
 * 删除某些元素（也可能不删除）得到的一个数组。只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [2,4,6], k = 2
 * 输出：4
 * 解释：数组 nums 中的美丽子集有：[2], [4], [6], [2, 6] 。
 * 可以证明数组 [2,4,6] 中只存在 4 个美丽子集。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1], k = 1
 * 输出：1
 * 解释：数组 nums 中的美丽数组有：[1] 。
 * 可以证明数组 [1] 中只存在 1 个美丽子集。 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 18
 * 1 <= nums[i], k <= 1000
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        List<Set<Integer>> remainderNums = new ArrayList<>(k);
        for (int i = 0; i < k; i++)
            remainderNums.add(new HashSet<Integer>());

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            remainderNums.get(num % k).add(num);
        }

        int subsets = 1;
        for (int i = 0; i < k; i++) {
            List<Integer> list = new ArrayList<Integer>(remainderNums.get(i));
            Collections.sort(list);
            subsets *= countBeautifulSubsets(list, counts, k);
        }

        return subsets - 1;
    }

    private int countBeautifulSubsets(List<Integer> nums, Map<Integer, Integer> map, int k) {
        int m = nums.size();
        int[] dp = new int[m + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            int num = nums.get(i - 1);
            int count = map.get(num);

            if (i > 1 && num - nums.get(i - 2) == k)
                dp[i] = dp[i - 1] + dp[i - 2] * ((1 << count) - 1);
            else
                dp[i] = dp[i - 1] << count;
        }

        return dp[m];
    }
}
// @lc code=end
