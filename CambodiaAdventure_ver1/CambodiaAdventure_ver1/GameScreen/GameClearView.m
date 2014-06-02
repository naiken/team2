//
//  GameClearView.m
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/18/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "GameClearView.h"

@implementation GameClearView {
    CAEmitterLayer* fireworksEmitter;
    CAEmitterCell *sparkCell;
}

-(void)awakeFromNib {
    
    //set ref to the layer
    fireworksEmitter = (CAEmitterLayer*)self.layer; //2
    
    //configure the emitter layer
    fireworksEmitter.emitterSize = CGSizeMake(1, 1);
    fireworksEmitter.renderMode = kCAEmitterLayerAdditive;
    
    
    sparkCell = [CAEmitterCell emitterCell];
    sparkCell.contents = (id)[[UIImage imageNamed:@"particle1.png"] CGImage];
    sparkCell.emissionRange = 2 * M_PI;
    sparkCell.birthRate = 0;
    sparkCell.scale = 0.5;
    sparkCell.velocity = 100;
    sparkCell.lifetime = 1.5;
    sparkCell.yAcceleration = 40;
    sparkCell.alphaSpeed = -0.1;
    sparkCell.scaleSpeed = -0.1;
    [sparkCell setName:@"fire"];
    
    //add the cell to the layer and we're done
    fireworksEmitter.emitterCells = [NSArray arrayWithObject:sparkCell];
}

+ (Class) layerClass {
    //configure the UIView to have emitter layer
    return [CAEmitterLayer class];
}


-(void)startEmitting {
    
    int pX = arc4random() % 380 + 100;
    int pY = arc4random() % 220 + 50;
    int colorR = arc4random() % 25;
    int colorG = arc4random() % 25;
    int colorB = arc4random() % 25;
    int velocity = arc4random() % 5;
    fireworksEmitter.emitterPosition = CGPointMake(pX, pY);
    sparkCell.color = [[UIColor colorWithRed:(colorR*10)/255.0f green:(colorG*10)/255.0f blue:(colorB*10)/255.0f alpha:0.6] CGColor];
    sparkCell.velocity = 100 + velocity*10;
    [fireworksEmitter setValue:[NSNumber numberWithInt:500]
                    forKeyPath:@"emitterCells.fire.birthRate"];
    
}

-(void)stopEmitting {
    
    //turn on/off the emitting of particles
    [fireworksEmitter setValue:[NSNumber numberWithInt:0]
                    forKeyPath:@"emitterCells.fire.birthRate"];
}

@end
