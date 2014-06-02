//
//  CEHTutorialViewController.m
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 4/30/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import "TutorialScreen.h"
#import "UIImage+animatedGIF.h"

@interface TutorialScreen (){
    int i;
}

@end

@implementation TutorialScreen


- (void)viewDidLoad {
    [super viewDidLoad];
    //    self.infoArray = [[NSArray alloc]initWithObjects:@"Welcome to Cambodia, Kingdom of Wonder!!! You are going to explore various historical places and resorts in 3 different cities: Sihanouk Ville, Siem Reap and Phnom Penh.",
    //                      @"You will start your journey from Sihanouk Ville and stop your journey in Phnom Penh. Each city, there are 3 historical places and resorts for you to visit. ",
    //                      @"Your mission is to walk across the road from one side to other side on time and without getting accident to visit your new place." @"You are required to earn enough star and finish all level in current city to move to next city.",
    //                      @"Let's start your journey!!!", nil];
    self.infoArray = [[NSArray alloc]initWithObjects:@"カンボジアで最も有名な3つのシティをプレイできる。",
                      @"端末を前後に傾けるとキャラクターが前後に移動する。",
                      @"端末を左右に傾けるとキャラクターが左右に移動する。",
                      @"それぞれのシティで最低6個以上スターを獲得しないと、次のシティへは行けないぞ。",
                      @"それでは、ゲームをはじめよう！",nil];
    self.charactorArray = [[NSArray alloc] initWithObjects:@"charactor_select_level",
                           @"charactor-move-up-down",
                           @"charactor-move-left-right",
                           @"get_enought_lotus",
                           @"let_start",nil];
    self.motionArray = [[NSArray alloc] initWithObjects:@"tap_select_level",
                        @"tilt_up_down",
                        @"tilt_left_right",
                        @"six_lotus_togo",
                        @"tap_select_level", nil];
    
    i = 0;
    [self setContentWithIndex:i];
    
}


- (IBAction)back:(UIButton *)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)previous:(UIButton *)sender {
    i--;
    [self setContentWithIndex:i];
    
    if(i == 0) self.previousButton.enabled =false;
    self.nextButton.enabled =true;
}

- (IBAction)next:(UIButton *)sender {
    i++;
    [self setContentWithIndex:i];
    
    if (i == self.infoArray.count - 1) self.nextButton.enabled =false;
    self.previousButton.enabled = true;
}

- (void)setContentWithIndex:(int)index{
    NSURL *c = [[NSBundle mainBundle] URLForResource:self.charactorArray[index] withExtension:@"gif"];
    NSURL *m = [[NSBundle mainBundle] URLForResource:self.motionArray[index] withExtension:@"gif"];
    self.characterImage.image=[UIImage animatedImageWithAnimatedGIFURL:c];
    self.motionImage.image=[UIImage animatedImageWithAnimatedGIFURL:m];
    self.infoLabel.text=self.infoArray[index];
}

@end
