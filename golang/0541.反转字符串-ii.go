/*
 * @lc app=leetcode.cn id=541 lang=golang
 *
 * [541] 反转字符串 II
 *
 * https://leetcode.cn/problems/reverse-string-ii/description/
 *
 * algorithms
 * Easy (57.64%)
 * Likes:    633
 * Dislikes: 0
 * Total Accepted:    332.8K
 * Total Submissions: 576.5K
 * Testcase Example:  '"abcdefg"\n2'
 *
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^4
 * s 仅由小写英文组成
 * 1 <= k <= 10^4
 *
 *
 */

// @lc code=start
package main

func reverseStr(s string, k int) string {
	bytes := []byte(s)

	for i := 0; i < len(s); i += 2 * k {
		array := bytes[i:min(i+k, len(s))]
		for j, n := 0, len(array); j < n/2; j++ {
			array[j], array[n-1-j] = array[n-1-j], array[j]
		}
	}

	return string(bytes)
}

// @lc code=end
