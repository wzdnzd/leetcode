/*
 * @lc app=leetcode.cn id=3102 lang=java
 *
 * [3102] 最小化曼哈顿距离
 *
 * https://leetcode.cn/problems/minimize-manhattan-distances/description/
 *
 * algorithms
 * Hard (38.38%)
 * Likes:    24
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 8.9K
 * Testcase Example:  '[[3,10],[5,15],[10,2],[4,4]]'
 *
 * 给你一个下标从 0 开始的数组 points ，它表示二维平面上一些点的整数坐标，其中 points[i] = [xi, yi] 。
 * 
 * 两点之间的距离定义为它们的曼哈顿距离。
 * 
 * 请你恰好移除一个点，返回移除后任意两点之间的 最大 距离可能的 最小 值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：points = [[3,10],[5,15],[10,2],[4,4]]
 * 输出：12
 * 解释：移除每个点后的最大距离如下所示：
 * - 移除第 0 个点后，最大距离在点 (5, 15) 和 (10, 2) 之间，为 |5 - 10| + |15 - 2| = 18 。
 * - 移除第 1 个点后，最大距离在点 (3, 10) 和 (10, 2) 之间，为 |3 - 10| + |10 - 2| = 15 。
 * - 移除第 2 个点后，最大距离在点 (5, 15) 和 (4, 4) 之间，为 |5 - 4| + |15 - 4| = 12 。
 * - 移除第 3 个点后，最大距离在点 (5, 15) 和 (10, 2) 之间的，为 |5 - 10| + |15 - 2| = 18 。
 * 在恰好移除一个点后，任意两点之间的最大距离可能的最小值是 12 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：points = [[1,1],[1,1],[1,1]]
 * 输出：0
 * 解释：移除任一点后，任意两点之间的最大距离都是 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= points.length <= 10^5
 * points[i].length == 2
 * 1 <= points[i][0], points[i][1] <= 10^8
 * 
 * 
 */

// @lc code=start

import java.util.TreeMap;

class Solution {
    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> xs = new TreeMap<>();
        TreeMap<Integer, Integer> ys = new TreeMap<>();

        for (int[] point : points) {
            xs.merge(point[0] + point[1], 1, Integer::sum);
            ys.merge(point[1] - point[0], 1, Integer::sum);
        }

        int result = Integer.MAX_VALUE;
        for (int[] point : points) {
            int x = point[0] + point[1];
            int y = point[1] - point[0];

            if (xs.get(x) == 1)
                xs.remove(x);
            else
                xs.merge(x, -1, Integer::sum);
            if (ys.get(y) == 1)
                ys.remove(y);
            else
                ys.merge(y, -1, Integer::sum);

            int dx = xs.lastKey() - xs.firstKey();
            int dy = ys.lastKey() - ys.firstKey();
            result = Math.min(result, Math.max(dx, dy));

            xs.merge(x, 1, Integer::sum);
            ys.merge(y, 1, Integer::sum);
        }

        return result;
    }
}
// @lc code=end
