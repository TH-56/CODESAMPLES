; -----------------------------------------------------------

; Creator: Thomas Hernandez.
; Purpose: Palindrome Problem. Another purpose is to showcase ability to translate code across languages (in this case, java to assembly language with the 8051).

; -----------------------------------------------------------

; Getting the length of the sequence:
MOV 0x20, #11 ; Getting length of sequence (user input). <<<<<
MOV A, 0x20 ; Getting (Input - 1).
DEC A ; Getting (Input - 1).
MOV R5, A ; R5 = Length - 1.

; Calculating the length of the sequence / 2.
MOV A, 0x20 ; ACC = Length.
MOV B, #0x02 ; B = 2.
DIV AB ; Length / 2.
MOV R4, A ; R4 = Result.
JZ ISPALINDROME

; Getting the sequence (user input): <<<<<
MOV 0x21, #0x01
MOV 0x22, #0x03
MOV 0x23, #0x05
MOV 0x24, #0x05
MOV 0x25, #0x03
MOV 0x26, #0x01
MOV 0x27, #0x01
MOV 0x28, #0x03
MOV 0x29, #0x05
MOV 0x2A, #0x05
MOV 0x2B, #0x03


; -----------------------------------------------------------

; Calculating the leftmost value:
MOV R0, #0x21 ; Memory location stored in R0.
MOV R6, 0x21 ; Actual value stored in R6.

; Calculating the rightmost value:
MOV R1, #0x21 ; Memory location stored in R1.
MOV A, R5 ; Leftmost + (Input - 1).
ADD A, R0 ; Leftmost + (Input - 1).
MOV R1, A ; Rightmost stored in R1.
MOV 0x18, @R1 ; Actual value stored in R7.
MOV R7, 0x18 ; Actual value stored in R7. 

; -----------------------------------------------------------

; SOME NOTES FOR READ-ABILITY:
; R0/R6 = LEFTMOST (LOCATION/VALUE).
; R1/R7 = RIGHTMOST (LOCATION/VALUE).
; R5 = LENGTH OF SEQUENCE.
; R4 = (LENGTH OF SEQUENCE) / 2.

; -----------------------------------------------------------

; The main loop for palindrome checking.
MAINLOOP: ; Loops R4 (Length / 2) Times.
MOV A, R7 ; ACC = Rightmost.
SUBB A, R6 ; Rightmost - Leftmost.

JNZ NOTPALINDROME ; If ACC != 0, the sequence is NOT a palindrome.

INC R0 ; Incrementing leftmost register and value.
MOV 0x17, @R0 ; Incrementing leftmost register and value.
MOV R6, 0x17 ; Incrementing leftmost register and value.

DEC R1 ; Decrementing rightmost register and value.
MOV 0x18, @R1 ; Decrementing rightmost register and value.
MOV R7, 0x18 ; Decrementing rightmost register and value.

DJNZ R4, MAINLOOP ; Loops R4 (Length / 2) Times.

; -----------------------------------------------------------

ISPALINDROME: ; When the sequence IS a palindrome.
MOV R0, #0x01 ; If the sequence IS a palindrome, then R0 = #0x01.
AJMP FINISH ; Jump to "program ending" point.

NOTPALINDROME: ; When the sequence IS NOT a palindrome.
MOV R0, #0x00 ; If the sequence IS NOT a palindrome, then R0 = #0x00.
AJMP FINISH ; Jump to "program ending" point.

FINISH: ; "Program ending" point.
END ; End the program.

; -----------------------------------------------------------

