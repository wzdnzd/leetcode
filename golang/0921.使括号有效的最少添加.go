package main

func minAddToMakeValid(s string) int {
	count, left := 0, 0
	for _, c := range s {
		if c == '(' {
			left++
		} else if left > 0 {
			left--
		} else {
            count++
		}
	}

	return count + left
}
