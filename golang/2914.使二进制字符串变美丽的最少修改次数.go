/*
 * @lc app=leetcode.cn id=2914 lang=golang
 *
 * [2914] 使二进制字符串变美丽的最少修改次数
 *
 * https://leetcode.cn/problems/minimum-number-of-changes-to-make-binary-string-beautiful/description/
 *
 * algorithms
 * Medium (77.33%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    7.4K
 * Total Submissions: 9.2K
 * Testcase Example:  '"1001"'
 *
 * 给你一个长度为偶数下标从 0 开始的二进制字符串 s 。
 *
 * 如果可以将一个字符串分割成一个或者更多满足以下条件的子字符串，那么我们称这个字符串是 美丽的 ：
 *
 *
 * 每个子字符串的长度都是 偶数 。
 * 每个子字符串都 只 包含 1 或 只 包含 0 。
 *
 *
 * 你可以将 s 中任一字符改成 0 或者 1 。
 *
 * 请你返回让字符串 s 美丽的 最少 字符修改次数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "1001"
 * 输出：2
 * 解释：我们将 s[1] 改为 1 ，且将 s[3] 改为 0 ，得到字符串 "1100" 。
 * 字符串 "1100" 是美丽的，因为我们可以将它分割成 "11|00" 。
 * 将字符串变美丽最少需要 2 次修改。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "10"
 * 输出：1
 * 解释：我们将 s[1] 改为 1 ，得到字符串 "11" 。
 * 字符串 "11" 是美丽的，因为它已经是美丽的。
 * 将字符串变美丽最少需要 1 次修改。
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "0000"
 * 输出：0
 * 解释：不需要进行任何修改，字符串 "0000" 已经是美丽字符串。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= s.length <= 10^5
 * s 的长度为偶数。
 * s[i] 要么是 '0' ，要么是 '1' 。
 *
 *
 */

// @lc code=start
package main

func minChanges(s string) int {
	count := 0

	for i := 0; i < len(s); i += 2 {
		if s[i] != s[i+1] {
			count++
		}
	}

	return count
}

// @lc code=end
