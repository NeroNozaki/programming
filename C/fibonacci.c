#include <stdio.h>

int main() {
  int x = 0;
  int y = 1;
  int z = x + y;

  while (x >= 0){
    printf("%d\n", x);
    x = y;
    y = z;
    z = x + y;

  }
  return 0;
}
