#
# @lc app=leetcode.cn id=2742 lang=python3
#
# [2742] 给墙壁刷油漆
#
# https://leetcode.cn/problems/painting-the-walls/description/
#
# algorithms
# Hard (34.90%)
# Likes:    25
# Dislikes: 0
# Total Accepted:    2.6K
# Total Submissions: 7.4K
# Testcase Example:  '[1,2,3,2]\n[1,2,3,2]'
#
# 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。你有两名油漆匠：
#
#
# 一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
# 一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
#
#
# 请你返回刷完 n 堵墙最少开销为多少。
#
#
#
# 示例 1：
#
# 输入：cost = [1,2,3,2], time = [1,2,3,2]
# 输出：3
# 解释：下标为 0 和 1 的墙由付费油漆匠来刷，需要 3 单位时间。同时，免费油漆匠刷下标为 2 和 3 的墙，需要 2 单位时间，开销为 0 。总开销为
# 1 + 2 = 3 。
#
#
# 示例 2：
#
# 输入：cost = [2,3,4,2], time = [1,1,1,1]
# 输出：4
# 解释：下标为 0 和 3 的墙由付费油漆匠来刷，需要 2 单位时间。同时，免费油漆匠刷下标为 1 和 2 的墙，需要 2 单位时间，开销为 0 。总开销为
# 2 + 2 = 4 。
#
#
#
#
# 提示：
#
#
# 1 <= cost.length <= 500
# cost.length == time.length
# 1 <= cost[i] <= 10^6
# 1 <= time[i] <= 500
#
#
#


# @lc code=start
class Solution:
    def paintWalls(self, cost: List[int], time: List[int]) -> int:
        n = len(cost)
        dp = [float("inf")] * (n + 1)
        dp[0] = 0

        for i in range(1, n + 1):
            for j in range(n, -1, -1):
                dp[j] = min(dp[j], dp[max(0, j - time[i - 1] - 1)] + cost[i - 1])

        return dp[n]


# @lc code=end
