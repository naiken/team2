//
//  ViewController.h
//  Cambodia_Map
//
//  Created by Tang Chanrith on 4/30/14.
//  Copyright (c) 2014 RUPP. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UserData.h"
#import "CityMap.h"
#import "TitleScreen.h"

@interface CambodianMap : UIViewController{
    
    __weak IBOutlet UIScrollView *scrollerCountryMap;
}

@property (weak, nonatomic) IBOutlet UIImageView *imageCountryMap;
@property (weak, nonatomic) IBOutlet UIButton *buttonSihanoukVille;
@property (weak, nonatomic) IBOutlet UIButton *buttonSiemReap;
@property (weak, nonatomic) IBOutlet UIButton *buttonPhnomPenh;

@property (weak, nonatomic) IBOutlet UIImageView *sihanoukvilleLetter;
@property (weak, nonatomic) IBOutlet UIImageView *siemreapLetter;
@property (weak, nonatomic) IBOutlet UIImageView *phnompenhLetter;



- (void)iniUserData;
- (IBAction)back:(UIButton *)sender;

@end
