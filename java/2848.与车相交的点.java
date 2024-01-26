/*
 * @lc app=leetcode.cn id=2848 lang=java
 *
 * [2848] 与车相交的点
 *
 * https://leetcode.cn/problems/points-that-intersect-with-cars/description/
 *
 * algorithms
 * Easy (73.88%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    8K
 * Total Submissions: 10.9K
 * Testcase Example:  '[[3,6],[1,5],[4,7]]'
 *
 * 给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。对于任意下标 i，nums[i] = [starti, endi] ，其中
 * starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。
 * 
 * 返回数轴上被车 任意部分 覆盖的整数点的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [[3,6],[1,5],[4,7]]
 * 输出：7
 * 解释：从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [[1,3],[5,8]]
 * 输出：7
 * 解释：1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * nums[i].length == 2
 * 1 <= starti <= endi <= 100
 * 
 * 
 */

// @lc code=start

import java.util.List;

class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int lower = Integer.MAX_VALUE, upper = Integer.MIN_VALUE;

        for (List<Integer> num : nums) {
            lower = Math.min(lower, num.get(0));
            upper = Math.max(upper, num.get(1));
        }

        int[] diffs = new int[upper - lower + 1];
        for (List<Integer> num : nums) {
            int start = num.get(0), end = num.get(1);
            diffs[start - lower]++;
            if (end < upper)
                diffs[end - lower + 1]--;
        }

        int covered = 0, count = 0;
        for (int i = lower; i <= upper; i++) {
            count += diffs[i - lower];
            if (count > 0)
                covered++;
        }

        return covered;
    }
}
// @lc code=end
