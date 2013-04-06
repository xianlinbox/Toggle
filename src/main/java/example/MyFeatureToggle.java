package example;

import com.thoughtworks.toggle.Toggle;
import com.thoughtworks.toggle.ToggleStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/3/13
 * Time: 4:37 PM
 */
public enum MyFeatureToggle implements Toggle {
    TEST_FEATURE(ToggleStatus.ON), STAGE1(ToggleStatus.ON), STAGE2(ToggleStatus.OFF);
    private ToggleStatus status;

    MyFeatureToggle(ToggleStatus status) {
        this.status = status;
    }

    @Override
    public void setStatus(ToggleStatus status) {
        this.status = status;
    }

    public boolean isEnabled() {
        return status.equals(ToggleStatus.ON);
    }

    @Override
    public ToggleStatus getStatus() {
        return status;
    }

    public static Map<MyFeatureToggle, ToggleStatus> getStatusMap() {
        Map<MyFeatureToggle, ToggleStatus> statusMap = new HashMap<MyFeatureToggle, ToggleStatus>();
        for (MyFeatureToggle toggle : MyFeatureToggle.values()) {
            statusMap.put(toggle, toggle.getStatus());
        }
        return statusMap;
    }
}
