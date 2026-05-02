/*
 * @lc app=leetcode.cn id=788 lang=golang
 *
 * [788] 旋转数字
 *
 * https://leetcode.cn/problems/rotated-digits/description/
 *
 * algorithms
 * Medium (66.59%)
 * Likes:    230
 * Dislikes: 0
 * Total Accepted:    52.7K
 * Total Submissions: 78.7K
 * Testcase Example:  '10'
 *
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 *
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5
 * 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9
 * 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 *
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 *
 *
 *
 * 示例：
 *
 * 输入: 10
 * 输出: 4
 * 解释:
 * 在[1, 10]中有四个好数： 2, 5, 6, 9。
 * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
 *
 *
 *
 *
 * 提示：
 *
 *
 * N 的取值范围是 [1, 10000]。
 *
 *
 */

// @lc code=start
package main

import "strconv"

var diffs = [10]int{0, 0, 1, -1, -1, 1, 1, -1, 0, 1}

func rotatedDigits(n int) int {
	s := strconv.Itoa(n)

	m := len(s)
	memos := make([][2]int, m)
	for i := range memos {
		memos[i] = [2]int{-1, -1}
	}

	var dfs func(int, int, bool) int
	dfs = func(i, diff int, limit bool) int {
		result := 0

		if i == m {
			return diff
		}

		if !limit {
			p := &memos[i][diff]
			if *p >= 0 {
				return *p
			}

			defer func() { *p = result }()
		}

		upper := 9
		if limit {
			upper = int(s[i] - '0')
		}

		for num := 0; num <= upper; num++ {
			if diffs[num] != -1 {
				result += dfs(i+1, diff|diffs[num], limit && num == upper)
			}
		}

		return result
	}

	return dfs(0, 0, true)
}

// @lc code=end
