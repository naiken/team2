//
//  GameOverView.h
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/19/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>

@interface GameOverView : UIView

-(void)startEmitting:(int)x :(int)y;
-(void)stopEmitting;

@end