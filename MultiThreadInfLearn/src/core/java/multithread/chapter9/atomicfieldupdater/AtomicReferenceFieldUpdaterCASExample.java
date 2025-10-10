package core.java.multithread.chapter9.atomicfieldupdater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicReferenceFieldUpdaterCASExample {
    private static AtomicReferenceFieldUpdater<AtomicReferenceFieldUpdaterCASExample, String> messageUpdater =
            AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterCASExample.class, String.class, "message");

    private volatile String message = "";

    public void doSomething() {
        if (messageUpdater.compareAndSet(this, "", "Hello World!")) {
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
            messageUpdater.set(this, "");
        } else {
            System.out.println("This thread will not enter the loop");
        }
    }

    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterCASExample example1 = new AtomicReferenceFieldUpdaterCASExample();
        new Thread(example1::doSomething).start();
        new Thread(example1::doSomething).start();
    }
}
