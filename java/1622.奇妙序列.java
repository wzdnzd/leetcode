/*
 * @lc app=leetcode.cn id=1622 lang=java
 *
 * [1622] 奇妙序列
 *
 * https://leetcode.cn/problems/fancy-sequence/description/
 *
 * algorithms
 * Hard (21.38%)
 * Likes:    97
 * Dislikes: 0
 * Total Accepted:    9.3K
 * Total Submissions: 39.7K
 * Testcase Example:  '["Fancy","append","addAll","append","multAll","getIndex","addAll","append","multAll","getIndex","getIndex","getIndex"]\n' +
  '[[],[2],[3],[7],[2],[0],[3],[10],[2],[0],[1],[2]]'
 *
 * 请你实现三个 API append，addAll 和 multAll 来实现奇妙序列。
 * 
 * 请实现 Fancy 类 ：
 * 
 * 
 * Fancy() 初始化一个空序列对象。
 * void append(val) 将整数 val 添加在序列末尾。
 * void addAll(inc) 将所有序列中的现有数值都增加 inc 。
 * void multAll(m) 将序列中的所有现有数值都乘以整数 m 。
 * int getIndex(idx) 得到下标为 idx 处的数值（下标从 0 开始），并将结果对 10^9 + 7
 * 取余。如果下标大于等于序列的长度，请返回 -1 。
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：
 * ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll",
 * "append", "multAll", "getIndex", "getIndex", "getIndex"]
 * [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
 * 输出：
 * [null, null, null, null, null, 10, null, null, null, 26, 34, 20]
 * 
 * 解释：
 * Fancy fancy = new Fancy();
 * fancy.append(2);   // 奇妙序列：[2]
 * fancy.addAll(3);   // 奇妙序列：[2+3] -> [5]
 * fancy.append(7);   // 奇妙序列：[5, 7]
 * fancy.multAll(2);  // 奇妙序列：[5*2, 7*2] -> [10, 14]
 * fancy.getIndex(0); // 返回 10
 * fancy.addAll(3);   // 奇妙序列：[10+3, 14+3] -> [13, 17]
 * fancy.append(10);  // 奇妙序列：[13, 17, 10]
 * fancy.multAll(2);  // 奇妙序列：[13*2, 17*2, 10*2] -> [26, 34, 20]
 * fancy.getIndex(0); // 返回 26
 * fancy.getIndex(1); // 返回 34
 * fancy.getIndex(2); // 返回 20
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 总共最多会有 10^5 次对 append，addAll，multAll 和 getIndex 的调用。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Fancy {
    private static final int MOD = 1000000007;

    private final List<Integer> records = new ArrayList<>();
    private long add = 0, mul = 1;

    public void append(int val) {
        records.add((int) ((val - add + MOD) * pow(mul, MOD - 2) % MOD));
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = mul * m % MOD;
        add = add * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= records.size())
            return -1;

        return (int) ((records.get(idx) * mul + add) % MOD);
    }

    private long pow(long x, int n) {
        long result = 1;
        while (n > 0) {
            if (n % 2 > 0)
                result = result * x % MOD;

            x = x * x % MOD;
            n /= 2;
        }

        return result;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
// @lc code=end
