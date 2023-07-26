#
# @lc app=leetcode.cn id=2569 lang=python3
#
# [2569] 更新数组后处理求和查询
#
# https://leetcode.cn/problems/handling-sum-queries-after-update/description/
#
# algorithms
# Hard (39.90%)
# Likes:    26
# Dislikes: 0
# Total Accepted:    4.2K
# Total Submissions: 9.6K
# Testcase Example:  '[1,0,1]\n[0,0,0]\n[[1,1,1],[2,1,0],[3,0,0]]'
#
# 给你两个下标从 0 开始的数组 nums1 和 nums2 ，和一个二维数组 queries 表示一些操作。总共有 3 种类型的操作：
#
#
# 操作类型 1 为 queries[i] = [1, l, r] 。你需要将 nums1 从下标 l 到下标 r 的所有 0 反转成 1 或将 1 反转成
# 0 。l 和 r 下标都从 0 开始。
# 操作类型 2 为 queries[i] = [2, p, 0] 。对于 0 <= i < n 中的所有下标，令 nums2[i] = nums2[i] +
# nums1[i] * p 。
# 操作类型 3 为 queries[i] = [3, 0, 0] 。求 nums2 中所有元素的和。
#
#
# 请你返回一个数组，包含所有第三种操作类型的答案。
#
#
#
# 示例 1：
#
#
# 输入：nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
# 输出：[3]
# 解释：第一个操作后 nums1 变为 [1,1,1] 。第二个操作后，nums2 变成 [1,1,1] ，所以第三个操作的答案为 3 。所以返回 [3]
# 。
#
#
# 示例 2：
#
#
# 输入：nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
# 输出：[5]
# 解释：第一个操作后，nums2 保持不变为 [5] ，所以第二个操作的答案是 5 。所以返回 [5] 。
#
#
#
#
# 提示：
#
#
# 1 <= nums1.length,nums2.length <= 10^5
# nums1.length = nums2.length
# 1 <= queries.length <= 10^5
# queries[i].length = 3
# 0 <= l <= r <= nums1.length - 1
# 0 <= p <= 10^6
# 0 <= nums1[i] <= 1
# 0 <= nums2[i] <= 10^9
#
#
#


# @lc code=start
class Solution:
    def handleQuery(
        self, nums1: List[int], nums2: List[int], queries: List[List[int]]
    ) -> List[int]:
        seg_tree = SegTree(nums1)
        ans, total, n = [], sum(nums2), len(nums1)

        for op, x, y in queries:
            if op == 1:
                seg_tree.reverse_range(x, y)
            elif op == 2:
                total += seg_tree.sum_range(0, n - 1) * x
            elif op == 3:
                ans.append(total)

        return ans


class SegTree:
    def __init__(self, nums):
        n = len(nums)
        self.arr = [SegNode() for _ in range(n * 4 + 1)]
        self.build(1, 0, n - 1, nums)

    def sum_range(self, left, right):
        return self.query(1, left, right)

    def reverse_range(self, left, right):
        self.modify(1, left, right)

    def build(self, id, l, r, nums):
        arr = self.arr
        arr[id] = SegNode()
        arr[id].l = l
        arr[id].r = r
        arr[id].lazy = False
        if l == r:
            arr[id].sum = nums[l]
            return

        mid = (l + r) >> 1
        self.build(2 * id, l, mid, nums)
        self.build(2 * id + 1, mid + 1, r, nums)
        arr[id].sum = arr[2 * id].sum + arr[2 * id + 1].sum

    def pushdown(self, x):
        arr = self.arr
        if arr[x].lazy:
            arr[2 * x].lazy = not arr[2 * x].lazy
            arr[2 * x].sum = arr[2 * x].r - arr[2 * x].l + 1 - arr[2 * x].sum
            arr[2 * x + 1].lazy = not arr[2 * x + 1].lazy
            arr[2 * x + 1].sum = (
                arr[2 * x + 1].r - arr[2 * x + 1].l + 1 - arr[2 * x + 1].sum
            )
            arr[x].lazy = False

    def modify(self, id, l, r):
        arr = self.arr
        if arr[id].l >= l and arr[id].r <= r:
            arr[id].sum = (arr[id].r - arr[id].l + 1) - arr[id].sum
            arr[id].lazy = not arr[id].lazy
            return

        self.pushdown(id)

        if arr[2 * id].r >= l:
            self.modify(2 * id, l, r)
        if arr[2 * id + 1].l <= r:
            self.modify(2 * id + 1, l, r)
        arr[id].sum = arr[2 * id].sum + arr[2 * id + 1].sum

    def query(self, id, l, r):
        arr = self.arr
        if arr[id].l >= l and arr[id].r <= r:
            return arr[id].sum
        if arr[id].r < l or arr[id].l > r:
            return 0

        self.pushdown(id)

        res = 0
        if arr[2 * id].r >= l:
            res += self.query(2 * id, l, r)
        if arr[2 * id + 1].l <= r:
            res += self.query(2 * id + 1, l, r)
        return res


class SegNode:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.sum = 0
        self.lazy = False


# @lc code=end
