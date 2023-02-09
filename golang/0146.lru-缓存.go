/*
* @lc app=leetcode.cn id=146 lang=golang
*
* [146] LRU 缓存
*
* https://leetcode.cn/problems/lru-cache/description/
*
  - algorithms
  - Medium (53.38%)
  - Likes:    2537
  - Dislikes: 0
  - Total Accepted:    444.4K
  - Total Submissions: 831.7K
  - Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n' +
    '[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'

*
* 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
*
* 实现 LRUCache 类：
*
*
*
*
* LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
* int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
* void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组
* key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
*
*
* 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
*
*
*
*
*
* 示例：
*
*
* 输入
* ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
* [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
* 输出
* [null, null, null, 1, null, -1, null, -1, 3, 4]
*
* 解释
* LRUCache lRUCache = new LRUCache(2);
* lRUCache.put(1, 1); // 缓存是 {1=1}
* lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
* lRUCache.get(1);    // 返回 1
* lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
* lRUCache.get(2);    // 返回 -1 (未找到)
* lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
* lRUCache.get(1);    // 返回 -1 (未找到)
* lRUCache.get(3);    // 返回 3
* lRUCache.get(4);    // 返回 4
*
*
*
*
* 提示：
*
*
* 1 <= capacity <= 3000
* 0 <= key <= 10000
* 0 <= value <= 10^5
* 最多调用 2 * 10^5 次 get 和 put
*
*
*/
package main

import "fmt"

// @lc code=start
type LRUCache struct {
	capacity, size int
	cache          map[int]*DoubleLinkedNode
	data           *DoubleLinkedList
}

type DoubleLinkedNode struct {
	Key, Val  int
	Pre, Next *DoubleLinkedNode
}

type DoubleLinkedList struct {
	Head, Tail *DoubleLinkedNode
}

func Constructor(capacity int) LRUCache {
	if capacity < 1 {
		fmt.Errorf("capacity must great than 0")
	}

	head := &DoubleLinkedNode{
		Key: -1,
		Val: -1,
	}
	tail := &DoubleLinkedNode{
		Key: -1,
		Val: -1,
		Pre: head,
	}
	head.Next = tail

	return LRUCache{
		size:     0,
		capacity: capacity,
		cache:    make(map[int]*DoubleLinkedNode),
		data: &DoubleLinkedList{
			Head: head,
			Tail: tail,
		},
	}
}

func (this *LRUCache) Get(key int) int {
	dln, ok := this.cache[key]
	if ok {
		this.data.moveToHead(dln)
		return dln.Val
	}

	return -1
}

func (dll *DoubleLinkedList) moveToHead(dln *DoubleLinkedNode) {
	dll.remove(dln)
	dll.addToHead(dln)
}

func (dll *DoubleLinkedList) remove(dln *DoubleLinkedNode) {
	if dln == nil {
		return
	}

	dln.Next.Pre = dln.Pre
	dln.Pre.Next = dln.Next
}

func (dll *DoubleLinkedList) addToHead(dln *DoubleLinkedNode) {
	dln.Next = dll.Head.Next
	dll.Head.Next.Pre = dln
	dln.Pre = dll.Head
	dll.Head.Next = dln
}

func (this *LRUCache) deleteLastUsed() {
	dln := this.data.Tail.Pre
	predln := dln.Pre
	if predln == nil {
		return
	}

	// remove DoublueLinkedNode
	predln.Next = this.data.Tail
	this.data.Tail.Pre = predln

	// delete key from cache
	delete(this.cache, dln.Key)

	// decrease size
	this.size--
}

func (this *LRUCache) Put(key int, value int) {
	dln, ok := this.cache[key]
	if ok {
		dln.Val = value
		this.data.moveToHead(dln)
		return
	}

	if this.size >= this.capacity {
		this.deleteLastUsed()
	}

	dln = &DoubleLinkedNode{
		Key: key,
		Val: value,
	}

	this.cache[key] = dln
	this.data.addToHead(dln)
	this.size++
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
// @lc code=end
