/*
 * @lc app=leetcode.cn id=3027 lang=java
 *
 * [3027] 人员站位的方案数 II
 *
 * https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-ii/description/
 *
 * algorithms
 * Hard (43.71%)
 * Likes:    14
 * Dislikes: 0
 * Total Accepted:    5.3K
 * Total Submissions: 10K
 * Testcase Example:  '[[1,1],[2,2],[3,3]]'
 *
 * 给你一个  n x 2 的二维数组 points ，它表示二维平面上的一些点坐标，其中 points[i] = [xi, yi] 。
 * 
 * 我们定义 x 轴的正方向为 右 （x 轴递增的方向），x 轴的负方向为 左 （x 轴递减的方向）。类似的，我们定义 y 轴的正方向为 上 （y
 * 轴递增的方向），y 轴的负方向为 下 （y 轴递减的方向）。
 * 
 * 你需要安排这 n 个人的站位，这 n 个人中包括 Alice 和 Bob 。你需要确保每个点处 恰好 有 一个 人。同时，Alice 想跟 Bob
 * 单独玩耍，所以 Alice 会以 Alice 的坐标为 左上角 ，Bob 的坐标为 右下角 建立一个矩形的围栏（注意，围栏可能 不
 * 包含任何区域，也就是说围栏可能是一条线段）。如果围栏的 内部 或者 边缘 上有任何其他人，Alice 都会难过。
 * 
 * 请你在确保 Alice 不会 难过的前提下，返回 Alice 和 Bob 可以选择的 点对 数目。
 * 
 * 注意，Alice 建立的围栏必须确保 Alice 的位置是矩形的左上角，Bob 的位置是矩形的右下角。比方说，以 (1, 1) ，(1, 3) ，(3,
 * 1) 和 (3, 3) 为矩形的四个角，给定下图的两个输入，Alice 都不能建立围栏，原因如下：
 * 
 * 
 * 图一中，Alice 在 (3, 3) 且 Bob 在 (1, 1) ，Alice 的位置不是左上角且 Bob 的位置不是右下角。
 * 图二中，Alice 在 (1, 3) 且 Bob 在 (1, 1) ，Bob 的位置不是在围栏的右下角。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：0
 * 解释：没有办法可以让 Alice 的围栏以 Alice 的位置为左上角且 Bob 的位置为右下角。所以我们返回 0 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：points = [[6,2],[4,4],[2,6]]
 * 输出：2
 * 解释：总共有 2 种方案安排 Alice 和 Bob 的位置，使得 Alice 不会难过：
 * - Alice 站在 (4, 4) ，Bob 站在 (6, 2) 。
 * - Alice 站在 (2, 6) ，Bob 站在 (4, 4) 。
 * 不能安排 Alice 站在 (2, 6) 且 Bob 站在 (6, 2) ，因为站在 (4, 4) 的人处于围栏内。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 
 * 输入：points = [[3,1],[1,3],[1,1]]
 * 输出：2
 * 解释：总共有 2 种方案安排 Alice 和 Bob 的位置，使得 Alice 不会难过：
 * - Alice 站在 (1, 1) ，Bob 站在 (3, 1) 。
 * - Alice 站在 (1, 3) ，Bob 站在 (1, 1) 。
 * 不能安排 Alice 站在 (1, 3) 且 Bob 站在 (3, 1) ，因为站在 (1, 1) 的人处于围栏内。
 * 注意围栏是可以不包含任何面积的，上图中第一和第二个围栏都是合法的。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= n <= 1000
 * points[i].length == 2
 * -10^9 <= points[i][0], points[i][1] <= 10^9
 * points[i] 点对两两不同。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int numberOfPairs(int[][] points) {
        int count = 0;
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else
                return b[1] - a[1];
        });

        int n = points.length;
        for (int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                if (points[i][1] >= points[j][1] && points[j][1] > max) {
                    count++;
                    max = points[j][1];
                }
            }
        }

        return count;
    }
}
// @lc code=end
