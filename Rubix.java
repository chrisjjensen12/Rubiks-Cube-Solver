import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Rubix extends Cube{


    //function to print side of cube given a 3x3 matrix
    public static void printSide(char side[][]){
        // Loop through all rows
        for (char[] row : side)
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public static void printKey(String side[][]){
        // Loop through all rows
        for (String[] row : side)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    //function to print entire cube
    public static void printCube(Cube cube){
        System.out.println("front:");
        printSide(cube.F);
        System.out.println("left:");
        printSide(cube.L);
        System.out.println("right:");
        printSide(cube.R);
        System.out.println("down:");
        printSide(cube.D);
        System.out.println("up:");
        printSide(cube.U);
        System.out.println("back:");
        printSide(cube.B);
        System.out.println("Key:");
        printKey(cube.key);
    }

    //makes a U cube turn
    public static Cube turnU(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"U turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make top rotation
        cube.U[0][0] = cube_dup.U[2][0];
        cube.U[0][1] = cube_dup.U[1][0];
        cube.U[0][2] = cube_dup.U[0][0];

        cube.U[1][0] = cube_dup.U[2][1];
        cube.U[1][1] = cube_dup.U[1][1];
        cube.U[1][2] = cube_dup.U[0][1];

        cube.U[2][0] = cube_dup.U[2][2];
        cube.U[2][1] = cube_dup.U[1][2];
        cube.U[2][2] = cube_dup.U[0][2];

        //front side
        for (int i = 0; i < 3; i++) {
            cube.F[0][i] = cube_dup.R[0][i];
        }
        //left side
        for (int i = 0; i < 3; i++) {
            cube.L[0][i] = cube_dup.F[0][i];
        }
        // //back side
        // for (int i = 0; i < 3; i++) {
        //     cube.B[0][i] = cube_dup.L[0][i];
        // }
        cube.B[0][2] = cube_dup.L[0][2];
        cube.B[0][1] = cube_dup.L[0][1];
        cube.B[0][0] = cube_dup.L[0][0];
        //right side
        for (int i = 0; i < 3; i++) {
            cube.R[0][i] = cube_dup.B[0][i];
        }
        return cube;
    }
    
    //makes an R' cube turn
    public static Cube turnRprime(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"R' turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make right rotation
        cube.R[0][0] = cube_dup.R[0][2];
        cube.R[0][1] = cube_dup.R[1][2];
        cube.R[0][2] = cube_dup.R[2][2];

        cube.R[1][0] = cube_dup.R[0][1];
        cube.R[1][1] = cube_dup.R[1][1];
        cube.R[1][2] = cube_dup.R[2][1];

        cube.R[2][0] = cube_dup.R[0][0];
        cube.R[2][1] = cube_dup.R[1][0];
        cube.R[2][2] = cube_dup.R[2][0];

        //front side
        cube.F[0][2] = cube_dup.U[0][2];
        cube.F[1][2] = cube_dup.U[1][2];
        cube.F[2][2] = cube_dup.U[2][2];

        //down
        cube.D[0][2] = cube_dup.F[0][2];
        cube.D[1][2] = cube_dup.F[1][2];
        cube.D[2][2] = cube_dup.F[2][2];

        //back side is weird, remember that -- will also change up
        cube.B[2][0] = cube_dup.D[0][2];
        cube.B[1][0] = cube_dup.D[1][2];
        cube.B[0][0] = cube_dup.D[2][2];

        //up switches sides because back also switches sides
        cube.U[0][2] = cube_dup.B[2][0];
        cube.U[1][2] = cube_dup.B[1][0];
        cube.U[2][2] = cube_dup.B[0][0];

        return cube;
    }

    //makes a L cube turn
    public static Cube turnL(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"L turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make left rotation
        cube.L[0][0] = cube_dup.L[2][0];
        cube.L[0][1] = cube_dup.L[1][0];
        cube.L[0][2] = cube_dup.L[0][0];

        cube.L[1][0] = cube_dup.L[2][1];
        cube.L[1][1] = cube_dup.L[1][1];
        cube.L[1][2] = cube_dup.L[0][1];

        cube.L[2][0] = cube_dup.L[2][2];
        cube.L[2][1] = cube_dup.L[1][2];
        cube.L[2][2] = cube_dup.L[0][2];

        //front side
        cube.F[0][0] = cube_dup.U[0][0];
        cube.F[1][0] = cube_dup.U[1][0];
        cube.F[2][0] = cube_dup.U[2][0];

        //down
        cube.D[0][0] = cube_dup.F[0][0];
        cube.D[1][0] = cube_dup.F[1][0];
        cube.D[2][0] = cube_dup.F[2][0];

        //back side is weird, remember that -- will also change up
        cube.B[2][2] = cube_dup.D[0][0];
        cube.B[1][2] = cube_dup.D[1][0];
        cube.B[0][2] = cube_dup.D[2][0];

        //up switches sides because back also switches sides
        cube.U[0][0] = cube_dup.B[2][2];
        cube.U[1][0] = cube_dup.B[1][2];
        cube.U[2][0] = cube_dup.B[0][2];

        return cube;
    }

    //makes a U' cube turn
    public static Cube turnUprime(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"U' turn");
        
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make top rotation
        cube.U[0][0] = cube_dup.U[0][2];
        cube.U[0][1] = cube_dup.U[1][2];
        cube.U[0][2] = cube_dup.U[2][2];

        cube.U[1][0] = cube_dup.U[0][1];
        cube.U[1][1] = cube_dup.U[1][1];
        cube.U[1][2] = cube_dup.U[2][1];

        cube.U[2][0] = cube_dup.U[0][0];
        cube.U[2][1] = cube_dup.U[1][0];
        cube.U[2][2] = cube_dup.U[2][0];

        //front side
        // for (int i = 3; i < 0; i--) {
        //     cube.F[0][i] = cube_dup.R[0][i];
        // }
        cube.F[0][2] = cube_dup.L[0][2];
        cube.F[0][1] = cube_dup.L[0][1];
        cube.F[0][0] = cube_dup.L[0][0];
        //left side
        // for (int i = 3; i < 0; i--) {
        //     cube.L[0][i] = cube_dup.F[0][i];
        // }
        cube.L[0][2] = cube_dup.B[0][2];
        cube.L[0][1] = cube_dup.B[0][1];
        cube.L[0][0] = cube_dup.B[0][0];
        // //back side
        // for (int i = 0; i < 3; i++) {
        //     cube.B[0][i] = cube_dup.L[0][i];
        // }
        cube.B[0][2] = cube_dup.R[0][2];
        cube.B[0][1] = cube_dup.R[0][1];
        cube.B[0][0] = cube_dup.R[0][0];
        //right side
        // for (int i = 3; i < 0; i--) {
        //     cube.R[0][i] = cube_dup.B[0][i];
        // }
        cube.R[0][2] = cube_dup.F[0][2];
        cube.R[0][1] = cube_dup.F[0][1];
        cube.R[0][0] = cube_dup.F[0][0];
        return cube;
    }

    //makes an R cube turn
    public static Cube turnR(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"R turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make right rotation
        cube.R[0][0] = cube_dup.R[2][0];
        cube.R[0][1] = cube_dup.R[1][0];
        cube.R[0][2] = cube_dup.R[0][0];

        cube.R[1][0] = cube_dup.R[2][1];
        cube.R[1][1] = cube_dup.R[1][1];
        cube.R[1][2] = cube_dup.R[0][1];

        cube.R[2][0] = cube_dup.R[2][2];
        cube.R[2][1] = cube_dup.R[1][2];
        cube.R[2][2] = cube_dup.R[0][2];

        //front side
        cube.F[0][2] = cube_dup.D[0][2];
        cube.F[1][2] = cube_dup.D[1][2];
        cube.F[2][2] = cube_dup.D[2][2];

        //down
        cube.D[0][2] = cube_dup.B[2][0];
        cube.D[1][2] = cube_dup.B[1][0];
        cube.D[2][2] = cube_dup.B[0][0];

        //back 
        cube.B[0][0] = cube_dup.U[2][2];
        cube.B[1][0] = cube_dup.U[1][2];
        cube.B[2][0] = cube_dup.U[0][2];

        //up 
        cube.U[0][2] = cube_dup.F[0][2];
        cube.U[1][2] = cube_dup.F[1][2];
        cube.U[2][2] = cube_dup.F[2][2];

        return cube;
    }

     //makes an L' cube turn
     public static Cube turnLprime(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"L' turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make right rotation
        cube.L[0][0] = cube_dup.L[0][2];
        cube.L[0][1] = cube_dup.L[1][2];
        cube.L[0][2] = cube_dup.L[2][2];

        cube.L[1][0] = cube_dup.L[0][1];
        cube.L[1][1] = cube_dup.L[1][1];
        cube.L[1][2] = cube_dup.L[2][1];

        cube.L[2][0] = cube_dup.L[0][0];
        cube.L[2][1] = cube_dup.L[1][0];
        cube.L[2][2] = cube_dup.L[2][0];

        //front side
        cube.F[0][0] = cube_dup.D[0][0];
        cube.F[1][0] = cube_dup.D[1][0];
        cube.F[2][0] = cube_dup.D[2][0];

        //down
        cube.D[2][0] = cube_dup.B[0][2];
        cube.D[1][0] = cube_dup.B[1][2];
        cube.D[0][0] = cube_dup.B[2][2];

        //back 
        cube.B[0][2] = cube_dup.U[2][0];
        cube.B[1][2] = cube_dup.U[1][0];
        cube.B[2][2] = cube_dup.U[0][0];

        //up 
        cube.U[0][0] = cube_dup.F[0][0];
        cube.U[1][0] = cube_dup.F[1][0];
        cube.U[2][0] = cube_dup.F[2][0];

        return cube;
    }

    //makes an Mtop cube turn
    public static Cube turnMtop(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"Mtop turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        cube.L[2][1] = cube_dup.D[1][2];
        cube.L[1][1] = cube_dup.D[1][1];
        cube.L[0][1] = cube_dup.D[1][0];

        cube.U[1][0] = cube_dup.L[2][1];
        cube.U[1][1] = cube_dup.L[1][1];
        cube.U[1][2] = cube_dup.L[0][1];

        cube.R[0][1] = cube_dup.U[1][0];
        cube.R[1][1] = cube_dup.U[1][1];
        cube.R[2][1] = cube_dup.U[1][2];

        cube.D[1][2] = cube_dup.R[0][1];
        cube.D[1][1] = cube_dup.R[1][1];
        cube.D[1][0] = cube_dup.R[2][1];

        return cube;
    }

    //makes an Mside cube turn
    public static Cube turnMside(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"Mside turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        cube.F[1][2] = cube_dup.L[1][2];
        cube.F[1][1] = cube_dup.L[1][1];
        cube.F[1][0] = cube_dup.L[1][0];

        cube.L[1][0] = cube_dup.B[1][0];
        cube.L[1][1] = cube_dup.B[1][1];
        cube.L[1][2] = cube_dup.B[1][2];

        cube.R[1][0] = cube_dup.F[1][0];
        cube.R[1][1] = cube_dup.F[1][1];
        cube.R[1][2] = cube_dup.F[1][2];

        cube.B[1][0] = cube_dup.R[1][0];
        cube.B[1][1] = cube_dup.R[1][1];
        cube.B[1][2] = cube_dup.R[1][2];
        

        return cube;
    }



    //function that duplicates the cube youre working with into a new (empty) cube 
    public static Cube duplicateCube(Cube cube, Cube cube_dup){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                cube_dup.F[j][i] = cube.F[j][i];
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                cube_dup.B[j][i] = cube.B[j][i];
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                cube_dup.L[j][i] = cube.L[j][i];
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                cube_dup.R[j][i] = cube.R[j][i];
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                cube_dup.U[j][i] = cube.U[j][i];
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                cube_dup.D[j][i] = cube.D[j][i];
            }
        }
        // printCube(cube);
        // System.out.println("//////////");
        // printCube(cube_dup);
        return cube_dup;
    }

    //makes a D cube turn
    public static Cube turnD(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"D turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make bottom rotation
        cube.D[0][0] = cube_dup.D[2][0];
        cube.D[0][1] = cube_dup.D[1][0];
        cube.D[0][2] = cube_dup.D[0][0];

        cube.D[1][0] = cube_dup.D[2][1];
        cube.D[1][1] = cube_dup.D[1][1];
        cube.D[1][2] = cube_dup.D[0][1];

        cube.D[2][0] = cube_dup.D[2][2];
        cube.D[2][1] = cube_dup.D[1][2];
        cube.D[2][2] = cube_dup.D[0][2];

        //front side
        for (int i = 0; i < 3; i++) {
            cube.F[2][i] = cube_dup.L[2][i];
        }
        //left side
        for (int i = 0; i < 3; i++) {
            cube.L[2][i] = cube_dup.B[2][i];
        }
        // //back side
        // for (int i = 0; i < 3; i++) {
        //     cube.B[0][i] = cube_dup.L[0][i];
        // }
        cube.B[2][2] = cube_dup.R[2][2];
        cube.B[2][1] = cube_dup.R[2][1];
        cube.B[2][0] = cube_dup.R[2][0];
        //right side
        for (int i = 0; i < 3; i++) {
            cube.R[2][i] = cube_dup.F[2][i];
        }
        return cube;
    }

    //makes a U cube turn
    public static Cube turnDprime(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"D' turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make bottom rotation
        cube.D[0][0] = cube_dup.D[0][2];
        cube.D[0][1] = cube_dup.D[1][2];
        cube.D[0][2] = cube_dup.D[2][2];

        cube.D[1][0] = cube_dup.D[0][1];
        cube.D[1][1] = cube_dup.D[1][1];
        cube.D[1][2] = cube_dup.D[2][1];

        cube.D[2][0] = cube_dup.D[0][0];
        cube.D[2][1] = cube_dup.D[1][0];
        cube.D[2][2] = cube_dup.D[2][0];

        //front side
        for (int i = 0; i < 3; i++) {
            cube.F[2][i] = cube_dup.R[2][i];
        }
        //left side
        for (int i = 0; i < 3; i++) {
            cube.L[2][i] = cube_dup.F[2][i];
        }
        // //back side
        // for (int i = 0; i < 3; i++) {
        //     cube.B[0][i] = cube_dup.L[0][i];
        // }
        cube.B[2][2] = cube_dup.L[2][2];
        cube.B[2][1] = cube_dup.L[2][1];
        cube.B[2][0] = cube_dup.L[2][0];
        //right side
        for (int i = 0; i < 3; i++) {
            cube.R[2][i] = cube_dup.B[2][i];
        }
        return cube;
    }

    //makes an F cube turn
    public static Cube turnF(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"F turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make front rotation
        cube.F[0][0] = cube_dup.F[2][0];
        cube.F[0][1] = cube_dup.F[1][0];
        cube.F[0][2] = cube_dup.F[0][0];

        cube.F[1][0] = cube_dup.F[2][1];
        cube.F[1][1] = cube_dup.F[1][1];
        cube.F[1][2] = cube_dup.F[0][1];

        cube.F[2][0] = cube_dup.F[2][2];
        cube.F[2][1] = cube_dup.F[1][2];
        cube.F[2][2] = cube_dup.F[0][2];

        //right side
        cube.R[0][0] = cube_dup.U[2][0];
        cube.R[1][0] = cube_dup.U[2][1];
        cube.R[2][0] = cube_dup.U[2][2];

        //down
        cube.D[0][0] = cube_dup.R[2][0];
        cube.D[0][1] = cube_dup.R[1][0];
        cube.D[0][2] = cube_dup.R[0][0];

        //left
        cube.L[0][2] = cube_dup.D[0][0];
        cube.L[1][2] = cube_dup.D[0][1];
        cube.L[2][2] = cube_dup.D[0][2];

        //up
        cube.U[2][0] = cube_dup.L[2][2];
        cube.U[2][1] = cube_dup.L[1][2];
        cube.U[2][2] = cube_dup.L[0][2];

        return cube;
    }


    //makes an F' cube turn
    public static Cube turnFprime(Cube cube){

        Globals.turnCounter++;
        System.out.println(Globals.turnCounter+") "+"F' turn");
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make front rotation
        cube.F[0][0] = cube_dup.F[0][2];
        cube.F[0][1] = cube_dup.F[1][2];
        cube.F[0][2] = cube_dup.F[2][2];

        cube.F[1][0] = cube_dup.F[0][1];
        cube.F[1][1] = cube_dup.F[1][1];
        cube.F[1][2] = cube_dup.F[2][1];

        cube.F[2][0] = cube_dup.F[0][0];
        cube.F[2][1] = cube_dup.F[1][0];
        cube.F[2][2] = cube_dup.F[2][0];

        //right side
        cube.R[0][0] = cube_dup.D[0][2];
        cube.R[1][0] = cube_dup.D[0][1];
        cube.R[2][0] = cube_dup.D[0][0];

        //down
        cube.D[0][0] = cube_dup.L[0][2];
        cube.D[0][1] = cube_dup.L[1][2];
        cube.D[0][2] = cube_dup.L[2][2];

        //left
        cube.L[0][2] = cube_dup.U[2][2];
        cube.L[1][2] = cube_dup.U[2][1];
        cube.L[2][2] = cube_dup.U[2][0];

        //up
        cube.U[2][0] = cube_dup.R[0][0];
        cube.U[2][1] = cube_dup.R[1][0];
        cube.U[2][2] = cube_dup.R[2][0];

        return cube;
    }

//makes an B cube turn
public static Cube turnB(Cube cube){

    Globals.turnCounter++;
    System.out.println(Globals.turnCounter+") "+"B turn");
    //create a duplicate cube for reference
    Cube cube_dup = new Cube();
    cube_dup = duplicateCube(cube, cube_dup);

    //make back rotation
    cube.B[0][0] = cube_dup.B[2][0];
    cube.B[0][1] = cube_dup.B[1][0];
    cube.B[0][2] = cube_dup.B[0][0];

    cube.B[1][0] = cube_dup.B[2][1];
    cube.B[1][1] = cube_dup.B[1][1];
    cube.B[1][2] = cube_dup.B[0][1];

    cube.B[2][0] = cube_dup.B[2][2];
    cube.B[2][1] = cube_dup.B[1][2];
    cube.B[2][2] = cube_dup.B[0][2];

    //right side
    cube.R[0][2] = cube_dup.D[2][2];
    cube.R[1][2] = cube_dup.D[2][1];
    cube.R[2][2] = cube_dup.D[2][0];

    //down
    cube.D[2][0] = cube_dup.L[0][0];
    cube.D[2][1] = cube_dup.L[1][0];
    cube.D[2][2] = cube_dup.L[2][0];

    //left
    cube.L[0][0] = cube_dup.U[0][2];
    cube.L[1][0] = cube_dup.U[0][1];
    cube.L[2][0] = cube_dup.U[0][0];

    //up
    cube.U[0][0] = cube_dup.R[0][2];
    cube.U[0][1] = cube_dup.R[1][2];
    cube.U[0][2] = cube_dup.R[2][2];

    return cube;
}

//makes an B' cube turn
public static Cube turnBprime(Cube cube){

    Globals.turnCounter++;
    System.out.println(Globals.turnCounter+") "+"B' turn");
    //create a duplicate cube for reference
    Cube cube_dup = new Cube();
    cube_dup = duplicateCube(cube, cube_dup);

    //make back rotation
    cube.B[0][0] = cube_dup.B[0][2];
    cube.B[0][1] = cube_dup.B[1][2];
    cube.B[0][2] = cube_dup.B[2][2];

    cube.B[1][0] = cube_dup.B[0][1];
    cube.B[1][1] = cube_dup.B[1][1];
    cube.B[1][2] = cube_dup.B[2][1];

    cube.B[2][0] = cube_dup.B[0][0];
    cube.B[2][1] = cube_dup.B[1][0];
    cube.B[2][2] = cube_dup.B[2][0];

    //right side
    cube.R[0][2] = cube_dup.U[0][0];
    cube.R[1][2] = cube_dup.U[0][1];
    cube.R[2][2] = cube_dup.U[0][2];

    //down
    cube.D[2][0] = cube_dup.R[2][2];
    cube.D[2][1] = cube_dup.R[1][2];
    cube.D[2][2] = cube_dup.R[0][2];

    //left
    cube.L[0][0] = cube_dup.D[2][0];
    cube.L[1][0] = cube_dup.D[2][1];
    cube.L[2][0] = cube_dup.D[2][2];

    //up
    cube.U[0][0] = cube_dup.L[2][0];
    cube.U[0][1] = cube_dup.L[1][0];
    cube.U[0][2] = cube_dup.L[0][0];

    return cube;
}

//randomizes cube
public static Cube randomize(Cube cube){
        
    Random rand = new Random(); //instance of random class
    int upperbound = 12;
    //generate random values from 0-12
    for (int i = 0; i < 25; i++) {
        int int_random = rand.nextInt(upperbound)+1;
        // System.out.println(int_random);
        //cube is turned based on random number
        scramble(cube, int_random);
    }
    
    return cube;
}

public static Cube scramble(Cube cube, int int_random){
    
    switch(int_random)
    {
        case 1:
            turnU(cube);
            // System.out.println("U");
            break;
        case 2:
            turnUprime(cube);
            // System.out.println("U'");
            break;
        case 3:
            turnL(cube);
            // System.out.println("L");
            break;
        case 4:
            turnLprime(cube);
            // System.out.println("L'");
            break;
        case 5:
            turnR(cube);
            // System.out.println("R");
            break;
        case 6:
            turnRprime(cube);
            // System.out.println("R'");
            break;
        case 7:
            turnD(cube);
            // System.out.println("D");
            break;
        case 8:
            turnDprime(cube);
            // System.out.println("D'");
            break;
        case 9:
            turnF(cube);
            // System.out.println("F");
            break;
        case 10:
            turnFprime(cube);
            // System.out.println("F'");
            break;
        case 11:
            turnB(cube);
            // System.out.println("B");
            break;
        case 12:
            turnBprime(cube);
            // System.out.println("B'");
            break;
        default:
            // System.out.println("default");
            break;
    }
    
    return cube;
}
public static Boolean openSpaceOnBottom(Cube cube, int row, int col){
    for (int i = 0; i < 4; i++) {
        // System.out.println(i);
        if(cube.D[row][col] != 'W'){
            // System.out.println("found a spot!");
            return true;
        }else{
            turnD(cube);
        }
    }
    // System.out.println("unable to find spot");
    return false;
}

public static Boolean checkFaces(Cube cube){
    boolean drive = true;
        if(cube.F[1][0] == 'W'){
            drive = false;
        }
        if(cube.F[1][2] == 'W'){
            drive = false;
        }
        if(cube.R[1][0] == 'W'){
            drive = false;
        }
        if(cube.R[1][2] == 'W'){
            drive = false;
        }
        if(cube.L[1][0] == 'W'){
            drive = false;
        }
        if(cube.L[1][2] == 'W'){
            drive = false;
        }
        if(cube.B[1][0] == 'W'){
            drive = false;
        }
        if(cube.B[1][2] == 'W'){
            drive = false;
        }
        if(cube.U[1][0] == 'W'){
            drive = false;
        }
        if(cube.U[1][2] == 'W'){
            drive = false;
        }
    if(drive == false){
        return false;
    }
    return true;
}

public static Boolean checkFacesFinal(Cube cube){
    boolean drive = true;
        if(cube.F[1][0] == 'W' || cube.F[1][2] == 'W' || cube.F[0][1] == 'W' || cube.F[2][1] == 'W'){
            drive = false;
        }
        if(cube.R[1][0] == 'W' || cube.R[1][2] == 'W' || cube.R[0][1] == 'W' || cube.R[2][1] == 'W'){
            drive = false;
        }
        if(cube.L[1][0] == 'W' || cube.L[1][2] == 'W' || cube.L[0][1] == 'W' || cube.L[2][1] == 'W'){
            drive = false;
        }
        if(cube.B[1][0] == 'W' || cube.B[1][2] == 'W' || cube.B[0][1] == 'W' || cube.B[2][1] == 'W'){
            drive = false;
        }
        if(cube.U[1][0] == 'W' || cube.U[1][2] == 'W' || cube.U[0][1] == 'W' || cube.U[2][1] == 'W'){
            drive = false;
        }
    if(drive == false){
        return false;
    }
    return true;
}

public static Cube scanFacesDrive(Cube cube){
    //if piece is on the road, drive it home
    //if piece is in median, roate face and drive it home
    //if car is blocking, rotate top until a free space emerges
    
    while(checkFaces(cube) == false){
        if(cube.F[1][0] == 'W'){
            openSpaceOnBottom(cube, 1, 0);
            turnL(cube);
        }
        if(cube.F[1][2] == 'W'){
            openSpaceOnBottom(cube, 1, 2);
            turnRprime(cube);
        }
        if(cube.R[1][0] == 'W'){
            openSpaceOnBottom(cube, 0, 1);
            turnF(cube);
        }
        if(cube.R[1][2] == 'W'){
            openSpaceOnBottom(cube, 2, 1);
            turnBprime(cube);
        }
        if(cube.L[1][0] == 'W'){
            openSpaceOnBottom(cube, 2, 1);
            turnB(cube);
        }
        if(cube.L[1][2] == 'W'){
            openSpaceOnBottom(cube, 0, 1);
            turnFprime(cube);
        }
        if(cube.B[1][0] == 'W'){
            openSpaceOnBottom(cube, 1, 2);
            turnR(cube);
        }
        if(cube.B[1][2] == 'W'){
            openSpaceOnBottom(cube, 1, 0);
            turnLprime(cube);
        }
        if(cube.U[1][0] == 'W'){
            openSpaceOnBottom(cube, 1, 0);
            turnL(cube);
            turnL(cube);
        }
        if(cube.U[1][2] == 'W'){
            openSpaceOnBottom(cube, 1, 2);
            turnRprime(cube);
            turnRprime(cube);
        }
    }
    return cube;
    }
    public static Cube scanFacesMedian(Cube cube){
        //if piece is on the road, drive it home
        //if piece is in median, roate face and drive it home
        //if car is blocking, rotate top until a free space emerges
        
            if(cube.F[0][1] == 'W'){
                openSpaceOnBottom(cube, 0, 1);
                turnF(cube);
                openSpaceOnBottom(cube, 1, 2);
                turnRprime(cube);
            }
            if(cube.F[2][1] == 'W'){
                openSpaceOnBottom(cube, 0, 1);
                turnFprime(cube);
                openSpaceOnBottom(cube, 1, 2);
                turnRprime(cube);
            }
            if(cube.R[0][1] == 'W'){
                openSpaceOnBottom(cube, 1, 2);
                turnRprime(cube);
                openSpaceOnBottom(cube, 0, 1);
                turnF(cube);
            }
            if(cube.R[2][1] == 'W'){
                openSpaceOnBottom(cube, 1, 2);
                turnR(cube);
                openSpaceOnBottom(cube, 0, 1);
                turnF(cube);
            }
            if(cube.L[0][1] == 'W'){
                openSpaceOnBottom(cube, 1, 0);
                turnL(cube);
                openSpaceOnBottom(cube, 0, 1);
                turnFprime(cube);
            }
            if(cube.L[2][1] == 'W'){
                openSpaceOnBottom(cube, 1, 0);
                turnLprime(cube);
                openSpaceOnBottom(cube, 0, 1);
                turnFprime(cube);
            }
            if(cube.B[0][1] == 'W'){
                openSpaceOnBottom(cube, 2, 1);
                turnBprime(cube);
                openSpaceOnBottom(cube, 1, 2);
                turnR(cube);
            }
            if(cube.B[2][1] == 'W'){
                openSpaceOnBottom(cube, 2, 1);
                turnB(cube);
                openSpaceOnBottom(cube, 1, 2);
                turnR(cube);
            }
            
            if(cube.U[0][1] == 'W'){
                openSpaceOnBottom(cube, 2, 1);
                turnBprime(cube);
                turnBprime(cube);
            }
            if(cube.U[2][1] == 'W'){
                openSpaceOnBottom(cube, 0, 1);
                turnF(cube);
                turnF(cube);
            }

    return cube;
}

public static Boolean sidesMatchedUp(Cube cube){
    if(cube.U[0][1] != 'W' || cube.U[1][0] != 'W' || cube.U[1][1] != 'W' || cube.U[1][2] != 'W' || cube.U[2][1] != 'W'){
        return false;
    }
    if(cube.F[1][1] != 'G' && cube.F[0][1] != 'G'){
        return false;
    }
    if(cube.R[1][1] != 'R' && cube.R[0][1] != 'R'){
        return false;
    }
    if(cube.L[1][1] != 'O' && cube.L[0][1] != 'O'){
        return false;
    }
    if(cube.B[1][1] != 'B' && cube.B[0][1] != 'B'){
        return false;
    }
    return true;
}

//step one done!
public static Cube whiteDaisy(Cube cube){
    while(checkFacesFinal(cube) == false){
        scanFacesDrive(cube);
        scanFacesMedian(cube);
    }
    return cube;
}

//step two done!
public static Cube whiteCross(Cube cube){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(sidesMatchedUp(cube) == true){
                    return cube;
                }
                if(i == 0){
                    if((cube.F[1][1] == cube.F[2][1]) && cube.U[2][1] != 'W' && cube.D[0][1] == 'W'){
                        turnF(cube);
                        turnF(cube);
                        break;
                    }else{
                        // System.out.println("green");
                        turnD(cube);
                    }
                }
                if(i == 1){
                    if((cube.R[1][1] == cube.R[2][1]) && cube.U[1][2] != 'W' && cube.D[1][2] == 'W'){
                        turnRprime(cube);
                        turnRprime(cube);
                        break;
                    }else{
                        // System.out.println("red");
                        turnD(cube);
                    }
                }
                if(i == 2){
                    if((cube.L[1][1] == cube.L[2][1]) && cube.U[1][0] != 'W' && cube.D[1][0] == 'W'){
                        turnL(cube);
                        turnL(cube);
                        break;
                    }else{
                        // System.out.println("orange");
                        turnD(cube);
                    }
                }
                if(i == 3){
                    if((cube.B[1][1] == cube.B[2][1]) && (cube.U[0][1] != 'W') && cube.D[2][1] == 'W'){
                        turnBprime(cube);
                        turnBprime(cube);
                        break;
                    }else{
                        // System.out.println("blue");
                        turnD(cube);
                    }
                }
            }
        }
    return cube;
}



public static Cube popCorner(Cube cube, char color1, char color2){
    //corner 1
    if((cube.F[0][2] == color1 || cube.R[0][0] == color1 || cube.U[2][2] == color1) && (cube.F[0][2] == color2 || cube.R[0][0] == color2 || cube.U[2][2] == color2)){
        // System.out.println("popping corner1");
        turnRprime(cube);//R'
        turnDprime(cube);//D'
        turnR(cube);//R
    }
    //corner 2
    if((cube.R[0][2] == color1 || cube.B[0][0] == color1 || cube.U[0][2] == color1) && (cube.R[0][2] == color2 || cube.B[0][0] == color2 || cube.U[0][2] == color2)){
        // System.out.println("popping corner2");
        turnR(cube);//R'
        turnD(cube);//D'
        turnRprime(cube);//R
    }
    //corner 3
    if((cube.L[0][0] == color1 || cube.B[0][2] == color1 || cube.U[0][0] == color1) && (cube.L[0][0] == color2 || cube.B[0][2] == color2 || cube.U[0][0] == color2)){
        // System.out.println("popping corner3");
        turnLprime(cube);//R'
        turnD(cube);//D'
        turnL(cube);//R
    }
    //corner 4
    if((cube.F[0][0] == color1 || cube.L[0][2] == color1 || cube.U[2][0] == color1) && (cube.F[0][0] == color2 || cube.L[0][2] == color2 || cube.U[2][0] == color2)){
        // System.out.println("popping corner4");
        turnL(cube);//R'
        turnD(cube);//D'
        turnLprime(cube);//R
    }

    return cube;
}

public static Boolean conditionsMet(Cube cube, char color1, char color2){
    if(color1 == 'G' && color2 == 'R'){ //corner 1 (red green)
        if(cube.F[2][2] == 'G' && cube.R[2][0] == 'W' && cube.D[0][2] == 'R'){ //condition 1
            //do alg and return true
            turnRprime(cube);
            turnDprime(cube);
            turnR(cube);
            // System.out.println("condition1 met, returning true");
            return true;
        }
        else if(cube.F[2][2] == 'W' && cube.R[2][0] == 'R' && cube.D[0][2] == 'G'){ //condition 2
            //do alg and return true
            turnF(cube);
            turnD(cube);
            turnFprime(cube);
            // System.out.println("condition2 met, returning true");
            return true;
        }
        else if(cube.F[2][2] == 'R' && cube.R[2][0] == 'G' && cube.D[0][2] == 'W'){ //condition 3
            //do alg and return true
            turnF(cube);
            turnL(cube);
            turnD(cube);
            turnD(cube);
            turnLprime(cube);
            turnFprime(cube);
            // System.out.println("condition3 met, returning true");
            return true;
        }else if(cube.U[2][2] == 'W' && cube.F[0][2] == 'G' && cube.R[0][0] == 'R'){
            // System.out.println("corner 1 solved!");
            return true;
        }else{
            //pop and return false
            // System.out.println("no condition met, popping all unsolved corners and trying again");
            popCorner(cube, 'R', 'G');
            return false;
        }
    }

    if(color1 == 'R' && color2 == 'B'){ //corner 2 (red blue)
        if(cube.R[2][2] == 'R' && cube.B[2][0] == 'W' && cube.D[2][2] == 'B'){ //condition 1
            //do alg and return true
            turnBprime(cube);
            turnDprime(cube);
            turnB(cube);
            // System.out.println("condition1 met, returning true");
            return true;
        }
        else if(cube.R[2][2] == 'W' && cube.B[2][0] == 'B' && cube.D[2][2] == 'R'){ //condition 2
            //do alg and return true
            turnR(cube);
            turnD(cube);
            turnRprime(cube);
            // System.out.println("condition2 met, returning true");
            return true;
        }
        else if(cube.R[2][2] == 'B' && cube.B[2][0] == 'R' && cube.D[2][2] == 'W'){ //condition 3
            //do alg and return true
            turnR(cube);
            turnF(cube);
            turnD(cube);
            turnD(cube);
            turnFprime(cube);
            turnRprime(cube);
            // System.out.println("condition3 met, returning true");
            return true;
        }else if(cube.U[0][2] == 'W' && cube.R[0][2] == 'R' && cube.B[0][0] == 'B'){
            // System.out.println("corner 2 solved!");
            return true;
        }else{
            //pop and return false
            // System.out.println("no condition met, popping all unsolved corners and trying again");
            popCorner(cube, 'B', 'R');
            return false;
        }
    }

    if(color1 == 'B' && color2 == 'O'){ //corner 2 (blue orange)
        if(cube.B[2][2] == 'B' && cube.L[2][0] == 'W' && cube.D[2][0] == 'O'){ //condition 1
            //do alg and return true
            turnLprime(cube);
            turnDprime(cube);
            turnL(cube);
            // System.out.println("condition1 met, returning true");
            return true;
        }
        else if(cube.B[2][2] == 'W' && cube.L[2][0] == 'O' && cube.D[2][0] == 'B'){ //condition 2
            //do alg and return true
            turnB(cube);
            turnD(cube);
            turnBprime(cube);
            // System.out.println("condition2 met, returning true");
            return true;
        }
        else if(cube.B[2][2] == 'O' && cube.L[2][0] == 'B' && cube.D[2][0] == 'W'){ //condition 3
            //do alg and return true
            turnB(cube);
            turnR(cube);
            turnD(cube);
            turnD(cube);
            turnRprime(cube);
            turnBprime(cube);
            // System.out.println("condition3 met, returning true");
            return true;
        }else if(cube.U[0][0] == 'W' && cube.B[0][2] == 'B' && cube.L[0][0] == 'O'){
            // System.out.println("corner 3 solved!");
            return true;
        }else{
            //pop and return false
            // System.out.println("no condition met, popping all unsolved corners and trying again");
            popCorner(cube, 'B', 'O');
            return false;
        }
    }

    if(color1 == 'O' && color2 == 'G'){ //corner 2 (orange green)
        if(cube.F[2][0] == 'W' && cube.L[2][2] == 'O' && cube.D[0][0] == 'G'){ //condition 1
            //do alg and return true
            turnFprime(cube);
            turnDprime(cube);
            turnF(cube);
            // System.out.println("condition1 met, returning true");
            return true;
        }
        else if(cube.F[2][0] == 'G' && cube.L[2][2] == 'W' && cube.D[0][0] == 'O'){ //condition 2
            //do alg and return true
            turnL(cube);
            turnD(cube);
            turnLprime(cube);
            // System.out.println("condition2 met, returning true");
            return true;
        }
        else if(cube.F[2][0] == 'O' && cube.L[2][2] == 'G' && cube.D[0][0] == 'W'){ //condition 3
            //do alg and return true
            turnL(cube);
            turnB(cube);
            turnD(cube);
            turnD(cube);
            turnBprime(cube);
            turnLprime(cube);
            // System.out.println("condition3 met, returning true");
            return true;
        }else if(cube.U[2][0] == 'W' && cube.F[0][0] == 'G' && cube.L[0][2] == 'O'){
            // System.out.println("corner 4 solved!");
            return true;
        }else{
            //pop and return false
            // System.out.println("no condition met, popping all unsolved corners and trying again");
            popCorner(cube, 'O', 'G');
            return false;
        }
    }


    return true;
}

// 
public static Cube whiteCorners(Cube cube){
    // findUnsolvedCorners(cube);
    char color1 = ' ';
    char color2 = ' ';
    for(int i = 0; i < 4; i++){ //for every corner
        if(i == 0){ //corner 1 (green red)
            color1 = 'G';
            color2 = 'R';
            while(!conditionsMet(cube, color1, color2)){ //while conditions not met 
                // System.out.println("spinning (corner 1)");
                turnD(cube); //spin da block
            }
        }
        if(i == 1){ //corner 2 (red blue)
            color1 = 'R';
            color2 = 'B';
            while(!conditionsMet(cube, color1, color2)){ //while conditions not met 
                // System.out.println("spinning (corner 2)");
                turnD(cube); //spin da block
            }
        }
        if(i == 2){ //(blue orange)
            color1 = 'B';
            color2 = 'O';
            while(!conditionsMet(cube, color1, color2)){ //while conditions not met 
                // System.out.println("spinning (corner 3)");
                turnD(cube); //spin da block
            }
        }
        if(i == 3){ //(orange green)
            color1 = 'O';
            color2 = 'G';
            while(!conditionsMet(cube, color1, color2)){ //while conditions not met 
                // System.out.println("spinning (corner 4)");
                turnD(cube); //spin da block
            }
        }
            
    }
    return cube;
}

    public static Cube popCorner(Cube cube){ 

        turnU(cube);

        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnUprime(cube);

        turnFprime(cube);
        turnUprime(cube);
        turnF(cube);
        turnU(cube);

        return cube;
    }

    public static Cube popIfNeeded(Cube cube, char color1, char color2){ 
        if((cube.F[1][2] == color1 && cube.R[1][0] == color2) || (cube.F[1][2] == color2 && cube.R[1][0] == color1)){ //if needed cube is in corner 1
            // System.out.println("Need to pop corner 1!");
            popCorner(cube);
        }else if((cube.R[1][2] == color1 && cube.B[1][0] == color2) || (cube.R[1][2] == color2 && cube.B[1][0] == color1)){ //if needed cube is in corner 2
            // System.out.println("Need to pop corner 2!");
            turnU(cube);
            turnMside(cube);
            turnMside(cube);
            turnMside(cube);
            turnDprime(cube);
            popCorner(cube);
            turnD(cube);
            turnMside(cube);
            turnUprime(cube);
        } else if((cube.B[1][2] == color1 && cube.L[1][0] == color2) || (cube.B[1][2] == color2 && cube.L[1][0] == color1)){ //if needed cube is in corner 3
            // System.out.println("Need to pop corner 3!");
            turnUprime(cube);
            turnUprime(cube);
            turnMside(cube);
            turnMside(cube);
            turnD(cube);
            turnD(cube);
            popCorner(cube);
            turnDprime(cube);
            turnDprime(cube);
            turnMside(cube);
            turnMside(cube);
            turnU(cube);
            turnU(cube);
        } else if((cube.L[1][2] == color1 && cube.F[1][0] == color2) || (cube.L[1][2] == color2 && cube.F[1][0] == color1)){ //if needed cube is in corner 4
            // System.out.println("Need to pop corner 4!");
            turnUprime(cube);
            turnMside(cube);
            turnD(cube);
            popCorner(cube);
            turnDprime(cube);
            turnMside(cube);
            turnMside(cube);
            turnMside(cube);
            turnU(cube);
        }else{
            // System.out.println("No need to pop corner");
        }
        return cube;
    }

    public static Cube solveLeftFirst(Cube cube){
        turnUprime(cube);

        turnFprime(cube);
        turnUprime(cube);
        turnF(cube);
        turnU(cube);

        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnUprime(cube);

        return cube;
    }

    public static Cube solveRightFirst(Cube cube){

        turnU(cube);

        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnUprime(cube);

        turnFprime(cube);
        turnUprime(cube);
        turnF(cube);
        turnU(cube);

        return cube;
    }

    public static Cube solveMiddlePieces(Cube cube, char color1x, char color2y){

        int cornerSolved = 0;
        char color1 = ' ';
        char color2 = ' ';

        while(cornerSolved != 1){
            color1 = color1x;
            color2 = color2y;
            if((cube.F[1][2] == cube.F[1][1] && cube.R[1][0] == cube.R[1][1])){
                // System.out.println("Corner Already Solved!");
                cornerSolved = 1;
                break;
            }
            popIfNeeded(cube, color1, color2);
            for(int i = 0; i < 4; i++){ 
                turnU(cube);
                if(cube.R[1][1] == cube.R[0][1] && cube.U[1][2] == color1){
                    cornerSolved = 1;
                    // System.out.println("Corner ready to be solved! (left first)");
                    solveLeftFirst(cube);
                    break;
                }
                if(cube.F[1][1] == cube.F[0][1] && cube.U[2][1] == color2){
                    cornerSolved = 1;
                    // System.out.println("Corner ready to be solved! (right first)");
                    solveRightFirst(cube);
                    break;
                }
            }
        }

        return cube;
    }

    public static Cube secondLayerMiddlePieces(Cube cube){
        //flip upside down
        turnF(cube);
        turnF(cube);
        turnMtop(cube);
        turnMtop(cube);
        turnBprime(cube);
        turnBprime(cube);

        //corner 1:
        solveMiddlePieces(cube, 'G', 'O');

        
        //corner 2
        turnU(cube);
        turnMside(cube);
        turnMside(cube);
        turnMside(cube);
        turnDprime(cube);
        solveMiddlePieces(cube, 'O', 'B');
        turnD(cube);
        turnMside(cube);
        turnUprime(cube);

        //corner 3
        turnUprime(cube);
        turnUprime(cube);
        turnMside(cube);
        turnMside(cube);
        turnD(cube);
        turnD(cube);
        solveMiddlePieces(cube, 'B', 'R');
        turnDprime(cube);
        turnDprime(cube);
        turnMside(cube);
        turnMside(cube);
        turnU(cube);
        turnU(cube);

        //corner 4
        turnUprime(cube);
        turnMside(cube);
        turnD(cube);
        solveMiddlePieces(cube, 'R', 'G');
        turnDprime(cube);
        turnMside(cube);
        turnMside(cube);
        turnMside(cube);
        turnU(cube);
        
        

        return cube;
    }

    public static Cube yellowCrossAlg(Cube cube){

        turnF(cube);
        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnUprime(cube);
        turnFprime(cube);

        return cube;
    }

    public static Boolean conditionsMetYellowEdges(Cube cube) {
        if(cube.F[0][1] != 'G' || cube.R[0][1] != 'O' || cube.B[0][1] != 'B' || cube.L[0][1] != 'R'){
            return false;
        }else{
            return true;
        }
    }

    public static Cube yellowCross(Cube cube){

        // while(!conditionsMetYellowEdges(cube)){

            if(cube.U[0][1] == 'Y' && cube.U[1][1] == 'Y' && cube.U[2][1] == 'Y' && cube.U[1][2] == 'Y' && cube.U[1][0] == 'Y'){
                // System.out.println("Easiest case found, returning");
                return cube;
            }
    
            for(int i = 0; i < 4; i++){ 
                //check for easiest case first
                if(cube.U[1][0] == 'Y' && cube.U[1][1] == 'Y' && cube.U[1][2] == 'Y'){
                    yellowCrossAlg(cube);
                    return cube;
                }
                turnU(cube);
            }
            for(int i = 0; i < 4; i++){ 
                //check for easiest case first
                if(cube.U[1][0] == 'Y' && cube.U[1][1] == 'Y' && cube.U[0][1] == 'Y'){
                    yellowCrossAlg(cube);
                    yellowCrossAlg(cube);
                    return cube;
                }
                turnU(cube);
            }
            for(int i = 0; i < 4; i++){ 
                //check for easiest case first
                if(cube.U[1][1] == 'Y' && cube.U[1][0] != 'Y' && cube.U[2][1] != 'Y' && cube.U[0][1] != 'Y' && cube.U[1][2] != 'Y'){
                    // System.out.println("Bruh");
                    yellowCrossAlg(cube);
                    turnU(cube);
                    turnU(cube);
                    turnMside(cube);
                    turnMside(cube);
                    turnDprime(cube);
                    turnDprime(cube);
                    yellowCrossAlg(cube);
                    turnU(cube);
                    turnU(cube);
                    turnMside(cube);
                    turnMside(cube);
                    turnDprime(cube);
                    turnDprime(cube);
                    yellowCrossAlg(cube);
                }
                turnU(cube);
            }

        // }

        return cube;
    }

    public static Cube yellowEdgesAlg(Cube cube){
        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnU(cube);
        turnR(cube);
        turnUprime(cube);
        turnUprime(cube);
        turnRprime(cube);
        return cube;
    }

    public static Cube switchEdges(Cube cube){
        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnU(cube);
        turnR(cube);
        turnU(cube);
        turnU(cube);
        turnRprime(cube);
        turnU(cube);

        return cube;
    }
    public static Cube switchAcrossEdges(Cube cube){
        turnU(cube);
        switchEdges(cube);
        turnU(cube);
        turnU(cube);
        turnMside(cube);
        turnMside(cube);
        turnDprime(cube);
        turnDprime(cube);
        switchEdges(cube);

        return cube;
    }

    public static Cube yellowEdgesProcedure(Cube cube, int x){

        if(conditionsMetYellowEdges(cube)){
            // System.out.println("skipping step and returning");
            return cube;
        }else{
            turnUprime(cube);
            turnMside(cube);
            turnD(cube);
        }

        for(int i = 0; i < 4; i++){  //look for switch edges case
            if((cube.R[1][1] == cube.R[0][1] && cube.B[1][1] == cube.B[0][1]) && (cube.L[1][1] != cube.L[0][1] && cube.F[1][1] != cube.F[0][1])){
                switchEdges(cube);
                // System.out.println("case "+x+" found! (switch edges)");
                while(cube.F[0][1] != 'G'){
                    turnU(cube);
                }
                return cube;
            }else if((cube.F[1][1] == cube.F[0][1] && cube.B[1][1] == cube.B[0][1]) && (cube.L[1][1] != cube.L[0][1] && cube.R[1][1] != cube.R[0][1])){
                switchAcrossEdges(cube);
                // System.out.println("case "+x+" found! (switch across edges)");
                while(cube.F[0][1] != 'G'){
                    turnU(cube);
                }
                return cube;
            }else{
                // System.out.println("No cases found");
            }
            turnU(cube);
        }
        // //reset and line up
        // while(cube.F[0][1] != 'G'){
        //     turnU(cube);
        // }


        return cube;
    }

    public static Cube yellowEdges(Cube cube){

        if(!conditionsMetYellowEdges(cube)){
            yellowEdgesProcedure(cube, 1); //case 1 green and orange
            yellowEdgesProcedure(cube, 2); //case 2 green and orange
            yellowEdgesProcedure(cube, 3);
            yellowEdgesProcedure(cube, 4);
            while(cube.F[1][1] != 'G'){
                turnMside(cube);
                turnD(cube);
            }
        }else{
            // System.out.println("yellow edges already solved!");
        }
        


        return cube;
        
    }

    public static Boolean conditionsMetPositionCorners(Cube cube){

        int i = 0;

        if((cube.F[0][2] == cube.F[1][1] || cube.F[0][2] == cube.R[1][1] || cube.F[0][2] == cube.U[1][1])&&(cube.R[0][0] == cube.F[1][1] || cube.R[0][0] == cube.R[1][1] || cube.R[0][0] == cube.U[1][1])&&(cube.U[2][2] == cube.F[1][1] || cube.U[2][2] == cube.R[1][1] || cube.U[2][2] == cube.U[1][1])){
            i++;
        }
        if((cube.R[0][2] == cube.R[1][1] || cube.R[0][2] == cube.B[1][1] || cube.R[0][2] == cube.U[1][1])&&(cube.B[0][0] == cube.R[1][1] || cube.B[0][0] == cube.B[1][1] || cube.B[0][0] == cube.U[1][1])&&(cube.U[0][2] == cube.R[1][1] || cube.U[0][2] == cube.B[1][1] || cube.U[0][2] == cube.U[1][1])){
            i++;
        }
        if((cube.B[0][2] == cube.L[1][1] || cube.B[0][2] == cube.B[1][1] || cube.B[0][2] == cube.U[1][1])&&(cube.L[0][0] == cube.L[1][1] || cube.L[0][0] == cube.B[1][1] || cube.L[0][0] == cube.U[1][1])&&(cube.U[0][0] == cube.L[1][1] || cube.U[0][0] == cube.B[1][1] || cube.U[0][0] == cube.U[1][1])){
            i++;
        }
        if((cube.L[0][2] == cube.F[1][1] || cube.L[0][2] == cube.L[1][1] || cube.L[0][2] == cube.U[1][1])&&(cube.F[0][0] == cube.F[1][1] || cube.F[0][0] == cube.L[1][1] || cube.F[0][0] == cube.U[1][1])&&(cube.U[2][0] == cube.F[1][1] || cube.U[2][0] == cube.L[1][1] || cube.U[2][0] == cube.U[1][1])){
            i++;
        }
        if(i == 4){
            // System.out.println("true");
            return true;
        }else{
            // System.out.println(i);
            return false;
        }
        
    }

    public static Cube positionCornersAlg(Cube cube){
        while(!conditionsMetPositionCorners(cube)){
            // System.out.println("yew");
            turnU(cube);
            turnR(cube);
            turnUprime(cube);
            turnLprime(cube);
            turnU(cube);
            turnRprime(cube);
            turnUprime(cube);
            turnL(cube);
        }
        //return back to green in front
        while(cube.F[1][1] != 'G'){
            turnUprime(cube);
            turnMside(cube);
            turnD(cube);
        }
        return cube;
    }

    public static Cube positionFinalCorners(Cube cube){
        // System.out.println("corner 1: F[0][2], R[0][0], U[2][2] (green orange yellow)");
        // System.out.println("corner 2: R[0][2], B[0][0], U[0][2] (blue orange yellow)");
        // System.out.println("corner 3: L[0][0], B[0][2], U[0][0] (blue red yellow)");
        // System.out.println("corner 4: F[0][0], L[0][2], U[2][0] (green red yellow)");

        while(!conditionsMetPositionCorners(cube)){
            //if corner 1 in right spot, do alg until every corner is solved
            if((cube.F[0][2] == 'G' || cube.F[0][2] == 'O' || cube.F[0][2] == 'Y')&&(cube.R[0][0] == 'G' || cube.R[0][0] == 'O' || cube.R[0][0] == 'Y')&&(cube.U[2][2] == 'G' || cube.U[2][2] == 'O' || cube.U[2][2] == 'Y')){
                // System.out.println("Corner 1 in right spot");
                positionCornersAlg(cube);
                return cube;
            }else if((cube.R[0][2] == 'B' || cube.R[0][2] == 'O' || cube.R[0][2] == 'Y')&&(cube.B[0][0] == 'B' || cube.B[0][0] == 'O' || cube.B[0][0] == 'Y')&&(cube.U[0][2] == 'B' || cube.U[0][2] == 'O' || cube.U[0][2] == 'Y')){
                // System.out.println("Corner 2 in right spot");
                while(cube.F[1][1] != 'O'){
                    turnUprime(cube);
                    turnMside(cube);
                    turnD(cube);
                }
                positionCornersAlg(cube);
                return cube;
            }else if((cube.B[0][2] == 'B' || cube.B[0][2] == 'R' || cube.B[0][2] == 'Y')&&(cube.L[0][0] == 'B' || cube.L[0][0] == 'R' || cube.L[0][0] == 'Y')&&(cube.U[0][0] == 'B' || cube.U[0][0] == 'R' || cube.U[0][0] == 'Y')){
                // System.out.println("Corner 3 in right spot");
                while(cube.F[1][1] != 'B'){
                    turnUprime(cube);
                    turnMside(cube);
                    turnD(cube);
                }
                positionCornersAlg(cube);
                return cube;
            }else if((cube.L[0][2] == 'G' || cube.L[0][2] == 'R' || cube.L[0][2] == 'Y')&&(cube.F[0][0] == 'G' || cube.F[0][0] == 'R' || cube.F[0][0] == 'Y')&&(cube.U[2][0] == 'G' || cube.U[2][0] == 'R' || cube.U[2][0] == 'Y')){
                // System.out.println("Corner 4 in right spot");
                while(cube.F[1][1] != 'R'){
                    turnUprime(cube);
                    turnMside(cube);
                    turnD(cube);
                }
                positionCornersAlg(cube);
                return cube;
            }else{
                // System.out.println("No corner in right spot");
                turnU(cube);
                turnR(cube);
                turnUprime(cube);
                turnLprime(cube);
                turnU(cube);
                turnRprime(cube);
                turnUprime(cube);
                turnL(cube);
            }
        }

        return cube;
    }
    public static Cube alg(Cube cube){
        turnR(cube);
        turnU(cube);
        turnRprime(cube);
        turnUprime(cube);
        return cube;
    }

    public static Cube solveCompletely(Cube cube){
        //turn upside down
        turnF(cube);
        turnF(cube);
        turnMtop(cube);
        turnMtop(cube);
        turnBprime(cube);
        turnBprime(cube);

            while(cube.F[2][2] != 'G' || cube.D[0][2] != 'Y' || cube.R[2][0] != 'R'){
                // System.out.println("1st corner correcting");
                alg(cube);
            }
            turnD(cube);
            while(cube.F[2][2] != 'O' || cube.D[0][2] != 'Y' || cube.R[2][0] != 'G'){
                // System.out.println("2nd corner correcting");
                alg(cube);
            }
            turnD(cube);
            while(cube.F[2][2] != 'B' || cube.D[0][2] != 'Y' || cube.R[2][0] != 'O'){
                // System.out.println("3rd corner correcting");
                alg(cube);
            }
            turnD(cube);
            while(cube.F[2][2] != 'R' || cube.D[0][2] != 'Y' || cube.R[2][0] != 'B'){
                // System.out.println("4th corner correcting");
                alg(cube);
            }
            turnD(cube);


        return cube;
    }


    public static void solveCube(Cube cube){
        
        System.out.println("Step 0 (Randomizing Cube):");
        Globals.turnCounter = 0;
        Globals.turnCounterTotal = 0;
        randomize(cube); //randomizes cube

        ////////////////////////////////////////////////////
        long startTime = System.nanoTime(); //start time
        System.out.println("Step 1 (White Daisy):");
        Globals.turnCounter = 0;
        Globals.turnCounterTotal += Globals.turnCounter; //this one comes after because we don't count randomizing as part of solution
        whiteDaisy(cube); //step 1 (done)
        System.out.println("Step 2 (White Cross):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        whiteCross(cube); //step 2 (done)
        System.out.println("Step 3 (White Corners):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        whiteCorners(cube); //step 3 (done)
        System.out.println("Step 4 (Middle Layer Solve):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        secondLayerMiddlePieces(cube); //step 4 (done)
        System.out.println("Step 5 (Yellow Cross):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        yellowCross(cube); //step 5 (done)
        System.out.println("Step 6 (Yellow Edges):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        yellowEdges(cube); //step 6 (done)
        System.out.println("Step 7 (Position Corners):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        positionFinalCorners(cube); //step 7 (done)
        System.out.println("Step 8 (Solve Cube!):");
        Globals.turnCounterTotal += Globals.turnCounter;
        Globals.turnCounter = 0;
        solveCompletely(cube); //step 8!! (done!)
        long stopTime = System.nanoTime(); //end time
        ////////////////////////////////////////////////////
        
        long durationMS = TimeUnit.NANOSECONDS.toMillis(stopTime-startTime);

        printCube(cube); //prints cube
        System.out.println("Solution Found in: "+(stopTime-startTime)+" Nanoseconds! ("+durationMS+" Milliseconds)"); //prints solution time
        System.out.println("Achieves Solution in "+Globals.turnCounterTotal+" Turns"); //prints total number of turns for solution
    }


 
    public static void main(String args[])
        throws IOException{
        //create fresh cube
        Cube cube = new Cube();
        solveCube(cube);
    }
    
}

//done:
//U
//U'
//L
//L'
//R
//R'
//D
//D'
//F
//F'
//B
//B'
//done!



