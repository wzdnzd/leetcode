import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=773 lang=java
 *
 * [773] 滑动谜题
 *
 * https://leetcode.cn/problems/sliding-puzzle/description/
 *
 * algorithms
 * Hard (70.35%)
 * Likes:    278
 * Dislikes: 0
 * Total Accepted:    31.1K
 * Total Submissions: 44.3K
 * Testcase Example:  '[[1,2,3],[4,0,5]]'
 *
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0
 * 与一个相邻的数字（上下左右）进行交换.
 * 
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 
 * 
 * 示例 2:
 * 
 * 
 * 
 * 
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 
 * 
 * 示例 3:
 * 
 * 
 * 
 * 
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * board[i][j] 中每个值都 不同
 * 
 * 
 */

// @lc code=start
class Solution {
    public int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length, step = 0;
        String target = "123450";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }

        String raw = sb.toString();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(raw);
        visited.add(raw);
        int[][] neighbors = calculateNeighbors(m, n);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (target.equals(current))
                    return step;

                int index = 0;
                while ('0' != current.charAt(index))
                    index++;

                for (int j : neighbors[index]) {
                    if (j < 0)
                        continue;
                    String word = swap(current, index, j);
                    if (!visited.contains(word)) {
                        queue.offer(word);
                        visited.add(word);
                    }
                }

            }

            step++;
        }

        return -1;
    }

    private String swap(String content, int i, int j) {
        char[] chars = content.toCharArray();
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;

        return new String(chars);
    }

    private int[][] calculateNeighbors(int m, int n) {
        int[][] indexs = new int[m * n][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                // 上
                indexs[idx][0] = i == 0 ? -1 : idx - n;
                // 下
                indexs[idx][1] = i == m - 1 ? -1 : idx + n;
                // 左
                indexs[idx][2] = j == 0 ? -1 : idx - 1;
                // 右
                indexs[idx][3] = j == n - 1 ? -1 : idx + 1;
            }
        }
        return indexs;
    }
}
// @lc code=end
