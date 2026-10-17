/*
 * @lc app=leetcode.cn id=1569 lang=golang
 *
 * [1569] 将子数组重新排序得到同一个二叉搜索树的方案数
 *
 * https://leetcode.cn/problems/number-of-ways-to-reorder-array-to-get-same-bst/description/
 *
 * algorithms
 * Hard (50.94%)
 * Likes:    80
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 9.9K
 * Testcase Example:  '[2,1,3]'
 *
 * 给你一个数组 nums 表示 1 到 n 的一个排列。我们按照元素在 nums 中的顺序依次插入一个初始为空的二叉搜索树（BST）。请你统计将 nums
 * 重新排序后，统计满足如下条件的方案数：重排后得到的二叉搜索树与 nums 原本数字顺序得到的二叉搜索树相同。
 *
 *
 * 比方说，给你 nums = [2,1,3]，我们得到一棵 2 为根，1 为左孩子，3 为右孩子的树。数组 [2,3,1] 也能得到相同的 BST，但
 * [3,2,1] 会得到一棵不同的 BST 。
 *
 *
 * 请你返回重排 nums 后，与原数组 nums 得到相同二叉搜索树的方案数。
 *
 * 由于答案可能会很大，请将结果对 10^9 + 7 取余数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：nums = [2,1,3]
 * 输出：1
 * 解释：我们将 nums 重排， [2,3,1] 能得到相同的 BST 。没有其他得到相同 BST 的方案了。
 *
 *
 * 示例 2：
 *
 *
 *
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：5
 * 解释：下面 5 个数组会得到相同的 BST：
 * [3,1,2,4,5]
 * [3,1,4,2,5]
 * [3,1,4,5,2]
 * [3,4,1,2,5]
 * [3,4,1,5,2]
 *
 *
 * 示例 3：
 *
 *
 *
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：没有别的排列顺序能得到相同的 BST 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= nums.length
 * nums 中所有数 互不相同 。
 *
 *
 */

// @lc code=start
package main

const MOD = 1000000007

func numOfWays(nums []int) int {
	n := len(nums)

	prev := make([]int, n+2)
	next := make([]int, n+2)
	size := make([]int, n+2)

	for i := range prev {
		prev[i] = i - 1
		next[i] = i + 1
	}
	fac, prod := 1, 1

	for i := n - 1; i >= 0; i-- {
		fac = fac * (i + 1) % MOD

		x := nums[i]
		size[x] = 1

		left := prev[x]
		if size[left] > 0 {
			size[x] += size[left]
			pl := prev[left]
			prev[x] = pl
			next[pl] = x
		}

		right := next[x]
		if size[right] > 0 {
			size[x] += size[right]
			nr := next[right]
			prev[nr] = x
			next[x] = nr
		}

		prod = prod * size[x] % MOD
	}

	return (fac*pow(prod, MOD-2) - 1 + MOD) % MOD
}

func pow(x, n int) int {
	result := 1
	for ; n > 0; n /= 2 {
		if n%2 > 0 {
			result = result * x % MOD
		}

		x = x * x % MOD
	}

	return result
}

// @lc code=end
