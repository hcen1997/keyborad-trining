#   This program is mean to improve my skill in type 0-9,`-+ without seeing
# keyboard with time limit

# the view is very simple. it only have 2 line. head for info, body is for
# typing

# and get a fuking log in /var/log/${repName}/yyyy-mm-dd.log
# echo every line_info to log
import secrets
import os, sys
import time
from multiprocessing import Process

keySet = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
timeLimit = 1.5

total = 0
right = 0
current_target = -1
current_typing = -1

#  use Signal like a shared memory
isTiming = False


def check():
    global current_target
    global current_typing
    global isTiming

    isTiming = True
    print("check",isTiming)
    time.sleep(timeLimit)
    isTiming = False
    print("check",isTiming)

    if current_typing == current_target:
        return

    print("you lose for now", end="")
    return


def First():
    clear(None)
    print("current_set=", keySet)
    print("press digit to start, alpha to exit. ", end="")
    input()


def info_head():
    global current_target
    global current_typing
    current_target = secrets.choice(keySet)
    print({
        "try_type": current_target,
        "total": total,
        "score": 0.00 if total == 0 else round((right / total) * 100),
    })


def handleInput():
    c = input()
    if c.isalpha():
        exit()
    #  c will be "" if timeout and no one try
    return -1 if c == "" else int(c)


def type_body():
    global right
    global total
    global current_target
    global current_typing
    global isTiming
    # use for condition but no change
    # global isTimeOut
    # global isTiming
    print("> ", end="")

    # here timing is start because computer is so fast
    current_typing = handleInput()
    print("type_body",current_target,current_typing,isTiming)
    # find bug  isTiming always false
    if isTiming:
        if current_typing == current_target:
            right += 1
    total += 1
    current_typing = -1
    input()



def clear(t: Process):
    if t is not None:
        t.terminate()
    os.system('clear')


def main():
    info_head()
    t = Process(target=check)
    t.start()
    type_body()
    clear(t)
    print(current_typing)


if __name__ == '__main__':
    First()
    while True:
        main()
