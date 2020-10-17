#   This program is mean to improve my skill in type 0-9,`-+ without seeing
# keyboard

# the view is very simple. it only have 2 line. head for info, body is for
# typing

# and get a fuking log in /var/log/${repName}/yyyy-mm-dd.log
# echoe
import secrets

keySet = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

first = True


def printFirst():
    print("current_set=", keySet)


def printPractisInfo():
    line = {
        "total":secrets.choice(keySet),
        "score":secrets.choice(keySet),
        "try_type":secrets.choice(keySet),
    }

    print(line)


def info_head():
    global first
    if not first:
        printPractisInfo()
    else:
        first = False
        printFirst()


def type_body():
    pass


while True:
    info_head()
    type_body()
