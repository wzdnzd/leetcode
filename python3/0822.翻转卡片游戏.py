#
# @lc app=leetcode.cn id=822 lang=python3
#
# [822] 翻转卡片游戏
#
# https://leetcode.cn/problems/card-flipping-game/description/
#
# algorithms
# Medium (51.63%)
# Likes:    44
# Dislikes: 0
# Total Accepted:    8.9K
# Total Submissions: 15.5K
# Testcase Example:  '[1,2,4,4,7]\n[1,3,4,1,3]'
#
# 在桌子上有 N 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。
#
# 我们可以先翻转任意张卡片，然后选择其中一张卡片。
#
# 如果选中的那张卡片背面的数字 X 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。
#
# 哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。
#
# 其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。
#
# 如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。
#
# 示例：
#
#
# 输入：fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
# 输出：2
# 解释：假设我们翻转第二张卡片，那么在正面的数变成了 [1,3,4,4,7] ， 背面的数变成了 [1,2,4,1,3]。
# 接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。
#
#
#
# 提示：
#
#
# 1 <= fronts.length == backs.length <= 1000
# 1 <= fronts[i] <= 2000
# 1 <= backs[i] <= 2000
#
#
#


# @lc code=start
class Solution:
    def flipgame(self, fronts: List[int], backs: List[int]) -> int:
        forbiden = {k for k, v in zip(fronts, backs) if k == v}
        ans, exists = float("inf"), False
        for num in fronts + backs:
            if num not in forbiden:
                ans = min(ans, num)
                exists = True

        return ans if exists else 0


# @lc code=end
