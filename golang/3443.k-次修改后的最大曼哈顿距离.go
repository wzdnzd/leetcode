/*
 * @lc app=leetcode.cn id=3443 lang=golang
 *
 * [3443] K 次修改后的最大曼哈顿距离
 *
 * https://leetcode.cn/problems/maximum-manhattan-distance-after-k-changes/description/
 *
 * algorithms
 * Medium (48.06%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    3.7K
 * Total Submissions: 6.8K
 * Testcase Example:  '"NWSE"\n1'
 *
 * 给你一个由字符 'N'、'S'、'E' 和 'W' 组成的字符串 s，其中 s[i] 表示在无限网格中的移动操作：
 *
 *
 * 'N'：向北移动 1 个单位。
 * 'S'：向南移动 1 个单位。
 * 'E'：向东移动 1 个单位。
 * 'W'：向西移动 1 个单位。
 *
 *
 * 初始时，你位于原点 (0, 0)。你 最多 可以修改 k 个字符为任意四个方向之一。
 *
 * 请找出在 按顺序 执行所有移动操作过程中的 任意时刻 ，所能达到的离原点的 最大曼哈顿距离 。
 *
 * 曼哈顿距离 定义为两个坐标点 (xi, yi) 和 (xj, yj) 的横向距离绝对值与纵向距离绝对值之和，即 |xi - xj| + |yi -
 * yj|。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "NWSE", k = 1
 *
 * 输出：3
 *
 * 解释：
 *
 * 将 s[2] 从 'S' 改为 'N' ，字符串 s 变为 "NWNE" 。
 *
 *
 *
 *
 * 移动操作
 * 位置 (x, y)
 * 曼哈顿距离
 * 最大值
 *
 *
 *
 *
 * s[0] == 'N'
 * (0, 1)
 * 0 + 1 = 1
 * 1
 *
 *
 * s[1] == 'W'
 * (-1, 1)
 * 1 + 1 = 2
 * 2
 *
 *
 * s[2] == 'N'
 * (-1, 2)
 * 1 + 2 = 3
 * 3
 *
 *
 * s[3] == 'E'
 * (0, 2)
 * 0 + 2 = 2
 * 3
 *
 *
 *
 *
 * 执行移动操作过程中，距离原点的最大曼哈顿距离是 3 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "NSWWEW", k = 3
 *
 * 输出：6
 *
 * 解释：
 *
 * 将 s[1] 从 'S' 改为 'N' ，将 s[4] 从 'E' 改为 'W' 。字符串 s 变为 "NNWWWW" 。
 *
 * 执行移动操作过程中，距离原点的最大曼哈顿距离是 6 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^5
 * 0 <= k <= s.length
 * s 仅由 'N'、'S'、'E' 和 'W' 。
 *
 *
 */

// @lc code=start
package main

func maxDistance(s string, k int) int {
	distance, x, y := 0, 0, 0

	for i, c := range s {
		switch c {
		case 'N':
			y++
		case 'S':
			y--
		case 'E':
			x++
		default:
			x--
		}

		distance = max(distance, min(abs(x)+abs(y)+k*2, i+1))
	}

	return distance
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

// @lc code=end
