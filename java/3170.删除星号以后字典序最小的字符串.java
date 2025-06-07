/*
 * @lc app=leetcode.cn id=3170 lang=java
 *
 * [3170] 删除星号以后字典序最小的字符串
 *
 * https://leetcode.cn/problems/lexicographically-minimum-string-after-removing-stars/description/
 *
 * algorithms
 * Medium (40.75%)
 * Likes:    30
 * Dislikes: 0
 * Total Accepted:    10.8K
 * Total Submissions: 23.1K
 * Testcase Example:  '"aaba*"'
 *
 * 给你一个字符串 s 。它可能包含任意数量的 '*' 字符。你的任务是删除所有的 '*' 字符。
 * 
 * 当字符串还存在至少一个 '*' 字符时，你可以执行以下操作：
 * 
 * 
 * 删除最左边的 '*' 字符，同时删除该星号字符左边一个字典序 最小 的字符。如果有多个字典序最小的字符，你可以删除它们中的任意一个。
 * 
 * 
 * 请你返回删除所有 '*' 字符以后，剩余字符连接而成的 字典序最小 的字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "aaba*"
 * 
 * 输出："aab"
 * 
 * 解释：
 * 
 * 删除 '*' 号和它左边的其中一个 'a' 字符。如果我们选择删除 s[3] ，s 字典序最小。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "abc"
 * 
 * 输出："abc"
 * 
 * 解释：
 * 
 * 字符串中没有 '*' 字符。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 只含有小写英文字母和 '*' 字符。
 * 输入保证操作可以删除所有的 '*' 字符。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public String clearStars(String s) {
        Map<Character, Deque<Integer>> stacks = new HashMap<>();
        PriorityQueue<Character> pq = new PriorityQueue<>();

        int n = s.length();
        boolean[] removed = new boolean[n];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (Character.isLetter(c)) {
                stacks.putIfAbsent(c, new ArrayDeque<>());
                if (stacks.get(c).isEmpty())
                    pq.offer(c);

                stacks.get(c).push(i);
            } else {
                char smallest = pq.peek();
                int index = stacks.get(smallest).pop();

                if (stacks.get(smallest).isEmpty())
                    pq.poll();

                removed[index] = true;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!removed[i] && Character.isLetter(c))
                sb.append(c);

        }
        
        return sb.toString();
    }
}
// @lc code=end

