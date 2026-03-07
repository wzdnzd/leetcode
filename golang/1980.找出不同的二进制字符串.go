/*
 * @lc app=leetcode.cn id=1980 lang=golang
 *
 * [1980] 找出不同的二进制字符串
 *
 * https://leetcode.cn/problems/find-unique-binary-string/description/
 *
 * algorithms
 * Medium (62.66%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    11.6K
 * Total Submissions: 18.5K
 * Testcase Example:  '["01","10"]'
 *
 * 给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。请你找出并返回一个长度为 n 且 没有出现
 * 在 nums 中的二进制字符串。如果存在多种答案，只需返回 任意一个 即可。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = ["01","10"]
 * 输出："11"
 * 解释："11" 没有出现在 nums 中。"00" 也是正确答案。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = ["00","01"]
 * 输出："11"
 * 解释："11" 没有出现在 nums 中。"10" 也是正确答案。
 *
 *
 * 示例 3：
 *
 *
 * 输入：nums = ["111","011","001"]
 * 输出："101"
 * 解释："101" 没有出现在 nums 中。"000"、"010"、"100"、"110" 也是正确答案。
 *
 *
 *
 * 提示：
 *
 *
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] 为 '0' 或 '1'
 * nums 中的所有字符串 互不相同
 *
 *
 */

// @lc code=start
package main

import (
	"fmt"
	"strconv"
)

func findDifferentBinaryString(nums []string) string {
	n := len(nums)
	records := make(map[int]bool, n)

	for _, s := range nums {
		x, _ := strconv.ParseInt(s, 2, 64)
		records[int(x)] = true
	}

	count := 0
	for records[count] {
		count++
	}

	return fmt.Sprintf("%0*b", n, count)
}

// @lc code=end
