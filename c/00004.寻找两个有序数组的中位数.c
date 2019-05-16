/*
 * @lc app=leetcode.cn id=4 lang=c
 *
 * [4] 寻找两个有序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (33.24%)
 * Likes:    1050
 * Dislikes: 0
 * Total Accepted:    54.6K
 * Total Submissions: 156.5K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * 则中位数是 2.0
 * 
 * 
 * 示例 2:
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 
 */

double findKth(int *nums1, int x1, int y1, int *nums2, int x2, int y2, int k)
{
    int m = y1 - x1 + 1;
    int n = y2 - x2 + 1;

    if (m < n)
        return findKth(nums2, x2, y2, nums1, x1, y1, k);

    if (n == 0)
        return nums1[x1 + k - 1];

    if (k == 1)
        return nums1[x1] < nums2[x2] ? nums1[x1] : nums2[x2];

    int k2 = k / 2 < n ? k / 2 : n;
    int k1 = k - k2;

    if (nums1[x1 + k1 - 1] == nums2[x2 + k2 - 1])
        return nums1[x1 + k1 - 1];
    else if (nums1[x1 + k1 - 1] > nums2[x2 + k2 - 1])
        return findKth(nums1, x1, y1, nums2, x2 + k2, y2, k - k2);
    else
        return findKth(nums1, x1 + k1, y1, nums2, x2, y2, k - k1);
}

double findMedianSortedArrays(int *nums1, int nums1Size, int *nums2, int nums2Size)
{
    int total = nums1Size + nums2Size;
    if (total % 2 == 0)
    {
        double c1 = findKth(nums1, 0, nums1Size - 1, nums2, 0, nums2Size - 1, total / 2);
        double c2 = findKth(nums1, 0, nums1Size - 1, nums2, 0, nums2Size - 1, total / 2 + 1);

        return (c1 + c2) / 2;
    }
    return findKth(nums1, 0, nums1Size - 1, nums2, 0, nums2Size - 1, total / 2 + 1);
}
