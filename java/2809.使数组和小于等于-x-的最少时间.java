/*
 * @lc app=leetcode.cn id=2809 lang=java
 *
 * [2809] 使数组和小于等于 x 的最少时间
 *
 * https://leetcode.cn/problems/minimum-time-to-make-array-sum-at-most-x/description/
 *
 * algorithms
 * Hard (39.37%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    2.3K
 * Total Submissions: 4.7K
 * Testcase Example:  '[1,2,3]\n[1,2,3]\n4'
 *
 * 给你两个长度相等下标从 0 开始的整数数组 nums1 和 nums2 。每一秒，对于所有下标 0 <= i < nums1.length
 * ，nums1[i] 的值都增加 nums2[i] 。操作 完成后 ，你可以进行如下操作：
 * 
 * 
 * 选择任一满足 0 <= i < nums1.length 的下标 i ，并使 nums1[i] = 0 。
 * 
 * 
 * 同时给你一个整数 x 。
 * 
 * 请你返回使 nums1 中所有元素之和 小于等于 x 所需要的 最少 时间，如果无法实现，那么返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums1 = [1,2,3], nums2 = [1,2,3], x = 4
 * 输出：3
 * 解释：
 * 第 1 秒，我们对 i = 0 进行操作，得到 nums1 = [0,2+2,3+3] = [0,4,6] 。
 * 第 2 秒，我们对 i = 1 进行操作，得到 nums1 = [0+1,0,6+3] = [1,0,9] 。
 * 第 3 秒，我们对 i = 2 进行操作，得到 nums1 = [1+1,0+2,0] = [2,2,0] 。
 * 现在 nums1 的和为 4 。不存在更少次数的操作，所以我们返回 3 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums1 = [1,2,3], nums2 = [3,3,3], x = 4
 * 输出：-1
 * 解释：不管如何操作，nums1 的和总是会超过 x 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums1.length <= 10^3
 * 1 <= nums1[i] <= 10^3
 * 0 <= nums2[i] <= 10^3
 * nums1.length == nums2.length
 * 0 <= x <= 10^6
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), sum1 = 0, sum2 = 0;
        int[] dp = new int[n + 1];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i), b = nums2.get(i);
            list.add(Arrays.asList(b, a));

            sum1 += a;
            sum2 += b;
        }

        list.sort((a, b) -> a.get(0) - b.get(0));

        for (int i = 1; i <= n; i++) {
            int a = list.get(i - 1).get(1);
            int b = list.get(i - 1).get(0);

            for (int j = i; j > 0; j--)
                dp[j] = Math.max(dp[j], dp[j - 1] + j * b + a);
        }

        for (int i = 0; i <= n; i++)
            if (sum2 * i + sum1 - dp[i] <= x)
                return i;

        return -1;
    }
}
// @lc code=end
