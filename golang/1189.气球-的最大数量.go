/*
 * @lc app=leetcode.cn id=1189 lang=golang
 *
 * [1189] “气球” 的最大数量
 *
 * https://leetcode.cn/problems/maximum-number-of-balloons/description/
 *
 * algorithms
 * Easy (68.25%)
 * Likes:    147
 * Dislikes: 0
 * Total Accepted:    69.1K
 * Total Submissions: 101K
 * Testcase Example:  '"nlaebolko"'
 *
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 *
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：text = "nlaebolko"
 * 输出：1
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 *
 *
 * 示例 3：
 *
 *
 * 输入：text = "leetcode"
 * 输出：0
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 *
 *
 *
 *
 * 注意：本题与 2287. 重排字符形成目标字符串 相同。
 *
 */

// @lc code=start
package main

import "math"

func maxNumberOfBalloons(text string) int {
	records := make([]int, 26)
	for _, c := range text {
		records[c-'a']++
	}

	targets := []rune{'a', 'b', 'l', 'n', 'o'}
	count := math.MaxInt
	for _, c := range targets {
		if c == 'o' || c == 'l' {
			count = min(count, records[c-'a']/2)
		} else {
			count = min(count, records[c-'a'])
		}
	}

	return count
}

func min(x, y int) int {
	if x < y {
		return x
	}

	return y
}

// @lc code=end
