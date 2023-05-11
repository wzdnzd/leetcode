#
# @lc app=leetcode.cn id=57 lang=python3
#
# [57] 插入区间
#
# https://leetcode.cn/problems/insert-interval/description/
#
# algorithms
# Medium (41.88%)
# Likes:    704
# Dislikes: 0
# Total Accepted:    143.4K
# Total Submissions: 341.9K
# Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
#
# 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
# 
# 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
# 输出：[[1,5],[6,9]]
# 
# 
# 示例 2：
# 
# 
# 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
# 输出：[[1,2],[3,10],[12,16]]
# 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
# 
# 示例 3：
# 
# 
# 输入：intervals = [], newInterval = [5,7]
# 输出：[[5,7]]
# 
# 
# 示例 4：
# 
# 
# 输入：intervals = [[1,5]], newInterval = [2,3]
# 输出：[[1,5]]
# 
# 
# 示例 5：
# 
# 
# 输入：intervals = [[1,5]], newInterval = [2,7]
# 输出：[[1,7]]
# 
# 
# 
# 
# 提示：
# 
# 
# 0 
# intervals[i].length == 2
# 0 
# intervals 根据 intervals[i][0] 按 升序 排列
# newInterval.length == 2
# 0 
# 
# 
#

# @lc code=start
class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        if not intervals:
            return [newInterval]
        
        ans, appended = [], False
        for i in range(len(intervals)):
            interval = intervals[i]
            if interval[1] < newInterval[0]:
                ans.append(interval)
                continue
            elif newInterval[1] < interval[0]:
                ans.append(newInterval)
                appended = True
                ans.extend(intervals[i:])
                break

            first = min(newInterval[0], interval[0])
            second = max(newInterval[1], interval[1])
            newInterval = [first, second]

        if not appended:
            ans.append(newInterval)
            
        return ans
# @lc code=end

