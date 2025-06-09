/*
 * @lc app=leetcode.cn id=440 lang=java
 *
 * [440] 字典序的第K小数字
 *
 * https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/description/
 *
 * algorithms
 * Hard (42.39%)
 * Likes:    607
 * Dislikes: 0
 * Total Accepted:    56.6K
 * Total Submissions: 133.5K
 * Testcase Example:  '13\n2'
 *
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: n = 1, k = 1
 * 输出: 1
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= k <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findKthNumber(int n, int k) {
        int num = 1;
        while (k > 1) {
            int count = getCount(num, n);
            if (count < k) {
                k -= count;
                num++;
            } else {
                k--;
                num *= 10;
            }
        }

        return num;
    }

    private int getCount(int prefix, long n) {
        long count = 0;
        long start = prefix, end = prefix;

        while (start <= n) {
            long currCount = end - start + 1;
            
            count += currCount;
            start *= 10;
            end = Math.min(end * 10 + 9, n);
        }

        return (int) count;
    }
}
// @lc code=end

