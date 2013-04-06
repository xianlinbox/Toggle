package com.thoughtworks.toggle;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xianlinbox
 * Date: 4/4/13
 * Time: 9:38 PM
 */
public class ToggleRule implements TestRule {
    @Override
    public Statement apply(final Statement statement, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                ToggleEnabled toggleEnabledAnnotation = description.getAnnotation(ToggleEnabled.class);
                ToggleDisabled toggleDisabledAnnotation = description.getAnnotation(ToggleDisabled.class);
                Map<Object, ToggleStatus> originStatusMap = new HashMap<Object, ToggleStatus>();

                processedEnabledAnnotation(toggleEnabledAnnotation, originStatusMap);
                processedDisabledAnnotation(toggleDisabledAnnotation, originStatusMap);
                try {
                    statement.evaluate();
                } finally {
                    rollBackToggleStatusToOrigin(originStatusMap, toggleEnabledAnnotation, toggleDisabledAnnotation);
                }
            }
        };
    }

    private void processedDisabledAnnotation(ToggleDisabled disabledAnnotation, Map<Object, ToggleStatus> originStatusMap) throws ClassNotFoundException {
        if (disabledAnnotation != null) {
            String disabledToggleClassName = disabledAnnotation.toggleClass();
            String[] disabledToggleNames = disabledAnnotation.toggleNames();
            Object[] disableToggles = getToggleEnumObjects(disabledToggleClassName);

            for (Object toggle : disableToggles) {
                addToggleIntoMapIfAbsent(originStatusMap, toggle);
                updateToggleStatus(disabledToggleNames, (Toggle) toggle, ToggleStatus.OFF);
            }
        }
    }

    private void processedEnabledAnnotation(ToggleEnabled enabledAnnotation, Map<Object, ToggleStatus> originStatusMap) throws ClassNotFoundException {
        if (enabledAnnotation != null) {
            String[] toggleNames = enabledAnnotation.toggleNames();
            String toggleClassName = enabledAnnotation.toggleClass();

            Object[] toggles = getToggleEnumObjects(toggleClassName);
            for (Object toggle : toggles) {
                addToggleIntoMapIfAbsent(originStatusMap, toggle);
                updateToggleStatus(toggleNames, (Toggle) toggle, ToggleStatus.ON);
            }
        }
    }

    private void rollBackToggleStatusToOrigin(Map<Object, ToggleStatus> originStatusMap,
                                              ToggleEnabled enabledAnnotation,
                                              ToggleDisabled disabledAnnotation) throws ClassNotFoundException {
        if (enabledAnnotation != null) {
            String toggleClassName = enabledAnnotation.toggleClass();
            updateToggleStatusToOrigin(originStatusMap, toggleClassName);
        }

        if (disabledAnnotation != null) {
            String toggleClassName = disabledAnnotation.toggleClass();
            updateToggleStatusToOrigin(originStatusMap, toggleClassName);
        }
    }

    private void updateToggleStatus(String[] toggleNames, Toggle toggle, ToggleStatus status) {
        if (isToggleInToggleNamesArray(toggleNames, toggle.name())) {
            toggle.setStatus(status);
        }
    }

    private boolean isToggleInToggleNamesArray(String[] toggleNames, String name) {
            if (toggleNames == null) {
                return false;
            }
            if (name == null) {
                for (int i = 0; i < toggleNames.length; i++) {
                    if (toggleNames[i] == null) {
                        return true;
                    }
                }
            } else if (toggleNames.getClass().getComponentType().isInstance(name)) {
                for (int i = 0; i < toggleNames.length; i++) {
                    if (name.equals(toggleNames[i])) {
                        return true;
                    }
                }
            }
            return false;
    }

    private void addToggleIntoMapIfAbsent(Map<Object, ToggleStatus> originStatusMap, Object toggle) {
        if (originStatusMap.get(toggle) == null) {
            originStatusMap.put(toggle, ((Toggle) toggle).getStatus());
        }
    }

    private void updateToggleStatusToOrigin(Map<Object, ToggleStatus> originStatusMap, String toggleClassName) throws ClassNotFoundException {
        Object[] toggles = getToggleEnumObjects(toggleClassName);
        for (Object toggle : toggles) {
            ((Toggle) toggle).setStatus(originStatusMap.get(toggle));
        }
    }

    private Object[] getToggleEnumObjects(String toggleClassName) throws ClassNotFoundException {
        Class<?> toggleClass = Class.forName(toggleClassName);
        return toggleClass.getEnumConstants();
    }



}
