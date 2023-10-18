/*
 * @lc app=leetcode.cn id=2530 lang=java
 *
 * [2530] 执行 K 次操作后的最大分数
 *
 * https://leetcode.cn/problems/maximal-score-after-applying-k-operations/description/
 *
 * algorithms
 * Medium (45.17%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    8.9K
 * Total Submissions: 18K
 * Testcase Example:  '[10,10,10,10,10]\n5'
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你的 起始分数 为 0 。
 * 
 * 在一步 操作 中：
 * 
 * 
 * 选出一个满足 0 <= i < nums.length 的下标 i ，
 * 将你的 分数 增加 nums[i] ，并且
 * 将 nums[i] 替换为 ceil(nums[i] / 3) 。
 * 
 * 
 * 返回在 恰好 执行 k 次操作后，你可能获得的最大分数。
 * 
 * 向上取整函数 ceil(val) 的结果是大于或等于 val 的最小整数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [10,10,10,10,10], k = 5
 * 输出：50
 * 解释：对数组中每个元素执行一次操作。最后分数是 10 + 10 + 10 + 10 + 10 = 50 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,10,3,3,3], k = 3
 * 输出：17
 * 解释：可以执行下述操作：
 * 第 1 步操作：选中 i = 1 ，nums 变为 [1,4,3,3,3] 。分数增加 10 。
 * 第 2 步操作：选中 i = 1 ，nums 变为 [1,2,3,3,3] 。分数增加 4 。
 * 第 3 步操作：选中 i = 2 ，nums 变为 [1,1,1,3,3] 。分数增加 3 。
 * 最后分数是 10 + 4 + 3 = 17 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length, k <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public long maxKelements(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums)
            queue.offer(num);

        long sum = 0;
        for (int i = 0; i < k; i++) {
            int num = queue.poll();
            sum += num;
            queue.offer((int) Math.ceil(num / 3.0));
        }

        return sum;
    }
}
// @lc code=end
