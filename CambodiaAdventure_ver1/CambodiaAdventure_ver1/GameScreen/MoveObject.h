//
//  MoveObject.h
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/5/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Vehicle.h"
#import "Stage.h"

@interface MoveObject : NSObject {
    
    Stage *_stagea;
    
    bool _attackedBorder;
    float _moveToBackgroundX;// to share this value between moveBackgrond and moveload
    float _moveToBackgroundY;// to share this value between moveBackgrond and moveloa
    bool _isStageNum3;// to check drown
}

@property(nonatomic)bool wasGameover;
@property(nonatomic)bool wasDrown;
@property(nonatomic)bool wasClear;
@property(nonatomic)float stepMoveFactor;

-(id)initWithStageNum:(int)stageNum;

// move man
-(CGRect *)moveBackground:(CGRect *)backgroundFrame
            accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)moveBlack:(CGRect *)loadFrame
       accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)movevehicleR:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)movevehicleL:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)movevehicleD:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)movevehicleU:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)movevehicleRD:(UIImageView *)vehicleImgV
           accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(CGRect *)movevehicleLU:(UIImageView *)vehicleImgV
           accelerationY:(float)accelerationY accelerationX:(float)accelerationX;

-(void)checkCrash:(float)x : (float)y vehicleType:(int)type direction:(int)direction;


@end

