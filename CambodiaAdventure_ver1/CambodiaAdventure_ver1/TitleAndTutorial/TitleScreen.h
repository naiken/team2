//
//  CEHViewController.h
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 4/30/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CEHImageView.h"

@interface TitleScreen : UIViewController

@property (strong, nonatomic) CEHImageView *forwardCarImageView;
@property (strong, nonatomic) CEHImageView *backwardCarImageView;
@property (strong, nonatomic) CEHImageView *personImageView;
@property (weak, nonatomic) IBOutlet UIImageView *titleImageView;

@end
