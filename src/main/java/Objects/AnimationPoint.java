package Objects;

import javafx.scene.transform.Translate;

public class AnimationPoint {
    Translate translate;
    int xAngle;
    int yAngle;

    public AnimationPoint(){
        translate = new Translate(0,0,0);
        xAngle = 0;
        yAngle = 0;
    }

    public AnimationPoint(Translate translate){
        this.translate = translate;
        xAngle = 0;
        yAngle = 0;
    }

    public AnimationPoint(int xAngle, int yAngle){
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        translate = new Translate(0,0,0);
    }

    public AnimationPoint(Translate translate, int xAngle, int yAngle){
        this.translate = translate;
        this.xAngle = xAngle;
        this.yAngle = yAngle;
    }

    public Translate getTranslate(){
        return translate;
    }

    public int getXAngle(){
        return xAngle;
    }

    public int getYAngle(){
        return yAngle;
    }
}

