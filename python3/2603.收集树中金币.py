#
# @lc app=leetcode.cn id=2603 lang=python3
#
# [2603] 收集树中金币
#
# https://leetcode.cn/problems/collect-coins-in-a-tree/description/
#
# algorithms
# Hard (43.36%)
# Likes:    114
# Dislikes: 0
# Total Accepted:    9.7K
# Total Submissions: 16.8K
# Testcase Example:  '[1,0,0,0,0,1]\n[[0,1],[1,2],[2,3],[3,4],[4,5]]'
#
# 给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1 。给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中
# edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。再给你一个长度为 n 的数组 coins ，其中 coins[i]
# 可能为 0 也可能为 1 ，1 表示节点 i 处有一个金币。
#
# 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
#
#
# 收集距离当前节点距离为 2 以内的所有金币，或者
# 移动到树中一个相邻节点。
#
#
# 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
#
# 如果你多次经过一条边，每一次经过都会给答案加一。
#
#
#
# 示例 1：
#
#
#
# 输入：coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
# 输出：2
# 解释：从节点 2 出发，收集节点 0 处的金币，移动到节点 3 ，收集节点 5 处的金币，然后移动回节点 2 。
#
#
# 示例 2：
#
#
#
# 输入：coins = [0,0,0,1,1,0,0,1], edges =
# [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
# 输出：2
# 解释：从节点 0 出发，收集节点 4 和 3 处的金币，移动到节点 2 处，收集节点 7 处的金币，移动回节点 0 。
#
#
#
#
# 提示：
#
#
# n == coins.length
# 1 <= n <= 3 * 10^4
# 0 <= coins[i] <= 1
# edges.length == n - 1
# edges[i].length == 2
# 0 <= ai, bi < n
# ai != bi
# edges 表示一棵合法的树。
#
#
#


# @lc code=start
from collections import defaultdict, deque


class Solution:
    def collectTheCoins(self, coins: List[int], edges: List[List[int]]) -> int:
        n, graph = len(coins), defaultdict(list)
        degrees, ans = [0] * n, n

        for x, y in edges:
            graph[x].append(y)
            graph[y].append(x)
            degrees[x] += 1
            degrees[y] += 1

        queue = deque(i for i in range(n) if degrees[i] == 1 and coins[i] == 0)
        while queue:
            u = queue.popleft()
            degrees[u] -= 1
            ans -= 1
            for v in graph[u]:
                degrees[v] -= 1
                if degrees[v] == 1 and coins[v] == 0:
                    queue.append(v)

        for _ in range(2):
            queue = deque(i for i in range(n) if degrees[i] == 1)
            while queue:
                u = queue.popleft()
                degrees[u] -= 1
                ans -= 1
                for v in graph[u]:
                    degrees[v] -= 1

        return 0 if ans == 0 else (ans - 1) * 2


# @lc code=end
