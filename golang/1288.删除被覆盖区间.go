/*
 * @lc app=leetcode.cn id=1288 lang=golang
 *
 * [1288] 删除被覆盖区间
 *
 * https://leetcode.cn/problems/remove-covered-intervals/description/
 *
 * algorithms
 * Medium (56.49%)
 * Likes:    132
 * Dislikes: 0
 * Total Accepted:    39.7K
 * Total Submissions: 69.3K
 * Testcase Example:  '[[1,4],[3,6],[2,8]]'
 *
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 *
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 *
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 *
 *
 *
 * 示例：
 *
 *
 * 输入：intervals = [[1,4],[3,6],[2,8]]
 * 输出：2
 * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
 *
 *
 *
 *
 * 提示：​​​​​​
 *
 *
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * 对于所有的 i != j：intervals[i] != intervals[j]
 *
 *
 */

// @lc code=start
package main

import (
	"cmp"
	"slices"
)

func removeCoveredIntervals(intervals [][]int) int {
	slices.SortFunc(intervals, func(a, b []int) int {
		return cmp.Or(a[0]-b[0], b[1]-a[1])
	})

	count, maxRight := 0, 0
	for _, interval := range intervals {
		if interval[1] > maxRight {
			maxRight = interval[1]
			count++
		}
	}

	return count
}

// @lc code=end
