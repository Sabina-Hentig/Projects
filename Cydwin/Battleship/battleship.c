#include "battleship.h"

void welcomeScreen (void) {
	printf ("XXXXX   XXXX  XXXXXX XXXXXX XX     XXXXXX  XXXXX XX  XX XX XXXX\n");
	printf ("XX  XX XX  XX   XX     XX   XX     XX     XX     XX  XX XX XX  XX\n");
	printf ("XXXXX  XX  XX   XX     XX   XX     XXXX    XXXX  XXXXXX XX XXXX\n"); 
	printf ("XX  XX XXXXXX   XX     XX   XX     XX         XX XX  XX XX XX\n");
	printf ("XXXXX  XX  XX   XX     XX   XXXXXX XXXXXX XXXXX  XX  XX XX XX\n");
	printf ("\n\n");
	printf ("RULES OF THE GAME:\n");
	printf ("1. This is a two player game.\n");
	printf ("2. Player 1 is you and Player 2 is the computer.\n");
	printf ("3. Player 1 will be prompted if user wants to manually input coordinates\n");
	printf ("   for the game board or have the computer randomly generate a game board\n");
	printf ("4. There are five types of ships to be placed by longest length to the\n"); 
	printf ("   shortest; [c] Carrier has 5 cells, [b] Battleship has 4 cells, [r] Cruiser\n");
	printf ("   has 3 cells, [s] Submarine has 3 cells, [d] Destroyer has 2 cells\n");
	printf ("5. The computer randomly selects which player goes first\n");
	printf ("6. The game begins as each player tries to guess the location of the ships\n");
	printf ("   of the opposing player's game board; [*] hit and [m] miss\n");
	printf ("7. First player to guess the location of all ships wins\n\n");
}


void initializeGameBoard (Cell gameBoard[][COLS]) {
	int i = 0, j = 0;

	for (i = 0; i < ROWS; i++)
		for (j = 0; j < COLS; j++) {
			gameBoard[i][j].symbol          = WATER;
			gameBoard[i][j].position.row    = i;
			gameBoard[i][j].position.column = j;
		}
}


void printGameBoard (Cell gameBoard [][COLS], Boolean showPegs) {
	int i = 0, j = 0;

	printf ("  0 1 2 3 4 5 6 7 8 9\n");

	for (i = 0; i < ROWS; i++) {
		printf ("%d ", i);

		for (j = 0; j < COLS; j++) {
			if (showPegs == TRUE) 
				printf ("%c ", gameBoard [i][j].symbol);
			else {
				switch (gameBoard [i][j].symbol) {
					case HIT:   printf ("%c ", HIT);   break;
					case MISS:  printf ("%c ", MISS);  break;
					case WATER: 
					default:    printf ("%c ", WATER); break;
				}
			}
		}
		
		putchar ('\n');
	}
}


void putShipOnGameBoard (Cell gameBoard[][COLS], WaterCraft ship, 
	                     Coordinate position, int direction) {
	int i = ship.length - 1;

	for (i = 0; i < ship.length; i++) {
		if (direction == HORIZONTAL) 
			gameBoard [position.row][position.column + i].symbol = ship.symbol;
		else 
			gameBoard [position.row + i][position.column].symbol = ship.symbol;
	}
}


void manuallyPlaceShipsOnGameBoard (Cell gameBoard[][COLS], WaterCraft ship[]) {
	char       stringPosition[11] = "";
	int        i = 0, j = 0;

	Coordinate position[5];
	Boolean    isValid = FALSE;

	fflush (stdin);

	for (i = 0; i < NUM_OF_SHIPS; i++) {

		while (TRUE) {
			system ("cls");
			printGameBoard (gameBoard, TRUE);
			printf ("> Please enter the %d cells to place the %s across (no spaces):\n", ship[i].length, ship[i].name);
			printf ("> ");
			scanf ("%s", stringPosition);

			
			if (convertStringtoPosition (position, stringPosition, ship[i].length)) {

				isValid = TRUE;

				for (j = 0; j < ship[i].length; j++) {

					if (gameBoard[position[j].row][position[j].column].symbol == WATER) {
						gameBoard[position[j].row][position[j].column].symbol = ship[i].symbol;
					} else {
						isValid = FALSE;
						printf ("> Invalid input!\n");

						if (j != 0)
							while (j >= 0) {
								gameBoard[position[j].row][position[j].column].symbol = WATER;
								j--;
							}

						break;
					}
				}
			} else {
				isValid = FALSE;
				printf ("> Invalid input!\n");
			}

			if (isValid == TRUE) break;
		}

	}
}


void randomlyPlaceShipsOnGameBoard (Cell gameBoard[][COLS], WaterCraft ship[]) {
	Coordinate position;
	int direction = -1;
	int i = 0;

	for (i = 0; i < NUM_OF_SHIPS; i++) {
		while (TRUE) {
			direction = getRandomNumber (0, 1); /* 0 -> horizontal, 1 -> vertical */
			position = generatePosition (direction, ship[i].length);

			if (isValidLocation (gameBoard, position, direction, ship[i].length)) break;
		}

		putShipOnGameBoard (gameBoard, ship[i], position, direction);
	}
}


void updateGameBoard (Cell gameBoard[][COLS], Coordinate target) {
	switch (gameBoard[target.row][target.column].symbol) {
		/* miss */ 
		case WATER: 
			gameBoard[target.row][target.column].symbol = MISS;
			break;

		/* hit */
		case CARRIER: 
		case BATTLESHIP:
		case CRUISER:
		case SUBMARINE:
		case DESTROYER:
			gameBoard[target.row][target.column].symbol = HIT;
			break;

		case HIT:
		case MISS:
		default:
			break;
	}	
}


void checkBoundsOfCardinal (Boolean cardinals[], int bound, int direction) {
	switch (direction) {
		case NORTH: 
			if (bound < 0) 
				cardinals[0] = FALSE;
			else            
				cardinals[0] = TRUE;
			break;

		case SOUTH:
			if (bound > 9) 
				cardinals[1] = FALSE;
			else            
				cardinals[1] = TRUE;
			break;

		case WEST:
			if (bound < 0)  
				cardinals[2] = FALSE;
			else            
				cardinals[2] = TRUE;
			break;

		case EAST:
			if (bound > 9)  
				cardinals[3] = FALSE;
			else            
				cardinals[3] = TRUE;	
			break;
	}
}


void systemMessage (char *message) {
	char ch = '\0';

	do {
		printf ("%s", message);
	} while ((ch = getch()) != '\r');
}


Boolean checkSunkShip (short sunkShip[][NUM_OF_SHIPS], short player, char shipSymbol, FILE *stream) {
	Boolean sunked = FALSE;

	switch (shipSymbol) {
		case CARRIER:    
			if (--sunkShip[player][0] == 0) {
				printf ("> Player %d's Carrier sunked!\n", player + 1);

				
				fprintf (stream, "Player %d's Carrier sunked!\n", player + 1);

				sunked = TRUE;
			}
			break;

		case BATTLESHIP: 
			if (--sunkShip[player][1] == 0) {
				printf ("> Player %d's Battleship sunked!\n", player + 1);

			
				fprintf (stream, "Player %d's Battleship sunked!\n", player + 1);

				sunked = TRUE;
			}
			break;

		case CRUISER:    
			if (--sunkShip[player][2] == 0) {
				printf ("> Player %d's Cruiser sunked!\n", player + 1);

				
				fprintf (stream, "Player %d's Cruiser sunked!\n", player + 1);

				sunked = TRUE;
			}
			break;

		case SUBMARINE:  
			if (--sunkShip[player][3] == 0) {
				printf ("> Player %d's Submarine sunked!\n", player + 1);

				
				fprintf (stream, "Player %d's Submarine sunked!\n", player + 1);

				sunked = TRUE;
			}
			break;

		case DESTROYER:  
			if (--sunkShip[player][4] == 0) {
				printf ("> Player %d's Destroyer sunked!\n", player + 1);

				
				fprintf (stream, "Player %d's Destroyer sunked!\n", player + 1);

				sunked = TRUE;
			}
			break;
	}

	return sunked;
}


Boolean isValidLocation (Cell gameBoard[][COLS], Coordinate position, 
				         int direction, int length) {
	int i = length - 1;
	Boolean isValid = TRUE;

	for (i = 0; isValid && i < length; i++) {
		if (direction == HORIZONTAL) {
			if (gameBoard [position.row][position.column + i].symbol != WATER &&
				(position.column + i) < COLS)
				isValid = FALSE;
		} else { /* VERTICAL */
			if (gameBoard [position.row + i][position.column].symbol != WATER &&
				(position.row + i) < ROWS)
				isValid = FALSE;
		}
	}

	return isValid;
}


Boolean convertStringtoPosition (Coordinate position[], char *stringPosition, int length) {
	Boolean flag = TRUE;
	char temp = '\0';
	int i = 0, j = 0, k = 1;

	
	if (strlen (stringPosition)/2 == length) {
		
		for (i = 0; i < length && flag; i++) {
			
			if (isdigit (stringPosition[j]) && isdigit (stringPosition[k])) {
				position[i].row    = stringPosition[j] - '0';
				position[i].column = stringPosition[k] - '0'; 

				j += 2;
				k += 2;
			} else {
				flag = FALSE;
			}
		}
	} else {
		flag = FALSE;
	}

	return flag;
}


Boolean isWinner (Stats players[], int player) {
	Boolean isWin = FALSE;

	if (players[player].numHits == 17) 
		isWin = TRUE;

	return isWin;
}


Coordinate generatePosition (int direction, int length) {
	Coordinate position;

	if (direction == HORIZONTAL) {
		position.row    = getRandomNumber (0, ROWS);
		position.column = getRandomNumber (0, COLS - length);
	} else { /* VERTICAL */
		position.row    = getRandomNumber (0, ROWS - length);
		position.column = getRandomNumber (0, COLS);
	}

	return position;
}

/
Coordinate getTarget (void) {
	Coordinate target;

	fflush (stdin);

	printf ("> Enter Target (ex. > 3 4):\n");
	printf ("> ");
	scanf ("%d %d", &target.row, &target.column);

	return target;
}


short checkShot (Cell gameBoard[][COLS], Coordinate target) {
	int hit = -2;

	switch (gameBoard[target.row][target.column].symbol) {
		/* miss */ 
		case WATER: 
			hit = 0;
			break;

		/* hit */
		case CARRIER: 
		case BATTLESHIP:
		case CRUISER:
		case SUBMARINE:
		case DESTROYER:
			hit = 1;
			break;

		case HIT:
		case MISS:
		default:
			hit = -1;
			break;
	}	

	return hit;
}


int getRandomNumber (int lowest, int highest) {
	if (lowest == 0)
		return rand () % ++highest;
	
	if (lowest > 0)
		return rand () % ++highest + lowest;
}

int main (void)
{
	Stats players[2] = {{0, 0, 0, 0.0}, {0, 0, 0, 0.0}};

	Cell playerOneGameBoard[ROWS][COLS];       
	Cell playerTwoGameBoard[ROWS][COLS];       

	Coordinate target;             
	Coordinate targetTemp;         
	Coordinate targetOrigin;       
	Coordinate targetAI;           

	WaterCraft ship[NUM_OF_SHIPS] = {{'c', 5, "Carrier"}, 
	                                 {'b', 4, "Battleship"}, 
	                                 {'r', 3, "Cruiser"}, 
	                                 {'s', 3, "Submarine"}, 
	                                 {'d', 2, "Destroyer"}};

	Boolean    huntMode       = TRUE;                     
	Boolean    targetMode     = FALSE;                    
	Boolean    flipper        = TRUE;	                  
	Boolean    cardinals[4]   = {TRUE, TRUE, TRUE, TRUE}; 
	Boolean    hasAShipSunked = FALSE;                    


	short sunkShip[2][NUM_OF_SHIPS] = {{5, 4, 3, 3, 2},    
	                                   {5, 4, 3, 3, 2}};  

	short player  = 0;	           
	short shot    = 0;             
	int   option  = 0;             
	int   north   = 0,             
		  south   = 0,             
		  east    = 0,             
		  west    = 0;             
	int   i       = 0,             
		  counter = 1;             

	char  shipSymbol = '\0';       
	
	FILE *outStream = NULL;        

	
	outStream = fopen (LOG_FILE_NAME, "w");

	srand ((unsigned int) time (NULL));

	
	welcomeScreen ();
	systemMessage ("                            Hit <ENTER> to continue!\n");
	system ("cls");

	initializeGameBoard (playerOneGameBoard);
	initializeGameBoard (playerTwoGameBoard);

	
	printf ("> Please select from the following menu:\n");
	printf ("> [1] Manually\n");
	printf ("> [2] Randomly\n");
	printf ("> Enter Option: ");
	scanf ("%d", &option);
	
	switch (option) {
		case 1: manuallyPlaceShipsOnGameBoard (playerOneGameBoard, ship);
	            break;
		case 2: randomlyPlaceShipsOnGameBoard (playerOneGameBoard, ship);
				break;
	}


	
	randomlyPlaceShipsOnGameBoard (playerTwoGameBoard, ship);
	printf ("> Player 2 (Computer's) board has been generated.\n");

	
	player = getRandomNumber (0, 1);
	printf ("> Player %d has been randomly selected to go first.\n", player + 1);
	systemMessage ("> Hit <ENTER> to continue!\n");
	system ("cls");

	
	
	while (TRUE) {

		
		fprintf (outStream, "Player %d's turn.\n", player + 1);


		switch (player) {

			case PLAYER_ONE: 
				
				printf ("> Player 2's Board:\n");
				printGameBoard (playerTwoGameBoard, FALSE);
				printf ("> PLAYER 1'S TURN\n");

				
				do {
					target = getTarget (); 
					shot = checkShot (playerTwoGameBoard, target);
					
					
					if (shot == -1) 
						printf ("> Try inputting another target!\n");

				} while (shot == -1);

				
				shipSymbol = playerTwoGameBoard[target.row][target.column].symbol;
				break;

			case PLAYER_TWO: 

				printf ("> Player 1's Board:\n");
				printGameBoard (playerOneGameBoard, TRUE);
				printf ("> COMPUTER'S TURN\n");

				
				if (hasAShipSunked) {
					hasAShipSunked = FALSE;
					targetMode = FALSE;
					huntMode = TRUE;
				}
				
				
				if (targetMode) {
					
					target = targetAI;

					do {
						if (cardinals[NORTH]) {        /* NORTH */
							target.row = north;
						} else if (cardinals[SOUTH]) { /* SOUTH */
							target.row = south;
						} else if (cardinals[WEST]) {  /* WEST */
							target.column = west;
						} else if (cardinals[EAST]) {  /* EAST */
							target.column = east;
						} else if (!cardinals[NORTH] && !cardinals[SOUTH] && 
						           !cardinals[WEST]  && !cardinals[EAST]  && 
								   !hasAShipSunked) {

							target = targetOrigin;
							targetTemp = target;

							
							north = target.row - counter;
							targetTemp.row = north;

							
							if (checkShot (playerOneGameBoard, targetTemp) != -1 && north >= 0) {
								cardinals[NORTH] = TRUE;
							}

							targetTemp = target;
							south = target.row + counter;
							targetTemp.row = south;

							
							if (checkShot (playerOneGameBoard, targetTemp) != -1 && south <= 9) {
								cardinals[SOUTH] = TRUE;
							}

							targetTemp = target;
							west = target.column - counter;
							targetTemp.column = west;

							
							if (checkShot (playerOneGameBoard, targetTemp) != -1 && west >= 0) {
								cardinals[WEST] = TRUE;
							}

							targetTemp = target;
							east = target.column + counter;
							targetTemp.column = east;

							
							if (checkShot (playerOneGameBoard, targetTemp) != -1 && east <= 9) {
								cardinals[EAST] = TRUE;
							}

							
							counter++;

						} else  {
							
							targetMode = FALSE;
							huntMode = TRUE;
							break;
						}
						
						
						shot = checkShot (playerOneGameBoard, target);

					} while (shot == -1 && targetMode == TRUE);

					
					if (shot == 1 && huntMode == FALSE) {
						for (i = 0; i < 4; i++) {
							if (flipper == FALSE)
								cardinals[i] = FALSE;

							if (cardinals[i] == flipper) 
								flipper = FALSE;
						}
					} else {
						for (i = 0; i < 4; i++) {
							if (flipper == TRUE && cardinals[i] != FALSE) {
								cardinals[i] = FALSE;
								break;
							}
						}
					}

					
					flipper = TRUE;
				}

				if (huntMode) {	

					
					counter = 1;
					flipper = TRUE;
					for (i = 0; i < 4; i++)
						cardinals[i] = TRUE;

					
					do {
						target.row = getRandomNumber (0, 9);
						target.column = getRandomNumber (0, 9);

						
						shot = checkShot (playerOneGameBoard, target);
					} while (shot == -1);

					
					if (shot == 1) targetOrigin = target;
				}

				
				if (shot == 1) {

					
					if (!cardinals[NORTH] && !cardinals[SOUTH] && 
						!cardinals[WEST]  && !cardinals[EAST]  && 
						!hasAShipSunked) { target = targetOrigin; }

					
					huntMode = FALSE;
					targetMode = TRUE;
					targetAI = target;

					
					if (cardinals[NORTH] == TRUE) {  /* NORTH */
						north = (targetAI.row - 1);
						checkBoundsOfCardinal (cardinals, north, NORTH);
						targetTemp = target;
						targetTemp.row = north;
						if (checkShot (playerOneGameBoard, targetTemp) == -1)
							cardinals[NORTH] = FALSE;
					}
					
					if (cardinals[SOUTH] == TRUE) {  /* SOUTH */
						south = targetAI.row + 1;
						checkBoundsOfCardinal (cardinals, south, SOUTH);
						targetTemp = target;
						targetTemp.row = south;
						if (checkShot (playerOneGameBoard, targetTemp) == -1)
							cardinals[SOUTH] = FALSE;
					}

					if (cardinals[WEST] == TRUE) {   /* WEST */
						west  = targetAI.column - 1;
						checkBoundsOfCardinal (cardinals, west, WEST);
						targetTemp = target;
						targetTemp.column = west;
						if (checkShot (playerOneGameBoard, targetTemp) == -1)
							cardinals[WEST] = FALSE;
					}

					if (cardinals[EAST] == TRUE) {   /* EAST */
						east  = targetAI.column + 1;
						checkBoundsOfCardinal (cardinals, east, EAST);
						targetTemp = target;
						targetTemp.column = east;
						if (checkShot (playerOneGameBoard, targetTemp) == -1)
							cardinals[EAST] = FALSE;
					}
				}

				
				shipSymbol = playerOneGameBoard[target.row][target.column].symbol;
				break;
		}

		
		if (shot == 1) { /* HIT */
			printf ("> %d, %d is a hit!\n", target.row, target.column);

			
			fprintf (outStream, "%d, %d is a hit!\n", target.row, target.column);

			
			players[player].numHits++;

			
			if (player == 1)  
				hasAShipSunked = checkSunkShip (sunkShip, !player, shipSymbol, outStream);
			else
				checkSunkShip (sunkShip, !player, shipSymbol, outStream);

		} else {        
			printf ("> %d, %d is a miss!\n", target.row, target.column);

			
			fprintf (outStream, "%d, %d is a miss!\n", target.row, target.column);
			players[player].numMisses++;
		}
		
		if (player == 0) 
			updateGameBoard (playerTwoGameBoard, target);
		else               
			updateGameBoard (playerOneGameBoard, target);


		if (isWinner (players, player)) {
			printf ("\n> Player %d wins!\n", player + 1);

			fprintf (outStream, "\n>>>>> Player %d wins! <<<<<\n", player + 1);
			break;
		}

		systemMessage ("> Hit <ENTER> to continue!\n");

		player = !player;	

		system ("cls");
	}


	players[0].totalShots = players[0].numHits + players[0].numMisses;
	players[0].hitMissRatio = ((double) players[0].numHits/(double) players[0].numMisses) * 100;
	players[1].totalShots = players[1].numHits + players[1].numMisses;
	players[1].hitMissRatio = ((double) players[1].numHits/(double) players[1].numMisses) * 100;
	fprintf (outStream, "+===================================================\n");
	fprintf (outStream, "|                    PLAYER STATS                   \n");
	fprintf (outStream, "+---------------------------------------------------\n");
	fprintf (outStream, "| PLAYER 1 : %d hits                                \n", players[0].numHits);
	fprintf (outStream, "|            %d misses                              \n", players[0].numMisses);
	fprintf (outStream, "|            %d total shots                         \n", players[0].totalShots);
	fprintf (outStream, "|            %.2lf%% hit/miss ratio                 \n", players[0].hitMissRatio);
	fprintf (outStream, "| PLAYER 2 : %d hits                                \n", players[1].numHits);
	fprintf (outStream, "|            %d misses                              \n", players[1].numMisses);
	fprintf (outStream, "|            %d total shots                         \n", players[1].totalShots);
	fprintf (outStream, "|            %.2lf%% hit/miss ratio                 \n", players[1].hitMissRatio);
	fprintf (outStream, "+===================================================");

	fclose (outStream);
	return 0;
}