/*
 * @lc app=leetcode.cn id=1232 lang=java
 *
 * [1232] 缀点成线
 *
 * https://leetcode.cn/problems/check-if-it-is-a-straight-line/description/
 *
 * algorithms
 * Easy (45.21%)
 * Likes:    141
 * Dislikes: 0
 * Total Accepted:    49.9K
 * Total Submissions: 110.3K
 * Testcase Example:  '[[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]'
 *
 * 给定一个数组 coordinates ，其中 coordinates[i] = [x, y] ， [x, y] 表示横坐标为 x、纵坐标为 y
 * 的点。请你来判断，这些点是否在该坐标系中属于同一条直线上。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int x1 = coordinates[1][0], y1 = coordinates[1][1];

        int dx = x1 - x0, dy = y1 - y0;
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (dx * (y - y1) != dy * (x - x1))
                return false;
        }

        return true;
    }
}
// @lc code=end
