//
//  GameStartView.m
//  CambodiaAdventure_ver1
//
//  Created by 田村 昂之 on 5/18/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "GameStartView.h"

@implementation GameStartView {
    CAEmitterLayer* _fireEmitter;
    CGSize _size;
}

-(void)awakeFromNib {
    
    //set ref to the layer
    _fireEmitter = (CAEmitterLayer*)self.layer;
    
    _size = self.bounds.size;
    
    //configure the emitter layer
    _fireEmitter.emitterPosition = CGPointMake(0, _size.height / 2);
    _fireEmitter.emitterSize = CGSizeMake(3, 3);
    _fireEmitter.renderMode = kCAEmitterLayerAdditive;
    
    CAEmitterCell* fire = [CAEmitterCell emitterCell];
    fire.birthRate = 20;
    fire.lifetime = 0.5;
    fire.lifetimeRange = 0.5;
    fire.color = [[UIColor colorWithRed:250/255.0f green:250/255.0f blue:250/255.0f alpha:0.6]
                  CGColor];
    
    fire.contents = (id)[[UIImage imageNamed:@"particle_lightblue.png"] CGImage];
    fire.velocity = 200;
    fire.velocityRange = 20;
    fire.emissionRange = 2 * M_PI;
    fire.scaleSpeed = 0.3;
    fire.spin = 0.5;
    [fire setName:@"fire"];
    
    //add the cell to the layer and we're done
    _fireEmitter.emitterCells = [NSArray arrayWithObject:fire];
}

+ (Class) layerClass {
    //configure the UIView to have emitter layer
    return [CAEmitterLayer class];
}



-(void)setEmitterPosition:(int)x {
    CGPoint newEmitterPosition = CGPointMake(x * 50 - 50 , _size.height / 2) ;
    _fireEmitter.emitterPosition = newEmitterPosition;
}



@end
