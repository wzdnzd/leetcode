/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (41.14%)
 * Likes:    5264
 * Dislikes: 0
 * Total Accepted:    665.4K
 * Total Submissions: 1.6M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            return getKTh(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2 + 1);
        }

        return (getKTh(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2)
                + getKTh(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2 + 1)) * 0.5;
    }

    private int getKTh(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int m = end1 - start1 + 1;
        int n = end2 - start2 + 1;

        if (m > n) {
            return getKTh(nums2, start2, end2, nums1, start1, end1, k);
        }

        if (m == 0) {
            return nums2[start2 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(m, k / 2) - 1;
        int j = start2 + Math.min(n, k / 2) - 1;

        if (nums1[i] < nums2[j]) {
            return getKTh(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }

        return getKTh(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
    }
}
// @lc code=end
