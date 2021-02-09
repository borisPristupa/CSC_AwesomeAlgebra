import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

fun testPalindromes() {
    assertEquals("", maxPalindromeSubsequence(""))
    assertEquals("a", maxPalindromeSubsequence("a"))

    assertEquals("aa", maxPalindromeSubsequence("aa"))
    assertEquals("aa", maxPalindromeSubsequence("aab"))
    assertEquals("aa", maxPalindromeSubsequence("baa"))

    assert(maxPalindromeSubsequence("ab") in listOf("a", "b"))

    assertEquals("aba", maxPalindromeSubsequence("aba"))
    assertEquals("aba", maxPalindromeSubsequence("alba"))
    assert(maxPalindromeSubsequence("abla") in listOf("aba", "ala"))
    assertEquals("aba", maxPalindromeSubsequence("abal"))
    assertEquals("aba", maxPalindromeSubsequence("laba"))
    assertEquals("aba", maxPalindromeSubsequence("labac"))
    assertEquals("aba", maxPalindromeSubsequence("labacc"))
    assert(maxPalindromeSubsequence("lacbac") in listOf("aba", "cbc"))

    assertEquals("abba", maxPalindromeSubsequence("labbacc"))
    assert(maxPalindromeSubsequence("a blablacar") in listOf("ababa", "alala"))
}

fun testLcs() {
    assertEquals(0, lcs("", ""))
    assertEquals(0, lcs("abc", ""))
    assertEquals(0, lcs("", "abc"))

    assertEquals(1, lcs("a", "abc"))
    assertEquals(1, lcs("b", "abc"))
    assertEquals(1, lcs("c", "abc"))

    assertEquals(2, lcs("bac", "abc"))
    assertEquals(2, lcs("cab", "abc"))
    assertEquals(1, lcs("cba", "abc"))

    assertEquals(4, lcs("abcabcd", "bcbca"))
}


fun main() {
    testPalindromes()
    testLcs()

    println(lcsConstant(12, 30, TimeUnit.SECONDS))
}