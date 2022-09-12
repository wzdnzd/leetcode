import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * @lc app=leetcode.cn id=710 lang=java
 *
 * [710] 黑名单中的随机数
 *
 * https://leetcode.cn/problems/random-pick-with-blacklist/description/
 *
 * algorithms
 * Hard (44.17%)
 * Likes:    210
 * Dislikes: 0
 * Total Accepted:    26K
 * Total Submissions: 58.8K
 * Testcase Example:  '["Solution","pick","pick","pick","pick","pick","pick","pick"]\n' +
  '[[7,[2,3,5]],[],[],[],[],[],[],[]]'
 *
 * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入
 * 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 * 
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 * 
 * 实现 Solution 类:
 * 
 * 
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * 输出
 * [null, 0, 4, 1, 6, 1, 0, 4]
 * 
 * 解释
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
 * ⁠                // 0、1、4和6的返回概率必须相等(即概率为1/4)。
 * solution.pick(); // 返回 4
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 6
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 0
 * solution.pick(); // 返回 4
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= n <= 10^9
 * 0 <= blacklist.length <= min(10^5, n - 1)
 * 0 <= blacklist[i] < n
 * blacklist 中所有值都 不同
 * pick 最多被调用 2 * 10^4 次
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 假设黑名单长度为m，那么可以将[0, n - m)中的黑名单数映射成[n - m, n - 1]中的白名单数
     * 
     * 证明：
     * 
     * 假设[0, n - m]有x个黑名单数
     * 那么[n - m, n - 1]有m - x个黑名单数
     * [n - m, n - 1]总长度为m
     * [n - m, n - 1]有m - (m - x) = x个白名单数
     * 所以[0, n - m)中的黑名单数可以和[n - m, n - 1]中的白名单数作一一映射
     * 
     * @param n
     * @param blacklist
     */

    private Map<Integer, Integer> map;
    private Random random;
    private int size;

    public Solution(int n, int[] blacklist) {
        map = new HashMap<>();
        size = n - blacklist.length;
        random = new Random();

        for (int num : blacklist) {
            map.put(num, -1);
        }

        int last = n - 1;
        for (int num : blacklist) {
            if (num >= size)
                continue;
            while (map.containsKey(last))
                last--;
            map.put(num, last--);
        }
    }

    public int pick() {
        int index = random.nextInt(size);
        return map.getOrDefault(index, index);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
// @lc code=end
