import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode.cn/problems/permutations/description/
 *
 * algorithms
 * Medium (78.69%)
 * Likes:    2167
 * Dislikes: 0
 * Total Accepted:    693.2K
 * Total Submissions: 880.9K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1]
 * 输出：[[1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutions = new ArrayList<>();
        List<Integer> permution = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrace(nums, permutions, permution, visited);

        return permutions;
    }

    private void backtrace(int[] nums, List<List<Integer>> permutions, List<Integer> permution, boolean[] visited) {
        if (permution.size() == nums.length) {
            permutions.add(new ArrayList<>(permution));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            permution.add(nums[i]);
            backtrace(nums, permutions, permution, visited);
            permution.remove(permution.size() - 1);
            visited[i] = false;
        }
    }
}
// @lc code=end
