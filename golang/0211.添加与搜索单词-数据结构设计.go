/*
 * @lc app=leetcode.cn id=211 lang=golang
 *
 * [211] 添加与搜索单词 - 数据结构设计
 *
 * https://leetcode.cn/problems/design-add-and-search-words-data-structure/description/
 *
 * algorithms
 * Medium (50.29%)
 * Likes:    588
 * Dislikes: 0
 * Total Accepted:    97.5K
 * Total Submissions: 193.3K
 * Testcase Example:  '["WordDictionary","addWord","addWord","addWord","search","search","search","search"]\n' +
  '[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]'
 *
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些
 * '.' ，每个 . 都可以表示任何一个字母。
 *
 *
 *
 *
 * 示例：
 *
 *
 * 输入：
 *
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // 返回 False
 * wordDictionary.search("bad"); // 返回 True
 * wordDictionary.search(".ad"); // 返回 True
 * wordDictionary.search("b.."); // 返回 True
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= word.length <= 25
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 10^4 次 addWord 和 search
 *
 *
*/

// @lc code=start
package main

type WordDictionary struct {
	Next  map[byte]*WordDictionary
	IsEnd bool
}

func Constructor() WordDictionary {
	return WordDictionary{Next: make(map[byte]*WordDictionary)}
}

func (w *WordDictionary) AddWord(word string) {
	node := w

	for i := 0; i < len(word); i++ {
		if _, ok := node.Next[word[i]]; !ok {
			node.Next[word[i]] = &WordDictionary{Next: make(map[byte]*WordDictionary)}
		}

		node = node.Next[word[i]]
	}

	node.IsEnd = true
}

func (w *WordDictionary) Search(word string) bool {
	var dfs func(string, *WordDictionary) bool
	dfs = func(subWord string, node *WordDictionary) bool {
		if node == nil {
			return false
		}

		if len(subWord) == 0 {
			return node.IsEnd
		}

		if subWord[0] == '.' {
			for _, nextNode := range node.Next {
				if dfs(subWord[1:], nextNode) {
					return true
				}
			}
		} else {
			if dfs(subWord[1:], node.Next[subWord[0]]) {
				return true
			}
		}

		return false
	}

	return dfs(word, w)
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddWord(word);
 * param_2 := obj.Search(word);
 */
// @lc code=end
