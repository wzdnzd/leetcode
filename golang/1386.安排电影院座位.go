/*
 * @lc app=leetcode.cn id=1386 lang=golang
 *
 * [1386] 安排电影院座位
 *
 * https://leetcode.cn/problems/cinema-seat-allocation/description/
 *
 * algorithms
 * Medium (37.38%)
 * Likes:    84
 * Dislikes: 0
 * Total Accepted:    15.1K
 * Total Submissions: 37.7K
 * Testcase Example:  '3\n[[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]'
 *
 *
 *
 * 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。
 *
 * 给定一个二维数组 reservedSeats ，其中 reservedSeats[i] = [rowi, seati] 表示第 rowi 行的座位
 * seati 已经被预定。
 *
 * 四人小组必须被安排在同一排的四个座位上。该小组可以坐在以下座位块之一：
 *
 *
 * 座位 2, 3, 4, 5
 * 座位 4, 5, 6, 7
 * 座位 6, 7, 8, 9
 *
 *
 * 只有当该块中的所有座位都 没有 被预订时，才能使用该块。每个座位 最多 只能分配给一个小组。
 *
 * 返回一个整数，表示可以分配的 最大 四人小组数量。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * 输出：4
 * 解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * 输出：2
 *
 *
 * 示例 3：
 *
 *
 * 输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * 输出：4
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10 * n, 10^4)
 * reservedSeats[i] == [rowi, seati]
 * 1 <= rowi <= n
 * 1 <= seati <= 10
 * 所有 reservedSeats[i] 都是互不相同的。
 *
 *
 */

// @lc code=start
package main

func maxNumberOfFamilies(n int, reservedSeats [][]int) int {
	seats := map[int]int{}
	for _, row := range reservedSeats {
		seat := row[1]

		if 2 <= seat && seat <= 9 {
			seats[row[0]] |= 1 << (seat - 2)
		}
	}

	emptyRows := n - len(seats)
	count := emptyRows * 2
	for _, x := range seats {
		if x&0b1111 == 0 || x&0b111100 == 0 || x&0b11110000 == 0 {
			count++
		}
	}

	return count
}

// @lc code=end
