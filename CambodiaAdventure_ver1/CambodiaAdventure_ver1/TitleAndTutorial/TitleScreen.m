
//
//  CEHViewController.m
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 4/30/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import "TitleScreen.h"

@interface TitleScreen ()

@end

@implementation TitleScreen

- (void)viewDidLoad {
    [super viewDidLoad];
    NSArray *images = [[NSArray alloc]initWithObjects:[UIImage imageNamed:@"chara_1.png"],[UIImage imageNamed:@"chara_2.png"],[UIImage imageNamed:@"chara_3.png"], nil];
	// Do any additional setup after loading the view, typically from a nib.
    self.forwardCarImageView = [[CEHImageView alloc]initWithFrame:CGRectMake(-170, 162, 183, 72) WithImage:[UIImage imageNamed:@"car_green.png"]];
    [self.view addSubview:self.forwardCarImageView];
    
    self.backwardCarImageView = [[CEHImageView alloc]initWithFrame:CGRectMake([[UIScreen mainScreen]bounds].size.height - 20, 80, 183, 72) WithImage:[UIImage imageNamed:@"car_blue.png"]];
    [self.view addSubview:self.backwardCarImageView];
    
    self.personImageView = [[CEHImageView alloc]initWithFrame:CGRectMake((self.view.frame.size.height - 46)/2, self.view.frame.size.width- 95, 46, 95) WithImage:[UIImage imageNamed:@"chara_1.png"]];
    [self.view addSubview:self.personImageView];
    
    [self.view bringSubviewToFront:self.titleImageView];
    
    [self.forwardCarImageView moveRight];
    [self.backwardCarImageView moveLeft];
    [self.personImageView moveTop];
    [self.personImageView setAnimationImages:images];
    [self.personImageView setAnimationDuration:0.5f];
    [self.personImageView startAnimating];
    
}


@end
