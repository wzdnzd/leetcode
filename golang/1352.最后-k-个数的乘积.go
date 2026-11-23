/*
 * @lc app=leetcode.cn id=1352 lang=golang
 *
 * [1352] 最后 K 个数的乘积
 *
 * https://leetcode.cn/problems/product-of-the-last-k-numbers/description/
 *
 * algorithms
 * Medium (52.65%)
 * Likes:    137
 * Dislikes: 0
 * Total Accepted:    21.4K
 * Total Submissions: 40.1K
 * Testcase Example:  '["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]\n' +
  '[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]'
 *
 * 设计一个算法，该算法接受一个整数流并检索该流中最后 k 个整数的乘积。
 *
 * 实现 ProductOfNumbers 类：
 *
 *
 * ProductOfNumbers() 用一个空的流初始化对象。
 * void add(int num) 将数字 num 添加到当前数字列表的最后面。
 * int getProduct(int k) 返回当前数字列表中，最后 k 个数字的乘积。你可以假设当前列表中始终 至少 包含 k 个数字。
 *
 *
 * 题目数据保证：任何时候，任一连续数字序列的乘积都在 32 位整数范围内，不会溢出。
 *
 *
 *
 * 示例：
 *
 *
 * 输入：
 *
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 *
 * 输出：
 * [null,null,null,null,null,null,20,40,0,null,32]
 *
 * 解释：
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();
 * productOfNumbers.add(3);        // [3]
 * productOfNumbers.add(0);        // [3,0]
 * productOfNumbers.add(2);        // [3,0,2]
 * productOfNumbers.add(5);        // [3,0,2,5]
 * productOfNumbers.add(4);        // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
 * productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
 * productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]
 * productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32
 *
 *
 *
 *
 * 提示：
 *
 *
 * 0 <= num <= 100
 * 1 <= k <= 4 * 10^4
 * add 和 getProduct 最多被调用 4 * 10^4 次。
 * 在任何时间点流的乘积都在 32 位整数范围内。
 *
 *
 *
 *
 * 进阶：您能否 同时 将 GetProduct 和 Add 的实现改为 O(1) 时间复杂度，而不是 O(k) 时间复杂度？
 *
*/

// @lc code=start
package main

type ProductOfNumbers struct {
	prefixProducts []int
}

func Constructor() ProductOfNumbers {
	return ProductOfNumbers{
		prefixProducts: []int{1},
	}
}

func (this *ProductOfNumbers) Add(num int) {
	if num == 0 {
		this.prefixProducts = []int{1}
	} else {
		last := this.prefixProducts[len(this.prefixProducts)-1]
		this.prefixProducts = append(this.prefixProducts, num*last)
	}
}

func (this *ProductOfNumbers) GetProduct(k int) int {
	n := len(this.prefixProducts)
	if n <= k {
		return 0
	}

	return this.prefixProducts[n-1] / this.prefixProducts[n-1-k]
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(num);
 * param_2 := obj.GetProduct(k);
 */
// @lc code=end
