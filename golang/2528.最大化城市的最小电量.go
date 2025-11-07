/*
 * @lc app=leetcode.cn id=2528 lang=golang
 *
 * [2528] 最大化城市的最小电量
 *
 * https://leetcode.cn/problems/maximize-the-minimum-powered-city/description/
 *
 * algorithms
 * Hard (42.56%)
 * Likes:    65
 * Dislikes: 0
 * Total Accepted:    7.7K
 * Total Submissions: 17.2K
 * Testcase Example:  '[1,2,4,5,0]\n1\n2'
 *
 * 给你一个下标从 0 开始长度为 n 的整数数组 stations ，其中 stations[i] 表示第 i 座城市的供电站数目。
 *
 * 每个供电站可以在一定 范围 内给所有城市提供电力。换句话说，如果给定的范围是 r ，在城市 i 处的供电站可以给所有满足 |i - j| <= r 且
 * 0 <= i, j <= n - 1 的城市 j 供电。
 *
 *
 * |x| 表示 x 的 绝对值 。比方说，|7 - 5| = 2 ，|3 - 10| = 7 。
 *
 *
 * 一座城市的 电量 是所有能给它供电的供电站数目。
 *
 * 政府批准了可以额外建造 k 座供电站，你需要决定这些供电站分别应该建在哪里，这些供电站与已经存在的供电站有相同的供电范围。
 *
 * 给你两个整数 r 和 k ，如果以最优策略建造额外的发电站，返回所有城市中，最小电量的最大值是多少。
 *
 * 这 k 座供电站可以建在多个城市。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：stations = [1,2,4,5,0], r = 1, k = 2
 * 输出：5
 * 解释：
 * 最优方案之一是把 2 座供电站都建在城市 1 。
 * 每座城市的供电站数目分别为 [1,4,4,5,0] 。
 * - 城市 0 的供电站数目为 1 + 4 = 5 。
 * - 城市 1 的供电站数目为 1 + 4 + 4 = 9 。
 * - 城市 2 的供电站数目为 4 + 4 + 5 = 13 。
 * - 城市 3 的供电站数目为 5 + 4 = 9 。
 * - 城市 4 的供电站数目为 5 + 0 = 5 。
 * 供电站数目最少是 5 。
 * 无法得到更优解，所以我们返回 5 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：stations = [4,4,4,4], r = 0, k = 3
 * 输出：4
 * 解释：
 * 无论如何安排，总有一座城市的供电站数目是 4 ，所以最优解是 4 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == stations.length
 * 1 <= n <= 10^5
 * 0 <= stations[i] <= 10^5
 * 0 <= r <= n - 1
 * 0 <= k <= 10^9
 *
 *
 */

// @lc code=start
package main

func maxPower(stations []int, r int, k int) int64 {
	n := len(stations)
	records := make([]int64, n+1)

	for i := 0; i < n; i++ {
		left := max(0, i-r)
		right := min(n, i+r+1)
		records[left] += int64(stations[i])
		records[right] -= int64(stations[i])
	}

	minVal := int64(stations[0])
	sumTotal := int64(0)
	for _, s := range stations {
		if int64(s) < minVal {
			minVal = int64(s)
		}

		sumTotal += int64(s)
	}

	low, high := minVal, sumTotal+int64(k)
	var result int64 = 0

	for low <= high {
		mid := low + (high-low)/2
		if check(records, mid, r, k) {
			result = mid
			low = mid + 1
		} else {
			high = mid - 1
		}
	}

	return result
}

func check(records []int64, val int64, r int, k int) bool {
	n := len(records) - 1
	diffs := make([]int64, len(records))
	copy(diffs, records)

	var sum int64 = 0
	remaining := int64(k)

	for i := 0; i < n; i++ {
		sum += diffs[i]
		if sum < val {
			add := val - sum
			if remaining < add {
				return false
			}

			remaining -= add
			end := min(n, i+2*r+1)
			diffs[end] -= add
			sum += add
		}
	}

	return true
}

// @lc code=end
