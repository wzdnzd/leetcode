/*
 * @lc app=leetcode.cn id=3934 lang=golang
 *
 * [3934] 最短唯一子数组
 *
 * https://leetcode.cn/problems/smallest-unique-subarray/description/
 *
 * algorithms
 * Hard (54.75%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    2.5K
 * Total Submissions: 3.9K
 * Testcase Example:  '[3,3,3]'
 *
 * 给你一个整数数组 nums 。
 *
 * 找出 nums 中与其他任何 子数组 均 不 相同 的 子数组 的 最小 长度。
 *
 * 返回一个整数，表示此类 子数组 的 最小可能长度 。
 *
 * 子数组 是数组中的一个连续的非空元素序列。
 *
 * 如果两个 子数组 具有相同的长度，并且对应位置的元素也相同，则认为这两个 子数组 是相同的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： nums = [3,3,3]
 *
 * 输出： 3
 *
 * 解释：
 *
 *
 * 长度为 1 的子数组：[3] → 出现 3 次
 * 长度为 2 的子数组：[3, 3] → 出现 2 次
 * 长度为 3 的子数组：[3, 3, 3] → 出现 1 次
 *
 *
 * 子数组 [3, 3, 3] 是唯一的，因此最小唯一子数组的长度为 3。
 *
 *
 * 示例 2：
 *
 *
 * 输入： nums = [2,1,2,3,3]
 *
 * 输出： 1
 *
 * 解释：
 *
 * 长度为 1 的子数组：
 *
 *
 * [2] → 出现 2 次
 * [1] → 出现 1 次
 * [3] → 出现 2 次
 *
 * 子数组 [1] 是唯一的，因此最小唯一子数组的长度为 1。
 *
 * 示例 3：
 *
 *
 * 输入： nums = [1,1,2,2,1]
 *
 * 输出： 2
 *
 * 解释：
 *
 * 长度为 1 的子数组：
 *
 *
 * [1] → 出现 3 次
 * [2] → 出现 2 次
 *
 *
 * 长度为 2 的子数组：
 *
 *
 * [1, 1] → 出现 1 次
 * [1, 2] → 出现 1 次
 * [2, 2] → 出现 1 次
 * [2, 1] → 出现 1 次
 *
 * 至少有一个长度为 2 的子数组是唯一的，因此最小唯一子数组的长度为 2。
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

import (
	"index/suffixarray"
	"unsafe"
)

func smallestUniqueSubarray(nums []int) int {
	n := len(nums)

	encodedNums := make([]byte, 0, n*3)
	for _, num := range nums {
		encodedNums = append(encodedNums, byte(num>>16), byte(num>>8), byte(num))
	}

	type suffixArrayIndex struct {
		data        []byte
		suffixArray []int32
	}

	encodedSuffixArray := (*suffixArrayIndex)(unsafe.Pointer(suffixarray.New(encodedNums))).suffixArray

	suffixArray := make([]int32, 0, n)
	for _, encodedPosition := range encodedSuffixArray {
		if encodedPosition%3 == 0 {
			suffixArray = append(suffixArray, encodedPosition/3)
		}
	}

	suffixRank := make([]int, n)
	for rank, suffixStart := range suffixArray {
		suffixRank[int(suffixStart)] = rank
	}

	lcpArray := make([]int, n)
	lcpLength := 0

	for suffixStart, rank := range suffixRank {
		if lcpLength > 0 {
			lcpLength--
		}

		if rank > 0 {
			previousSuffixStart := int(suffixArray[rank-1])
			for suffixStart+lcpLength < n &&
				previousSuffixStart+lcpLength < n &&
				nums[suffixStart+lcpLength] == nums[previousSuffixStart+lcpLength] {
				lcpLength++
			}
		}

		lcpArray[rank] = lcpLength
	}

	minUniqueLength := n
	for rank, lcpLength := range lcpArray {
		uniqueLength := lcpLength + 1
		if rank < n-1 {
			uniqueLength = max(lcpLength, lcpArray[rank+1]) + 1
		}

		if uniqueLength <= n-int(suffixArray[rank]) {
			minUniqueLength = min(minUniqueLength, uniqueLength)
		}
	}

	return minUniqueLength
}

// @lc code=end
