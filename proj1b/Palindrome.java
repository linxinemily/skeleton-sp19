public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> char_deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char_deque.addLast(word.charAt(i));
        }
        return char_deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> word_deque = wordToDeque(word);
        return checkPalindrome(word_deque);
    }

    private boolean checkPalindrome(Deque<Character> word_deque)
    {
        if (word_deque.size() < 2) {
            return true;
        }
        Character first =  word_deque.removeFirst();
        Character last =  word_deque.removeLast();
        if (!first.equals(last)) {
            return false;
        }
        return checkPalindrome(word_deque);
    }
}