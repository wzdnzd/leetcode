/*
 * @lc app=leetcode.cn id=2916 lang=golang
 *
 * [2916] 子数组不同元素数目的平方和 II
 *
 * https://leetcode.cn/problems/subarrays-distinct-element-sum-of-squares-ii/description/
 *
 * algorithms
 * Hard (40.56%)
 * Likes:    24
 * Dislikes: 0
 * Total Accepted:    3.4K
 * Total Submissions: 7.4K
 * Testcase Example:  '[1,2,1]'
 *
 * 给你一个下标从 0 开始的整数数组 nums 。
 *
 * 定义 nums 一个子数组的 不同计数 值如下：
 *
 *
 * 令 nums[i..j] 表示 nums 中所有下标在 i 到 j 范围内的元素构成的子数组（满足 0 <= i <= j < nums.length
 * ），那么我们称子数组 nums[i..j] 中不同值的数目为 nums[i..j] 的不同计数。
 *
 *
 * 请你返回 nums 中所有子数组的 不同计数 的 平方 和。
 *
 * 由于答案可能会很大，请你将它对 10^9 + 7 取余 后返回。
 *
 * 子数组指的是一个数组里面一段连续 非空 的元素序列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [1,2,1]
 * 输出：15
 * 解释：六个子数组分别为：
 * [1]: 1 个互不相同的元素。
 * [2]: 1 个互不相同的元素。
 * [1]: 1 个互不相同的元素。
 * [1,2]: 2 个互不相同的元素。
 * [2,1]: 2 个互不相同的元素。
 * [1,2,1]: 2 个互不相同的元素。
 * 所有不同计数的平方和为 1^2 + 1^2 + 1^2 + 2^2 + 2^2 + 2^2 = 15 。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,2]
 * 输出：3
 * 解释：三个子数组分别为：
 * [2]: 1 个互不相同的元素。
 * [2]: 1 个互不相同的元素。
 * [2,2]: 1 个互不相同的元素。
 * 所有不同计数的平方和为 1^2 + 1^2 + 1^2 = 3 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 *
 */

// @lc code=start
package main

type lazySeg []struct{ sum, todo int }

func (t lazySeg) do(index, left, right, add int) {
	t[index].sum += add * (right - left + 1)
	t[index].todo += add
}

func (t lazySeg) queryAndAdd(index, left, right, start, end int) int {
	result := 0
	if start <= left && right <= end {
		result = t[index].sum
		t.do(index, left, right, 1)
		return result
	}

	m := (left + right) >> 1
	if add := t[index].todo; add != 0 {
		t.do(index<<1, left, m, add)
		t.do(index<<1|1, m+1, right, add)
		t[index].todo = 0
	}

	if start <= m {
		result = t.queryAndAdd(index<<1, left, m, start, end)
	}

	if m < end {
		result += t.queryAndAdd(index<<1|1, m+1, right, start, end)
	}

	t[index].sum = t[index<<1].sum + t[index<<1|1].sum
	return result
}

func sumCounts(nums []int) int {
	const mod = 1000000007

	last := map[int]int{}
	n, sum, result := len(nums), 0, 0
	t := make(lazySeg, n*4)

	for i, num := range nums {
		i++
		j := last[num]
		sum += t.queryAndAdd(1, 1, n, j+1, i)*2 + i - j
		result = (result + sum) % mod
		last[num] = i
	}

	return result
}

// @lc code=end
