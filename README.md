## Setup

### Android SDK

#### Path setup

    export ANDROID_HOME=/Users/amark/android-sdk-macosx
    export PATH="$ANDROID_HOME/build-tools/17.0.0:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/build-tools:$PATH" 

#### Symlinks for tools for Intellij

    mkdir -p $ANDROID_HOME/platforms/android-17/tools/lib
    ln -s $ANDROID_HOME/build-tools/17.0.0/aapt $ANDROID_HOME/platforms/android-17/tools/aapt
    ln -s $ANDROID_HOME/build-tools/17.0.0/lib/dx.jar $ANDROID_HOME/platforms/android-17/tools/lib/dx.jar