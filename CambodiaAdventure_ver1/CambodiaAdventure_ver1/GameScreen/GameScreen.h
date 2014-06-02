//
//  Level1Stage1.h
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/10/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

#import "GameScreen.h"
#import "CreateObject.h"
#import "MoveObject.h"
#import "GameStartView.h"
#import "GameClearView.h"
#import "GameOverView.h"
#import "GameClearScreen.h"
#import "GameOverScreen.h"

#import "ADVRoundProgressView.h"// Timer

@class ADVRoundProgressView;
@interface GameScreen : UIViewController


@property (weak, nonatomic) IBOutlet UIView *naviArea;
@property (strong, nonatomic) IBOutlet ADVRoundProgressView *roundProgressLarge;
@property (weak, nonatomic) IBOutlet UILabel *secLabel;

@property (readonly) BOOL accelerometerAvailable;
@property (readonly) BOOL gyroAvailable;
@property (readonly) BOOL deviceMotionAvailable;

@property (nonatomic) int stageSerialNumber;
@property (nonatomic) float sec;
@property (nonatomic) int lvl;
@property (nonatomic) int stageNum;
@property (weak, nonatomic) IBOutlet UILabel *cityName;
@property (weak, nonatomic) IBOutlet UILabel *stageName;
@property (nonatomic) NSString *cityNameHolder;
@property (nonatomic) NSString *stageNameHolder;



@property (weak, nonatomic) IBOutlet UIImageView *gameStartLetter;
@property (weak, nonatomic) IBOutlet GameStartView *gameStartView;
@property (weak, nonatomic) IBOutlet UIImageView *gameClearLetter;
@property (weak, nonatomic) IBOutlet GameClearView *gameClearView;
@property (weak, nonatomic) IBOutlet UIImageView *gameOverLetter;
@property (weak, nonatomic) IBOutlet GameOverView *gameOverView;
@property (weak, nonatomic) IBOutlet UIImageView *timeupLetter;

// Pause
@property (weak, nonatomic) IBOutlet UIView *pauseView;
@property (weak, nonatomic) IBOutlet UISlider *speedBar;
- (IBAction)reStart:(UIButton *)sender;
- (IBAction)pause:(UIButton *)sender;


-(void)initWithStageNum:(int)stageNum;
-(void)setStageLavel:(int)stageNum;
-(void)prepareAnime;
-(void)startParticleManage:(NSTimer *)tm;
-(void)clearParticleManage:(NSTimer *)tm;
-(void)overParticleManage:(NSTimer *)tm;



@end