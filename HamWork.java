int x = 0;
int y = 0;
int z = 1;
int corn = 0;

void main() {
    //YOUR CODE GOES HERE
}

/* Directions in the HamWork are written as numbers
 * 1 -> Forward / Front         
 * 2 -> Right                   
 * 3 -> Backwards / Backwards   
 * 4 -> Left                    
 */

/////   // HAMWORK METHODS //   /////

//REQUIRED METHODS
void dev_turnRight() {
    linksUm();
    linksUm();
    linksUm();
}

void dev_turnAround() {
    linksUm();
    linksUm();
}

void dev_turnLeft() {
    linksUm();
}

//shortcuts
void go() {
	hamwork_go();
}
//

//HAMWORK REGION: hamwork_
//Regular
//Walks 1 tile
void hamwork_go() {
    vor();
    switch (z) {
        case 1:
            y++;
            break;
        case 2:
            x++;
            break;
        case 3:
            y--;
            break;
        case 4:
            x--;
            break;
    }
}
void hamwork_walk(int input) {
    for(int i = 0; i < input; i++) {
        hamwork_go();
    }
}
//HAMWORK REGION END//

//HAMWORK REGION: hamwork_next
//goes to the next wall
void hamwork_next() {
    while(vornFrei()) {
        hamwork_go();
    }
}
void hamwork_next_pickall() {
    hamwork_corn_get();
    while (vornFrei()) {
        hamwork_go();
        hamwork_corn_get();
    }
}
void hamwork_next_dropall() {
    hamwork_corn_drop();
    while(vornFrei()) {
        hamwork_go();
        hamwork_corn_drop();
    }
}
//HAMWORK REGION END//

//HAMWORK REGION: hamwork_corn
void hamwork_corn_get() {
    if (kornDa()) {
        corn++;
        nimm();
    }
}
void hamwork_corn_drop() {
    if (!maulLeer()) {
        corn--;
        gib();
    }
}
void hamwork_corn_dropall() {
    while (!maulLeer()) {
        corn--;
        gib();
    }
}
void hamwork_corn_getall() {
    while (kornDa()) {
        corn++;
        nimm();
    }
}
//_count
int hamwork_corn_count_tile() {
    int result = 0;
    while (kornDa()) {
        nimm();
        result++;
    }
    for(int i = 0;i < result; i++) {
        gib();
    }
    return result;
}
void hamwork_count_mouth() { //use variable corn for this instead, this will only readjust that variable
    int result = 0;
    while(!maulLeer()) {
        gib();
        result++;
    }
    for(int i = 0; i < result; i++) {
        nimm();
    }
    corn = result;
}
//HAMWORK REGION END//


//HAMWORK REGION: hamwork_relative
//_turn
//turns 1 to the left
void hamwork_relative_turn_left() {
    linksUm();

    if(z != 1) {
        z--;
    }
    else {
        z = 4;
    }
}
//turns 1 to the Right
void hamwork_relative_turn_right() {
    linksUm();
    linksUm();
    linksUm();

    if (z != 4)
    {
        z++;
    }
    else
    {
        z = 1;
    }
}
//turns around
void hamwork_relative_turn_back() {
    hamwork_relative_turn_left();
    hamwork_relative_turn_left();
}
//well.. it's always turned to it's front
void hamwork_relative_turn_front() {}
//_strafe
void hamwork_relative_strafe_left() {
    hamwork_relative_turn_left();
    hamwork_go();
    hamwork_relative_turn_right();
}
void hamwork_relative_strafe_right() {
    hamwork_relative_turn_right();
    hamwork_go();
    hamwork_relative_turn_left();
}
void hamwork_relative_strafe_back() {
    hamwork_relative_turn_back();
    hamwork_go();
    hamwork_relative_turn_back();
}
void hamwork_relative_strafe_front() { //the same as hamwork_go();
    hamwork_go();
}
//_check
//these return 1 for free and 0 for blocked
//checks the front
int hamwork_relative_check_front() {
    int result = 0;
    if(vornFrei()) {
        result = 1;
    }
    return result;
}
int hamwork_relative_check_right() {
    int result = 0;
    dev_turnRight();
    if(vornFrei()) {
        result = 1;
    }
    dev_turnLeft();
    return result;
}
int hamwork_relative_check_left() {
    int result = 0;
    dev_turnLeft();
    if (vornFrei()) {
        result = 1;
    }
    dev_turnRight();
    return result;
}
int hamwork_relative_check_back() {
    int result = 0;
    dev_turnAround();
    if (vornFrei()) {
        result = 1;
    }
    dev_turnAround();
    return result;
}
//HAMWORK REGION END//


//HAMWORK REGION: hamwork_direct
//_turn(direction)
void hamwork_direct_turn(int input) {
    while (input != z) {
        dev_turnLeft();
        if(z != 1) {
            z--;
        }
        else {
            z= 4;
        }
    }
}
//_strafe(direction)
void hamwork_direct_strafe(int input) {
    int tempSave = z;
    hamwork_direct_turn(input);
    hamwork_go();
    hamwork_direct_turn(tempSave);
}
//_check(direction)
int hamwork_direct_check(int input) {
    int tempSave = z;
    hamwork_direct_turn(input);
    int result = hamwork_relative_check_front();
    hamwork_direct_turn(tempSave);
    return result;
}
//HAMWORK REGION END//

//HAMWORK REGION: EXTENSIONS//
//Extensions are accessed via hamwork_x_<name> (x standing for extension, obviously)
//PUT ANY EXTENSIONS BETWEEN HERE

//AND HERE (Extension END)
//HAMWORK REGION END//

void ENDMARKER() {
    //PREVENTS 'FILE ENDED WHILE PARSING' ERROR/BUG
}
