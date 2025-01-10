/*
 * @lc app=leetcode.cn id=3298 lang=golang
 *
 * [3298] 统计重新排列后包含另一个字符串的子字符串数目 II
 *
 * https://leetcode.cn/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-ii/description/
 *
 * algorithms
 * Hard (64.31%)
 * Likes:    19
 * Dislikes: 0
 * Total Accepted:    8.1K
 * Total Submissions: 11.4K
 * Testcase Example:  '"bcca"\n"abc"'
 *
 * 给你两个字符串 word1 和 word2 。
 *
 * 如果一个字符串 x 重新排列后，word2 是重排字符串的 前缀 ，那么我们称字符串 x 是 合法的 。
 *
 * 请你返回 word1 中 合法 子字符串 的数目。
 *
 * 注意 ，这个问题中的内存限制比其他题目要 小 ，所以你 必须 实现一个线性复杂度的解法。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：word1 = "bcca", word2 = "abc"
 *
 * 输出：1
 *
 * 解释：
 *
 * 唯一合法的子字符串是 "bcca" ，可以重新排列得到 "abcc" ，"abc" 是它的前缀。
 *
 *
 * 示例 2：
 *
 *
 * 输入：word1 = "abcabc", word2 = "abc"
 *
 * 输出：10
 *
 * 解释：
 *
 * 除了长度为 1 和 2 的所有子字符串都是合法的。
 *
 *
 * 示例 3：
 *
 *
 * 输入：word1 = "abcabc", word2 = "aaabc"
 *
 * 输出：0
 *
 *
 *
 *
 * 解释：
 *
 *
 * 1 <= word1.length <= 10^6
 * 1 <= word2.length <= 10^4
 * word1 和 word2 都只包含小写英文字母。
 *
 *
 */

// @lc code=start
package main

func validSubstringCount(word1 string, word2 string) int64 {
	diff := make([]int, 26)
	for _, c := range word2 {
		diff[c-'a']--
	}

	count := 0
	for _, c := range diff {
		if c < 0 {
			count++
		}
	}

	var result int64
	l, r := 0, 0

	for l < len(word1) {
		for r < len(word1) && count > 0 {
			update(diff, int(word1[r]-'a'), 1, &count)
			r++
		}

		if count == 0 {
			result += int64(len(word1) - r + 1)
		}

		update(diff, int(word1[l]-'a'), -1, &count)
		l++
	}

	return result
}

func update(diff []int, c, add int, count *int) {
	diff[c] += add
	if add == 1 && diff[c] == 0 {
		*count--
	} else if add == -1 && diff[c] == -1 {
		*count++
	}
}

// @lc code=end
