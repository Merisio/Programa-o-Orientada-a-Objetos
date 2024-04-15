#include <stdio.h>
#include <stdlib.h>

float fatorial(int n){
    if (n == 1 || n == 0)
        return 1;
    else
        return n * fatorial(n - 1);
}

void calc_neperiano(float e, int n){
    for (int i = 0; i < n; i++)
        e = e + (1 / fatorial(i));

    printf ("%f", e);
}

int main(void){
    calc_neperiano(0, 15);

    return 0;
}
