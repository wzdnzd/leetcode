/*
 * @lc app=leetcode.cn id=1111 lang=golang
 *
 * [1111] 有效括号的嵌套深度
 *
 * https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/description/
 *
 * algorithms
 * Medium (76.98%)
 * Likes:    186
 * Dislikes: 0
 * Total Accepted:    33.1K
 * Total Submissions: 42.8K
 * Testcase Example:  '"(()())"'
 *
 * 如果一个字符串仅由字符 "(" 和 ")" 组成，并且满足以下条件，则称为有效括号字符串（VPS）：
 *
 *
 * 它是空字符串，或
 * 它可以表示为 AB（A 连接 B），其中 A 和 B 都是VPS，或者
 * 它可以表示为 (A)，其中 A 是一个 VPS。
 *
 *
 * 我们可以类似地定义任何 VPS S 的嵌套深度 depth(S) 如下：
 *
 *
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 VPS
 * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 VPS。
 *
 *
 * 例如，""，"()()" 和 "()(()())" 都是 VPS（嵌套深度 0，1 和 2），并且 ")(" 和 "(()" 不是 VPS。
 *
 * 给定一个 VPS 序列，将其拆分成两个不相交的子序列 A 和 B，使得 A 和 B 都是 VPS（且 A.length + B.length =
 * seq.length）。这些子序列不一定是连续的。
 *
 * 例如，对于序列 123456789，一种可能的拆分是：
 *
 *
 *
 * A = {1, 3, 5, 7, 9}，
 *
 *
 * B = {2, 4, 6, 8}。
 *
 *
 * 这对应于输出 [0, 1, 0, 1, 0, 1, 0, 1, 0]，其中 0 表示属于 A，1 表示属于 B。
 *
 *
 *
 * 现在选择 任意 这样的 A 和 B，使得 max(depth(A), depth(B)) 的值是最小的。
 *
 * 返回一个 answer 数组（长度为 seq.length），该数组编码了 A 和 B 的选择：如果 seq[i] 是 A 的一部分则
 * answer[i] = 0，否则 answer[i] = 1。请注意，尽管可能存在多种答案，但你可以返回其中任意一种。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 *
 *
 * 示例 2：
 *
 *
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 * 解释：本示例答案不唯一。
 * 按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
 * 像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A),
 * depth(B)) = 1 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 < seq.size <= 10000
 *
 *
 *
 *
 * 有效括号字符串：
 *
 *
 * 仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
 * 下述几种情况同样属于有效括号字符串：
 *
 * ⁠ 1. 空字符串
 * ⁠ 2. 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 * ⁠ 3. 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 *
 *
 * 嵌套深度：
 *
 *
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 *
 * ⁠ 1. s 为空时，depth("") = 0
 * ⁠ 2. s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 * ⁠ 3. s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 *
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()"
 * 都不是有效括号字符串。
 *
 *
 */

// @lc code=start
package main

func maxDepthAfterSplit(seq string) []int {
	result := make([]int, len(seq))

	for i, c := range seq {
		if c == '(' {
			result[i] = i % 2
		} else {
			result[i] = (i + 1) % 2
		}
	}

	return result
}

// @lc code=end
