/*
 * @lc app=leetcode.cn id=815 lang=java
 *
 * [815] 公交路线
 *
 * https://leetcode.cn/problems/bus-routes/description/
 *
 * algorithms
 * Hard (44.47%)
 * Likes:    379
 * Dislikes: 0
 * Total Accepted:    40.9K
 * Total Submissions: 91.9K
 * Testcase Example:  '[[1,2,7],[3,6,7]]\n1\n6'
 *
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * 
 * 
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1
 * -> ... 这样的车站路线行驶。
 * 
 * 
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * 
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target =
 * 12
 * 输出：-1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 .
 * 1 
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) 
 * 0 
 * 0 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;

        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> records = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = records.getOrDefault(site, new ArrayList<>());
                for (int j : list)
                    edge[i][j] = edge[j][i] = true;

                list.add(i);
                records.put(site, list);
            }
        }

        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        Queue<Integer> que = new LinkedList<>();

        for (int bus : records.getOrDefault(source, new ArrayList<>())) {
            distances[bus] = 1;
            que.offer(bus);
        }

        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && distances[y] == -1) {
                    distances[y] = distances[x] + 1;
                    que.offer(y);
                }
            }
        }

        int count = Integer.MAX_VALUE;
        for (int bus : records.getOrDefault(target, new ArrayList<>())) {
            if (distances[bus] != -1)
                count = Math.min(count, distances[bus]);

        }

        return count == Integer.MAX_VALUE ? -1 : count;
    }
}
// @lc code=end
