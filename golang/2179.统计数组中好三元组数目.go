/*
 * @lc app=leetcode.cn id=2179 lang=golang
 *
 * [2179] 统计数组中好三元组数目
 *
 * https://leetcode.cn/problems/count-good-triplets-in-an-array/description/
 *
 * algorithms
 * Hard (40.31%)
 * Likes:    61
 * Dislikes: 0
 * Total Accepted:    5.4K
 * Total Submissions: 12.1K
 * Testcase Example:  '[2,0,1,3]\n[0,1,2,3]'
 *
 * 给你两个下标从 0 开始且长度为 n 的整数数组 nums1 和 nums2 ，两者都是 [0, 1, ..., n - 1] 的 排列 。
 *
 * 好三元组 指的是 3 个 互不相同 的值，且它们在数组 nums1 和 nums2 中出现顺序保持一致。换句话说，如果我们将 pos1v 记为值 v 在
 * nums1 中出现的位置，pos2v 为值 v 在 nums2 中的位置，那么一个好三元组定义为 0 <= x, y, z <= n - 1 ，且
 * pos1x < pos1y < pos1z 和 pos2x < pos2y < pos2z 都成立的 (x, y, z) 。
 *
 * 请你返回好三元组的 总数目 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * 输出：1
 * 解释：
 * 总共有 4 个三元组 (x,y,z) 满足 pos1x < pos1y < pos1z ，分别是 (2,0,1) ，(2,0,3) ，(2,1,3) 和
 * (0,1,3) 。
 * 这些三元组中，只有 (0,1,3) 满足 pos2x < pos2y < pos2z 。所以只有 1 个好三元组。
 *
 *
 * 示例 2：
 *
 * 输入：nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * 输出：4
 * 解释：总共有 4 个好三元组 (4,0,3) ，(4,0,2) ，(4,1,3) 和 (4,1,2) 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == nums1.length == nums2.length
 * 3 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= n - 1
 * nums1 和 nums2 是 [0, 1, ..., n - 1] 的排列。
 *
 *
 */

// @lc code=start
package main

type fenwick []int

func newFenwickTree(n int) fenwick {
	return make(fenwick, n+1)
}

func (f fenwick) update(i int, val int) {
	for ; i < len(f); i += i & -i {
		f[i] += val
	}
}

func (f fenwick) prefixSum(i int) int {
	result := 0
	for ; i > 0; i &= i - 1 {
		result += f[i]
	}

	return result
}

func goodTriplets(nums1, nums2 []int) int64 {
	n := len(nums1)
	records := make([]int, n)

	for i, x := range nums1 {
		records[x] = i
	}

	count := int64(0)
	tree := newFenwickTree(n)

	for i, v := range nums2[:n-1] {
		y := records[v]
		less := tree.prefixSum(y)
		count += int64(less) * int64(n-1-y-(i-less))
		tree.update(y+1, 1)
	}

	return count
}

// @lc code=end
