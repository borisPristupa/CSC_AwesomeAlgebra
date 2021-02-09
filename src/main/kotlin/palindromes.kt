fun maxPalindromeSubsequence(s: String): String {
    if (s.length < 2) return s

    val dyn = Array(s.length + 1) { lm1 -> Array(s.length - lm1 + 1) { 1 } }

    for (l in 2..s.length) {
        for (i in 0..(s.length - l)) {
            if (l > 2) {
                dyn[l][i] = maxOf(
                    dyn[l - 1][i],
                    dyn[l - 1][i + 1],
                    dyn[l - 2][i + 1] + if (s[i] == s[i + l - 1]) 2 else 0
                )
            } else {
                dyn[2][i] = if (s[i] == s[i + 1]) 2 else 1
            }
        }
    }

    var tail = ""
    var i = 0
    var l = s.length

    while (l > 1) {
        if (s[i] == s[i + l - 1]) {
            tail += s[i++]
            l -= 2
            continue
        }

        if (dyn[l - 1][i + 1] == dyn[l][i]) {
            i++
        }
        l--
    }

    val mid = if (l == 1) s[i] else ""
    return tail + mid + tail.reversed()
}