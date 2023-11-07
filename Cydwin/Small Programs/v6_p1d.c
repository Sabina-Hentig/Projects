main(){
	int i, s, f, n; 
    scanf("%d", &n);
	printf("n=%d\n", n);
    f=1;
	s=0;
	
	for (i=1;i<=n;i++){
		f=f*i;
		s+=f;
	}

printf("%d\n", s);
    
}