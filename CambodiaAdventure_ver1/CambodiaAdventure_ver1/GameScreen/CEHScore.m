//
//  CEHScore.m
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 5/19/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import "CEHScore.h"

@implementation CEHScore

-(id)init {
    self = [super init];
    if (self) {
        // Initialization code here.
    }
    return self;
}

- (int)getLotus:(int)timeAllowance :(int)timeUsage {
    
    // if timeUsage was initial value
    if(timeUsage == 60) {
        return 0;
    } else {
        int lotus;
        float percentage;
        percentage = ((float)timeUsage / (float)timeAllowance)*100;
        if(percentage > 80) lotus = 1;
        else if(percentage >= 50 && percentage <= 80) lotus = 2;
        else if(percentage < 50) lotus = 3;
        return lotus;
    }
}
@end
