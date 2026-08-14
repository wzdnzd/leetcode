/*
 * @lc app=leetcode.cn id=3090 lang=golang
 *
 * [3090] 每个字符最多出现两次的最长子字符串
 *
 * https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/description/
 *
 * algorithms
 * Easy (72.48%)
 * Likes:    48
 * Dislikes: 0
 * Total Accepted:    51.3K
 * Total Submissions: 70.2K
 * Testcase Example:  '"bcbbbcba"'
 *
 * 给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： s = "bcbbbcba"
 *
 * 输出： 4
 *
 * 解释：
 *
 * 以下子字符串长度为 4，并且每个字符最多出现两次："bcbbbcba"。
 *
 *
 * 示例 2：
 *
 *
 * 输入： s = "aaaa"
 *
 * 输出： 2
 *
 * 解释：
 *
 * 以下子字符串长度为 2，并且每个字符最多出现两次："aaaa"。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= s.length <= 100
 *
 * s 仅由小写英文字母组成。
 *
 *
 */

// @lc code=start
package main

func maximumLengthSubstring(s string) int {
	records := [26]int{}
	left, count := 0, 0

	for i, c := range s {
		c -= 'a'
		records[c]++
		for records[c] > 2 {
			records[s[left]-'a']--
			left++
		}

		count = max(count, i-left+1)
	}

	return count
}

func max(x, y int) int {
	if x > y {
		return x
	}

	return y
}

// @lc code=end
