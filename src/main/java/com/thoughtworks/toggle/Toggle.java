package com.thoughtworks.toggle;

/**
 * This interface is used to define which methods must be applied in your Feature Toggle.
 * User: xianlinbox
 * Date: 4/3/13
 * Time: 4:26 PM
 */
public interface Toggle {
    void setStatus(ToggleStatus status);
    ToggleStatus getStatus();
    String name();
}
