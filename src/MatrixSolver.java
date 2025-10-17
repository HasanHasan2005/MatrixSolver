package src;

import java.util.ArrayList;

public class MatrixSolver {

    private boolean isNumericalChar(char character) {
        return ((int) character >= 48 & (int) character <= 57) || ((int) character == 47);
    }

    private void getVals(String equation, ArrayList<Character> variables, ArrayList<Fraction> values) {
        int ptr = 0;
        boolean pos = true;

        if (equation.charAt(ptr) == '-') {
            pos = false;
            do ptr++;
            while (equation.charAt(ptr) == ' ');
        }

        while (true) {
            int ptr2 = ptr;
            while (isNumericalChar(equation.charAt(ptr2))) {
                ptr2++;
            }

            variables.add(equation.charAt(ptr2));
            Fraction f = (ptr == ptr2) ? new Fraction(1) : new Fraction(equation.substring(ptr, ptr2));
            if (!pos) f.invertAdditive();
            values.add(f);
            ptr = ptr2;

            do ptr++;
            while (equation.charAt(ptr) == ' ');

            if (equation.charAt(ptr) == '=') break;
            pos = (equation.charAt(ptr) == '+');

            do ptr++;
            while (equation.charAt(ptr) == ' ');
        }

        pos = false;
        do ptr++;
        while (equation.charAt(ptr) == ' ');

        if (equation.charAt(ptr) == '-') {
            pos = true;
            do ptr++;
            while (equation.charAt(ptr) == ' ');
        }

        int ptr2 = ptr;
        do ptr2++;
        while (ptr2 < equation.length());

        variables.add(' ');
        Fraction f = new Fraction(equation.substring(ptr, ptr2));
        if (!pos) f.invertAdditive();
        values.add(f);
    }

    public void getSolutions(String[] equations) {
        int numRows = equations.length;

        /*
        3x + 4y + 5z = 3;
        2z = -6;
        10z + 7y = 18;

        -->

        {x,y,z,_} {3,4,5,-3}
        {z,_} {2,6}
        {z,y,_} {10,7,-18}

        new list = {}
        for all char lists:
           insert the char if not already in new list
        new list is now {x, y, z, _}

        create matrix (num rows = num equations,  num columns = size of new list)

        for all equations:
        start with first Fraction:
        check it's corresponding letter
        move in the new list until match

        set Fraction entry in (equation num, corresponding letter) = Fraction

         */



    }

    public static void main(String[] args) {

        //MatrixSolver ms = new MatrixSolver();

        //int precision = 3;

        Matrix m1 = new Matrix(new int[][]{
                {2, 0, -2, -6},
                {1, 0, 5, 1},
                {3, 0, 15, 3}});

        //m2.print();
        //m1.print();
        //System.out.println();

        //ms.bringToRREF(m1);

        //m1.print();

        //Fraction f = new Fraction(3.35f, 2);
        //System.out.println(f);

        //Fraction f = new Fraction("44");
        //System.out.println(f);



        //String equation = "- x - y - z = - 6/3";
        //ArrayList<Character> chars = new ArrayList<>();
        //ArrayList<Fraction> fractions = new ArrayList<>();
        //ms.getVals(equation, chars, fractions);

        //System.out.println(chars);
        //System.out.println(fractions);
    }
}