/*
 * @lc app=leetcode.cn id=2124 lang=golang
 *
 * [2124] 检查是否所有 A 都在 B 之前
 *
 * https://leetcode.cn/problems/check-if-all-as-appears-before-all-bs/description/
 *
 * algorithms
 * Easy (69.70%)
 * Likes:    24
 * Dislikes: 0
 * Total Accepted:    21K
 * Total Submissions: 29.9K
 * Testcase Example:  '"aaabbb"'
 *
 * 给你一个 仅 由字符 'a' 和 'b' 组成的字符串  s 。如果字符串中 每个  'a' 都出现在 每个 'b' 之前，返回 true ；否则，返回
 * false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aaabbb"
 * 输出：true
 * 解释：
 * 'a' 位于下标 0、1 和 2 ；而 'b' 位于下标 3、4 和 5 。
 * 因此，每个 'a' 都出现在每个 'b' 之前，所以返回 true 。
 *
 *
 * 示例 2：
 *
 * 输入：s = "abab"
 * 输出：false
 * 解释：
 * 存在一个 'a' 位于下标 2 ，而一个 'b' 位于下标 1 。
 * 因此，不能满足每个 'a' 都出现在每个 'b' 之前，所以返回 false 。
 *
 *
 * 示例 3：
 *
 * 输入：s = "bbb"
 * 输出：true
 * 解释：
 * 不存在 'a' ，因此可以视作每个 'a' 都出现在每个 'b' 之前，所以返回 true 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 100
 * s[i] 为 'a' 或 'b'
 *
 *
 */

// @lc code=start
package main

import "strings"

func checkString(s string) bool {
	return !strings.Contains(s, "ba")
}

// @lc code=end
