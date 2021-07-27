package main;

class Mario extends Sprite {


    private double velocityY = 0.0;
    private boolean onGround = true;

    Mario(int x, int y){
        super(x, y);
        initMario();
    }

    private void initMario() {
        this.loadImage("src\\resources\\marioWalking.gif");
        this.getImageDimensions();
    }

    private void startJump() {
        if (onGround) {
            this.loadImage("src\\resources\\marioJump.png");
            velocityY = -2.5;
            onGround = false;
        }
    }

    void updatePosition() {
        double gravity = 0.037;
        velocityY += gravity;
        y += velocityY;

        if (y > 240.0)
        {
            y = 240.0;
            velocityY = 0.0;
            onGround = true;
            this.loadImage("src\\resources\\marioWalking.gif");
        }
    }

    void getInput() {
        boolean key = KeyManager.getKey();
        if (key) {
            startJump();
        }
    }
}
