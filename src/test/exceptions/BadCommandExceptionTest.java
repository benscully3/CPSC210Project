package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadCommandExceptionTest {

    @Test
    public void throwBadCommandTest(){
        assertThrows(BadCommandException.class, () -> {
            testHelperFunction();
        }, "BadCommand throw was expected");
    }

    public void testHelperFunction() throws BadCommandException {
        throw new BadCommandException();
    }
}
