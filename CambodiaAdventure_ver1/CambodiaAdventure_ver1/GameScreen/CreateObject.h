//
//  CreateObject.h
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/5/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CreateObject : NSObject

// Create ImageView
-(UIImageView *)makeImageView:(float)x :(float)y :(float)width :(float)height name:(NSString *)name;

//-(UIImageView *)makeVehicleImageView:(float)x :(float)y : name:(NSString *)name direction:(int)direction;
-(UIImageView *)makeVehicleImageView:(float)x : (float)y name :(NSString *)name direction:(int)direction;



@end
