//
//  CEHTutorialViewController.h
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 4/30/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TutorialScreen : UIViewController

@property (strong, nonatomic) IBOutletCollection(UIImageView) NSArray *backwardCarsImageView;

@property(weak,nonatomic) UIPopoverController *popView;
@property(strong, nonatomic) NSArray *infoArray;
@property(strong,nonatomic) NSArray *charactorArray;
@property(strong,nonatomic) NSArray *motionArray;

@property (weak, nonatomic) IBOutlet UILabel *infoLabel;
@property (weak, nonatomic) IBOutlet UIButton *previousButton;
@property (weak, nonatomic) IBOutlet UIImageView *characterImage;
@property (weak, nonatomic) IBOutlet UIImageView *motionImage;
@property (weak, nonatomic) IBOutlet UIButton *nextButton;

- (IBAction)back:(UIButton *)sender;
- (IBAction)previous:(UIButton *)sender;
- (IBAction)next:(UIButton *)sender;

@end
