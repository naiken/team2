//
//  MoveObject.m
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/5/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.

#import "MoveObject.h"


@implementation MoveObject {
    
    int For4inch;
    
    int _stageNum;
}


-(id)initWithStageNum:(int)stageNum {
    
    if (self = [super init]) {
        
        For4inch = 44;
        
        _stagea = [[Stage alloc] initWithStageNum:stageNum];
        
        _stageNum = stageNum;
        _wasDrown = false;
        _wasGameover = false;
        _wasClear = false;
        _stepMoveFactor = 5;
        _attackedBorder = false;
        
        // To check drown
        if (stageNum == 3) {
            _isStageNum3 = true;
        } else {
            _isStageNum3 = false;
        }
        
    }
    return  self;
    
}




-(CGRect *)moveBackground:(CGRect *)backgroundFrame
            accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    _moveToBackgroundX = backgroundFrame->origin.x + (accelerationY * _stepMoveFactor);
    _moveToBackgroundY = backgroundFrame->origin.y + (accelerationX * _stepMoveFactor);
    
//   NSLog(@"あああああ%f",_moveToBackgroundX);
    
    
    // If man is insisde of stage
    if ([_stagea checkManInside:_moveToBackgroundX :_moveToBackgroundY]) {
    
        _attackedBorder = false;
        
        [_stagea makeBlocks:_moveToBackgroundX :_moveToBackgroundY];
        [_stagea checkAttackedBlock];
    
        
        
        // if human reached to goal, return to begining
        if ([_stagea checkGoal:_moveToBackgroundX :_moveToBackgroundY]) {
            _wasClear = true;
        }
    
        // If man did not attack to black
        if (!_stagea.attackedBlock) {
            backgroundFrame->origin.x += accelerationY * _stepMoveFactor;
            backgroundFrame->origin.y += accelerationX * _stepMoveFactor;
            
        } else {
    
            if(_stageNum == 3) {
                _wasGameover = true;
                _wasDrown = true;
            }
        }

    
        // If man attacked to bolder of stage
    } else {
        _attackedBorder = true;
    }

    return backgroundFrame;
}




-(CGRect *)moveBlack:(CGRect *)loadFrame accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    // if human is inside the stage
    if ([_stagea checkManInside:_moveToBackgroundX :_moveToBackgroundY]) {
        
        
        // If man did not attack to black
        if (!_stagea.attackedBlock) {
            loadFrame->origin.x += accelerationY * _stepMoveFactor;
            loadFrame->origin.y += accelerationX * _stepMoveFactor;
        }
    }
    
    return loadFrame;
}





-(CGRect *)movevehicleR:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    NSInteger x_y_speed = vehicleImgV.tag;
    CGRect vehicleFrame = vehicleImgV.frame;
    
    float positionX = vehicleFrame.origin.x;
    
    int vehicleType = x_y_speed % 100;
    int vehicleSpeed = x_y_speed / 100;
    
    // Check whether man crashed or not
    [self checkCrash:vehicleFrame.origin.x :vehicleFrame.origin.y vehicleType:vehicleType direction:1];
    
    
    //    NSLog(@"aaaaaa%f",collisionX);
    
    
    // If vehicle is inside the stage
    if (positionX >= -150 && positionX < 600) {
        
        if (!_stagea.attackedBlock && !_attackedBorder) {
            vehicleFrame.origin.x += vehicleSpeed + accelerationY * _stepMoveFactor;
            vehicleFrame.origin.y += accelerationX * _stepMoveFactor;
            
            // If man attackedt to block
        } else {
            vehicleFrame.origin.x += vehicleSpeed;
        }
        
        
        // Move to begining position
    } else {
        [vehicleImgV setTag:[Vehicle decideSpeed:vehicleType]*100 + vehicleType];
        vehicleFrame.origin.x = -150;
    }
    
    return &vehicleFrame;
}






-(CGRect *)movevehicleL:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    NSInteger x_y_speed = vehicleImgV.tag;
    CGRect vehicleFrame = vehicleImgV.frame;
    
    float positionX = vehicleFrame.origin.x;
    
    NSInteger vehicleType = x_y_speed % 100;
    int vehicleSpeed = x_y_speed / 100;
    
    //    NSLog(@"aaaaaa%f",collisionX);
    
    // Check whether man crashed or not
    [self checkCrash:vehicleFrame.origin.x :vehicleFrame.origin.y vehicleType:vehicleType direction:2];
    
    
    // If vehicle is inside the stage
    if (positionX > -150 && positionX <= 600) {
        
        if (!_stagea.attackedBlock && !_attackedBorder) {
            vehicleFrame.origin.x -= vehicleSpeed - accelerationY * _stepMoveFactor;
            vehicleFrame.origin.y += accelerationX * _stepMoveFactor;
            
            // If man attackedt to block
        } else {
            vehicleFrame.origin.x -= vehicleSpeed;
        }
        
        
        // Move to begining position
    } else {
        [vehicleImgV setTag:(([Vehicle decideSpeed:vehicleType]*100) + vehicleType)];
        vehicleFrame.origin.x = 600;
    }
    
    
    return &vehicleFrame;
}






-(CGRect *)movevehicleD:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    NSInteger x_y_speed = vehicleImgV.tag;
    CGRect vehicleFrame = vehicleImgV.frame;
    
    float positionY = vehicleFrame.origin.y;
    
    int vehicleType = x_y_speed % 100;
    int vehicleSpeed = x_y_speed / 100;
    
    //    NSLog(@"aaaaaa%f",collisionX);
    
    // Check whether man crashed or not
    [self checkCrash:vehicleFrame.origin.x :vehicleFrame.origin.y vehicleType:vehicleType direction:3];
    
    
    // If vehicle is inside the stage
    if (positionY >= -200 && positionY < 500) {
        
        if (!_stagea.attackedBlock && !_attackedBorder) {
            vehicleFrame.origin.y += vehicleSpeed + accelerationX * _stepMoveFactor;
            vehicleFrame.origin.x += accelerationY * _stepMoveFactor;
            
            // If man attackedt to block
        } else {
            vehicleFrame.origin.y += vehicleSpeed;
        }
        
        
        // Move to begining position
    } else {
        [vehicleImgV setTag:[Vehicle decideSpeed:vehicleType]*100 + vehicleType];
        vehicleFrame.origin.y = -200;
    }
    
    return &vehicleFrame;
}





-(CGRect *)movevehicleU:(UIImageView *)vehicleImgV
          accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    NSInteger x_y_speed = vehicleImgV.tag;
    CGRect vehicleFrame = vehicleImgV.frame;
    
    float positionY = vehicleFrame.origin.y;
    
    int vehicleType = x_y_speed % 100;
    int vehicleSpeed = x_y_speed / 100;
    
    //    NSLog(@"aaaaaa%f",collisionX);
    
    // Check whether man crashed or not
    [self checkCrash:vehicleFrame.origin.x :vehicleFrame.origin.y vehicleType:vehicleType direction:4];
    
    
    // If vehicle is inside the stage
    if (positionY > -200 && positionY <= 500) {
        
        if (!_stagea.attackedBlock && !_attackedBorder) {
            vehicleFrame.origin.y -= vehicleSpeed - accelerationX * _stepMoveFactor;
            vehicleFrame.origin.x += accelerationY * _stepMoveFactor;
            
            // If man attackedt to block
        } else {
            vehicleFrame.origin.y -= vehicleSpeed;
        }
        
        
        // Move to begining position
    } else {
        [vehicleImgV setTag:[Vehicle decideSpeed:vehicleType]*100 + vehicleType];
        vehicleFrame.origin.y = 500;
    }
    
    return &vehicleFrame;
}


-(CGRect *)movevehicleRD:(UIImageView *)vehicleImgV
           accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    NSInteger x_y_speed = vehicleImgV.tag;
    CGRect vehicleFrame = vehicleImgV.frame;
    
    float positionY = vehicleFrame.origin.y;
    
    int vehicleType = x_y_speed % 100;
    float vehicleSpeed = x_y_speed / 100;
    float vX = vehicleSpeed * cos(M_PI_4);
    float vY = vehicleSpeed * sin(M_PI_4);
    
    //    NSLog(@"aaaaaa%f",collisionX);
    
    // Check whether man crashed or not
    [self checkCrash:vehicleFrame.origin.x :vehicleFrame.origin.y vehicleType:vehicleType direction:5];
    
    
    // If vehicle is inside the stage
    if (positionY >= -200 && positionY <= 500) {
        
        if (!_stagea.attackedBlock && !_attackedBorder) {
            vehicleFrame.origin.x += vX + accelerationY * _stepMoveFactor;
            vehicleFrame.origin.y += vY + accelerationX * _stepMoveFactor;
            
            // If man attackedt to block
        } else {
            vehicleFrame.origin.x += vX;
            vehicleFrame.origin.y += vY;
        }
        
        
        // Move to begining position
    } else {
        [vehicleImgV setTag:[Vehicle decideSpeed:vehicleType]*100 + vehicleType];
        vehicleFrame.origin.y -= 700;
        vehicleFrame.origin.x -= 700;
    }
    
    return &vehicleFrame;
}




-(CGRect *)movevehicleLU:(UIImageView *)vehicleImgV
           accelerationY:(float)accelerationY accelerationX:(float)accelerationX {
    
    NSInteger x_y_speed = vehicleImgV.tag;
    CGRect vehicleFrame = vehicleImgV.frame;
    
    float positionY = vehicleFrame.origin.y;
    
    int vehicleType = x_y_speed % 100;
    float vehicleSpeed = x_y_speed / 100;
    float vX = vehicleSpeed * cos(M_PI_4);
    float vY = vehicleSpeed * sin(M_PI_4);
    
    //    NSLog(@"aaaaaa%f",collisionX);
    
    // Check whether man crashed or not
    [self checkCrash:vehicleFrame.origin.x :vehicleFrame.origin.y vehicleType:vehicleType direction:6];
    
    
    // If vehicle is inside the stage
    if (positionY >= -200 && positionY <= 500) {
        
        if (!_stagea.attackedBlock && !_attackedBorder) {
            vehicleFrame.origin.x -= vX - accelerationY * _stepMoveFactor;
            vehicleFrame.origin.y -= vY - accelerationX * _stepMoveFactor;
            
            // If man attackedt to block
        } else {
            vehicleFrame.origin.x -= vX;
            vehicleFrame.origin.y -= vY;
        }
        
        
        // Move to begining position
    } else {
        [vehicleImgV setTag:[Vehicle decideSpeed:vehicleType]*100 + vehicleType];
        vehicleFrame.origin.y += 700;
        vehicleFrame.origin.x += 700;
    }
    
    return &vehicleFrame;
}



-(void)checkCrash:(float)x :(float)y vehicleType:(int)type direction:(int)direction {
    
    float x1 = [Vehicle getCollisinX1:type direction:direction] + x;
    float x2 = [Vehicle getCollisinX2:type direction:direction] + x;
    
    float y1 = [Vehicle getCollisinY1:type direction:direction] + y;
    float y2 = [Vehicle getCollisinY2:type direction:direction] + y;
    
    if(x1 > 230+For4inch && x1 < 250+For4inch && y1 > 140 && y1 < 180) {
        self.wasGameover = true;
    } else if (x2 > 230+For4inch && x2 < 250+For4inch && y2 > 140 && y2 < 180) {
        self.wasGameover = true;
    }
}





@end