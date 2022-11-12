package michael.vocabflash.Model;

import java.io.Serializable;

public class Vocab implements Serializable {
    private String word;

    public Vocab(String word) {
        this.word = word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
