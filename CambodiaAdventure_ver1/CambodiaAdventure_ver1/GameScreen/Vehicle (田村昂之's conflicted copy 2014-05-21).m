//
//  Vehicle.m
//  Level1Stage1
//
//  Created by 田村 昂之 on 5/7/14.
//  Copyright (c) 2014 tamura takayuki. All rights reserved.
//

#import "Vehicle.h"

@implementation Vehicle

+(int)decideSpeed:(NSInteger)vehicleId {
    float speed;
    int rand = arc4random() % 5;
    
    // nomal car
    if(vehicleId == 1) {
        speed = 8 + rand;
        
        // scotter
    } else if (vehicleId == 2) {
        speed = 4 + rand;
        
        // tuktuk
    } else if (vehicleId == 3) {
        speed = 5 + rand;
        
        // bigcar
    } else if (vehicleId == 4) {
        speed = 10 + rand;
    }
    
    return speed;
}



// directionId R = 1, L = 2, D = 3, U = 4
+(float)getCollisinX1:(NSInteger)vehicleId direction:(int)directionId {
    
    if (directionId == 1) {
        
        if (vehicleId == 1) {
            return 80;
        } else if (vehicleId == 2) {
            return 20;
        } else if (vehicleId == 3) {
            return 100;
        } else if (vehicleId == 4) {
            return 120;
        } else {
            return 0;
        }
        
    } else if (directionId == 2) {
        
        return 10;
        
    } else if (directionId == 3 || directionId == 4) {
        
        if (vehicleId == 1) {
            return 45;
        } else if (vehicleId == 2) {
            return 35;
        } else if (vehicleId == 3) {
            return 45;
        } else if (vehicleId == 4) {
            return 55;
        } else {
            return 0;
        }
        
    } else if (directionId == 5) {
        if (vehicleId == 1) {
            return 65;
        } else if (vehicleId == 2) {
            return 34;
        } else if (vehicleId == 3) {
            return 55;
        } else if (vehicleId == 4) {
            return 70;
        } else {
            return 0;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 46;
        } else if (vehicleId == 2) {
            return 15;
        } else if (vehicleId == 3) {
            return 25;
        } else if (vehicleId == 4) {
            return 58;
        } else {
            return 0;
        }
        
    } else {
        return 0;
    }
}


// directionId R = 1, L = 2, D = 3, U = 4
+(float)getCollisinY1:(NSInteger)vehicleId direction:(int)directionId {
    
    if (directionId == 1 || directionId == 2) {
        if (vehicleId == 1) {
            return 10;
        } else if (vehicleId == 2) {
            return 10;
        } else if (vehicleId == 3) {
            return 10;
        } else if (vehicleId == 4) {
            return 20;
        } else {
            return 0;
        }
        
    } else if (directionId == 3) {
        if (vehicleId == 1) {
            return 80;
        } else if (vehicleId == 2) {
            return 20;
        } else if (vehicleId == 3) {
            return 100;
        } else if (vehicleId == 4) {
            return 120;
        } else {
            return 0;
        }
        
    } else if (directionId == 4) {
        if (directionId == 1) {
            return 20;
        } else {
            return 10;
        }
        
    } else if (directionId == 5) {
        if (vehicleId == 1) {
            return 58;
        } else if (vehicleId == 2) {
            return 32;
        } else if (vehicleId == 3) {
            return 85;
        } else if (vehicleId == 4) {
            return 88;
        } else {
            return 0;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 17;
        } else if (vehicleId == 2) {
            return 12;
        } else if (vehicleId == 3) {
            return 15;
        } else if (vehicleId == 4) {
            return 12;
        } else {
            return 0;
        }
        
    } else {
        return 0;
    }
}

// directionId R = 1, L = 2, D = 3, U = 4
+(float)getCollisinX2:(NSInteger)vehicleId direction:(int)directionId {
    
    if (directionId == 1) {
        if (vehicleId == 1) {
            return 80;
        } else if (vehicleId == 2) {
            return 20;
        } else if (vehicleId == 3) {
            return 70;
        } else if (vehicleId == 4) {
            return 120;
        } else {
            return 0;
        }
        
    } else if (directionId == 2) {
        
        if (vehicleId == 1) {
            return 20;
        } else if (vehicleId == 3) {
            return 30;
        } else {
            return 10;
        }
        
    } else if (directionId == 3 || directionId == 4) {
        
        if (vehicleId == 1) {
            return 8;
        } else if (vehicleId == 2) {
            return 5;
        } else if (vehicleId == 3) {
            return 8;
        } else if (vehicleId == 4) {
            return 5;
        } else {
            return 0;
        }
        
    } else if (directionId == 5) {
        if (vehicleId == 1) {
            return 75;
        } else if (vehicleId == 2) {
            return 42;
        } else if (vehicleId == 3) {
            return 57;
        } else if (vehicleId == 4) {
            return 95;
        } else {
            return 0;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 19;
        } else if (vehicleId == 2) {
            return 38;
        } else if (vehicleId == 3) {
            return 60;
        } else if (vehicleId == 4) {
            return 28;
        } else {
            return 0;
        }
        
    }else {
        return 0;
    }
}



+(float)getCollisinY2:(NSInteger)vehicleId direction:(int)directionId {
    
    if (directionId == 1 || directionId == 2) {
        if (vehicleId == 1) {
            return 5;
        } else if (vehicleId == 2) {
            return 5;
        } else if (vehicleId == 3) {
            return 5;
        } else if (vehicleId == 4) {
            return 5;
        } else {
            return 0;
        }
        
    } else if (directionId == 3) {
        if (vehicleId == 1) {
            return 60;
        } else if (vehicleId == 2) {
            return 20;
        } else if (vehicleId == 3) {
            return 70;
        } else if (vehicleId == 4) {
            return 120;
        } else {
            return 0;
        }
        
    } else if (directionId == 4) {
        
        if (vehicleId == 1) {
            return 20;
        } else if (vehicleId == 3) {
            return 30;
        } else {
            return 10;
        }
        
    } else if (directionId == 5) {
        
        if (vehicleId == 1) {
            return 45;
        } else if (vehicleId == 2) {
            return 11;
        } else if (vehicleId == 3) {
            return 68;
        } else if (vehicleId == 4) {
            return 47;
        } else {
            return 110;
        }
        
    } else if (directionId == 6) {
        
        if (vehicleId == 1) {
            return 16;
        } else if (vehicleId == 2) {
            return 12;
        } else if (vehicleId == 3) {
            return 15;
        } else if (vehicleId == 4) {
            return 40;
        } else {
            return 0;
        }
        
    } else {
        return 0;
    }
}





@end
