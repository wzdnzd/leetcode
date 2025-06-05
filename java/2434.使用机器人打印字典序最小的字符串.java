/*
 * @lc app=leetcode.cn id=2434 lang=java
 *
 * [2434] 使用机器人打印字典序最小的字符串
 *
 * https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string/description/
 *
 * algorithms
 * Medium (44.88%)
 * Likes:    73
 * Dislikes: 0
 * Total Accepted:    10K
 * Total Submissions: 21.2K
 * Testcase Example:  '"zza"'
 *
 * 给你一个字符串 s 和一个机器人，机器人当前有一个空字符串 t 。执行以下操作之一，直到 s 和 t 都变成空字符串：
 * 
 * 
 * 删除字符串 s 的 第一个 字符，并将该字符给机器人。机器人把这个字符添加到 t 的尾部。
 * 删除字符串 t 的 最后一个 字符，并将该字符给机器人。机器人将该字符写到纸上。
 * 
 * 
 * 请你返回纸上能写出的字典序最小的字符串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "zza"
 * 输出："azz"
 * 解释：用 p 表示写出来的字符串。
 * 一开始，p="" ，s="zza" ，t="" 。
 * 执行第一个操作三次，得到 p="" ，s="" ，t="zza" 。
 * 执行第二个操作三次，得到 p="azz" ，s="" ，t="" 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "bac"
 * 输出："abc"
 * 解释：用 p 表示写出来的字符串。
 * 执行第一个操作两次，得到 p="" ，s="c" ，t="ba" 。
 * 执行第二个操作两次，得到 p="ab" ，s="c" ，t="" 。
 * 执行第一个操作，得到 p="ab" ，s="" ，t="c" 。
 * 执行第二个操作，得到 p="abc" ，s="" ，t="" 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：s = "bdda"
 * 输出："addb"
 * 解释：用 p 表示写出来的字符串。
 * 一开始，p="" ，s="bdda" ，t="" 。
 * 执行第一个操作四次，得到 p="" ，s="" ，t="bdda" 。
 * 执行第二个操作四次，得到 p="addb" ，s="" ，t="" 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String robotWithString(String s) {
        StringBuffer sb = new StringBuffer();
        Map<Character, Integer> map = new HashMap<>();

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        char minChar = 'a';
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0)
                map.remove(c);

            stack.push(c);
            while (minChar < 'z' && !map.containsKey(minChar))
                minChar++;

            while (!stack.isEmpty() && stack.peek() <= minChar)
                sb.append(stack.pop());

        }
        
        return sb.toString();
    }
}
// @lc code=end

