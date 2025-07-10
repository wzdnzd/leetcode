/*
 * @lc app=leetcode.cn id=3440 lang=golang
 *
 * [3440] 重新安排会议得到最多空余时间 II
 *
 * https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-ii/description/
 *
 * algorithms
 * Medium (48.74%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    2.5K
 * Total Submissions: 4.3K
 * Testcase Example:  '5\n[1,3]\n[2,5]'
 *
 * 给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。
 *
 * 同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i
 * 个会议的时间为 [startTime[i], endTime[i]] 。
 *
 * 你可以重新安排 至多 一个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长
 * 连续空余时间。
 *
 * 请你返回重新安排会议以后，可以得到的 最大 空余时间。
 *
 * 注意，会议 不能 安排到整个活动的时间以外，且会议之间需要保持互不重叠。
 *
 * 注意：重新安排会议以后，会议之间的顺序可以发生改变。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：eventTime = 5, startTime = [1,3], endTime = [2,5]
 *
 * 输出：2
 *
 * 解释：
 *
 *
 *
 * 将 [1, 2] 的会议安排到 [2, 3] ，得到空余时间 [0, 2] 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]
 *
 * 输出：7
 *
 * 解释：
 *
 *
 *
 * 将 [0, 1] 的会议安排到 [8, 9] ，得到空余时间 [0, 7] 。
 *
 *
 * 示例 3：
 *
 *
 * 输入：eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]
 *
 * 输出：6
 *
 * 解释：
 *
 *
 *
 * 将 [3, 4] 的会议安排到 [8, 9] ，得到空余时间 [1, 7] 。
 *
 *
 * 示例 4：
 *
 *
 * 输入：eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
 *
 * 输出：0
 *
 * 解释：
 *
 * 活动中的所有时间都被会议安排满了。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= eventTime <= 10^9
 * n == startTime.length == endTime.length
 * 2 <= n <= 10^5
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。
 *
 *
 */

// @lc code=start
package main

func maxFreeTime(eventTime int, startTime []int, endTime []int) int {
	meetingCount := len(startTime)
	canRemoveMeeting := make([]bool, meetingCount)
	maxLeftGap, maxRightGap := 0, 0

	for i := 0; i < meetingCount; i++ {
		meetingDuration := endTime[i] - startTime[i]

		if meetingDuration <= maxLeftGap {
			canRemoveMeeting[i] = true
		}
		if i == 0 {
			maxLeftGap = max(maxLeftGap, startTime[i])
		} else {
			maxLeftGap = max(maxLeftGap, startTime[i]-endTime[i-1])
		}

		rightIndex := meetingCount - i - 1
		rightMeetingDuration := endTime[rightIndex] - startTime[rightIndex]
		if rightMeetingDuration <= maxRightGap {
			canRemoveMeeting[rightIndex] = true
		}

		if i == 0 {
			maxRightGap = max(maxRightGap, eventTime-endTime[meetingCount-1])
		} else {
			maxRightGap = max(maxRightGap, startTime[meetingCount-i]-endTime[meetingCount-i-1])
		}
	}

	maxFreeTimeResult := 0
	for i := 0; i < meetingCount; i++ {
		left := 0
		if i != 0 {
			left = endTime[i-1]
		}

		right := eventTime
		if i != meetingCount-1 {
			right = startTime[i+1]
		}

		if canRemoveMeeting[i] {
			maxFreeTimeResult = max(maxFreeTimeResult, right-left)
		} else {
			meetingDuration := endTime[i] - startTime[i]
			maxFreeTimeResult = max(maxFreeTimeResult, right-left-meetingDuration)
		}
	}

	return maxFreeTimeResult
}

// @lc code=end
