public class Dictionary {
    Word[] words = new Word[1];
    String[] targets = new String[60000];
    int count = 0;
    public void addWord(String target, String explain){
        if(count == words.length){
            resize(2*words.length);
        }
        words[count] = new Word(target, explain);
        targets[count] = target;
        count++;
    }

    public void resize(int capacity){
        Word[] copy = new Word[capacity];
        if (count >= 0) System.arraycopy(words, 0, copy, 0, count);
        words = copy;
    }
}

