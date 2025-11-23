/*
 * @lc app=leetcode.cn id=735 lang=golang
 *
 * [735] 小行星碰撞
 *
 * https://leetcode.cn/problems/asteroid-collision/description/
 *
 * algorithms
 * Medium (42.87%)
 * Likes:    562
 * Dislikes: 0
 * Total Accepted:    114.3K
 * Total Submissions: 266.4K
 * Testcase Example:  '[5,10,-5]'
 *
 * 给定一个整数数组 asteroids，表示在同一行的小行星。数组中小行星的索引表示它们在空间中的相对位置。
 *
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 *
 *
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 *
 * 示例 2：
 *
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 *
 * 示例 3：
 *
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 *
 * 示例 4：
 *
 *
 * 输入：asteroids = [3,5,-6,2,-1,4]
 * 输出：[-6,2,4]
 * 解释：小行星 -6 使小行星 3 和 5 爆炸，然后继续向左移动。在另一边，小行星 2 使小行星 -1 爆炸，然后继续向右移动，没有碰撞小行星
 * 4。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 *
 */

// @lc code=start
package main

func asteroidCollision(asteroids []int) []int {
	stack := make([]int, 0)
	for _, asteroid := range asteroids {
		for len(stack) > 0 && stack[len(stack)-1] > 0 && asteroid < 0 {
			num := stack[len(stack)-1]
			stack = stack[:len(stack)-1]

			x, y := abs(num), abs(asteroid)
			if x > y {
				asteroid = num
			} else if x == y {
				asteroid = 0
			}
		}

		if asteroid != 0 {
			stack = append(stack, asteroid)
		}
	}

	return stack
}

func abs(x int) int {
	if x < 0 {
		return -x
	}

	return x
}

// @lc code=end
