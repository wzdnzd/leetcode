/*
 * @lc app=leetcode.cn id=2332 lang=java
 *
 * [2332] 坐上公交的最晚时间
 *
 * https://leetcode.cn/problems/the-latest-time-to-catch-a-bus/description/
 *
 * algorithms
 * Medium (25.38%)
 * Likes:    50
 * Dislikes: 0
 * Total Accepted:    7.8K
 * Total Submissions: 29.1K
 * Testcase Example:  '[10,20]\n[2,17,18,19]\n2'
 *
 * 给你一个下标从 0 开始长度为 n 的整数数组 buses ，其中 buses[i] 表示第 i 辆公交车的出发时间。同时给你一个下标从 0 开始长度为
 * m 的整数数组 passengers ，其中 passengers[j] 表示第 j
 * 位乘客的到达时间。所有公交车出发的时间互不相同，所有乘客到达的时间也互不相同。
 * 
 * 给你一个整数 capacity ，表示每辆公交车 最多 能容纳的乘客数目。
 * 
 * 每位乘客都会搭乘下一辆有座位的公交车。如果你在 y 时刻到达，公交在 x 时刻出发，满足 y <= x  且公交没有满，那么你可以搭乘这一辆公交。最早
 * 到达的乘客优先上车。
 * 
 * 返回你可以搭乘公交车的最晚到达公交站时间。你 不能 跟别的乘客同时刻到达。
 * 
 * 注意：数组 buses 和 passengers 不一定是有序的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：buses = [10,20], passengers = [2,17,18,19], capacity = 2
 * 输出：16
 * 解释：
 * 第 1 辆公交车载着第 1 位乘客。
 * 第 2 辆公交车载着你和第 2 位乘客。
 * 注意你不能跟其他乘客同一时间到达，所以你必须在第二位乘客之前到达。
 * 
 * 示例 2：
 * 
 * 输入：buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
 * 输出：20
 * 解释：
 * 第 1 辆公交车载着第 4 位乘客。
 * 第 2 辆公交车载着第 6 位和第 2 位乘客。
 * 第 3 辆公交车载着第 1 位乘客和你。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == buses.length
 * m == passengers.length
 * 1 <= n, m, capacity <= 10^5
 * 2 <= buses[i], passengers[i] <= 10^9
 * buses 中的元素 互不相同 。
 * passengers 中的元素 互不相同 。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int position = 0, space = 0;
        for (int arrive : buses) {
            space = capacity;
            while (space > 0 && position < passengers.length && passengers[position] <= arrive) {
                space--;
                position++;
            }
        }

        position--;
        int lastCatchTime = space > 0 ? buses[buses.length - 1] : passengers[position];
        while (position >= 0 && passengers[position] == lastCatchTime) {
            position--;
            lastCatchTime--;
        }

        return lastCatchTime;
    }
}
// @lc code=end
