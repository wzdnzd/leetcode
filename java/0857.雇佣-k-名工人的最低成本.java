/*
 * @lc app=leetcode.cn id=857 lang=java
 *
 * [857] 雇佣 K 名工人的最低成本
 *
 * https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/description/
 *
 * algorithms
 * Hard (63.55%)
 * Likes:    327
 * Dislikes: 0
 * Total Accepted:    22.6K
 * Total Submissions: 35.1K
 * Testcase Example:  '[10,20,5]\n[70,50,30]\n2'
 *
 * 有 n 名工人。 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为
 * wage[i] 。
 * 
 * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
 * 
 * 
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 
 * 
 * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10^-5 以内的答案将被接受。。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： quality = [10,20,5], wage = [70,50,30], k = 2
 * 输出： 105.00000
 * 解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。
 * 
 * 示例 2：
 * 
 * 
 * 输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * 输出： 30.66667
 * 解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == quality.length == wage.length
 * 1 <= k <= n <= 10^4
 * 1 <= quality[i], wage[i] <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] indexs = new Integer[n];

        Arrays.setAll(indexs, i -> i);
        Arrays.sort(indexs, (i, j) -> wage[i] * quality[j] - wage[j] * quality[i]);

        int totalQuality = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            pq.offer(quality[indexs[i]]);
            totalQuality += quality[indexs[i]];
        }

        double result = totalQuality * ((double) wage[indexs[k - 1]] / quality[indexs[k - 1]]);
        for (int i = k; i < n; i++) {
            int q = quality[indexs[i]];

            if (q < pq.peek()) {
                totalQuality -= pq.poll() - q;
                pq.offer(q);
                result = Math.min(result, totalQuality * ((double) wage[indexs[i]] / q));
            }
        }

        return result;
    }
}
// @lc code=end
