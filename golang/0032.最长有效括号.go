#
# @lc app=leetcode.cn id=32 lang=go
#
# [32] 最长有效括号
#
# https://leetcode-cn.com/problems/longest-valid-parentheses/description/
#
# algorithms
# Hard (27.86%)
# Likes:    281
# Dislikes: 0
# Total Accepted:    15.6K
# Total Submissions: 56K
# Testcase Example:  '"(()"'
#
# 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
#
# 示例 1:
#
# 输入: "(()"
# 输出: 2
# 解释: 最长有效括号子串为 "()"
#
#
# 示例 2:
#
# 输入: ")()())"
# 输出: 4
# 解释: 最长有效括号子串为 "()()"
#
#
#

package main

func longestValidParentheses(s string) int {
    maxAns := 0
    dp := make([]int, len(s))

    for i := 1; i < len(s); i++ {
        if s[i] == ')' {
            if s[i-1] == '(' {
                if i >= 2 {
                    dp[i] = dp[i - 2] + 2
                } else {
                    dp[i] = 2
                }
            } else if i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(' {
                if i - dp[i - 1] >= 2 {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2
                } else {
                    dp[i] = dp[i - 1] + 2
                }
            }

            maxAns = max(maxAns, dp[i])
        }
    }

    return maxAns
}

func max(x, y int) int {
    if x > y {
        return x
    }

    return y
}
