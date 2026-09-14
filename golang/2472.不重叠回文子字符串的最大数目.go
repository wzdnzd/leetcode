/*
 * @lc app=leetcode.cn id=2472 lang=golang
 *
 * [2472] 不重叠回文子字符串的最大数目
 *
 * https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings/description/
 *
 * algorithms
 * Hard (49.11%)
 * Likes:    58
 * Dislikes: 0
 * Total Accepted:    10K
 * Total Submissions: 19.8K
 * Testcase Example:  '"abaccdbbd"\n3'
 *
 * 给你一个字符串 s 和一个 正 整数 k 。
 *
 * 从字符串 s 中选出一组满足下述条件且 不重叠 的子字符串：
 *
 *
 * 每个子字符串的长度 至少 为 k 。
 * 每个子字符串是一个 回文串 。
 *
 *
 * 返回最优方案中能选择的子字符串的 最大 数目。
 *
 * 子字符串 是字符串中一个连续的字符序列。
 *
 *
 *
 * 示例 1 ：
 *
 *
 * 输入：s = "abaccdbbd", k = 3
 * 输出：2
 * 解释：可以选择 s = "abaccdbbd" 中斜体加粗的子字符串。"aba" 和 "dbbd" 都是回文，且长度至少为 k = 3 。
 * 可以证明，无法选出两个以上的有效子字符串。
 *
 *
 * 示例 2 ：
 *
 *
 * 输入：s = "adbcda", k = 2
 * 输出：0
 * 解释：字符串中不存在长度至少为 2 的回文子字符串。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= k <= s.length <= 2000
 * s 仅由小写英文字母组成
 *
 *
 */

// @lc code=start
package main

func maxPalindromes(s string, k int) int {
	count, n := 0, len(s)
	for i := 0; i <= n-k; {
		if isPalindrome(s[i : i+k]) {
			count++
			i += k
		} else if i < n-k && isPalindrome(s[i:i+k+1]) {
			count++
			i += k + 1
		} else {
			i++
		}
	}

	return count
}

func isPalindrome(s string) bool {
	n := len(s)
	for i := range n / 2 {
		if s[i] != s[n-1-i] {
			return false
		}
	}

	return true
}

// @lc code=end
