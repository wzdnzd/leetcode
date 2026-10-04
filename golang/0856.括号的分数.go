package main

func scoreOfParentheses(s string) int {
	depth, result := 0, 0
	for i, c := range s {
		if c == '(' {
			depth++
		} else {
			depth--
			if s[i-1] == '(' {
				result += 1 << depth
			}
		}
	}
    
	return result
}
