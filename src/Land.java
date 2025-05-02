public class Land extends Entities{
    public Land(){
        this.emoji = "\u2B1C";
    }

    @Override
    public void setEmoji() {
        this.emoji = "\u2B1C";
    }

    @Override
    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString(String emojiString) {
        return emojiString;
    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public int getI() {
        return 0;
    }

    @Override
    public int getJ() {
        return 0;
    }

    @Override
    public void setEmojiSelected() {
    }
}
