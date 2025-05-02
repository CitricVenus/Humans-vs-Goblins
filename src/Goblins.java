public class Goblins extends Entities{
    private int life;
    private int damage;
    private int turn;
    private int positionJ;
    private int positionI;
    public Goblins(){
    }

    public Goblins (int turn, int i, int j){
        super.emoji =  "\uD83D\uDC7D";
        this.life = 100;
        this.damage = 0;
        this.turn = turn;
        this.positionI = i;
        this.positionJ = j;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return damage;
    }

    public void setAttack(int damage) {
        this.damage = damage;
    }

    @Override
    public void setEmoji() {
        this.emoji = "\uD83D\uDC7D";
    }

    @Override
    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString(String emojiString) {
        return "\uD83D\uDC7D";
    }

    @Override
    public void setPosition(int i, int j) {
        this.positionI = i;
        this.positionJ = j;
    }

    @Override
    public int getI() {
        return positionI;
    }

    @Override
    public int getJ() {
        return positionJ;
    }

    @Override
    public void setEmojiSelected() {
        this.emoji = "\uD83D\uDD34";
    }
}
