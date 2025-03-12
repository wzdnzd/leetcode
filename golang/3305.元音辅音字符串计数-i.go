/*
 * @lc app=leetcode.cn id=3305 lang=golang
 *
 * [3305] 元音辅音字符串计数 I
 *
 * https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/description/
 *
 * algorithms
 * Medium (39.62%)
 * Likes:    11
 * Dislikes: 0
 * Total Accepted:    4K
 * Total Submissions: 9.2K
 * Testcase Example:  '"aeioqq"\n1'
 *
 * 给你一个字符串 word 和一个 非负 整数 k。
 *
 * 返回 word 的 子字符串 中，每个元音字母（'a'、'e'、'i'、'o'、'u'）至少 出现一次，并且 恰好 包含 k
 * 个辅音字母的子字符串的总数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：word = "aeioqq", k = 1
 *
 * 输出：0
 *
 * 解释：
 *
 * 不存在包含所有元音字母的子字符串。
 *
 *
 * 示例 2：
 *
 *
 * 输入：word = "aeiou", k = 0
 *
 * 输出：1
 *
 * 解释：
 *
 * 唯一一个包含所有元音字母且不含辅音字母的子字符串是 word[0..4]，即 "aeiou"。
 *
 *
 * 示例 3：
 *
 *
 * 输入：word = "ieaouqqieaouqq", k = 1
 *
 * 输出：3
 *
 * 解释：
 *
 * 包含所有元音字母并且恰好含有一个辅音字母的子字符串有：
 *
 *
 * word[0..5]，即 "ieaouq"。
 * word[6..11]，即 "qieaou"。
 * word[7..12]，即 "ieaouq"。
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * 5 <= word.length <= 250
 * word 仅由小写英文字母组成。
 * 0 <= k <= word.length - 5
 *
 *
 */

// @lc code=start
package main

func countOfSubstrings(word string, k int) int64 {
	vowels := map[byte]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	statistics := func(m int) int64 {
		n := len(word)
		if n < m+1 {
			return int64(0)
		}

		var count int64 = 0
		consonants := 0
		records := make(map[byte]int)

		for i, j := 0, 0; i < n; i++ {
			for j < n && (consonants < m || len(records) < 5) {
				if vowels[word[j]] {
					records[word[j]]++
				} else {
					consonants++
				}

				j++
			}

			if consonants >= m && len(records) == 5 {
				count += int64(n - j + 1)
			}

			if vowels[word[i]] {
				records[word[i]]--
				if records[word[i]] == 0 {
					delete(records, word[i])
				}
			} else {
				consonants--
			}
		}

		return count
	}

	return statistics(k) - statistics(k+1)
}

// @lc code=end
