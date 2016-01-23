package com.vijaysrini.jobdemo.common;

import android.util.Log;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
import com.vijaysrini.jobdemo.aws.AWSMobileClient;

import java.util.Map;

/**
 * Created by 181647 on 1/22/2016.
 */
public class Analytics {
    public static final String LOGTAG = "Analytics";

    public static void generateAWSAnalyticsEvent(String eventName, Map<String,String> attributes ) {
        Log.d(LOGTAG, "Generating AWS Analytics event...");
        final EventClient eventClient =
                AWSMobileClient.defaultMobileClient().getMobileAnalyticsManager().getEventClient();
        final AnalyticsEvent event = eventClient.createEvent(eventName);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            event.withAttribute(entry.getKey(), entry.getValue());
        }
        eventClient.recordEvent(event);
        eventClient.submitEvents();
    }

    public static void generateScreenOpenAWSAnalyticsEvent(String screenName) {
        Log.d(LOGTAG, "Generating Open Screen AWS event...");
        final EventClient eventClient =
                AWSMobileClient.defaultMobileClient().getMobileAnalyticsManager().getEventClient();
        final AnalyticsEvent event = eventClient.createEvent(Constants.EVENT_SCREEN_OPEN);
        event.withAttribute("screen", screenName);
        eventClient.recordEvent(event);
        eventClient.submitEvents();
    }
}
