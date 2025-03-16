package interperter.token.types;

import org.kosta.interperter.token.Precedence;
import org.kosta.interperter.token.Token;

import static org.kosta.interperter.token.Precedence.CALL;

public class Lp implements Token {


    @Override
    public Precedence precedence() {
        return CALL;  // Grouping operators don't have precedence in the traditional sense
    }

    @Override
    public String literal() {
        return "(";
    }

    @Override
    public Token setLiteral(String string) {
        return this;
    }
}
