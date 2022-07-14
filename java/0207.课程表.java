import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=207 lang=java
 *
 * [207] 课程表
 *
 * https://leetcode.cn/problems/course-schedule/description/
 *
 * algorithms
 * Medium (53.86%)
 * Likes:    1328
 * Dislikes: 0
 * Total Accepted:    224.2K
 * Total Submissions: 416.3K
 * Testcase Example:  '2\n[[1,0]]'
 *
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi]
 * ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 
 * 
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 
 * 
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 
 * 示例 2：
 * 
 * 
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * prerequisites[i].length == 2
 * 0 i, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1)
            return true;

        Map<Integer, Integer> indegreeMap = new HashMap<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            indegreeMap.put(i, 0);
        }

        Map<Integer, List<Integer>> dependentsMap = new HashMap<>();
        for (int[] requisite : prerequisites) {
            int current = requisite[1];
            int next = requisite[0];

            indegreeMap.put(next, indegreeMap.get(next) + 1);
            if (!dependentsMap.containsKey(current))
                dependentsMap.put(current, new ArrayList<>());
            dependentsMap.get(current).add(next);
        }

        Deque<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0)
                queue.offer(entry.getKey());
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            indegreeMap.remove(course);

            if (!dependentsMap.containsKey(course))
                continue;

            List<Integer> list = dependentsMap.get(course);
            for (Integer num : list) {
                int indegree = indegreeMap.get(num) - 1;
                if (indegree <= 0)
                    queue.offer(num);

                indegreeMap.put(num, indegree);
            }
        }

        return indegreeMap.isEmpty();
    }

}
// @lc code=end
