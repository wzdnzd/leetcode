/*
 * @lc app=leetcode.cn id=447 lang=java
 *
 * [447] 回旋镖的数量
 *
 * https://leetcode.cn/problems/number-of-boomerangs/description/
 *
 * algorithms
 * Medium (66.81%)
 * Likes:    295
 * Dislikes: 0
 * Total Accepted:    68.5K
 * Total Submissions: 101K
 * Testcase Example:  '[[0,0],[1,0],[2,0]]'
 *
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组
 * ，其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 * 
 * 返回平面上所有回旋镖的数量。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：points = [[1,1]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * 所有点都 互不相同
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;

        for (int[] x : points) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int[] y : points) {
                int distance = (x[0] - y[0]) * (x[0] - y[0])
                        + (x[1] - y[1]) * (x[1] - y[1]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (int v : map.values())
                count += v * (v - 1);
        }

        return count;
    }
}
// @lc code=end
