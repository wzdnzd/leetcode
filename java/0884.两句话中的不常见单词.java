/*
 * @lc app=leetcode.cn id=884 lang=java
 *
 * [884] 两句话中的不常见单词
 *
 * https://leetcode.cn/problems/uncommon-words-from-two-sentences/description/
 *
 * algorithms
 * Easy (71.23%)
 * Likes:    191
 * Dislikes: 0
 * Total Accepted:    51.9K
 * Total Submissions: 72.8K
 * Testcase Example:  '"this apple is sweet"\n"this apple is sour"'
 *
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * 
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * 
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
 * 输出：["sweet","sour"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s1 = "apple apple", s2 = "banana"
 * 输出：["banana"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s1.length, s2.length <= 200
 * s1 和 s2 由小写英文字母和空格组成
 * s1 和 s2 都不含前导或尾随空格
 * s1 和 s2 中的所有单词间均由单个空格分隔
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        String[] l1 = s1.split(" ");
        for (String s : l1)
            map.put(s, map.getOrDefault(s, 0) + 1);

        String[] l2 = s2.split(" ");
        for (String s : l2)
            map.put(s, map.getOrDefault(s, 0) + 1);

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet())
            if (entry.getValue() == 1)
                list.add(entry.getKey());

        return list.toArray(new String[0]);
    }
}
// @lc code=end
