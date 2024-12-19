/*
 * @lc app=leetcode.cn id=3285 lang=java
 *
 * [3285] 找到稳定山的下标
 *
 * https://leetcode.cn/problems/find-indices-of-stable-mountains/description/
 *
 * algorithms
 * Easy (89.35%)
 * Likes:    10
 * Dislikes: 0
 * Total Accepted:    9.7K
 * Total Submissions: 10.4K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 有 n 座山排成一列，每座山都有一个高度。给你一个整数数组 height ，其中 height[i] 表示第 i 座山的高度，再给你一个整数
 * threshold 。
 * 
 * 对于下标不为 0 的一座山，如果它左侧相邻的山的高度 严格大于 threshold ，那么我们称它是 稳定 的。我们定义下标为 0 的山 不是
 * 稳定的。
 * 
 * 请你返回一个数组，包含所有 稳定 山的下标，你可以以 任意 顺序返回下标数组。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：height = [1,2,3,4,5], threshold = 2
 * 
 * 输出：[3,4]
 * 
 * 解释：
 * 
 * 
 * 下标为 3 的山是稳定的，因为 height[2] == 3 大于 threshold == 2 。
 * 下标为 4 的山是稳定的，因为 height[3] == 4 大于 threshold == 2.
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：height = [10,1,10,1,10], threshold = 3
 * 
 * 输出：[1,3]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：height = [10,1,10,1,10], threshold = 10
 * 
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= n == height.length <= 100
 * 1 <= height[i] <= 100
 * 1 <= threshold <= 100
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < height.length; i++) {
            if (height[i - 1] > threshold)
                result.add(i);
        }

        return result;
    }
}
// @lc code=end
