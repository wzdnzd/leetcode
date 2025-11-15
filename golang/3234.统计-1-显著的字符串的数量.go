/*
 * @lc app=leetcode.cn id=3234 lang=golang
 *
 * [3234] 统计 1 显著的字符串的数量
 *
 * https://leetcode.cn/problems/count-the-number-of-substrings-with-dominant-ones/description/
 *
 * algorithms
 * Medium (24.19%)
 * Likes:    20
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 13.3K
 * Testcase Example:  '"00011"'
 *
 * 给你一个二进制字符串 s。
 *
 * 请你统计并返回其中 1 显著  的 子字符串 的数量。
 *
 * 如果字符串中 1 的数量 大于或等于 0 的数量的 平方，则认为该字符串是一个 1 显著 的字符串 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "00011"
 *
 * 输出：5
 *
 * 解释：
 *
 * 1 显著的子字符串如下表所示。
 *
 *
 *
 *
 *
 * i
 * j
 * s[i..j]
 * 0 的数量
 * 1 的数量
 *
 *
 *
 *
 * 3
 * 3
 * 1
 * 0
 * 1
 *
 *
 * 4
 * 4
 * 1
 * 0
 * 1
 *
 *
 * 2
 * 3
 * 01
 * 1
 * 1
 *
 *
 * 3
 * 4
 * 11
 * 0
 * 2
 *
 *
 * 2
 * 4
 * 011
 * 1
 * 2
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "101101"
 *
 * 输出：16
 *
 * 解释：
 *
 * 1 不显著的子字符串如下表所示。
 *
 * 总共有 21 个子字符串，其中 5 个是 1 不显著字符串，因此有 16 个 1
 * 显著子字符串。
 *
 *
 *
 *
 *
 * i
 * j
 * s[i..j]
 * 0 的数量
 * 1 的数量
 *
 *
 *
 *
 * 1
 * 1
 * 0
 * 1
 * 0
 *
 *
 * 4
 * 4
 * 0
 * 1
 * 0
 *
 *
 * 1
 * 4
 * 0110
 * 2
 * 2
 *
 *
 * 0
 * 4
 * 10110
 * 2
 * 3
 *
 *
 * 1
 * 5
 * 01101
 * 2
 * 3
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 4 * 10^4
 * s 仅包含字符 '0' 和 '1'。
 *
 *
 */

// @lc code=start
package main

func numberOfSubstrings(s string) int {
	records := []int{-1}
	total, count := 0, 0

	for i, c := range s {
		if c == '0' {
			records = append(records, i)
		} else {
			total++
			count += i - records[len(records)-1]
		}

		m := len(records)
		for j := m - 1; j > 0 && (m-j)*(m-j) <= total; j-- {
			p, q := records[j-1], records[j]
			zeros := m - j
			ones := i - q + 1 - zeros
			count += max(q-max(zeros*zeros-ones, 0)-p, 0)
		}
	}

	return count
}

// @lc code=end
