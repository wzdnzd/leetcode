import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode.cn/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (65.16%)
 * Likes:    1201
 * Dislikes: 0
 * Total Accepted:    382.3K
 * Total Submissions: 586.2K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * ⁠[1,2,1],
 * ⁠[2,1,1]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backktrace(nums, results, new ArrayList<>(), visited);
        return results;
    }

    private void backktrace(int[] nums, List<List<Integer>> results,
            List<Integer> trace, boolean[] visited) {
        if (trace.size() == nums.length) {
            results.add(new ArrayList<>(trace));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || ((i > 0) && (nums[i] == nums[i - 1]) && !visited[i - 1]))
                continue;

            trace.add(nums[i]);
            visited[i] = true;
            backktrace(nums, results, trace, visited);
            visited[i] = false;
            trace.remove(trace.size() - 1);
        }
    }
}
// @lc code=end
