import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=659 lang=java
 *
 * [659] 分割数组为连续子序列
 *
 * https://leetcode.cn/problems/split-array-into-consecutive-subsequences/description/
 *
 * algorithms
 * Medium (54.46%)
 * Likes:    403
 * Dislikes: 0
 * Total Accepted:    34.1K
 * Total Submissions: 62.6K
 * Testcase Example:  '[1,2,3,3,4,5]'
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * 
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 : 
 * 1, 2, 3
 * 3, 4, 5
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 : 
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> needsMap = new HashMap<>();

        for (int num : nums) {
            int count = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, count);
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (freqMap.get(num) == 0)
                continue;
            if (needsMap.getOrDefault(num, 0) > 0) {
                freqMap.put(num, freqMap.get(num) - 1);
                needsMap.put(num, needsMap.get(num) - 1);
                int next = num + 1;
                needsMap.put(next, needsMap.getOrDefault(next, 0) + 1);
            } else if (freqMap.getOrDefault(num, 0) > 0
                    && freqMap.getOrDefault(num + 1, 0) > 0
                    && freqMap.getOrDefault(num + 2, 0) > 0) {
                freqMap.put(num, freqMap.get(num) - 1);
                freqMap.put(num + 1, freqMap.get(num + 1) - 1);
                freqMap.put(num + 2, freqMap.get(num + 2) - 1);
                needsMap.put(num + 3, needsMap.getOrDefault(num + 3, 0) + 1);
            } else
                return false;
        }

        return true;
    }
}
// @lc code=end
