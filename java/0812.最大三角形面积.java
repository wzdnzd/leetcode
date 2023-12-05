/*
 * @lc app=leetcode.cn id=812 lang=java
 *
 * [812] 最大三角形面积
 *
 * https://leetcode.cn/problems/largest-triangle-area/description/
 *
 * algorithms
 * Easy (68.10%)
 * Likes:    194
 * Dislikes: 0
 * Total Accepted:    38.1K
 * Total Submissions: 55.9K
 * Testcase Example:  '[[0,0],[0,1],[1,0],[0,2],[2,0]]'
 *
 * 给你一个由 X-Y 平面上的点组成的数组 points ，其中 points[i] = [xi, yi]
 * 。从其中取任意三个不同的点组成三角形，返回能组成的最大三角形的面积。与真实值误差在 10^-5 内的答案将会视为正确答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出：2.00000
 * 解释：输入中的 5 个点如上图所示，红色的三角形面积最大。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：points = [[1,0],[0,0],[0,1]]
 * 输出：0.50000
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= points.length <= 50
 * -50 <= xi, yi <= 50
 * 给出的所有点 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;

        for (int[] i : points) {
            for (int[] j : points) {
                for (int[] k : points) {
                    maxArea = Math.max(maxArea, 0.5 * Math
                            .abs(i[0] * j[1] + j[0] * k[1] + k[0] * i[1] - i[0] * k[1] - j[0] * i[1] - k[0] * j[1]));
                }
            }
        }

        return maxArea;
    }
}
// @lc code=end
