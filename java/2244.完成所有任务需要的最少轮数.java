/*
 * @lc app=leetcode.cn id=2244 lang=java
 *
 * [2244] 完成所有任务需要的最少轮数
 *
 * https://leetcode.cn/problems/minimum-rounds-to-complete-all-tasks/description/
 *
 * algorithms
 * Medium (57.61%)
 * Likes:    27
 * Dislikes: 0
 * Total Accepted:    13.8K
 * Total Submissions: 23.2K
 * Testcase Example:  '[2,2,3,3,2,4,4,4,4,4]'
 *
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别
 * 的任务。
 * 
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：tasks = [2,2,3,3,2,4,4,4,4,4]
 * 输出：4
 * 解释：要想完成所有任务，一个可能的计划是：
 * - 第一轮，完成难度级别为 2 的 3 个任务。 
 * - 第二轮，完成难度级别为 3 的 2 个任务。 
 * - 第三轮，完成难度级别为 4 的 3 个任务。 
 * - 第四轮，完成难度级别为 4 的 2 个任务。 
 * 可以证明，无法在少于 4 轮的情况下完成所有任务，所以答案为 4 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：tasks = [2,3,3]
 * 输出：-1
 * 解释：难度级别为 2 的任务只有 1 个，但每一轮执行中，只能选择完成 2 个或者 3 个相同难度级别的任务。因此，无法完成所有任务，答案为 -1
 * 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= tasks.length <= 10^5
 * 1 <= tasks[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks)
            map.put(task, map.getOrDefault(task, 0) + 1);

        int count = 0;
        for (int num : map.values()) {
            if (num == 1)
                return -1;

            if (num % 3 == 0)
                count += num / 3;
            else
                count += num / 3 + 1;
        }

        return count;
    }
}
// @lc code=end
