/*
 * @lc app=leetcode.cn id=1840 lang=java
 *
 * [1840] 最高建筑高度
 *
 * https://leetcode.cn/problems/maximum-building-height/description/
 *
 * algorithms
 * Hard (41.72%)
 * Likes:    57
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 10K
 * Testcase Example:  '5\n[[2,1],[4,1]]'
 *
 * 在一座城市里，你需要建 n 栋新的建筑。这些新的建筑会从 1 到 n 编号排成一列。
 * 
 * 这座城市对这些新建筑有一些规定：
 * 
 * 
 * 每栋建筑的高度必须是一个非负整数。
 * 第一栋建筑的高度 必须 是 0 。
 * 任意两栋相邻建筑的高度差 不能超过  1 。
 * 
 * 
 * 除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 restrictions 的形式给出，其中 restrictions[i] =
 * [idi, maxHeighti] ，表示建筑 idi 的高度 不能超过 maxHeighti 。
 * 
 * 题目保证每栋建筑在 restrictions 中 至多出现一次 ，同时建筑 1 不会 出现在 restrictions 中。
 * 
 * 请你返回 最高 建筑能达到的 最高高度 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 5, restrictions = [[2,1],[4,1]]
 * 输出：2
 * 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
 * 我们可以使建筑高度分别为 [0,1,2,1,2] ，最高建筑的高度为 2 。
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 6, restrictions = []
 * 输出：5
 * 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
 * 我们可以使建筑高度分别为 [0,1,2,3,4,5] ，最高建筑的高度为 5 。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
 * 输出：5
 * 解释：上图中的绿色区域为每栋建筑被允许的最高高度。
 * 我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 
 * 0 
 * 2 i 
 * idi 是 唯一的 。
 * 0 i 
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        int maxHeight = 0;
        int m = restrictions.length;
        int prevId = 1, prevHeight = 0;

        for (int i = 0; i < m; i++) {
            int currId = restrictions[i][0];
            int difference = currId - prevId;
            restrictions[i][1] = Math.min(restrictions[i][1], prevHeight + difference);
            prevId = currId;
            prevHeight = restrictions[i][1];
        }

        for (int i = m - 2; i >= 0; i--) {
            int difference = restrictions[i + 1][0] - restrictions[i][0];
            restrictions[i][1] = Math.min(restrictions[i][1], restrictions[i + 1][1] + difference);
        }

        prevId = 1;
        prevHeight = 0;
        for (int i = 0; i < m; i++) {
            int currId = restrictions[i][0], currHeight = restrictions[i][1];
            int currMaxHeight = (currId - prevId + currHeight + prevHeight) / 2;
            maxHeight = Math.max(maxHeight, currMaxHeight);
            prevId = currId;
            prevHeight = currHeight;
        }

        int lastHeight = prevHeight + (n - prevId);
        maxHeight = Math.max(maxHeight, lastHeight);

        return maxHeight;
    }
}
// @lc code=end
