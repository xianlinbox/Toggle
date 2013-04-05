package example;

import com.xianlinbox.toggle.Toggle;
import com.xianlinbox.toggle.ToggleStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/4/13
 * Time: 5:08 PM
 */
public class MyFeatureToggleTest {
    @Test
    public void testGetOriginStatus() throws Exception {
        Map<MyFeatureToggle, ToggleStatus> statusMap = MyFeatureToggle.getStatusMap();

        assertThat(statusMap.get(MyFeatureToggle.TEST_FEATURE), is(ToggleStatus.ON));
        assertThat(statusMap.get(MyFeatureToggle.STAGE1), is(ToggleStatus.ON));
        assertThat(statusMap.get(MyFeatureToggle.STAGE2), is(ToggleStatus.OFF));
    }

    @Test
    public void testInitFromString() throws Exception {

        Class featureToggle =  Class.forName("example.MyFeatureToggle");

        Map<Object, ToggleStatus> statusMap = new HashMap<>();

        for (Object toggle : featureToggle.getEnumConstants()) {
            statusMap.put(toggle,((Toggle)toggle).getStatus());
        }

        assertThat(statusMap.get(MyFeatureToggle.TEST_FEATURE), is(ToggleStatus.ON));
        assertThat(statusMap.get(MyFeatureToggle.STAGE1), is(ToggleStatus.ON));
        assertThat(statusMap.get(MyFeatureToggle.STAGE2), is(ToggleStatus.OFF));
    }
}
