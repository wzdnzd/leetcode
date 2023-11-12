/*
 * @lc app=leetcode.cn id=1089 lang=java
 *
 * [1089] 复写零
 *
 * https://leetcode.cn/problems/duplicate-zeros/description/
 *
 * algorithms
 * Easy (59.02%)
 * Likes:    265
 * Dislikes: 0
 * Total Accepted:    61.5K
 * Total Submissions: 104.4K
 * Testcase Example:  '[1,0,2,3,0,4,5,0]'
 *
 * 给你一个长度固定的整数数组 arr ，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 
 * 注意：请不要在超过该数组长度的位置写入元素。请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：arr = [1,0,2,3,0,4,5,0]
 * 输出：[1,0,0,2,3,0,0,4]
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * 
 * 示例 2：
 * 
 * 
 * 输入：arr = [1,2,3]
 * 输出：[1,2,3]
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] nums = new int[n];
        int i = 0, j = 0;

        while (i < n && j < n) {
            nums[j++] = arr[i];
            if (arr[i++] == 0 && j < n)
                nums[j++] = 0;
        }

        for (i = 0; i < n; i++)
            arr[i] = nums[i];
    }
}
// @lc code=end
