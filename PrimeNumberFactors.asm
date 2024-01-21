; ========================================

; Creator: Thomas Hernandez,
; Purpose: Prime Factors Problem.
; NOTE: The prime factors are stored at 0x70, 0x71, 0x72, etc.

; ========================================

MOV 0x70, #0
MOV 0x71, #0
MOV 0x72, #0
MOV 0x73, #0
MOV 0x74, #0
MOV 0x75, #0

MOV 0x10, #8 ; User input here (the number you want to check).
MOV SP, #0x3F ; Places the stack at location 0x40.
SJMP MAIN ; Jumps to the main function.

; ========================================

MAIN: ; The main function:

PUSH 0x10 ; Push the parameter onto the stack.
ACALL SUBR ; Calls the subroutine and pushes the return address onto the stack (LOWB, HIGHB).
POP 0x00 ; Pushes the result of the checker into R0.
MOV A, R0 ; Moves result into the ACC for checking.
JZ PRIMEFACTORS ; Jumps to check the prime factors when the number is not prime.
MOV 0x70, 0x10 ; Put the prime number into the proper memory location for display to user.
JNZ ENDPROG ; Jumps to the end of the program when the number is prime.

; ========================================

PRIMEFACTORS: ; Checks the prime factors.

MOV A, 0x10 ; Move user input into ACC.
MOV B, #1 ; Move 1 into the B register.
SUBB A, B ; (User input - 1).
MOV 0x50, A ; Store (user input - 1) in 0x50.
MOV 0x51, #1 ; Store 1 in 0x51 (starting point for loop).
MOV 0x52, #0x70 ; Pointer to where the prime factors will be stored.
MOV R1, #0x70 ; Pointer to where the prime factors will be stored.

LOOP: ; Main factor checking loop.

INC 0x51 ; Increment by 1 upon each iteration (starting at 1 as well).
MOV A, 0x10 ; Move user input into ACC.
MOV B, 0x51 ; Move the increment into the B register.
DIV AB ; (User input / Increment).
MOV A, B ; Move the remainder into the ACC.
JNZ ENDLOOP ; If not a factor, skip to the end of the loop.
PUSH 0x51 ; Push the parameter (the factor) onto the stack.
ACALL SUBR ; Calls the subroutine and pushes the return address onto the stack (LOWB, HIGHB).
POP 0x00 ; Pushes the result of the checker into R0.
MOV A, R0 ; Moves result into the ACC for checking.
JZ ENDLOOP ; Jumps to the end of the loop if the given factor is not prime.
MOV R1, 0x52 ; Sets R1 to the value of 0x52 (where to store the next prime factor).
MOV @R1, 0x51 ; Sets the given location to the given prime factor.
INC R1 ; Increments to update the memory pointer for the next iteration.
MOV 0x52, R1 ; Moves the pointer into another storage point.
MOV A, 0x10 ; Place user input into the ACC.
MOV B, 0x51 ; Place increment into the B register.
DIV AB ; (User input / increment).
MOV 0x25, A

DUPLICATES:

MOV A, 0x25 ; Reset the ACC.
MOV B, 0x51 ; Place increment back into the B register.
DIV AB ; (User input / increment).
MOV 0x25, A ; Move the ACC to another storage place to prevent overriding.
MOV A, B ; Move the B register into the ACC.

JNZ SKIP ; If there is a remainder, skip.

MOV R1, 0x52 ; Sets R1 to the value of 0x52 (where to store the next prime factor).
MOV @R1, 0x51 ; Sets the given location to the given prime factor.
INC R1 ; Increments to update the memory pointer for the next iteration.
MOV 0x52, R1 ; Moves the pointer into another storage point.

SKIP: ; Skip to here.

JZ DUPLICATES ; If there is a remainder, break the loop.

MOV 0x52, R1 ; Stores the memory pointer separately to avoid being overriden.

ENDLOOP: ; The end of the loop.

DJNZ 0x50, LOOP ; Loop again if 0x50 != 0.

; ========================================

ENDPROG: ; "End" of the program.

JMP ENDPROG ; Infinite loop.

; ========================================

MOV R1, 0x52 ; Sets R1 to the value of 0x52 (where to store the next prime factor).
MOV @R1, 0x51 ; Sets the given location to the given prime factor.
INC R1 ; Increments to update the memory pointer for the next iteration.
MOV 0x52, R1 ; Moves the pointer into another storage point.

SUBR: ; The subroutine:

POP 0x11 ; Pops the return address (HIGHB).
POP 0x12 ; Pops the return address (LOWB).
POP 0x13 ; Pops the parameter.

; ----------------------------------------

MOV R0, 0x13 ; R0 = NUMBER TO CHECK.
MOV R1, #3 ; STARTING VALUE FOR R1.
MOV R5, #2 ; R5 = 2.

MOV A, R0 ; Checking if the number to check is 1.
MOV B, #1 ; Checking if the number to check is 1.
SUBB A, B ; Checking if the number to check is 1.
JZ ISPRIME ; If the number that we are checking is 1, it's prime.

MOV A, R0 ; Checking if the number to check is 2.
MOV B, #2 ; Checking if the number to check is 2.
SUBB A, B ; Checking if the number to check is 2.
JZ ISPRIME ; If the number that we are checking is 2, it's prime.

MOV ACC, R0 ; PUT R0 INTO THE ACC.
SUBB A, R5 ; ACC - 2.
MOV R6, ACC ; NUMBER OF ITERATIONS.
MOV ACC, R0 ; SET THE ACC TO R0.
MOV R7, #1 ; R7 = 1.

REPEAT: ; START OF LOOP.
INC R7 ; R5++.
MOV B, R7 ; B = R5.
MOV A, R0 ; RESETTING THE ACC (A).
DIV AB ; A / B.
MOV A, B ; MAKE A = B.
JZ ISNOTPRIME ; IF B == 0.
DJNZ R6, REPEAT ; LOOP R6 TIMES.

JNZ ISPRIME ; IF THE # IS PRIME.

ISPRIME: ; IF THE # IS PRIME.

MOV 0x20, #0x01
PUSH 0x20
SJMP ENDREPEAT

ISNOTPRIME: ; IF THE # IS NOT PRIME.

MOV 0x20, #0x00
PUSH 0x20
SJMP ENDREPEAT

; ----------------------------------------

ENDREPEAT: ; After the loop:

PUSH 0x12 ; Pushes the return address onto the stack (LOWB).
PUSH 0x11 ; Pushes the return address onto the stack (HIGHB).
RET ; Returns.

; ========================================
