/*
 * @lc app=leetcode.cn id=2929 lang=java
 *
 * [2929] 给小朋友们分糖果 II
 *
 * https://leetcode.cn/problems/distribute-candies-among-children-ii/description/
 *
 * algorithms
 * Medium (41.77%)
 * Likes:    20
 * Dislikes: 0
 * Total Accepted:    5.1K
 * Total Submissions: 12.2K
 * Testcase Example:  '5\n2'
 *
 * 给你两个正整数 n 和 limit 。
 * 
 * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 5, limit = 2
 * 输出：3
 * 解释：总共有 3 种方法分配 5 颗糖果，且每位小朋友的糖果数不超过 2 ：(1, 2, 2) ，(2, 1, 2) 和 (2, 2, 1) 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 3, limit = 3
 * 输出：10
 * 解释：总共有 10 种方法分配 3 颗糖果，且每位小朋友的糖果数不超过 3 ：(0, 0, 3) ，(0, 1, 2) ，(0, 2, 1) ，(0,
 * 3, 0) ，(1, 0, 2) ，(1, 1, 1) ，(1, 2, 0) ，(2, 0, 1) ，(2, 1, 0) 和 (3, 0, 0)
 * 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^6
 * 1 <= limit <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public long distributeCandies(int n, int limit) {
        if (n > limit * 3)
            return 0;
        
        return combinations(n + 2, 2) - combinations(n - limit + 1, 2) * 3 + combinations(n - limit * 2, 2) * 3 - combinations(n - limit * 3 - 1, 2);
    }

    private long combinations(int m, int k) {
        if (m < k)
            return 0;
        
        long count = 1;
        for (int i = 1; i <= k; i++)
            count *= m - i + 1;
        
        for (int i = 1; i <= k; i++)
            count /= i;
        
        return count;
    }
}
// @lc code=end

