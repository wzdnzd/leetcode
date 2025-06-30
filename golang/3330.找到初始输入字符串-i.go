/*
 * @lc app=leetcode.cn id=3330 lang=golang
 *
 * [3330] 找到初始输入字符串 I
 *
 * https://leetcode.cn/problems/find-the-original-typed-string-i/description/
 *
 * algorithms
 * Easy (69.21%)
 * Likes:    2
 * Dislikes: 0
 * Total Accepted:    3.6K
 * Total Submissions: 5.2K
 * Testcase Example:  '"abbcccc"'
 *
 * Alice 正在她的电脑上输入一个字符串。但是她打字技术比较笨拙，她 可能 在一个按键上按太久，导致一个字符被输入 多次 。
 *
 * 尽管 Alice 尽可能集中注意力，她仍然可能会犯错 至多 一次。
 *
 * 给你一个字符串 word ，它表示 最终 显示在 Alice 显示屏上的结果。
 *
 * 请你返回 Alice 一开始可能想要输入字符串的总方案数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：word = "abbcccc"
 *
 * 输出：5
 *
 * 解释：
 *
 * 可能的字符串包括："abbcccc" ，"abbccc" ，"abbcc" ，"abbc" 和 "abcccc" 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：word = "abcd"
 *
 * 输出：1
 *
 * 解释：
 *
 * 唯一可能的字符串是 "abcd" 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：word = "aaaa"
 *
 * 输出：4
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= word.length <= 100
 * word 只包含小写英文字母。
 *
 *
 */

// @lc code=start
package main

func possibleStringCount(word string) int {
	n, count := len(word), 1

	for i := 1; i < n; i++ {
		if word[i-1] == word[i] {
			count++
		}
	}

	return count
}

// @lc code=end
