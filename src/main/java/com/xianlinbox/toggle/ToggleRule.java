package com.xianlinbox.toggle;

import example.MyFeatureToggle;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/4/13
 * Time: 11:52 AM
 */
public class ToggleRule implements TestRule {

    @Override
    public Statement apply(final Statement statement, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Map<MyFeatureToggle, ToggleStatus> originStatusMap = MyFeatureToggle.getStatusMap();

                processEnabledToggleAnnotation(description);
                processDisabledToggleAnnotation(description);
                try{
                    statement.evaluate();
                }finally {
                    revertToggleStatusToOrigin(originStatusMap);
                }
            }
        };
    }

    private void revertToggleStatusToOrigin(Map<MyFeatureToggle, ToggleStatus> originStatusMap) {
        for (MyFeatureToggle toggle : MyFeatureToggle.values()){
            toggle.setStatus(originStatusMap.get(toggle));
        }
    }

    private void processDisabledToggleAnnotation(Description description) {
        ToggleDisabled toggleDisabledAnnotation = description.getAnnotation(ToggleDisabled.class);
        if (toggleDisabledAnnotation != null) {
//            MyFeatureToggle[] DisabledToggles = toggleDisabledAnnotation.value();
//            for (MyFeatureToggle disabledToggle : DisabledToggles) {
//                disabledToggle.setStatus(ToggleStatus.OFF);
//            }
        }
    }

    private void processEnabledToggleAnnotation(Description description) {
        ToggleEnabled toggleEnabledAnnotation = description.getAnnotation(ToggleEnabled.class);
        if (toggleEnabledAnnotation != null) {
//            MyFeatureToggle[] enabledToggles = toggleEnabledAnnotation.value();
//            for (MyFeatureToggle enabledToggle : enabledToggles) {
//                enabledToggle.setStatus(ToggleStatus.ON);
//            }
        }
    }
}
