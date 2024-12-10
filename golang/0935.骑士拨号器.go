/*
 * @lc app=leetcode.cn id=935 lang=golang
 *
 * [935] 骑士拨号器
 *
 * https://leetcode.cn/problems/knight-dialer/description/
 *
 * algorithms
 * Medium (54.77%)
 * Likes:    155
 * Dislikes: 0
 * Total Accepted:    13.7K
 * Total Submissions: 23.9K
 * Testcase Example:  '1'
 *
 * 象棋骑士有一个独特的移动方式，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个 L 的形状)。
 *
 * 象棋骑士可能的移动方式如下图所示:
 *
 *
 *
 * 我们有一个象棋骑士和一个电话垫，如下所示，骑士只能站在一个数字单元格上(即蓝色单元格)。
 *
 *
 *
 * 给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。
 *
 * 你可以将骑士放置在任何数字单元格上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是有效的骑士跳跃。
 *
 * 因为答案可能很大，所以输出答案模 10^9 + 7.
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 1
 * 输出：10
 * 解释：我们需要拨一个长度为1的数字，所以把骑士放在10个单元格中的任何一个数字单元格上都能满足条件。
 *
 *
 * 示例 2：
 *
 *
 * 输入：n = 2
 * 输出：20
 * 解释：我们可以拨打的所有有效号码为[04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67,
 * 72, 76, 81, 83, 92, 94]
 *
 *
 * 示例 3：
 *
 *
 * 输入：n = 3131
 * 输出：136006598
 * 解释：注意取模
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 5000
 *
 *
 */

// @lc code=start
package main

var edges = [][]int{{6, 4}, {8, 6}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}}

func knightDialer(n int) int {
	mod := 1000000007
	dp := make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, 10)
	}

	for j := range dp[1] {
		dp[1][j] = 1
	}

	for i := 2; i <= n; i++ {
		for j := range dp[i] {
			for _, e := range edges[j] {
				dp[i][j] = (dp[i][j] + dp[i-1][e]) % (1e9 + 7)
			}
		}
	}

	count := 0
	for j := range dp[n] {
		count = (count + dp[n][j]) % mod
	}

	return count
}

// @lc code=end
