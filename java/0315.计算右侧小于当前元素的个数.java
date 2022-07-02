import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=315 lang=java
 *
 * [315] 计算右侧小于当前元素的个数
 *
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/description/
 *
 * algorithms
 * Hard (42.49%)
 * Likes:    841
 * Dislikes: 0
 * Total Accepted:    65K
 * Total Submissions: 153K
 * Testcase Example:  '[5,2,6,1]'
 *
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i]
 * 右侧小于 nums[i] 的元素的数量。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0] 
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [-1]
 * 输出：[0]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int length = nums.length;
        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }

        List<Integer> counts = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            counts.add(0);
        }

        mergeSortAndCount(nums, indices, counts, 0, length - 1);
        return counts;
    }

    public void mergeSortAndCount(int[] nums, int[] indices, List<Integer> counts, int low, int high) {
        if (low >= high)
            return;

        int mid = low + (high - low) / 2;
        mergeSortAndCount(nums, indices, counts, low, mid);
        mergeSortAndCount(nums, indices, counts, mid + 1, high);
        merge(nums, indices, counts, low, mid, high);
    }

    public void merge(int[] nums, int[] indices, List<Integer> counts, int low, int mid, int high) {
        int currLength = high - low + 1;
        int[] tempNums = new int[currLength];
        int[] tempIndices = new int[currLength];
        int i = low, j = mid + 1, k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                tempNums[k] = nums[i];
                tempIndices[k] = indices[i];
                counts.set(indices[i], counts.get(indices[i]) + (j - mid - 1));
                i++;
            } else {
                tempNums[k] = nums[j];
                tempIndices[k] = indices[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            tempNums[k] = nums[i];
            tempIndices[k] = indices[i];
            counts.set(indices[i], counts.get(indices[i]) + (j - mid - 1));
            i++;
            k++;
        }

        while (j <= high) {
            tempNums[k] = nums[j];
            tempIndices[k] = indices[j];
            j++;
            k++;
        }

        System.arraycopy(tempNums, 0, nums, low, currLength);
        System.arraycopy(tempIndices, 0, indices, low, currLength);
    }
}
// @lc code=end
