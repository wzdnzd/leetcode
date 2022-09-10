import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=870 lang=java
 *
 * [870] 优势洗牌
 *
 * https://leetcode.cn/problems/advantage-shuffle/description/
 *
 * algorithms
 * Medium (47.44%)
 * Likes:    200
 * Dislikes: 0
 * Total Accepted:    28.6K
 * Total Submissions: 60.3K
 * Testcase Example:  '[2,7,11,15]\n[1,10,4,11]'
 *
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i
 * 的数目来描述。
 * 
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 田忌赛马：打得过就打，打不过就保存实力，用最差的去跟对方最好的打
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        // 从大到小排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((pairs1, pairs2) -> {
            return pairs2[1] - pairs1[1];
        });

        for (int i = 0; i < nums2.length; i++) {
            pq.offer(new int[] { i, nums2[i] });
        }

        int left = 0, right = nums1.length - 1;
        int[] ans = new int[nums1.length];
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int idx = pair[0], val = pair[1];
            if (nums1[right] > val)
                ans[idx] = nums1[right--];
            else
                ans[idx] = nums1[left++];
        }

        return ans;
    }
}
// @lc code=end
