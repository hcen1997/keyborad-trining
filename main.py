#   This program is mean to improve my skill in type 0-9,`-+ without seeing
# keyboard with time limit

# the view is very simple. it only have 2 line. head for info, body is for
# typing

# and get a fuking log in /var/log/${repName}/yyyy-mm-dd.log
# echo every line_info to log
import secrets
import os , sys
import time
from multiprocessing import Process

keySet = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
timeLimit = 1.5

total = 0
right = 0
easterEgg = -1938475062
current_target = easterEgg
current_typing = easterEgg


def check():
    time.sleep(timeLimit)
    if current_typing == current_target:
        return
    print("you lose for now", end="")
    return easterEgg


def First():
    clear(None)
    print("current_set=", keySet, " ", easterEgg)
    print("press digit to start, alpha to exit. ", end="")
    input()





def info_head():
    global current_target
    current_target = secrets.choice(keySet)
    print({
        "try_type": current_target,
        "total": total,
        "score": 0.00 if total == 0 else round ((right / total) * 100),
    })

def handleInput():
    c: str = input()
    if c.isalpha():
        exit()
    #  bad fix condition "" for lose check,
    #  i think the fine solution is class and signal
    #  OR if i can
    if c == "":
        return easterEgg
    return int(c)


def type_body():
    global right
    global total
    global current_target
    global current_typing
    print("> ", end="")
    current_typing = handleInput()
    if current_typing == current_target:
        right += 1
    total += 1
    current_typing = easterEgg


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


if __name__ == '__main__':
    First()
    while True:
        main()
