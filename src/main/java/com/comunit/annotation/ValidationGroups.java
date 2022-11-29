package com.comunit.annotation;


import net.bytebuddy.implementation.bind.annotation.Default;

public class ValidationGroups {
    private ValidationGroups() {
    }

    public interface signup extends Default {};

    public interface login extends Default {};

    public interface board extends Default {};
}
