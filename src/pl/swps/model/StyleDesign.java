package pl.swps.model;

import static pl.swps.model.StyleDesign.StyleType.*;

public class StyleDesign {
    private static final String KINDLE_SEPIA_FONT_COLOR_CODE = "#5F4B32";
    private static final String KINDLE_SEPIA_BACKGROUND_COLOR_CODE = "#FBF0D9";

    public String fontName;
    public String fontColor;
    public double fontSize;
    public String backgroundColor;

    private StyleDesign() {

    }

    private StyleDesign(String fontColor, String backgroundColor) {
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;

        this.fontName = "Georgia"; //Palatino
        this.fontSize = 47.0;
    }

    public static StyleDesign newInstance(StyleType type) {
        switch (type) {
            case SEPIA:
                return new StyleDesign(KINDLE_SEPIA_FONT_COLOR_CODE, KINDLE_SEPIA_BACKGROUND_COLOR_CODE);
            case BLACK:
                return new StyleDesign("#FFFFFF", "#000000");
            case GREEN:
                return new StyleDesign("#45624E", "#C5E7CE");
            case WHITE:
                return new StyleDesign("#000000", "#FFFFFF");
            case LIGHT:
                return new StyleDesign("#424242", "#DEE4E7");
            case DARK:
                return new StyleDesign("#FFFFFF", "#37474F");
            default:
                return new StyleDesign();
        }
    }

    public static StyleDesign getStyleInstance(String value) {

        if (value.contains(GREEN.toString())) {
            return StyleDesign.newInstance(GREEN);

        } else if (value.contains(SEPIA.toString())) {
            return StyleDesign.newInstance(SEPIA);

        } else if (value.contains(BLACK.toString())) {
            return StyleDesign.newInstance(BLACK);

        } else if (value.contains(WHITE.toString())) {
            return StyleDesign.newInstance(WHITE);

        } else if (value.contains(LIGHT.toString())) {
            return StyleDesign.newInstance(LIGHT);
        } else {
            return StyleDesign.newInstance(DARK);
        }

    }

    public enum StyleType {
        SEPIA, BLACK, WHITE, GREEN, LIGHT, DARK
    }
}
