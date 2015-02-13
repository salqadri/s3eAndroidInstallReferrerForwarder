# s3eAndroidInstallReferrerForwarder
Use this Marmalade extension to allow multiple install_referrer receivers on Android.

## Supported Platforms
 * Android

## Integration
This Extension does not have any C++ aspect to it; all it does it place a jar file in your android deployment folder.

Here is how to use it:
* In your manifest file, make `com.ideaworks3d.marmalade.s3eAndroidInstallReferrerForwarderReceiver.InstallReferrerForwarderReceiver` the ONLY listener to all install_referrer events.
* Give it a list of receivers to forward to via meta-data tags, one tag for each receiver.
* The meta-data tags have to have the key "forward.XYZ" where XYZ can be anything.
* The value must be the fully qualified class name of the receiver to forward to.

## Example
In the <application> section of your manifest, enter the following:
```
	<receiver android:name ="com.ideaworks3d.marmalade.s3eAndroidInstallReferrerForwarderReceiver.InstallReferrerForwarderReceiver" android:exported ="true" >
		<intent-filter>
			<action android:name ="com.android.vending.INSTALL_REFERRER" />
		</intent-filter>
		<meta-data android:name="forward.ABC" android:value="com.example1.receiver1"/>
		<meta-data android:name="forward.XYZ" android:value="com.example2.receiver2"/>
	</receiver>
```
