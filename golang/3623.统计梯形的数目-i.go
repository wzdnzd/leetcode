/*
 * @lc app=leetcode.cn id=3623 lang=golang
 *
 * [3623] 统计梯形的数目 I
 *
 * https://leetcode.cn/problems/count-number-of-trapezoids-i/description/
 *
 * algorithms
 * Medium (35.47%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    6.5K
 * Total Submissions: 16.4K
 * Testcase Example:  '[[1,0],[2,0],[3,0],[2,2],[3,2]]'
 *
 * 给你一个二维整数数组 points，其中 points[i] = [xi, yi] 表示第 i 个点在笛卡尔平面上的坐标。
 *
 * 水平梯形 是一种凸四边形，具有 至少一对 水平边（即平行于 x 轴的边）。两条直线平行当且仅当它们的斜率相同。
 *
 * 返回可以从 points 中任意选择四个不同点组成的 水平梯形 数量。
 *
 * 由于答案可能非常大，请返回结果对 10^9 + 7 取余数后的值。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
 *
 * 输出： 3
 *
 * 解释：
 *
 * ⁠
 *
 * 有三种不同方式选择四个点组成一个水平梯形：
 *
 *
 * 使用点 [1,0]、[2,0]、[3,2] 和 [2,2]。
 * 使用点 [2,0]、[3,0]、[3,2] 和 [2,2]。
 * 使用点 [1,0]、[3,0]、[3,2] 和 [2,2]。
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： points = [[0,0],[1,0],[0,1],[2,1]]
 *
 * 输出： 1
 *
 * 解释：
 *
 *
 *
 * 只有一种方式可以组成一个水平梯形。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 4 <= points.length <= 10^5
 * –10^8 <= xi, yi <= 10^8
 * 所有点两两不同。
 *
 *
 */

// @lc code=start
package main

const MOD = 1000000007

func countTrapezoids(points [][]int) int {

	records := make(map[int]int, len(points))
	for _, point := range points {
		records[point[1]]++
	}

	count, sum := 0, 0
	for _, freq := range records {
		num := freq * (freq - 1) / 2
		count += sum * num
		sum += num
	}

	return count % MOD
}

// @lc code=end
