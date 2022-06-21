import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode.cn id=140 lang=java
 *
 * [140] 单词拆分 II
 *
 * https://leetcode.cn/problems/word-break-ii/description/
 *
 * algorithms
 * Hard (53.42%)
 * Likes:    604
 * Dislikes: 0
 * Total Accepted:    69.8K
 * Total Submissions: 130.7K
 * Testcase Example:  '"catsanddog"\n["cat","cats","and","sand","dog"]'
 *
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序
 * 返回所有这些可能的句子。
 * 
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入:s = "pineapplepenapple", wordDict =
 * ["apple","pen","applepen","pine","pineapple"]
 * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 解释: 注意你可以重复使用字典中的单词。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * 输出:[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> hs = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int r = 1; r <= s.length(); r++) {
            for (int l = 0; l < r; l++) {
                if (dp[l] && hs.contains(s.substring(l, r))) {
                    dp[r] = true;
                    break;
                }
            }
        }

        List<String> list = new ArrayList<>();
        if (dp[s.length()]) {
            Deque<String> stack = new ArrayDeque<>();
            dfs(s, s.length(), hs, dp, stack, list);
        }

        return list;
    }

    private void dfs(String s, int len, HashSet<String> wordDicts, boolean[] dp, Deque<String> path,
            List<String> array) {
        if (len == 0) {
            array.add(String.join(" ", path));
            return;
        }

        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (dp[i] && wordDicts.contains(suffix)) {
                path.addFirst(suffix);
                dfs(s, i, wordDicts, dp, path, array);
                path.removeFirst();
            }
        }
    }
}
// @lc code=end
