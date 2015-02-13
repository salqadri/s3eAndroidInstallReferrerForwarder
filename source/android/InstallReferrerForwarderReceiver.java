package com.ideaworks3d.marmalade.s3eAndroidInstallReferrerForwarderReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.ComponentName;

public class InstallReferrerForwarderReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
        Log.v("InstallReferrerForwarderReceiver", "InstallReferrerForwarderReceiver received intent!");
		
		ActivityInfo info = null;
        
        try {
            info = context.getPackageManager().getReceiverInfo(new ComponentName(context, InstallReferrerForwarderReceiver.class), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
		Bundle metaData = info.metaData;
		for(String key : metaData.keySet()){
			if (key.startsWith("forward.")) {
				Object obj = metaData.get(key);
				String className = obj.toString();
				try {
					Class<?> c = Class.forName(className);
					Object i = c.newInstance();
					if (i instanceof BroadcastReceiver) {
						Log.v("InstallReferrerForwarderReceiver", "    Forwarding intent to " + className);
						((BroadcastReceiver) i).onReceive(context, intent);
					}
					else {
						Log.w("InstallReferrerForwarderReceiver", "Expecting a className to be a broadcast receiver!");
					}
				}
				catch (Exception e) {
					Log.w("InstallReferrerForwarderReceiver", "Unable to instantiate class " + className);
				}
			}
		}
	}
}
