/*
 * @lc app=leetcode.cn id=3161 lang=java
 *
 * [3161] 物块放置查询
 *
 * https://leetcode.cn/problems/block-placement-queries/description/
 *
 * algorithms
 * Hard (21.44%)
 * Likes:    23
 * Dislikes: 0
 * Total Accepted:    3.6K
 * Total Submissions: 15K
 * Testcase Example:  '[[1,2],[2,3,3],[2,3,1],[2,2,2]]'
 *
 * 有一条无限长的数轴，原点在 0 处，沿着 x 轴 正 方向无限延伸。
 * 
 * 给你一个二维数组 queries ，它包含两种操作：
 * 
 * 
 * 操作类型 1 ：queries[i] = [1, x] 。在距离原点 x 处建一个障碍物。数据保证当操作执行的时候，位置 x 处 没有
 * 任何障碍物。
 * 操作类型 2 ：queries[i] = [2, x, sz] 。判断在数轴范围 [0, x] 内是否可以放置一个长度为 sz 的物块，这个物块需要
 * 完全 放置在范围 [0, x] 内。如果物块与任何障碍物有重合，那么这个物块 不能 被放置，但物块可以与障碍物刚好接触。注意，你只是进行查询，并 不是
 * 真的放置这个物块。每个查询都是相互独立的。
 * 
 * 
 * 请你返回一个 boolean 数组results ，如果第 i 个操作类型 2 的操作你可以放置物块，那么 results[i] 为 true ，否则为
 * false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
 * 
 * 输出：[false,true,true]
 * 
 * 解释：
 * 
 * 
 * 
 * 查询 0 ，在 x = 2 处放置一个障碍物。在 x = 3 之前任何大小不超过 2 的物块都可以被放置。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
 * 
 * 输出：[true,true,false]
 * 
 * 解释：
 * 
 * 
 * 
 * 
 * 查询 0 在 x = 7 处放置一个障碍物。在 x = 7 之前任何大小不超过 7 的物块都可以被放置。
 * 查询 2 在 x = 2 处放置一个障碍物。现在，在 x = 7 之前任何大小不超过 5 的物块可以被放置，x = 2 之前任何大小不超过 2
 * 的物块可以被放置。
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= queries.length <= 15 * 10^4
 * 2 <= queries[i].length <= 3
 * 1 <= queries[i][0] <= 2
 * 1 <= x, sz <= min(5 * 10^4, 3 * queries.length)
 * 输入保证操作 1 中，x 处不会有障碍物。
 * 输入保证至少有一个操作类型 2 。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

class Solution {
    private int[] segmentTrees;

    private void update(int index, int val, int p, int left, int right) {
        if (left == right) {
            segmentTrees[p] = val;
            return;
        }

        int mid = (left + right) >> 1;
        if (index <= mid)
            update(index, val, p << 1, left, mid);
        else
            update(index, val, p << 1 | 1, mid + 1, right);

        segmentTrees[p] = Math.max(segmentTrees[p << 1], segmentTrees[p << 1 | 1]);
    }

    private int query(int low, int high, int p, int left, int right) {
        if (low <= left && right <= high)
            return segmentTrees[p];

        int mid = (left + right) >> 1;
        int result = 0;
        if (low <= mid)
            result = Math.max(result, query(low, high, p << 1, left, mid));
        if (high > mid)
            result = Math.max(result, query(low, high, p << 1 | 1, mid + 1, right));

        return result;
    }

    public List<Boolean> getResults(int[][] queries) {
        int limit = 50000;
        segmentTrees = new int[limit << 2];

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(limit);

        update(limit, limit, 1, 0, limit);
        List<Boolean> result = new ArrayList<>();

        for (int[] query : queries) {
            if (query[0] == 1) {
                int x = query[1];
                int left = Optional.ofNullable(set.floor(x - 1)).orElse(0);
                int right = Optional.ofNullable(set.ceiling(x + 1)).orElse(limit);

                update(x, x - left, 1, 0, limit);
                update(right, right - x, 1, 0, limit);
                set.add(x);
            } else {
                int x = query[1], size = query[2];
                int pre = Optional.ofNullable(set.floor(x)).orElse(0);
                int maxSpace = query(0, pre, 1, 0, limit);
                maxSpace = Math.max(maxSpace, x - pre);
                result.add(maxSpace >= size);
            }
        }

        return result;
    }
}
// @lc code=end
