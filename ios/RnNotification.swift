//
//  RNNotification.swift
//  RNNotificationLibrary
//
//  Created by Yadhukrishnan Ekambaran on 2021-03-18.
//  Copyright © 2021 Facebook. All rights reserved.
//

import UIKit
import Foundation

enum NotificationPermissionStatus : String {
    case granted, denied
}

@objc(RNNotification)
class RnNotification: RCTEventEmitter, UNUserNotificationCenterDelegate {
  
  override init() {
    super.init()
    UNUserNotificationCenter.current().delegate = self
  }
  
  
  @objc
  func requestNotificationPermission(_ resolve: @escaping (RCTPromiseResolveBlock), rejecter reject: @escaping (RCTPromiseRejectBlock) ) -> Void {
    UNUserNotificationCenter.current().requestAuthorization(options: [.alert]) {
        (granted, error) in if (granted) {
          resolve(NotificationPermissionStatus.granted.rawValue)
        } else {
          reject("Notification request denied", "Rejected" , error)
        }
    }
  }
  
  func userNotificationCenter(_ center: UNUserNotificationCenter, willPresent notification: UNNotification, withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
    if #available(iOS 14.0, *) {
      completionHandler([.banner, .sound, .badge])
    } else {
      completionHandler([.alert, .sound, .badge])
    }
  }
  
  @objc func showNotification(_ channelId: String, notificationTitle title: String,notificationBody body: String) -> Void {
      let notificationContent = UNMutableNotificationContent();
      notificationContent.title = title
      notificationContent.body = body
      
      let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 2, repeats: false)
      let request = UNNotificationRequest(identifier: channelId, content: notificationContent, trigger: trigger)
      UNUserNotificationCenter.current().add(request) {
          (error) in
          if let error = error {
              print(error)
          } else {
              print("-- Notification shown --")
          }
      }
  }

  override func supportedEvents() -> [String]! {
    return ["RNNotification"]
  }
}
