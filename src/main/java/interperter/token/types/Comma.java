package interperter.token.types;

import org.kosta.interperter.token.Precedence;
import org.kosta.interperter.token.Token;

import static org.kosta.interperter.token.Precedence.LOWEST;

public class Comma implements Token {

    @Override
    public Precedence precedence() {
        return LOWEST;  // Lowest precedence
    }

    @Override
    public String literal() {
        return ",";
    }

    @Override
    public Token setLiteral(String string) {
        return this;
    }
}
