
//
//  RNNotificationLibrary.m
//  RNNotificationLibrary
//
//  Created by Yadhukrishnan Ekambaran on 2021-03-17.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

@interface RCT_EXTERN_MODULE(RnNotification, RCTEventEmitter)

RCT_EXTERN_METHOD(requestNotificationPermission:
                  (RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject
                  )

RCT_EXTERN_METHOD(showNotification: (NSString *) channelId
                  notificationTitle: (NSString *)title
                  notificationBody: (NSString *)body)
@end
