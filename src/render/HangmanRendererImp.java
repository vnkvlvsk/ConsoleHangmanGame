package render;

import interfaces.HangmanRenderer;

public class HangmanRendererImp implements HangmanRenderer {

    public String render(int attempts) {

        if (attempts < 0 || attempts >= HangmanDraw.values().length) {
            return "";
        }

        return HangmanDraw.values()[attempts].getDraw();
    }
}