import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=391 lang=java
 *
 * [391] 完美矩形
 *
 * https://leetcode.cn/problems/perfect-rectangle/description/
 *
 * algorithms
 * Hard (46.14%)
 * Likes:    238
 * Dislikes: 0
 * Total Accepted:    25.1K
 * Total Submissions: 54.5K
 * Testcase Example:  '[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]'
 *
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi]
 * 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * 
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * 输出：true
 * 解释：5 个矩形一起可以精确地覆盖一个矩形区域。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * 输出：false
 * 解释：两个矩形之间有间隔，无法覆盖成一个矩形。
 * 
 * 示例 3：
 * 
 * 
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * 输出：false
 * 解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= rectangles.length <= 2 * 10^4
 * rectangles[i].length == 4
 * -10^5 <= xi, yi, ai, bi <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int length = rectangles.length * 2, ids = 0;
        int[][] matrix = new int[length][4];
        // 初始化re数组,组成[横坐标,纵坐标下顶点,纵坐标上顶点,矩形的左边or右边标志]
        for (int[] i : rectangles) {
            matrix[ids++] = new int[] { i[0], i[1], i[3], 1 };
            matrix[ids++] = new int[] { i[2], i[1], i[3], -1 };
        }
        // 排序,按照横坐标进行排序,横坐标相等就按纵坐标排序
        Arrays.sort(matrix, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        // 操作每一个顶点，判断是否符合要求
        for (int i = 0; i < length;) {
            // 如果该边是矩形的左边界,就加入left
            List<int[]> left = new ArrayList<>();
            // 如果该边是矩形的左边界,就加入right
            List<int[]> right = new ArrayList<>();
            // 标志该边是不是 矩形的左边
            boolean flag = i == 0;
            // 判断横坐标相同情况下的边
            int x = i;
            while (x < length && matrix[x][0] == matrix[i][0])
                x++;
            // 判断该横坐标的 边是不是符合要求
            while (i < x) {
                // 因为是引用数据类型,所以可以直接操作list,相当于操作left或者right
                List<int[]> list = matrix[i][3] == 1 ? left : right;
                if (list.isEmpty())
                    list.add(matrix[i++]);
                else {
                    int[] pre = list.get(list.size() - 1);
                    int[] cur = matrix[i++];
                    // 有重叠 直接放回false
                    if (cur[1] < pre[2])
                        return false;
                    if (cur[1] == pre[2])
                        pre[2] = cur[2];
                    else
                        list.add(cur);
                }
            }
            // 判断边是中间边还是边界边
            if (!flag && x < length) {
                // 如果是中间边 判断左右是不是相等
                if (left.size() != right.size())
                    return false;
                for (int j = 0; j < left.size(); ++j) {
                    if (left.get(j)[2] == right.get(j)[2] && left.get(j)[1] == right.get(j)[1])
                        continue;
                    return false;
                }
            } else {
                // 如果是边界边判断是不是一条
                if (left.size() != 1 && right.size() == 0 || left.size() == 0 && right.size() != 1)
                    return false;
            }
        }
        return true;
    }
}
// @lc code=end
