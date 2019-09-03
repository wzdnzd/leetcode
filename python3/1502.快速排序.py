def quick_sort(array):
    def qsort(nums, start, end):
        if start >= end:
            return nums

        value = nums[start]
        left, right = start, end
        while left < right:
            while left < right and nums[right] <= value:
                right -= 1
            while left < right and nums[left] >= value:
                left += 1
            nums[left], nums[right] = nums[right], nums[left]

        nums[start], nums[left] = nums[left], nums[start]

        qsort(nums, start, left - 1)
        qsort(nums, right + 1, end)

        return nums

    return qsort(array, 0, len(array) - 1)
