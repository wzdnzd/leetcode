/*
 * @lc app=leetcode.cn id=955 lang=golang
 *
 * [955] 删列造序 II
 *
 * https://leetcode.cn/problems/delete-columns-to-make-sorted-ii/description/
 *
 * algorithms
 * Medium (37.35%)
 * Likes:    101
 * Dislikes: 0
 * Total strsccepted:    8.6K
 * Total Submissions: 21.7K
 * Testcase Example:  '["ca","bb","ac"]'
 *
 * 给定由 n 个字符串组成的数组 strs，其中每个字符串长度相等。
 *
 * 选取一个删除索引序列，对于 strs 中的每个字符串，删除对应每个索引处的字符。
 *
 * 比如，有 strs = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 strs 为["bef", "vyz"]。
 *
 * 假设，我们选择了一组删除索引 answer，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（strs[0] ）排列的，然后请你返回
 * answer.length 的最小可能值。
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：strs = ["ca","bb","ac"]
 * 输出：1
 * 解释：
 * 删除第一列后，strs = ["a", "b", "c"]。
 * 现在 strs 中元素是按字典排列的 (即，strs[0]
 *
 * 示例 2：
 *
 *
 * 输入：strs = ["xc","yb","za"]
 * 输出：0
 * 解释：
 * strs 的列已经是按字典序排列了，所以我们不需要删除任何东西。
 * 注意 strs 的行不需要按字典序排列。
 * 也就是说，strs[0][0]
 *
 * 示例 3：
 *
 *
 * 输入：strs = ["zyx","wvu","tsr"]
 * 输出：3
 * 解释：
 * 我们必须删掉每一列。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == strs.length
 * 1
 * 1
 * strs[i] 由小写英文字母组成
 *
 *
 */

// @lc code=start
package main

func minDeletionSize(strs []string) int {
	count := 0
	records := make([]bool, len(strs)-1)

	for j := 0; j < len(strs[0]); j++ {
		canKeep := true
		for i := 0; i < len(strs)-1; i++ {
			if !records[i] && strs[i][j] > strs[i+1][j] {
				canKeep = false
				break
			}
		}

		if canKeep {
			for i := 0; i < len(strs)-1; i++ {
				if strs[i][j] < strs[i+1][j] {
					records[i] = true
				}
			}
		} else {
			count++
		}
	}

	return count
}

// @lc code=end
