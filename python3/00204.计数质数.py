#
# @lc app=leetcode.cn id=204 lang=python3
#
# [204] 计数质数
#
# https://leetcode-cn.com/problems/count-primes/description/
#
# algorithms
# Easy (25.74%)
# Total Accepted:    15.5K
# Total Submissions: 57.2K
# Testcase Example:  '10'
#
# 统计所有小于非负整数 n 的质数的数量。
#
# 示例:
#
# 输入: 10
# 输出: 4
# 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
#
#
#


class Solution:
    def countPrimes(self, n: int) -> int:
        """
        https://leetcode-cn.com/problems/count-primes/comments/38186
        """
        if n < 3:
            return 0
        else:
            output = [1] * n
            output[0], output[1] = 0, 0
            for i in range(2, int(n**0.5)+1):
                if output[i] == 1:
                    output[i*i:n:i] = [0] * len(output[i*i:n:i])

        return sum(output)
