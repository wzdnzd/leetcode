/*
 * @lc app=leetcode.cn id=1002 lang=java
 *
 * [1002] 查找共用字符
 *
 * https://leetcode.cn/problems/find-common-characters/description/
 *
 * algorithms
 * Easy (70.45%)
 * Likes:    351
 * Dislikes: 0
 * Total Accepted:    88.7K
 * Total Submissions: 126K
 * Testcase Example:  '["bella","label","roller"]'
 *
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序
 * 返回答案。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：words = ["bella","label","roller"]
 * 输出：["e","l","l"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：words = ["cool","lock","cook"]
 * 输出：["c","o"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 由小写英文字母组成
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> commonChars(String[] words) {
        int[] records = new int[26];
        Arrays.fill(records, Integer.MAX_VALUE);

        for (String word : words) {
            int[] freq = new int[26];
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                ++freq[c - 'a'];
            }

            for (int i = 0; i < 26; ++i)
                records[i] = Math.min(records[i], freq[i]);
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < records[i]; ++j)
                list.add(String.valueOf((char) (i + 'a')));
        }

        return list;
    }
}
// @lc code=end
