/*
 * @lc app=leetcode.cn id=468 lang=java
 *
 * [468] 验证IP地址
 *
 * https://leetcode.cn/problems/validate-ip-address/description/
 *
 * algorithms
 * Medium (28.17%)
 * Likes:    218
 * Dislikes: 0
 * Total Accepted:    58.3K
 * Total Submissions: 207K
 * Testcase Example:  '"172.16.254.1"'
 *
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6"
 * ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 * 
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如:
 * “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址;
 * “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 * 
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * 
 * 
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 
 * 
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和
 * "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而
 * "2001:0db8:85a3::8A2E:037j:7334" 和
 * "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final String IPv6_CHARS = "0123456789abcdefABCDEF";

    public String validIPAddress(String queryIP) {
        boolean isIPv4 = queryIP.indexOf(".") >= 0, isIPv6 = queryIP.indexOf(":") >= 0;
        if (isIPv4 && !isIPv6) {
            if (checkIPv4(queryIP))
                return "IPv4";
        } else if (isIPv6 && !isIPv4) {
            if (checkIPv6(queryIP))
                return "IPv6";
        }
        return "Neither";
    }

    private boolean checkIPv4(String ip) {
        String[] splits = ip.split("\\.");
        if (splits.length != 4 || ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.')
            return false;
        for (String s : splits) {
            if (s.length() > 3 || s.length() == 0 || (s.length() > 1 && s.charAt(0) == '0'))
                return false;

            int cur = 0;
            for (int i = 0; i < s.length(); i++) {
                if ('0' <= s.charAt(i) && s.charAt(i) <= '9')
                    cur = 10 * cur + s.charAt(i) - '0';
                else
                    return false;
            }

            if (cur > 255)
                return false;
        }

        return true;
    }

    private boolean checkIPv6(String ip) {
        String[] splits = ip.split("\\:");
        if (splits.length != 8 || ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':')
            return false;

        for (String s : splits) {
            if (s.length() > 4 || s.length() == 0)
                return false;

            for (int i = 0; i < s.length(); i++) {
                if (IPv6_CHARS.indexOf(s.charAt(i)) == -1)
                    return false;
            }
        }

        return true;
    }
}
// @lc code=end
