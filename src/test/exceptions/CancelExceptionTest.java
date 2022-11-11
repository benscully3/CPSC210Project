package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CancelExceptionTest {

    @Test
    public void throwCancelExceptionTest(){
        assertThrows(CancelException.class, () -> {
            testHelperFunction();
        }, "CancelException throw was expected");
    }

    public void testHelperFunction() throws CancelException {
        throw new CancelException();
    }
}
