package render;

public enum HangmanDraw {

    STAGE_0("""
               
               
               
            """),

    STAGE_1("""
             O
            
            
            """),

    STAGE_2("""
             O
             |
            
            
            """),

    STAGE_3("""
            O
           /|
            
            
            """),

    STAGE_4("""
            O
           /|\\
            
            
            """),

    STAGE_5("""
            O
           /|\\
           /
            """),

    STAGE_6("""
            O
           /|\\
           / \\
            """);

    private final String draw;

    HangmanDraw(String draw) {
        this.draw = draw;
    }

    String getDraw() {
        return draw;
    }
}