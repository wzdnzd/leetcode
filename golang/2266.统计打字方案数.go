/*
 * @lc app=leetcode.cn id=2266 lang=golang
 *
 * [2266] 统计打字方案数
 *
 * https://leetcode.cn/problems/count-number-of-texts/description/
 *
 * algorithms
 * Medium (46.71%)
 * Likes:    90
 * Dislikes: 0
 * Total Accepted:    17K
 * Total Submissions: 36K
 * Testcase Example:  '"22233"'
 *
 * Alice 在给 Bob 用手机打字。数字到字母的 对应 如下图所示。
 *
 *
 *
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 *
 *
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 *
 *
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 *
 *
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 *
 *
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 *
 * 由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：pressedKeys = "22233"
 * 输出：8
 * 解释：
 * Alice 可能发出的文字信息包括：
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
 * 由于总共有 8 种可能的信息，所以我们返回 8 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：pressedKeys = "222222222222222222222222222222222222"
 * 输出：82876089
 * 解释：
 * 总共有 2082876103 种 Alice 可能发出的文字信息。
 * 由于我们需要将答案对 10^9 + 7 取余，所以我们返回 2082876103 % (10^9 + 7) = 82876089 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= pressedKeys.length <= 10^5
 * pressedKeys 只包含数字 '2' 到 '9' 。
 *
 *
 */

// @lc code=start
package main

func countTexts(pressedKeys string) int {
	const mod = 1e9 + 7

	dp := [100000][2]int{{1, 1}}
	for i := 1; i < len(pressedKeys); i++ {
		dp[i][0] = dp[i-1][1]
		dp[i][1] = dp[i][0]

		if pressedKeys[i] == pressedKeys[i-1] {
			dp[i][1] = (dp[i-1][0] + dp[i][1]) % mod
			if i-2 >= 0 && pressedKeys[i] == pressedKeys[i-2] {
				dp[i][1] = (dp[i-2][0] + dp[i][1]) % mod

				if i-3 >= 0 && pressedKeys[i] == pressedKeys[i-3] && (pressedKeys[i] == '7' || pressedKeys[i] == '9') {
					dp[i][1] = (dp[i-3][0] + dp[i][1]) % mod
				}
			}
		}
	}

	return dp[len(pressedKeys)-1][1]
}

// @lc code=end
