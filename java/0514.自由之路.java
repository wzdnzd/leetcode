import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=514 lang=java
 *
 * [514] 自由之路
 *
 * https://leetcode.cn/problems/freedom-trail/description/
 *
 * algorithms
 * Hard (50.93%)
 * Likes:    250
 * Dislikes: 0
 * Total Accepted:    25.1K
 * Total Submissions: 49.2K
 * Testcase Example:  '"godding"\n"gd"'
 *
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 
 * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 
 * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00
 * 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * 
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 
 * 
 * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00
 * 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key
 * 的下一个字符（下一阶段）, 直至完成所有拼写。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 
 * 
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * ⁠对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
 * ⁠对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * ⁠当然, 我们还需要1步进行拼写。
 * ⁠因此最终的输出是 4。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: ring = "godding", key = "godding"
 * 输出: 13
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= ring.length, key.length <= 100
 * ring 和 key 只包含小写英文字母
 * 保证 字符串 key 一定可以由字符串  ring 旋转拼出
 * 
 * 
 */

// @lc code=start
class Solution {
    // @SuppressWarnings("unchecked")
    // public int findRotateSteps(String ring, String key) {
    // if ("".equals(key))
    // return 0;

    // int m = key.length(), n = ring.length();
    // List<Integer>[] pos = new List[26];
    // for (int i = 0; i < 26; i++) {
    // pos[i] = new ArrayList<>();
    // }

    // for (int i = 0; i < n; i++) {
    // pos[ring.charAt(i) - 'a'].add(i);
    // }

    // int[][] dp = new int[m][n];
    // for (int[] array : dp) {
    // Arrays.fill(array, Integer.MAX_VALUE);
    // }

    // for (int i = 0; i < m; i++) {
    // for (int j : pos[key.charAt(i) - 'a']) {
    // if (i == 0) {
    // dp[i][j] = Math.min(dp[i][j], calculate(n, 0, j) + 1);
    // continue;
    // }

    // for (int k : pos[key.charAt(i - 1) - 'a']) {
    // dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + calculate(n, k, j) + 1);
    // }
    // }
    // }

    // return Arrays.stream(dp[m - 1]).min().getAsInt();
    // }

    // private int calculate(int n, int x, int y) {
    // return Math.min((n + x - y) % n, (n + y - x) % n);
    // }

    public int findRotateSteps(String ring, String key) {
        if ("".equals(key))
            return 0;

        char[] ringChars = ring.toCharArray();
        char[] keyChars = key.toCharArray();
        int[][] status = new int[ring.length()][key.length()];

        return findRotateStepsHelper(ringChars, keyChars, 0, 0, status);
    }

    private int findRotateStepsHelper(char[] ring, char[] key,
            int ringPos, int keyPos, int[][] status) {
        if (status[ringPos][keyPos] != 0)
            return status[ringPos][keyPos];
        int positive = ringPos, negtive = ringPos, positiveStep = 1, negtiveStep = 1;
        while (ring[positive] != key[keyPos]) {
            positiveStep++;
            if (positive == ring.length - 1)
                positive = 0;
            else
                positive++;
        }

        while (ring[negtive] != key[keyPos]) {
            negtiveStep++;
            if (negtive == 0)
                negtive = ring.length - 1;
            else
                negtive--;
        }

        if (keyPos == key.length - 1) {
            status[ringPos][keyPos] = positiveStep < negtiveStep ? positiveStep : negtiveStep;
            return status[ringPos][keyPos];
        }

        positiveStep += findRotateStepsHelper(ring, key, positive, keyPos + 1, status);
        negtiveStep += findRotateStepsHelper(ring, key, negtive, keyPos + 1, status);
        status[ringPos][keyPos] = positiveStep < negtiveStep ? positiveStep : negtiveStep;
        return status[ringPos][keyPos];
    }
}
// @lc code=end
