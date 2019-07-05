package Enums;

import javafx.scene.paint.Color;

public enum AreaColor {
    forest(Color.rgb(5, 77, 27)), water(Color.rgb(46, 65, 140)),hill(Color.rgb(72, 138, 91)), mountain(Color.rgb(196, 196, 196)), swamp(Color.rgb(92, 42, 2)), farm(Color.rgb(255, 201, 5));
    private Color color;
    AreaColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
}
