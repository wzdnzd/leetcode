import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=212 lang=java
 *
 * [212] 单词搜索 II
 *
 * https://leetcode.cn/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (45.56%)
 * Likes:    672
 * Dislikes: 0
 * Total Accepted:    77.2K
 * Total Submissions: 169.4K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n' +
  '["oath","pea","eat","rain"]'
 *
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 
 * 单词必须按照字母顺序，通过 相邻的单元格
 * 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：board =
 * [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    class TrieNode {
        private String value;
        private TrieNode[] next = new TrieNode[26];
    }

    private TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }

            node = node.next[index];
        }

        node.value = word;
    }

    // 邻域
    private int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> hs = new HashSet<>();

        // 是否访问过
        boolean[][] visited = new boolean[15][15];

        int row = board.length;
        int col = board[0].length;

        for (String word : words) {
            insert(word);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int index = board[i][j] - 'a';
                if (root.next[index] != null) {
                    visited[i][j] = true;
                    dfs(i, row, j, col, root.next[index], board, visited, hs);
                    visited[i][j] = false;
                }
            }
        }

        return new ArrayList<>(hs);
    }

    private void dfs(int i, int row, int j, int col, TrieNode node, char[][] board, boolean[][] visited,
            Set<String> hs) {
        if (node.value != null) {
            hs.add(node.value);
        }
        for (int[] array : directions) {
            int m = i + array[0];
            int n = j + array[1];

            // 边界
            if (m < 0 || m >= row || n < 0 || n >= col) {
                continue;
            }

            // 已经访问过
            if (visited[m][n]) {
                continue;
            }

            int index = board[m][n] - 'a';
            if (node.next[index] != null) {
                visited[m][n] = true;
                dfs(m, row, n, col, node.next[index], board, visited, hs);
                visited[m][n] = false;
            }
        }
    }
}
// @lc code=end
