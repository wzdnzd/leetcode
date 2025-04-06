/*
 * @lc app=leetcode.cn id=368 lang=java
 *
 * [368] 最大整除子集
 *
 * https://leetcode.cn/problems/largest-divisible-subset/description/
 *
 * algorithms
 * Medium (46.01%)
 * Likes:    581
 * Dislikes: 0
 * Total Accepted:    66.4K
 * Total Submissions: 143.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i],
 * answer[j]) 都应当满足：
 * 
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 
 * 
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * nums 中的所有整数 互不相同
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, maxSubsetSize = 1, maxIndex = 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }

            if (dp[i] > maxSubsetSize) {
                maxSubsetSize = dp[i];
                maxIndex = i;
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        int index = maxIndex;
        while (index >= 0) {
            result.add(nums[index]);
            index = prev[index];
        }

        Collections.reverse(result);
        return result;
    }
}
// @lc code=end
