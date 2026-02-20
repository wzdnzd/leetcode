/*
 * @lc app=leetcode.cn id=761 lang=golang
 *
 * [761] 特殊的二进制序列
 *
 * https://leetcode.cn/problems/special-binary-string/description/
 *
 * algorithms
 * Hard (74.94%)
 * Likes:    253
 * Dislikes: 0
 * Total Accepted:    20.7K
 * Total Submissions: 27.4K
 * Testcase Example:  '"11011000"'
 *
 * 特殊的二进制字符串 是具有以下两个性质的二进制序列：
 *
 *
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 *
 *
 * 给定一个特殊的二进制字符串 s。
 *
 * 一次移动操作包括选择字符串 s
 * 中的两个连续的、非空的、特殊子串，并交换它们。两个字符串是连续的，如果第一个字符串的最后一个字符与第二个字符串的第一个字符的索引相差正好为 1。
 *
 * 返回在字符串上应用任意次操作后可能得到的字典序最大的字符串。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: S = "11011000"
 * 输出: "11100100"
 * 解释:
 * 将子串 "10" （在 s[1] 出现） 和 "1100" （在 s[3] 出现）进行交换。
 * 这是在进行若干次操作后按字典序排列最大的结果。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "10"
 * 输出："10"
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 50
 * s[i] 为 '0' 或 '1'。
 * s 是一个特殊的二进制字符串。
 *
 *
 */

// @lc code=start
package main

import (
	"cmp"
	"slices"
	"strings"
)

func makeLargestSpecial(s string) string {
	if len(s) <= 2 {
		return s
	}

	records := []string{}
	start, diff := 0, 0

	for i, c := range s {
		if c == '1' {
			diff++
		} else if diff--; diff == 0 {
			records = append(records, "1"+makeLargestSpecial(s[start+1:i])+"0")
			start = i + 1
		}
	}

	slices.SortFunc(records, func(a, b string) int { return cmp.Compare(b, a) })
	return strings.Join(records, "")
}

// @lc code=end
