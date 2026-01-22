/*
 * @lc app=leetcode.cn id=3507 lang=java
 *
 * [3507] 移除最小数对使数组有序 I
 *
 * https://leetcode.cn/problems/minimum-pair-removal-to-sort-array-i/description/
 *
 * algorithms
 * Easy (50.81%)
 * Likes:    9
 * Dislikes: 0
 * Total Accepted:    4.7K
 * Total Submissions: 8.7K
 * Testcase Example:  '[5,2,3,1]'
 *
 * 给你一个数组 nums，你可以执行以下操作任意次数：
 * 
 * 
 * 选择 相邻 元素对中 和最小 的一对。如果存在多个这样的对，选择最左边的一个。
 * 用它们的和替换这对元素。
 * 
 * 
 * 返回将数组变为 非递减 所需的 最小操作次数 。
 * 
 * 如果一个数组中每个元素都大于或等于它前一个元素（如果存在的话），则称该数组为非递减。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： nums = [5,2,3,1]
 * 
 * 输出： 2
 * 
 * 解释：
 * 
 * 
 * 元素对 (3,1) 的和最小，为 4。替换后 nums = [5,2,4]。
 * 元素对 (2,4) 的和为 6。替换后 nums = [5,6]。
 * 
 * 
 * 数组 nums 在两次操作后变为非递减。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： nums = [1,2,2]
 * 
 * 输出： 0
 * 
 * 解释：
 * 
 * 数组 nums 已经是非递减的。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 50
 * -1000 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    private class Node {
        private int index;
        private long value;
        private Node prev;
        private Node next;

        public Node() {
            this(-1, Integer.MAX_VALUE);
        }

        public Node(int index, long value) {
            this.index = index;
            this.value = value;
            prev = null;
            next = null;
        }

        public int getIndex() {
            return index;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int removals = 0;
        Map<Integer, Node> indexToNode = new HashMap<>();

        Node pseudoHead = new Node();
        Node pseudoTail = new Node();
        Node prevNode = pseudoHead;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            Node node = new Node(i, nums[i]);
            indexToNode.put(i, node);
            prevNode.setNext(node);
            node.setPrev(prevNode);
            prevNode = node;
        }

        prevNode.setNext(pseudoTail);
        pseudoTail.setPrev(prevNode);
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>(
                (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));

        int reversePairs = 0;
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new long[] { nums[i] + nums[i + 1], i, i + 1 });
            if (nums[i] > nums[i + 1])
                reversePairs++;

        }

        while (reversePairs > 0) {
            long[] arr = pq.poll();
            long newValue = arr[0];
            int index1 = (int) arr[1], index2 = (int) arr[2];
            if (!indexToNode.containsKey(index1) || !indexToNode.containsKey(index2)
                    || newValue != indexToNode.get(index1).getValue() + indexToNode.get(index2).getValue())
                continue;

            removals++;
            Node node1 = indexToNode.get(index1), node2 = indexToNode.get(index2);
            if (node1.getValue() > node2.getValue())
                reversePairs--;

            if (node1.getPrev().getIndex() >= 0 && node1.getPrev().getValue() > node1.getValue())
                reversePairs--;

            if (node2.getNext().getIndex() >= 0 && node2.getNext().getValue() < node2.getValue())
                reversePairs--;

            node1.setValue(newValue);
            remove(node2);
            indexToNode.remove(index2);

            if (node1.getPrev().getIndex() >= 0) {
                pq.offer(new long[] { node1.getPrev().getValue() + node1.getValue(), node1.getPrev().getIndex(),
                        node1.getIndex() });
                if (node1.getPrev().getValue() > node1.getValue())
                    reversePairs++;

            }

            if (node1.getNext().getIndex() >= 0) {
                pq.offer(new long[] { node1.getValue() + node1.getNext().getValue(), node1.getIndex(),
                        node1.getNext().getIndex() });
                if (node1.getNext().getValue() < node1.getValue())
                    reversePairs++;

            }
        }

        return removals;
    }

    public void remove(Node node) {
        Node prev = node.getPrev(), next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
    }
}
// @lc code=end
