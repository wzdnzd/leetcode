/*
 * @lc app=leetcode.cn id=721 lang=java
 *
 * [721] 账户合并
 *
 * https://leetcode.cn/problems/accounts-merge/description/
 *
 * algorithms
 * Medium (48.88%)
 * Likes:    498
 * Dislikes: 0
 * Total Accepted:    44.5K
 * Total Submissions: 90.4K
 * Testcase Example:  '[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]'
 *
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称
 * (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 
 * 
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。账户本身可以以 任意顺序
 * 返回。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John",
 * "johnnybravo@mail.com"], ["John", "johnsmith@mail.com",
 * "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：[["John", 'john00@mail.com', 'john_newyork@mail.com',
 * 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary",
 * "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案
 * [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']]
 * 也是正确的。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：accounts =
 * [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * 
 * 输出：[["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] 由英文字母组成
 * accounts[i][j] (for j > 0) 是有效的邮箱地址
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> names = new HashMap<>();
        Map<String, Integer> indices = new HashMap<>();

        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();

            for (int i = 1; i < size; i++) {
                String email = account.get(i);

                if (!indices.containsKey(email)) {
                    names.put(email, name);
                    indices.put(email, emailsCount);
                    emailsCount++;
                }
            }
        }

        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            int firstIndex = indices.get(account.get(1));
            int size = account.size();

            for (int i = 2; i < size; i++) {
                int nextIndex = indices.get(account.get(i));
                uf.union(firstIndex, nextIndex);
            }
        }

        Map<Integer, String> rootsNames = new HashMap<>();
        Map<Integer, List<String>> rootsEmailsList = new HashMap<>();
        Set<Map.Entry<String, Integer>> entries = indices.entrySet();

        for (Map.Entry<String, Integer> entry : entries) {
            String email = entry.getKey();
            String name = names.get(email);

            int index = entry.getValue();
            int root = uf.find(index);

            rootsNames.putIfAbsent(root, name);
            rootsEmailsList.putIfAbsent(root, new ArrayList<>());
            rootsEmailsList.get(root).add(email);
        }

        List<List<String>> merged = new ArrayList<>();
        Set<Integer> roots = rootsNames.keySet();

        for (int root : roots) {
            List<String> account = new ArrayList<>();
            String name = rootsNames.get(root);
            List<String> emails = rootsEmailsList.get(root);

            Collections.sort(emails);

            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }

        return merged;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        this.parent = new int[n];

        for (int i = 0; i < n; i++)
            this.parent[i] = i;

        this.rank = new int[n];
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);

        if (rootx != rooty) {
            if (this.rank[rootx] > this.rank[rooty])
                this.parent[rooty] = rootx;
            else if (this.rank[rootx] < this.rank[rooty])
                this.parent[rootx] = rooty;
            else {
                this.parent[rooty] = rootx;
                this.rank[rootx]++;
            }
        }
    }

    public int find(int x) {
        if (this.parent[x] != x)
            this.parent[x] = find(this.parent[x]);

        return this.parent[x];
    }
}
// @lc code=end
