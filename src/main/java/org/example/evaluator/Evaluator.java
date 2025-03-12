package org.example.evaluator;

import org.example.ast.expression.BooleanLiteral;
import org.example.ast.expression.Expression;
import org.example.ast.expression.IntegerLiteral;
import org.example.ast.expression.PrefixExpression;
import org.example.ast.node.Node;
import org.example.ast.node.Program;
import org.example.ast.statement.ExpressionStatement;
import org.example.ast.statement.Statement;
import org.example.object.*;
import org.example.object.Boolean;
import org.example.object.Integer;
import org.example.object.Object;

import java.util.List;

public class Evaluator
{
    private Boolean TRUE = new Boolean(true);
    private Boolean FALSE = new Boolean(false);

    private Null aNull = new Null("null");


    public Object evaluate(Node node) {
        if (node instanceof Program)
        {
            return evaluateStatements(((Program) node).getStatements());
        }
        else if (node instanceof ExpressionStatement)
        {
            return evaluate(((ExpressionStatement) node).getExpression());
        }
        else if (node instanceof PrefixExpression)
        {
            Object right  = evaluate(((PrefixExpression) node).getRight());
            return evaluatePrefixExpression(((PrefixExpression) node).getOperator(), right);
        }
        else if (node instanceof IntegerLiteral)
        {
            return new Integer(((IntegerLiteral) node).getValue());
        }
        else if (node instanceof BooleanLiteral)
        {
            return ((BooleanLiteral) node).getValue() ? TRUE : FALSE;
        }

        return aNull;
    }

    private Object evaluatePrefixExpression(String operator, Object right) {
        return switch (operator) {
            case "!" ->
                evaluateBangOperatorExpression(right);
            case "-" -> evaluateMinusOperatorExpression(right);
            default -> aNull;
        };
    }

    private Object evaluateMinusOperatorExpression(Object right) {
        if (right.objectType() != ObjectType.INTEGER_OBJECT)
        {
            return aNull;
        }

        int value = ((Integer) right).getValue();
        return new Integer(-value);
    }

    private Object evaluateBangOperatorExpression(Object right) {
        return switch (right.inspect())
        {
            case "false", "null" -> TRUE;
            default -> FALSE;
        };
    }


    public Object evaluateStatements(List<Statement> statements)
    {
        Object result = null;

        for (Statement statement : statements)
            result = evaluate(statement);

        return result;
    }




}
