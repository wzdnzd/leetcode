/*
 * @lc app=leetcode.cn id=1061 lang=java
 *
 * [1061] 按字典序排列最小的等效字符串
 *
 * https://leetcode.cn/problems/lexicographically-smallest-equivalent-string/description/
 *
 * algorithms
 * Medium (66.94%)
 * Likes:    57
 * Dislikes: 0
 * Total Accepted:    9.7K
 * Total Submissions: 14K
 * Testcase Example:  '"parker"\n"morris"\n"parser"'
 *
 * 给出长度相同的两个字符串s1 和 s2 ，还有一个字符串 baseStr 。
 * 
 * 其中  s1[i] 和 s2[i]  是一组等价字符。
 * 
 * 
 * 举个例子，如果 s1 = "abc" 且 s2 = "cde"，那么就有 'a' == 'c', 'b' == 'd', 'c' == 'e'。
 * 
 * 
 * 等价字符遵循任何等价关系的一般规则：
 * 
 * 
 * 自反性 ：'a' == 'a'
 * 对称性 ：'a' == 'b' 则必定有 'b' == 'a'
 * 传递性 ：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'
 * 
 * 
 * 例如， s1 = "abc" 和 s2 = "cde" 的等价信息和之前的例子一样，那么 baseStr = "eed" , "acd" 或
 * "aab"，这三个字符串都是等价的，而 "aab" 是 baseStr 的按字典序最小的等价字符串
 * 
 * 利用 s1 和 s2 的等价信息，找出并返回 baseStr 的按字典序排列最小的等价字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s1 = "parker", s2 = "morris", baseStr = "parser"
 * 输出："makkek"
 * 解释：根据 A 和 B 中的等价信息，我们可以将这些字符分为 [m,p], [a,o], [k,r,s], [e,i] 共 4
 * 组。每组中的字符都是等价的，并按字典序排列。所以答案是 "makkek"。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s1 = "hello", s2 = "world", baseStr = "hold"
 * 输出："hdld"
 * 解释：根据 A 和 B 中的等价信息，我们可以将这些字符分为 [h,w], [d,e,o], [l,r] 共 3 组。所以只有 S 中的第二个字符
 * 'o' 变成 'd'，最后答案为 "hdld"。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
 * 输出："aauaaaaada"
 * 解释：我们可以把 A 和 B 中的等价字符分为 [a,o,e,r,s,c], [l,p], [g,t] 和 [d,m] 共 4 组，因此 S 中除了
 * 'u' 和 'd' 之外的所有字母都转化成了 'a'，最后答案为 "aauaaaaada"。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s1.length, s2.length, baseStr <= 1000
 * s1.length == s2.length
 * 字符串s1, s2, and baseStr 仅由从 'a' 到 'z' 的小写英文字母组成。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);
        int length = s1.length();

        for (int i = 0; i < length; i++)
            uf.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        
        StringBuffer sb = new StringBuffer();
        int baseLength = baseStr.length();

        for (int i = 0; i < baseLength; i++) {
            int index = baseStr.charAt(i) - 'a';
            int root = uf.find(index);
            sb.append((char) ('a' + root));
        }

        return sb.toString();
    }
}

class UnionFind {
    private int[] parents;

    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = i;
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        
        if (px != py) {
            if (px < py)
                parents[py] = px;
            else
                parents[px] = py;
        }
    }

    public int find(int x) {
        if (parents[x] != x)
            parents[x] = find(parents[x]);
        
        return parents[x];
    }
}
// @lc code=end

