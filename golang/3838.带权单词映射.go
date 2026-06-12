/*
 * @lc app=leetcode.cn id=3838 lang=golang
 *
 * [3838] 带权单词映射
 *
 * https://leetcode.cn/problems/weighted-word-mapping/description/
 *
 * algorithms
 * Easy (87.43%)
 * Likes:    4
 * Dislikes: 0
 * Total Accepted:    2.9K
 * Total Submissions: 3.2K
 * Testcase Example:  '["abcd","def","xyz"]\n' +
  '[5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2]'
 *
 * 给你一个字符串数组 words，其中每个字符串表示一个由小写英文字母组成的单词。
 *
 * 同时给你一个长度为 26 的整数数组 weights，其中 weights[i] 表示第 i 个小写英文字母的权重。
 *
 * 单词的 权重 定义为其所有字符权重的 总和。
 *
 * 对于每个单词，将其权重对 26 取模，并将结果按字母倒序映射到一个小写英文字母（0 -> 'z', 1 -> 'y', ..., 25 ->
 * 'a'）。
 *
 * 返回一个由所有单词映射后的字符按顺序连接而成的字符串。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： words = ["abcd","def","xyz"], weights =
 * [5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2]
 *
 * 输出： "rij"
 *
 * 解释：
 *
 *
 * "abcd" 的权重是 5 + 3 + 12 + 14 = 34。对 26 取模的结果是 34 % 26 = 8，映射为 'r'。
 * "def" 的权重是 14 + 1 + 2 = 17。对 26 取模的结果是 17 % 26 = 17，映射为 'i'。
 * "xyz" 的权重是 7 + 7 + 2 = 16。对 26 取模的结果是 16 % 26 = 16，映射为 'j'。
 *
 *
 * 因此，连接映射字符后形成的字符串是 "rij"。
 *
 *
 * 示例 2：
 *
 *
 * 输入： words = ["a","b","c"], weights =
 * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 *
 * 输出： "yyy"
 *
 * 解释：
 *
 * 每个单词的权重均为 1。对 26 取模的结果是 1 % 26 = 1，映射为 'y'。
 *
 * 因此，连接映射字符后形成的字符串是 "yyy"。
 *
 *
 * 示例 3：
 *
 *
 * 输入： words = ["abcd"], weights =
 * [7,5,3,4,3,5,4,9,4,2,2,7,10,2,5,10,6,1,2,2,4,1,3,4,4,5]
 *
 * 输出： "g"
 *
 * 解释：
 *
 * "abcd" 的权重是 7 + 5 + 3 + 4 = 19。对 26 取模的结果是 19 % 26 = 19，映射为 'g'。
 *
 * 因此，连接映射字符后形成的字符串是 "g"。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 10
 * weights.length == 26
 * 1 <= weights[i] <= 100
 * words[i] 仅由小写英文字母组成。
 *
 *
*/

// @lc code=start
package main

func mapWordWeights(words []string, weights []int) string {
	chars := make([]rune, len(words))
	for index, word := range words {
		sum := 0
		for _, c := range word {
			sum += weights[c-'a']
		}

		chars[index] = 'a' + rune(25-(sum%26))
	}

	return string(chars)
}

// @lc code=end
