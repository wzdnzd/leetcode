/*
 * @lc app=leetcode.cn id=2998 lang=java
 *
 * [2998] 使 X 和 Y 相等的最少操作次数
 *
 * https://leetcode.cn/problems/minimum-number-of-operations-to-make-x-and-y-equal/description/
 *
 * algorithms
 * Medium (46.02%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    3K
 * Total Submissions: 6.4K
 * Testcase Example:  '26\n1'
 *
 * 给你两个正整数 x 和 y 。
 * 
 * 一次操作中，你可以执行以下四种操作之一：
 * 
 * 
 * 如果 x 是 11 的倍数，将 x 除以 11 。
 * 如果 x 是 5 的倍数，将 x 除以 5 。
 * 将 x 减 1 。
 * 将 x 加 1 。
 * 
 * 
 * 请你返回让 x 和 y 相等的 最少 操作次数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：x = 26, y = 1
 * 输出：3
 * 解释：我们可以通过以下操作将 26 变为 1 ：
 * 1. 将 x 减 1
 * 2. 将 x 除以 5
 * 3. 将 x 除以 5
 * 将 26 变为 1 最少需要 3 次操作。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：x = 54, y = 2
 * 输出：4
 * 解释：我们可以通过以下操作将 54 变为 2 ：
 * 1. 将 x 加 1
 * 2. 将 x 除以 11
 * 3. 将 x 除以 5
 * 4. 将 x 加 1
 * 将 54 变为 2 最少需要 4 次操作。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：x = 25, y = 30
 * 输出：5
 * 解释：我们可以通过以下操作将 25 变为 30 ：
 * 1. 将 x 加 1
 * 2. 将 x 加 1
 * 3. 将 x 加 1
 * 4. 将 x 加 1
 * 5. 将 x 加 1
 * 将 25 变为 30 最少需要 5 次操作。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= x, y <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int minimumOperationsToMakeEqual(int x, int y) {
        if (x <= y)
            return y - x;

        int minOperations = -1;
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(x);

        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[] { x, 0 });

        while (!queue.isEmpty() && minOperations < 0) {
            int[] arr = queue.poll();
            int num = arr[0], operations = arr[1];
            int nextOperations = operations + 1;
            List<Integer> list = getNext(num);

            for (int next : list) {
                if (next == y)
                    minOperations = nextOperations;
                else if (visited.add(next))
                    queue.offer(new int[] { next, nextOperations });
            }
        }

        return minOperations;
    }

    private List<Integer> getNext(int num) {
        List<Integer> list = new ArrayList<Integer>();
        if (num % 11 == 0)
            list.add(num / 11);

        if (num % 5 == 0)
            list.add(num / 5);

        list.add(num - 1);
        list.add(num + 1);

        return list;
    }
}
// @lc code=end
