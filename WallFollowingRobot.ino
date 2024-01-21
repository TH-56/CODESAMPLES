// CREATOR: THOMAS HERNANDEZ.

// SERVO SETUP.
#include <Servo.h>
Servo left;
Servo right;

// DECLARING/INITIALIZING VARIABLES.
const int leftWhisker = 6;
const int rightWhisker = 9;
const int leftLED = 12;
const int rightLED = 2;
const int speaker = 3;
bool needsToFindWall = true;
bool wallOnLeft = false;
bool wallOnRight = false;

void setup() // SENSOR SECTION (ACTS ON ITS ENVIRONMENT, SENSORS BEING CONNECTED).
{
  left.attach(11); // ATTACHING SERVOS.
  right.attach(10); // ATTACHING SERVOS.
  needsToFindWall = true; // INITIALIZING BOOL.
  wallOnLeft = false; // INITIALIZING BOOL.
  wallOnRight = false; // INITIALIZING BOOL.
  pinMode(leftWhisker, INPUT); // SETS THE MODE OF THE SPECIFIED DIGITAL PIN (INPUT OR OUTPUT).
  pinMode(rightWhisker, INPUT); // SETS THE MODE OF THE SPECIFIED DIGITAL PIN (INPUT OR OUTPUT).
  pinMode(leftLED, OUTPUT); // SETS THE MODE OF THE SPECIFIED DIGITAL PIN (INPUT OR OUTPUT).
  pinMode(rightLED, OUTPUT); // SETS THE MODE OF THE SPECIFIED DIGITAL PIN (INPUT OR OUTPUT).
  pinMode(speaker, OUTPUT); // SETS THE MODE OF THE SPECIFIED DIGITAL PIN (INPUT OR OUTPUT).
  randomSeed(analogRead(A0)); // PREPPING FOR RANDOM NUMBER LATER ON.
  Serial.begin(9600);
}

// NOTE: WHISKERS ARE FLIPPED.

void loop() // MAIN LOOP.
{
  Serial.println(needsToFindWall);
  int left = digitalRead(leftWhisker); // SENSOR SECTION (ACTS ON ITS ENVIRONMENT, SENSORS BEING CONNECTED).
  int right = digitalRead(rightWhisker); // SENSOR SECTION (ACTS ON ITS ENVIRONMENT, SENSORS BEING CONNECTED).
  
  if(needsToFindWall == true) // IF THE ROBOT HASN'T FOUND ITS FIRST WALL YET.
  {
      if(left == 1 && right == 1) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: NEITHER WHISKER MAKES CONTACT).
      {
        digitalWrite(leftLED, LOW); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        digitalWrite(rightLED, LOW); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        noTone(speaker);
        driveServos(200, 200); // DRIVE FORWARDS.
      }
      if(left == 1 && right == 0) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: LEFT WHISKER MAKES CONTACT).
      {
        digitalWrite(leftLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 2000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        needsToFindWall = false; // SET BOOL TO FALSE.
        wallOnLeft = true; // SET BOOL TO TRUE.
        wallOnRight = false; // SET BOOL TO FALSE.
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(500); // ONE SECOND DELAY.
        driveServos(0, -200); // 90 DEGREE RIGHT TURN.
        delay(1000); // ONE SECOND DELAY.
        driveServos(0, 0); // STOP.
      }
      if(left == 0 && right == 1) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: RIGHT WHISKER MAKES CONTACT).
      {
        digitalWrite(rightLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 1000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        needsToFindWall = false; // SET BOOL TO FALSE.
        wallOnLeft = false; // SET BOOL TO FALSE.
        wallOnRight = true; // SET BOOL TO TRUE.
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(500); // ONE SECOND DELAY.
        driveServos(-200, 0); // 90 DEGREE LEFT TURN.
        delay(1000); // ONE SECOND DELAY.
        driveServos(0, 0); // STOP.
      }
      if(left == 0 && right == 0) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: BOTH WHISKERS MAKES CONTACT).
      {
        digitalWrite(leftLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        digitalWrite(rightLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 3000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        needsToFindWall = false; // SET BOOL TO FALSE.

        long randomNumber = random(1, 10); // RANDOM NUMBER GENERATOR.
        
        if(randomNumber <= 5) // 50/50 CHANCE.
        {
          wallOnLeft = true; // SET BOOL TO TRUE.
          wallOnRight = false; // SET BOOL TO FALSE.
          driveServos(-200, -200); // DRIVE BACKWARDS.
          delay(500); // ONE SECOND DELAY.
          driveServos(0, -200); // 90 DEGREE RIGHT TURN.
          delay(1000); // ONE SECOND DELAY.
          driveServos(0, 0); // STOP.
        }

        if(randomNumber >= 6) // 50/50 CHANCE.
        {
          wallOnLeft = false; // SET BOOL TO FALSE.
          wallOnRight = true; // SET BOOL TO TRUE.
          driveServos(-200, -200); // DRIVE BACKWARDS.
          delay(500); // ONE SECOND DELAY.
          driveServos(-200, 0); // 90 DEGREE LEFT TURN.
          delay(1000); // ONE SECOND DELAY.
          driveServos(0, 0); // STOP.
        }
      }
  }
  else
  {
    if(wallOnLeft == true) // IF THE WALL IS ON THE LEFT.
    {
      if(left == 1 && right == 1) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: NEITHER WHISKER MAKES CONTACT).
      {
        digitalWrite(leftLED, LOW); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        digitalWrite(rightLED, LOW); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        noTone(speaker);
        driveServos(75, 200);
      }
  
      if(left == 1 && right == 0) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: LEFT WHISKER MAKES CONTACT).
      {
        digitalWrite(leftLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 2000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(1000); // ONE SECOND DELAY.
        driveServos(200, 50); // TURN AWAY FROM WALL.
        delay(1500); // 1.5 SECOND DELAY.
        driveServos(0,0); // STOP.
      }
  
      if(left == 0 && right == 1) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: RIGHT WHISKER MAKES CONTACT).
      {
        digitalWrite(rightLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 1000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(-200, 0); // TURN AWAY FROM WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(500); // HALF SECOND DELAY.
        driveServos(0, -200); // TURN BACK TOWARDS WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(50, 200); // DRIVE FORWARDS.
        delay(1000); // ONE SECOND DELAY.
        driveServos(0, -200); // 90 DEGREE RIGHT TURN.
        delay(2000); // TWO SECOND DELAY.
        driveServos(0, 0); // STOP.
      }
  
      if(left == 0 && right == 0) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: BOTH WHISKERS MAKES CONTACT).
      {
        digitalWrite(leftLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        digitalWrite(rightLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 3000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(-200, 0); // TURN AWAY FROM WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(500); // HALF SECOND DELAY.
        driveServos(0, -200); // TURN BACK TOWARDS WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(50, 200); // DRIVE FORWARDS.
        delay(1000); // ONE SECOND DELAY.
        driveServos(0, -200); // 90 DEGREE RIGHT TURN.
        delay(2000); // TWO SECOND DELAY.
        driveServos(0, 0); // STOP.
      }
    }
    
    else // IF THE WALL IS ON THE RIGHT.
    {
      if(left == 1 && right == 1) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: NEITHER WHISKER MAKES CONTACT).
      {
        digitalWrite(leftLED, LOW); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        digitalWrite(rightLED, LOW); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        noTone(speaker); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(200, 75);
      }
   
      if(left == 0 && right == 1) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: RIGHT WHISKER MAKES CONTACT).
      {
        digitalWrite(rightLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 1000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(1000); // ONE SECOND DELAY.
        driveServos(50, 200); // TURN AWAY FROM WALL.
        delay(1500); // 1.5 SECOND DELAY.
        driveServos(0,0); // STOP.
      }

      if(left == 1 && right == 0) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: LEFT WHISKER MAKES CONTACT).
      {
        digitalWrite(leftLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 2000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(0, -200); // TURN AWAY FROM WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(500); // HALF SECOND DELAY.
        driveServos(-200, 0); // TURN BACK TOWARDS WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(200, 50); // DRIVE FORWARDS.
        delay(1000); // ONE SECOND DELAY.
        driveServos(-200, 0); // 90 DEGREE RIGHT TURN.
        delay(2000); // TWO SECOND DELAY.
        driveServos(0, 0); // STOP.
      }
  
      if(left == 0 && right == 0) // ACTION DETERMINATION (ACTS ON SENSES, MAKING A DECISION BASED ON SENSES: BOTH WHISKERS MAKES CONTACT).
      {
        digitalWrite(leftLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        digitalWrite(rightLED, HIGH); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET LED).
        tone(speaker, 3000); // PERFORM SPECIFIED ACTIONS (ACHIEVE A GOAL: SET SPEAKER).
        driveServos(0, -200); // TURN AWAY FROM WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(-200, -200); // DRIVE BACKWARDS.
        delay(500); // HALF SECOND DELAY.
        driveServos(-200, 0); // TURN BACK TOWARDS WALL.
        delay(1000); // ONE SECOND DELAY.
        driveServos(200, 50); // DRIVE FORWARDS.
        delay(1000); // ONE SECOND DELAY.
        driveServos(-200, 0); // 90 DEGREE RIGHT TURN.
        delay(2000); // TWO SECOND DELAY.
        driveServos(0, 0); // STOP.
      }
    }
  }
}

// FUNCTION GIVEN IN CLASS.
// 200 forwards, -200 backwards, 0 stopped.
void driveServos(int leftpw, int rightpw) 
{
  left.writeMicroseconds(1500 + leftpw);
  right.writeMicroseconds(1500 - rightpw);
}
