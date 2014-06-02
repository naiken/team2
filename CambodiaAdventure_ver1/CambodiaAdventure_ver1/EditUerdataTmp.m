//
//  EditUerdataTmp.m
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/24/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "EditUerdataTmp.h"

@interface EditUerdataTmp () {
    NSMutableArray *Level1Data;
    NSMutableArray *Level2Data;
    NSMutableArray *Level3Data;
}

@end

@implementation EditUerdataTmp

- (void)viewDidLoad {
    [super viewDidLoad];
    Level1Data = [NSMutableArray array];
    Level2Data = [NSMutableArray array];
    Level3Data = [NSMutableArray array];
}


- (IBAction)back:(UIButton *)sender {
    
    [self dismissViewControllerAnimated:YES completion:nil];
    
}

- (IBAction)changeUerdata:(UIButton *)sender {
    
    bool stage1Cleard = false, stage2Cleard = false, stage3Cleard = false,
    stage4Cleard = false, stage5Cleard = false, stage6Cleard = false,
    stage7Cleard = false, stage8Cleard = false, stage9Cleard = false;
    
    int stage1ST = 60, stage2ST = 60, stage3ST = 60,
     stage4ST = 60, stage5ST = 60, stage6ST = 60,
    stage7ST = 60, stage8ST = 60, stage9ST = 60;
    
    int lvl1Lots = 0, lvl2Lots = 0, lvl3Lots = 0;
    
    switch (sender.tag) {
            
        case 9:
            stage9Cleard = true;
            stage9ST = 10;
            lvl3Lots = 6;
        case 8:
            stage8Cleard = true;
            stage8ST = 10;
        case 7:
            stage7Cleard = true;
            stage7ST = 10;
        case 6:
            stage6Cleard = true;
            stage6ST = 10;
            lvl2Lots = 6;
        case 5:
            stage5Cleard = true;
            stage5ST = 10;
        case 4:
            stage4Cleard = true;
            stage4ST = 10;
        case 3:
            stage3Cleard = true;
            stage3ST = 10;
            lvl1Lots = 6;
        case 2:
            stage2Cleard = true;
            stage2ST = 10;
        case 1:
            stage1Cleard = true;
            stage1ST = 10;
        default:
            break;
    }
    
    
    [Level1Data insertObject:[NSNumber numberWithBool:stage1Cleard] atIndex:0];// check stage clear
    [Level1Data insertObject:[NSNumber numberWithInt:stage1ST] atIndex:1];// shortest time
    [Level1Data insertObject:[NSNumber numberWithBool:stage2Cleard] atIndex:2];
    [Level1Data insertObject:[NSNumber numberWithInt:stage2ST] atIndex:3];
    [Level1Data insertObject:[NSNumber numberWithBool:stage3Cleard] atIndex:4];
    [Level1Data insertObject:[NSNumber numberWithInt:stage3ST] atIndex:5];
    [Level1Data insertObject:[NSNumber numberWithInt:lvl1Lots] atIndex:6];// total lotas number
    
    [Level2Data insertObject:[NSNumber numberWithBool:stage4Cleard] atIndex:0];
    [Level2Data insertObject:[NSNumber numberWithInt:stage4ST] atIndex:1];
    [Level2Data insertObject:[NSNumber numberWithBool:stage5Cleard] atIndex:2];
    [Level2Data insertObject:[NSNumber numberWithInt:stage5ST] atIndex:3];
    [Level2Data insertObject:[NSNumber numberWithBool:stage6Cleard] atIndex:4];
    [Level2Data insertObject:[NSNumber numberWithInt:stage6ST] atIndex:5];
    [Level2Data insertObject:[NSNumber numberWithInt:lvl2Lots] atIndex:6];
    
    [Level3Data insertObject:[NSNumber numberWithBool:stage7Cleard] atIndex:0];
    [Level3Data insertObject:[NSNumber numberWithInt:stage7ST] atIndex:1];
    [Level3Data insertObject:[NSNumber numberWithBool:stage8Cleard] atIndex:2];
    [Level3Data insertObject:[NSNumber numberWithInt:stage8ST] atIndex:3];
    [Level3Data insertObject:[NSNumber numberWithBool:stage9Cleard] atIndex:4];
    [Level3Data insertObject:[NSNumber numberWithInt:stage9ST] atIndex:5];
    [Level3Data insertObject:[NSNumber numberWithInt:lvl3Lots] atIndex:6];
    
    UDSetValue(@"LVL1", Level1Data);
    UDSetValue(@"LVL2", Level2Data);
    UDSetValue(@"LVL3", Level3Data);
    UDPush();
}

- (IBAction)iniUserdata:(UIButton *)sender {
    
    [Level1Data insertObject:@NO atIndex:0];// check stage clear
    [Level1Data insertObject:@60 atIndex:1];// shortest time
    [Level1Data insertObject:@NO atIndex:2];
    [Level1Data insertObject:@60 atIndex:3];
    [Level1Data insertObject:@NO atIndex:4];
    [Level1Data insertObject:@60 atIndex:5];
    [Level1Data insertObject:@0 atIndex:6];// total lotas number
    
    [Level2Data insertObject:@NO atIndex:0];
    [Level2Data insertObject:@60 atIndex:1];
    [Level2Data insertObject:@NO atIndex:2];
    [Level2Data insertObject:@60 atIndex:3];
    [Level2Data insertObject:@NO atIndex:4];
    [Level2Data insertObject:@60 atIndex:5];
    [Level2Data insertObject:@0 atIndex:6];
    
    [Level3Data insertObject:@NO atIndex:0];
    [Level3Data insertObject:@60 atIndex:1];
    [Level3Data insertObject:@NO atIndex:2];
    [Level3Data insertObject:@60 atIndex:3];
    [Level3Data insertObject:@NO atIndex:4];
    [Level3Data insertObject:@60 atIndex:5];
    [Level3Data insertObject:@0 atIndex:6];
    
    UDSetValue(@"LVL1", Level1Data);
    UDSetValue(@"LVL2", Level2Data);
    UDSetValue(@"LVL3", Level3Data);
    UDPush();
    
    
}
@end
