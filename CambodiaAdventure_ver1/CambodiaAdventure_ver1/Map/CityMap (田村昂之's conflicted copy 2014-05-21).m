//
//  CityViewController.m
//  cityMap
//
//  Created by Measna on 5/7/14.
//  Copyright (c) 2014 Measna. All rights reserved.
//

#import "CityMap.h"


@interface CityMap () {
    
    NSString* keyArr;
    CALayer *character;
    NSMutableArray* arrData;
    NSMutableArray *photoNameArray;
    NSTimer *_slitimer;
    UIBezierPath *humanPath;
    
    int nextNo;// For slide show
    BOOL isTabOpen;
    int timeButtonOne,timeButtonTwo,timeButtonThree;
    BOOL isEnableButtonOne,isEnableButtonTwo,isEnableButtonThree;
    int selectedStageNum;
    
}

@end






@implementation CityMap


-(void)viewDidLayoutSubviews {
    [self setBtnPosition:_tagToCityMap];// Only here
}


- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    arrData = [[NSMutableArray alloc]init];
    photoNameArray=[[NSMutableArray alloc]init];
    humanPath = [[UIBezierPath alloc] init];
    
    nextNo = 0;// For slide show
    
    keyArr = [NSString stringWithFormat:@"LEVEL%d",_tagToCityMap];//key for UD
    
    [self saveData];// tmp
    [self getData];
    
    /*Add BackGround*/
    if(_tagToCityMap == 1){
        _mapImageView.image=[UIImage imageNamed:@"Sihanoukville.png"];
    }else if (_tagToCityMap == 2){
        _mapImageView.image=[UIImage imageNamed:@"SiemReap.png"];
    } else if(_tagToCityMap == 3){
        _mapImageView.image=[UIImage imageNamed:@"PhnomPenh.png"];
    }
    
    /*Add Character*/
    character = [CALayer layer];
    character.frame = CGRectMake(-100, 0, 64, 64);
    character.contents = (__bridge id)[UIImage imageNamed:@"chara_1.png"].CGImage;
    [self.mapCityView.layer addSublayer:character];
    
    /*Add way*/
    [self way];
    
    _descriptionLabel.textAlignment = NSTextAlignmentJustified;// text center wrap content
}


-(void)viewDidAppear:(BOOL)animated {
    
    /*Animation Character*/
    if(_tagToCityMap==1){
        [self humanAnimation:CGPointMake(0, 30) :CGPointMake(105, 130 )];
        [self humanAnimation:CGPointMake(105, 130) :CGPointMake(215, 200 )];
        [self humanAnimation:CGPointMake(215, 200) :CGPointMake(490, 220 )];
        [self humanAnimation:CGPointMake(490, 220) :CGPointMake(570, 85 )];
    }else if(_tagToCityMap==2){
        [self humanAnimation:CGPointMake(0, 60) :CGPointMake(115, 185 )];
        [self humanAnimation:CGPointMake(115, 185) :CGPointMake(280, 190 )];
        [self humanAnimation:CGPointMake(280, 190) :CGPointMake(450, 130 )];
        [self humanAnimation:CGPointMake(450, 130) :CGPointMake(565, 30 )];
    }else if (_tagToCityMap==3){
        [self humanAnimation:CGPointMake(80, 330) :CGPointMake(135, 210 )];
        [self humanAnimation:CGPointMake(135, 210 ) :CGPointMake(265, 110 )];
        [self humanAnimation:CGPointMake(265, 110) :CGPointMake(444, 155 )];
        // [self humanAnimation:CGPointMake(444, 155) :CGPointMake(570, 85 )];
    }
}


- (IBAction)backButton:(UIButton *)sender {
    // 前画面に戻る
    [self dismissViewControllerAnimated:YES completion:^{
        //
    }];
}


//getData from User Default
-(void)getData{
    
    arrData = UDGetObj(keyArr);
    
    isEnableButtonOne = [arrData[0] boolValue];
    timeButtonOne = [arrData[1]integerValue];
    isEnableButtonTwo = [arrData[2] boolValue];
    timeButtonTwo = [arrData[3]integerValue];
    isEnableButtonThree = [arrData[4] boolValue];
    timeButtonThree = [arrData[5]integerValue];
    
//    _levelOneButton.enabled = isEnableButtonOne;
//    _levelTwoButton.enabled = isEnableButtonTwo;
//    _levelThreeButton.enabled = isEnableButtonThree;
    
    _levelOneButton.enabled = true;
    _levelTwoButton.enabled = true;
    _levelThreeButton.enabled = true;

    
}


//saveData to User Default
-(void)saveData{
    isEnableButtonOne=YES;
    
    if(arrData.count>0){
        [arrData removeAllObjects];
    }
    
    [arrData addObject:[NSString stringWithFormat:@"%d",isEnableButtonOne]];
    [arrData addObject:[NSString stringWithFormat:@"%d",timeButtonOne]];
    [arrData addObject:[NSString stringWithFormat:@"%d",isEnableButtonTwo]];
    [arrData addObject:[NSString stringWithFormat:@"%d",timeButtonTwo]];
    [arrData addObject:[NSString stringWithFormat:@"%d",isEnableButtonThree]];
    [arrData addObject:[NSString stringWithFormat:@"%d",timeButtonThree]];
    
    UDSetValue(keyArr, arrData);
    UDSave();
    
}


//Way of the Map
-(void)way {
    
    UIBezierPath *bezierPath = [[UIBezierPath alloc] init];
    
    if(_tagToCityMap==1){
        [bezierPath moveToPoint:CGPointMake(0, 30)];
        [bezierPath addLineToPoint:CGPointMake(105, 130)];
        [bezierPath addLineToPoint:CGPointMake(215, 200)];
        [bezierPath addLineToPoint:CGPointMake(490, 220)];
        [bezierPath addLineToPoint:CGPointMake(570, 85)];
    
    }else if (_tagToCityMap==2){
        [bezierPath moveToPoint:CGPointMake(0, 60)];
        [bezierPath addLineToPoint:CGPointMake(115, 185)];
        [bezierPath addLineToPoint:CGPointMake(280, 190)];
        [bezierPath addLineToPoint:CGPointMake(450, 130)];
        [bezierPath addLineToPoint:CGPointMake(565, 30)];
        
    }else if (_tagToCityMap==3){
        [bezierPath moveToPoint:CGPointMake(80, 330)];
        [bezierPath addLineToPoint:CGPointMake(135, 210)];
        [bezierPath addLineToPoint:CGPointMake(265, 110)];
        [bezierPath addLineToPoint:CGPointMake(444, 155)];
//        [bezierPath addLineToPoint:CGPointMake(570, 85)];
    }
    
    CAShapeLayer *pathLayer = [CAShapeLayer layer];
    pathLayer.path = bezierPath.CGPath;
    pathLayer.fillColor = [UIColor clearColor].CGColor;
    pathLayer.strokeColor = [UIColor redColor].CGColor;
    pathLayer.lineWidth = 8.0f;
    [self.mapCityView.layer addSublayer:pathLayer];
    
}

-(void)humanAnimation:(CGPoint)start:(CGPoint)stop{
    
    [humanPath moveToPoint:start];
    [humanPath addLineToPoint:stop];
    
    
    //[humanPath closePath];
    character.position = stop;
    CAKeyframeAnimation *animation = [CAKeyframeAnimation animation];
    animation.keyPath = @"position";
    
    if (isEnableButtonThree) {
        animation.duration = 5.0; //Time
    } else if (isEnableButtonTwo) {
        animation.duration = 3.0; //Time
    } else {
        animation.duration = 1.5; //Time
    }
    
    animation.path = humanPath.CGPath;
    // animation.rotationMode = kCAAnimationRotateAuto;
    [character addAnimation:animation forKey:nil];
}

// Button Animation
-(void)animationButton:(UIButton *)btn {
    
    [UIView beginAnimations:nil context:(__bridge void *)(btn)];
    [UIView setAnimationDuration:1];
    [UIView setAnimationRepeatCount:1000];
    [UIView setAnimationRepeatAutoreverses:YES];
    CGRect rect = btn.bounds;
    rect.size.width += 5;
    rect.size.height += 5;
    btn.bounds = rect;
    [UIView commitAnimations];
    
}

- (IBAction)LevelButton:(UIButton *)sender {
    
    if(isTabOpen==false){
        CGPoint location = _mapCityView.center;
        CGFloat center_y = self.view.center.y;
        location.x = center_y - 201;
        [UIView animateWithDuration:0.5 animations:^{
            _mapCityView.center = location;
        }];
        _slitimer = [NSTimer scheduledTimerWithTimeInterval:3 target:self selector:@selector(goNextPhoto) userInfo:nil repeats:YES];
    }
    _descriptionLabel.text=[self getDescription:_tagToCityMap :sender.tag];
    
    if(_tagToCityMap==1){
        
        if(sender.tag==1){
            [self sliImageView:4 :@"smarket"];
        }else if(sender.tag==2){
            [self sliImageView:5 :@"lion"];
        }else if(sender.tag==3){
            [self sliImageView:5:@"beach"];
        }
        
    }else if (_tagToCityMap==2){
        
        if(sender.tag==1){
            [self sliImageView:5 :@"street"];
        }else if(sender.tag==2){
            [self sliImageView:6 :@"village"];
        }else if(sender.tag==3){
            [self sliImageView:6:@"angkor"];
        }
        
    }else if (_tagToCityMap==3){
        
        if(sender.tag==1){
            [self sliImageView:4 :@"indepence"];
        }else if(sender.tag==2){
            [self sliImageView:5 :@"Market"];
        }else if(sender.tag==3){
            [self sliImageView:4 :@"watPhnom"];
        }
        
    }
    
    selectedStageNum = sender.tag;
    
    isTabOpen=true;
    
}

//function for getImage to ImageSli
-(void)sliImageView:(int)n:(NSString *)name{
    if(photoNameArray.count>0){
        [photoNameArray removeAllObjects];
    }
    for (int i=0; i<=n;i++) {
        [photoNameArray addObject:[NSString stringWithFormat:@"%@%d.jpg",name,i]];
    }
}


//Image Sli Show
-(void) goNextPhoto {
    
    // If not last photo
    if(nextNo < (photoNameArray.count -1)) {
        nextNo++;
    } else {
        nextNo = 0;
    }
    UIImageView *frontView = _slideshowImageView;
    UIImage *nextPhoto = [UIImage imageNamed:photoNameArray[nextNo]];
    UIImageView *nextImageView = [[UIImageView alloc] initWithImage:nextPhoto];
    nextImageView.frame = frontView.frame;
    [UIView transitionFromView:frontView toView:nextImageView duration:1.0 options:UIViewAnimationOptionTransitionCurlUp completion:nil];
    _slideshowImageView = nextImageView;
    
}


- (IBAction)gotoButton:(UIButton *)sender {
    
    GameScreen *gameScreen = [self.storyboard instantiateViewControllerWithIdentifier:@"GameScreenID"];
    int test = sender.tag + (_tagToCityMap-1)*3;
    gameScreen.stageNumber = selectedStageNum + (_tagToCityMap-1)*3;
    [self presentViewController:gameScreen animated:YES completion:nil];
    
}

- (IBAction)cancel:(UIButton *)sender {
    
    CGPoint location = _mapCityView.center;
    CGFloat center_y = self.view.center.y;
    
    location.x = center_y ;
    [UIView animateWithDuration:0.5 animations:^{
        _mapCityView.center = location;
    }];
    if(_slitimer.isValid){
        [_slitimer invalidate];
    }
    isTabOpen=false;
    
}

//Set Position of button to each stage
-(void)setBtnPosition:(int)lvl{
    
    NSString* lvlOneImage,*lvlTwoImage,*lvlThreeImage;
    
    CGRect lvlOne = _levelOneButton.frame;
    CGRect lvlTwo= _levelTwoButton.frame;
    CGRect lvlThree=_levelThreeButton.frame;
    
    
    if(lvl==1){
        
        lvlOne.origin.x=65;
        lvlOne.origin.y=130;
        
        lvlTwo.origin.x=180;
        lvlTwo.origin.y=205;
        
        lvlThree.origin.x=485;
        lvlThree.origin.y=215;

        
    }else if(lvl==2){
        
        lvlOne.origin.x=101;
        lvlOne.origin.y=125;
        
        lvlTwo.origin.x=275;
        lvlTwo.origin.y=185;
        
        lvlThree.origin.x=450;
        lvlThree.origin.y=122;
        
    }else if (lvl==3){
        
        lvlOne.origin.x=85;
        lvlOne.origin.y=157;
        
        lvlTwo.origin.x=243;
        lvlTwo.origin.y=52;
        
        lvlThree.origin.x=411;
        lvlThree.origin.y=157;
        
    }
    
    /* change image button each station*/
    [self.levelOneButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%d%d.png",lvl,1] ] forState:UIControlStateNormal];
    [self.levelTwoButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%d%d.png",lvl,2] ] forState:UIControlStateNormal];
    [self.levelThreeButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"%d%d.png",lvl,3] ] forState:UIControlStateNormal];
    
    [self.levelOneButton setTag:1];
    [self.levelTwoButton setTag:2];
    [self.levelThreeButton setTag:3];
    
    _levelOneButton.frame=lvlOne;
    _levelTwoButton.frame=lvlTwo;
    _levelThreeButton.frame=lvlThree;
    
}


//Descript of each station
-(NSString* ) getDescription:(int)level:(int)state{
    NSString* description;
    if(level==1){
        if(state==1){
            description=@"The Independence Monument (Khmer: វិមានឯករាជ្យ, 'Vimean Akareach') in Phnom Penh, capital of Cambodia, was built in 1958 for Cambodia's independence from France in 1953.It is in the form of a lotus-shaped stupa, of the style seen at the great Khmer temple at Angkor Wat and other Khmer historical sites.";
        }else if(state==3){
            description=@"The Central Market (Khmer: ផ្សារធំថ្មី, 'Psah Thom Thmey', 'New Grand Market'), is a large market constructed in 1937 in the shape of a dome with four arms branching out into vast hallways with countless stalls of goods.";
            
        }else if(state==2){
            description=@"Wat Phnom (Khmer: វត្តភ្នំ; 'Mountain Pagoda') is a Buddhist temple (wat) located in Phnom Penh, Cambodia. It was built in 1373, and stands 27 metres (88.5 ft) above the ground. It is the tallest religious structure in the city";
        }
    }else if (level==2){
        if(state==1){
            description=@"The pub street (ផាប់​ស្រ្ទីត) is a street with many pubs, restaurants and shops in lined end to end on both sides. After returning from the temple tours in the evening, many tourists are taken to the Pub Street to take advantage of the happy hour deals or a good meal. The street is closed to traffic in the evening; allowing pedestrians to stroll about freely. The street comes alive when the bars crank up the music and join the restaurants to spill their seats out onto the curbs. On this street, one can find bars and restaurants serving drinks and food of almost all culture and flavours. ";
        }else if (state==2){
            description=@"The Cambodian Cultural Village (ភូមិ​វប្ប​ធម៌​កម្ពុជា​)​ is a theme park and museum in Siem Reap, Cambodia.[1] It is located on the road to the airport, 6km from the town. This theme park was constructed in 2001 and opened to the public on 24 September 2003. It covers a total area of 210,000 square meters. It presents miniature versions of important historical buildings and structures, together with local customs. There are eleven unique villages, representing the varied culture heritage of nineteen races. At each village are wood houses, carvings, soft skill in stone, traditional performances in the different style such as Apsara Dancing, performances of ethnic minorities from Northeast of Cambodia, traditional wedding ceremony, Circus, Popular games, Peacock dancing, Acrobat, elephants shows, Khmer boxing, etc.";
        }else if(state==3){
            description=@"Angkor Wat (អង្គរវត្ត), is a Hindu temple complex built to replicate the heavens on earth.  Constructed by the King Suryavarman II in the early 12th century, it is the best-preserved temple and is the only one to have remained a significant religious centre since its foundation. The temple is the epitome of the high classical style of Khmer architecture. It has become a symbol of Cambodia, appearing on its national flag, and it is the country's prime attraction for visitors.";
        }
        
    }else if(level==3){
        if(state==1){
            description=@"The Phsar Leu Market (ផ្សារលើ) is the biggest traditional market in Sihanoukville. Phsar Leu Market located in the heart of the city at 7 Makara Street, the Phsar Leu Market is sprawled at a distance of 2 kilometers from the popular Ochheuteal Beach in Sihanoukville. Featuring sections which provide fruits, clothing, seafood, tailors, jewelry, barbers and Khmer food, the building of this market is beautiful is a well-known landmark in town. However, the Phsar Leu Market closes and open with the, it mean when the sun rise the market open when the sun set the market close.";
        }else if (state==2){
            description=@"Golden Lion (តោ​ពីរ​) is a landmark of Sihanoukville to tell you that where you are. This landmark is have couple of big lions on a roundabout in town near Ochheuteal Beach. The Golden Lion was constructed in 1996. This landmark is surrounded by Restaurant, Karaoke, Pub, Gift shop, Hotel and Casino.";
        }else if(state==3){
            description=@"Ochheuteal Beach (អូ​ឈឺ​ទាល) is the most active beach in Sihanoukville and very popular with travelers and weekenders alike. Occheuteal Beach is a long and narrow strip of beach lined with Casuarina trees, grass umbrellas, rental chairs and little drink huts as well as bigger restaurants and night-time party spots from one end of Ochheuteal to the other. Next to it is “Serendipity beach” which is especially popular with budget travelers. Noted for small guesthouse rooms right on the beach. Aside from these guest houses on the beach there are around 30 beach huts serving good value meals and a wide selection of drinks.";
        }
    }
    return description;
}

@end
