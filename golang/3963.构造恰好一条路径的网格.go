/*
 * @lc app=leetcode.cn id=3963 lang=golang
 *
 * [3963] 构造恰好一条路径的网格
 *
 * https://leetcode.cn/problems/create-grid-with-exactly-one-path/description/
 *
 * algorithms
 * Easy (77.08%)
 * Likes:    3
 * Dislikes: 0
 * Total Accepted:    2.4K
 * Total Submissions: 2.9K
 * Testcase Example:  '2\n3'
 *
 * 给你两个整数 m 和 n，分别表示网格的行数和列数。
 *
 * 请你构造 任意 一个只包含字符 '.' 和 '#' 的 m x n 网格，其中：
 *
 *
 * '.' 表示空单元格。
 * '#' 表示障碍物单元格。
 *
 *
 * 有效路径 是满足以下条件的空单元格序列：
 *
 *
 * 从左上角单元格 (0, 0) 开始。
 * 在右下角单元格 (m - 1, n - 1) 结束。
 * 只能向：
 *
 * 右移动，从 (i, j) 到 (i, j + 1)，或者
 * 下移动，从 (i, j) 到 (i + 1, j)。
 *
 *
 *
 *
 * 返回任意一个从左上角到右下角 恰好只有一条有效路径 的网格。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： m = 2, n = 3
 *
 * 输出： ["..#","#.."]
 *
 * 解释：
 *
 *
 *
 * 唯一的有效路径是：(0,0) → (0,1) → (1,1) → (1,2)
 *
 *
 * 示例 2：
 *
 *
 * 输入： m = 3, n = 3
 *
 * 输出： ["..#","#..","##."]
 *
 * 解释：
 *
 *
 *
 * 唯一的有效路径是：(0,0) → (0,1) → (1,1) → (1,2) → (2,2)
 *
 *
 * 示例 3：
 *
 *
 * 输入： m = 1, n = 4
 *
 * 输出： ["...."]
 *
 * 解释：
 *
 * 唯一的有效路径是：(0,0) → (0,1) → (0,2) → (0,3)
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= m, n <= 25
 *
 *
 */

// @lc code=start
package main

import "strings"

func createGrid(m, n int) []string {
	result := make([]string, m)
	result[0] = strings.Repeat(".", n)
	row := strings.Repeat("#", n-1) + "."

	for i := 1; i < m; i++ {
		result[i] = row
	}

	return result
}

// @lc code=end
