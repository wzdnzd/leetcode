/*
 * @lc app=leetcode.cn id=1349 lang=java
 *
 * [1349] 参加考试的最大学生数
 *
 * https://leetcode.cn/problems/maximum-students-taking-exam/description/
 *
 * algorithms
 * Hard (54.76%)
 * Likes:    170
 * Dislikes: 0
 * Total Accepted:    7.3K
 * Total Submissions: 12.7K
 * Testcase Example:  '[["#",".","#","#",".","#"],[".","#","#","#","#","."],["#",".","#","#",".","#"]]'
 *
 * 给你一个 m * n 的矩阵 seats 表示教室中的座位分布。如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
 * 
 * 
 * 学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的同时参加考试且无法作弊的
 * 最大 学生人数。
 * 
 * 学生必须坐在状况良好的座位上。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：seats = [["#",".","#","#",".","#"],
 * [".","#","#","#","#","."],
 * ["#",".","#","#",".","#"]]
 * 输出：4
 * 解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：seats = [[".","#"],
 * ["#","#"],
 * ["#","."],
 * ["#","#"],
 * [".","#"]]
 * 输出：3
 * 解释：让所有学生坐在可用的座位上。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：seats = [["#",".",".",".","#"],
 * [".","#",".","#","."],
 * [".",".","#",".","."],
 * [".","#",".","#","."],
 * ["#",".",".",".","#"]]
 * 输出：10
 * 解释：让学生坐在第 1、3 和 5 列的可用座位上。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * seats 只包含字符 '.' 和'#'
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxStudents(char[][] seats) {
        /**
         * thought:
         * https://leetcode.cn/problems/maximum-students-taking-exam/solutions/90312/xiang-jie-ya-suo-zhuang-tai-dong-tai-gui-hua-jie-f/comments/1721079
         */
        int m = seats.length, n = seats[0].length;
        int[][] dp = new int[m + 1][1 << n];

        for (int i = 1; i <= m; i++) {
            int invalid = 0;
            for (int j = 0; j < n; j++)
                invalid = (invalid << 1) + (seats[i - 1][j] == '#' ? 1 : 0);

            for (int j = 0; j < (1 << n); j++) {
                int mask = j << 1;
                if ((j & invalid) != 0 || (j & mask) != 0) {
                    dp[i][j] = -1;
                    continue;
                }

                for (int k = 0; k < (1 << n); k++) {
                    if (dp[i - 1][k] == -1)
                        continue;

                    if ((j & (k << 1)) != 0 || (mask & k) != 0)
                        continue;

                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Integer.bitCount(j));
                }
            }
        }

        int count = 0;
        for (int i = 0; i < (1 << n); i++)
            count = Math.max(count, dp[m][i]);

        return count;
    }
}
// @lc code=end
