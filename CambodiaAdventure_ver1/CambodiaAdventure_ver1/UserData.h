//
//  UserData.h
//  Cambodia_Map
//
//  Created by CKCC on 5/8/14.
//  Copyright (c) 2014 RUPP. All rights reserved.
//



//User Data
#define UDSetValue(key,value) [[NSUserDefaults standardUserDefaults] setValue:value forKey:key]
#define UDGetValue(key)     [[NSUserDefaults standardUserDefaults] objectForKey:key] ? [[NSUserDefaults standardUserDefaults] objectForKey:key] : @"NULL"


#define UDPush()   [[NSUserDefaults standardUserDefaults] synchronize]


#define UDRemoveAtKey(key) [[NSUserDefaults standardUserDefaults] removeObjectForKey:key]
#define UDClearAll()   [[NSUserDefaults standardUserDefaults] setPersistentDomain:[NSDictionary dictionary] forName:[[NSBundle mainBundle] bundleIdentifier]];

