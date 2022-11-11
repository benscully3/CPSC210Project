package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NegativeNumberExceptionTest {

    @Test
    public void throwNotPositiveNumberTest(){
        assertThrows(NegativeNumberException.class, () -> {
            testHelperFunction();
        }, "NotPositiveNumber throw was expected");
    }

    public void testHelperFunction() throws NegativeNumberException {
        throw new NegativeNumberException();
    }


}
