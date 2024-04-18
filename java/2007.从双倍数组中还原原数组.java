/*
 * @lc app=leetcode.cn id=2007 lang=java
 *
 * [2007] 从双倍数组中还原原数组
 *
 * https://leetcode.cn/problems/find-original-array-from-doubled-array/description/
 *
 * algorithms
 * Medium (34.29%)
 * Likes:    35
 * Dislikes: 0
 * Total Accepted:    9.6K
 * Total Submissions: 26K
 * Testcase Example:  '[1,3,4,2,6,8]'
 *
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2
 * 加入数组中，然后将所有元素 随机打乱 。
 * 
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以
 * 任意 顺序返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：changed = [1,3,4,2,6,8]
 * 输出：[1,3,4]
 * 解释：一个可能的 original 数组为 [1,3,4] :
 * - 将 1 乘以 2 ，得到 1 * 2 = 2 。
 * - 将 3 乘以 2 ，得到 3 * 2 = 6 。
 * - 将 4 乘以 2 ，得到 4 * 2 = 8 。
 * 其他可能的原数组方案为 [4,3,1] 或者 [3,1,4] 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：changed = [6,3,0,1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 * 
 * 
 * 示例 3：
 * 
 * 输入：changed = [1]
 * 输出：[]
 * 解释：changed 不是一个双倍数组。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= changed.length <= 10^5
 * 0 <= changed[i] <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1)
            return new int[0];

        Arrays.sort(changed);

        Map<Integer, Integer> map = new HashMap<>();
        for (int x : changed)
            map.put(x, map.getOrDefault(x, 0) + 1);

        int[] result = new int[n / 2];
        int index = 0;

        for (int x : changed) {
            if (map.get(x) == 0)
                continue;

            map.put(x, map.get(x) - 1);

            int y = x * 2;
            if (map.getOrDefault(y, 0) == 0)
                return new int[0];

            map.put(y, map.get(y) - 1);
            result[index++] = x;
        }

        return result;
    }
}
// @lc code=end
