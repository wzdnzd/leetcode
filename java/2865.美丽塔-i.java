/*
 * @lc app=leetcode.cn id=2865 lang=java
 *
 * [2865] 美丽塔 I
 *
 * https://leetcode.cn/problems/beautiful-towers-i/description/
 *
 * algorithms
 * Medium (49.35%)
 * Likes:    21
 * Dislikes: 0
 * Total Accepted:    7.4K
 * Total Submissions: 14K
 * Testcase Example:  '[5,3,4,1,1]'
 *
 * 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
 * 
 * 你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
 * 
 * 如果以下条件满足，我们称这些塔是 美丽 的：
 * 
 * 
 * 1 <= heights[i] <= maxHeights[i]
 * heights 是一个 山脉 数组。
 * 
 * 
 * 如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 数组：
 * 
 * 
 * 对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
 * 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 
 * 
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：maxHeights = [5,3,4,1,1]
 * 输出：13
 * 解释：和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]  
 * - heights 是个山脉数组，峰值在 i = 0 处。
 * 13 是所有美丽塔方案中的最大高度和。
 * 
 * 示例 2：
 * 
 * 
 * 输入：maxHeights = [6,5,3,9,2,7]
 * 输出：22
 * 解释： 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，峰值在 i = 3 处。
 * 22 是所有美丽塔方案中的最大高度和。
 * 
 * 示例 3：
 * 
 * 
 * 输入：maxHeights = [3,2,5,5,2,3]
 * 输出：18
 * 解释：和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，最大值在 i = 2 处。
 * 注意，在这个方案中，i = 3 也是一个峰值。
 * 18 是所有美丽塔方案中的最大高度和。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n == maxHeights <= 10^3
 * 1 <= maxHeights[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long result = 0L;
        long[] prefix = new long[n], suffix = new long[n];
        Deque<Integer> s1 = new LinkedList<>(), s2 = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            long height = maxHeights.get(i);
            while (!s1.isEmpty() && height < maxHeights.get(s1.peek()))
                s1.pop();

            if (s1.isEmpty())
                prefix[i] = height * (i + 1);
            else
                prefix[i] = prefix[s1.peek()] + height * (i - s1.peek());

            s1.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            long height = maxHeights.get(i);
            while (!s2.isEmpty() && height < maxHeights.get(s2.peek()))
                s2.pop();

            if (s2.isEmpty())
                suffix[i] = height * (n - i);
            else
                suffix[i] = suffix[s2.peek()] + height * (s2.peek() - i);

            s2.push(i);
            result = Math.max(result, prefix[i] + suffix[i] - height);
        }

        return result;
    }
}
// @lc code=end
