#
# @lc app=leetcode.cn id=345 lang=python3
#
# [345] 反转字符串中的元音字母
#
# https://leetcode-cn.com/problems/reverse-vowels-of-a-string/description/
#
# algorithms
# Easy (45.82%)
# Total Accepted:    8.3K
# Total Submissions: 18K
# Testcase Example:  '"hello"'
#
# 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
#
# 示例 1:
#
# 输入: "hello"
# 输出: "holle"
#
#
# 示例 2:
#
# 输入: "leetcode"
# 输出: "leotcede"
#
# 说明:
# 元音字母不包含字母"y"。
#
#


class Solution:
    def reverseVowels(self, s: str) -> str:
        chars = list(s)
        i, j = 0, len(chars) - 1
        vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
        while i < j:
            if chars[i] in vowels:
                while not chars[j] in vowels:
                    j -= 1
                chars[i], chars[j] = chars[j], chars[i]
                j -= 1
            i += 1
        return ''.join(chars)
