/*
 * @lc app=leetcode.cn id=2054 lang=golang
 *
 * [2054] 两个最好的不重叠活动
 *
 * https://leetcode.cn/problems/two-best-non-overlapping-events/description/
 *
 * algorithms
 * Medium (42.54%)
 * Likes:    71
 * Dislikes: 0
 * Total Accepted:    8.2K
 * Total Submissions: 18.4K
 * Testcase Example:  '[[1,3,2],[4,5,2],[2,4,3]]'
 *
 * 给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。第
 * i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。你 最多 可以参加
 * 两个时间不重叠 活动，使得它们的价值之和 最大 。
 *
 * 请你返回价值之和的 最大值 。
 *
 * 注意，活动的开始时间和结束时间是 包括
 * 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个活动，且结束时间为 t
 * ，那么下一个活动必须在 t + 1 或之后的时间开始。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：events = [[1,3,2],[4,5,2],[2,4,3]]
 * 输出：4
 * 解释：选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
 *
 *
 * 示例 2：
 *
 *
 *
 * 输入：events = [[1,3,2],[4,5,2],[1,5,5]]
 * 输出：5
 * 解释：选择活动 2 ，价值和为 5 。
 *
 *
 * 示例 3：
 *
 *
 *
 * 输入：events = [[1,5,3],[1,5,1],[6,6,5]]
 * 输出：8
 * 解释：选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= events.length <= 10^5
 * events[i].length == 3
 * 1 <= startTimei <= endTimei <= 10^9
 * 1 <= valuei <= 10^6
 *
 *
 */

// @lc code=start
package main

import "sort"

func maxTwoEvents(events [][]int) int {
	type Event struct {
		timestamp int
		action    int
		profit    int
	}

	records := make([]Event, 0)
	for _, event := range events {
		records = append(records, Event{event[0], 0, event[2]})
		records = append(records, Event{event[1], 1, event[2]})
	}

	sort.Slice(records, func(i, j int) bool {
		if records[i].timestamp != records[j].timestamp {
			return records[i].timestamp < records[j].timestamp
		}

		return records[i].action < records[j].action
	})

	result, bestFirst := 0, 0
	for _, e := range records {
		if e.action == 0 {
			if e.profit+bestFirst > result {
				result = e.profit + bestFirst
			}
		} else {
			if e.profit > bestFirst {
				bestFirst = e.profit
			}
		}
	}

	return result
}

// @lc code=end
