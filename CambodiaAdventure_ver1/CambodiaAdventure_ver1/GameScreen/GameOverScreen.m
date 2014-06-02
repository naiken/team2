//
//  GameOverScreen.m
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/20/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "GameOverScreen.h"

@interface GameOverScreen ()

@end


@implementation GameOverScreen

- (void)viewDidLoad {
    [super viewDidLoad];

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

-(IBAction)retry:(id)sender {
    
    // 前画面に戻る
    [self dismissViewControllerAnimated:YES completion:^{
        //
    }];
    
}
@end
