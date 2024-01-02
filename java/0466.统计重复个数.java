/*
 * @lc app=leetcode.cn id=466 lang=java
 *
 * [466] 统计重复个数
 *
 * https://leetcode.cn/problems/count-the-repetitions/description/
 *
 * algorithms
 * Hard (37.78%)
 * Likes:    205
 * Dislikes: 0
 * Total Accepted:    15.5K
 * Total Submissions: 39.8K
 * Testcase Example:  '"acb"\n4\n"ab"\n2'
 *
 * 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
 * 
 * 
 * 例如，str == ["abc", 3] =="abcabcabc" 。
 * 
 * 
 * 如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
 * 
 * 
 * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
 * 
 * 
 * 现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2,
 * n2] 。
 * 
 * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
 * 输出：2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s1 和 s2 由小写英文字母组成
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int m = s1.length(), n = s2.length();
        int[] indexer = new int[n + 1];
        int[] counter = new int[n + 1];
        int index = 0, count = 0;

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m; j++) {
                if (s1.charAt(j) == s2.charAt(index)) {
                    index++;
                    if (index == n) {
                        index = 0;
                        count++;
                    }
                }
            }

            counter[i] = count;
            indexer[i] = index;

            for (int k = 0; k < i; k++) {
                if (indexer[k] == index) {
                    int prevCount = counter[k];
                    int patternCount = ((n1 - 1 - k) / (i - k)) * (counter[i] - counter[k]);
                    int remainCount = counter[k + (n1 - 1 - k) % (i - k)] - counter[k];
                    return (prevCount + patternCount + remainCount) / n2;
                }
            }
        }

        return counter[n1 - 1] / n2;
    }
}
// @lc code=end
