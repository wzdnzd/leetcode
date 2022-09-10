import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=321 lang=java
 *
 * [321] 拼接最大数
 *
 * https://leetcode.cn/problems/create-maximum-number/description/
 *
 * algorithms
 * Hard (41.96%)
 * Likes:    496
 * Dislikes: 0
 * Total Accepted:    32.7K
 * Total Submissions: 77.8K
 * Testcase Example:  '[3,4,6,5]\n[9,1,2,5,8,3]\n5'
 *
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n)
 * 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * 
 * 示例 1:
 * 
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 
 * 示例 2:
 * 
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 
 * 示例 3:
 * 
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * 
 */

// @lc code=start
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0)
            return maxSubsequence(nums2, k);
        if (nums2 == null || nums2.length == 0)
            return maxSubsequence(nums1, k);

        int m = nums1.length, n = nums2.length;
        int[] ans = new int[k];
        for (int i = 0; i <= k; i++) {
            int j = k - i;
            if (i > m || j > n)
                continue;

            int[] arr1 = maxSubsequence(nums1, i);
            int[] arr2 = maxSubsequence(nums2, j);

            ans = compare(ans, merge(arr1, arr2));
        }

        return ans;
    }

    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = -1, remain = nums.length - k;
        for (int num : nums) {
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1)
                stack[++top] = num;
            else
                remain--;
        }
        return stack;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0)
            return nums2;
        if (nums2 == null || nums2.length == 0)
            return nums1;

        int i = 0, j = 0, index = 0, len = nums1.length + nums2.length;
        int[] array = new int[len];
        while (index < len) {
            if (greatThan(nums1, i, nums2, j))
                array[index++] = nums1[i++];
            else
                array[index++] = nums2[j++];
        }

        return array;
    }

    private boolean greatThan(int[] nums1, int i, int[] nums2, int j) {
        int m = nums1.length, n = nums2.length;
        while (i < m && j < n) {
            int difference = nums1[i] - nums2[j];
            if (difference != 0)
                return difference > 0;

            i++;
            j++;
        }

        return (m - i) - (n - j) > 0;
    }

    private int[] compare(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n)
            return compare(nums2, nums1);

        for (int i = 0; i < m; i++) {
            if (nums1[i] < nums2[i])
                return nums2;
            else if (nums1[i] > nums2[i])
                return nums1;
        }

        return nums2;
    }
}
// @lc code=end
