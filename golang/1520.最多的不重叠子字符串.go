/*
 * @lc app=leetcode.cn id=1520 lang=golang
 *
 * [1520] 最多的不重叠子字符串
 *
 * https://leetcode.cn/problems/maximum-number-of-non-overlapping-substrings/description/
 *
 * algorithms
 * Hard (38.68%)
 * Likes:    96
 * Dislikes: 0
 * Total Accepted:    5.5K
 * Total Submissions: 13.4K
 * Testcase Example:  '"adefaddaccc"'
 *
 * 给你一个只包含小写字母的字符串 s ，你需要找到 s 中最多数目的非空子字符串，满足如下条件：
 *
 *
 * 这些字符串之间互不重叠，也就是说对于任意两个子字符串 s[i..j] 和 s[x..y] ，要么 j < x 要么 i > y 。
 * 如果一个子字符串包含字符 char ，那么 s 中所有 char 字符都应该在这个子字符串中。
 *
 *
 * 请你找到满足上述条件的最多子字符串数目。如果有多个解法有相同的子字符串数目，请返回这些子字符串总长度最小的一个解。可以证明最小总长度解是唯一的。
 *
 * 请注意，你可以以 任意 顺序返回最优解的子字符串。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s = "adefaddaccc"
 * 输出：["e","f","ccc"]
 * 解释：下面为所有满足第二个条件的子字符串：
 * [
 * "adefaddaccc"
 * "adefadda",
 * "ef",
 * "e",
 * ⁠ "f",
 * "ccc",
 * ]
 * 如果我们选择第一个字符串，那么我们无法再选择其他任何字符串，所以答案为 1 。如果我们选择 "adefadda" ，剩下子字符串中我们只可以选择
 * "ccc" ，它是唯一不重叠的子字符串，所以答案为 2 。同时我们可以发现，选择 "ef" 不是最优的，因为它可以被拆分成 2
 * 个子字符串。所以最优解是选择 ["e","f","ccc"] ，答案为 3 。不存在别的相同数目子字符串解。
 *
 *
 * 示例 2：
 *
 *
 * 输入：s = "abbaccd"
 * 输出：["d","bb","cc"]
 * 解释：注意到解 ["d","abba","cc"] 答案也为 3 ，但它不是最优解，因为它的总长度更长。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 *
 *
 */

// @lc code=start
package main

import (
	"slices"
	"sort"
)

func maxNumOfSubstrings(s string) []string {
	positions := [26][]int{}
	for i, c := range s {
		c -= 'a'
		positions[c] = append(positions[c], i)
	}

	graph := [26][]int{}
	for i, position := range positions {
		if position == nil {
			continue
		}

		l, r := position[0], position[len(position)-1]
		for j, p := range positions {
			if j == i {
				continue
			}

			k := sort.SearchInts(p, l)
			if k < len(p) && p[k] <= r {
				graph[i] = append(graph[i], j)
			}
		}
	}

	visited := [26]bool{}
	var l, r int

	var dfs func(int)
	dfs = func(x int) {
		visited[x] = true
		p := positions[x]

		l = min(l, p[0])
		r = max(r, p[len(p)-1])

		for _, y := range graph[x] {
			if !visited[y] {
				dfs(y)
			}
		}
	}

	type pair struct{ l, r int }
	intervals := []pair{}

	for i, p := range positions {
		if p == nil {
			continue
		}

		visited = [26]bool{}
		l, r = len(s), 0
		dfs(i)

		intervals = append(intervals, pair{l, r})
	}

	slices.SortFunc(intervals, func(a, b pair) int { return a.r - b.r })
	results := make([]string, 0)
	record := -1

	for _, interval := range intervals {
		if interval.l > record {
			results = append(results, s[interval.l:interval.r+1])
			record = interval.r
		}
	}

	return results
}
