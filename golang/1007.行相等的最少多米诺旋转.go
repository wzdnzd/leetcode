/*
 * @lc app=leetcode.cn id=1007 lang=golang
 *
 * [1007] 行相等的最少多米诺旋转
 *
 * https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row/description/
 *
 * algorithms
 * Medium (48.92%)
 * Likes:    139
 * Dislikes: 0
 * Total Accepted:    12.2K
 * Total Submissions: 24.2K
 * Testcase Example:  '[2,1,2,4,2,2]\n[5,2,6,2,3,2]'
 *
 * 在一排多米诺骨牌中，tops[i] 和 bottoms[i] 分别代表第 i 个多米诺骨牌的上半部分和下半部分。（一个多米诺是两个从 1 到 6
 * 的数字同列平铺形成的 —— 该平铺的每一半上都有一个数字。）
 *
 * 我们可以旋转第 i 张多米诺，使得 tops[i] 和 bottoms[i] 的值交换。
 *
 * 返回能使 tops 中所有值或者 bottoms 中所有值都相同的最小旋转次数。
 *
 * 如果无法做到，返回 -1.
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
 * 输出：2
 * 解释：
 * 图一表示：在我们旋转之前， tops 和 bottoms 给出的多米诺牌。
 * 如果我们旋转第二个和第四个多米诺骨牌，我们可以使上面一行中的每个值都等于 2，如图二所示。
 *
 *
 * 示例 2：
 *
 *
 * 输入：tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
 * 输出：-1
 * 解释： 在这种情况下，不可能旋转多米诺牌使一行的值相等。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= tops.length <= 2 * 10^4
 * bottoms.length == tops.length
 * 1 <= tops[i], bottoms[i] <= 6
 *
 *
 */

// @lc code=start
package main

import "math"

func minDominoRotations(tops, bottoms []int) int {
	minRot := func(target int) int {
		toTop, toBottom := 0, 0

		for i, x := range tops {
			y := bottoms[i]
			if x != target && y != target {
				return math.MaxInt
			}

			if x != target {
				toTop++
			} else if y != target {
				toBottom++
			}
		}

		return min(toTop, toBottom)
	}

	count := min(minRot(tops[0]), minRot(bottoms[0]))
	if count == math.MaxInt {
		return -1
	}

	return count
}

// @lc code=end
