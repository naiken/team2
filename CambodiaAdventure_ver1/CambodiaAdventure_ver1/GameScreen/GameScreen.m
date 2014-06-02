//
//  Level1Stage1.m
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/10/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import <CoreMotion/CoreMotion.h>
#import "GameScreen.h"

@interface GameScreen () {
    
    int For4inch;
    
    CreateObject *_createObject;
    CMMotionManager *_motionManager;
    MoveObject *_moveObject;
    
    NSMutableArray* cars;
    NSMutableArray* scooters;
    NSMutableArray* tuktuks;
    NSMutableArray* bigcars;
    NSMutableArray* cyclos;
    
    NSMutableArray* vehiclesR;// put imageview of right direction vehicles
    NSMutableArray* vehiclesL;
    NSMutableArray* vehiclesD;
    NSMutableArray* vehiclesU;
    NSMutableArray* vehiclesRD;
    NSMutableArray* vehiclesLU;
    NSMutableArray* blacks;
    
    NSTimer *gameStartTimer;
    NSTimer *gameClearTimer;
    NSTimer *gameOverTimer;
    bool manMoving;// to stop man animation
    bool wasCrashed;// to distinguish crashed or time up
    int particleCounter;// counter for particle
    bool isPausing;
    
    AVAudioPlayer *audio;
    AVAudioPlayer *overAudio;
    AVAudioPlayer *clearAudio;
    
    // Timer
    NSTimer *_timerprogress;
    float secord;
    float loadsec;
    float divsec;
}



@property (strong, nonatomic) UIImageView *backgroundImgV;
@property (strong, nonatomic) UIImageView *manImgV;
@property (strong, nonatomic) UIImageView *blackR;
@property (strong, nonatomic) UIImageView *blackL;
@property (strong, nonatomic) UIImageView *blackD;
@property (strong, nonatomic) UIImageView *blackU;


// Car
@property (strong, nonatomic) UIImageView *carImgVR1;
@property (strong, nonatomic) UIImageView *carImgVR2;
@property (strong, nonatomic) UIImageView *carImgVR3;
@property (strong, nonatomic) UIImageView *carImgVR4;
@property (strong, nonatomic) UIImageView *carImgVR5;

@property (strong, nonatomic) UIImageView *carImgVL1;
@property (strong, nonatomic) UIImageView *carImgVL2;
@property (strong, nonatomic) UIImageView *carImgVL3;
@property (strong, nonatomic) UIImageView *carImgVL4;
@property (strong, nonatomic) UIImageView *carImgVL5;

@property (strong, nonatomic) UIImageView *carImgVD1;
@property (strong, nonatomic) UIImageView *carImgVD2;
@property (strong, nonatomic) UIImageView *carImgVD3;

@property (strong, nonatomic) UIImageView *carImgVU1;
@property (strong, nonatomic) UIImageView *carImgVU2;
@property (strong, nonatomic) UIImageView *carImgVU3;

@property (strong, nonatomic) UIImageView *carImgVRD1;
@property (strong, nonatomic) UIImageView *carImgVRD2;

@property (strong, nonatomic) UIImageView *carImgVLU1;
@property (strong, nonatomic) UIImageView *carImgVLU2;


// Scooter
@property (strong, nonatomic) UIImageView *scooterImgVR1;
@property (strong, nonatomic) UIImageView *scooterImgVR2;
@property (strong, nonatomic) UIImageView *scooterImgVR3;
@property (strong, nonatomic) UIImageView *scooterImgVR4;
@property (strong, nonatomic) UIImageView *scooterImgVR5;

@property (strong, nonatomic) UIImageView *scooterImgVL1;
@property (strong, nonatomic) UIImageView *scooterImgVL2;
@property (strong, nonatomic) UIImageView *scooterImgVL3;
@property (strong, nonatomic) UIImageView *scooterImgVL4;
@property (strong, nonatomic) UIImageView *scooterImgVL5;

@property (strong, nonatomic) UIImageView *scooterImgVD1;
@property (strong, nonatomic) UIImageView *scooterImgVD2;
@property (strong, nonatomic) UIImageView *scooterImgVD3;

@property (strong, nonatomic) UIImageView *scooterImgVU1;
@property (strong, nonatomic) UIImageView *scooterImgVU2;
@property (strong, nonatomic) UIImageView *scooterImgVU3;

@property (strong, nonatomic) UIImageView *scooterImgVRD1;
@property (strong, nonatomic) UIImageView *scooterImgVRD2;

@property (strong, nonatomic) UIImageView *scooterImgVLU1;
@property (strong, nonatomic) UIImageView *scooterImgVLU2;


// Tuktuk
@property (strong, nonatomic) UIImageView *tuktukImgVR1;
@property (strong, nonatomic) UIImageView *tuktukImgVR2;
@property (strong, nonatomic) UIImageView *tuktukImgVR3;
@property (strong, nonatomic) UIImageView *tuktukImgVR4;
@property (strong, nonatomic) UIImageView *tuktukImgVR5;

@property (strong, nonatomic) UIImageView *tuktukImgVL1;
@property (strong, nonatomic) UIImageView *tuktukImgVL2;
@property (strong, nonatomic) UIImageView *tuktukImgVL3;
@property (strong, nonatomic) UIImageView *tuktukImgVL4;
@property (strong, nonatomic) UIImageView *tuktukImgVL5;

@property (strong, nonatomic) UIImageView *tuktukImgVD1;
@property (strong, nonatomic) UIImageView *tuktukImgVD2;
@property (strong, nonatomic) UIImageView *tuktukImgVD3;

@property (strong, nonatomic) UIImageView *tuktukImgVU1;
@property (strong, nonatomic) UIImageView *tuktukImgVU2;
@property (strong, nonatomic) UIImageView *tuktukImgVU3;

@property (strong, nonatomic) UIImageView *tuktukImgVRD1;
@property (strong, nonatomic) UIImageView *tuktukImgVRD2;

@property (strong, nonatomic) UIImageView *tuktukImgVLU1;
@property (strong, nonatomic) UIImageView *tuktukImgVLU2;


// BigCar
@property (strong, nonatomic) UIImageView *bigcarImgVR1;
@property (strong, nonatomic) UIImageView *bigcarImgVR2;
@property (strong, nonatomic) UIImageView *bigcarImgVR3;
@property (strong, nonatomic) UIImageView *bigcarImgVR4;
@property (strong, nonatomic) UIImageView *bigcarImgVR5;

@property (strong, nonatomic) UIImageView *bigcarImgVL1;
@property (strong, nonatomic) UIImageView *bigcarImgVL2;
@property (strong, nonatomic) UIImageView *bigcarImgVL3;
@property (strong, nonatomic) UIImageView *bigcarImgVL4;
@property (strong, nonatomic) UIImageView *bigcarImgVL5;

@property (strong, nonatomic) UIImageView *bigcarImgVD1;
@property (strong, nonatomic) UIImageView *bigcarImgVD2;
@property (strong, nonatomic) UIImageView *bigcarImgVD3;

@property (strong, nonatomic) UIImageView *bigcarImgVU1;
@property (strong, nonatomic) UIImageView *bigcarImgVU2;
@property (strong, nonatomic) UIImageView *bigcarImgVU3;

@property (strong, nonatomic) UIImageView *bigcarImgVRD1;
@property (strong, nonatomic) UIImageView *bigcarImgVRD2;

@property (strong, nonatomic) UIImageView *bigcarImgVLU1;
@property (strong, nonatomic) UIImageView *bigcarImgVLU2;


//Cyclo
@property (strong, nonatomic) UIImageView *cycloImgVR1;
@property (strong, nonatomic) UIImageView *cycloImgVR2;


@property (strong, nonatomic) UIImageView *cycloImgVL1;
@property (strong, nonatomic) UIImageView *cycloImgVL2;

@property (strong, nonatomic) UIImageView *cycloImgVD1;
@property (strong, nonatomic) UIImageView *cycloImgVD2;

@property (strong, nonatomic) UIImageView *cycloImgVU1;
@property (strong, nonatomic) UIImageView *cycloImgVU2;

-(void)main;

@end












@implementation GameScreen



-(void)initWithStageNum:(int)stageNum {
    
    For4inch = 44;
    
    // Objects
    _createObject = [[CreateObject alloc] init];
    _motionManager = [[CMMotionManager alloc] init];
    
    // Create Man
    _manImgV = [_createObject makeImageView:230+For4inch :150 :20 :40 name:@"chara_1.png"];
    manMoving = false;
    wasCrashed = false;
    isPausing = false;
    particleCounter = 0;
    
    switch (stageNum) {
        case 1:
            _moveObject = [[MoveObject alloc] initWithStageNum:1];
            _lvl = 1;
            _stageNum = 1;
            _sec = 20;
            _backgroundImgV = [_createObject makeImageView:150.0f+For4inch :-450.0f :480.0f :640.0f name:@"Level1Stage1_newmarket.ex.png"];
            
            // Create scooter
            //            _scooterImgVR1 = [_createObject makeVehicleImageView:100 :10 name:@"scooter.png" direction:1];
            //            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :80 name:@"scooter.png" direction:1];
            _scooterImgVR3 = [_createObject makeVehicleImageView:150 :-200 name:@"scooter.png" direction:1];
            _scooterImgVR4 = [_createObject makeVehicleImageView:-10 :-230 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-40 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-60 name:@"scooter.png" direction:2];
            _scooterImgVL3 = [_createObject makeVehicleImageView:50 :-290 name:@"scooter.png" direction:2];
            //            _scooterImgVL4 = [_createObject makeVehicleImageView:500 :-320 name:@"scooter.png" direction:2];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:-110 :50 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:100 :30 name:@"tuktuk.png" direction:1];
            //            _tuktukImgVR3 = [_createObject makeVehicleImageView:200 :-240 name:@"tuktuk.png" direction:1];
            //            _tuktukImgVR4 = [_createObject makeVehicleImageView:110 :-210 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:200 :-50 name:@"tuktuk.png" direction:2];
            //            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-10 name:@"tuktuk.png" direction:2];
            _tuktukImgVL3 = [_createObject makeVehicleImageView:100 :-320 name:@"tuktuk.png" direction:2];
            _tuktukImgVL4 = [_createObject makeVehicleImageView:60 :-360 name:@"tuktuk.png" direction:2];
            
            
            // Create cyclo
            _cycloImgVR1 = [_createObject makeVehicleImageView:-100 :55 name:@"cyclo.png" direction:1];
            _cycloImgVR2 = [_createObject makeVehicleImageView:200 :-210 name:@"cyclo.png" direction:1];
            
            _cycloImgVL1 = [_createObject makeVehicleImageView:300 :-80 name:@"cyclo.png" direction:2];
            _cycloImgVL2 = [_createObject makeVehicleImageView:0 :-315 name:@"cyclo.png" direction:2];
            
            scooters = [NSMutableArray array];
            
            //            [scooters addObject:_scooterImgVR1];
            //            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVR3];
            [scooters addObject:_scooterImgVR4];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVL3];
            //            [scooters addObject:_scooterImgVL4];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            //            [tuktuks addObject:_tuktukImgVR3];
            //            [tuktuks addObject:_tuktukImgVR4];
            [tuktuks addObject:_tuktukImgVL1];
            //            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVL3];
            [tuktuks addObject:_tuktukImgVL4];
            
            cyclos = [NSMutableArray array];
            
            [cyclos addObject:_cycloImgVR1];
            [cyclos addObject:_cycloImgVR2];
            [cyclos addObject:_cycloImgVL1];
            [cyclos addObject:_cycloImgVL2];
            
            vehiclesR = [NSMutableArray array];
            
            //            [vehiclesR addObject:_scooterImgVR1];
            //            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_scooterImgVR3];
            [vehiclesR addObject:_scooterImgVR4];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            //            [vehiclesR addObject:_tuktukImgVR3];
            //            [vehiclesR addObject:_tuktukImgVR4];
            [vehiclesR addObject:_cycloImgVR1];
            [vehiclesR addObject:_cycloImgVR2];
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_scooterImgVL3];
            //            [vehiclesL addObject:_scooterImgVL4];
            [vehiclesL addObject:_tuktukImgVL1];
            //            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_tuktukImgVL3];
            [vehiclesL addObject:_tuktukImgVL4];
            [vehiclesL addObject:_cycloImgVL1];
            [vehiclesL addObject:_cycloImgVL2];
            
            _blackR = [_createObject makeImageView:630+For4inch :-450 :284 :640 name:@"black.png"];
            _blackL = [_createObject makeImageView:-90-44+For4inch :-450 :284 :640 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            
            
            break;
            
            
            
            
        case 2:
            _moveObject = [[MoveObject alloc] initWithStageNum:2];
            _lvl = 1;
            _stageNum = 2;
            _sec = 30;
            _backgroundImgV = [_createObject makeImageView:-280.0f+For4inch :-700.0f :580.0f :890.0f name:@"Level1Stage2_goldlion.ex.png"];
            
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:-100 :-495 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-350 name:@"car.png" direction:1];
            _carImgVR3 = [_createObject makeVehicleImageView:-100 :-120 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-340 name:@"car.png" direction:2];
            _carImgVL2 = [_createObject makeVehicleImageView:-100 :-240 name:@"car.png" direction:2];
            _carImgVL3 = [_createObject makeVehicleImageView:-100 :10 name:@"car.png" direction:2];
            
            
            // Create scooter
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :-500 name:@"scooter.png" direction:1];
            _scooterImgVR3 = [_createObject makeVehicleImageView:-100 :-450 name:@"scooter.png" direction:1];
            _scooterImgVR4 = [_createObject makeVehicleImageView:-100 :-135 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-590 name:@"scooter.png" direction:2];
            //            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-255 name:@"scooter.png" direction:2];
            _scooterImgVL3 = [_createObject makeVehicleImageView:500 :-330 name:@"scooter.png" direction:2];
            _scooterImgVL4 = [_createObject makeVehicleImageView:500 :-170 name:@"scooter.png" direction:2];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:-110 :-480 name:@"tuktuk.png" direction:1];
            _tuktukImgVR3 = [_createObject makeVehicleImageView:170 :-125 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-600 name:@"tuktuk.png" direction:2];
            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-240 name:@"tuktuk.png" direction:2];
            _tuktukImgVL3 = [_createObject makeVehicleImageView:600 :-360 name:@"tuktuk.png" direction:2];
            //            _tuktukImgVL4 = [_createObject makeVehicleImageView:600 :25 name:@"tuktuk.png" direction:2];
            
            
            // Create cyclo
            _cycloImgVR1 = [_createObject makeVehicleImageView:-100 :40 name:@"cyclo.png" direction:1];
            _cycloImgVR2 = [_createObject makeVehicleImageView:200 :-130 name:@"cyclo.png" direction:1];
            
            
            _cycloImgVL1 = [_createObject makeVehicleImageView:300 :-10 name:@"cyclo.png" direction:2];
            _cycloImgVL2 = [_createObject makeVehicleImageView:0 :-250 name:@"cyclo.png" direction:2];
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            [cars addObject:_carImgVR3];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVL3];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVR3];
            [scooters addObject:_scooterImgVR4];
            [scooters addObject:_scooterImgVL1];
            //            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVL3];
            [scooters addObject:_scooterImgVL4];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR3];
            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVL3];
            //            [tuktuks addObject:_tuktukImgVL4];
            
            cyclos = [NSMutableArray array];
            
            [cyclos addObject:_cycloImgVR1];
            [cyclos addObject:_cycloImgVR2];
            [cyclos addObject:_cycloImgVL1];
            [cyclos addObject:_cycloImgVL2];
            
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            [vehiclesR addObject:_carImgVR3];
            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_scooterImgVR3];
            [vehiclesR addObject:_scooterImgVR4];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR3];
            [vehiclesR addObject:_cycloImgVR1];
            [vehiclesR addObject:_cycloImgVR2];
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_carImgVL3];
            [vehiclesL addObject:_scooterImgVL1];
            //            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_scooterImgVL3];
            [vehiclesL addObject:_scooterImgVL4];
            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_tuktukImgVL3];
            //            [vehiclesL addObject:_tuktukImgVL4];
            [vehiclesL addObject:_cycloImgVL1];
            [vehiclesL addObject:_cycloImgVL2];
            
            
            _blackR = [_createObject makeImageView:300+For4inch :-700 :284 :890 name:@"black.png"];
            _blackL = [_createObject makeImageView:-520-44+For4inch :-700 :284 :890 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            
            break;
            
            
        case 3:
            _moveObject = [[MoveObject alloc] initWithStageNum:3];
            _lvl = 1;
            _stageNum = 3;
            _sec = 30;
            _backgroundImgV = [_createObject makeImageView:-50.0f+For4inch :-1200.0f :580.0f :1390.0f name:@"Level1Stage3_beach.ex.png"];
            
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:-100 :-1010 name:@"car.png" direction:1];
            _carImgVR3 = [_createObject makeVehicleImageView:-150 :-200 name:@"car.png" direction:1];
            _carImgVR4 = [_createObject makeVehicleImageView:10 :-650 name:@"car.png" direction:1];
            _carImgVR5 = [_createObject makeVehicleImageView:10 :-715 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-790 name:@"car.png" direction:2];
            _carImgVL2 = [_createObject makeVehicleImageView:100 :-1080 name:@"car.png" direction:2];
            _carImgVL3 = [_createObject makeVehicleImageView:-10 :-320 name:@"car.png" direction:2];
            _carImgVL4 = [_createObject makeVehicleImageView:-100 :-490 name:@"car.png" direction:2];
            
            
            // Create scooter
            _scooterImgVR1 = [_createObject makeVehicleImageView:200 :-30 name:@"scooter.png" direction:1];
            //            _scooterImgVR2 = [_createObject makeVehicleImageView:70 :-205 name:@"scooter.png" direction:1];
            _scooterImgVR4 = [_createObject makeVehicleImageView:300 :-630 name:@"scooter.png" direction:1];
            _scooterImgVR5 = [_createObject makeVehicleImageView:-100 :-980 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :25 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:100 :-50 name:@"scooter.png" direction:2];
            _scooterImgVL4 = [_createObject makeVehicleImageView:-50 :-830 name:@"scooter.png" direction:2];
            _scooterImgVL5 = [_createObject makeVehicleImageView:500 :-1080 name:@"scooter.png" direction:2];
            
            
            // Create tuktuk
            _tuktukImgVR2 = [_createObject makeVehicleImageView:115 :-230 name:@"tuktuk.png" direction:1];
            _tuktukImgVR3 = [_createObject makeVehicleImageView:60 :-460 name:@"tuktuk.png" direction:1];
            _tuktukImgVR4 = [_createObject makeVehicleImageView:110 :-1020 name:@"tuktuk.png" direction:1];
            _tuktukImgVR5 = [_createObject makeVehicleImageView:-100 :-710 name:@"tuktuk.png" direction:1];
            //            _tuktukImgVR1 = [_createObject makeVehicleImageView:110 :-600 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:25 :-40 name:@"tuktuk.png" direction:2];
            _tuktukImgVL3 = [_createObject makeVehicleImageView:600 :-81 name:@"tuktuk.png" direction:2];
            _tuktukImgVL4 = [_createObject makeVehicleImageView:-200 :-1050 name:@"tuktuk.png" direction:2];
            
            
            // Create cyclo
            _cycloImgVR1 = [_createObject makeVehicleImageView:70 :-205 name:@"cyclo.png" direction:1];
            _cycloImgVR2 = [_createObject makeVehicleImageView:110 :-640 name:@"cyclo.png" direction:1];
            
            _cycloImgVL1 = [_createObject makeVehicleImageView:300 :-70 name:@"cyclo.png" direction:2];
            _cycloImgVL2 = [_createObject makeVehicleImageView:-100 :-500 name:@"cyclo.png" direction:2];
            
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR3];
            [cars addObject:_carImgVR4];
            [cars addObject:_carImgVR5];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVL3];
            [cars addObject:_carImgVL4];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR1];
            //            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVR4];
            [scooters addObject:_scooterImgVR5];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVL4];
            [scooters addObject:_scooterImgVL5];
            
            tuktuks = [NSMutableArray array];
            
            //            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            [tuktuks addObject:_tuktukImgVR3];
            [tuktuks addObject:_tuktukImgVR4];
            [tuktuks addObject:_tuktukImgVR5];
            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL3];
            [tuktuks addObject:_tuktukImgVL4];
            
            cyclos = [NSMutableArray array];
            
            [cyclos addObject:_cycloImgVR1];
            [cyclos addObject:_cycloImgVR2];
            [cyclos addObject:_cycloImgVL1];
            [cyclos addObject:_cycloImgVL2];
            
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR3];
            [vehiclesR addObject:_carImgVR4];
            [vehiclesR addObject:_carImgVR5];
            [vehiclesR addObject:_scooterImgVR1];
            //            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_scooterImgVR4];
            [vehiclesR addObject:_scooterImgVR5];
            //            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            [vehiclesR addObject:_tuktukImgVR3];
            [vehiclesR addObject:_tuktukImgVR4];
            [vehiclesR addObject:_tuktukImgVR5];
            [vehiclesR addObject:_cycloImgVR1];
            [vehiclesR addObject:_cycloImgVR2];
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_carImgVL3];
            [vehiclesL addObject:_carImgVL4];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_scooterImgVL4];
            [vehiclesL addObject:_scooterImgVL5];
            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL3];
            [vehiclesL addObject:_tuktukImgVL4];
            [vehiclesL addObject:_cycloImgVL1];
            [vehiclesL addObject:_cycloImgVL2];
            
            _blackR = [_createObject makeImageView:530+For4inch :-1200 :284 :1390 name:@"black.png"];
            _blackL = [_createObject makeImageView:-290-44+For4inch :-1200 :284 :1390 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            
            break;
            
            
            
        case 4:
            _moveObject = [[MoveObject alloc] initWithStageNum:4];
            _lvl = 2;
            _stageNum = 1;
            _sec = 20;
            _backgroundImgV = [_createObject makeImageView:-280.0f :-450.0f :680.0f :640.0f name:@"L2-1.png"];
            
            
            // Create car
            //            _carImgVR1 = [_createObject makeVehicleImageView:100 :10 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-180 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-50 name:@"car.png" direction:2];
            _carImgVL2 = [_createObject makeVehicleImageView:-100 :-290 name:@"car.png" direction:2];
            
            //            _carImgVD1 = [_createObject makeVehicleImageView:-10 :-150 name:@"car.png" direction:3];
            
            _carImgVU1 = [_createObject makeVehicleImageView:-120 :500 name:@"car.png" direction:4];
            
            
            // Create scooter
            _scooterImgVR1 = [_createObject makeVehicleImageView:-100 :0 name:@"scooter.png" direction:1];
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :-205 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-50 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-320 name:@"scooter.png" direction:2];
            
            _scooterImgVD1 = [_createObject makeVehicleImageView:-30 :-50 name:@"scooter.png" direction:3];
            
            _scooterImgVU1 = [_createObject makeVehicleImageView:-130 :150 name:@"scooter.png" direction:4];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:110 :-210 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:200 :-195 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-30 name:@"tuktuk.png" direction:2];
            //            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-300 name:@"tuktuk.png" direction:2];
            
            _tuktukImgVD1 = [_createObject makeVehicleImageView:-20 :100 name:@"tuktuk.png" direction:3];
            
            _tuktukImgVU1 = [_createObject makeVehicleImageView:-150 :240 name:@"tuktuk.png" direction:4];
            
            
            // Create cyclo
            _cycloImgVR1 = [_createObject makeVehicleImageView:70 :10 name:@"cyclo.png" direction:1];
            //            _cycloImgVR2 = [_createObject makeVehicleImageView:110 :-640 name:@"cyclo.png" direction:1];
            
            _cycloImgVL1 = [_createObject makeVehicleImageView:300 :-80 name:@"cyclo.png" direction:2];
            _cycloImgVL2 = [_createObject makeVehicleImageView:-100 :-300 name:@"cyclo.png" direction:2];
            
            _cycloImgVD1 = [_createObject makeVehicleImageView:-10 :-150 name:@"cyclo.png" direction:3];
            
            
            cars = [NSMutableArray array];
            
            //            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVL2];
            //            [cars addObject:_carImgVD1];
            [cars addObject:_carImgVU1];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR1];
            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVD1];
            [scooters addObject:_scooterImgVU1];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            [tuktuks addObject:_tuktukImgVL1];
            //            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVD1];
            [tuktuks addObject:_tuktukImgVU1];
            
            cyclos = [NSMutableArray array];
            
            [cyclos addObject:_cycloImgVR1];
            //            [cyclos addObject:_cycloImgVR2];
            [cyclos addObject:_cycloImgVL1];
            [cyclos addObject:_cycloImgVL2];
            [cyclos addObject:_cycloImgVD1];
            
            vehiclesR = [NSMutableArray array];
            
            //            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            [vehiclesR addObject:_scooterImgVR1];
            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            [vehiclesR addObject:_cycloImgVR1];
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_cycloImgVL1];
            [vehiclesL addObject:_cycloImgVL2];
            
            vehiclesD = [NSMutableArray array];
            
            //            [vehiclesD addObject:_carImgVD1];
            [vehiclesD addObject:_scooterImgVD1];
            [vehiclesD addObject:_tuktukImgVD1];
            [vehiclesD addObject:_cycloImgVD1];
            
            
            vehiclesU = [NSMutableArray array];
            
            [vehiclesU addObject:_carImgVU1];
            [vehiclesU addObject:_scooterImgVU1];
            [vehiclesU addObject:_tuktukImgVU1];
            
            _blackR = [_createObject makeImageView:400-44+For4inch :-450 :284 :640 name:@"black.png"];
            _blackL = [_createObject makeImageView:-520-88+For4inch :-450 :284 :640 name:@"black.png"];
            _blackD = [_createObject makeImageView:-280 :190 :680 :284 name:@"black.png"];
            _blackU = [_createObject makeImageView:-280 :-690-44 :680 :284 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            [blacks addObject:_blackD];
            [blacks addObject:_blackU];
            
            break;
            
        case 5:
            _moveObject = [[MoveObject alloc] initWithStageNum:5];
            _lvl = 2;
            _stageNum = 2;
            _sec = 40;
            _backgroundImgV = [_createObject makeImageView:-590.0f+For4inch :-450.0f :880.0f :640.0f name:@"L2-2.png"];
            
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:30 :10 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-270 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-50 name:@"car.png" direction:2];
            //            _carImgVL2 = [_createObject makeVehicleImageView:-100 :-310 name:@"car.png" direction:2];
            _carImgVL3 = [_createObject makeVehicleImageView:-100 :-350 name:@"car.png" direction:2];
            
            //            _carImgVD1 = [_createObject makeVehicleImageView:50 :160 name:@"car.png" direction:3];
            _carImgVD2 = [_createObject makeVehicleImageView:-120 :-150 name:@"car.png" direction:3];
            _carImgVD3 = [_createObject makeVehicleImageView:-450 :-150 name:@"car.png" direction:3];
            
            _carImgVU1 = [_createObject makeVehicleImageView:-340 :500 name:@"car.png" direction:4];
            _carImgVU2 = [_createObject makeVehicleImageView:-390 :500 name:@"car.png" direction:4];
            
            
            // Create scooter
            _scooterImgVR1 = [_createObject makeVehicleImageView:200 :0 name:@"scooter.png" direction:1];
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :55 name:@"scooter.png" direction:1];
            //            _scooterImgVR3 = [_createObject makeVehicleImageView:-100 :-205 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-50 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-320 name:@"scooter.png" direction:2];
            
            _scooterImgVD1 = [_createObject makeVehicleImageView:-450 :50 name:@"scooter.png" direction:3];
            
            //            _scooterImgVU1 = [_createObject makeVehicleImageView:130 :50 name:@"scooter.png" direction:4];
            _scooterImgVU2 = [_createObject makeVehicleImageView:-10 :80 name:@"scooter.png" direction:4];
            _scooterImgVU3 = [_createObject makeVehicleImageView:-30 :150 name:@"scooter.png" direction:4];
            
            
            // Create tuktuk
            //            _tuktukImgVR1 = [_createObject makeVehicleImageView:110 :-20 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:250 :-205 name:@"tuktuk.png" direction:1];
            _tuktukImgVR3 = [_createObject makeVehicleImageView:200 :-315 name:@"tuktuk.png" direction:1];
            
            //            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-50 name:@"tuktuk.png" direction:2];
            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-100 name:@"tuktuk.png" direction:2];
            _tuktukImgVL3 = [_createObject makeVehicleImageView:600 :-300 name:@"tuktuk.png" direction:2];
            
            _tuktukImgVD1 = [_createObject makeVehicleImageView:-100 :100 name:@"tuktuk.png" direction:3];
            _tuktukImgVD2 = [_createObject makeVehicleImageView:-490 :100 name:@"tuktuk.png" direction:3];
            
            _tuktukImgVU1 = [_createObject makeVehicleImageView:100 :240 name:@"tuktuk.png" direction:4];
            //            _tuktukImgVU2 = [_createObject makeVehicleImageView:-370 :240 name:@"tuktuk.png" direction:4];
            
            
            // Create bigcar
            _bigcarImgVR1 = [_createObject makeVehicleImageView:-150 :10 name:@"bigcar.png" direction:1];
            //            _bigcarImgVR2 = [_createObject makeVehicleImageView:-150 :-235 name:@"bigcar.png" direction:1];
            _bigcarImgVR3 = [_createObject makeVehicleImageView:-150 :-260 name:@"bigcar.png" direction:1];
            
            //            _bigcarImgVL1 = [_createObject makeVehicleImageView:-150 :-100 name:@"bigcar.png" direction:2];
            _bigcarImgVL2 = [_createObject makeVehicleImageView:150 :-80 name:@"bigcar.png" direction:2];
            //            _bigcarImgVL3 = [_createObject makeVehicleImageView:-150 :-330 name:@"bigcar.png" direction:2];
            
            
            // Create cyclo
            _cycloImgVR1 = [_createObject makeVehicleImageView:-50 :-25 name:@"cyclo.png" direction:1];
            _cycloImgVR2 = [_createObject makeVehicleImageView:110 :-235 name:@"cyclo.png" direction:1];
            
            _cycloImgVL1 = [_createObject makeVehicleImageView:300 :-50 name:@"cyclo.png" direction:2];
            _cycloImgVL2 = [_createObject makeVehicleImageView:-100 :-320 name:@"cyclo.png" direction:2];
            
            _cycloImgVD1 = [_createObject makeVehicleImageView:50 :150 name:@"cyclo.png" direction:3];
            _cycloImgVD2 = [_createObject makeVehicleImageView:-470 :150 name:@"cyclo.png" direction:3];
            
            _cycloImgVU1 = [_createObject makeVehicleImageView:-30 :-150 name:@"cyclo.png" direction:4];
            _cycloImgVU2 = [_createObject makeVehicleImageView:-370 :350 name:@"cyclo.png" direction:4];
            
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            [cars addObject:_carImgVL1];
            //            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVL3];
            //            [cars addObject:_carImgVD1];
            [cars addObject:_carImgVD2];
            [cars addObject:_carImgVD3];
            [cars addObject:_carImgVU1];
            [cars addObject:_carImgVU2];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR1];
            [scooters addObject:_scooterImgVR2];
            //            [scooters addObject:_scooterImgVR3];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVD1];
            //            [scooters addObject:_scooterImgVU1];
            [scooters addObject:_scooterImgVU2];
            [scooters addObject:_scooterImgVU3];
            
            tuktuks = [NSMutableArray array];
            
            //            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            [tuktuks addObject:_tuktukImgVR3];
            //            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVL3];
            [tuktuks addObject:_tuktukImgVD1];
            [tuktuks addObject:_tuktukImgVD2];
            [tuktuks addObject:_tuktukImgVU1];
            //            [tuktuks addObject:_tuktukImgVU2];
            
            bigcars = [NSMutableArray array];
            
            [bigcars addObject:_bigcarImgVR1];
            //            [bigcars addObject:_bigcarImgVR2];
            [bigcars addObject:_bigcarImgVR3];
            //            [bigcars addObject:_bigcarImgVL1];
            [bigcars addObject:_bigcarImgVL2];
            //            [bigcars addObject:_bigcarImgVL3];
            
            cyclos = [NSMutableArray array];
            
            [cyclos addObject:_cycloImgVR1];
            [cyclos addObject:_cycloImgVR2];
            [cyclos addObject:_cycloImgVL1];
            [cyclos addObject:_cycloImgVL2];
            [cyclos addObject:_cycloImgVD1];
            [cyclos addObject:_cycloImgVD2];
            [cyclos addObject:_cycloImgVU1];
            [cyclos addObject:_cycloImgVU2];
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            [vehiclesR addObject:_scooterImgVR1];
            [vehiclesR addObject:_scooterImgVR2];
            //            [vehiclesR addObject:_scooterImgVR3];
            //            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            [vehiclesR addObject:_tuktukImgVR3];
            [vehiclesR addObject:_bigcarImgVR1];
            //            [vehiclesR addObject:_bigcarImgVR2];
            [vehiclesR addObject:_bigcarImgVR3];
            [vehiclesR addObject:_cycloImgVR1];
            [vehiclesR addObject:_cycloImgVR2];
            
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            //            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_carImgVL3];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            //            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_tuktukImgVL3];
            //            [vehiclesL addObject:_bigcarImgVL1];
            [vehiclesL addObject:_bigcarImgVL2];
            //            [vehiclesL addObject:_bigcarImgVL3];
            [vehiclesL addObject:_cycloImgVL1];
            [vehiclesL addObject:_cycloImgVL2];
            
            
            vehiclesD = [NSMutableArray array];
            
            //            [vehiclesD addObject:_carImgVD1];
            [vehiclesD addObject:_carImgVD2];
            [vehiclesD addObject:_carImgVD3];
            [vehiclesD addObject:_scooterImgVD1];
            [vehiclesD addObject:_tuktukImgVD1];
            [vehiclesD addObject:_tuktukImgVD2];
            [vehiclesD addObject:_cycloImgVD1];
            [vehiclesD addObject:_cycloImgVD2];
            
            
            vehiclesU = [NSMutableArray array];
            
            [vehiclesU addObject:_carImgVU1];
            [vehiclesU addObject:_carImgVU2];
            //            [vehiclesU addObject:_scooterImgVU1];
            [vehiclesU addObject:_scooterImgVU2];
            [vehiclesU addObject:_scooterImgVU3];
            [vehiclesU addObject:_tuktukImgVU1];
            //            [vehiclesU addObject:_tuktukImgVU2];
            [vehiclesU addObject:_cycloImgVU1];
            [vehiclesU addObject:_cycloImgVU2];
            
            _blackR = [_createObject makeImageView:290+For4inch :-450 :284 :640 name:@"black.png"];
            _blackL = [_createObject makeImageView:-830-44+For4inch :-450 :284 :640 name:@"black.png"];
            _blackD = [_createObject makeImageView:-590 :190 :880 :240 name:@"black.png"];
            _blackU = [_createObject makeImageView:-590 :-690 :880 :240 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            [blacks addObject:_blackD];
            [blacks addObject:_blackU];
            
            
            break;
            
        case 6:
            _moveObject = [[MoveObject alloc] initWithStageNum:6];
            _lvl = 2;
            _stageNum = 3;
            _sec = 40;
            _backgroundImgV = [_createObject makeImageView:-600.0f :-880.0f :960.0f :1060.0f name:@"L2-3.png"];
            
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:30 :0 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-630 name:@"car.png" direction:1];
            
            //            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-130 name:@"car.png" direction:2];
            //            _carImgVL2 = [_createObject makeVehicleImageView:100 :-750 name:@"car.png" direction:2];
            _carImgVL3 = [_createObject makeVehicleImageView:-100 :-780 name:@"car.png" direction:2];
            
            _carImgVD1 = [_createObject makeVehicleImageView:120 :160 name:@"car.png" direction:3];
            //            _carImgVD2 = [_createObject makeVehicleImageView:-355 :-150 name:@"car.png" direction:3];
            _carImgVD3 = [_createObject makeVehicleImageView:-370 :-150 name:@"car.png" direction:3];
            
            _carImgVU1 = [_createObject makeVehicleImageView:70 :500 name:@"car.png" direction:4];
            _carImgVU2 = [_createObject makeVehicleImageView:-430 :500 name:@"car.png" direction:4];
            
            
            // Create scooter
            //            _scooterImgVR1 = [_createObject makeVehicleImageView:-100 :-10 name:@"scooter.png" direction:1];
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :-215 name:@"scooter.png" direction:1];
            _scooterImgVR3 = [_createObject makeVehicleImageView:-100 :-670 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-150 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-765 name:@"scooter.png" direction:2];
            
            _scooterImgVD1 = [_createObject makeVehicleImageView:125 :50 name:@"scooter.png" direction:3];
            
            //            _scooterImgVU1 = [_createObject makeVehicleImageView:90 :50 name:@"scooter.png" direction:4];
            _scooterImgVU2 = [_createObject makeVehicleImageView:-380 :80 name:@"scooter.png" direction:4];
            _scooterImgVU3 = [_createObject makeVehicleImageView:-800 :150 name:@"scooter.png" direction:4];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:110 :-20 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:200 :-230 name:@"tuktuk.png" direction:1];
            //            _tuktukImgVR3 = [_createObject makeVehicleImageView:200 :-675 name:@"tuktuk.png" direction:1];
            
            //            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-100 name:@"tuktuk.png" direction:2];
            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-290 name:@"tuktuk.png" direction:2];
            _tuktukImgVL3 = [_createObject makeVehicleImageView:600 :-800 name:@"tuktuk.png" direction:2];
            
            _tuktukImgVD1 = [_createObject makeVehicleImageView:100 :100 name:@"tuktuk.png" direction:3];
            _tuktukImgVD2 = [_createObject makeVehicleImageView:-360 :100 name:@"tuktuk.png" direction:3];
            
            _tuktukImgVU1 = [_createObject makeVehicleImageView:55 :240 name:@"tuktuk.png" direction:4];
            //            _tuktukImgVU2 = [_createObject makeVehicleImageView:-410 :140 name:@"tuktuk.png" direction:4];
            
            
            // Create bigcar
            //            _bigcarImgVR1 = [_createObject makeVehicleImageView:250 :-50 name:@"bigcar.png" direction:1];
            _bigcarImgVR2 = [_createObject makeVehicleImageView:-150 :-235 name:@"bigcar.png" direction:1];
            _bigcarImgVR3 = [_createObject makeVehicleImageView:-150 :-660 name:@"bigcar.png" direction:1];
            
            //            _bigcarImgVL1 = [_createObject makeVehicleImageView:-150 :-130 name:@"bigcar.png" direction:2];
            _bigcarImgVL2 = [_createObject makeVehicleImageView:-150 :-280 name:@"bigcar.png" direction:2];
            _bigcarImgVL3 = [_createObject makeVehicleImageView:-150 :-735 name:@"bigcar.png" direction:2];
            
            
            // Create cyclo
            _cycloImgVR1 = [_createObject makeVehicleImageView:0 :-130 name:@"cyclo.png" direction:1];
            _cycloImgVR2 = [_createObject makeVehicleImageView:110 :-685 name:@"cyclo.png" direction:1];
            
            _cycloImgVL1 = [_createObject makeVehicleImageView:300 :-50 name:@"cyclo.png" direction:2];
            _cycloImgVL2 = [_createObject makeVehicleImageView:-100 :-760 name:@"cyclo.png" direction:2];
            
            _cycloImgVD1 = [_createObject makeVehicleImageView:125 :150 name:@"cyclo.png" direction:3];
            _cycloImgVD2 = [_createObject makeVehicleImageView:-360 :150 name:@"cyclo.png" direction:3];
            
            _cycloImgVU1 = [_createObject makeVehicleImageView:70 :-150 name:@"cyclo.png" direction:4];
            _cycloImgVU2 = [_createObject makeVehicleImageView:-410 :350 name:@"cyclo.png" direction:4];
            
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            //            [cars addObject:_carImgVL1];
            //            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVL3];
            [cars addObject:_carImgVD1];
            //            [cars addObject:_carImgVD2];
            [cars addObject:_carImgVD3];
            [cars addObject:_carImgVU1];
            [cars addObject:_carImgVU2];
            
            scooters = [NSMutableArray array];
            
            //            [scooters addObject:_scooterImgVR1];
            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVR3];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVD1];
            //            [scooters addObject:_scooterImgVU1];
            [scooters addObject:_scooterImgVU2];
            [scooters addObject:_scooterImgVU3];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            //            [tuktuks addObject:_tuktukImgVR3];
            //            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVL3];
            [tuktuks addObject:_tuktukImgVD1];
            [tuktuks addObject:_tuktukImgVD2];
            [tuktuks addObject:_tuktukImgVU1];
            //            [tuktuks addObject:_tuktukImgVU2];
            
            bigcars = [NSMutableArray array];
            
            //            [bigcars addObject:_bigcarImgVR1];
            [bigcars addObject:_bigcarImgVR2];
            [bigcars addObject:_bigcarImgVR3];
            //            [bigcars addObject:_bigcarImgVL1];
            [bigcars addObject:_bigcarImgVL2];
            [bigcars addObject:_bigcarImgVL3];
            
            cyclos = [NSMutableArray array];
            
            [cyclos addObject:_cycloImgVR1];
            [cyclos addObject:_cycloImgVR2];
            [cyclos addObject:_cycloImgVL1];
            [cyclos addObject:_cycloImgVL2];
            [cyclos addObject:_cycloImgVD1];
            [cyclos addObject:_cycloImgVD2];
            [cyclos addObject:_cycloImgVU1];
            [cyclos addObject:_cycloImgVU2];
            
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            //            [vehiclesR addObject:_scooterImgVR1];
            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_scooterImgVR3];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            //            [vehiclesR addObject:_tuktukImgVR3];
            //            [vehiclesR addObject:_bigcarImgVR1];
            [vehiclesR addObject:_bigcarImgVR2];
            [vehiclesR addObject:_bigcarImgVR3];
            [vehiclesR addObject:_cycloImgVR1];
            [vehiclesR addObject:_cycloImgVR2];
            
            
            vehiclesL = [NSMutableArray array];
            
            //            [vehiclesL addObject:_carImgVL1];
            //            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_carImgVL3];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            //            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_tuktukImgVL3];
            //            [vehiclesL addObject:_bigcarImgVL1];
            [vehiclesL addObject:_bigcarImgVL2];
            [vehiclesL addObject:_bigcarImgVL3];
            [vehiclesL addObject:_cycloImgVL1];
            [vehiclesL addObject:_cycloImgVL2];
            
            vehiclesD = [NSMutableArray array];
            
            [vehiclesD addObject:_carImgVD1];
            //            [vehiclesD addObject:_carImgVD2];
            [vehiclesD addObject:_carImgVD3];
            [vehiclesD addObject:_scooterImgVD1];
            [vehiclesD addObject:_tuktukImgVD1];
            [vehiclesD addObject:_tuktukImgVD2];
            [vehiclesD addObject:_cycloImgVD1];
            [vehiclesD addObject:_cycloImgVD2];
            
            
            vehiclesU = [NSMutableArray array];
            
            [vehiclesU addObject:_carImgVU1];
            [vehiclesU addObject:_carImgVU2];
            //            [vehiclesU addObject:_scooterImgVU1];
            [vehiclesU addObject:_scooterImgVU2];
            [vehiclesU addObject:_scooterImgVU3];
            [vehiclesU addObject:_tuktukImgVU1];
            //            [vehiclesU addObject:_tuktukImgVU2];
            [vehiclesU addObject:_cycloImgVU1];
            [vehiclesU addObject:_cycloImgVU2];
            
            
            _blackR = [_createObject makeImageView:360-44+For4inch :-880 :284 : 1060 name:@"black.png"];
            _blackL = [_createObject makeImageView:-840-88+For4inch :-880 :284 :1060 name:@"black.png"];
            _blackD = [_createObject makeImageView:-600 :180 :960 :240 name:@"black.png"];
            _blackU = [_createObject makeImageView:-600 :-1120 :960 :240 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            [blacks addObject:_blackD];
            [blacks addObject:_blackU];
            
            break;
            
        case 7:
            _moveObject = [[MoveObject alloc] initWithStageNum:7];
            _lvl = 3;
            _stageNum = 1;
            _sec = 20;
            _backgroundImgV = [_createObject makeImageView:-100.0f :-650.0f :680.0f :840.0f name:@"Level3Stage1.ex.png"];
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:130 :10 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-470 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-50 name:@"car.png" direction:2];
            _carImgVL2 = [_createObject makeVehicleImageView:-100 :-550 name:@"car.png" direction:2];
            
            _carImgVRD1 = [_createObject makeVehicleImageView:300 :-180 name:@"car_slope.png" direction:5];
            
            _carImgVLU1 = [_createObject makeVehicleImageView:570 :200 name:@"car_slope.png" direction:6];
            
            
            // Create scooter
            _scooterImgVR1 = [_createObject makeVehicleImageView:-100 :60 name:@"scooter.png" direction:1];
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :-430 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-50 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-500 name:@"scooter.png" direction:2];
            
            _scooterImgVRD1 = [_createObject makeVehicleImageView:450 :-50 name:@"scooter_slope.png" direction:5];
            
            _scooterImgVLU1 = [_createObject makeVehicleImageView:130 :-230 name:@"scooter_slope.png" direction:6];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:290 :-10 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:200 :-450 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-50 name:@"tuktuk.png" direction:2];
            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-540 name:@"tuktuk.png" direction:2];
            
            _tuktukImgVRD1 = [_createObject makeVehicleImageView:450 :-100 name:@"tuktuk_slope.png" direction:5];
            
            _tuktukImgVLU1 = [_createObject makeVehicleImageView:560 :240 name:@"tuktuk_slope.png" direction:6];
            
            
            // Create bigcar
            _bigcarImgVR1 = [_createObject makeVehicleImageView:-150 :15 name:@"bigcar.png" direction:1];
            _bigcarImgVR2 = [_createObject makeVehicleImageView:-150 :-435 name:@"bigcar.png" direction:1];
            
            _bigcarImgVL1 = [_createObject makeVehicleImageView:-150 :-35 name:@"bigcar.png" direction:2];
            _bigcarImgVL2 = [_createObject makeVehicleImageView:-150 :-580 name:@"bigcar.png" direction:2];
            
            _bigcarImgVLU1 = [_createObject makeVehicleImageView:150 :-250 name:@"bigcar_slope.png" direction:6];
            
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVRD1];
            [cars addObject:_carImgVLU1];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR1];
            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVRD1];
            [scooters addObject:_scooterImgVLU1];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVRD1];
            [tuktuks addObject:_tuktukImgVLU1];
            
            bigcars = [NSMutableArray array];
            
            [bigcars addObject:_bigcarImgVR1];
            [bigcars addObject:_bigcarImgVR2];
            [bigcars addObject:_bigcarImgVL1];
            [bigcars addObject:_bigcarImgVL2];
            [bigcars addObject:_bigcarImgVLU1];
            
            
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            [vehiclesR addObject:_scooterImgVR1];
            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            [vehiclesR addObject:_bigcarImgVR1];
            [vehiclesR addObject:_bigcarImgVR2];
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_bigcarImgVL1];
            [vehiclesL addObject:_bigcarImgVL2];
            
            vehiclesRD = [NSMutableArray array];
            
            [vehiclesRD addObject:_carImgVRD1];
            [vehiclesRD addObject:_scooterImgVRD1];
            [vehiclesRD addObject:_tuktukImgVRD1];
            
            vehiclesLU = [NSMutableArray array];
            
            [vehiclesLU addObject:_carImgVLU1];
            [vehiclesLU addObject:_scooterImgVLU1];
            [vehiclesLU addObject:_tuktukImgVLU1];
            [vehiclesLU addObject:_bigcarImgVLU1];
            
            _blackR = [_createObject makeImageView:580-44+For4inch :-650-284 :284 :840+568 name:@"black.png"];
            _blackL = [_createObject makeImageView:-340-88+For4inch :-650-284 :284 :840+568 name:@"black.png"];
            _blackD = [_createObject makeImageView:-100 :190 :680 :284 name:@"black.png"];
            _blackU = [_createObject makeImageView:-100 :-890-44 :680 :284 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            [blacks addObject:_blackD];
            [blacks addObject:_blackU];
            
            break;
            
        case 8:
            _moveObject = [[MoveObject alloc] initWithStageNum:8];
            _lvl = 3;
            _stageNum = 2;
            _sec = 35;
            _backgroundImgV = [_createObject makeImageView:180.0f+For4inch :-950.0f :780.0f :1140.0f name:@"Level3Stage2.centralmarket.png"];
            
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:130 :10 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-710 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-130 name:@"car.png" direction:2];
            _carImgVL2 = [_createObject makeVehicleImageView:-100 :-850 name:@"car.png" direction:2];
            _carImgVL3 = [_createObject makeVehicleImageView:-100 :-815 name:@"car.png" direction:2];
            
            _carImgVD1 = [_createObject makeVehicleImageView:350 :215 name:@"car.png" direction:3];
            
            _carImgVU1 = [_createObject makeVehicleImageView:490 :315 name:@"car.png" direction:4];
            
            _carImgVRD1 = [_createObject makeVehicleImageView:670 :-180 name:@"car_slope.png" direction:5];
            
            _carImgVLU1 = [_createObject makeVehicleImageView:570 :-100 name:@"car_slope.png" direction:6];
            
            
            // Create scooter
            _scooterImgVR1 = [_createObject makeVehicleImageView:-100 :30 name:@"scooter.png" direction:1];
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :-720 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-50 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-800 name:@"scooter.png" direction:2];
            
            _scooterImgVD1 = [_createObject makeVehicleImageView:360 :50 name:@"scooter.png" direction:3];
            
            _scooterImgVU1 = [_createObject makeVehicleImageView:500 :30 name:@"scooter.png" direction:4];
            
            _scooterImgVRD1 = [_createObject makeVehicleImageView:720 :-50 name:@"scooter_slope.png" direction:5];
            
            _scooterImgVLU1 = [_createObject makeVehicleImageView:460 :-230 name:@"scooter_slope.png" direction:6];
            _scooterImgVLU2 = [_createObject makeVehicleImageView:550 :-80 name:@"scooter_slope.png" direction:6];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:290 :-10 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:100 :-760 name:@"tuktuk.png" direction:1];
            _tuktukImgVR3 = [_createObject makeVehicleImageView:300 :-725 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-80 name:@"tuktuk.png" direction:2];
            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-840 name:@"tuktuk.png" direction:2];
            
            _tuktukImgVD1 = [_createObject makeVehicleImageView:340 :40 name:@"tuktuk.png" direction:3];
            
            _tuktukImgVU1 = [_createObject makeVehicleImageView:520 :140 name:@"tuktuk.png" direction:4];
            
            _tuktukImgVRD1 = [_createObject makeVehicleImageView:730 :-100 name:@"tuktuk_slope.png" direction:5];
            _tuktukImgVRD2 = [_createObject makeVehicleImageView:700 :-100 name:@"tuktuk_slope.png" direction:5];
            
            _tuktukImgVLU1 = [_createObject makeVehicleImageView:560 :-140 name:@"tuktuk_slope.png" direction:6];
            
            
            // Create bigcar
            _bigcarImgVR1 = [_createObject makeVehicleImageView:150 :15 name:@"bigcar.png" direction:1];
            _bigcarImgVR2 = [_createObject makeVehicleImageView:-150 :0 name:@"bigcar.png" direction:1];
            _bigcarImgVR3 = [_createObject makeVehicleImageView:30 :-760 name:@"bigcar.png" direction:1];
            
            _bigcarImgVL1 = [_createObject makeVehicleImageView:-150 :-60 name:@"bigcar.png" direction:2];
            _bigcarImgVL2 = [_createObject makeVehicleImageView:-150 :-880 name:@"bigcar.png" direction:2];
            
            _bigcarImgVD1 = [_createObject makeVehicleImageView:365 :280 name:@"bigcar.png" direction:3];
            
            _bigcarImgVU1 = [_createObject makeVehicleImageView:450 :0 name:@"bigcar.png" direction:4];
            
            _bigcarImgVRD1 = [_createObject makeVehicleImageView:650 :-200 name:@"bigcar_slope.png" direction:5];
            
            _bigcarImgVLU1 = [_createObject makeVehicleImageView:500 :-150 name:@"bigcar_slope.png" direction:6];
            
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVL3];
            [cars addObject:_carImgVD1];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVRD1];
            [cars addObject:_carImgVLU1];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR1];
            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVD1];
            [scooters addObject:_scooterImgVU1];
            [scooters addObject:_scooterImgVRD1];
            [scooters addObject:_scooterImgVLU1];
            [scooters addObject:_scooterImgVLU2];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            [tuktuks addObject:_tuktukImgVR3];
            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVD1];
            [tuktuks addObject:_tuktukImgVU1];
            [tuktuks addObject:_tuktukImgVRD1];
            [tuktuks addObject:_tuktukImgVRD2];
            [tuktuks addObject:_tuktukImgVLU1];
            
            bigcars = [NSMutableArray array];
            
            [bigcars addObject:_bigcarImgVR1];
            [bigcars addObject:_bigcarImgVR2];
            [bigcars addObject:_bigcarImgVR3];
            [bigcars addObject:_bigcarImgVL1];
            [bigcars addObject:_bigcarImgVL2];
            [bigcars addObject:_bigcarImgVD1];
            [bigcars addObject:_bigcarImgVU1];
            [bigcars addObject:_bigcarImgVRD1];
            [bigcars addObject:_bigcarImgVLU1];
            
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            [vehiclesR addObject:_scooterImgVR1];
            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            [vehiclesR addObject:_tuktukImgVR3];
            [vehiclesR addObject:_bigcarImgVR1];
            [vehiclesR addObject:_bigcarImgVR2];
            [vehiclesR addObject:_bigcarImgVR3];
            
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_carImgVL3];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_bigcarImgVL1];
            [vehiclesL addObject:_bigcarImgVL2];
            
            
            vehiclesD = [NSMutableArray array];
            
            [vehiclesD addObject:_carImgVD1];
            [vehiclesD addObject:_scooterImgVD1];
            [vehiclesD addObject:_tuktukImgVD1];
            [vehiclesD addObject:_bigcarImgVD1];
            
            vehiclesU = [NSMutableArray array];
            
            [vehiclesU addObject:_carImgVU1];
            [vehiclesU addObject:_scooterImgVU1];
            [vehiclesU addObject:_tuktukImgVU1];
            [vehiclesU addObject:_bigcarImgVU1];
            
            
            vehiclesRD = [NSMutableArray array];
            
            [vehiclesRD addObject:_carImgVRD1];
            [vehiclesRD addObject:_scooterImgVRD1];
            [vehiclesRD addObject:_tuktukImgVRD1];
            [vehiclesRD addObject:_tuktukImgVRD2];
            [vehiclesRD addObject:_bigcarImgVRD1];
            
            
            vehiclesLU = [NSMutableArray array];
            
            [vehiclesLU addObject:_carImgVLU1];
            [vehiclesLU addObject:_scooterImgVLU1];
            [vehiclesLU addObject:_scooterImgVLU2];
            [vehiclesLU addObject:_tuktukImgVLU1];
            [vehiclesLU addObject:_bigcarImgVLU1];
            
            _blackR = [_createObject makeImageView:960+For4inch :-950-284 :284 :1140+568 name:@"black.png"];
            _blackL = [_createObject makeImageView:-60-44+For4inch :-950-284 :284 :1140+568 name:@"black.png"];
            _blackD = [_createObject makeImageView:180 :190 :880 :284 name:@"black.png"];
            _blackU = [_createObject makeImageView:180 :-1190-44 :780 :284 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            [blacks addObject:_blackD];
            [blacks addObject:_blackU];
            
            break;
            
        case 9:
            _moveObject = [[MoveObject alloc] initWithStageNum:9];
            _lvl = 3;
            _stageNum = 3;
            _sec = 35;
            _backgroundImgV = [_createObject makeImageView:190.0f+For4inch :-1200.0f :960.0f :1400.0f name:@"Level3Stage3.whatphnom.png"];
            
            
            // Create car
            _carImgVR1 = [_createObject makeVehicleImageView:130 :-350 name:@"car.png" direction:1];
            _carImgVR2 = [_createObject makeVehicleImageView:-100 :-980 name:@"car.png" direction:1];
            
            _carImgVL1 = [_createObject makeVehicleImageView:-100 :-130 name:@"car.png" direction:2];
            _carImgVL2 = [_createObject makeVehicleImageView:-100 :-600 name:@"car.png" direction:2];
            _carImgVL3 = [_createObject makeVehicleImageView:-100 :-1040 name:@"car.png" direction:2];
            
            _carImgVD1 = [_createObject makeVehicleImageView:880 :215 name:@"car.png" direction:3];
            
            _carImgVU1 = [_createObject makeVehicleImageView:760 :315 name:@"car.png" direction:4];
            
            _carImgVRD1 = [_createObject makeVehicleImageView:670 :-150 name:@"car_slope.png" direction:5];
            _carImgVRD2 = [_createObject makeVehicleImageView:1610 :-150 name:@"car_slope.png" direction:5];
            
            _carImgVLU1 = [_createObject makeVehicleImageView:510 :-150 name:@"car_slope.png" direction:6];
            _carImgVLU2 = [_createObject makeVehicleImageView:1530 :-100 name:@"car_slope.png" direction:6];
            
            
            // Create scooter
            _scooterImgVR1 = [_createObject makeVehicleImageView:100 :-60 name:@"scooter.png" direction:1];
            _scooterImgVR2 = [_createObject makeVehicleImageView:-100 :-375 name:@"scooter.png" direction:1];
            _scooterImgVR3 = [_createObject makeVehicleImageView:-100 :-905 name:@"scooter.png" direction:1];
            
            _scooterImgVL1 = [_createObject makeVehicleImageView:500 :-120 name:@"scooter.png" direction:2];
            _scooterImgVL2 = [_createObject makeVehicleImageView:500 :-1005 name:@"scooter.png" direction:2];
            
            _scooterImgVD1 = [_createObject makeVehicleImageView:890 :50 name:@"scooter.png" direction:3];
            
            _scooterImgVU1 = [_createObject makeVehicleImageView:835 :30 name:@"scooter.png" direction:4];
            
            _scooterImgVRD1 = [_createObject makeVehicleImageView:720 :-50 name:@"scooter_slope.png" direction:5];
            _scooterImgVRD2 = [_createObject makeVehicleImageView:1720 :-50 name:@"scooter_slope.png" direction:5];
            
            _scooterImgVLU1 = [_createObject makeVehicleImageView:500 :-100 name:@"scooter_slope.png" direction:6];
            _scooterImgVLU2 = [_createObject makeVehicleImageView:1550 :-80 name:@"scooter_slope.png" direction:6];
            
            
            // Create tuktuk
            _tuktukImgVR1 = [_createObject makeVehicleImageView:290 :-80 name:@"tuktuk.png" direction:1];
            _tuktukImgVR2 = [_createObject makeVehicleImageView:100 :-420 name:@"tuktuk.png" direction:1];
            _tuktukImgVR3 = [_createObject makeVehicleImageView:300 :-940 name:@"tuktuk.png" direction:1];
            
            _tuktukImgVL1 = [_createObject makeVehicleImageView:600 :-500 name:@"tuktuk.png" direction:2];
            _tuktukImgVL2 = [_createObject makeVehicleImageView:600 :-1080 name:@"tuktuk.png" direction:2];
            
            _tuktukImgVD1 = [_createObject makeVehicleImageView:910 :40 name:@"tuktuk.png" direction:3];
            
            _tuktukImgVU1 = [_createObject makeVehicleImageView:820 :140 name:@"tuktuk.png" direction:4];
            
            _tuktukImgVRD1 = [_createObject makeVehicleImageView:1630 :-200 name:@"tuktuk_slope.png" direction:5];
            _tuktukImgVRD2 = [_createObject makeVehicleImageView:700 :-100 name:@"tuktuk_slope.png" direction:5];
            
            _tuktukImgVLU1 = [_createObject makeVehicleImageView:550 :-140 name:@"tuktuk_slope.png" direction:6];
            _tuktukImgVLU2 = [_createObject makeVehicleImageView:1570 :-140 name:@"tuktuk_slope.png" direction:6];
            
            
            // Create bigcar
            _bigcarImgVR1 = [_createObject makeVehicleImageView:150 :-400 name:@"bigcar.png" direction:1];
            _bigcarImgVR2 = [_createObject makeVehicleImageView:-150 :-360 name:@"bigcar.png" direction:1];
            _bigcarImgVR3 = [_createObject makeVehicleImageView:30 :-960 name:@"bigcar.png" direction:1];
            
            _bigcarImgVL1 = [_createObject makeVehicleImageView:-150 :-500 name:@"bigcar.png" direction:2];
            _bigcarImgVL2 = [_createObject makeVehicleImageView:-150 :-1080 name:@"bigcar.png" direction:2];
            
            _bigcarImgVD1 = [_createObject makeVehicleImageView:905 :280 name:@"bigcar.png" direction:3];
            
            _bigcarImgVU1 = [_createObject makeVehicleImageView:810 :0 name:@"bigcar.png" direction:4];
            
            _bigcarImgVRD1 = [_createObject makeVehicleImageView:650 :-100 name:@"bigcar_slope.png" direction:5];
            _bigcarImgVRD2 = [_createObject makeVehicleImageView:1650 :-100 name:@"bigcar_slope.png" direction:5];
            
            _bigcarImgVLU1 = [_createObject makeVehicleImageView:1500 :-150 name:@"bigcar_slope.png" direction:6];
            
            
            cars = [NSMutableArray array];
            
            [cars addObject:_carImgVR1];
            [cars addObject:_carImgVR2];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVL2];
            [cars addObject:_carImgVL3];
            [cars addObject:_carImgVD1];
            [cars addObject:_carImgVL1];
            [cars addObject:_carImgVRD1];
            [cars addObject:_carImgVRD2];
            [cars addObject:_carImgVLU1];
            [cars addObject:_carImgVLU2];
            
            scooters = [NSMutableArray array];
            
            [scooters addObject:_scooterImgVR1];
            [scooters addObject:_scooterImgVR2];
            [scooters addObject:_scooterImgVR3];
            [scooters addObject:_scooterImgVL1];
            [scooters addObject:_scooterImgVL2];
            [scooters addObject:_scooterImgVD1];
            [scooters addObject:_scooterImgVU1];
            [scooters addObject:_scooterImgVRD1];
            [scooters addObject:_scooterImgVRD2];
            [scooters addObject:_scooterImgVLU1];
            [scooters addObject:_scooterImgVLU2];
            
            tuktuks = [NSMutableArray array];
            
            [tuktuks addObject:_tuktukImgVR1];
            [tuktuks addObject:_tuktukImgVR2];
            [tuktuks addObject:_tuktukImgVR3];
            [tuktuks addObject:_tuktukImgVL1];
            [tuktuks addObject:_tuktukImgVL2];
            [tuktuks addObject:_tuktukImgVD1];
            [tuktuks addObject:_tuktukImgVU1];
            [tuktuks addObject:_tuktukImgVRD1];
            [tuktuks addObject:_tuktukImgVRD2];
            [tuktuks addObject:_tuktukImgVLU1];
            [tuktuks addObject:_tuktukImgVLU2];
            
            bigcars = [NSMutableArray array];
            
            [bigcars addObject:_bigcarImgVR1];
            [bigcars addObject:_bigcarImgVR2];
            [bigcars addObject:_bigcarImgVR3];
            [bigcars addObject:_bigcarImgVL1];
            [bigcars addObject:_bigcarImgVL2];
            [bigcars addObject:_bigcarImgVD1];
            [bigcars addObject:_bigcarImgVU1];
            [bigcars addObject:_bigcarImgVRD1];
            [bigcars addObject:_bigcarImgVRD2];
            [bigcars addObject:_bigcarImgVLU1];
            
            
            vehiclesR = [NSMutableArray array];
            
            [vehiclesR addObject:_carImgVR1];
            [vehiclesR addObject:_carImgVR2];
            [vehiclesR addObject:_scooterImgVR1];
            [vehiclesR addObject:_scooterImgVR2];
            [vehiclesR addObject:_scooterImgVR3];
            [vehiclesR addObject:_tuktukImgVR1];
            [vehiclesR addObject:_tuktukImgVR2];
            [vehiclesR addObject:_tuktukImgVR3];
            [vehiclesR addObject:_bigcarImgVR1];
            [vehiclesR addObject:_bigcarImgVR2];
            [vehiclesR addObject:_bigcarImgVR3];
            
            
            vehiclesL = [NSMutableArray array];
            
            [vehiclesL addObject:_carImgVL1];
            [vehiclesL addObject:_carImgVL2];
            [vehiclesL addObject:_carImgVL3];
            [vehiclesL addObject:_scooterImgVL1];
            [vehiclesL addObject:_scooterImgVL2];
            [vehiclesL addObject:_tuktukImgVL1];
            [vehiclesL addObject:_tuktukImgVL2];
            [vehiclesL addObject:_bigcarImgVL1];
            [vehiclesL addObject:_bigcarImgVL2];
            
            
            vehiclesD = [NSMutableArray array];
            
            [vehiclesD addObject:_carImgVD1];
            [vehiclesD addObject:_scooterImgVD1];
            [vehiclesD addObject:_tuktukImgVD1];
            [vehiclesD addObject:_bigcarImgVD1];
            
            vehiclesU = [NSMutableArray array];
            
            [vehiclesU addObject:_carImgVU1];
            [vehiclesU addObject:_scooterImgVU1];
            [vehiclesU addObject:_tuktukImgVU1];
            [vehiclesU addObject:_bigcarImgVU1];
            
            
            vehiclesRD = [NSMutableArray array];
            
            [vehiclesRD addObject:_carImgVRD1];
            [vehiclesRD addObject:_carImgVRD2];
            [vehiclesRD addObject:_scooterImgVRD1];
            [vehiclesRD addObject:_scooterImgVRD2];
            [vehiclesRD addObject:_tuktukImgVRD1];
            [vehiclesRD addObject:_tuktukImgVRD2];
            [vehiclesRD addObject:_bigcarImgVRD1];
            [vehiclesRD addObject:_bigcarImgVRD2];
            
            
            vehiclesLU = [NSMutableArray array];
            
            [vehiclesLU addObject:_carImgVLU1];
            [vehiclesLU addObject:_carImgVLU2];
            [vehiclesLU addObject:_scooterImgVLU1];
            [vehiclesLU addObject:_scooterImgVLU2];
            [vehiclesLU addObject:_tuktukImgVLU1];
            [vehiclesLU addObject:_tuktukImgVLU2];
            [vehiclesLU addObject:_bigcarImgVLU1];
            
            
            _blackR = [_createObject makeImageView:1150+For4inch :-1200-284 :284 :1400+568 name:@"black.png"];
            _blackL = [_createObject makeImageView:-50-44+For4inch :-1200-284 :284 :1400+568 name:@"black.png"];
            _blackD = [_createObject makeImageView:190 :200 :960 :284 name:@"black.png"];
            _blackU = [_createObject makeImageView:190 :-1440-44 :960 :284 name:@"black.png"];
            
            blacks = [NSMutableArray array];
            
            [blacks addObject:_blackR];
            [blacks addObject:_blackL];
            [blacks addObject:_blackD];
            [blacks addObject:_blackU];
            
            break;
            
        default:
            break;
    }
}


- (void)viewDidLoad {
    [super viewDidLoad];
}


-(void)viewWillAppear:(BOOL)animated {
    
    [self initWithStageNum:_stageSerialNumber];
    
    [self.view addSubview:_backgroundImgV];
    
    _gameClearLetter.hidden = YES;//Hide gameclerLetter
    _gameOverLetter.hidden = YES;//Hide gameoverLetter
    _timeupLetter.hidden = YES;
    _pauseView.alpha = 0;

    _moveObject.stepMoveFactor = 5 + 2*(_speedBar.value/10);
    
    _cityName.text = _cityNameHolder;
    _cityName.textColor = [UIColor blueColor];
    
    _stageName.text = _stageNameHolder;
    _stageName.textColor = [UIColor cyanColor];
    
    for (int i = 0 ; i < [cars count]; i++) {
        [self.view addSubview:(UIImageView *)[cars objectAtIndex:i]];
    }
    
    for (int i = 0 ; i < [scooters count]; i++) {
        [self.view addSubview:(UIImageView *)[scooters objectAtIndex:i]];
    }
    
    for (int i = 0 ; i < [tuktuks count]; i++) {
        [self.view addSubview:(UIImageView *)[tuktuks objectAtIndex:i]];
    }
    
    for (int i = 0 ; i < [bigcars count]; i++) {
        [self.view addSubview:(UIImageView *)[bigcars objectAtIndex:i]];
    }
    
    for (int i = 0 ; i < [cyclos count]; i++) {
        [self.view addSubview:(UIImageView *)[cyclos objectAtIndex:i]];
    }
    
    [self.view addSubview:_manImgV];
    
    for (int i = 0; i < [blacks count]; i++) {
        [self.view addSubview:(UIImageView *)[blacks objectAtIndex:i]];
    }
    
    [self setStageLavel:_stageSerialNumber];
    [self prepareAnime];
    
    /* Music Play */
    NSString *path;
    if (_lvl == 1) {
        path = [[NSBundle mainBundle] pathForResource:@"sihanoukville" ofType:@"mp3"];
    } else if (_lvl == 2) {
        path = [[NSBundle mainBundle] pathForResource:@"siem_reap" ofType:@"mp3"];
    } else if (_lvl == 3) {
        path = [[NSBundle mainBundle] pathForResource:@"phnom penh" ofType:@"mp3"];
    }
    NSURL *url = [NSURL fileURLWithPath:path];
    audio = [[AVAudioPlayer alloc] initWithContentsOfURL:url error:nil];
    
    // gameover
    NSString *overAudioPath = [[NSBundle mainBundle] pathForResource:@"explosion_car_crash" ofType:@"mp3"];
    NSURL *overAudioUrl = [NSURL fileURLWithPath:overAudioPath];
    overAudio= [[AVAudioPlayer alloc] initWithContentsOfURL:overAudioUrl error:nil];
    
    // gameclear
    NSString *clearAudioPath = [[NSBundle mainBundle] pathForResource:@"fireworks" ofType:@"mp3"];
    NSURL *clearAudioUrl = [NSURL fileURLWithPath:clearAudioPath];
    clearAudio= [[AVAudioPlayer alloc] initWithContentsOfURL:clearAudioUrl error:nil];
    
    /* Progress Timer */
    UIColor *tintColor = [UIColor colorWithRed:0.03f green:0.64f blue:0.83f alpha:1.00f];
    [[ADVRoundProgressView appearance] setTintColor:tintColor];
    self.roundProgressLarge.progress = _sec;
    loadsec = 0;
    secord=_sec;
    self.secLabel.text=[NSString stringWithFormat:@"%0.f",_sec];
    [self LoadingTimer:true];
    
    
    /* Game start particle*/
    gameStartTimer = [NSTimer scheduledTimerWithTimeInterval:0.1f target:self
                                                    selector:@selector(startParticleManage:) userInfo:nil repeats:YES];
    [gameStartTimer fire];
    
    [self.view bringSubviewToFront:_gameStartView];
    [self.view bringSubviewToFront:_gameStartLetter];
}


-(void)viewDidDisappear:(BOOL)animated {
    
    [_backgroundImgV removeFromSuperview];
    
    for (int i = 0 ; i < [cars count]; i++) {
        [(UIImageView *)[cars objectAtIndex:i] removeFromSuperview];
    }
    
    for (int i = 0 ; i < [scooters count]; i++) {
        [(UIImageView *)[scooters objectAtIndex:i] removeFromSuperview];
    }
    
    for (int i = 0 ; i < [tuktuks count]; i++) {
        [(UIImageView *)[tuktuks objectAtIndex:i] removeFromSuperview];
    }
    
    for (int i = 0 ; i < [bigcars count]; i++) {
        [(UIImageView *)[bigcars objectAtIndex:i] removeFromSuperview];
    }
    
    for (int i = 0 ; i < [cyclos count]; i++) {
        [(UIImageView *)[cyclos objectAtIndex:i] removeFromSuperview];
    }
    
    [_manImgV removeFromSuperview];
    
    for (int i = 0; i < [blacks count]; i++) {
        [(UIImageView *)[blacks objectAtIndex:i] removeFromSuperview];
    }
    
}


-(void)main {
    
    // Interval
    // 30-60Hz is usually used for game
    _motionManager.accelerometerUpdateInterval = 0.035; // 50Hz
    
    // Make handler
    // Masureed value will be sended to this handler
    CMAccelerometerHandler handler = ^(CMAccelerometerData *data, NSError *error) {
        
        if (!_moveObject.wasClear && !_moveObject.wasGameover && !isPausing) {
            
            float newX = data.acceleration.x + 0.5;
            
            bool checkX = newX > -0.05 && newX < 0.05;
            bool checkY = data.acceleration.y > -0.05 && data.acceleration.y < 0.05;
            
            // Man Animation
            if (checkX && checkY) {
                if (manMoving) {
                    [_manImgV stopAnimating];
                    manMoving = false;
                }
                
            } else {
                if (!manMoving) {
                    [_manImgV startAnimating];
                    manMoving = true;
                }
            }
            
            // background
            CGRect backgroundFrame = self.backgroundImgV.frame;
            backgroundFrame = *[_moveObject moveBackground:&backgroundFrame accelerationY:data.acceleration.y accelerationX:newX];
            
            // Animation
            [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
             ^{
                 _backgroundImgV.frame = backgroundFrame;// Set ImageView
             }
                                  completion:nil
             
             ];
            
            
            
            // Move blacks
            for (int i = 0; i < [blacks count]; i++) {
                UIImageView *imgV = (UIImageView *)[blacks objectAtIndex:i];
                CGRect backgroundFrame = imgV.frame;
                
                backgroundFrame = *[_moveObject moveBlack:&backgroundFrame accelerationY:data.acceleration.y accelerationX:newX];
                
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = backgroundFrame;
                 }
                                      completion:nil
                 
                 ];
                
            }
            
            
            
            // Move right direction vehicles
            for (int i = 0 ; i < [vehiclesR count]; i++) {
                
                // get ImageView from array
                UIImageView *imgV = (UIImageView *)[vehiclesR objectAtIndex:i];
                CGRect vehicleFrame = imgV.frame;
                
                vehicleFrame = *[_moveObject movevehicleR:imgV accelerationY:data.acceleration.y accelerationX:newX];
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = vehicleFrame;
                 }
                                      completion:nil
                 
                 ];
            }
            
            
            
            // Move left direction vehicles
            for (int i = 0 ; i < [vehiclesL count]; i++) {
                
                // get ImageView from array
                UIImageView *imgV = (UIImageView *)[vehiclesL objectAtIndex:i];
                CGRect vehicleFrame = imgV.frame;
                
                vehicleFrame = *[_moveObject movevehicleL:imgV accelerationY:data.acceleration.y accelerationX:newX];
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = vehicleFrame;
                 }
                                      completion:nil
                 
                 ];
            }
            
            
            
            // Move up direction vehicles
            for (int i = 0 ; i < [vehiclesD count]; i++) {
                
                // get ImageView from array
                UIImageView *imgV = (UIImageView *)[vehiclesD objectAtIndex:i];
                CGRect vehicleFrame = imgV.frame;
                
                vehicleFrame = *[_moveObject movevehicleD:imgV accelerationY:data.acceleration.y accelerationX:data.acceleration.x];
                
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = vehicleFrame;
                 }
                                      completion:nil
                 
                 ];
            }
            
            
            
            // Move up direction vehicles
            for (int i = 0 ; i < [vehiclesU count]; i++) {
                
                // get ImageView from array
                UIImageView *imgV = (UIImageView *)[vehiclesU objectAtIndex:i];
                CGRect vehicleFrame = imgV.frame;
                
                vehicleFrame = *[_moveObject movevehicleU:imgV accelerationY:data.acceleration.y accelerationX:data.acceleration.x];
                
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = vehicleFrame;
                 }
                                      completion:nil
                 
                 ];
            }
            
            
            
            // Move down direction vehicles
            for (int i = 0 ; i < [vehiclesRD count]; i++) {
                
                // get ImageView from array
                UIImageView *imgV = (UIImageView *)[vehiclesRD objectAtIndex:i];
                CGRect vehicleFrame = imgV.frame;
                
                vehicleFrame = *[_moveObject movevehicleRD:imgV accelerationY:data.acceleration.y accelerationX:newX];
                
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = vehicleFrame;
                 }
                                      completion:nil
                 
                 ];
            }
            
            
            
            
            // Move up direction vehicles
            for (int i = 0 ; i < [vehiclesLU count]; i++) {
                
                // get ImageView from array
                UIImageView *imgV = (UIImageView *)[vehiclesLU objectAtIndex:i];
                CGRect vehicleFrame = imgV.frame;
                
                vehicleFrame = *[_moveObject movevehicleLU:imgV accelerationY:data.acceleration.y accelerationX:newX];
                
                
                [UIImageView animateWithDuration:0 delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:
                 ^{
                     imgV.frame = vehicleFrame;
                 }
                                      completion:nil
                 
                 ];
            }
            
            
            
            
            
            
            // GameOver
        } else if (_moveObject.wasGameover) {
            
            [_motionManager stopAccelerometerUpdates];//stop motionManger
            
            if (_moveObject.wasDrown == true) {
                wasCrashed = false;
            } else {
                wasCrashed = true;
            }
            
            _gameOverLetter.hidden = NO;//Show gamestartLetter
            [self.view bringSubviewToFront:_gameOverView];
            [self.view bringSubviewToFront:_gameOverLetter];
            gameOverTimer = [NSTimer scheduledTimerWithTimeInterval:0.1f target:self
                                                           selector:@selector(overParticleManage:) userInfo:nil repeats:YES];
            [gameOverTimer fire];
            
            
            // GameClear
        } else if (_moveObject.wasClear) {
            
            [_motionManager stopAccelerometerUpdates];//stop motionManger
            [_manImgV stopAnimating];
            
            _gameClearLetter.hidden = NO;//Show gamestartLetter
            [self.view bringSubviewToFront:_gameClearView];
            [self.view bringSubviewToFront:_gameClearLetter];
            gameClearTimer = [NSTimer scheduledTimerWithTimeInterval:0.1f target:self
                                                            selector:@selector(clearParticleManage:) userInfo:nil repeats:YES];
            [gameClearTimer fire];
            
            
            // Pausing
        } else if (isPausing) {
            //
        }
        
    };
    
    
    // Start using slope sensor
    [_motionManager startAccelerometerUpdatesToQueue:[NSOperationQueue currentQueue] withHandler:handler];
    
}


/*ProgressTime Start/Stop */
- (void)LoadingTimer:(BOOL)start {
    if (!_timerprogress && start==true) {
        _timerprogress = [NSTimer scheduledTimerWithTimeInterval:1.0f target:self selector:@selector(update:) userInfo:nil repeats:YES];
    }else if([_timerprogress isValid]){
        [_timerprogress invalidate];
        _timerprogress=nil;
    }
}

/*Uupdate Progress Timer*/
- (void)update:(NSTimer *)sender {
    
    // Not pausing
    if (!isPausing) {
        
        divsec=1/_sec;
        loadsec+=divsec;
        secord-=1;
        
        self.roundProgressLarge.progress = loadsec ;
        
        self.secLabel.text=[NSString stringWithFormat:@"%0.f",secord];
        
        // if timeover, gameclear, gameover
        if(loadsec>=1.0f || _moveObject.wasClear || _moveObject.wasGameover){
            
            [self LoadingTimer:false];//stop Progress Timer
            
            if (secord <= 0) {
                
                [_motionManager stopAccelerometerUpdates];//stop motionManger
                
                _timeupLetter.hidden = NO;
                [self.view bringSubviewToFront:_gameOverView];
                [self.view bringSubviewToFront:_timeupLetter];
                gameOverTimer = [NSTimer scheduledTimerWithTimeInterval:0.1f target:self
                                                               selector:@selector(overParticleManage:) userInfo:nil repeats:YES];
                [gameOverTimer fire];
                
            }
        }
        
        // Pausing
    } else {
        //
    }
}


-(void)setStageLavel:(int)stageNum {
    
    if (stageNum <= 3) {
        _cityName.text = @"Sihanoukville";
        if(stageNum == 1) {
            _stageName.text = @"New Market";
        } else if (stageNum == 2) {
            _stageName.text = @"Golden Lion";
        } else if (stageNum == 3) {
            _stageName.text = @"Occheuteal Beach";
        }
        
        
    } else if (stageNum <= 6) {
        _cityName.text = @"Siem Reap";
        if(stageNum == 4) {
            _stageName.text = @"Pub Street";
        } else if (stageNum == 5) {
            _stageName.text = @"Cambodia Cultutre Village";
        } else if (stageNum == 6) {
            _stageName.text = @"Angkor Wat";
        }
        
    } else {
        _cityName.text = @"Phnom Penh";
        if(stageNum == 7) {
            _stageName.text = @"Independence Monument";
        } else if (stageNum == 8) {
            _stageName.text = @"Central Market";
        } else if (stageNum == 9) {
            _stageName.text = @"Wat Phnom";
        }
        
    }
}

-(void)prepareAnime {
    
    // Put name of image into Array
    NSArray *imageNames = @[@"chara_1.png", @"chara_2.png", @"chara_3.png"];
    
    
    NSMutableArray *images = [[NSMutableArray alloc] init];
    
    
    for (int i = 0; i < imageNames.count; i++) {
        
        // Put image into MutableArray using name of image
        [images addObject:[UIImage imageNamed:[imageNames objectAtIndex:i]]];
    }
    
    
    // Pass images to ImageView
    _manImgV.animationImages = images;
    
    // Time of cycle
    _manImgV.animationDuration = 0.5;
    
}


-(void)startParticleManage:(NSTimer *)tm {
    
    if (particleCounter < 15) {
        [_gameStartView setEmitterPosition:particleCounter];
    } else {
        [gameStartTimer invalidate];//stop timer
        _gameStartLetter.hidden = YES;// hideGamestartLetter
        _gameStartView.hidden = YES;// hideGamestartView
        [self.view bringSubviewToFront:_naviArea];
        [self main];// Activate main
        [audio play];// Start music
        particleCounter = -1;
    }
    particleCounter++;
}


-(void)clearParticleManage:(NSTimer *)tm {
    
    if (particleCounter % 5 ==  0) {
        [_gameClearView startEmitting];
        
        if (particleCounter == 0) {
            [audio stop];// Stop music
            [clearAudio play];
        }
    } else if (particleCounter % 5 == 1) {
        [_gameClearView stopEmitting];
    } else if (particleCounter > 20){
        [gameClearTimer invalidate];
        particleCounter = -1;
        [gameOverTimer invalidate];
        GameClearScreen *gameClearScreen = [self.storyboard instantiateViewControllerWithIdentifier:@"GameClearScreenID"];
        gameClearScreen.preStageSerialNumber = _stageSerialNumber;
        gameClearScreen.prelvl = _lvl;
        gameClearScreen.preStageNum = _stageNum;
        gameClearScreen.timeAllowance = _sec;
        gameClearScreen.timeUsage = _sec - secord;
        [self presentViewController:gameClearScreen animated:YES completion:nil];
    }
    particleCounter++;
}

-(void)overParticleManage:(NSTimer *)tm {
    
    if (particleCounter == 0) {
        [_manImgV stopAnimating];
        [audio stop];// Stop music
        
        if (wasCrashed) {
            [overAudio play];
            [_gameOverView startEmitting:240+For4inch:170];
        }
        
    } else if (particleCounter == 1) {
        [_gameOverView stopEmitting];
        
    } else if (particleCounter > 20) {
        [gameOverTimer invalidate];
        GameOverScreen *gameOverScreen = [self.storyboard instantiateViewControllerWithIdentifier:@"GameOverScreenID"];
        gameOverScreen.preStageNum = _stageSerialNumber;
        [self presentViewController:gameOverScreen animated:YES completion:nil];
        particleCounter = -1;
    }
    particleCounter++;
}

- (IBAction)changeSpeed:(UISlider *)sender {
    
    _moveObject.stepMoveFactor = 5 + 2*(sender.value/10);
    NSLog(@"aaaaa%f",_moveObject.stepMoveFactor);
    
}

- (IBAction)reStart:(UIButton *)sender {
    
    isPausing = false;
    
    _gameOverView.hidden = YES;
    
    // Fadeout
    [UIView animateWithDuration:0.5 animations:^{
        _pauseView.alpha = 0;
    }];
    
    _moveObject.stepMoveFactor = 5 + 2*(_speedBar.value/10);
}

- (IBAction)pause:(UIButton *)sender {
    
    isPausing = true;
    
    _gameOverView.hidden = NO;
    [self.view bringSubviewToFront:_gameOverView];
    _pauseView.alpha = 0.0f;
    [self.view bringSubviewToFront:_pauseView];
    
    // Fadein
    [UIView animateWithDuration:1.0 animations:^{
        _pauseView.alpha = 1.0f;
    }];
}

@end