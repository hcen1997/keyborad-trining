#   This program is mean to improve my skill in type 0-9,`-+ without seeing
# keyboard

# the view is very simple. it only have 2 line. head for info, body is for
# typing

# and get a fuking log in /var/log/${repName}/yyyy-mm-dd.log
# echo every line_info to log
import secrets
import os

keySet = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

total = 0
right = 0
current_target = 0


def First():
    clear()
    print("current_set=", keySet)
    print("press digit to start, alpha to exit. ", end="")
    input()


def printPractisInfo():
    global current_target
    current_target = secrets.choice(keySet)
    print({
        "try_type": current_target,
        "total": total,
        "score": 0.00 if total == 0 else (right / total) * 100,
    })


def info_head():
    printPractisInfo()

def handleInput():
    c: str = input()
    if c.isalpha():
        exit()
    return int(c)


def type_body():
    global right
    global total
    print("> ", end="")
    c = handleInput()
    total += 1
    if c == current_target:
        right += 1


def clear():
    os.system('clear')


def main():
    First()
    while True:
        info_head()
        type_body()
        clear()


if __name__ == '__main__':
    main()
