public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> char_deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char_deque.addLast(word.charAt(i));
        }
        return char_deque;
    }

    public boolean isPalindrome(String word) {
        class basicComparator implements CharacterComparator {
            @Override
            public boolean equalChars(char x, char y) {
                return x == y;
            }
        }
        return isPalindrome(word, new basicComparator());
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> word_deque = wordToDeque(word);
        return checkPalindrome(word_deque, cc);
    }

    private boolean checkPalindrome(Deque<Character> word_deque, CharacterComparator cc)
    {
        if (word_deque.size() < 2) {
            return true;
        }
        Character first = word_deque.removeFirst();
        Character last = word_deque.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return checkPalindrome(word_deque, cc);
    }
}