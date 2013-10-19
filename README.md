GpsLogger
=========

A small Android app that logs your coordinates during trips (walks, commutes...) to files. This can be useful to you for many reasons - my goal in writing this application was to log my commutes so that I could feed them to an artificial neural net and predict how much time it would take me to get to my destination.

Installation is simple: download this code, compile it using "Eclipse with the ADT plugin":http://developer.android.com/tools/help/adt.html or "Android Studio":http://developer.android.com/sdk/installing/studio.html, and install it to your device.

If you use AIDE:https://play.google.com/store/apps/details?id=com.aide.ui&hl=en, the IDE running on the Android platform, you don't need a computer. Just clone this repo to your IDE and run it!

The current version assumes that you have a file manager that allows you to download your trip files. They're human-readable, JSON-formatted text files that reside in a directory of your phone's default storage called net.rickeldarwish.gps_logger.
