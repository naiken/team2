//
//  CityViewController.h
//  cityMap
//
//  Created by Measna on 5/7/14.
//  Copyright (c) 2014 Measna. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CambodianMap.h"
#import "UserData.h"
#import "GameScreen.h"
#import "CEHScore.h"


@interface CityMap : UIViewController


@property (nonatomic) NSInteger tagToCityMap;

@property (weak, nonatomic) IBOutlet UIButton *levelOneButton;
@property (weak, nonatomic) IBOutlet UIButton *levelTwoButton;
@property (weak, nonatomic) IBOutlet UIButton *levelThreeButton;


@property (weak, nonatomic) IBOutlet UIView *mapCityView;
@property (weak, nonatomic) IBOutlet UIImageView *slideshowImageView;
@property (weak, nonatomic) IBOutlet UIImageView *mapImageView;
@property (weak, nonatomic) IBOutlet UITextView *descriptionLabel;


@property (weak, nonatomic) IBOutlet UIImageView *lotusImg1;
@property (weak, nonatomic) IBOutlet UIImageView *lotusImg2;
@property (weak, nonatomic) IBOutlet UIImageView *lotusImg3;

- (IBAction)backButton:(UIButton *)sender;

- (IBAction)LevelButton:(UIButton *)sender;
- (IBAction)gotoButton:(UIButton *)sender;
- (IBAction)cancel:(UIButton *)sender;

-(void)setLoutsWithTimeAllowance:(int)tA shortestTime:(int)sT;

@end
