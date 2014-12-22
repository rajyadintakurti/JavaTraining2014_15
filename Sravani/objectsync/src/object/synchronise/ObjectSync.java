package object.classlevel.synchronise;
class A {
    synchronized void test1() {
        for (int i = 0; i < 50; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    synchronized void test2() {
        for (int i = 100; i < 150; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
class B extends Thread {
    A a1;
    B(A a1) {
        this.a1=a1;
    }
    public void run() {
        a1.test1();
    }
}
class C extends Thread {
    A a1;
    C(A a1) {
        this.a1=a1;
    }
    public void run() {
        a1.test2();
    }
}
public class ObjectSync {
    public static void main(String[] args) {
        A a1=new A();
        //A a2=new A();
        B b1=new B(a1);
        C c1=new C(a1);
        b1.start();
        System.out.println("============");
        c1.start();
        try {
            b1.join();
            c1.join();
        }
        catch (InturruptedException e) {
            e.printStackTrace();
    }
}
