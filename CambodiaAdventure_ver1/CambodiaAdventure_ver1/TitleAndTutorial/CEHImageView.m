//
//  CEHPersonImageView.m
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 5/8/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import "CEHImageView.h"

@implementation CEHImageView{
}

- (id)initWithFrame:(CGRect)frame WithImage:(UIImage *)image {
    self = [super initWithFrame:frame];
    if (self) {
        self.image = image;
    }
    return self;
}

- (void)moveRight {
    [UIView animateWithDuration:5.0f delay:0.0f options:UIViewAnimationOptionAllowUserInteraction | UIViewAnimationOptionRepeat animations:^{
        self.center = CGPointMake([[UIScreen mainScreen]bounds].size.height + self.frame.size.width, self.center.y);
    }completion:nil];
}

- (void)moveLeft {
    [UIView animateWithDuration:5.0f delay:0.0f options:UIViewAnimationOptionAllowUserInteraction | UIViewAnimationOptionRepeat animations:^{
        self.center = CGPointMake(-self.frame.size.width, self.center.y);
    }completion:nil];
}

- (void)moveTop {
    [UIView animateWithDuration:2.5f delay:0.0f options:UIViewAnimationOptionAllowUserInteraction | UIViewAnimationOptionRepeat | UIViewAnimationOptionAutoreverse animations:^{
        self.center = CGPointMake(self.center.x, 0);
    }completion:nil];
}

@end
