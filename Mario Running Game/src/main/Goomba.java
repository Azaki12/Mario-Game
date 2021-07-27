package main;

class Goomba extends Sprite {

    Goomba(double x, double y) {
        super(x, y);
        this.initGoomba();
    }

    private void initGoomba() {
        this.loadImage("src\\resources\\goomba.gif");
        this.getImageDimensions();
    }

    void move(double speed) {
        this.x -= speed;
    }
}
