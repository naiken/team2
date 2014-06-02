//
//  CEHPersonImageView.h
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 5/8/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CEHImageView : UIImageView

- (id)initWithFrame:(CGRect)frame
          WithImage:(UIImage *)image;
- (void)moveRight;
- (void)moveLeft;
- (void)moveTop;

@end
