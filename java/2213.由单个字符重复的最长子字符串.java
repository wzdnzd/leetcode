/*
 * @lc app=leetcode.cn id=2213 lang=java
 *
 * [2213] 由单个字符重复的最长子字符串
 *
 * https://leetcode.cn/problems/longest-substring-of-one-repeating-character/description/
 *
 * algorithms
 * Hard (44.30%)
 * Likes:    47
 * Dislikes: 0
 * Total Accepted:    5.2K
 * Total Submissions: 11.2K
 * Testcase Example:  '"babacc"\n"bcb"\n[1,3,3]'
 *
 * 给你一个下标从 0 开始的字符串 s 。另给你一个下标从 0 开始、长度为 k 的字符串 queryCharacters ，一个下标从 0
 * 开始、长度也是 k 的整数 下标 数组 queryIndices ，这两个都用来描述 k 个查询。
 * 
 * 第 i 个查询会将 s 中位于下标 queryIndices[i] 的字符更新为 queryCharacters[i] 。
 * 
 * 返回一个长度为 k 的数组 lengths ，其中 lengths[i] 是在执行第 i 个查询 之后 s 中仅由 单个字符重复 组成的 最长子字符串
 * 的 长度 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
 * 输出：[3,3,4]
 * 解释：
 * - 第 1 次查询更新后 s = "bbbacc" 。由单个字符重复组成的最长子字符串是 "bbb" ，长度为 3 。
 * - 第 2 次查询更新后 s = "bbbccc" 。由单个字符重复组成的最长子字符串是 "bbb" 或 "ccc"，长度为 3 。
 * - 第 3 次查询更新后 s = "bbbbcc" 。由单个字符重复组成的最长子字符串是 "bbbb" ，长度为 4 。
 * 因此，返回 [3,3,4] 。
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
 * 输出：[2,3]
 * 解释：
 * - 第 1 次查询更新后 s = "abazz" 。由单个字符重复组成的最长子字符串是 "zz" ，长度为 2 。
 * - 第 2 次查询更新后 s = "aaazz" 。由单个字符重复组成的最长子字符串是 "aaa" ，长度为 3 。
 * 因此，返回 [2,3] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * k == queryCharacters.length == queryIndices.length
 * 1 <= k <= 10^5
 * queryCharacters 由小写英文字母组成
 * 0 <= queryIndices[i] < s.length
 * 
 * 
 */

// @lc code=start

import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        TreeSet<int[]> intervals = new TreeSet<>((a, b) -> a[0] - b[0]);
        TreeMap<Integer, Integer> lengthToCount = new TreeMap<>();

        char[] chars = s.toCharArray();
        int n = chars.length, k = queryCharacters.length();
        int[] lengths = new int[k];

        for (int i = 0, length = 0; i < n; i++) {
            length++;

            if (i == n - 1 || chars[i] != chars[i + 1]) {
                intervals.add(new int[] { i - length + 1, i });
                increase(lengthToCount, length);
                length = 0;
            }
        }

        for (int i = 0; i < k; i++) {
            int index = queryIndices[i];
            char prev = chars[index], curr = queryCharacters.charAt(i);

            if (curr != prev) {
                boolean prevLeftSame = index > 0 && prev == chars[index - 1];
                boolean prevRightSame = index < n - 1 && prev == chars[index + 1];
                int[] interval = intervals.floor(new int[] { index, index });

                if (prevLeftSame || prevRightSame) {
                    intervals.remove(interval);
                    int prevLength = interval[1] - interval[0] + 1;
                    decrease(lengthToCount, prevLength);
                    if (prevLeftSame) {
                        intervals.add(new int[] { interval[0], index - 1 });
                        int leftLength = index - interval[0];
                        increase(lengthToCount, leftLength);
                    }

                    if (prevRightSame) {
                        intervals.add(new int[] { index + 1, interval[1] });
                        int rightLength = interval[1] - index;
                        increase(lengthToCount, rightLength);
                    }

                    intervals.add(new int[] { index, index });
                    int midLength = 1;
                    increase(lengthToCount, midLength);
                }

                chars[index] = curr;
                boolean currLeftSame = index > 0 && curr == chars[index - 1];
                boolean currRightSame = index < n - 1 && curr == chars[index + 1];
                int[] leftInterval = intervals.floor(new int[] { index - 1, index - 1 });
                int[] midInterval = new int[] { index, index };
                int[] rightInterval = intervals.floor(new int[] { index + 1, index + 1 });

                if (currLeftSame || currRightSame) {
                    if (currLeftSame) {
                        intervals.remove(leftInterval);
                        int leftLength = index - leftInterval[0];
                        decrease(lengthToCount, leftLength);
                    }

                    if (currRightSame) {
                        intervals.remove(rightInterval);
                        int rightLength = rightInterval[1] - index;
                        decrease(lengthToCount, rightLength);
                    }

                    intervals.remove(midInterval);
                    int midLength = 1;
                    decrease(lengthToCount, midLength);

                    int start = currLeftSame ? leftInterval[0] : index;
                    int end = currRightSame ? rightInterval[1] : index;
                    intervals.add(new int[] { start, end });

                    int currLength = end - start + 1;
                    increase(lengthToCount, currLength);
                }
            }

            lengths[i] = lengthToCount.lastKey();
        }

        return lengths;
    }

    private void increase(TreeMap<Integer, Integer> lengthToCount, int length) {
        lengthToCount.put(length, lengthToCount.getOrDefault(length, 0) + 1);
    }

    private void decrease(TreeMap<Integer, Integer> lengthToCount, int length) {
        lengthToCount.put(length, lengthToCount.get(length) - 1);
        if (lengthToCount.get(length) == 0)
            lengthToCount.remove(length);
    }
}
// @lc code=end
