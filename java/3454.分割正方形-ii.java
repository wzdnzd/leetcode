/*
 * @lc app=leetcode.cn id=3454 lang=java
 *
 * [3454] 分割正方形 II
 *
 * https://leetcode.cn/problems/separate-squares-ii/description/
 *
 * algorithms
 * Hard (37.83%)
 * Likes:    8
 * Dislikes: 0
 * Total Accepted:    1.9K
 * Total Submissions: 4K
 * Testcase Example:  '[[0,0,1],[2,2,1]]'
 *
 * 给你一个二维整数数组 squares ，其中 squares[i] = [xi, yi, li] 表示一个与 x
 * 轴平行的正方形的左下角坐标和正方形的边长。
 * 
 * 找到一个最小的 y 坐标，它对应一条水平线，该线需要满足它以上正方形的总面积 等于 该线以下正方形的总面积。
 * 
 * 答案如果与实际答案的误差在 10^-5 以内，将视为正确答案。
 * 
 * 注意：正方形 可能会 重叠。重叠区域只 统计一次 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： squares = [[0,0,1],[2,2,1]]
 * 
 * 输出： 1.00000
 * 
 * 解释：
 * 
 * 
 * 
 * 任何在 y = 1 和 y = 2 之间的水平线都会有 1 平方单位的面积在其上方，1 平方单位的面积在其下方。最小的 y 坐标是 1。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入： squares = [[0,0,2],[1,1,1]]
 * 
 * 输出： 1.00000
 * 
 * 解释：
 * 
 * 
 * 
 * 由于蓝色正方形和红色正方形有重叠区域且重叠区域只统计一次。所以直线 y = 1 将正方形分割成两部分且面积相等。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= squares.length <= 5 * 10^4
 * squares[i] = [xi, yi, li]
 * squares[i].length == 3
 * 0 <= xi, yi <= 10^9
 * 1 <= li <= 10^9
 * 所有正方形的总面积不超过 10^15。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public double separateSquares(int[][] squares) {
        Set<Integer> set = new HashSet<>();
        for (int[] square : squares) {
            set.add(square[0]);
            set.add(square[0] + square[2]);
        }

        List<Integer> xValues = new ArrayList<>(set);
        xValues.sort((a, b) -> a - b);

        SegmentTree tree = new SegmentTree(xValues);
        List<int[]> yValues = new ArrayList<>();
        for (int i = 0; i < squares.length; i++) {
            yValues.add(new int[] { squares[i][1], i, 1 });
            yValues.add(new int[] { squares[i][1] + squares[i][2], i, -1 });
        }

        yValues.sort((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            else if (a[1] != b[1])
                return a[1] - b[1];
            else
                return a[2] - b[2];
        });

        long totalArea = 0;
        List<Long> areas = new ArrayList<>();
        List<int[]> intervals = new ArrayList<>();
        areas.add(0L);
        intervals.add(new int[] { -1, -1 });

        int yValuesCount = yValues.size();
        int yValuesIndex = 0;

        while (yValuesIndex < yValuesCount) {
            int prev = yValuesIndex;
            while (yValuesIndex < yValuesCount - 1
                    && yValues.get(yValuesIndex)[0] == yValues.get(yValuesIndex + 1)[0])
                yValuesIndex++;

            if (yValuesIndex < yValuesCount - 1) {
                for (int i = prev; i <= yValuesIndex; i++) {
                    int[] arr = yValues.get(i);
                    int index = arr[1], delta = arr[2];
                    int start = binarySearch(xValues, squares[index][0]) + 1;
                    int end = binarySearch(xValues, squares[index][0] + squares[index][2]);
                    tree.update(0, delta, start, end);
                }

                int[] interval = { yValues.get(yValuesIndex)[0], yValues.get(yValuesIndex + 1)[0] };
                long currArea = (long) tree.getLength() * (interval[1] - interval[0]);
                totalArea += currArea;
                areas.add(totalArea);
                intervals.add(interval);
            }

            yValuesIndex++;
        }

        double halfArea = totalArea / 2.0;
        int areaIndex = binarySearch(areas, halfArea);
        double areaDiff = areas.get(areaIndex) - halfArea;
        double ratio = areaDiff / (areas.get(areaIndex) - areas.get(areaIndex - 1));
        int[] targetInterval = intervals.get(areaIndex);

        return targetInterval[1] - (targetInterval[1] - targetInterval[0]) * ratio;
    }

    private <T extends Number & Comparable<T>> int binarySearch(List<T> list, Number target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid).doubleValue() >= target.doubleValue())
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}

class SegmentTree {
    private int n;
    private int[] cover;
    private int[] length;
    private int[] maxLength;

    public SegmentTree(List<Integer> nums) {
        this.n = nums.size();
        this.cover = new int[n * 4 + 1];
        this.length = new int[n * 4 + 1];
        this.maxLength = new int[n * 4 + 1];
        build(0, n - 2, 0, nums);
    }

    public int getLength() {
        return length[0];
    }

    public void update(int index, int delta, int start, int end) {
        update(index, delta, start, end, 1, n - 1);
    }

    private void build(int start, int end, int treeIndex, List<Integer> nums) {
        if (start == end) {
            maxLength[treeIndex] = nums.get(start + 1) - nums.get(start);
            return;
        }

        int mid = start + (end - start) / 2;
        build(start, mid, treeIndex * 2 + 1, nums);
        build(mid + 1, end, treeIndex * 2 + 2, nums);
        maxLength[treeIndex] = maxLength[treeIndex * 2 + 1] + maxLength[treeIndex * 2 + 2];
    }

    private void update(int index, int delta, int rangeStart, int rangeEnd, int treeStart, int treeEnd) {
        if (treeStart > rangeEnd || treeEnd < rangeStart)
            return;

        if (treeStart >= rangeStart && treeEnd <= rangeEnd) {
            cover[index] += delta;
            updateLength(index, treeStart, treeEnd);
            return;
        }

        int mid = treeStart + (treeEnd - treeStart) / 2;
        update(index * 2 + 1, delta, rangeStart, rangeEnd, treeStart, mid);
        update(index * 2 + 2, delta, rangeStart, rangeEnd, mid + 1, treeEnd);
        updateLength(index, treeStart, treeEnd);
    }

    private void updateLength(int index, int treeStart, int treeEnd) {
        if (cover[index] > 0)
            length[index] = maxLength[index];
        else if (treeStart == treeEnd)
            length[index] = 0;
        else
            length[index] = length[index * 2 + 1] + length[index * 2 + 2];
    }
}
// @lc code=end
