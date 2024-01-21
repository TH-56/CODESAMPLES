# ----------------------------------------------------------------------------------------------------------------------

# Final Project.
# Creator: Thomas Hernandez.
# Start Date of Creation: 11/2/2020 (Began the project).
# End of Creation: 11/18/2020 (Finished with working code).
# Purpose: To explore creating games with Python while also completing the final project assignment!

# ----------------------------------------------------------------------------------------------------------------------

# Resources used to complete project:
# 1. graphics.py reference - https://mcsp.wartburg.edu/zelle/python/graphics/graphics.pdf
# 2. button.py reference - https://mcsp.wartburg.edu/zelle/python/ppics1/code/chapter11/button.py
# 3. time.py reference (sleep() function) - https://www.programiz.com/python-programming/time/sleep

# ----------------------------------------------------------------------------------------------------------------------

# Importing various libraries.
from graphics import *
from random import *
from time import *

# ----------------------------------------------------------------------------------------------------------------------

# Setting the global value for wins and losses used for the future scoreboard in the game screen.
wins = 0
losses = 0

# ----------------------------------------------------------------------------------------------------------------------

# Creating and naming the main window along with a grey background.
window = GraphWin("HIGHROLLER", 800, 800)
window.setBackground("grey")

# ----------------------------------------------------------------------------------------------------------------------

# Defining a function that will create my title screen.
def titlescreen():

    # Displaying the title screen assets.
    title = Text(Point(400, 100), "HIGHROLLER")
    title.setSize(32)
    title.setFace("helvetica")
    title.setStyle("bold italic")
    title.setTextColor("black")
    title.draw(window)

    subtitle = Text(Point(400, 135), "A SIMPLE DICE GAMBLING SIMULATOR")
    subtitle.setSize(10)
    subtitle.setFace("helvetica")
    subtitle.setStyle("bold")
    subtitle.setTextColor("black")
    subtitle.draw(window)

    subsubtitle = Text(Point(400, 155), "Creator: Thomas Hernandez")
    subsubtitle.setSize(8)
    subsubtitle.setFace("helvetica")
    subsubtitle.setStyle("normal")
    subsubtitle.setTextColor("black")
    subsubtitle.draw(window)

    topline = Line(Point(50, 250), Point(750, 250))
    topline.setFill("black")
    topline.draw(window)

    bottomline = Line(Point(50, 550), Point(750, 550))
    bottomline.setFill("black")
    bottomline.draw(window)

    tiptitle = Text(Point(400, 650), "TIP")
    tiptitle.setSize(16)
    tiptitle.setFace("helvetica")
    tiptitle.setStyle("bold")
    tiptitle.setTextColor("black")
    tiptitle.draw(window)

    tiptitlelinetop = Line(Point(350, 630), Point(450, 630))
    tiptitlelinetop.setFill("black")
    tiptitlelinetop.draw(window)

    tiptitlelinebottom = Line(Point(350, 670), Point(450, 670))
    tiptitlelinebottom.setFill("black")
    tiptitlelinebottom.draw(window)

    tipslist = ["Keep going!", "Luck is on your side!",
                "Guess the result of a dice roll to win!", "Try to predict the dice roll!",
                "Become one with the dice!", "Don't get discouraged!", "You can do it!",
                "Keep trying!", "Don't give up!", "I believe in you!", "Try again!",
                "You got it this next try!", "Good luck!", "No cheating!"]

    shuffle(tipslist)
    tipdisplay = Text(Point(400, 700), tipslist[0])
    tipdisplay.setSize(10)
    tipdisplay.setFace("helvetica")
    tipdisplay.setStyle("normal")
    tipdisplay.setTextColor("black")
    tipdisplay.draw(window)

# ----------------------------------------------------------------------------------------------------------------------

# Defining the main game function.
def game(dicevalue, diceresult, winsdisplay, lossesdisplay):

    global wins
    global losses

    if diceresult == dicevalue:

        wins = wins + 1
        winsdisplay.draw(window)

    else:

        losses = losses + 1
        lossesdisplay.draw(window)

    winstextvalue = Text(Point(90, 750), wins, )
    winstextvalue.setSize(12)
    winstextvalue.setFace("helvetica")
    winstextvalue.setStyle("bold")
    winstextvalue.setTextColor("black")
    winstextvalue.draw(window)
    winstextdisplay = Text(Point(60, 750), 'WINS:')
    winstextdisplay.setSize(12)
    winstextdisplay.setFace("helvetica")
    winstextdisplay.setStyle("bold")
    winstextdisplay.setTextColor("black")
    winstextdisplay.draw(window)

    lossestextvalue = Text(Point(115, 730), losses, )
    lossestextvalue.setSize(12)
    lossestextvalue.setFace("helvetica")
    lossestextvalue.setStyle("bold")
    lossestextvalue.setTextColor("black")
    lossestextvalue.draw(window)
    lossestextdisplay = Text(Point(72, 730), 'LOSSES:')
    lossestextdisplay.setSize(12)
    lossestextdisplay.setFace("helvetica")
    lossestextdisplay.setStyle("bold")
    lossestextdisplay.setTextColor("black")
    lossestextdisplay.draw(window)

    winlossratio = wins, "/", losses
    winlosstextvalue = Text(Point(192, 710), winlossratio, )
    winlosstextvalue.setSize(12)
    winlosstextvalue.setFace("helvetica")
    winlosstextvalue.setStyle("bold")
    winlosstextvalue.setTextColor("black")
    winlosstextvalue.draw(window)
    winlosstextdisplay = Text(Point(105, 710), 'WIN/LOSS RATIO:')
    winlosstextdisplay.setSize(12)
    winlosstextdisplay.setFace("helvetica")
    winlosstextdisplay.setStyle("bold")
    winlosstextdisplay.setTextColor("black")
    winlosstextdisplay.draw(window)

    progresstext = Text(Point(610, 750), 'If you return to the main menu via the back button\n '
                                         'all progress will be saved (wins, losses, etc.)')
    progresstext.setSize(10)
    progresstext.setFace("helvetica")
    progresstext.setStyle("bold")
    progresstext.setTextColor("black")
    progresstext.draw(window)

    # Creating "if" statements that display the simulated dice.
    if diceresult == 1:

        diceresult1base = Rectangle(Point(625, 275), Point(725, 375))
        diceresult1base.setFill("white")
        dice1resultcircle = Circle(Point(675, 325), 10)
        dice1resultcircle.setFill("black")
        diceresult1base.draw(window)
        dice1resultcircle.draw(window)

        dice1resulttext = Text(Point(675, 400), 'THE ACTUAL RESULT')
        dice1resulttext.setSize(12)
        dice1resulttext.setFace("helvetica")
        dice1resulttext.setStyle("bold")
        dice1resulttext.setTextColor("black")
        dice1resulttext.draw(window)

    if diceresult == 2:

        diceresult2base = Rectangle(Point(625, 275), Point(725, 375))
        diceresult2base.setFill("white")
        dice2resultcirclea = Circle(Point(650, 350), 10)
        dice2resultcirclea.setFill("black")
        dice2resultcircleb = Circle(Point(700, 300), 10)
        dice2resultcircleb.setFill("black")
        diceresult2base.draw(window)
        dice2resultcirclea.draw(window)
        dice2resultcircleb.draw(window)

        dice2resulttext = Text(Point(675, 400), 'THE ACTUAL RESULT')
        dice2resulttext.setSize(12)
        dice2resulttext.setFace("helvetica")
        dice2resulttext.setStyle("bold")
        dice2resulttext.setTextColor("black")
        dice2resulttext.draw(window)

    if diceresult == 3:

        diceresult3base = Rectangle(Point(625, 275), Point(725, 375))
        diceresult3base.setFill("white")
        dice3resultcirclea = Circle(Point(650, 350), 10)
        dice3resultcirclea.setFill("black")
        dice3resultcircleb = Circle(Point(700, 300), 10)
        dice3resultcircleb.setFill("black")
        dice3resultcirclec = Circle(Point(675, 325), 10)
        dice3resultcirclec.setFill("black")
        diceresult3base.draw(window)
        dice3resultcirclea.draw(window)
        dice3resultcircleb.draw(window)
        dice3resultcirclec.draw(window)

        dice3resulttext = Text(Point(675, 400), 'THE ACTUAL RESULT')
        dice3resulttext.setSize(12)
        dice3resulttext.setFace("helvetica")
        dice3resulttext.setStyle("bold")
        dice3resulttext.setTextColor("black")
        dice3resulttext.draw(window)

    if diceresult == 4:

        diceresult4base = Rectangle(Point(625, 275), Point(725, 375))
        diceresult4base.setFill("white")
        dice4resultcirclea = Circle(Point(650, 350), 10)
        dice4resultcirclea.setFill("black")
        dice4resultcircleb = Circle(Point(650, 300), 10)
        dice4resultcircleb.setFill("black")
        dice4resultcirclec = Circle(Point(700, 350), 10)
        dice4resultcirclec.setFill("black")
        dice4resultcircled = Circle(Point(700, 300), 10)
        dice4resultcircled.setFill("black")
        diceresult4base.draw(window)
        dice4resultcirclea.draw(window)
        dice4resultcircleb.draw(window)
        dice4resultcirclec.draw(window)
        dice4resultcircled.draw(window)

        dice4resulttext = Text(Point(675, 400), 'THE ACTUAL RESULT')
        dice4resulttext.setSize(12)
        dice4resulttext.setFace("helvetica")
        dice4resulttext.setStyle("bold")
        dice4resulttext.setTextColor("black")
        dice4resulttext.draw(window)

    if diceresult == 5:

        diceresult5base = Rectangle(Point(625, 275), Point(725, 375))
        diceresult5base.setFill("white")
        dice5resultcirclea = Circle(Point(650, 350), 10)
        dice5resultcirclea.setFill("black")
        dice5resultcircleb = Circle(Point(650, 300), 10)
        dice5resultcircleb.setFill("black")
        dice5resultcirclec = Circle(Point(700, 350), 10)
        dice5resultcirclec.setFill("black")
        dice5resultcircled = Circle(Point(700, 300), 10)
        dice5resultcircled.setFill("black")
        dice5resultcirclee = Circle(Point(675, 325), 10)
        dice5resultcirclee.setFill("black")
        diceresult5base.draw(window)
        dice5resultcirclea.draw(window)
        dice5resultcircleb.draw(window)
        dice5resultcirclec.draw(window)
        dice5resultcircled.draw(window)
        dice5resultcirclee.draw(window)

        dice5resulttext = Text(Point(675, 400), 'THE ACTUAL RESULT')
        dice5resulttext.setSize(12)
        dice5resulttext.setFace("helvetica")
        dice5resulttext.setStyle("bold")
        dice5resulttext.setTextColor("black")
        dice5resulttext.draw(window)

    if diceresult == 6:

        diceresult6base = Rectangle(Point(625, 275), Point(725, 375))
        diceresult6base.setFill("white")
        dice6resultcirclea = Circle(Point(650, 350), 10)
        dice6resultcirclea.setFill("black")
        dice6resultcircleb = Circle(Point(650, 300), 10)
        dice6resultcircleb.setFill("black")
        dice6resultcirclec = Circle(Point(700, 350), 10)
        dice6resultcirclec.setFill("black")
        dice6resultcircled = Circle(Point(700, 300), 10)
        dice6resultcircled.setFill("black")
        dice6resultcirclee = Circle(Point(650, 325), 10)
        dice6resultcirclee.setFill("black")
        dice6resultcirclef = Circle(Point(700, 325), 10)
        dice6resultcirclef.setFill("black")
        diceresult6base.draw(window)
        dice6resultcirclea.draw(window)
        dice6resultcircleb.draw(window)
        dice6resultcirclec.draw(window)
        dice6resultcircled.draw(window)
        dice6resultcirclee.draw(window)
        dice6resultcirclef.draw(window)

        dice6resulttext = Text(Point(675, 400), 'THE ACTUAL RESULT')
        dice6resulttext.setSize(12)
        dice6resulttext.setFace("helvetica")
        dice6resulttext.setStyle("bold")
        dice6resulttext.setTextColor("black")
        dice6resulttext.draw(window)

# ----------------------------------------------------------------------------------------------------------------------

# Defining a function that reloads the game screen after a few seconds.
def gamereload():

    sleep(1)
    reloadtext1 = Text(Point(400, 225), 'Reloading.')
    reloadtext1.setSize(10)
    reloadtext1.setFace("helvetica")
    reloadtext1.setStyle("bold")
    reloadtext1.setTextColor("black")
    reloadtext1.draw(window)

    sleep(1)
    reloadtext1.undraw()
    reloadtext2 = Text(Point(400, 225), 'Reloading..')
    reloadtext2.setSize(10)
    reloadtext2.setFace("helvetica")
    reloadtext2.setStyle("bold")
    reloadtext2.setTextColor("black")
    reloadtext2.draw(window)

    sleep(1)
    reloadtext2.undraw()
    reloadtext3 = Text(Point(400, 225), 'Reloading...')
    reloadtext3.setSize(10)
    reloadtext3.setFace("helvetica")
    reloadtext3.setStyle("bold")
    reloadtext3.setTextColor("black")
    reloadtext3.draw(window)

    sleep(1)
    gamescreen()

# ----------------------------------------------------------------------------------------------------------------------

# Defining a function that creates my in-game screen.
def gamescreen():

    # Drawing the game screen assets.
    gamescreenborder = Rectangle(Point(0, 0), Point(800, 800))
    gamescreenborder.setFill("grey")
    gamescreenborder.draw(window)

    gamescreenbg = Rectangle(Point(25, 25), Point(775, 775))
    gamescreenbg.setFill("darkgreen")
    gamescreenbg.draw(window)

    gamescreentitle = Text(Point(400, 100), "HIGHROLLER")
    gamescreentitle.setSize(32)
    gamescreentitle.setFace("helvetica")
    gamescreentitle.setStyle("bold italic")
    gamescreentitle.setTextColor("black")
    gamescreentitle.draw(window)

    gamescreensub = Text(Point(400, 135), "GAME TABLE")
    gamescreensub.setSize(10)
    gamescreensub.setFace("helvetica")
    gamescreensub.setStyle("bold")
    gamescreensub.setTextColor("black")
    gamescreensub.draw(window)

    guesstext = Text(Point(400, 350), "MAKE YOUR GUESS")
    guesstext.setSize(16)
    guesstext.setFace("helvetica")
    guesstext.setStyle("bold")
    guesstext.setTextColor("black")
    guesstext.draw(window)

    guessboxtop = Line(Point(225, 250), Point(575, 250))
    guessboxtop.draw(window)
    guessboxleft = Line(Point(225, 250), Point(225, 450))
    guessboxleft.draw(window)
    guessboxbottom = Line(Point(225, 450), Point(575, 450))
    guessboxbottom.draw(window)
    guessboxright = Line(Point(575, 250), Point(575, 450))
    guessboxright.draw(window)

    guesslinetop = Line(Point(50, 200), Point(750, 200))
    guesslinetop.draw(window)

    guesslinebottom = Line(Point(50, 500), Point(750, 500))
    guesslinebottom.draw(window)

    winsdisplay = Text(Point(400, 475), 'YOU WON!')
    winsdisplay.setSize(10)
    winsdisplay.setFace("helvetica")
    winsdisplay.setStyle("bold")
    winsdisplay.setTextColor("black")

    lossesdisplay = Text(Point(400, 475), 'YOU LOST.')
    lossesdisplay.setSize(10)
    lossesdisplay.setFace("helvetica")
    lossesdisplay.setStyle("bold")
    lossesdisplay.setTextColor("black")

    # Creating the main game buttons.
    gamebuttons1 = [(400, 600, 'BACK'), (300, 300, 'ONE'), (400, 300, 'TWO'), (500, 300, 'THREE'),
                    (300, 400, 'FOUR'), (400, 400, 'FIVE'), (500, 400, 'SIX')]
    gamebuttons2 = []

    for cx, cy, label in gamebuttons1:

        gamebuttons2.append(Button(window, Point(cx, cy), 100, 50, label))
        gamebuttons2[0 - 1].activate()

    point = window.getMouse()
    diceresult = randrange(1, 7)
    dicevalue = 0

    # Actions associated with the "back" button.
    if gamebuttons2[0].clicked(point):

        titlescreenbg = Rectangle(Point(0, 0), Point(800, 800))
        titlescreenbg.setFill("grey")
        titlescreenbg.draw(window)
        titlescreen()
        mainbuttons()

    # Actions associated with the "one" button.
    if gamebuttons2[1].clicked(point):

        dice1base = Rectangle(Point(75, 275), Point(175, 375))
        dice1base.setFill("white")
        dice1circle = Circle(Point(125, 325), 10)
        dice1circle.setFill("black")
        dice1base.draw(window)
        dice1circle.draw(window)

        dicevalue = 1

        dice1text = Text(Point(125, 400), 'YOUR GUESS')
        dice1text.setSize(12)
        dice1text.setFace("helvetica")
        dice1text.setStyle("bold")
        dice1text.setTextColor("black")
        dice1text.draw(window)

    # Actions associated with the "two" button.
    if gamebuttons2[2].clicked(point):

        dice2base = Rectangle(Point(75, 275), Point(175, 375))
        dice2base.setFill("white")
        dice2circlea = Circle(Point(100, 350), 10)
        dice2circleb = Circle(Point(150, 300), 10)
        dice2circlea.setFill("black")
        dice2circleb.setFill("black")
        dice2base.draw(window)
        dice2circlea.draw(window)
        dice2circleb.draw(window)

        dicevalue = 2

        dice2text = Text(Point(125, 400), 'YOUR GUESS')
        dice2text.setSize(12)
        dice2text.setFace("helvetica")
        dice2text.setStyle("bold")
        dice2text.setTextColor("black")
        dice2text.draw(window)

    # Actions associated with the "three" button.
    if gamebuttons2[3].clicked(point):

        dice3base = Rectangle(Point(75, 275), Point(175, 375))
        dice3base.setFill("white")
        dice3circlea = Circle(Point(100, 350), 10)
        dice3circleb = Circle(Point(150, 300), 10)
        dice3circlec = Circle(Point(125, 325), 10)
        dice3circlea.setFill("black")
        dice3circleb.setFill("black")
        dice3circlec.setFill("black")
        dice3base.draw(window)
        dice3circlea.draw(window)
        dice3circleb.draw(window)
        dice3circlec.draw(window)

        dicevalue = 3

        dice3text = Text(Point(125, 400), 'YOUR GUESS')
        dice3text.setSize(12)
        dice3text.setFace("helvetica")
        dice3text.setStyle("bold")
        dice3text.setTextColor("black")
        dice3text.draw(window)

    # Actions associated with the "four" button.
    if gamebuttons2[4].clicked(point):

        dice4base = Rectangle(Point(75, 275), Point(175, 375))
        dice4base.setFill("white")
        dice4circlea = Circle(Point(100, 300), 10)
        dice4circleb = Circle(Point(100, 350), 10)
        dice4circlec = Circle(Point(150, 300), 10)
        dice4circled = Circle(Point(150, 350), 10)
        dice4circlea.setFill("black")
        dice4circleb.setFill("black")
        dice4circlec.setFill("black")
        dice4circled.setFill("black")
        dice4base.draw(window)
        dice4circlea.draw(window)
        dice4circleb.draw(window)
        dice4circlec.draw(window)
        dice4circled.draw(window)

        dicevalue = 4

        dice4text = Text(Point(125, 400), 'YOUR GUESS')
        dice4text.setSize(12)
        dice4text.setFace("helvetica")
        dice4text.setStyle("bold")
        dice4text.setTextColor("black")
        dice4text.draw(window)

    # Actions associated with the "five" button.
    if gamebuttons2[5].clicked(point):

        dice5base = Rectangle(Point(75, 275), Point(175, 375))
        dice5base.setFill("white")
        dice5circlea = Circle(Point(100, 300), 10)
        dice5circleb = Circle(Point(100, 350), 10)
        dice5circlec = Circle(Point(150, 300), 10)
        dice5circled = Circle(Point(150, 350), 10)
        dice5circlee = Circle(Point(125, 325), 10)
        dice5circlea.setFill("black")
        dice5circleb.setFill("black")
        dice5circlec.setFill("black")
        dice5circled.setFill("black")
        dice5circlee.setFill("black")
        dice5base.draw(window)
        dice5circlea.draw(window)
        dice5circleb.draw(window)
        dice5circlec.draw(window)
        dice5circled.draw(window)
        dice5circlee.draw(window)

        dicevalue = 5

        dice5text = Text(Point(125, 400), 'YOUR GUESS')
        dice5text.setSize(12)
        dice5text.setFace("helvetica")
        dice5text.setStyle("bold")
        dice5text.setTextColor("black")
        dice5text.draw(window)

    # Actions associated with the "six" button.
    if gamebuttons2[6].clicked(point):

        dice6base = Rectangle(Point(75, 275), Point(175, 375))
        dice6base.setFill("white")
        dice6circlea = Circle(Point(100, 300), 10)
        dice6circleb = Circle(Point(100, 350), 10)
        dice6circlec = Circle(Point(150, 300), 10)
        dice6circled = Circle(Point(150, 350), 10)
        dice6circlee = Circle(Point(150, 325), 10)
        dice6circlef = Circle(Point(100, 325), 10)
        dice6circlea.setFill("black")
        dice6circleb.setFill("black")
        dice6circlec.setFill("black")
        dice6circled.setFill("black")
        dice6circlee.setFill("black")
        dice6circlef.setFill("black")
        dice6base.draw(window)
        dice6circlea.draw(window)
        dice6circleb.draw(window)
        dice6circlec.draw(window)
        dice6circled.draw(window)
        dice6circlee.draw(window)
        dice6circlef.draw(window)

        dicevalue = 6

        dice6text = Text(Point(125, 400), 'YOUR GUESS')
        dice6text.setSize(12)
        dice6text.setFace("helvetica")
        dice6text.setStyle("bold")
        dice6text.setTextColor("black")
        dice6text.draw(window)

    game(dicevalue, diceresult, winsdisplay, lossesdisplay)

    gamereload()

    window.getMouse()

# ----------------------------------------------------------------------------------------------------------------------

# Defining the main button function.
def mainbuttons():

    # Creating the play and rules buttons.
    mainbuttons1 = [(200, 400, 'PLAY'), (600, 400, 'RULES')]
    mainbuttons2 = []

    for cx, cy, label in mainbuttons1:
        mainbuttons2.append(Button(window, Point(cx, cy), 200, 200, label))
        mainbuttons2[0-1].activate()

    point = window.getMouse()

    # Actions associated with the play button.
    if mainbuttons2[0].clicked(point):

        gamescreen()

    # Actions associated with the rules button.
    if mainbuttons2[1].clicked(point):

        rulescreenbg = Rectangle(Point(0, 0), Point(800, 800))
        rulescreenbg.setFill("grey")
        rulescreenbg.draw(window)

        rulestitle = Text(Point(400, 100), "HIGHROLLER")
        rulestitle.setSize(32)
        rulestitle.setFace("helvetica")
        rulestitle.setStyle("bold italic")
        rulestitle.setTextColor("black")
        rulestitle.draw(window)

        subrules = Text(Point(400, 135), "THE RULES OF THE GAME")
        subrules.setSize(10)
        subrules.setFace("helvetica")
        subrules.setStyle("bold")
        subrules.setTextColor("black")
        subrules.draw(window)

        rules = Text(Point(400, 350), "1. Start by making a guess as to what the simulated dice roll will land on "
                                      "(between one and six).\n\n2. Once you have made your guess, the simulated "
                                      "dice will be rolled.\n\n3. If your guess was correct and matched the simulated "
                                      "dice, you win!\n\n4. If your guess was incorrect and did not match the "
                                      "simulated dice, you lose.\n\n5. Upon each win, you will be awarded one point "
                                      "towards your win category.\n\n6. Upon each loss, you will be awarded one "
                                      "point towards your loss category.\n\n7. The game will automatically reload a "
                                      "few seconds after the simulated dice is rolled.")

        rules.setSize(11)
        rules.setFace("helvetica")
        rules.setStyle("bold")
        rules.setTextColor("black")
        rules.draw(window)

        rulestopline = Line(Point(50, 200), Point(750, 200))
        rulestopline.draw(window)

        rulesbottomline = Line(Point(50, 500), Point(750, 500))
        rulesbottomline.draw(window)

        rulesbackbutton1 = [(400, 600, 'BACK')]
        rulesbackbutton2 = []

        for cx, cy, label in rulesbackbutton1:

            rulesbackbutton2.append(Button(window, Point(cx, cy), 100, 50, label))
            rulesbackbutton2[0].activate()

        point = window.getMouse()

        if rulesbackbutton2[0].clicked(point):

            titlescreenbg = Rectangle(Point(0, 0), Point(800, 800))
            titlescreenbg.setFill("grey")
            titlescreenbg.draw(window)
            titlescreen()
            mainbuttons()

# ----------------------------------------------------------------------------------------------------------------------

# Defining the main program function.
def main():

    titlescreen()

    mainbuttons()

# ----------------------------------------------------------------------------------------------------------------------

# Invoking the main function in order to start the process surrounding the game.
main()

# ----------------------------------------------------------------------------------------------------------------------
