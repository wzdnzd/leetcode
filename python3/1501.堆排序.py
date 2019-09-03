def heap_sort(array):
    def build_heap(nums, start, end):
        pivot = nums[start]
        index = 2 * start + 1

        while index < end:
            if index + 1 < end and nums[index] < nums[index + 1]:
                index += 1

            if pivot > nums[index]:
                break

            nums[start] = nums[index]
            start = index
            index = 2 * index + 1

        nums[start] = pivot

    for i in range(len(array) // 2 - 1, -1, -1):
        build_heap(array, i, len(array))
    for i in range(len(array) - 1, 0, -1):
        array[0], array[i] = array[i], array[0]
        build_heap(array, 0, i)

    return array
