import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * https://leetcode.cn/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (60.45%)
 * Likes:    1121
 * Dislikes: 0
 * Total Accepted:    353.9K
 * Total Submissions: 586.1K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 
 * 注意：解集不能包含重复的组合。 
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 
 * 示例 2:
 * 
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        backktrace(candidates, results, new ArrayList<>(), target, 0, 0);
        return results;
    }

    private void backktrace(int[] candidates, List<List<Integer>> results,
            List<Integer> trace, int target, int currentSum, int index) {
        if (currentSum == target) {
            results.add(new ArrayList<>(trace));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if ((i > index && candidates[i] == candidates[i - 1])
                    || currentSum + candidates[i] > target)
                continue;

            trace.add(candidates[i]);
            currentSum += candidates[i];
            backktrace(candidates, results, trace, target, currentSum, i + 1);
            currentSum -= candidates[i];
            trace.remove(trace.size() - 1);
        }
    }
}
// @lc code=end
