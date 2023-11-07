
main(){
	int i, s, s1, n; 
    scanf("%d", &n);
	printf("n=%d\n", n);
	i=1;
	s=1;
	
	for (i=2; i<=n; i++){
		s=(i-1)*i;
		s1=s1+s;
		printf("i=%d\n", i);
	}

	
printf("%d\n", s1);
    

}