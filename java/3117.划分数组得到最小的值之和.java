/*
 * @lc app=leetcode.cn id=3117 lang=java
 *
 * [3117] 划分数组得到最小的值之和
 *
 * https://leetcode.cn/problems/minimum-sum-of-values-by-dividing-array/description/
 *
 * algorithms
 * Hard (35.73%)
 * Likes:    22
 * Dislikes: 0
 * Total Accepted:    5.6K
 * Total Submissions: 12.2K
 * Testcase Example:  '[1,4,3,3,2]\n[0,3,3,2]'
 *
 * 给你两个数组 nums 和 andValues，长度分别为 n 和 m。
 * 
 * 数组的 值 等于该数组的 最后一个 元素。
 * 
 * 你需要将 nums 划分为 m 个 不相交的连续 子数组，对于第 i^th 个子数组 [li, ri]，子数组元素的按位 AND 运算结果等于
 * andValues[i]，换句话说，对所有的 1 <= i <= m，nums[li] & nums[li + 1] & ... & nums[ri]
 * == andValues[i] ，其中 & 表示按位 AND 运算符。
 * 
 * 返回将 nums 划分为 m 个子数组所能得到的可能的 最小 子数组 值 之和。如果无法完成这样的划分，则返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： nums = [1,4,3,3,2], andValues = [0,3,3,2]
 * 
 * 输出： 12
 * 
 * 解释：
 * 
 * 唯一可能的划分方法为：
 * 
 * 
 * [1,4] 因为 1 & 4 == 0
 * [3] 因为单元素子数组的按位 AND 结果就是该元素本身
 * [3] 因为单元素子数组的按位 AND 结果就是该元素本身
 * [2] 因为单元素子数组的按位 AND 结果就是该元素本身
 * 
 * 
 * 这些子数组的值之和为 4 + 3 + 3 + 2 = 12
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： nums = [2,3,5,7,7,7,5], andValues = [0,7,5]
 * 
 * 输出： 17
 * 
 * 解释：
 * 
 * 划分 nums 的三种方式为：
 * 
 * 
 * [[2,3,5],[7,7,7],[5]] 其中子数组的值之和为 5 + 7 + 5 = 17
 * [[2,3,5,7],[7,7],[5]] 其中子数组的值之和为 7 + 7 + 5 = 19
 * [[2,3,5,7,7],[7],[5]] 其中子数组的值之和为 7 + 7 + 5 = 19
 * 
 * 
 * 子数组值之和的最小可能值为 17
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入： nums = [1,2,3,4], andValues = [2]
 * 
 * 输出： -1
 * 
 * 解释：
 * 
 * 整个数组 nums 的按位 AND 结果为 0。由于无法将 nums 划分为单个子数组使得元素的按位 AND 结果为 2，因此返回 -1。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n == nums.length <= 10^4
 * 1 <= m == andValues.length <= min(n, 10)
 * 1 <= nums[i] < 10^5
 * 0 <= andValues[j] < 10^5
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumValueSum(int[] nums, int[] andValues) {
        Map<Long, Integer> memos = new HashMap<>();
        int result = dfs(0, 0, -1, nums, andValues, memos);
        return result < Integer.MAX_VALUE / 2 ? result : -1;
    }

    private int dfs(int i, int j, int and, int[] nums, int[] andValues, Map<Long, Integer> memos) {
        int n = nums.length;
        int m = andValues.length;
        if (n - i < m - j)
            return Integer.MAX_VALUE / 2;

        if (j == m)
            return i == n ? 0 : Integer.MAX_VALUE / 2;

        and &= nums[i];

        long mask = (long) i << 36 | (long) j << 32 | and;
        if (memos.containsKey(mask))
            return memos.get(mask);

        int res = dfs(i + 1, j, and, nums, andValues, memos);
        if (and == andValues[j])
            res = Math.min(res, dfs(i + 1, j + 1, -1, nums, andValues, memos) + nums[i]);

        memos.put(mask, res);
        return res;
    }
}
// @lc code=end
