package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadCommandTest {

    @Test
    public void throwBadCommandTest(){
        assertThrows(BadCommand.class, () -> {
            testHelperFunction();
        }, "BadCommand throw was expected");
    }

    public void testHelperFunction() throws BadCommand {
        throw new BadCommand();
    }
}
