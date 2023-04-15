package by.bntu.fitr.core;

import by.bntu.fitr.core.facade.StartApplicationFacade;
import by.bntu.fitr.core.test.Test;


public class Main {

    public static void main(String[] args) {
        StartApplicationFacade startApplicationFacade = new StartApplicationFacade();
        startApplicationFacade.startUp();
        Test test = new Test();
        test.test();
        test.start();


    }
}
