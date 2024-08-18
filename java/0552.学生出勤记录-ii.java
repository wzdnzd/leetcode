/*
 * @lc app=leetcode.cn id=552 lang=java
 *
 * [552] 学生出勤记录 II
 *
 * https://leetcode.cn/problems/student-attendance-record-ii/description/
 *
 * algorithms
 * Hard (58.14%)
 * Likes:    321
 * Dislikes: 0
 * Total Accepted:    31.5K
 * Total Submissions: 53.8K
 * Testcase Example:  '2'
 *
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 
 * 
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * 
 * 
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 
 * 
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 10^9 +
 * 7 取余 的结果。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 2
 * 输出：8
 * 解释：
 * 有 8 种长度为 2 的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL" 
 * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：3
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 10101
 * 输出：183236316
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int MOD = 1000000007;
    private static final int SIZE = 6;

    public int checkRecord(int n) {
        int[][] m = {
                { 1, 1, 0, 1, 0, 0 },
                { 1, 0, 1, 1, 0, 0 },
                { 1, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 0 },
        };

        int count = 0;
        for (int x : pow(m, n)[0])
            count = (count + x) % MOD;

        return count;
    }

    private int[][] pow(int[][] a, int n) {
        int[][] result = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++)
            result[i][i] = 1;

        while (n > 0) {
            if ((n & 1) > 0)
                result = multiply(result, a);

            a = multiply(a, a);
            n >>= 1;
        }

        return result;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++)
                    c[i][j] = (int) ((c[i][j] + (long) a[i][k] * b[k][j]) % MOD);
            }
        }

        return c;
    }
}
// @lc code=end
