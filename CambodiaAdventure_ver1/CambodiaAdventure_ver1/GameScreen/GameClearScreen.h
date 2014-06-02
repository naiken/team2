//
//  GameClearScreen.h
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/19/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GameScreen.h"
#import "CEHScore.h"
#import "CambodianMap.h"

@interface GameClearScreen : UIViewController

@property (weak, nonatomic) IBOutlet UILabel *levelLabel;
@property (strong, nonatomic) IBOutletCollection(UIImageView) NSArray *lotusImageView;
@property (weak, nonatomic) IBOutlet UILabel *timeAllowanceLabel;
@property (weak, nonatomic) IBOutlet UILabel *timeUsageLabel;
@property (weak, nonatomic) IBOutlet UILabel *shortestTimeLabel;

@property(nonatomic) int preStageSerialNumber;
@property(nonatomic) int prelvl;
@property(nonatomic) int preStageNum;
@property(nonatomic) int timeAllowance;
@property(nonatomic) int timeUsage;
@property(nonatomic) int shortestTime;

- (IBAction)retry:(UIButton *)sender;
- (IBAction)goMap:(id)sender;
- (IBAction)goNext:(UIButton *)sender;

-(void)updateUserData;

@end
