//
//  Vehicle.h
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/7/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Vehicle : NSObject

+(int)decideSpeed:(NSInteger)vehicleId;
+(float)getCollisinX1:(NSInteger)vehicleId direction:(int)directionId;
+(float)getCollisinX2:(NSInteger)vehicleId direction:(int)directionId;
+(float)getCollisinY1:(NSInteger)vehicleId direction:(int)directionId;
+(float)getCollisinY2:(NSInteger)vehicleId direction:(int)directionId;


@end

