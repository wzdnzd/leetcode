#
# @lc app=leetcode.cn id=195 lang=bash
#
# [195] 第十行
#
# https://leetcode-cn.com/problems/tenth-line/description/
#
# shell
# Easy (36.61%)
# Total Accepted:    5K
# Total Submissions: 13.2K
# Testcase Example:  'Line 1\\nLine 2\\nLine 3\\nLine 4\\nLine 5\\nLine 6\\nLine 7\\nLine 8\\nLine 9\\nLine 10'
#
# 给定一个文本文件 file.txt，请只打印这个文件中的第十行。
# 
# 示例:
# 
# 假设 file.txt 有如下内容：
# 
# Line 1
# Line 2
# Line 3
# Line 4
# Line 5
# Line 6
# Line 7
# Line 8
# Line 9
# Line 10
# 
# 
# 你的脚本应当显示第十行：
# 
# Line 10
# 
# 
# 说明:
# 1. 如果文件少于十行，你应当输出什么？
# 2. 至少有三种不同的解法，请尝试尽可能多的方法来解题。
# 
#
# Read from the file file.txt and output the tenth line to stdout.
awk 'NR == 10' file.txt

