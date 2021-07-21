// IGradeService.aidl
package com.zhpan.sample.binder.aidl;

// Declare any non-default types here with import statements

interface IGradeService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int getStudentGrade(String name);
}