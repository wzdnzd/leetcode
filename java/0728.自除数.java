/*
 * @lc app=leetcode.cn id=728 lang=java
 *
 * [728] 自除数
 *
 * https://leetcode.cn/problems/self-dividing-numbers/description/
 *
 * algorithms
 * Easy (76.98%)
 * Likes:    261
 * Dislikes: 0
 * Total Accepted:    81.6K
 * Total Submissions: 106.7K
 * Testcase Example:  '1\n22'
 *
 * 自除数 是指可以被它包含的每一位数整除的数。
 * 
 * 
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 
 * 
 * 自除数 不允许包含 0 。
 * 
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= left <= right <= 10^4
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            boolean flag = true;
            int num = i;

            while (num > 0) {
                int digit = num % 10;
                if (digit == 0 || i % digit != 0) {
                    flag = false;
                    break;
                }

                num /= 10;
            }

            if (flag)
                ans.add(i);
        }

        return ans;
    }
}
// @lc code=end
