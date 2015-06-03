/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.controllerLocal.ChessGameControler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Antoine
 */
public class ChessGameCmdLine {
    private ChessGameControler chessGameControler;
    
    private int [][] matriceJeu = 
    {
        {1, 2, 3, 4, 5, 6, 7, 8},
        {9, 10, 11, 12, 13, 14, 15, 16},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {17, 18, 19, 20, 21, 22, 23, 24},
        {25, 26, 27, 28, 29, 30, 31, 32}
    }; 
    
    private final String[] matriceNomPiece = {
        "_____", 
        
        "N_To1", "N_Ca1", "N_Fo1", "N_Re1",
        "N_Ro1", "N_Fo2", "N_Ca2", "N_To2",
        "N_Pi1", "N_Pi2", "N_Pi3", "N_Pi4",
        "N_Pi5", "N_Pi6", "N_Pi7", "N_Pi8",
        
        "B_Pi1", "B_Pi2", "B_Pi3", "B_Pi4",
        "B_Pi5", "B_Pi6", "B_Pi7", "B_Pi8",
        "B_To1", "B_Ca1", "B_Fo1", "B_Re1",
        "B_Ro1", "B_Fo2", "B_Ca2", "B_To2"
    };
    
    public ChessGameCmdLine(ChessGameControler chessGameControler) {
        this.chessGameControler = chessGameControler;
    }
    
    public void afficher(){
        System.out.println(chessGameControler.getMessage() + "\n");
        for(int i = 0 ; i < 8 ; i++)
            System.out.print("\t  " + i);
        
        System.out.println();
        
        for(int x = 0 ; x < 8 ; x++){
            System.out.print(x);
            for(int y = 0 ; y < 8 ; y++)
                System.out.print("\t" + matriceNomPiece[matriceJeu[x][y]]);
            System.out.println();
        }
        
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
