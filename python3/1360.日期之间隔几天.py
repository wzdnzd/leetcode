#
# @lc app=leetcode.cn id=1360 lang=python3
#
# [1360] 日期之间隔几天
#
# https://leetcode.cn/problems/number-of-days-between-two-dates/description/
#
# algorithms
# Easy (51.16%)
# Likes:    57
# Dislikes: 0
# Total Accepted:    15.7K
# Total Submissions: 30.7K
# Testcase Example:  '"2019-06-29"\n"2019-06-30"'
#
# 请你编写一个程序来计算两个日期之间隔了多少天。
# 
# 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
# 
# 
# 
# 示例 1：
# 
# 输入：date1 = "2019-06-29", date2 = "2019-06-30"
# 输出：1
# 
# 
# 示例 2：
# 
# 输入：date1 = "2020-01-15", date2 = "2019-12-31"
# 输出：15
# 
# 
# 
# 
# 提示：
# 
# 
# 给定的日期是 1971 年到 2100 年之间的有效日期。
# 
# 
#

# @lc code=start
from datetime import datetime

class Solution:
    def daysBetweenDates(self, date1: str, date2: str) -> int:
        start = datetime.strptime(date1, "%Y-%m-%d")
        end = datetime.strptime(date2, "%Y-%m-%d")

        return abs((end - start).days)
# @lc code=end

