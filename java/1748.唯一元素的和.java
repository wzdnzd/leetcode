import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1748 lang=java
 *
 * [1748] 唯一元素的和
 *
 * https://leetcode.cn/problems/sum-of-unique-elements/description/
 *
 * algorithms
 * Easy (77.70%)
 * Likes:    75
 * Dislikes: 0
 * Total Accepted:    44.2K
 * Total Submissions: 57K
 * Testcase Example:  '[1,2,3,2]'
 *
 * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
 * 
 * 请你返回 nums 中唯一元素的 和 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,2,3,2]
 * 输出：4
 * 解释：唯一元素为 [1,3] ，和为 4 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：没有唯一元素，和为 0 。
 * 
 * 
 * 示例 3 ：
 * 
 * 输入：nums = [1,2,3,4,5]
 * 输出：15
 * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1)
                ans += entry.getKey();
        }

        return ans;
    }
}
// @lc code=end
