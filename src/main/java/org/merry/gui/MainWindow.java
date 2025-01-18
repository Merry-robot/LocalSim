package org.merry.gui;

import org.merry.simulation.CreateGAAircraft;

public class MainWindow {

    static CreateGAAircraft ac = new CreateGAAircraft();
    public static void main(String[] args) {
        String returnedCS = CreateGAAircraft.returnCS();
        String returnedType = CreateGAAircraft.returnType();
        String returnedStatus = CreateGAAircraft.returnStatus();

        System.out.println(returnedCS);
        System.out.println(returnedType);
        System.out.println(returnedStatus);
        
    }
}