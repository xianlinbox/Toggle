package example;

import com.thoughtworks.toggle.Toggle;
import com.thoughtworks.toggle.ToggleStatus;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/5/13
 * Time: 9:05 PM
 */
public enum MyFeatureToggle2 implements Toggle {
    TEST_FEATURE_2(ToggleStatus.OFF);
    private ToggleStatus status;

    MyFeatureToggle2(ToggleStatus status) {
        this.status = status;
    }

    @Override
    public void setStatus(ToggleStatus status) {
        this.status = status;
    }

    @Override
    public ToggleStatus getStatus() {
        return status;
    }

    public boolean isEnabled() {
        return ToggleStatus.ON.equals(status);
    }
}
