class Solution {
    private static int[] sort(int[] array, int low, int high) {
        if (low >= high)
            return array;
        int mid = (low + high) / 2;

        sort(array, low, mid);
        sort(array, mid + 1, high);

        return merge(array, low, mid, high);
    }

    private static int[] merge(int[] array, int low, int mid, int high) {
        int[] nums = new int[high - low + 1];
        int i = low, j = mid + 1, index = 0;
        while (i <= mid && j <= high) {
            if (array[i] <= array[j])
                nums[index++] = array[i++];
            else
                nums[index++] = array[j++];
        }

        while (i <= mid)
            nums[index++] = array[i++];
        while (j <= high)
            nums[index++] = array[j++];

        System.arraycopy(nums, 0, array, low, nums.length);
        return nums;
    }

    public static int[] mergeSort(int[] array) {
        if (array == null || array.length == 0)
            return array;
        return sort(array, 0, array.length - 1);
    }
}
