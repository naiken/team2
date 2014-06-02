//
//  ViewController.m
//  Cambodia_Map
//
//  Created by Tang Chanrith on 4/30/14.
//  Copyright (c) 2014 RUPP. All rights reserved.
//

#import "CambodianMap.h"

int scaleCount=0;
int scaleChange=1;
float scaleSize=1.0f;

@interface CambodianMap (){
    NSTimer *_timer;
    NSArray *_buttons;
    
}

@end

@implementation CambodianMap

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _buttons=[[NSArray alloc] initWithObjects:self.buttonSihanoukVille,self.buttonPhnomPenh,self.buttonSiemReap, nil];
    
    //User Data
    [self loadUserData];
}

-(void)viewWillAppear:(BOOL)animated {
    //Scale Animation
    _timer=[NSTimer scheduledTimerWithTimeInterval:0.1f target:self selector:@selector(timerUpdate:) userInfo:nil repeats:YES];
}

- (void)timerUpdate:(NSTimer *)sender{
    
    if(scaleCount >= 15){
        scaleChange *= -1;
        scaleCount = 0;
    }
    scaleCount++;
    scaleSize += 0.01 * scaleChange;
    for(UIButton *btn in _buttons){
        if(btn.enabled)
            btn.transform=CGAffineTransformMakeScale(scaleSize, scaleSize);
    }
}

- (void)iniUserData {
    
    NSMutableArray *Level1Data = [NSMutableArray array];
    [Level1Data insertObject:@NO atIndex:0];// check stage clear
    [Level1Data insertObject:@60 atIndex:1];// shortest time
    [Level1Data insertObject:@NO atIndex:2];
    [Level1Data insertObject:@60 atIndex:3];
    [Level1Data insertObject:@NO atIndex:4];
    [Level1Data insertObject:@60 atIndex:5];
    [Level1Data insertObject:@0 atIndex:6];// total lotas number
    
    NSMutableArray *Level2Data = [NSMutableArray array];
    [Level2Data insertObject:@NO atIndex:0];
    [Level2Data insertObject:@60 atIndex:1];
    [Level2Data insertObject:@NO atIndex:2];
    [Level2Data insertObject:@60 atIndex:3];
    [Level2Data insertObject:@NO atIndex:4];
    [Level2Data insertObject:@60 atIndex:5];
    [Level2Data insertObject:@0 atIndex:6];
    
    NSMutableArray *Level3Data = [NSMutableArray array];
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
    
//    NSMutableArray *tmp;
//    tmp = UDGetDataLevel1(@"LEVEL1");
//    BOOL tmp1 = [[tmp objectAtIndex:0] boolValue];
//    int tmp2 = [[tmp objectAtIndex:1] intValue];

}

- (void)loadUserData{
    
//    UDRemoveAtKey(@"LVL1");
//    UDRemoveAtKey(@"LVL2");
//    UDRemoveAtKey(@"LVL3");
//    UDPush();
    
    // initialize user data
    if ([UDGetValue(@"LVL1") isEqual:@"NULL"]) {
        [self iniUserData];
    }
    
    NSMutableArray *tmp;
    tmp = UDGetValue(@"LVL1");
    BOOL tmp1 = [[tmp objectAtIndex:0] boolValue];
    int tmp2 = [[tmp objectAtIndex:1] intValue];
    BOOL tmp3 = [[tmp objectAtIndex:2] boolValue];
    int tmp4 = [[tmp objectAtIndex:3] intValue];
    BOOL tmp5 = [[tmp objectAtIndex:4] boolValue];
    int tmp6 = [[tmp objectAtIndex:5] intValue];
    
    // Button, user can click or not
    self.buttonSihanoukVille.enabled = TRUE;
    if ([[UDGetValue(@"LVL1") objectAtIndex:6] intValue] >= 6 && [[UDGetValue(@"LVL1") objectAtIndex:4] boolValue]) {
        self.buttonSiemReap.enabled =  TRUE;
    } else {
        self.buttonSiemReap.enabled = NO;
    }
    
    if ([[UDGetValue(@"LVL2") objectAtIndex:6] intValue] >= 6 && [[UDGetValue(@"LVL2") objectAtIndex:4] boolValue]) {
        self.buttonPhnomPenh.enabled = TRUE;
    } else {
        self.buttonPhnomPenh.enabled = NO;
    }
    
    // Set BtnImage
    for(UIButton *btn in _buttons){
            [btn setImage:[UIImage imageNamed:@"button-playable.png"] forState:UIControlStateNormal];
    }
}

- (IBAction)CityButton:(UIButton *)sender {
    
    CityMap *cityMap = [self.storyboard instantiateViewControllerWithIdentifier:@"CityMapID"];
    cityMap.tagToCityMap = sender.tag;
    [self presentViewController:cityMap animated:YES completion:nil];
    
    [_timer invalidate];
}


@end
