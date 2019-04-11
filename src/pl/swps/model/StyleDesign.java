package pl.swps.model;

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
                return new StyleDesign("#ffffff", "#000000");
            case GREEN:
                return new StyleDesign("#45624E", "#C5E7CE");
            case WHITE:
                return new StyleDesign("#000000", "#ffffff");
            default:
                return new StyleDesign();
        }
    }

    public enum StyleType {
        SEPIA, BLACK, WHITE, GREEN
    }
}
