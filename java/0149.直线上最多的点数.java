import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=149 lang=java
 *
 * [149] 直线上最多的点数
 *
 * https://leetcode.cn/problems/max-points-on-a-line/description/
 *
 * algorithms
 * Hard (37.47%)
 * Likes:    422
 * Dislikes: 0
 * Total Accepted:    64.7K
 * Total Submissions: 172.4K
 * Testcase Example:  '[[1,1],[2,2],[3,3]]'
 *
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * points[i].length == 2
 * -10^4 i, yi 
 * points 中的所有点 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();

            int count = 0;
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int dx = x1 - x2, dy = y1 - y2;
                int k = gcd(dx, dy);
                String key = (dx / k) + "-" + (dy / k);
                map.put(key, map.getOrDefault(key, 0) + 1);
                count = Math.max(count, map.get(key));
            }

            ans = Math.max(ans, count + 1);
        }

        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
// @lc code=end
