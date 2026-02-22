/*
 * @lc app=leetcode.cn id=1461 lang=golang
 *
 * [1461] 检查一个字符串是否包含所有长度为 K 的二进制子串
 *
 * https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k/description/
 *
 * algorithms
 * Medium (59.45%)
 * Likes:    109
 * Dislikes: 0
 * Total Accepted:    27.1K
 * Total Submissions: 45.2K
 * Testcase Example:  '"00110110"\n2'
 *
 * 给你一个二进制字符串 s 和一个整数 k 。如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 true ，否则请返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "00110110", k = 2
 * 输出：true
 * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "0110", k = 1
 * 输出：true
 * 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "0110", k = 2
 * 输出：false
 * 解释：长度为 2 的二进制串 "00" 没有出现在 s 中。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 5 * 10^5
 * s[i] 不是'0' 就是 '1'
 * 1 <= k <= 20
 *
 *
 */

// @lc code=start
package main

func hasAllCodes(s string, k int) bool {
	records := make([]bool, 1<<k)
	mask := 1<<k - 1
	count, x := 0, 0

	for i, c := range s {
		x = x<<1&mask | int(c&1)
		if i < k-1 || records[x] {
			continue
		}

		records[x] = true
		count++

		if count == 1<<k {
			return true
		}
	}

	return false
}

// @lc code=end
