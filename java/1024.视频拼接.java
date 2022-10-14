import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=1024 lang=java
 *
 * [1024] 视频拼接
 *
 * https://leetcode.cn/problems/video-stitching/description/
 *
 * algorithms
 * Medium (53.78%)
 * Likes:    296
 * Dislikes: 0
 * Total Accepted:    41.5K
 * Total Submissions: 77.1K
 * Testcase Example:  '[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]\n10'
 *
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 
 * 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于
 * endi 结束。
 * 
 * 甚至可以对这些片段自由地再剪辑：
 * 
 * 
 * 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 
 * 
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回
 * -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
 * 输出：3
 * 解释：
 * 选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：clips = [[0,1],[1,2]], time = 5
 * 输出：-1
 * 解释：
 * 无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：clips =
 * [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]],
 * time = 9
 * 输出：3
 * 解释： 
 * 选取片段 [0,4], [4,7] 和 [6,9] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= clips.length <= 100
 * 0 <= starti <= endi <= 100
 * 1 <= time <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (c1, c2) -> {
            int c = c1[0] - c2[0];
            if (c == 0)
                c = c1[1] - c2[1];
            return c;
        });

        int index = 0, length = clips.length, ans = 0;
        int currentEnd = 0, nextEnd = 0;
        while (index < length && clips[index][0] <= currentEnd) {
            while (index < length && clips[index][0] <= currentEnd) {
                nextEnd = Math.max(nextEnd, clips[index][1]);
                index++;
            }

            ans++;
            currentEnd = nextEnd;
            if (currentEnd >= time)
                return ans;
        }

        return -1;
    }
}
// @lc code=end
