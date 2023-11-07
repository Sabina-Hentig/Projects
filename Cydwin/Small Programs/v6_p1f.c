main(){
	float i, y, x, s, n; 
    scanf("%f", &n);
	printf("n=%f\n", n);

	for (i=1;i<=n;i++){
		x=i+1;
		printf("x=%f\n", x);
		y=i/x;
		printf("y=%f\n", y);
		s=s+y;
		printf("s=%f\n", s);
	}

printf("%f\n", s);
    
}