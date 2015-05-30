#Noemi Zamarripa
#Waiter App - MonkeyRunner - Testing: Login, Posting, Logout
#CS499
#Prof. Sun
#5-20-2015

from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

#looking for device
device = MonkeyRunner.waitForConnection()

#installs app
device.installPackage("C:/Users/Noemi/AndroidStudioProjects/Waiter/app/app-release.apk")

#sets up packages
package = 'my.app.noemi.waiter'
activity = 'my.app.noemi.waiter.HomeActivity'

#begins running app
runComponent = package + '/' + activity
device.startActivity(component=runComponent)

#enters username
device.touch(209, 954, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(3)
device.type('thelovely')
MonkeyRunner.sleep(2)

#enters password
device.touch(209, 1135, MonkeyDevice.DOWN_AND_UP)
device.type('209174')
device.touch(209, 1135, MonkeyDevice.DOWN_AND_UP)

#puts away keyboard
device.touch(1012, 1841, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#signs in
device.touch(316, 1638, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#selects post feature
device.touch(805, 1151, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#types restaurant name
device.touch(360, 509, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(1)
device.type('Yard')
device.press("KEYCODE_SPACE", MonkeyDevice.DOWN_AND_UP)
device.type('House')
MonkeyRunner.sleep(2)

#types city name
device.touch(271, 688, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(1)
device.type('Rancho')
device.press("KEYCODE_SPACE", MonkeyDevice.DOWN_AND_UP)
device.type('Cucamonga')
MonkeyRunner.sleep(2)

#types zip code
device.touch(900, 684, MonkeyDevice.DOWN_AND_UP)
device.type('91730')
MonkeyRunner.sleep(2)

#puts away keyboard
device.touch(971, 1803, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
device.touch(971, 1803, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#taps the size wheel for 3 people
device.touch(292, 1431, MonkeyDevice.DOWN_AND_UP)
device.touch(292, 1431, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#taps the time wheel for 5 minutes
device.touch(812, 1404, MonkeyDevice.DOWN_AND_UP)
device.touch(812, 1404, MonkeyDevice.DOWN_AND_UP)
device.touch(812, 1404, MonkeyDevice.DOWN_AND_UP)
device.touch(812, 1404, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#posts to app
device.touch(583, 1717, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)

#logs out
device.touch(959, 158, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)