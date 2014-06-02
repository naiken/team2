//
//  Stage.m
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/10/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "Stage.h"

@implementation Stage {
    int For4inch;
}


-(id)initWithStageNum:(int)stageNum {
    
    For4inch = 44;
    
    if(self = [super init]) {
        switch (stageNum) {
                
            case 1:
                _stageBorder[0] = -230+For4inch;
                _stageBorder[1] =  230+For4inch;
                _stageBorder[2] = -450;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = -140+For4inch;
                _goalBorder[1] = -80+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                _block1[0] = 80+For4inch;
                _block1[1] = 140+For4inch;
                _block1[2] = 120;
                _block1[3] = 140;
                
                _block2[0] = 110+For4inch;
                _block2[1] = 230+For4inch;
                _block2[2] = 60;
                _block2[3] = 150;
                
                break;
                
                
                
            case 2:
                _stageBorder[0] = -330+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -710;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = 140+For4inch;
                _goalBorder[1] = 200+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                _block1[0] = 70+For4inch;
                _block1[1] = 230+For4inch;
                _block1[2] = -710;
                _block1[3] = -560;
                
                _block2[0] = -330+For4inch;
                _block2[1] = -90+For4inch;
                _block2[2] = -560;
                _block2[3] = -400;
                
                _block3[0] = 70+For4inch;
                _block3[1] = 230+For4inch;
                _block3[2] = -200;
                _block3[3] = -50;
                
                break;
                
            case 3:
                
                _stageBorder[0] = -330+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -1210;
                _stageBorder[3] = 140;
                
                _goalBorder[0] = -90+For4inch;
                _goalBorder[1] = 0+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                // This block is not block but sea
                _block1[0] = 200+For4inch;
                _block1[1] = 230+For4inch;
                _block1[2] = -675;
                _block1[3] = -620;
                
                _block2[0] = -330+For4inch;
                _block2[1] = -290+For4inch;
                _block2[2] = -675;
                _block2[3] = -620;
                
                _block3[0] = -150+For4inch;
                _block3[1] = 50+For4inch;
                _block3[2] = -520;
                _block3[3] = -460;
                
                _block4[0] = -19+For4inch;
                _block4[1] = 125+For4inch;
                _block4[2] = -160;
                _block4[3] = -100;
                
                _block5[0] = -220+For4inch;
                _block5[1] = -100+For4inch;
                _block5[2] = -160;
                _block5[3] = -100;
                
                
                
                break;
                
            case 4:
                
                _stageBorder[0] = -430+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -460;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = 130+For4inch;
                _goalBorder[1] = 200+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                _block1[0] = -430+For4inch;
                _block1[1] = -100+For4inch;
                _block1[2] = -240;
                _block1[3] = -60;
                
                _block2[0] = 100+For4inch;
                _block2[1] = 150+For4inch;
                _block2[2] = -240;
                _block2[3] = -100;
                
                _block3[0] = 80+For4inch;
                _block3[1] = 130+For4inch;
                _block3[2] = 60;
                _block3[3] = 140;
                
                _block4[0] = 200+For4inch;
                _block4[1] = 230+For4inch;
                _block4[2] = 60;
                _block4[3] = 140;
                
                break;
                
                
            case 5:
                
                _stageBorder[0] = -630+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -460;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = 140+For4inch;
                _goalBorder[1] = 220+For4inch;
                _goalBorder[2] = 120;
                _goalBorder[3] = 150;
                
                _block1[0] = -440+For4inch;
                _block1[1] = -370+For4inch;
                _block1[2] = -640;
                _block1[3] = -370;
                
                _block2[0] = -290+For4inch;
                _block2[1] = -70+For4inch;
                _block2[2] = -460;
                _block2[3] = -370;
                
                _block3[0] = 110+For4inch;
                _block3[1] = 180+For4inch;
                _block3[2] = -200;
                _block3[3] = -70;
                
                break;
                
                
            case 6:
                
                _stageBorder[0] = -710+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -880;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = 100+For4inch;
                _goalBorder[1] = 150+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                _block1[0] = -460+For4inch;
                _block1[1] = -70+For4inch;
                _block1[2] = -430;
                _block1[3] = -110;
                
                _block2[0] = -460+For4inch;
                _block2[1] = -270+For4inch;
                _block2[2] = -880;
                _block2[3] = -750;
                
                _block3[0] = 10+For4inch;
                _block3[1] = 160+For4inch;
                _block3[2] = -430;
                _block3[3] = -220;
                
                
                break;
                
                
            case 7:
                
                _stageBorder[0] = -430+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -660;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = 80+For4inch;
                _goalBorder[1] = 140+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                _block1[0] = 80+For4inch;
                _block1[1] = 230+For4inch;
                _block1[2] = -390;
                _block1[3] = -100;
                
                _block2[0] = -430+For4inch;
                _block2[1] = -290+For4inch;
                _block2[2] = -200;
                _block2[3] = 70;
                
                break;
                
                
            case 8:
                
                _stageBorder[0] = -530+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -960;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = -450+For4inch;
                _goalBorder[1] = -380+For4inch;
                _goalBorder[2] = 130;
                _goalBorder[3] = 150;
                
                _block1[0] = -530+For4inch;
                _block1[1] = -185+For4inch;
                _block1[2] = -280;
                _block1[3] = -60;
                
                break;
                
                
            case 9:
                
                _stageBorder[0] = -710+For4inch;
                _stageBorder[1] = 230+For4inch;
                _stageBorder[2] = -1220;
                _stageBorder[3] = 150;
                
                _goalBorder[0] = -700+For4inch;
                _goalBorder[1] = -640+For4inch;
                _goalBorder[2] = 120;
                _goalBorder[3] = 150;
                
                _block1[0] = -180+For4inch;
                _block1[1] = 40+For4inch;
                _block1[2] = -1220;
                _block1[3] = -950;
                
                _block2[0] = -390+For4inch;
                _block2[1] = 130+For4inch;
                _block2[2] = -450;
                _block2[3] = -60;
                
                
                break;
                
                
            default:
                break;
        }
        
    }
    
    return self;
}

-(bool)checkManInside:(float)bgX :(float)bgY {
    
    return bgX > _stageBorder[0] && bgX < _stageBorder[1] &&
    bgY > _stageBorder[2] && bgY < _stageBorder[3];
}


-(bool)checkGoal:(float)bgX :(float)bgY {
    
    if  (bgX > _goalBorder[0] && bgX < _goalBorder[1] &&
         bgY > _goalBorder[2] && bgY < _goalBorder[3]) {
        return true;
    } else {
        return false;
    }
}



-(void)checkAttackedBlock {
    for (int i = 0; i < sizeof(_stageBlock); i++) {
        
        if (_stageBlock[i]) {
            _attackedBlock = true;
            break;
            
            // If man did not attackedBlock to block
        } else if (!_stageBlock[9]) {
            _attackedBlock = false;
        }
    }
}


-(void)makeBlocks:(float)moveToX :(float)moveToY {
    
    _stageBlock[0] = moveToX > _block1[0] && moveToX < _block1[1] && moveToY > _block1[2] && moveToY < _block1[3];
    _stageBlock[1] = moveToX > _block2[0] && moveToX < _block2[1] && moveToY > _block2[2] && moveToY < _block2[3];
    _stageBlock[2] = moveToX > _block3[0] && moveToX < _block3[1] && moveToY > _block3[2] && moveToY < _block3[3];
    _stageBlock[3] = moveToX > _block4[0] && moveToX < _block4[1] && moveToY > _block4[2] && moveToY < _block4[3];
    _stageBlock[4] = moveToX > _block5[0] && moveToX < _block5[1] && moveToY > _block5[2] && moveToY < _block5[3];
}

@end