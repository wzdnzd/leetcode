#
# @lc app=leetcode.cn id=146 lang=python3
#
# [146] LRU缓存机制
#
# https://leetcode-cn.com/problems/lru-cache/description/
#
# algorithms
# Hard (38.01%)
# Likes:    146
# Dislikes: 0
# Total Accepted:    8.4K
# Total Submissions: 20.6K
# Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
#
# 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
#
# 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
# 写入数据 put(key, value) -
# 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
#
# 进阶:
#
# 你是否可以在 O(1) 时间复杂度内完成这两种操作？
#
# 示例:
#
# LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
#
# cache.put(1, 1);
# cache.put(2, 2);
# cache.get(1);       // 返回  1
# cache.put(3, 3);    // 该操作会使得密钥 2 作废
# cache.get(2);       // 返回 -1 (未找到)
# cache.put(4, 4);    // 该操作会使得密钥 1 作废
# cache.get(1);       // 返回 -1 (未找到)
# cache.get(3);       // 返回  3
# cache.get(4);       // 返回  4
#
#
#


class LinkNode:
    def __init__(self, val, key):
        self.val = val
        self.key = key
        self.pre = None
        self.next = None


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}
        self.head = LinkNode(None, None)
        self.tail = LinkNode(None, None)
        self.head.next = self.tail
        self.tail.pre = self.head

    def insert(self, node):
        p = self.head.next
        self.head.next = node
        node.pre = self.head
        node.next = p
        p.pre = node

    def remove(self, node):
        p = node.pre
        q = node.next
        p.next = q
        q.pre = p

    def get(self, key: int) -> int:
        if key in self.cache:
            node = self.cache[key]
            # move to first
            self.remove(node)
            self.insert(node)

            return node.val
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            node.val = value
            self.remove(node)
            self.insert(node)
        else:
            if len(self.cache) == self.capacity:
                node = self.tail.pre
                self.cache.pop(node.key)
                self.remove(node)

            node = LinkNode(value, key)
            self.insert(node)
            self.cache[key] = node

        # Your LRUCache object will be instantiated and called as such:
        # obj = LRUCache(capacity)
        # param_1 = obj.get(key)
        # obj.put(key,value)
