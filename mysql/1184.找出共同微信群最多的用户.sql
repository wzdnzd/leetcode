--
-- @lc app=leetcode.cn id=1184 lang=mysql
--
-- [1184] 共同微信群最多的用户
--
-- database
--
-- 已知 用户-微信群 关系表(1亿用户量)，给定一个用户id=1234，使用SQL找出与该用户共同微信群最多的top3个用户
-- 
-- t 表：
-- 
-- +-----------+-----------+
-- | user_id  | wxgroup_id |
-- +-----------+-----------+
-- | 1234    |  38809303   |
-- | 2645    |  58675756   |
-- | 3868    |  85686935   |
-- | 1234    |  58675756   |
-- +-----------+-----------+
-- 
-- 
-- 
--
# Write your MySQL query statement below
SELECT t1.user_id user_id, COUNT(*) cnt
FROM t t1
JOIN (SELECT * FROM t WHERE t.user_id=1234) t2 ON t1.wxgroup_id=t2.wxgroup_id AND t1.user_id != t2.user_id
GROUP BY t1.user_id
ORDER BY cnt DESC
LIMIT 3

