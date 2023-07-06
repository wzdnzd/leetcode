/*
 * @lc app=leetcode.cn id=60 lang=java
 *
 * [60] 排列序列
 *
 * https://leetcode.cn/problems/permutation-sequence/description/
 *
 * algorithms
 * Hard (53.38%)
 * Likes:    777
 * Dislikes: 0
 * Total Accepted:    129.3K
 * Total Submissions: 241.9K
 * Testcase Example:  '3\n3'
 *
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * 
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * 
 * 给定 n 和 k，返回第 k 个排列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3, k = 3
 * 输出："213"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 3, k = 1
 * 输出："123"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        // 0-10 对应的阶乘
        final int[] records = new int[] { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

        StringBuilder permutation = new StringBuilder(n);
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(visited, false);

        for (int i = n - 1; i >= 0; i--) {
            int count = records[i];
            for (int j = 1; j <= n; j++) {
                if (visited[j])
                    continue;

                if (k > count) {
                    k -= count;
                    continue;
                }

                visited[j] = true;
                permutation.append(j);
                break;
            }
        }

        return permutation.toString();
    }
}
// @lc code=end
