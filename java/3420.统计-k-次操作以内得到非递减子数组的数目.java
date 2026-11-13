/*
 * @lc app=leetcode.cn id=3420 lang=java
 *
 * [3420] 统计 K 次操作以内得到非递减子数组的数目
 *
 * https://leetcode.cn/problems/count-non-decreasing-subarrays-after-k-operations/description/
 *
 * algorithms
 * Hard (36.03%)
 * Likes:    18
 * Dislikes: 0
 * Total Accepted:    2.8K
 * Total Submissions: 5.6K
 * Testcase Example:  '[6,3,1,2,4,4]\n7'
 *
 * 给你一个长度为 n 的数组 nums 和一个整数 k 。
 * 
 * 对于 nums 中的每一个子数组，你可以对它进行 至多 k 次操作。每次操作中，你可以将子数组中的任意一个元素增加 1 。
 * 
 * 注意 ，每个子数组都是独立的，也就是说你对一个子数组的修改不会保留到另一个子数组中。
 * 
 * 
 * 请你返回最多 k 次操作以内，有多少个子数组可以变成 非递减 的。
 * 
 * 如果一个数组中的每一个元素都大于等于前一个元素（如果前一个元素存在），那么我们称这个数组是 非递减 的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [6,3,1,2,4,4], k = 7
 * 
 * 输出：17
 * 
 * 解释：
 * 
 * nums 的所有 21 个子数组中，只有子数组 [6, 3, 1] ，[6, 3, 1, 2] ，[6, 3, 1, 2, 4] 和 [6, 3, 1,
 * 2, 4, 4] 无法在 k = 7 次操作以内变为非递减的。所以非递减子数组的数目为 21 - 4 = 17 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [6,3,1,3,6], k = 4
 * 
 * 输出：12
 * 
 * 解释：
 * 
 * 子数组 [3, 1, 3, 6] 和 nums 中所有小于等于三个元素的子数组中，除了 [6, 3, 1] 以外，都可以在 k
 * 次操作以内变为非递减子数组。总共有 5 个包含单个元素的子数组，4 个包含两个元素的子数组，除 [6, 3, 1] 以外有 2
 * 个包含三个元素的子数组，所以总共有 1 + 5 + 4 + 2 = 12 个子数组可以变为非递减的。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] rightClosest = new int[n];
        Arrays.fill(rightClosest, n);

        List<List<Integer>> rightSmallerIndices = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            rightSmallerIndices.add(new ArrayList<>());

        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i])
                rightClosest[stack.pop()] = i;

            if (!stack.isEmpty())
                rightSmallerIndices.get(stack.peek()).add(i);

            stack.push(i);
        }

        long count = 0;
        int start = 0, end = 0, operations = 0;
        Deque<Integer> queue = new ArrayDeque<Integer>();
        while (end < n) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[end])
                queue.pollLast();

            queue.offerLast(end);
            operations += nums[queue.peekFirst()] - nums[end];

            while (operations > k) {
                for (int index : rightSmallerIndices.get(start)) {
                    if (index <= end)
                        operations -= (nums[start] - nums[index])
                                * (Math.min(rightClosest[index] - 1, end) - index + 1);
                    else
                        break;
                }

                if (!queue.isEmpty() && queue.peekFirst() == start)
                    queue.pollFirst();

                start++;
            }

            count += end - start + 1;
            end++;
        }

        return count;
    }
}
// @lc code=end
