/*
 * @lc app=leetcode.cn id=1402 lang=java
 *
 * [1402] 做菜顺序
 *
 * https://leetcode.cn/problems/reducing-dishes/description/
 *
 * algorithms
 * Hard (76.16%)
 * Likes:    123
 * Dislikes: 0
 * Total Accepted:    17.6K
 * Total Submissions: 22.7K
 * Testcase Example:  '[-1,-8,0,5,-7]'
 *
 * 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
 * 
 * 一道菜的 「 like-time 系数 」定义为烹饪这道菜结束的时间（包含之前每道菜所花费的时间）乘以这道菜的满意程度，也就是
 * time[i]*satisfaction[i] 。
 * 
 * 返回厨师在准备了一定数量的菜肴后可以获得的最大 like-time 系数 总和。
 * 
 * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：satisfaction = [-1,-8,0,5,-9]
 * 输出：14
 * 解释：去掉第二道和最后一道菜，最大的 like-time 系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1
 * 单位时间完成。
 * 
 * 示例 2：
 * 
 * 
 * 输入：satisfaction = [4,3,2]
 * 输出：20
 * 解释：可以按照任意顺序做菜 (2*1 + 3*2 + 4*3 = 20)
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：satisfaction = [-1,-4,-5]
 * 输出：0
 * 解释：大家都不喜欢这些菜，所以不做任何菜就可以获得最大的 like-time 系数。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == satisfaction.length
 * 1 <= n <= 500
 * -1000 <= satisfaction[i] <= 1000
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int sum = 0, ans = 0;

        for (int i = satisfaction.length - 1; i >= 0; i--) {
            sum += satisfaction[i];
            if (sum < 0)
                break;

            ans += sum;
        }

        return ans;
    }
}
// @lc code=end
