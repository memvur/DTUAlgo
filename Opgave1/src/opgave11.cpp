#include <iostream>
#include <vector>

static int A[] = { 2, 0, 4, 3 };
static int B[] = { 2, 1, 4, 3 };
static int C[] = { 7, 3, 2, 5, 8, 1, 4, 0 };

/*
  OPpgave 8.1
*/
int find_missing(int *A, int n){
  
  /*
    c0 time for memory alloc
    n time for setting all to false
  */
  std::vector<bool> found(n, false);


  /*
    n-1 for running through the whole array
  */
  for (int i = 0; i < n-1; i++){
    found[A[i]] = true;
  }

  /*
    n for running through the whole array
  */
  for (int i = 0; i < found.size(); i++){
    if (!found[i]){
      return i;
    }
  }
  return 0;
}

/*
OPpgave 8.2
*/
int find_missing_2(int *A, int n){

  /*
    only extra veriables
    bool: 1 byte
    int : 4 bytes
    total: 5 bytes - always
  */
  int expected_lowest = 0;
  bool found = false;


  for (int i = 0; i < n - 1; i++){

    found = false;

    for (int i = 0; i < n - 1; i++){
      if (A[i] == expected_lowest){
        found = true;
        expected_lowest++;
        break;
      }

    }
    if (!found){
      return expected_lowest;
    }

  }
  
  return 0;
}


/*
OPpgave 8.3
*/
int find_missing_3(int *A, int n){
  int sum_all = (n*(n - 1)) / 2;
  int sum_real = 0;
  for (int i = 0; i < n - 1; i++){
    sum_real += A[i];
  }
  return sum_all-sum_real;
}


int main(){

  /* Using the first algorithm
     Complexity: O(n)
     Memory    : O(n)
  */
  std::cout << "Missing number in A: " << find_missing(A, 5) << std::endl;
  std::cout << "Missing number in B: " << find_missing(B, 5) << std::endl;
  std::cout << "Missing number in C: " << find_missing(C, 9) << std::endl;

  /* Using the second algorithm 
     complexity: O(n^2)
     memory    : O(1)
  */
  std::cout << "Missing number in A: " << find_missing_2(A, 5) << std::endl;
  std::cout << "Missing number in B: " << find_missing_2(B, 5) << std::endl;
  std::cout << "Missing number in C: " << find_missing_2(C, 9) << std::endl;

  /* Using the third algorithm
  complexity: O(n)
  memory    : O(1)
  */
  std::cout << "Missing number in A: " << find_missing_3(A, 5) << std::endl;
  std::cout << "Missing number in B: " << find_missing_3(B, 5) << std::endl;
  std::cout << "Missing number in C: " << find_missing_3(C, 9) << std::endl;


  return 0;
}