import java.util.Objects;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> L = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            L.addLast(word.charAt(i));
        }
        return L;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> L = wordToDeque(word);
        return isPalindromeHelper(L);
    }

    public boolean isPalindromeHelper(Deque<Character> L) {
        int n = L.size();
        if (n == 0 || n == 1) {
            return true;
        }
        if (L.removeFirst() == L.removeLast()) {
            return isPalindromeHelper(L);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int n = word.length();
        int pos = 0;
        while (pos < n - 1 - pos) {
            boolean or = cc.equalChars(word.charAt(pos), word.charAt(n - 1 - pos));
            if (!or) {
                break;
            }
            pos = pos + 1;
        }
        if (pos >= n - 1 - pos) {
            return true;
        }
        return false;
    }

}
