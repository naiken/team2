//
//  GameOverView.m
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/19/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "GameOverView.h"

@implementation GameOverView {
    CAEmitterLayer* explotionEmitter; //1
}

-(void)awakeFromNib {
    //set ref to the layer
    explotionEmitter = (CAEmitterLayer*)self.layer;
    explotionEmitter.emitterSize = CGSizeMake(10, 10);
    explotionEmitter.renderMode = kCAEmitterLayerAdditive;
    
    CAEmitterCell* explotion = [CAEmitterCell emitterCell];
    explotion.contents = (id)[[UIImage imageNamed:@"Particles_fireScaled.png"] CGImage];
    explotion.color = [[UIColor colorWithRed:204/255.0f green:102/255.0f blue:51/255.0f alpha:0.3]
                  CGColor];
    explotion.birthRate = 0;
    explotion.lifetime = 1.5;
    explotion.lifetimeRange = 0.5;
    explotion.velocity = 200;
    explotion.velocityRange = 300;
    explotion.emissionRange = 2 * M_PI;
    explotion.scaleSpeed = 0.3;
    explotion.spin = 0.5;
    [explotion setName:@"fire"];
    
    //add the cell to the layer and we're done
    explotionEmitter.emitterCells = [NSArray arrayWithObject:explotion];
}

+ (Class) layerClass {
    //configure the UIView to have emitter layer
    return [CAEmitterLayer class];
}


-(void)startEmitting:(int)x :(int)y {
    
    explotionEmitter.emitterPosition = CGPointMake(x, y);
    [explotionEmitter setValue:[NSNumber numberWithInt:500]
               forKeyPath:@"emitterCells.fire.birthRate"];
}

-(void)stopEmitting {
    
    [explotionEmitter setValue:[NSNumber numberWithInt:0]
               forKeyPath:@"emitterCells.fire.birthRate"];
}


@end
