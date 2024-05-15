/*
 * @lc app=leetcode.cn id=2589 lang=java
 *
 * [2589] 完成所有任务的最少时间
 *
 * https://leetcode.cn/problems/minimum-time-to-complete-all-tasks/description/
 *
 * algorithms
 * Hard (43.19%)
 * Likes:    46
 * Dislikes: 0
 * Total Accepted:    6.4K
 * Total Submissions: 12.7K
 * Testcase Example:  '[[2,3,1],[4,5,1],[1,5,2]]'
 *
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi,
 * durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi
 * 个整数时间点（但不需要连续）。
 * 
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * 
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：tasks = [[2,3,1],[4,5,1],[1,5,2]]
 * 输出：2
 * 解释：
 * - 第一个任务在闭区间 [2, 2] 运行。
 * - 第二个任务在闭区间 [5, 5] 运行。
 * - 第三个任务在闭区间 [2, 2] 和 [5, 5] 运行。
 * 电脑总共运行 2 个整数时间点。
 * 
 * 
 * 示例 2：
 * 
 * 输入：tasks = [[1,3,2],[2,5,3],[5,6,2]]
 * 输出：4
 * 解释：
 * - 第一个任务在闭区间 [2, 3] 运行
 * - 第二个任务在闭区间 [2, 3] 和 [5, 5] 运行。
 * - 第三个任务在闭区间 [5, 6] 运行。
 * 电脑总共运行 4 个整数时间点。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= tasks.length <= 2000
 * tasks[i].length == 3
 * 1 <= starti, endi <= 2000
 * 1 <= durationi <= endi - starti + 1 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);

        List<int[]> stack = new ArrayList<>();
        stack.add(new int[] { -2, -2, 0 });

        for (int[] task : tasks) {
            int start = task[0], end = task[1], duration = task[2];

            int[] tuple = stack.get(binarySearch(stack, start) - 1);
            duration -= stack.get(stack.size() - 1)[2] - tuple[2];

            if (start <= tuple[1])
                duration -= tuple[1] - start + 1;

            if (duration <= 0)
                continue;

            while (end - stack.get(stack.size() - 1)[1] <= duration) {
                tuple = stack.remove(stack.size() - 1);
                duration += tuple[1] - tuple[0] + 1;
            }

            stack.add(new int[] { end - duration + 1, end, stack.get(stack.size() - 1)[2] + duration });
        }

        return stack.get(stack.size() - 1)[2];
    }

    private int binarySearch(List<int[]> stack, int target) {
        int left = 0, right = stack.size();

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (stack.get(mid)[0] < target)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}
// @lc code=end
