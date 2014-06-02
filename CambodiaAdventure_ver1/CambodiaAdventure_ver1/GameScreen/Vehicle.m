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
        speed = 5 + rand;
        
        // tuktuk
    } else if (vehicleId == 3) {
        speed = 6 + rand;
        
        // bigcar
    } else if (vehicleId == 4) {
        speed = 10 + rand;
        
        // cyclo
    }  else if (vehicleId == 5) {
        speed = 3 + rand;
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
            return 44;
        } else if (vehicleId == 4) {
            return 120;
        } else if (vehicleId == 5) {
            return 50;
        } else {
            return 0;
        }
        
    } else if (directionId == 2) {
        
        if (vehicleId == 1) {
            return 20;
        } else if (vehicleId == 3) {
            return 44;
        } else {
            return 10;
        }
        
    } else if (directionId == 3 || directionId == 4) {
        if (vehicleId == 1) {
            return 40;
        } else if (vehicleId == 2) {
            return 15;
        } else if (vehicleId == 3) {
            return 40;
        } else if (vehicleId == 4) {
            return 50;
        } else if (vehicleId == 5) {
            return 55;
        } else {
            return 0;
        }
        
    } else if (directionId == 5) {
        if (vehicleId == 1) {
            return 68;
        } else if (vehicleId == 2) {
            return 13;
        } else if (vehicleId == 3) {
            return 75;
        } else if (vehicleId == 4) {
            return 48;
        } else {
            return 0;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 48;
        } else if (vehicleId == 2) {
            return 30;
        } else if (vehicleId == 3) {
            return 43;
        } else if (vehicleId == 4) {
            return 7;
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
            return 5;
        } else if (vehicleId == 2) {
            return 5;
        } else if (vehicleId == 3) {
            return 12;
        } else if (vehicleId == 4) {
            return 5;
        } else if (vehicleId == 5) {
            return 5;
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
        } else if (vehicleId == 5) {
            return 45;
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
            return 40;
        } else if (vehicleId == 2) {
            return 25;
        } else if (vehicleId == 3) {
            return 58;
        } else if (vehicleId == 4) {
            return 72;
        } else {
            return 0;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 17;
        } else if (vehicleId == 2) {
            return 15;
        } else if (vehicleId == 3) {
            return 14;
        } else if (vehicleId == 4) {
            return 5;
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
            return 78;
        } else if (vehicleId == 4) {
            return 120;
        } else if (vehicleId == 5) {
            return 50;
        } else {
            return 0;
        }
        
    } else if (directionId == 2) {
        
        if (vehicleId == 1) {
            return 20;
        } else {
            return 10;
        }
        
    } else if (directionId == 3 || directionId == 4) {
        if (vehicleId == 1) {
            return 10;
        } else if (vehicleId == 2) {
            return 15;
        } else if (vehicleId == 3) {
            return 10;
        } else if (vehicleId == 4) {
            return 10;
        } else if (vehicleId == 5) {
            return 10;
        } else {
            return 0;
        }
        
    } else if (directionId == 5) {
        if (vehicleId == 1) {
            return 72;
        } else if (vehicleId == 2) {
            return 20;
        } else if (vehicleId == 3) {
            return 93;
        } else if (vehicleId == 4) {
            return 53;
        } else {
            return 0;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 5;
        } else if (vehicleId == 2) {
            return 7;
        } else if (vehicleId == 3) {
            return 68;
        } else if (vehicleId == 4) {
            return 50;
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
            return 10;
        } else if (vehicleId == 2) {
            return 10;
        } else if (vehicleId == 3) {
            return 30;
        } else if (vehicleId == 4) {
            return 15;
        } else if (vehicleId == 5) {
            return 34;
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
        } else if (vehicleId == 5) {
            return 37;
        } else {
            return 0;
        }
        
    } else if (directionId == 4) {
        if (directionId == 1) {
            return 20;
        } else if (vehicleId == 5) {
            return 20;
        } else {
            return 0;
        }
        
    } else if (directionId == 5) {
        if (vehicleId == 1) {
            return 83;
        } else if (vehicleId == 2) {
            return 12;
        } else if (vehicleId == 3) {
            return 68;
        } else if (vehicleId == 4) {
            return 34;
        } else {
            return 110;
        }
        
    } else if (directionId == 6) {
        if (vehicleId == 1) {
            return 17;
        } else if (vehicleId == 2) {
            return 5;
        } else if (vehicleId == 3) {
            return 24;
        } else if (vehicleId == 4) {
            return 18;
        } else {
            return 0;
        }
        
    } else {
        return 0;
    }
}





@end
