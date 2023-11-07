
main(){
	int i, s, s1, n; 
    scanf("%d", &n);
	printf("n=%d\n", n);
	i=1;
	s=1;
	
	for (i=1; i<=n; i++){
		s=s*(i*3);
		printf("i=%d\n", i);
	}

	
printf("%d\n", s);
    

}