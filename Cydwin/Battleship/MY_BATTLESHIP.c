#include <stdio.h>
#include <stdlib.h>

void x1(int x[][5]){
	int l, c;
	for(l=0;l<5;l++)
	for(c=0;c<5;c++)
		x[l][c]=-1;
}

void x2(int x[][5]){

    int l, c;

        printf("\t1 \t2 \t3 \t4 \t5 ");
        printf("\n");

        for(l=0 ; l < 5 ; l++ ){
            printf("%d",l+1);
            for(c=0 ; c < 5 ; c++ ){
                if(x[l][c]==-1){
                    printf("\t-");
                }else if(x[l][c]==0){
                    printf("\tM");
                }else if(x[l][c]==1){
                    printf("\tH");
                }

            }
            printf("\n");
        }

    }

void Ships(int ship1[][5]){
        srand(time( NULL ));
        int ship;

        for(ship=0 ; ship < 6 ; ship++){
            ship1[ship][0]= rand()%5;
            ship1[ship][1]= rand()%5;
			ship1[ship][2]= rand()%5;
			ship1[ship][3]= rand()%5;
			ship1[ship][4]= rand()%5;

        }
    }

void shot(int s[2])
{

        printf("Linie: ");
        scanf("%d",&s[0]);
        s[0]--;

        printf("Coloana: ");
        scanf("%d",&s[1]);
        s[1]--;

}

int hit(int s[2], int ship1[][5])
{
    int ship;

        for(ship=0 ; ship < 6 ; ship++){
            if( s[0]==ship1[ship][0] && s[1]==ship1[ship][1]){
                printf("AI LOVIT O NAVA CU COORDONATELE (%d,%d)\n",s[0]+1,s[1]+1);
                return 1;
            }
        }
       return 0;
    }

void newx(int s[2], int ship1[][5], int x[][5]){
        if(hit(s,ship1))
            x[s[0]][s[1]]=1;
        else
            x[s[0]][s[1]]=0;
    }

int main() {
        int x[5][5];
        int ship1[6][5];
        int s[2];
        int a=0,
            h=0;
			
		printf("\n");
        printf("BATTLESHIP!!!\n");
		
        x1(x);
        Ships(ship1);

        printf("\n");

        do{
            x2(x);
            shot(s);
            a++;

            if(hit(s,ship1)){
                h++;
            }
            
            newx(s,ship1,x);
        }while(h!=6);

        printf("\n\n\nGame Over.\n");
        printf("Felicitari. Ai gasit toate navele in %d incercari\n", a);
        x2(x);
    }