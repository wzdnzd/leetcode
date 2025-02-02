/*
 * @lc app=leetcode.cn id=680 lang=golang
 *
 * [680] 验证回文串 II
 *
 * https://leetcode.cn/problems/valid-palindrome-ii/description/
 *
 * algorithms
 * Easy (40.42%)
 * Likes:    668
 * Dislikes: 0
 * Total Accepted:    158.7K
 * Total Submissions: 392.1K
 * Testcase Example:  '"aba"'
 *
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 *
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "aba"
 * 输出：true
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "abca"
 * 输出：true
 * 解释：你可以删除字符 'c' 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "abc"
 * 输出：false
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 *
 *
 */

// @lc code=start
package main

func validPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		if s[i] != s[j] {
			return isPalindrome(s[i+1:j+1]) || isPalindrome(s[i:j])
		}

		i++
		j--
	}

	return true
}

func isPalindrome(s string) bool {
	i, j := 0, len(s)-1

	for i < j {
		if s[i] != s[j] {
			return false
		}

		i++
		j--
	}

	return true
}

// @lc code=end
