/*
 * @lc app=leetcode.cn id=75 lang=golang
 *
 * [75] 颜色分类
 *
 * https://leetcode.cn/problems/sort-colors/description/
 *
 * algorithms
 * Medium (62.17%)
 * Likes:    1927
 * Dislikes: 0
 * Total Accepted:    781.2K
 * Total Submissions: 1.2M
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *
 *
 *
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *
 *
 *
 * 提示：
 *
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 *
 *
 *
 * 进阶：
 *
 *
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *
 */

// @lc code=start
package main

func sortColors(nums []int) {
	p0, p1, p2 := 0, 0, len(nums)-1

	for p1 <= p2 {
		if nums[p1] == 0 {
			nums[p0], nums[p1] = nums[p1], nums[p0]
			p0++
			p1++
		} else if nums[p1] == 1 {
			p1++
		} else {
			nums[p1], nums[p2] = nums[p2], nums[p1]
			p2--
		}
	}
}

// @lc code=end
