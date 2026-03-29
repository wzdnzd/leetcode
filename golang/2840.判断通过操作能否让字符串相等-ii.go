/*
 * @lc app=leetcode.cn id=2840 lang=golang
 *
 * [2840] 判断通过操作能否让字符串相等 II
 *
 * https://leetcode.cn/problems/check-if-strings-can-be-made-equal-with-operations-ii/description/
 *
 * algorithms
 * Medium (63.45%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    6.9K
 * Total Submissions: 10.2K
 * Testcase Example:  '"abcdba"\n"cabdab"'
 *
 * 给你两个字符串 s1 和 s2 ，两个字符串长度都为 n ，且只包含 小写 英文字母。
 *
 * 你可以对两个字符串中的 任意一个 执行以下操作 任意 次：
 *
 *
 * 选择两个下标 i 和 j ，满足 i < j 且 j - i 是 偶数，然后 交换 这个字符串中两个下标对应的字符。
 *
 *
 *
 *
 * 如果你可以让字符串 s1 和 s2 相等，那么返回 true ，否则返回 false 。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s1 = "abcdba", s2 = "cabdab"
 * 输出：true
 * 解释：我们可以对 s1 执行以下操作：
 * - 选择下标 i = 0 ，j = 2 ，得到字符串 s1 = "cbadba" 。
 * - 选择下标 i = 2 ，j = 4 ，得到字符串 s1 = "cbbdaa" 。
 * - 选择下标 i = 1 ，j = 5 ，得到字符串 s1 = "cabdab" = s2 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s1 = "abe", s2 = "bea"
 * 输出：false
 * 解释：无法让两个字符串相等。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == s1.length == s2.length
 * 1 <= n <= 10^5
 * s1 和 s2 只包含小写英文字母。
 *
 *
 */

// @lc code=start
package main

func checkStrings(s1, s2 string) bool {
	var record1, record2 [2][26]int

	for i, c := range s1 {
		record1[i%2][c-'a']++
		record2[i%2][s2[i]-'a']++
	}

	return record1 == record2
}

// @lc code=end
