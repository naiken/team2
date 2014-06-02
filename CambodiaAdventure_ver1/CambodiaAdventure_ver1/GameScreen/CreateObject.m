//
//  CreateObject.m
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/5/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "CreateObject.h"

@implementation CreateObject

-(UIImageView *)makeImageView:(float)x :(float)y :(float)width :(float)height
                         name:(NSString *)name {
    
    UIImageView* imgView = [[UIImageView alloc]
                            initWithFrame:CGRectMake(x, y, width, height)];
    imgView.image = [UIImage imageNamed:name];
    
    return  imgView;
    
}



-(UIImageView *)makeVehicleImageView:(float)x :(float)y name:(NSString *)name direction:(int)direction {
    
    UIImageView *imgView;
    
    if ([name isEqualToString:@"car.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 100, 50)];
        [imgView setTag:801];
    } else if ([name isEqualToString:@"scooter.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 40, 40)];
        [imgView setTag:602];
    } else if ([name isEqualToString:@"tuktuk.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 88, 60)];
        [imgView setTag:403];
    } else if ([name isEqualToString:@"bigcar.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 130, 60)];
        [imgView setTag:1004];
    }  else if ([name isEqualToString:@"cyclo.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 60, 60)];
        [imgView setTag:305];
    } else if ([name isEqualToString:@"car_slope.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 90, 90)];
        [imgView setTag:801];
    } else if ([name isEqualToString:@"scooter_slope.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 40, 40)];
        [imgView setTag:602];
    } else if ([name isEqualToString:@"tuktuk_slope.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 60, 90)];
        [imgView setTag:403];
    } else if ([name isEqualToString:@"bigcar_slope.png"]) {
        imgView = [[UIImageView alloc]
                   initWithFrame:CGRectMake(x, y, 110, 115)];
        [imgView setTag:1004];
    }
    
    
    
    // R = 1, L = 2, D = 3, U = 4, RD = 5, LU = 6
    if (direction == 1 || direction == 5) {
        //
    } else if (direction == 2) {
        imgView.transform = CGAffineTransformScale(imgView.transform, -1.0, 1.0);
    } else if (direction == 3) {
        imgView.transform = CGAffineTransformMakeRotation(M_PI_2);
        imgView.transform = CGAffineTransformScale(imgView.transform, 1.0, -1.0);
    } else if (direction == 4) {
        imgView.transform = CGAffineTransformMakeRotation(-M_PI_2);
    } else if (direction == 6) {
        imgView.transform = CGAffineTransformMakeRotation(M_PI_2);
        imgView.transform = CGAffineTransformScale(imgView.transform, -1.0, 1.0);
    }
    
    imgView.image = [UIImage imageNamed:name];
    
    return imgView;
    
}


@end
