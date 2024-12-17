/*
 * @lc app=leetcode.cn id=3291 lang=java
 *
 * [3291] 形成目标字符串需要的最少字符串数 I
 *
 * https://leetcode.cn/problems/minimum-number-of-valid-strings-to-form-target-i/description/
 *
 * algorithms
 * Medium (32.71%)
 * Likes:    26
 * Dislikes: 0
 * Total Accepted:    4.5K
 * Total Submissions: 14K
 * Testcase Example:  '["abc","aaaaa","bcdef"]\n"aabcdabc"'
 *
 * 给你一个字符串数组 words 和一个字符串 target。
 * 
 * 如果字符串 x 是 words 中 任意 字符串的 前缀，则认为 x 是一个 有效 字符串。
 * 
 * 现计划通过 连接 有效字符串形成 target ，请你计算并返回需要连接的 最少 字符串数量。如果无法通过这种方式形成 target，则返回
 * -1。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
 * 
 * 输出： 3
 * 
 * 解释：
 * 
 * target 字符串可以通过连接以下有效字符串形成：
 * 
 * 
 * words[1] 的长度为 2 的前缀，即 "aa"。
 * words[2] 的长度为 3 的前缀，即 "bcd"。
 * words[0] 的长度为 3 的前缀，即 "abc"。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： words = ["abababab","ab"], target = "ababaababa"
 * 
 * 输出： 2
 * 
 * 解释：
 * 
 * target 字符串可以通过连接以下有效字符串形成：
 * 
 * 
 * words[0] 的长度为 5 的前缀，即 "ababa"。
 * words[0] 的长度为 5 的前缀，即 "ababa"。
 * 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入： words = ["abcdef"], target = "xyz"
 * 
 * 输出： -1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 5 * 10^3
 * 输入确保 sum(words[i].length) <= 10^5。
 * words[i] 只包含小写英文字母。
 * 1 <= target.length <= 5 * 10^3
 * target 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

class Solution {
    private static final int MOD = 1_070_777_777;

    public int minValidStrings(String[] words, String target) {
        char[] chars = target.toCharArray();
        int n = chars.length;

        final int BASE = (int) 8e8 + new Random().nextInt((int) 1e8);
        int[] powBase = new int[n + 1];
        int[] preHash = new int[n + 1];

        powBase[0] = 1;
        for (int i = 0; i < n; i++) {
            powBase[i + 1] = (int) ((long) powBase[i] * BASE % MOD);
            preHash[i + 1] = (int) (((long) preHash[i] * BASE + chars[i]) % MOD);
        }

        int maxLen = 0;
        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
        }

        List<Set<Integer>> sets = new ArrayList<>(maxLen);
        for (int i = 0; i < maxLen; i++)
            sets.add(new HashSet<>());

        for (String word : words) {
            long code = 0;
            for (int j = 0; j < word.length(); j++) {
                code = (code * BASE + word.charAt(j)) % MOD;
                sets.get(j).add((int) code);
            }
        }

        int count = 0, currentR = 0, nextR = 0;
        for (int i = 0; i < n; i++) {
            int maxJump = calcMaxJump(i, preHash, powBase, sets);
            nextR = Math.max(nextR, i + maxJump);
            if (i == currentR) {
                if (i == nextR)
                    return -1;

                currentR = nextR;
                count++;
            }
        }

        return count;
    }

    private int calcMaxJump(int i, int[] preHash, int[] powBase, List<Set<Integer>> sets) {
        int left = 0;
        int right = Math.min(preHash.length - 1 - i, sets.size()) + 1;

        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            long subHash = (((long) preHash[i + mid] - (long) preHash[i] * powBase[mid]) % MOD + MOD) % MOD;

            if (sets.get(mid - 1).contains((int) subHash))
                left = mid;
            else
                right = mid;
        }

        return left;
    }
}
// @lc code=end
