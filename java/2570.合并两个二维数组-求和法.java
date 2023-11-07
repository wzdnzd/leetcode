/*
 * @lc app=leetcode.cn id=2570 lang=java
 *
 * [2570] 合并两个二维数组 - 求和法
 *
 * https://leetcode.cn/problems/merge-two-2d-arrays-by-summing-values/description/
 *
 * algorithms
 * Easy (69.61%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    9.2K
 * Total Submissions: 13.2K
 * Testcase Example:  '[[1,2],[2,3],[4,5]]\n[[1,4],[3,2],[4,1]]'
 *
 * 给你两个 二维 整数数组 nums1 和 nums2.
 * 
 * 
 * nums1[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
 * nums2[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
 * 
 * 
 * 每个数组都包含 互不相同 的 id ，并按 id 以 递增 顺序排列。
 * 
 * 请你将两个数组合并为一个按 id 以递增顺序排列的数组，并符合下述条件：
 * 
 * 
 * 只有在两个数组中至少出现过一次的 id 才能包含在结果数组内。
 * 每个 id 在结果数组中 只能出现一次 ，并且其对应的值等于两个数组中该 id 所对应的值求和。如果某个数组中不存在该 id ，则认为其对应的值等于 0
 * 。
 * 
 * 
 * 返回结果数组。返回的数组需要按 id 以递增顺序排列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
 * 输出：[[1,6],[2,3],[3,2],[4,6]]
 * 解释：结果数组中包含以下元素：
 * - id = 1 ，对应的值等于 2 + 4 = 6 。
 * - id = 2 ，对应的值等于 3 。
 * - id = 3 ，对应的值等于 2 。
 * - id = 4 ，对应的值等于5 + 1 = 6 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
 * 输出：[[1,3],[2,4],[3,6],[4,3],[5,5]]
 * 解释：不存在共同 id ，在结果数组中只需要包含每个 id 和其对应的值。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 200
 * nums1[i].length == nums2[j].length == 2
 * 1 <= idi, vali <= 1000
 * 数组中的 id 互不相同
 * 数据均按 id 以严格递增顺序排列
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int[][] result = new int[nums1.length + nums2.length][2];
        int i = 0, j = 0, k = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i][0] < nums2[j][0])
                result[k++] = nums1[i++];
            else if (nums1[i][0] > nums2[j][0])
                result[k++] = nums2[j++];
            else {
                result[k][0] = nums1[i][0];
                result[k++][1] = nums1[i++][1] + nums2[j++][1];
            }
        }

        while (i < nums1.length)
            result[k++] = nums1[i++];

        while (j < nums2.length)
            result[k++] = nums2[j++];

        return Arrays.copyOf(result, k);
    }
}
// @lc code=end
