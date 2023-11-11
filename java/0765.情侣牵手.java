/*
 * @lc app=leetcode.cn id=765 lang=java
 *
 * [765] 情侣牵手
 *
 * https://leetcode.cn/problems/couples-holding-hands/description/
 *
 * algorithms
 * Hard (65.79%)
 * Likes:    438
 * Dislikes: 0
 * Total Accepted:    40.6K
 * Total Submissions: 61.5K
 * Testcase Example:  '[0,2,1,3]'
 *
 * n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。
 * 
 * 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2,
 * 3)，以此类推，最后一对是 (2n-2, 2n-1)。
 * 
 * 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。 每次交换可选择任意两人，让他们站起来交换座位。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: row = [0,2,1,3]
 * 输出: 1
 * 解释: 只需要交换row[1]和row[2]的位置即可。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: row = [3,2,0,1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 2n == row.length
 * 2 <= n <= 30
 * n 是偶数
 * 0 <= row[i] < 2n
 * row 中所有元素均无重复
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int num = n / 2;
        UnionFind uf = new UnionFind(num);
        for (int i = 0; i < n; i += 2)
            uf.union(row[i] / 2, row[i + 1] / 2);

        return num - uf.getCount();
    }

    private class UnionFind {
        private int count;
        private int[] parent;

        public UnionFind(int count) {
            this.count = count;
            this.parent = new int[count];

            for (int i = 0; i < count; i++)
                parent[i] = i;
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }

            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY)
                return;

            parent[rootX] = rootY;
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
// @lc code=end
