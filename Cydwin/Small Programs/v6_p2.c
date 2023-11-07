main(){
	int i, n, x, c, a;
	printf("Cate numere doriti sa dati? \n");
	scanf("%d", &n);
	printf("Dati %d numere: \n", n);
	scanf("%d", &x);
	a=n;
	
	while (n>0){
		scanf("%d", &x);
		if (x % 2 == 1){
			printf("nr. impar.\n");
			c++;
		} else {
			printf("nr. par.\n");
		}
	
    }
	if(a == c){
		printf("Toate nr. sunt impare.\n");
	} else {
		printf("Nu toate nr. sunt impare.\n");
    }
	
}