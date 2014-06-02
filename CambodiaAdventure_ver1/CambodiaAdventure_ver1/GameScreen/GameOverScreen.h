//
//  GameOverScreen.h
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/20/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GameScreen.h"
#import "CambodianMap.h"

@interface GameOverScreen : UIViewController


@property (nonatomic) int preStageNum;

- (IBAction)goMap:(id)sender;
- (IBAction)retry:(id)sender;

@end
