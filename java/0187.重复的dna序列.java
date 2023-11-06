/*
 * @lc app=leetcode.cn id=187 lang=java
 *
 * [187] 重复的DNA序列
 *
 * https://leetcode.cn/problems/repeated-dna-sequences/description/
 *
 * algorithms
 * Medium (53.51%)
 * Likes:    534
 * Dislikes: 0
 * Total Accepted:    153.4K
 * Total Submissions: 283K
 * Testcase Example:  '"AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"'
 *
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * 
 * 
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 
 * 
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序
 * 返回答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= s.length <= 10^5
 * s[i]=='A'、'C'、'G' or 'T'
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>(), record = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String text = s.substring(i, i + 10);
            if (seen.contains(text))
                record.add(text);
            else
                seen.add(text);
        }

        return new ArrayList<>(record);
    }
}
// @lc code=end
