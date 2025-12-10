/*
 * @lc app=leetcode.cn id=3577 lang=golang
 *
 * [3577] 统计计算机解锁顺序排列数
 *
 * https://leetcode.cn/problems/count-the-number-of-computer-unlocking-permutations/description/
 *
 * algorithms
 * Medium (53.91%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    3.1K
 * Total Submissions: 5.2K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个长度为 n 的数组 complexity。
 *
 * 在房间里有 n 台 上锁的 计算机，这些计算机的编号为 0 到 n - 1，每台计算机都有一个 唯一 的密码。编号为 i 的计算机的密码复杂度为
 * complexity[i]。
 *
 * 编号为 0 的计算机密码已经 解锁 ，并作为根节点。其他所有计算机必须通过它或其他已经解锁的计算机来解锁，具体规则如下：
 *
 *
 * 可以使用编号为 j 的计算机的密码解锁编号为 i 的计算机，其中 j 是任何小于 i 的整数，且满足 complexity[j] <
 * complexity[i]（即 j < i 并且 complexity[j] < complexity[i]）。
 * 要解锁编号为 i 的计算机，你需要事先解锁一个编号为 j 的计算机，满足 j < i 并且 complexity[j] <
 * complexity[i]。
 *
 *
 * 求共有多少种 [0, 1, 2, ..., (n - 1)] 的排列方式，能够表示从编号为 0
 * 的计算机（唯一初始解锁的计算机）开始解锁所有计算机的有效顺序。
 *
 * 由于答案可能很大，返回结果需要对 10^9 + 7 取余数。
 *
 * 注意：编号为 0 的计算机的密码已解锁，而 不是 排列中第一个位置的计算机密码已解锁。
 *
 * 排列 是一个数组中所有元素的重新排列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： complexity = [1,2,3]
 *
 * 输出： 2
 *
 * 解释：
 *
 * 有效的排列有：
 *
 *
 * [0, 1, 2]
 *
 * 首先使用根密码解锁计算机 0。
 * 使用计算机 0 的密码解锁计算机 1，因为 complexity[0] < complexity[1]。
 * 使用计算机 1 的密码解锁计算机 2，因为 complexity[1] < complexity[2]。
 *
 *
 * [0, 2, 1]
 *
 * 首先使用根密码解锁计算机 0。
 * 使用计算机 0 的密码解锁计算机 2，因为 complexity[0] < complexity[2]。
 * 使用计算机 0 的密码解锁计算机 1，因为 complexity[0] < complexity[1]。
 *
 *
 *
 *
 *
 * 示例 2：
 *
 *
 * 输入： complexity = [3,3,3,4,4,4]
 *
 * 输出： 0
 *
 * 解释：
 *
 * 没有任何排列能够解锁所有计算机。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 2 <= complexity.length <= 10^5
 * 1 <= complexity[i] <= 10^9
 *
 *
 */

// @lc code=start
package main

func countPermutations(complexity []int) int {
	const mod = 1000000007
	cout := 1
	for i := 1; i < len(complexity); i++ {
		if complexity[i] <= complexity[0] {
			return 0
		}

		cout = cout * i % mod
	}

	return cout
}

// @lc code=end
