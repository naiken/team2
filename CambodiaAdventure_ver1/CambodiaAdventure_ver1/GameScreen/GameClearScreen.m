//
//  MyViewController.m
//  Cambodia Adventure
//
//  Created by Eanghort Choeng on 5/16/14.
//  Copyright (c) 2014 Eanghort Choeng. All rights reserved.
//

#import "GameClearScreen.h"

@interface GameClearScreen () {
    
    NSString* keyArr;
    NSMutableArray* arrData;
    GameScreen *_stage;
    CEHScore *_score;
    int i;
    int totalLotsNum;
}

@end


@implementation GameClearScreen

- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    // Get user data
    arrData = [[NSMutableArray alloc]init];
    keyArr = [NSString stringWithFormat:@"LVL%ld",(long)_prelvl];//key for UD
    arrData = [UDGetValue(keyArr) mutableCopy];
    
    _score = [[CEHScore alloc] init];
    
    [self updateUserData];
    
    // Set texts
    self.levelLabel.text = [NSString stringWithFormat:@"LEVEL%d STAGE%d",_prelvl,_preStageNum];
    self.timeAllowanceLabel.text = [NSString stringWithFormat:@"%d", self.timeAllowance];
    self.timeUsageLabel.text = [NSString stringWithFormat:@"%d", self.timeUsage];
    self.shortestTimeLabel.text = [NSString stringWithFormat:@"%d", _shortestTime];
    
    // Set lotsImage
    for(UIImageView *imageView in self.lotusImageView){
        
        if(i < [_score getLotus:_timeAllowance :_timeUsage]){
            [imageView setImage:[UIImage imageNamed:@"lotus-gl.png"]];
        }
        i++;
    }
    
}

-(void)updateUserData {
    
    // if this is final stage, not change to clear
    if (_prelvl == 3 && _preStageNum == 3) {
        //
    } else {
        [arrData replaceObjectAtIndex:2*((int)_preStageNum-1) withObject:[NSNumber numberWithBool:YES]];
    }
    
//    BOOL tmp1 = [[arrData objectAtIndex:0] boolValue];
//    int tmp2 = [[arrData objectAtIndex:1] intValue];
//    BOOL tmp3 = [[arrData objectAtIndex:2] boolValue];
//    int tmp4 = [[arrData objectAtIndex:3] intValue];
//    BOOL tmp5 = [[arrData objectAtIndex:4] boolValue];
//    int tmp6 = [[arrData objectAtIndex:5] intValue];
    
    int oldShortestTime = [[arrData objectAtIndex:2*((int)_preStageNum-1) + 1] intValue];
    int oldLotsNum = [_score getLotus:_timeAllowance :oldShortestTime];
    
    // check shorteset time or not
    if (_timeUsage < oldShortestTime) {
        
        [arrData replaceObjectAtIndex:2*((int)_preStageNum-1) + 1 withObject:[NSNumber numberWithInt:_timeUsage]];
        
        _shortestTime = _timeUsage;
    } else {
        _shortestTime = oldShortestTime;
    }
    
    int newLotsNum = [_score getLotus:_timeAllowance :_shortestTime];
    totalLotsNum = [[arrData objectAtIndex:6] intValue];

    
    // if newLotasNum is much than old one
    if (newLotsNum > oldLotsNum) {
        totalLotsNum += newLotsNum - oldLotsNum;
        [arrData replaceObjectAtIndex:6 withObject:[NSNumber numberWithInt: totalLotsNum]];
    }
    
    totalLotsNum = [[arrData objectAtIndex:6] intValue];// updatede totalLotsNum
    
    UDSetValue(keyArr, arrData);
    UDPush();
}


- (IBAction)retry:(UIButton *)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)goMap:(id)sender {
    
    CambodianMap *cambodianMap = [self.storyboard instantiateViewControllerWithIdentifier:@"CambodianMapID"];
    //    int level;
    //    if (_preStageNum >= 1 && _preStageNum <= 3) {
    //        level = 1;
    //    } else if (_preStageNum >= 4 && _preStageNum <= 6) {
    //        level = 2;
    //    } else {
    //        level = 3;
    //    }
    [self presentViewController:cambodianMap animated:YES completion:nil];
    
}

- (IBAction)goNext:(UIButton *)sender{
    
    if (_preStageNum == 3 && _prelvl == 3) {
        
        UIAlertView *alert = [[UIAlertView alloc]
                              initWithTitle:@"Congratulations!"
                              message:@"YOU CLEARED ALL STAGE"
                              delegate:nil
                              cancelButtonTitle:nil
                              otherButtonTitles:@"OK", nil
                              ];
        [alert show];
        
    } else if (totalLotsNum >= 6 || _preStageNum == 1 || _preStageNum == 2) {
        
        GameScreen *gameScreen = [self.storyboard instantiateViewControllerWithIdentifier:@"GameScreenID"];
        gameScreen.stageSerialNumber = _preStageSerialNumber + 1;
        [self presentViewController:gameScreen animated:YES completion:nil];
        
    } else {
        UIAlertView *alert = [[UIAlertView alloc]
                              initWithTitle:@"Can not move to next stage"
                              message:@"Shortage of total Lots number. If you want to go to next stage, you must get over 6 Stars in this city"
                              delegate:nil
                              cancelButtonTitle:nil
                              otherButtonTitles:@"OK", nil
         ];
        [alert show];
    }
    
    

}


@end
