/*
 * Copyright (C) 2017 Chiragasourabh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package iText_Pdf_Generation;
import java.util.Random;
/**
 *
 * @author Chiragasourabh
 */

public class nextMark {
	
    private static final Random rand = new Random();
    private static int mark=0;
    private static final int sixEight[]= {6,8};
    private static final int sixFive[]= {6,5};
    private static final int fourEight[]= {4,8};
	
    public static int getNextMark(int currentTotalMark)
    {		
        if(currentTotalMark<=10)
        {
            switch(currentTotalMark)
            {
                case 0 : return sixEight[rand.nextInt(2)];
                case 6 : return sixFive[rand.nextInt(2)];
                case 8 : return fourEight[rand.nextInt(2)];
            }
        }
        else
        {
            switch(currentTotalMark)
            {
                case 11 : return 5;
                case 12 : return 4;
            }
        }
        return 0;
    }
}

