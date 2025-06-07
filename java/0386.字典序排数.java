/*
 * @lc app=leetcode.cn id=386 lang=java
 *
 * [386] 字典序排数
 *
 * https://leetcode.cn/problems/lexicographical-numbers/description/
 *
 * algorithms
 * Medium (74.63%)
 * Likes:    516
 * Dislikes: 0
 * Total Accepted:    85.4K
 * Total Submissions: 114.3K
 * Testcase Example:  '13'
 *
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 2
 * 输出：[1,2]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 5 * 10^4
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1, num = 1; i <= n; i++) {

            list.add(num);
            if (num * 10 <= n)
                num *= 10;
            else {
                while (num % 10 == 9 || num + 1 > n)
                    num /= 10;

                num++;
            }
        }
        
        return list;
    }
}
// @lc code=end

