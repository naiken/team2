//
//  Stage.h
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/10/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Stage : NSObject {
    
    float _stageBorder[4];
    
    float _goalBorder[4];
    
    bool _stageBlock[10];
    
    float _block1[4];
    float _block2[4];
    float _block3[4];
    float _block4[4];
    float _block5[4];
    
    float _sea1[4];
    float _sea2[4];
    float _sea3[4];
    float _sea4[4];
    float _sea5[4];
    
}

@property (nonatomic) bool attackedBlock;

-(id)initWithStageNum:(int)stageNum;
-(bool)checkManInside:(float)bgX :(float)bgY;
-(bool)checkGoal:(float)bgX : (float)byY;
-(void)makeBlocks:(float)moveToX :(float)moveToY;
-(void)checkAttackedBlock;

@end
