/*
 * @lc app=leetcode.cn id=1462 lang=java
 *
 * [1462] 课程表 IV
 *
 * https://leetcode.cn/problems/course-schedule-iv/description/
 *
 * algorithms
 * Medium (45.83%)
 * Likes:    151
 * Dislikes: 0
 * Total Accepted:    20.1K
 * Total Submissions: 40.7K
 * Testcase Example:  '2\n[[1,0]]\n[[0,1],[1,0]]'
 *
 * 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。你会得到一个数组 prerequisite ，其中
 * prerequisites[i] = [ai, bi] 表示如果你想选 bi 课程，你 必须 先选 ai 课程。
 * 
 * 
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 
 * 
 * 先决条件也可以是 间接 的。如果课程 a 是课程 b 的先决条件，课程 b 是课程 c 的先决条件，那么课程 a 就是课程 c 的先决条件。
 * 
 * 你也得到一个数组 queries ，其中 queries[j] = [uj, vj]。对于第 j 个查询，您应该回答课程 uj 是否是课程 vj
 * 的先决条件。
 * 
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 
 * 输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries =
 * [[1,0],[1,2]]
 * 输出：[true,true]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 每一对 [ai, bi] 都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 10^4
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> records = new HashMap<>();
        int[] degrees = new int[numCourses];

        for (int[] requisite : prerequisites) {
            degrees[requisite[1]] += 1;
            List<Integer> courses = records.getOrDefault(requisite[0], new ArrayList<>());
            courses.add(requisite[1]);
            records.put(requisite[0], courses);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (degrees[i] == 0)
                queue.offer(i);
        }

        boolean[][] isDependence = new boolean[numCourses][numCourses];
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            List<Integer> courses = records.getOrDefault(i, null);
            if (courses == null || courses.isEmpty())
                continue;

            for (int j : courses) {
                degrees[j] -= 1;
                if (degrees[j] == 0)
                    queue.offer(j);

                isDependence[i][j] = true;
                for (int k = 0; k < numCourses; k++) {
                    isDependence[k][j] |= isDependence[k][i];
                }
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(isDependence[query[0]][query[1]]);
        }

        return ans;
    }
}
// @lc code=end
