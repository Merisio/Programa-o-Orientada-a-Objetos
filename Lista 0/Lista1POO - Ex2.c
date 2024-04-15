#include <stdio.h>
#include <stdlib.h>

void programa(int n){
    int alg, numalg = 0, mult2 = 0, mult3 = 0;

    while (n > 0){0,
    
        alg = n % 10;
        numalg++;

        if (alg % 2 == 0)
            mult2++;
        if (alg % 3 == 0)
            mult3++;

        n = n/10;
    }

    printf("\nnumero de algarismos: %d", numalg);
    printf("\nalgarismos multiplos de 2: %d", mult2);
    printf("\nalgarismos multiplos de 2: %d\n", mult3);
}

int main(){
    int n;

    printf ("digite um numero inteiro: ");
    scanf ("%d", &n);

    printf("numero informado: %d\n", n);

    programa(n);

    return 0;
}