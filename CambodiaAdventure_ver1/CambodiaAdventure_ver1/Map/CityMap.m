//
//  CityViewController.m
//  cityMap
//
//  Created by Measna on 5/7/14.
//  Copyright (c) 2014 Measna. All rights reserved.
//

#import "CityMap.h"


@interface CityMap () {
    
    CEHScore *_score;
    NSString* keyArr;
    CALayer *character;
    NSMutableArray* arrData;
    NSMutableArray *photoNameArray;
    NSTimer *_slitimer;
    UIBezierPath *humanPath;
    
    int nextNo;// For slide show
    BOOL isTabOpen;
    int timeButtonOne,timeButtonTwo,timeButtonThree;
    int selectedStageNum;
    
}

@end






@implementation CityMap


-(void)viewDidLayoutSubviews {
    [self setBtnPosition:(int)_tagToCityMap];// Only here
}


- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    arrData = [[NSMutableArray alloc]init];
    keyArr = [NSString stringWithFormat:@"LVL%ld",(long)_tagToCityMap];//key for UD
    arrData = UDGetValue(keyArr);
    
    _score = [[CEHScore alloc] init];
    
    photoNameArray=[[NSMutableArray alloc]init];
    humanPath = [[UIBezierPath alloc] init];
    
    nextNo = 0;// For slide show


    
    /*Add BackGround*/
    if(_tagToCityMap == 1){
        _mapImageView.image=[UIImage imageNamed:@"Sihanoukville.png"];
    }else if (_tagToCityMap == 2){
        _mapImageView.image=[UIImage imageNamed:@"SiemReap.png"];
    } else if(_tagToCityMap == 3){
        _mapImageView.image=[UIImage imageNamed:@"PhnomPenh.png"];
    }
    
    /*Add way*/
    [self way];
    
    /*Add Character*/
    character = [CALayer layer];
    character.frame = CGRectMake(-100, 0, 60, 90);
    character.contents = (__bridge id)[UIImage imageNamed:@"Chara_right.png"].CGImage;
    [self.mapCityView.layer addSublayer:character];
    
    _descriptionLabel.textAlignment = NSTextAlignmentJustified;// text center wrap content
    [self.descriptionLabel setFont:[UIFont fontWithName:@"KhmerOSWatPhnom" size:14]];
}


-(void)viewDidAppear:(BOOL)animated {
    
    /*Animation Character*/
    if(_tagToCityMap==1){
        
        [self humanAnimation:CGPointMake(0, 30) :CGPointMake(105, 130 )];
        
        if ([[arrData objectAtIndex:0] boolValue]) {
            [self humanAnimation:CGPointMake(105, 130) :CGPointMake(215, 200 )];
        }
        if ([[arrData objectAtIndex:2] boolValue]) {
            [self humanAnimation:CGPointMake(215, 200) :CGPointMake(490, 220 )];
        }
        if ([[arrData objectAtIndex:4] boolValue] && [[arrData objectAtIndex:6] intValue] >= 6) {
            [self humanAnimation:CGPointMake(490, 220) :CGPointMake(540, 85 )];
        }
        
        
    }else if(_tagToCityMap==2){
        
        [self humanAnimation:CGPointMake(0, 60) :CGPointMake(115, 185 )];
        if ([[arrData objectAtIndex:0] boolValue]) {
            [self humanAnimation:CGPointMake(115, 185) :CGPointMake(280, 190 )];
        }
        if ([[arrData objectAtIndex:2] boolValue]) {
            [self humanAnimation:CGPointMake(280, 190) :CGPointMake(450, 130 )];
        }
        if ([[arrData objectAtIndex:4] boolValue]) {
            [self humanAnimation:CGPointMake(450, 130) :CGPointMake(540, 80 )];
        }
        
        
    }else if (_tagToCityMap==3){
        
        [self humanAnimation:CGPointMake(80, 330) :CGPointMake(135, 210 )];
        if ([[arrData objectAtIndex:0] boolValue]) {
            [self humanAnimation:CGPointMake(135, 210 ) :CGPointMake(265, 110 )];
        }
        if ([[arrData objectAtIndex:2] boolValue]) {
            [self humanAnimation:CGPointMake(265, 110) :CGPointMake(444, 155 )];
        }
        // [self humanAnimation:CGPointMake(444, 155) :CGPointMake(570, 85 )];
    }
}


- (IBAction)backButton:(UIButton *)sender {
    
    CambodianMap *cambodianMap = [self.storyboard instantiateViewControllerWithIdentifier:@"CambodianMapID"];
    [self presentViewController:cambodianMap animated:YES completion:nil];
    
}


//getData from User Default
-(void)getData{
    arrData = UDGetValue(keyArr);
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

-(void)humanAnimation:(CGPoint)startPosition:(CGPoint)stopPosition{
    
    [humanPath moveToPoint:startPosition];
    [humanPath addLineToPoint:stopPosition];
    
    
    //[humanPath closePath];
    character.position = stopPosition;
    CAKeyframeAnimation *animation = [CAKeyframeAnimation animation];
    animation.keyPath = @"position";
    
    if ([[arrData objectAtIndex:4] boolValue]) {
        animation.duration = 5.0; //Time
    } else if ([[arrData objectAtIndex:2] boolValue]) {
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
    
    if(_tagToCityMap==1){
        
        if(sender.tag==1){
            [self sliImageView:4 :@"smarket"];
            [self setLoutsWithTimeAllowance:20 shortestTime:[[arrData objectAtIndex:1] intValue]];
        }else if(sender.tag==2){
            [self sliImageView:5 :@"lion"];
            [self setLoutsWithTimeAllowance:30 shortestTime:[[arrData objectAtIndex:3] intValue]];
        }else if(sender.tag==3){
            [self sliImageView:5:@"beach"];
            [self setLoutsWithTimeAllowance:30 shortestTime:[[arrData objectAtIndex:5] intValue]];
        }
        
    }else if (_tagToCityMap==2){
        
        if(sender.tag==1){
            [self sliImageView:5 :@"street"];
            [self setLoutsWithTimeAllowance:20 shortestTime:[[arrData objectAtIndex:1] intValue]];
        }else if(sender.tag==2){
            [self sliImageView:6 :@"village"];
            [self setLoutsWithTimeAllowance:40 shortestTime:[[arrData objectAtIndex:3] intValue]];
        }else if(sender.tag==3){
            [self sliImageView:6:@"angkor"];
            [self setLoutsWithTimeAllowance:40 shortestTime:[[arrData objectAtIndex:5] intValue]];
        }
        
    }else if (_tagToCityMap==3){
        
        if(sender.tag==1){
            [self sliImageView:4 :@"indepence"];
            [self setLoutsWithTimeAllowance:20 shortestTime:[[arrData objectAtIndex:1] intValue]];
        }else if(sender.tag==2){
            [self sliImageView:5 :@"Market"];
            [self setLoutsWithTimeAllowance:35 shortestTime:[[arrData objectAtIndex:3] intValue]];
        }else if(sender.tag==3){
            [self sliImageView:4 :@"watPhnom"];
            [self setLoutsWithTimeAllowance:35 shortestTime:[[arrData objectAtIndex:5] intValue]];
        }
        
    }
    
    if(isTabOpen==false){
        
        // Set first image
        UIImageView *frontView = _slideshowImageView;
        frontView.image = [UIImage imageNamed:photoNameArray[0]];
        
        CGPoint location = _mapCityView.center;
        CGFloat center_y = self.view.center.y;
        location.x = center_y - 201;
        [UIView animateWithDuration:0.5 animations:^{
            _mapCityView.center = location;
        }];
        _slitimer = [NSTimer scheduledTimerWithTimeInterval:3 target:self selector:@selector(goNextPhoto) userInfo:nil repeats:YES];
    }
    _descriptionLabel.text=[self getDescription:_tagToCityMap :sender.tag];
    
    
    selectedStageNum = sender.tag;
    
    isTabOpen=true;
    
}

-(void)setLoutsWithTimeAllowance:(int)tA shortestTime:(int)sT {
    int lotusNum = [_score getLotus:tA :sT];
    
    // Initialize
    _lotusImg1.alpha = 1.0;
    _lotusImg2.alpha = 1.0;
    _lotusImg3.alpha = 1.0;
    
    // Change Lots alpha
    switch (lotusNum) {
        case 0:
            _lotusImg1.alpha = 0.3;
        case 1:
            _lotusImg2.alpha = 0.3;
        case 2:
            _lotusImg3.alpha = 0.3;
        default:
            break;
    }
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
    gameScreen.stageSerialNumber = selectedStageNum + (_tagToCityMap-1)*3;
//    [self dismissViewControllerAnimated:YES completion:nil];
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
    
    /* Set Btn backgrond */
    [self.levelOneButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"btn%d%d.png",lvl,1] ] forState:UIControlStateNormal];
    [self.levelTwoButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"btn%d%d.png",lvl,2] ] forState:UIControlStateNormal];
    [self.levelThreeButton setBackgroundImage:[UIImage imageNamed:[NSString stringWithFormat:@"btn%d%d.png",lvl,3] ] forState:UIControlStateNormal];
    
    // if user have not cleared stage1 yet
    if (![[arrData objectAtIndex:0] boolValue]) {
        _levelTwoButton.enabled = NO;
    }
    
    // if user have not cleared stage2 yet
    if (![[arrData objectAtIndex:2] boolValue]) {
        _levelThreeButton.enabled = NO;
    }
    
    _levelOneButton.frame=lvlOne;
    _levelTwoButton.frame=lvlTwo;
    _levelThreeButton.frame=lvlThree;
    
}


//Descript of each station
-(NSString* ) getDescription:(int)level:(int)state{
    NSString* description;
    if(level==3){
        if(state==1){
            description=@"独立記念塔 (Khmer: វិមានឯករាជ្យ, 'Vimean Akareach') \n\n 独立記念塔はカンボジアの首都のプノンペンにある。カンボジアが、フランスから独立した1953年から5年後の1958年に建立された。アンコールワットの中央塔や、様々なクメールの史跡を意識したデザインとなっている。独立記念塔中心に円形の道路が舗装されており、特徴的である。夜や週末はライトアップされるので，昼間とは違った優雅な姿を堪能できる。";
        }else if(state==2){
            description=@"セントラルマーケット (Khmer: ផ្សារធំថ្មី, 'Psah Thom Thmey', 'New Grand Market') \n\n セントラルマーケットは、プノンペン市街の中心に位置する大規模な市場である。食料をはじめとして、衣服、生活用品、家電製品と、ここにくれば生活に必要なものはあらかた手に入る。広いうえに、品物がごった返しているので、いろいろと探検してみると新しい発見がありおもしろい。中央に位置する白いドーム状の建物はひときは荘厳である。";
            
        }else if(state==3){
            description=@"ワットプノン (Khmer: វត្តភ្នំ; 'Mountain Pagoda') \n\n ワットプノンは1373年に建立された、プノンペンのシンボルともなっている仏教寺院である。小高い丘の上にある上に、高さは27mとプノンペン市内の宗教建築物としては随一の高さを誇っており、遠くからでも目立つ。ワットプノンの本堂には黄金の仏が安置されており一見の価値がある。また周囲の壁面は宗教絵画で埋め尽くされている。";
        }
    }else if (level==2){
        if(state==1){
            description=@"パブストリート (ផាប់​ស្រ្ទីត) \n\n パブストリートには、たくさんのパブをはじめとして、レストランや観光客向けのお店もある。昼間さまざまな遺跡めぐりをした観光客が、ここで飲食を楽しみながら昼間の思い出話に花を咲かせている光景がどこかしこに見られる。夜のパブストリートは歩行者天国であり、自由に往来できる。様々なお店があつまっているので、ここにくれば古今東西、世界中のありとあらゆる味を堪能出来る。";
        }else if (state==2){
            description=@"カンボジアン・カルチャー・ヴィレッジ (ភូមិ​វប្ប​ធម៌​កម្ពុជា​) \n\n カンボジアン・カルチャー・ヴィレッジはシェムリアップのテーマパークであり、博物館である。2001年に建立され、2003年にオープンした。全面積は210,000平方メートルと広大である。中には11の村があり、それぞれがカンボジアの異なる文化を象徴している。また、伝統的な踊りや伝統的な結婚式、象の曲芸など、さまざまショウを堪能することができる。他には、カンボジアの歴史的に重要な建物のミニチュアが展示されており、一見の価値がある。";
        }else if(state==3){
            description=@"アンコールワット (អង្គរវត្ត) \n\n アンコールワットはヒンドゥー教の寺院で、地上の楽園として建てられた。12世紀前半、アンコール王朝のスーリヤヴァルマン2世によって建設されて以来、その荘厳な姿を保っている唯一無二も建築物であり、世界的にも有名である。毎日多くの観光客が訪れる、カンボジア随一の観光名所である。観光客は内部に入り、遺跡に直に触れることができる。カンボジアの国旗にも描かれており、カンボジアの象徴といっても過言ではない。";
        }
        
    }else if(level==1){
        if(state==1){
            description=@"プサー・ルー・マーケット (ផ្សារលើ) \n\n プサー・ルー・マーケットはシアヌークビルで最大の伝統的な市場である。プサー・ルー・マーケットは中心街のマカラストリートで、オーチティルビーチから2kmに渡って横たわっている。フルーツや衣類、シーフードや仕立て屋、宝石店、床屋など様々なものが売られている。日の出とともにオープンし、日没とともにクローズする。";
        }else if (state==2){
            description=@"ゴールデン・ライオン (តោ​ពីរ​) \n\n ゴールデン・ライオンはシアヌークビルのシンボルである。オスとメスペアのライオンの黄金像である。人気の観光スポットである。1996年に造られ、オーチティルビーチ近くの町のロータリーに位置しており、周囲にはレストランやパブ、お土産屋やホテルなど観光客向けの施設が多数ある。";
        }else if(state==3){
            description=@"オーチティルビーチ (អូ​ឈឺ​ទាល) \n\n Ochheuteal オーチティルビーチはシアヌークビルで最も有名なビーチで、観光客に人気が高い。白い鳴き砂と澄み渡った水が広がっている。その美しさにより、アジアのベストビーチ第8位に選ばれたこともある。長細い形状のビーチには、植物の葉で作られた可愛らしい小屋が立ち並び、飲み物などを売る小さなお店や、ビーチパラソルのレンタルショップなどの他、レストランもある。";
        }
    }
    return description;
}

@end
