package assignment04.csc214.texteditor;

/**
 * Created by Nate on 2/16/17.
 */

public class FontModel {

    private static FontModel INSTANCE;

    private FontModel() {
    }

    public static FontModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FontModel();
        }
        return INSTANCE;
    }

    // style
    boolean bold = false;
    boolean italic = false;
    boolean underline = false;

    // color
    String fontColor = "black";

    // size
    int fontSize = 20;

    // sentence
    String sentence = "Pied Piper is an integrated, multi-platform data compression solution featuring the revolutionary 'middle-out' algorithm.";

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public String getFontColor() {
        return fontColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getSentence() {
        return sentence;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontSize(int fontSize) {
        // max the font size at 40
        if(fontSize < 40) {
            this.fontSize = fontSize;
        }
        else{
            this.fontSize = 40;
        }
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
