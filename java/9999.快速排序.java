class Solution {
    public static int[] quickSort(int[] array) {
        if (array == null || array.length == 0)
            return array;

        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int start, int end) {
        if (start >= end)
            return array;

        int left = start, right = end;
        int pivot = array[start];

        while (left < right) {
            while (left < right && array[right] >= pivot)
                right--;
            while (left < right && array[left] <= pivot)
                left++;

            swap(array, left, right);
        }

        swap(array, start, left);
        sort(array, start, left - 1);
        sort(array, right + 1, end);

        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int v = array[i];
        array[i] = array[j];
        array[j] = v;
    }
}
