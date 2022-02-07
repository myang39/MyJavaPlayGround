package c3ai;

import java.util.Arrays;

public class CountVowel {
  public static void main(String[] args) {
//    System.out.println(maxOf(1, 2, 3));
//    System.out.println(maxOf(1, 2));
//    System.out.println(1000_000_007);

    int n = 144;

    System.out.println(countVowelPermutation(n));

    System.out.println();

    System.out.println(countVowelPermutationII(n));
  }

  public static int countVowelPermutation(int n) {
    int mod = 1000_000_007;
    // dp[i][j] = # of strings of length i that ends with the
    //  j-th vowel,
    long[][] dp = new long[n + 1][5];
    // 0 1 2 3 4
    // a e i o u
    for (int i = 0; i < 5; i++) {
      dp[1][i] = 1;
    }

    for (int i = 2; i <= n; i++) {
      // case 0 :  // a, can be preceded by e, i, u
      dp[i][0] = (dp[i-1][1] + dp[i-1][2] + dp[i-1][4]) % mod;

      // case 1: // e, can be proceded by a, i
      dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % mod;

      // case 2: // i, can be proceded by e and o
      dp[i][2] = (dp[i-1][1] + dp[i-1][3]) % mod;

      // case 3: // o, can be proceded by i
      dp[i][3] = dp[i-1][2] % mod;

      // case 4: // u, can be proceded by i and o
      dp[i][4] = (dp[i-1][2] + dp[i-1][3]) % mod;
    }

    long sum = 0;
    for (int i = 0; i < 5; i++) {
      sum = sum + dp[n][i];
      sum %= mod;
      System.out.println(sum);
    }
//    System.out.println(Arrays.deepToString(dp).replace("],", "\n"));
    return (int)sum;


  }

  public static int countVowelPermutationII(int n) {
    int MOD = 1000_000_007;

    long[][] dp = new long[n + 1][5]; // dp[i][j] := a string of length 'i' ends with a vowel represented by index 'j'

    // vowel & index mapping
    // a: 0
    // e: 1
    // i: 2
    // o: 3
    // u: 4

    // Initialize dp
    // 'a': dp[1][0] = 1
    // 'e': dp[1][1] = 1
    // 'i': dp[1][2] = 1
    // 'o': dp[1][3] = 1
    // 'u': dp[1][4] = 1
    for(int i = 0; i < 5; i ++){
      dp[1][i] = 1;
    }

    // Each vowel 'a' may only be followed by an 'e'.
    // Each vowel 'e' may only be followed by an 'a' or an 'i'.
    // Each vowel 'i' may not be followed by another 'i'.
    // Each vowel 'o' may only be followed by an 'i' or a 'u'.
    // Each vowel 'u' may only be followed by an 'a'.
    // === In other words:
    // 'a' can be followed by {'e'}
    // 'e' can be followed by {'a', 'i'}
    // 'i' can be followed by {'a', 'e', 'o', 'u'}
    // 'o' can be followed by {'i', 'u'}
    // 'u' can be followed by {'a'}
    // === In other words:
    // 'a' can follow {'e', 'i', 'u'}
    // 'e' can follow {'a', 'i'}
    // 'i' can follow {'e', 'o'}
    // 'o' can follow {'i'}
    // 'u' can follow {'i', 'o'}


    // State transition
    for(int i = 2; i <= n; i ++){
      dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % MOD; // 'a' can follow {'e', 'i', 'u'}
      dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD; // 'e' can follow {'a', 'i'}
      dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % MOD; // 'i' can follow {'e', 'o'}
      dp[i][3] = (dp[i - 1][2]) % MOD; // 'o' can follow {'i'}
      dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % MOD; // 'u' can follow {'i', 'o'}
    }

    long count = 0;

    for(long d : dp[n]){
      count += d;
      count %= MOD;
      System.out.println(count);
    }

//    System.out.println(Arrays.deepToString(dp).replace("],", "\n"));
    return (int) count;
  }

  private static int maxOf(int... numbers) {
    int max = 0;
    for (int i : numbers) {
      max = Math.max(max, i);
    }
    return max;
  }
}
