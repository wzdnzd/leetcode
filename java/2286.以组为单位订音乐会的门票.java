/*
 * @lc app=leetcode.cn id=2286 lang=java
 *
 * [2286] 以组为单位订音乐会的门票
 *
 * https://leetcode.cn/problems/booking-concert-tickets-in-groups/description/
 *
 * algorithms
 * Hard (25.39%)
 * Likes:    41
 * Dislikes: 0
 * Total Accepted:    4.7K
 * Total Submissions: 17.1K
 * Testcase Example:  '["BookMyShow","gather","gather","scatter","scatter"]\n' +
  '[[2,5],[4,0],[2,0],[5,1],[5,1]]'
 *
 * 一个音乐会总共有 n 排座位，编号从 0 到 n - 1 ，每一排有 m 个座椅，编号为 0 到 m - 1
 * 。你需要设计一个买票系统，针对以下情况进行座位安排：
 * 
 * 
 * 同一组的 k 位观众坐在 同一排座位，且座位连续 。
 * k 位观众中 每一位 都有座位坐，但他们 不一定 坐在一起。
 * 
 * 
 * 由于观众非常挑剔，所以：
 * 
 * 
 * 只有当一个组里所有成员座位的排数都 小于等于 maxRow ，这个组才能订座位。每一组的 maxRow 可能 不同 。
 * 如果有多排座位可以选择，优先选择 最小 的排数。如果同一排中有多个座位可以坐，优先选择号码 最小 的。
 * 
 * 
 * 请你实现 BookMyShow 类：
 * 
 * 
 * BookMyShow(int n, int m) ，初始化对象，n 是排数，m 是每一排的座位数。
 * int[] gather(int k, int maxRow) 返回长度为 2 的数组，表示 k 个成员中 第一个座位 的排数和座位编号，这 k
 * 位成员必须坐在 同一排座位，且座位连续 。换言之，返回最小可能的 r 和 c 满足第 r 排中 [c, c + k - 1] 的座位都是空的，且 r
 * <= maxRow 。如果 无法 安排座位，返回 [] 。
 * boolean scatter(int k, int maxRow) 如果组里所有 k 个成员 不一定 要坐在一起的前提下，都能在第 0 排到第
 * maxRow 排之间找到座位，那么请返回 true 。这种情况下，每个成员都优先找排数 最小 ，然后是座位编号最小的座位。如果不能安排所有 k
 * 个成员的座位，请返回 false 。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：
 * ["BookMyShow", "gather", "gather", "scatter", "scatter"]
 * [[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
 * 输出：
 * [null, [0, 0], [], true, false]
 * 
 * 解释：
 * BookMyShow bms = new BookMyShow(2, 5); // 总共有 2 排，每排 5 个座位。
 * bms.gather(4, 0); // 返回 [0, 0]
 * ⁠                 // 这一组安排第 0 排 [0, 3] 的座位。
 * bms.gather(2, 0); // 返回 []
 * ⁠                 // 第 0 排只剩下 1 个座位。
 * ⁠                 // 所以无法安排 2 个连续座位。
 * bms.scatter(5, 1); // 返回 True
 * ⁠                  // 这一组安排第 0 排第 4 个座位和第 1 排 [0, 3] 的座位。
 * bms.scatter(5, 1); // 返回 False
 * ⁠                  // 总共只剩下 2 个座位。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 5 * 10^4
 * 1 <= m, k <= 10^9
 * 0 <= maxRow <= n - 1
 * gather 和 scatter 总 调用次数不超过 5 * 10^4 次。
 * 
 * 
 */

// @lc code=start

class BookMyShow {
    private final int rows;
    private final int seats;

    private final int[] minTree;
    private final long[] sumTree;

    public BookMyShow(int n, int m) {
        this.rows = n;
        this.seats = m;

        this.minTree = new int[4 * n];
        this.sumTree = new long[4 * n];
    }

    public int[] gather(int k, int maxRow) {
        int index = queryMinRow(1, 0, rows - 1, seats - k);
        if (index > maxRow)
            return new int[0];

        long used = querySum(1, 0, rows - 1, index, index);
        update(1, 0, rows - 1, index, (int) (used + k));

        return new int[] { index, (int) used };
    }

    public boolean scatter(int k, int maxRow) {
        long usedTotal = querySum(1, 0, rows - 1, 0, maxRow);
        if ((maxRow + 1L) * seats - usedTotal < k)
            return false;

        int index = queryMinRow(1, 0, rows - 1, seats - 1);
        while (true) {
            long used = querySum(1, 0, rows - 1, index, index);
            if (seats - used >= k) {
                update(1, 0, rows - 1, index, (int) (used + k));
                break;
            }

            k -= seats - used;
            update(1, 0, rows - 1, index, seats);
            index++;
        }

        return true;
    }

    private void update(int start, int left, int right, int index, int val) {
        if (left == right) {
            minTree[start] = val;
            sumTree[start] = val;
            return;
        }

        int mid = (left + right) / 2;
        if (index <= mid)
            update(start * 2, left, mid, index, val);
        else
            update(start * 2 + 1, mid + 1, right, index, val);

        minTree[start] = Math.min(minTree[start * 2], minTree[start * 2 + 1]);
        sumTree[start] = sumTree[start * 2] + sumTree[start * 2 + 1];
    }

    private int queryMinRow(int index, int left, int right, int val) {
        if (left == right) {
            if (minTree[index] > val)
                return rows;

            return left;
        }

        int mid = (left + right) / 2;
        if (minTree[index * 2] <= val)
            return queryMinRow(index * 2, left, mid, val);
        else
            return queryMinRow(index * 2 + 1, mid + 1, right, val);
    }

    private long querySum(int index, int left, int right, int start, int end) {
        if (start <= left && right <= end)
            return sumTree[index];

        int mid = (left + right) / 2;
        long sum = 0;
        if (mid >= start)
            sum += querySum(index * 2, left, mid, start, end);

        if (mid + 1 <= end)
            sum += querySum(index * 2 + 1, mid + 1, right, start, end);

        return sum;
    }
}

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
// @lc code=end
