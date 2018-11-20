package com.tw.bootcamp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionValidatorTest {

    private ExpressionValidator validator;

    @Before
    public void setup() {
        validator = new ExpressionValidator();
    }

    @Test
    public void shouldReturnTrueForOpenBracketFollowedByClosedBracket() {
        assertTrue(validator.validate("()"));
    }

    @Test
    public void shouldReturnTrueForValidExprWithMultipleOpenNClosedBrackets() {
        assertTrue(validator.validate("(())()"));
    }

    @Test
    public void shouldReturnTrueForEmptyExpression() {
        assertTrue(validator.validate(""));
    }

    @Test
    public void shouldReturnFalseForNullExpression() {
        assertFalse(validator.validate(null));
    }

    @Test
    public void shouldReturnTrueForExpressionWithLettersAndValidBrackets() {
        assertTrue(validator.validate("(aaa)((b))()"));
    }

    @Test
    public void shouldReturnFalseForClosingBracketFollowedByOpeningBracketAlthoughBalanced() {
        assertFalse(validator.validate(")("));
    }
}