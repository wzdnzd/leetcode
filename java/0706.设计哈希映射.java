/*
 * @lc app=leetcode.cn id=706 lang=java
 *
 * [706] 设计哈希映射
 *
 * https://leetcode.cn/problems/design-hashmap/description/
 *
 * algorithms
 * Easy (63.70%)
 * Likes:    401
 * Dislikes: 0
 * Total Accepted:    107.6K
 * Total Submissions: 167.7K
 * Testcase Example:  '["MyHashMap","put","put","get","get","put","get","remove","get"]\n' +
  '[[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]'
 *
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * 
 * 实现 MyHashMap 类：
 * 
 * 
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key
 * 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 * 
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= key, value <= 10^6
 * 最多调用 10^4 次 put、get 和 remove 方法
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class MyHashMap {
    private static final int MAX_LEN = 1000001;
    private final int[] data;

    public MyHashMap() {
        this.data = new int[MAX_LEN];
        Arrays.fill(this.data, -1);
    }

    public void put(int key, int value) {
        this.data[key] = value;
    }

    public int get(int key) {
        return this.data[key];
    }

    public void remove(int key) {
        this.data[key] = -1;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
// @lc code=end
