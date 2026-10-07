package main

func removeOuterParentheses(s string) string {
    var chars, remains []rune
    for _, c := range s {
        if c == ')' {
            remains = remains[:len(remains)-1]
        }
        
        if len(remains) > 0 {
            chars = append(chars, c)
        }
        
        if c == '(' {
            remains = append(remains, c)
        }
    }
    
    return string(chars)
}
