/*
 * @lc app=leetcode.cn id=407 lang=golang
 *
 * [407] 接雨水 II
 *
 * https://leetcode.cn/problems/trapping-rain-water-ii/description/
 *
 * algorithms
 * Hard (57.33%)
 * Likes:    791
 * Dislikes: 0
 * Total Accepted:    46.3K
 * Total Submissions: 80.5K
 * Testcase Example:  '[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]'
 *
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 *
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 *
 *
 * 示例 2:
 *
 *
 *
 *
 * 输入: heightMap =
 * [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 *
 *
 *
 *
 * 提示:
 *
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 10^4
 *
 *
 *
 *
 */

// @lc code=start
package main

import "container/heap"

func trapRainWater(heightMap [][]int) int {
	result, m, n := 0, len(heightMap), len(heightMap[0])
	if m <= 2 || n <= 2 {
		return result
	}

	visited := make([][]bool, m)
	for i := range visited {
		visited[i] = make([]bool, n)
	}

	h := &hp{}
	for i, row := range heightMap {
		for j, v := range row {
			if i == 0 || i == m-1 || j == 0 || j == n-1 {
				heap.Push(h, cell{v, i, j})
				visited[i][j] = true
			}
		}
	}

	directions := []int{-1, 0, 1, 0, -1}
	for h.Len() > 0 {
		current := heap.Pop(h).(cell)
		for k := 0; k < 4; k++ {
			x, y := current.x+directions[k], current.y+directions[k+1]

			if 0 <= x && x < m && 0 <= y && y < n && !visited[x][y] {
				if heightMap[x][y] < current.h {
					result += current.h - heightMap[x][y]
				}

				visited[x][y] = true
				heap.Push(h, cell{max(heightMap[x][y], current.h), x, y})
			}
		}
	}

	return result
}

type cell struct{ h, x, y int }
type hp []cell

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].h < h[j].h }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(cell)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

func max(x, y int) int {
	if y > x {
		return y
	}

	return x
}

// @lc code=end
