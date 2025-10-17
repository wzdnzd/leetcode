/*
 * @lc app=leetcode.cn id=3003 lang=golang
 *
 * [3003] 执行操作后的最大分割数量
 *
 * https://leetcode.cn/problems/maximize-the-number-of-partitions-after-operations/description/
 *
 * algorithms
 * Hard (27.95%)
 * Likes:    16
 * Dislikes: 0
 * Total Accepted:    2.7K
 * Total Submissions: 8K
 * Testcase Example:  '"accca"\n2'
 *
 * 给你一个下标从 0 开始的字符串 s 和一个整数 k。
 *
 * 你需要执行以下分割操作，直到字符串 s 变为 空：
 *
 *
 * 选择 s 的最长 前缀，该前缀最多包含 k 个 不同 字符。
 * 删除 这个前缀，并将分割数量加一。如果有剩余字符，它们在 s 中保持原来的顺序。
 *
 *
 * 执行操作之 前 ，你可以将 s 中 至多一处 下标的对应字符更改为另一个小写英文字母。
 *
 * 在最优选择情形下改变至多一处下标对应字符后，用整数表示并返回操作结束时得到的 最大 分割数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "accca", k = 2
 *
 * 输出：3
 *
 * 解释：
 *
 * 最好的方式是把 s[2] 变为除了 a 和 c 之外的东西，比如 b。然后它变成了 "acbca"。
 *
 * 然后我们执行以下操作：
 *
 *
 * 最多包含 2 个不同字符的最长前缀是 "ac"，我们删除它然后 s 变为 "bca"。
 * 现在最多包含 2 个不同字符的最长前缀是 "bc"，所以我们删除它然后 s 变为 "a"。
 * 最后，我们删除 "a" 并且 s 变成空串，所以该过程结束。
 *
 *
 * 进行操作时，字符串被分成 3 个部分，所以答案是 3。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "aabaab", k = 3
 *
 * 输出：1
 *
 * 解释：
 *
 * 一开始 s 包含 2 个不同的字符，所以无论我们改变哪个， 它最多包含 3 个不同字符，因此最多包含 3 个不同字符的最长前缀始终是所有字符，因此答案是
 * 1。
 *
 *
 * 示例 3：
 *
 *
 * 输入：s = "xxyz", k = 1
 *
 * 输出：4
 *
 * 解释：
 *
 * 最好的方式是将 s[0] 或 s[1] 变为 s 中字符以外的东西，例如将 s[0] 变为 w。
 *
 * 然后 s 变为 "wxyz"，包含 4 个不同的字符，所以当 k 为 1，它将分为 4 个部分。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^4
 * s 只包含小写英文字母。
 * 1 <= k <= 26
 *
 *
 */

// @lc code=start
package main

import "math/bits"

func maxPartitionsAfterOperations(s string, k int) int {
	if k == 26 {
		return 1
	}

	segment, mask, size := 1, 0, 0
	update := func(i int) {
		bit := 1 << (s[i] - 'a')
		if mask&bit > 0 {
			return
		}

		size++
		if size > k {
			segment++
			mask = bit
			size = 1
		} else {
			mask |= bit
		}
	}

	n := len(s)
	type pair struct{ seg, mask int }
	suffixs := make([]pair, n+1)
	for i := n - 1; i >= 0; i-- {
		update(i)
		suffixs[i] = pair{segment, mask}
	}

	count := segment
	segment, mask, size = 1, 0, 0
	for i := range s {
		p := suffixs[i+1]
		result := segment + p.seg

		unionSize := bits.OnesCount(uint(mask | p.mask))
		if unionSize < k {
			result--
		} else if unionSize < 26 && size == k && bits.OnesCount(uint(p.mask)) == k {
			result++
		}

		count = max(count, result)
		update(i)
	}

	return count
}

// @lc code=end
