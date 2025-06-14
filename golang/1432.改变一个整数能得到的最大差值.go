/*
 * @lc app=leetcode.cn id=1432 lang=golang
 *
 * [1432] 改变一个整数能得到的最大差值
 *
 * https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer/description/
 *
 * algorithms
 * Medium (40.46%)
 * Likes:    25
 * Dislikes: 0
 * Total Accepted:    8K
 * Total Submissions: 19.9K
 * Testcase Example:  '555'
 *
 * 给你一个整数 num 。你可以对它进行以下步骤共计 两次：
 *
 *
 * 选择一个数字 x (0 <= x <= 9).
 * 选择另一个数字 y (0 <= y <= 9) 。数字 y 可以等于 x 。
 * 将 num 中所有出现 x 的数位都用 y 替换。
 *
 *
 * 令两次对 num 的操作得到的结果分别为 a 和 b 。
 *
 * 请你返回 a 和 b 的 最大差值 。
 *
 * 注意，新的整数（a 或 b）必须不能 含有前导 0，并且 非 0。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：num = 555
 * 输出：888
 * 解释：第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。
 * 第二次选择 x = 5 且 y = 1 ，并把得到的新数字保存在 b 中。
 * 现在，我们有 a = 999 和 b = 111 ，最大差值为 888
 *
 *
 * 示例 2：
 *
 *
 * 输入：num = 9
 * 输出：8
 * 解释：第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。
 * 第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在 b 中。
 * 现在，我们有 a = 9 和 b = 1 ，最大差值为 8
 *
 *
 * 示例 3：
 *
 *
 * 输入：num = 123456
 * 输出：820000
 *
 *
 * 示例 4：
 *
 *
 * 输入：num = 10000
 * 输出：80000
 *
 *
 * 示例 5：
 *
 *
 * 输入：num = 9288
 * 输出：8700
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= num <= 10^8
 *
 *
 */

// @lc code=start
package main

import (
	"strconv"
	"strings"
)

func maxDiff(num int) int {
	replace := func(s string, x, y byte) string {
		return strings.ReplaceAll(s, string(x), string(y))
	}

	minNum := strconv.Itoa(num)
	maxNum := strconv.Itoa(num)

	for _, digit := range maxNum {
		if digit != '9' {
			maxNum = replace(maxNum, byte(digit), '9')
			break
		}
	}

	for i := 0; i < len(minNum); i++ {
		digit := minNum[i]
		if i == 0 {
			if digit != '1' {
				minNum = replace(minNum, digit, '1')
				break
			}
		} else {
			if digit != '0' && digit != minNum[0] {
				minNum = replace(minNum, digit, '0')
				break
			}
		}
	}

	max, _ := strconv.Atoi(maxNum)
	min, _ := strconv.Atoi(minNum)

	return max - min
}

// @lc code=end
