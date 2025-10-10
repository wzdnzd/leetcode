/*
 * @lc app=leetcode.cn id=3147 lang=golang
 *
 * [3147] 从魔法师身上吸取的最大能量
 *
 * https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon/description/
 *
 * algorithms
 * Medium (46.76%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    8.2K
 * Total Submissions: 16.1K
 * Testcase Example:  '[5,2,-10,-5,1]\n3'
 *
 * 在神秘的地牢中，n 个魔法师站成一排。每个魔法师都拥有一个属性，这个属性可以给你提供能量。有些魔法师可能会给你负能量，即从你身上吸取能量。
 *
 * 你被施加了一种诅咒，当你从魔法师 i 处吸收能量后，你将被立即传送到魔法师 (i + k) 处。这一过程将重复进行，直到你到达一个不存在 (i + k)
 * 的魔法师为止。
 *
 * 换句话说，你将选择一个起点，然后以 k 为间隔跳跃，直到到达魔法师序列的末端，在过程中吸收所有的能量。
 *
 * 给定一个数组 energy 和一个整数k，返回你能获得的 最大 能量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： energy = [5,2,-10,-5,1], k = 3
 *
 * 输出： 3
 *
 * 解释：可以从魔法师 1 开始，吸收能量 2 + 1 = 3。
 *
 *
 * 示例 2：
 *
 *
 * 输入： energy = [-2,-3,-1], k = 2
 *
 * 输出： -1
 *
 * 解释：可以从魔法师 2 开始，吸收能量 -1。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= energy.length <= 10^5
 * -1000 <= energy[i] <= 1000
 * 1 <= k <= energy.length - 1
 *
 *
 *
 *
 */

// @lc code=start
package main

import "math"

func maximumEnergy(energy []int, k int) int {
	n, result := len(energy), math.MinInt

	for i := n - k; i < n; i++ {
		total := 0
		for j := i; j >= 0; j -= k {
			total += energy[j]
			result = max(result, total)
		}
	}

	return result
}

// @lc code=end
