/*
 * @lc app=leetcode.cn id=2671 lang=java
 *
 * [2671] 频率跟踪器
 *
 * https://leetcode.cn/problems/frequency-tracker/description/
 *
 * algorithms
 * Medium (33.29%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    11.3K
 * Total Submissions: 29K
 * Testcase Example:  '["FrequencyTracker","add","add","hasFrequency"]\n[[],[3],[3],[2]]'
 *
 * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
 * 
 * 实现 FrequencyTracker 类：
 * 
 * 
 * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
 * void add(int number)：添加一个 number 到数据结构中。
 * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number
 * ，在这种情况下不删除任何内容。
 * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回
 * false。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入
 * ["FrequencyTracker", "add", "add", "hasFrequency"]
 * [[], [3], [3], [2]]
 * 输出
 * [null, null, null, true]
 * 
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(3); // 数据结构现在包含 [3]
 * frequencyTracker.add(3); // 数据结构现在包含 [3, 3]
 * frequencyTracker.hasFrequency(2); // 返回 true ，因为 3 出现 2 次
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入
 * ["FrequencyTracker", "add", "deleteOne", "hasFrequency"]
 * [[], [1], [1], [1]]
 * 输出
 * [null, null, null, false]
 * 
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(1); // 数据结构现在包含 [1]
 * frequencyTracker.deleteOne(1); // 数据结构现在为空 []
 * frequencyTracker.hasFrequency(1); // 返回 false ，因为数据结构为空
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入
 * ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
 * [[], [2], [3], [1]]
 * 输出
 * [null, false, null, true]
 * 
 * 解释
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.hasFrequency(2); // 返回 false ，因为数据结构为空
 * frequencyTracker.add(3); // 数据结构现在包含 [3]
 * frequencyTracker.hasFrequency(1); // 返回 true ，因为 3 出现 1 次
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= number <= 10^5
 * 1 <= frequency <= 10^5
 * 最多调用 add、deleteOne 和 hasFrequency 共计 2 * 10^5 次
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class FrequencyTracker {
    // 记录每个数字出现的次数
    private final Map<Integer, Integer> frequencyMap;

    // 记录出现次数为 k 的数字有哪些
    private final Map<Integer, Set<Integer>> frequencyCountMap;

    public FrequencyTracker() {
        this.frequencyMap = new HashMap<>();
        this.frequencyCountMap = new HashMap<>();
    }

    public void add(int number) {
        int frequency = frequencyMap.getOrDefault(number, 0);
        frequencyMap.put(number, frequency + 1);

        if (frequency > 0)
            frequencyCountMap.get(frequency).remove(number);

        frequencyCountMap.computeIfAbsent(frequency + 1, k -> new HashSet<>()).add(number);
    }

    public void deleteOne(int number) {
        int frequency = frequencyMap.getOrDefault(number, 0);
        if (frequency == 0)
            return;

        if (frequency == 1) {
            frequencyMap.remove(number);
            frequencyCountMap.get(frequency).remove(number);
        } else {
            frequencyMap.put(number, frequency - 1);
            frequencyCountMap.get(frequency).remove(number);
            frequencyCountMap.computeIfAbsent(frequency - 1, k -> new HashSet<>()).add(number);
        }
    }

    public boolean hasFrequency(int frequency) {
        return frequencyCountMap.containsKey(frequency) && !frequencyCountMap.get(frequency).isEmpty();
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */
// @lc code=end
