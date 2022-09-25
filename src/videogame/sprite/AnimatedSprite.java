package videogame.sprite;

public class AnimatedSprite extends Sprite {
    public static final int TOTAL_MOVEMENTS = 4;
    public static final int LEFT = 1;
    public static final int RIGHT = 0;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final byte SPRITE_CHANGE = 5;

    protected int currentDirection;
    protected byte currentSprite;
    protected byte currentSprite_Change;

    protected int[][] spriteXCoordinates = new int[TOTAL_MOVEMENTS][];
    protected int[][] spriteYCoordinates = new int[TOTAL_MOVEMENTS][];

    public AnimatedSprite(int width, int height) {
        super(width,height);
        currentSprite = 0;
        currentSprite_Change = 0;
        currentDirection = RIGHT;
    }

    public void animate(int movement) {
        if (movement != currentDirection) {
            currentSprite = 0;
            currentSprite_Change = 0;
            currentDirection = movement;
        } else {
            currentSprite_Change++;
            if (currentSprite_Change >= SPRITE_CHANGE) {
                currentSprite_Change = 0;
                currentSprite = (byte) ((currentSprite + 1)
                        % spriteXCoordinates[currentDirection].length);
            }
        }
        updateSpriteCoordinates();
    }

    protected void updateSpriteCoordinates() {
        spriteX = spriteXCoordinates[currentDirection][currentSprite];
        spriteY = spriteYCoordinates[currentDirection][currentSprite];
    }
}
