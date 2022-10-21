package exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotPositiveNumberTest {

    @Test
    public void throwNotPositiveNumberTest(){
        assertThrows(NotPositiveNumber.class, () -> {
            testHelperFunction();
        }, "NotPositiveNumber throw was expected");
    }

    public void testHelperFunction() throws NotPositiveNumber {
        throw new NotPositiveNumber();
    }


}
