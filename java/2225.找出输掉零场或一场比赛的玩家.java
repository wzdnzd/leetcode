/*
 * @lc app=leetcode.cn id=2225 lang=java
 *
 * [2225] 找出输掉零场或一场比赛的玩家
 *
 * https://leetcode.cn/problems/find-players-with-zero-or-one-losses/description/
 *
 * algorithms
 * Medium (63.45%)
 * Likes:    21
 * Dislikes: 0
 * Total Accepted:    11.7K
 * Total Submissions: 18.6K
 * Testcase Example:  '[[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]'
 *
 * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了
 * loseri 。
 * 
 * 返回一个长度为 2 的列表 answer ：
 * 
 * 
 * answer[0] 是所有 没有 输掉任何比赛的玩家列表。
 * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
 * 
 * 
 * 两个列表中的值都应该按 递增 顺序返回。
 * 
 * 注意：
 * 
 * 
 * 只考虑那些参与 至少一场 比赛的玩家。
 * 生成的测试用例保证 不存在 两场比赛结果 相同 。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 * 输出：[[1,2,10],[4,5,7,8]]
 * 解释：
 * 玩家 1、2 和 10 都没有输掉任何比赛。
 * 玩家 4、5、7 和 8 每个都输掉一场比赛。
 * 玩家 3、6 和 9 每个都输掉两场比赛。
 * 因此，answer[0] = [1,2,10] 和 answer[1] = [4,5,7,8] 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：matches = [[2,3],[1,3],[5,4],[6,4]]
 * 输出：[[1,2,5,6],[]]
 * 解释：
 * 玩家 1、2、5 和 6 都没有输掉任何比赛。
 * 玩家 3 和 4 每个都输掉两场比赛。
 * 因此，answer[0] = [1,2,5,6] 和 answer[1] = [] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= matches.length <= 10^5
 * matches[i].length == 2
 * 1 <= winneri, loseri <= 10^5
 * winneri != loseri
 * 所有 matches[i] 互不相同
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> records = new HashMap<>();

        for (int[] match : matches) {
            int winner = match[0], loser = match[1];

            records.putIfAbsent(winner, 0);
            records.put(loser, records.getOrDefault(loser, 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; ++i)
            result.add(new ArrayList<Integer>());

        for (Map.Entry<Integer, Integer> entry : records.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            if (value < 2)
                result.get(value).add(key);
        }

        Collections.sort(result.get(0));
        Collections.sort(result.get(1));

        return result;
    }
}
// @lc code=end
