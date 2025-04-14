/*
 * @lc app=leetcode.cn id=1534 lang=golang
 *
 * [1534] 统计好三元组
 *
 * https://leetcode.cn/problems/count-good-triplets/description/
 *
 * algorithms
 * Easy (75.58%)
 * Likes:    123
 * Dislikes: 0
 * Total Accepted:    69.3K
 * Total Submissions: 91.4K
 * Testcase Example:  '[3,0,1,1,9,7]\n7\n2\n3'
 *
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
 *
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 *
 *
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 *
 *
 * 其中 |x| 表示 x 的绝对值。
 *
 * 返回 好三元组的数量 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * 输出：4
 * 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
 *
 *
 * 示例 2：
 *
 * 输入：arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * 输出：0
 * 解释：不存在满足所有条件的三元组。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 *
 *
 */

// @lc code=start
package main

import "slices"

func countGoodTriplets(arr []int, a, b, c int) int {
	count := 0
	maxValue := slices.Max(arr)
	records := make([]int, maxValue+2)

	for i, x := range arr {
		for _, y := range arr[i+1:] {
			if abs(x-y) > b {
				continue
			}

			l := max(max(x-a, y-c), 0)
			r := min(min(x+a, y+c), maxValue)
			count += max(records[r+1]-records[l], 0)
		}

		for v := x + 1; v < maxValue+2; v++ {
			records[v]++
		}
	}

	return count
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

// @lc code=end
