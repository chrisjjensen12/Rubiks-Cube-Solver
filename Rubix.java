import java.io.*;
import java.util.*;

public class Rubix extends Cube{

    //function to print side of cube given a 3x3 matrix
    public static void printSide(char side[][]){
        //prints name of side given to function
        switch(side[1][1])
        {
            case 'G':
                System.out.println("front:");
                break;
            case 'O':
                System.out.println("left:");
                break;
            case 'R':
                System.out.println("right:");
                break;
            case 'Y':
                System.out.println("down:");
                break;
            case 'W':
                System.out.println("up:");
                break;
            case 'B':
                System.out.println("back:");
                break;
            default:
                System.out.println("default");
                break;
        }
        
        // Loop through all rows
        for (char[] row : side)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    //function to print entire cube
    public static void printCube(Cube cube){
        printSide(cube.F);
        printSide(cube.L);
        printSide(cube.R);
        printSide(cube.D);
        printSide(cube.U);
        printSide(cube.B);
    }

    //makes a U cube turn
    public static Cube turnU(Cube cube){
        
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
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make front rotation
        cube.R[0][0] = cube_dup.R[2][0];
        cube.R[0][1] = cube_dup.R[1][0];
        cube.R[0][2] = cube_dup.R[0][0];

        cube.R[1][0] = cube_dup.R[2][1];
        cube.R[1][1] = cube_dup.R[1][1];
        cube.R[1][2] = cube_dup.R[0][1];

        cube.R[2][0] = cube_dup.R[2][2];
        cube.R[2][1] = cube_dup.R[1][2];
        cube.R[2][2] = cube_dup.R[0][2];

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
        //create a duplicate cube for reference
        Cube cube_dup = new Cube();
        cube_dup = duplicateCube(cube, cube_dup);

        //make front rotation
        cube.R[0][0] = cube_dup.R[2][0];
        cube.R[0][1] = cube_dup.R[1][0];
        cube.R[0][2] = cube_dup.R[0][0];

        cube.R[1][0] = cube_dup.R[2][1];
        cube.R[1][1] = cube_dup.R[1][1];
        cube.R[1][2] = cube_dup.R[0][1];

        cube.R[2][0] = cube_dup.R[2][2];
        cube.R[2][1] = cube_dup.R[1][2];
        cube.R[2][2] = cube_dup.R[0][2];

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

    


    public static void solveCube(Cube cube){
        //step one...
        // turnU(cube);
        // turnL(cube);
        // turnLprime(cube);
        // turnR(cube);
        // turnUprime(cube);
        // turnD(cube);
        // turnLprime(cube);
        // turnDprime(cube);
        // turnRprime(cube);
        // turnD(cube);
        // turnDprime(cube);
        // turnDprime(cube);
        turnF(cube);
        turnLprime(cube);
        turnR(cube);
        turnU(cube);
        printCube(cube);
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


