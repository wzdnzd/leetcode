/*
 * @lc app=leetcode.cn id=1128 lang=golang
 *
 * [1128] 等价多米诺骨牌对的数量
 *
 * https://leetcode.cn/problems/number-of-equivalent-domino-pairs/description/
 *
 * algorithms
 * Easy (54.59%)
 * Likes:    188
 * Dislikes: 0
 * Total Accepted:    58K
 * Total Submissions: 101.2K
 * Testcase Example:  '[[1,2],[2,1],[3,4],[5,6]]'
 *
 * 给你一组多米诺骨牌 dominoes 。
 *
 * 形式上，dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 当且仅当 (a == c 且 b == d) 或者
 * (a == d 且 b == c) 。即一张骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌。
 *
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对
 * (i, j) 的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 *
 *
 * 示例 2：
 *
 *
 * 输入：dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= dominoes.length <= 4 * 10^4
 * dominoes[i].length == 2
 * 1 <= dominoes[i][j] <= 9
 *
 *
 */

// @lc code=start
package main

func numEquivDominoPairs(dominoes [][]int) int {
	records := [100]int{}
	count := 0

	for _, d := range dominoes {
		if d[0] > d[1] {
			d[0], d[1] = d[1], d[0]
		}

		v := d[0]*10 + d[1]
		count += records[v]
		records[v]++
	}

	return count
}

// @lc code=end
