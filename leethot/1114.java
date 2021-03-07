class Foo {
    public Semaphore seam1 = new Semaphore(0);  
    public Semaphore seam2 = new Semaphore(0);

    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        
        printFirst.run();
        seam1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        seam1.acquire();
        printSecond.run();
        seam2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        seam2.acquire();
        printThird.run();
    }
}