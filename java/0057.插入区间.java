/*
 * @lc app=leetcode.cn id=57 lang=java
 *
 * [57] 插入区间
 *
 * https://leetcode.cn/problems/insert-interval/description/
 *
 * algorithms
 * Medium (41.95%)
 * Likes:    772
 * Dislikes: 0
 * Total Accepted:    163.7K
 * Total Submissions: 385.9K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 
 * 示例 3：
 * 
 * 
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * intervals[i].length == 2
 * 0 
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> arrays = new ArrayList<>();
        boolean inserted = false;

        for (int[] interval : intervals) {
            if (newInterval[1] < interval[0]) {
                if (!inserted) {
                    arrays.add(newInterval);
                    inserted = true;
                }

                arrays.add(interval);
            } else if (interval[1] < newInterval[0]) {
                arrays.add(interval);
            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        
        if (!inserted) {
            arrays.add(newInterval);
        }

        return arrays.toArray(new int[arrays.size()][]);
    }
}
// @lc code=end

