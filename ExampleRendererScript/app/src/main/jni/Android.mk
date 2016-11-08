LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS += -Wall
LOCAL_LDLIBS := -llog -ljnigraphics
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include
LOCAL_MODULE    := Rs-jni
LOCAL_SRC_FILES := src/Rs-jni.c 


include $(BUILD_SHARED_LIBRARY)

