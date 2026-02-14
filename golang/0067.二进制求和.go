/*
 * @lc app=leetcode.cn id=67 lang=golang
 *
 * [67] 二进制求和
 *
 * https://leetcode.cn/problems/add-binary/description/
 *
 * algorithms
 * Easy (54.07%)
 * Likes:    1350
 * Dislikes: 0
 * Total Accepted:    507.8K
 * Total Submissions: 937.9K
 * Testcase Example:  '"11"\n"1"'
 *
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入:a = "11", b = "1"
 * 输出："100"
 *
 * 示例 2：
 *
 *
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= a.length, b.length <= 10^4
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 *
 *
 */

// @lc code=start
package main

func addBinary(a, b string) string {
	if len(a) < len(b) {
		a, b = b, a
	}

	n, m := len(a), len(b)
	bytes := make([]byte, n+1)
	carry := byte(0)

	for i := n - 1; i >= 0; i-- {
		sum := a[i] - '0' + carry
		if j := m - (n - i); j >= 0 {
			sum += b[j] - '0'
		}

		bytes[i+1] = sum%2 + '0'
		carry = sum / 2
	}

	bytes[0] = carry + '0'
	return string(bytes[carry^1:])
}

// @lc code=end
