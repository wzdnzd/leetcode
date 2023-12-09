--
-- @lc app=leetcode.cn id=1587 lang=mysql
--
-- [1587] 银行账户概要 II
--
-- https://leetcode.cn/problems/bank-account-summary-ii/description/
--
-- database
-- Easy (79.45%)
-- Likes:    42
-- Dislikes: 0
-- Total Accepted:    33.5K
-- Total Submissions: 42.2K
-- Testcase Example:  '{"headers": {"Users": ["account", "name"], "Transactions": ["trans_id", "account", "amount", "transacted_on"]}, "rows": {"Users": [[900001, "Alice"], [900002, "Bob"], [900003, "Charlie"]], "Transactions": [[1, 900001, 7000, "2020-08-01"], [2, 900001, 7000, "2020-09-01"], [3, 900001, -3000, "2020-09-02"], [4, 900002, 1000, "2020-09-12"], [5, 900003, 6000, "2020-08-07"], [6, 900003, 6000, "2020-09-07"], [7, 900003, -4000, "2020-09-11"]]}}'
--
-- 表: Users
-- 
-- 
-- +--------------+---------+
-- | Column Name  | Type    |
-- +--------------+---------+
-- | account      | int     |
-- | name         | varchar |
-- +--------------+---------+
-- account 是该表的主键(具有唯一值的列)。
-- 该表的每一行都包含银行中每个用户的帐号。
-- 表中不会有两个用户具有相同的名称。
-- 
-- 
-- 
-- 
-- 表: Transactions
-- 
-- 
-- +---------------+---------+
-- | Column Name   | Type    |
-- +---------------+---------+
-- | trans_id      | int     |
-- | account       | int     |
-- | amount        | int     |
-- | transacted_on | date    |
-- +---------------+---------+
-- trans_id 是该表主键(具有唯一值的列)。
-- 该表的每一行包含了所有账户的交易改变情况。
-- 如果用户收到了钱, 那么金额是正的; 如果用户转了钱, 那么金额是负的。
-- 所有账户的起始余额为 0。
-- 
-- 
-- 
-- 
-- 编写解决方案,  报告余额高于 10000 的所有用户的名字和余额. 账户的余额等于包含该账户的所有交易的总和。
-- 
-- 返回结果表单 无顺序要求 。
-- 
-- 查询结果格式如下例所示。
-- 
-- 
-- 
-- 示例 1：
-- 
-- 
-- 输入：
-- Users table:
-- +------------+--------------+
-- | account    | name         |
-- +------------+--------------+
-- | 900001     | Alice        |
-- | 900002     | Bob          |
-- | 900003     | Charlie      |
-- +------------+--------------+
-- 
-- Transactions table:
-- +------------+------------+------------+---------------+
-- | trans_id   | account    | amount     | transacted_on |
-- +------------+------------+------------+---------------+
-- | 1          | 900001     | 7000       |  2020-08-01   |
-- | 2          | 900001     | 7000       |  2020-09-01   |
-- | 3          | 900001     | -3000      |  2020-09-02   |
-- | 4          | 900002     | 1000       |  2020-09-12   |
-- | 5          | 900003     | 6000       |  2020-08-07   |
-- | 6          | 900003     | 6000       |  2020-09-07   |
-- | 7          | 900003     | -4000      |  2020-09-11   |
-- +------------+------------+------------+---------------+
-- 输出：
-- +------------+------------+
-- | name       | balance    |
-- +------------+------------+
-- | Alice      | 11000      |
-- +------------+------------+
-- 解释：
-- Alice 的余额为(7000 + 7000 - 3000) = 11000.
-- Bob 的余额为1000.
-- Charlie 的余额为(6000 + 6000 - 4000) = 8000.
-- 
-- 
--

-- @lc code=start
# Write your MySQL query statement below
SELECT u.name, SUM(t.amount) AS balance FROM Users u, Transactions t WHERE u.account = t.account GROUP BY u.account HAVING balance > 10000;
-- @lc code=end

