package ca.mcmaster.se2aa4.mazerunner.Yes;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Symbol {
    WALL('#', WallTile.class), PATH(' ', PathTile.class);

    private final char code;
    private final Class<? extends Tile> klass;

    private final static Map<Character, Symbol> bindings = new HashMap<>();
    static {
        for(Symbol s: Symbol.values()) {
            bindings.put(s.code, s);
        }
    }

    Symbol(char code, Class<? extends Tile> c) {
        this.code = code;
        this.klass = c;
    }

    public static Tile buildFrom(String data) {
        Symbol translated = bindings.get(data.charAt(0));
        Objects.requireNonNull(translated);
        return translated.instantiate();
    }

    private Tile instantiate() {
        try {
            return this.klass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(this.klass.getName());
        }
    }
}
