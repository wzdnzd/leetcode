#
# @lc app=leetcode.cn id=1773 lang=python3
#
# [1773] 统计匹配检索规则的物品数量
#
# https://leetcode.cn/problems/count-items-matching-a-rule/description/
#
# algorithms
# Easy (86.42%)
# Likes:    86
# Dislikes: 0
# Total Accepted:    50.5K
# Total Submissions: 58.4K
# Testcase Example:  '[["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]]\n' +
  '"color"\n' +
  '"silver"'
#
# 给你一个数组 items ，其中 items[i] = [typei, colori, namei] ，描述第 i 件物品的类型、颜色以及名称。
# 
# 另给你一条由两个字符串 ruleKey 和 ruleValue 表示的检索规则。
# 
# 如果第 i 件物品能满足下述条件之一，则认为该物品与给定的检索规则 匹配 ：
# 
# 
# ruleKey == "type" 且 ruleValue == typei 。
# ruleKey == "color" 且 ruleValue == colori 。
# ruleKey == "name" 且 ruleValue == namei 。
# 
# 
# 统计并返回 匹配检索规则的物品数量 。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：items =
# [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]],
# ruleKey = "color", ruleValue = "silver"
# 输出：1
# 解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
# 
# 
# 示例 2：
# 
# 
# 输入：items =
# [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]],
# ruleKey = "type", ruleValue = "phone"
# 输出：2
# 解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"]
# 。注意，["computer","silver","phone"] 未匹配检索规则。
# 
# 
# 
# 提示：
# 
# 
# 1 
# 1 i.length, colori.length, namei.length, ruleValue.length 
# ruleKey 等于 "type"、"color" 或 "name"
# 所有字符串仅由小写字母组成
# 
# 
#

# @lc code=start
class Solution:
    def countMatches(self, items: List[List[str]], ruleKey: str, ruleValue: str) -> int:
        record, count = {"type": 0, "color": 1, "name": 2}, 0
        for item in items:
            if item[record[ruleKey]] == ruleValue:
                count += 1
        
        return count
# @lc code=end

