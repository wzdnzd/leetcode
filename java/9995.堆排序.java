class Solution {
    private static void swap(int[] nums, int i, int j) {
        int v = nums[i];
        nums[i] = nums[j];
        nums[j] = v;
    }

    private static void heapify(int[] nums, int start, int end) {
        int pivot = nums[start], index = 2 * start + 1;

        while (index < end) {
            if (index + 1 < end && nums[index] < nums[index + 1])
                index++;
            if (nums[index] <= pivot)
                break;

            nums[start] = nums[index];
            start = index;
            index = index * 2 + 1;
        }

        nums[start] = pivot;
    }

    public static int[] heapSort(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;

        int length = nums.length;
        for (int i = length / 2 - 1; i >= 0; i--)
            heapify(nums, i, length);
        for (int i = length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }

        return nums;
    }
}