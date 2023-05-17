#
# @lc app=leetcode.cn id=77 lang=python3
#
# [77] 组合
#
# https://leetcode.cn/problems/combinations/description/
#
# algorithms
# Medium (77.26%)
# Likes:    1379
# Dislikes: 0
# Total Accepted:    534.3K
# Total Submissions: 692.7K
# Testcase Example:  '4\n2'
#
# 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
#
# 你可以按 任何顺序 返回答案。
#
#
#
# 示例 1：
#
#
# 输入：n = 4, k = 2
# 输出：
# [
# ⁠ [2,4],
# ⁠ [3,4],
# ⁠ [2,3],
# ⁠ [1,2],
# ⁠ [1,3],
# ⁠ [1,4],
# ]
#
# 示例 2：
#
#
# 输入：n = 1, k = 1
# 输出：[[1]]
#
#
#
# 提示：
#
#
# 1
# 1
#
#
#


# @lc code=start
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        def dfs(index: int, path: List[int]) -> None:
            count = k - len(path)
            if count == 0:
                result.append(path.copy())
                return

            for i in range(index, count - 1, -1):
                path.append(i)
                dfs(i - 1, path)
                path.pop()

        result = []
        dfs(n, [])
        return result


# @lc code=end
