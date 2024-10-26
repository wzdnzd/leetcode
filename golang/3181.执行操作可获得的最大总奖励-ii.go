/*
 * @lc app=leetcode.cn id=3181 lang=golang
 *
 * [3181] 执行操作可获得的最大总奖励 II
 *
 * https://leetcode.cn/problems/maximum-total-reward-using-operations-ii/description/
 *
 * algorithms
 * Hard (34.40%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    6K
 * Total Submissions: 14.8K
 * Testcase Example:  '[1,1,3,3]'
 *
 * 给你一个整数数组 rewardValues，长度为 n，代表奖励的值。
 *
 * 最初，你的总奖励 x 为 0，所有下标都是 未标记 的。你可以执行以下操作 任意次 ：
 *
 *
 * 从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
 * 如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x +
 * rewardValues[i]），并 标记 下标 i。
 *
 *
 * 以整数形式返回执行最优操作能够获得的 最大 总奖励。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：rewardValues = [1,1,3,3]
 *
 * 输出：4
 *
 * 解释：
 *
 * 依次标记下标 0 和 2，总奖励为 4，这是可获得的最大值。
 *
 *
 * 示例 2：
 *
 *
 * 输入：rewardValues = [1,6,4,3,2]
 *
 * 输出：11
 *
 * 解释：
 *
 * 依次标记下标 0、2 和 1。总奖励为 11，这是可获得的最大值。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= rewardValues.length <= 5 * 10^4
 * 1 <= rewardValues[i] <= 5 * 10^4
 *
 *
 */

// @lc code=start
package main

import (
	"math/big"
	"slices"
)

func maxTotalReward(rewardValues []int) int {
	maxReward := slices.Max(rewardValues)
	if slices.Contains(rewardValues, maxReward-1) {
		return maxReward*2 - 1
	}

	slices.Sort(rewardValues)
	rewardValues = slices.Compact(rewardValues)

	one := big.NewInt(1)
	dp := big.NewInt(1)
	cur := new(big.Int)

	for _, v := range rewardValues {
		mask := cur.Sub(cur.Lsh(one, uint(v)), one)
		dp.Or(dp, cur.Lsh(cur.And(dp, mask), uint(v)))
	}

	return dp.BitLen() - 1
}

// @lc code=end
