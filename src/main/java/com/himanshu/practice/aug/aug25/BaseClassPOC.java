package com.himanshu.practice.aug.aug25;

/**
 * Created by himanshubhardwaj on 25/08/19.
 */
public class BaseClassPOC {
    public static void main(String[] args) {
        ConcClass concClass = new ConcClass(1, 2, 3);
        System.out.println(concClass.add());
        System.out.println(((BaseClass) concClass).add());
        BaseClass baseClass = concClass;
        System.out.println(baseClass.add());

        //ConcClass concClass1 = (ConcClass) new BaseClass(1, 2);


        //System.out.println(concClass1.add());

        System.out.println(((ConcClass)baseClass).square() );
    }
}

class BaseClass {
    int a;
    int b;

    @java.beans.ConstructorProperties({"a", "b"})
    public BaseClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int add() {
        return a + b;
    }
}


class ConcClass extends BaseClass {
    int c;

    public ConcClass(int a, int b, int c) {
        super(a, b);
        this.c = c;
    }

    public int add() {
        return a + b + c;
    }

    public int square() {
        return a * a + b * b + c * c;
    }
}
