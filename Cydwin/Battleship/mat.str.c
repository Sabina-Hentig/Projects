#include <stdio.h>

int m[10][10];
int n=3;

void printm(){
	int l,c;
	 printf("\tName \tAuthor \tPages");
        printf("\n");

        for(l=0 ; l < 3 ; l++ ){
            printf("%d",l+1);
            for(c=0 ; c < 3 ; c++ )
            printf("\n");
        }
}
 struct Book(){
	 char Name[50];
	 char Author[50];
	 int Pages[4];
 }


main (){
	printm();
}