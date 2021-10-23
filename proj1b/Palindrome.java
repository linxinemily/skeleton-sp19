public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    public boolean isPalindrome(String word) {
        class BasicComparator implements CharacterComparator {
            @Override
            public boolean equalChars(char x, char y) {
                return x == y;
            }
        }
        return isPalindrome(word, new BasicComparator());
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return checkPalindrome(wordDeque, cc);
    }

    private boolean checkPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() < 2) {
            return true;
        }
        Character first = wordDeque.removeFirst();
        Character last = wordDeque.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return checkPalindrome(wordDeque, cc);
    }
}
