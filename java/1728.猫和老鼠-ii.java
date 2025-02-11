/*
 * @lc app=leetcode.cn id=1728 lang=java
 *
 * [1728] 猫和老鼠 II
 *
 * https://leetcode.cn/problems/cat-and-mouse-ii/description/
 *
 * algorithms
 * Hard (63.08%)
 * Likes:    175
 * Dislikes: 0
 * Total Accepted:    15K
 * Total Submissions: 23.5K
 * Testcase Example:  '["####F","#C...","M...."]\n1\n2'
 *
 * 一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
 * 
 * 它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
 * 
 * 
 * 玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
 * 地板由字符 '.' 表示，玩家可以通过这个格子。
 * 墙用字符 '#' 表示，玩家不能通过这个格子。
 * 食物用字符 'F' 表示，玩家可以通过这个格子。
 * 字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。
 * 
 * 
 * 猫和老鼠按照如下规则移动：
 * 
 * 
 * 老鼠 先移动 ，然后两名玩家轮流移动。
 * 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。
 * catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
 * 它们可以停留在原地。
 * 老鼠可以跳跃过猫的位置。
 * 
 * 
 * 游戏有 4 种方式会结束：
 * 
 * 
 * 如果猫跟老鼠处在相同的位置，那么猫获胜。
 * 如果猫先到达食物，那么猫获胜。
 * 如果老鼠先到达食物，那么老鼠获胜。
 * 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
 * 
 * 
 * 给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回
 * true ，否则返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * 输出：true
 * 解释：猫无法抓到老鼠，也没法比老鼠先到达食物。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * 输出：false
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
 * 输出：false
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump
 * = 1
 * 输出：true
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * rows == grid.length
 * cols = grid[i].length
 * 1 
 * grid[i][j] 只包含字符 'C' ，'M' ，'F' ，'.' 和 '#' 。
 * grid 中只包含一个 'C' ，'M' 和 'F' 。
 * 1 
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public static final int MAX_MOVES = 1000;
    public static final int MOUSE_TURN = 0, CAT_TURN = 1;
    public static final int UNKNOWN = 0, MOUSE_WIN = 1, CAT_WIN = 2;

    public static final char WALL = '#';
    public static final char CAT = 'C', MOUSE = 'M', FOOD = 'F';
    private static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private int food;
    private int rows, cols;
    private int catJump, mouseJump;

    private String[] grid;
    private int[][][] degrees;
    private int[][][][] results;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.rows = grid.length;
        this.cols = grid[0].length();
        this.grid = grid;
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int mouseStart = -1, catStart = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = grid[i].charAt(j);
                if (c == CAT) {
                    catStart = getPos(i, j);
                } else if (c == MOUSE) {
                    mouseStart = getPos(i, j);
                } else if (c == FOOD) {
                    food = getPos(i, j);
                }
            }
        }

        int total = rows * cols;
        this.degrees = new int[total][total][2];
        this.results = new int[total][total][2][2];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (grid[mouseRow].charAt(mouseCol) == WALL) {
                continue;
            }

            for (int cat = 0; cat < total; cat++) {
                int catRow = cat / cols, catCol = cat % cols;
                if (grid[catRow].charAt(catCol) == WALL) {
                    continue;
                }

                degrees[mouse][cat][MOUSE_TURN]++;
                degrees[mouse][cat][CAT_TURN]++;

                for (int[] dir : dirs) {
                    for (int row = mouseRow + dir[0], col = mouseCol + dir[1], jump = 1; row >= 0 && row < rows
                            && col >= 0 && col < cols && grid[row].charAt(col) != WALL
                            && jump <= mouseJump; row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(row, col), nextCat = getPos(catRow, catCol);
                        degrees[nextMouse][nextCat][MOUSE_TURN]++;
                    }

                    for (int row = catRow + dir[0], col = catCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0
                            && col < cols && grid[row].charAt(col) != WALL
                            && jump <= catJump; row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(mouseRow, mouseCol), nextCat = getPos(row, col);
                        degrees[nextMouse][nextCat][CAT_TURN]++;
                    }
                }
            }
        }

        for (int pos = 0; pos < total; pos++) {
            if (pos == food) {
                continue;
            }

            int row = pos / cols, col = pos % cols;
            if (grid[row].charAt(col) == WALL) {
                continue;
            }

            results[pos][pos][MOUSE_TURN][0] = CAT_WIN;
            results[pos][pos][MOUSE_TURN][1] = 0;
            results[pos][pos][CAT_TURN][0] = CAT_WIN;
            results[pos][pos][CAT_TURN][1] = 0;

            queue.offer(new int[] { pos, pos, MOUSE_TURN });
            queue.offer(new int[] { pos, pos, CAT_TURN });
        }

        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (grid[mouseRow].charAt(mouseCol) == WALL || mouse == food) {
                continue;
            }

            results[mouse][food][MOUSE_TURN][0] = CAT_WIN;
            results[mouse][food][MOUSE_TURN][1] = 0;
            results[mouse][food][CAT_TURN][0] = CAT_WIN;
            results[mouse][food][CAT_TURN][1] = 0;
            queue.offer(new int[] { mouse, food, MOUSE_TURN });
            queue.offer(new int[] { mouse, food, CAT_TURN });
        }

        for (int cat = 0; cat < total; cat++) {
            int catRow = cat / cols, catCol = cat % cols;
            if (grid[catRow].charAt(catCol) == WALL || cat == food) {
                continue;
            }

            results[food][cat][MOUSE_TURN][0] = MOUSE_WIN;
            results[food][cat][MOUSE_TURN][1] = 0;
            results[food][cat][CAT_TURN][0] = MOUSE_WIN;
            results[food][cat][CAT_TURN][1] = 0;

            queue.offer(new int[] { food, cat, MOUSE_TURN });
            queue.offer(new int[] { food, cat, CAT_TURN });
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int result = results[mouse][cat][turn][0];
            int steps = results[mouse][cat][turn][1];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);

            for (int[] prevState : prevStates) {
                int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];

                if (results[prevMouse][prevCat][prevTurn][0] == UNKNOWN) {
                    boolean winState = (result == MOUSE_WIN && prevTurn == MOUSE_TURN)
                            || (result == CAT_WIN && prevTurn == CAT_TURN);
                    if (winState) {
                        results[prevMouse][prevCat][prevTurn][0] = result;
                        results[prevMouse][prevCat][prevTurn][1] = steps + 1;
                        queue.offer(new int[] { prevMouse, prevCat, prevTurn });
                    } else {
                        degrees[prevMouse][prevCat][prevTurn]--;
                        if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                            results[prevMouse][prevCat][prevTurn][0] = result;
                            results[prevMouse][prevCat][prevTurn][1] = steps + 1;
                            queue.offer(new int[] { prevMouse, prevCat, prevTurn });
                        }
                    }
                }
            }
        }

        return results[mouseStart][catStart][MOUSE_TURN][0] == MOUSE_WIN
                && results[mouseStart][catStart][MOUSE_TURN][1] <= MAX_MOVES;
    }

    private List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<int[]>();
        int mouseRow = mouse / cols, mouseCol = mouse % cols;
        int catRow = cat / cols, catCol = cat % cols;
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        int maximumJump = prevTurn == MOUSE_TURN ? mouseJump : catJump;
        int startRow = prevTurn == MOUSE_TURN ? mouseRow : catRow;
        int startCol = prevTurn == MOUSE_TURN ? mouseCol : catCol;
        prevStates.add(new int[] { mouse, cat, prevTurn });

        for (int[] dir : dirs) {
            for (int i = startRow + dir[0], j = startCol + dir[1], jump = 1; i >= 0 && i < rows && j >= 0 && j < cols
                    && grid[i].charAt(j) != WALL && jump <= maximumJump; i += dir[0], j += dir[1], jump++) {
                int prevMouseRow = prevTurn == MOUSE_TURN ? i : mouseRow;
                int prevMouseCol = prevTurn == MOUSE_TURN ? j : mouseCol;
                int prevCatRow = prevTurn == MOUSE_TURN ? catRow : i;
                int prevCatCol = prevTurn == MOUSE_TURN ? catCol : j;
                int prevMouse = getPos(prevMouseRow, prevMouseCol);
                int prevCat = getPos(prevCatRow, prevCatCol);
                prevStates.add(new int[] { prevMouse, prevCat, prevTurn });
            }
        }

        return prevStates;
    }

    private int getPos(int row, int col) {
        return row * cols + col;
    }
}
// @lc code=end
