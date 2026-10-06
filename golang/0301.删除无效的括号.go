package main

func isValid(s string) bool {
    count := 0
    for _, c := range s {
        if c == '(' {
            count++
        } else if c == ')' {
            count--
            if count < 0 {
                return false
            }
        }
    }
    
    return count == 0
}

func helper(result *[]string, word string, start, left, right int) {
    if left == 0 && right == 0 {
        if isValid(word) {
            *result = append(*result, word)
        }
        
        return
    }

    for i := start; i < len(word); i++ {
        if i != start && word[i] == word[i-1] {
            continue
        }
        
        if left+right > len(word)-i {
            return
        }
        
        if left > 0 && word[i] == '(' {
            helper(result, word[:i]+word[i+1:], i, left-1, right)
        }
        
        if right > 0 && word[i] == ')' {
            helper(result, word[:i]+word[i+1:], i, left, right-1)
        }
    }
}

func removeInvalidParentheses(s string) []string {
    left, right := 0, 0
    result := make([]string, 0)
    
    for _, c := range s {
        if c == '(' {
            left++
        } else if c == ')' {
            if left == 0 {
                right++
            } else {
                left--
            }
        }
    }

    helper(&result, s, 0, left, right)
    return result
}
