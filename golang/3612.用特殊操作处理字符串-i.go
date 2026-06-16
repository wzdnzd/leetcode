/*
 * @lc app=leetcode.cn id=3612 lang=golang
 *
 * [3612] 用特殊操作处理字符串 I
 *
 * https://leetcode.cn/problems/process-string-with-special-operations-i/description/
 *
 * algorithms
 * Medium (65.38%)
 * Likes:    10
 * Dislikes: 0
 * Total Accepted:    7K
 * Total Submissions: 10K
 * Testcase Example:  '"a#b%*"'
 *
 * 给你一个字符串 s，它由小写英文字母和特殊字符：*、# 和 % 组成。
 *
 * 请根据以下规则从左到右处理 s 中的字符，构造一个新的字符串 result：
 *
 *
 * 如果字符是 小写 英文字母，则将其添加到 result 中。
 * 字符 '*' 会 删除 result 中的最后一个字符（如果存在）。
 * 字符 '#' 会 复制 当前的 result 并 追加 到其自身后面。
 * 字符 '%' 会 反转 当前的 result。
 *
 *
 * 在处理完 s 中的所有字符后，返回最终的字符串 result。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入： s = "a#b%*"
 *
 * 输出： "ba"
 *
 * 解释：
 *
 *
 *
 *
 * i
 * s[i]
 * 操作
 * 当前 result
 *
 *
 *
 *
 * 0
 * 'a'
 * 添加 'a'
 * "a"
 *
 *
 * 1
 * '#'
 * 复制 result
 * "aa"
 *
 *
 * 2
 * 'b'
 * 添加 'b'
 * "aab"
 *
 *
 * 3
 * '%'
 * 反转 result
 * "baa"
 *
 *
 * 4
 * '*'
 * 删除最后一个字符
 * "ba"
 *
 *
 *
 *
 * 因此，最终的 result 是 "ba"。
 *
 *
 * 示例 2：
 *
 *
 * 输入： s = "z*#"
 *
 * 输出： ""
 *
 * 解释：
 *
 *
 *
 *
 * i
 * s[i]
 * 操作
 * 当前 result
 *
 *
 *
 *
 * 0
 * 'z'
 * 添加 'z'
 * "z"
 *
 *
 * 1
 * '*'
 * 删除最后一个字符
 * ""
 *
 *
 * 2
 * '#'
 * 复制字符串
 * ""
 *
 *
 *
 *
 * 因此，最终的 result 是 ""。
 *
 *
 *
 *
 * 提示:
 *
 *
 * 1 <= s.length <= 20
 * s 只包含小写英文字母和特殊字符 *、# 和 %。
 *
 *
 */

// @lc code=start
package main

import "slices"

func processStr(s string) string {
	chars := []byte{}

	for _, c := range s {
		if c == '*' {
			if len(chars) > 0 {
				chars = chars[:len(chars)-1]
			}
		} else if c == '#' {
			chars = append(chars, chars...)
		} else if c == '%' {
			slices.Reverse(chars)
		} else {
			chars = append(chars, byte(c))
		}
	}

	return string(chars)
}

// @lc code=end
