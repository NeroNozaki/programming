a1 = [0, 5, 2, -1]
b1 = 6
a2 = [0, 3, 6, -2]
b2 = 9
a3 = [0, 1, -1, 3]
b3 = 8


def compute():
    err = 0.0001
    i = 0;
    x = 1
    y = 1
    z = 1
    while True:
        xn = (b1 - (a1[2]*y) - (a1[3]*z)) / a1[1]
        yn = (b2 -(a2[1]*xn) - (a2[3]*z)) / a2[2]
        zn = (b3 - (a3[1]*xn) - (a3[2]*yn)) / a3[3]
        i+=1;

        if (abs(xn-x) < err and abs(yn-y) < err and abs(zn-z) < err):
            break
        else:
            x = xn
            y = yn
            z = zn

    print("x = ", x)
    print("y = ", y)
    print("z = ", z)
    print("iterations = ", i)
    return

compute()
