# react-native-rn-notification

## Getting started

`$ npm install react-native-rn-notification-v1 --save`

### Mostly automatic installation

`$ react-native link react-native-rn-notification-v1`

## Usage

```javascript
import RnNotification from "react-native-rn-notification-v1";

// For android, a channel is needed for showing a notification.

/*
STEP 1:
    Paramenters:
       channelId: String, Every notification channel must be associated with an ID that is      unique within your package. You use this channel ID later, to post your notifications

       channelName: String, Set the channel name. The name is displayed under notification Categories in the device's user-visible Settings app.

       importance: Integer, This param determines how to interrupt the user for any notification that belongs to this channel, By default it is set to it can range from 0 to 5

       description: String, This param specify the description that the user sees in the system settings.
*/
RnNotification.createNotificationChannel(
  channelId,
  channelName,
  importance,
  description
);

/*
For iOS, Request the permission for Notification
This method return a promise
if user grants the permission then the control will go to then section of the Promise otherwise it will give you an error.
*/
RnNotification.requestNotificationPermission();

// After the channle creation, you can call method for showing  the notification
/*
STEP 2:
    Parameters: 
        channelId: String, Provide the channel id that was created previouly
        title: String, It is title for the notification,
        body: String, It is the body part of the notification
*/
RnNotification.showNotification(channelId, title, body);

/*
For android by default it is taking the ic_launcher as small icon for the notification.
if you want your own icon please add icon in the drawable with the name ic_notification.
*/
```
