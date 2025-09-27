/*
 * @lc app=leetcode.cn id=812 lang=golang
 *
 * [812] 最大三角形面积
 *
 * https://leetcode.cn/problems/largest-triangle-area/description/
 *
 * algorithms
 * Easy (68.20%)
 * Likes:    210
 * Dislikes: 0
 * Total Accepted:    43.7K
 * Total Submissions: 63.7K
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
package main

import "math"

func largestTriangleArea(points [][]int) float64 {
	area := 0.0
	for i, x := range points {
		for j, y := range points[:i] {
			for _, z := range points[:j] {
				area = math.Max(area, triangleArea(x[0], x[1], y[0], y[1], z[0], z[1]))
			}
		}
	}

	return area
}

func triangleArea(x1, y1, x2, y2, x3, y3 int) float64 {
	return math.Abs(float64(x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2)) / 2
}

// @lc code=end
