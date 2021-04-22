import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;


public class ListTest {
    @Test
    public void testSize(){
        Integer excepted = 100;
        List list = PowerMockito.mock(List.class);
        PowerMockito.when(list.size()).thenReturn(excepted);
        Integer actual = list.size();
        Assert.assertEquals("返回值不等", excepted, actual);
    }

}
