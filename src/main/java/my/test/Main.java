package my.test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import su.litvak.chromecast.api.v2.Application;
import su.litvak.chromecast.api.v2.ChromeCast;
import su.litvak.chromecast.api.v2.ChromeCasts;
import su.litvak.chromecast.api.v2.MediaStatus;
import su.litvak.chromecast.api.v2.Status;

public class Main {
	private static final String APP_ID = "CC1AD845";

	public static void main(String[] args) throws InterruptedException, IOException {
		InetAddress address = InetAddress.getByName("192.168.1.41");
		ChromeCasts.startDiscovery(address);
		List<ChromeCast> chromeCasts;
		while((chromeCasts = ChromeCasts.get()).isEmpty()) {
			Thread.sleep(500L);
		}
		ChromeCasts.stopDiscovery();

		ChromeCast chromeCast = chromeCasts.get(0);
		System.out.println("ChromeCast: " + chromeCast);
		Status status = chromeCast.getStatus();
		System.out.println("Status: " + status);
		if (chromeCast.isAppAvailable(APP_ID) /*&& !status.isAppRunning(APP_ID)*/) {
			Application app = chromeCast.launchApp(APP_ID);
			System.out.println("Application: " + app);
			MediaStatus mediaStatus = chromeCast.load("https://download.samplelib.com/mp4/sample-30s.mp4");
			System.out.println("Media status: " + mediaStatus);
		}
	}
}
