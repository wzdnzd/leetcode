/*
 * @lc app=leetcode.cn id=1738 lang=java
 *
 * [1738] 找出第 K 大的异或坐标值
 *
 * https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value/description/
 *
 * algorithms
 * Medium (65.19%)
 * Likes:    107
 * Dislikes: 0
 * Total Accepted:    33.9K
 * Total Submissions: 51.8K
 * Testcase Example:  '[[5,2],[1,6]]\n1'
 *
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素
 * matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * 
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 
 * 示例 3：
 * 
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 
 * 示例 4：
 * 
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 10^6
 * 1 <= k <= m * n
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] array = new int[m + 1][n + 1];
        List<Integer> results = new ArrayList<Integer>();

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                array[i][j] = array[i - 1][j] ^ array[i][j - 1] ^ array[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results.add(array[i][j]);
            }
        }

        nthElement(results, 0, k - 1, results.size() - 1);
        return results.get(k - 1);
    }

    private void nthElement(List<Integer> results, int left, int kth, int right) {
        if (left == right)
            return;

        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(results, pivot, right);

        int low = left - 1, high = left - 1;
        for (int i = left; i <= right; i++) {
            if (results.get(i) > results.get(right)) {
                swap(results, ++high, i);
                swap(results, ++low, high);
            } else if (results.get(i) == results.get(right))
                swap(results, ++high, i);
        }

        if (low < left + kth && left + kth <= high)
            return;
        else if (left + kth <= low)
            nthElement(results, left, kth, low);
        else
            nthElement(results, high + 1, kth - (high - left + 1), right);
    }

    private void swap(List<Integer> results, int i, int j) {
        int temp = results.get(i);

        results.set(i, results.get(j));
        results.set(j, temp);
    }
}
// @lc code=end
