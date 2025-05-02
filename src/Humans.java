public class Humans extends Entities{
    private int life;
    private int attack ;
    private int turn;
    private int positionI;
    private int getPositionJ;

    public Humans(){}

    public Humans (int turn, int i, int j){
        this.emoji = "\uD83E\uDD16" ;
        this.life = 100;
        this.attack = 0;
        this.turn = turn;
        this.positionI = i;
        this.getPositionJ = j;

    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int damage) {
        this.attack = damage;
    }

    @Override
    public void setEmoji() {
        this.emoji = "\uD83E\uDD16" ;
    }

    @Override
    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString(String emojiString) {
        return "\uD83E\uDD16";
    }

    @Override
    public void setPosition(int i, int j) {
        this.positionI = i;
        this.getPositionJ = j;
    }

    @Override
    public int getI() {
        return positionI;
    }

    @Override
    public int getJ() {
        return getPositionJ;
    }

    @Override
    public void setEmojiSelected() {
        this.emoji = "\uD83D\uDD34";
    }

}
