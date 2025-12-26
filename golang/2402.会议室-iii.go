/*
 * @lc app=leetcode.cn id=2402 lang=golang
 *
 * [2402] 会议室 III
 *
 * https://leetcode.cn/problems/meeting-rooms-iii/description/
 *
 * algorithms
 * Hard (34.72%)
 * Likes:    65
 * Dislikes: 0
 * Total Accepted:    10.2K
 * Total Submissions: 28K
 * Testcase Example:  '2\n[[0,10],[1,5],[2,7],[3,4]]'
 *
 * 给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。
 *
 * 给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi] 表示一场会议将会在 半闭 时间区间
 * [starti, endi) 举办。所有 starti 的值 互不相同 。
 *
 * 会议将会按以下方式分配给会议室：
 *
 *
 * 每场会议都会在未占用且编号 最小 的会议室举办。
 * 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同 。
 * 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
 *
 *
 * 返回举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。
 *
 * 半闭区间 [a, b) 是 a 和 b 之间的区间，包括 a 但 不包括 b 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * 输出：0
 * 解释：
 * - 在时间 0 ，两个会议室都未占用，第一场会议在会议室 0 举办。
 * - 在时间 1 ，只有会议室 1 未占用，第二场会议在会议室 1 举办。
 * - 在时间 2 ，两个会议室都被占用，第三场会议延期举办。
 * - 在时间 3 ，两个会议室都被占用，第四场会议延期举办。
 * - 在时间 5 ，会议室 1 的会议结束。第三场会议在会议室 1 举办，时间周期为 [5,10) 。
 * - 在时间 10 ，两个会议室的会议都结束。第四场会议在会议室 0 举办，时间周期为 [10,11) 。
 * 会议室 0 和会议室 1 都举办了 2 场会议，所以返回 0 。
 *
 *
 * 示例 2：
 *
 * 输入：n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * 输出：1
 * 解释：
 * - 在时间 1 ，所有三个会议室都未占用，第一场会议在会议室 0 举办。
 * - 在时间 2 ，会议室 1 和 2 未占用，第二场会议在会议室 1 举办。
 * - 在时间 3 ，只有会议室 2 未占用，第三场会议在会议室 2 举办。
 * - 在时间 4 ，所有三个会议室都被占用，第四场会议延期举办。
 * - 在时间 5 ，会议室 2 的会议结束。第四场会议在会议室 2 举办，时间周期为 [5,10) 。
 * - 在时间 6 ，所有三个会议室都被占用，第五场会议延期举办。
 * - 在时间 10 ，会议室 1 和 2 的会议结束。第五场会议在会议室 1 举办，时间周期为 [10,12) 。
 * 会议室 1 和会议室 2 都举办了 2 场会议，所以返回 1 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n <= 100
 * 1 <= meetings.length <= 10^5
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 10^5
 * starti 的所有值 互不相同
 *
 *
 */

// @lc code=start
package main

import (
	"container/heap"
	"sort"
)

type RoomHeap []int

func (h RoomHeap) Len() int            { return len(h) }
func (h RoomHeap) Less(i, j int) bool  { return h[i] < h[j] }
func (h RoomHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *RoomHeap) Push(x interface{}) { *h = append(*h, x.(int)) }
func (h *RoomHeap) Pop() interface{} {
	old := *h
	x := old[len(old)-1]
	*h = old[:len(old)-1]
	return x
}

type UsedRoom struct{ endTime, room int }
type UsedHeap []UsedRoom

func (h UsedHeap) Len() int { return len(h) }
func (h UsedHeap) Less(i, j int) bool {
	if h[i].endTime == h[j].endTime {
		return h[i].room < h[j].room
	}

	return h[i].endTime < h[j].endTime
}

func (h UsedHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *UsedHeap) Push(x interface{}) { *h = append(*h, x.(UsedRoom)) }
func (h *UsedHeap) Pop() interface{} {
	old := *h
	x := old[len(old)-1]
	*h = old[:len(old)-1]
	return x
}

func mostBooked(n int, meetings [][]int) int {
	sort.Slice(meetings, func(i, j int) bool { return meetings[i][0] < meetings[j][0] })
	availRooms := &RoomHeap{}
	for i := 0; i < n; i++ {
		*availRooms = append(*availRooms, i)
	}

	heap.Init(availRooms)
	usedRooms := &UsedHeap{}
	heap.Init(usedRooms)
	usedCount := make([]int, n)
	currentTime := 0

	for _, meeting := range meetings {
		if currentTime < meeting[0] {
			currentTime = meeting[0]
		}

		for usedRooms.Len() > 0 && (*usedRooms)[0].endTime <= currentTime {
			heap.Push(availRooms, heap.Pop(usedRooms).(UsedRoom).room)
		}

		if availRooms.Len() == 0 {
			currentTime = (*usedRooms)[0].endTime
			for usedRooms.Len() > 0 && (*usedRooms)[0].endTime <= currentTime {
				heap.Push(availRooms, heap.Pop(usedRooms).(UsedRoom).room)
			}
		}

		room := heap.Pop(availRooms).(int)
		usedCount[room]++
		heap.Push(usedRooms, UsedRoom{currentTime + meeting[1] - meeting[0], room})
	}

	result := 0
	for i := 1; i < n; i++ {
		if usedCount[i] > usedCount[result] {
			result = i
		}
	}

	return result
}

// @lc code=end
