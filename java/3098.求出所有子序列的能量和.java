/*
 * @lc app=leetcode.cn id=3098 lang=java
 *
 * [3098] 求出所有子序列的能量和
 *
 * https://leetcode.cn/problems/find-the-sum-of-subsequence-powers/description/
 *
 * algorithms
 * Hard (39.82%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    2.6K
 * Total Submissions: 5.8K
 * Testcase Example:  '[1,2,3,4]\n3'
 *
 * 给你一个长度为 n 的整数数组 nums 和一个 正 整数 k 。
 * 
 * 一个 子序列 的 能量 定义为子序列中 任意 两个元素的差值绝对值的 最小值 。
 * 
 * 请你返回 nums 中长度 等于 k 的 所有 子序列的 能量和 。
 * 
 * 由于答案可能会很大，将答案对 10^9 + 7 取余 后返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3,4], k = 3
 * 
 * 输出：4
 * 
 * 解释：
 * 
 * nums 中总共有 4 个长度为 3 的子序列：[1,2,3] ，[1,3,4] ，[1,2,4] 和 [2,3,4] 。能量和为 |2 - 3| +
 * |3 - 4| + |2 - 1| + |3 - 4| = 4 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [2,2], k = 2
 * 
 * 输出：0
 * 
 * 解释：
 * 
 * nums 中唯一一个长度为 2 的子序列是 [2,2] 。能量和为 |2 - 2| = 0 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [4,3,-1], k = 2
 * 
 * 输出：10
 * 
 * 解释：
 * 
 * nums 总共有 3 个长度为 2 的子序列：[4,3] ，[4,-1] 和 [3,-1] 。能量和为 |4 - 3| + |4 - (-1)| +
 * |3 - (-1)| = 10 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= n == nums.length <= 50
 * -10^8 <= nums[i] <= 10^8 
 * 2 <= k <= n
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Long, Integer> memos = new HashMap<>();
    private final int MOD = (int) 1e9 + 7;

    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);

        return dfs(nums, 0, nums.length, k, Integer.MAX_VALUE);
    }

    private int dfs(int[] nums, int i, int j, int k, int diff) {
        if (i >= nums.length)
            return k == 0 ? diff : 0;

        if (nums.length - i < k)
            return 0;

        long key = (1L * diff) << 18 | (i << 12) | (j << 6) | k;
        if (memos.containsKey(key))
            return memos.get(key);

        int result = dfs(nums, i + 1, j, k, diff);
        if (j == nums.length)
            result += dfs(nums, i + 1, i, k - 1, diff);
        else
            result += dfs(nums, i + 1, i, k - 1, Math.min(diff, nums[i] - nums[j]));

        result %= MOD;
        memos.put(key, result);

        return result;
    }
}
// @lc code=end
