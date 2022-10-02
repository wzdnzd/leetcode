import java.util.Arrays;
import java.util.Comparator;

/*
 * @lc app=leetcode.cn id=354 lang=java
 *
 * [354] 俄罗斯套娃信封问题
 *
 * https://leetcode.cn/problems/russian-doll-envelopes/description/
 *
 * algorithms
 * Hard (40.23%)
 * Likes:    812
 * Dislikes: 0
 * Total Accepted:    93.3K
 * Total Submissions: 234.2K
 * Testcase Example:  '[[5,4],[6,4],[6,7],[2,3]]'
 *
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 
 * 注意：不允许旋转信封。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 
 * 示例 2：
 * 
 * 
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= envelopes.length <= 10^5
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int length = envelopes.length;
        if (length < 2)
            return length;

        // 按照宽度w升序排序，若宽度相同，则按照高度h降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int value = o1[0] - o2[0];
                if (value == 0)
                    value = o2[1] - o1[1];
                return value;
            }
        });

        // int[] dp = new int[length];
        // Arrays.fill(dp, 1);
        // for (int i = 1; i < length; i++) {
        // for (int j = 0; j < i; j++) {
        // if (envelopes[j][1] < envelopes[i][1])
        // dp[i] = Math.max(dp[i], 1 + dp[j]);
        // }
        // }

        // int ans = Integer.MIN_VALUE;
        // for (int i = 0; i < length; i++)
        // ans = Math.max(ans, dp[i]);
        // return ans;

        int[] array = new int[length];
        int piles = 0;
        for (int i = 0; i < length; i++) {
            int target = envelopes[i][1];
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (array[mid] >= target)
                    right = mid;
                else
                    left = mid + 1;
            }

            if (left == piles)
                piles++;
            array[left] = target;
        }

        return piles;
    }
}
// @lc code=end
