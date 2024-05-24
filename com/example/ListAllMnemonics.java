import java.util.*;
import java.io.*;

/**
 * Uses recursion to get telephone Mnemonics list.
 *
 * @author Kent Gatera
 * @version 1.0
 * @since 2024-05-06
 */

// ListAllMnemonics class
public final class ListAllMnemonics {

  /**
   * Private constructor to prevent instantiation.
   * 
   * @return
   */
  private void ListAllMnemonics() {
    throw new UnsupportedOperationException("Cannot instantiate");
  }

  public static void main(final String[] args) {
    final File inputFile = new File("input.txt");
    final String outputFile = "output.txt";
    List<String> MnemonicsOutputList = new ArrayList<>();
    /*
     * - This is where the dictionary, for Mnemonics goes.
     * - This is where the call for Mnemonics go.
     */
    Map<String, String> lettersForDigitDictionary = new HashMap<>();
    lettersForDigitDictionary.put("1", " ");
    lettersForDigitDictionary.put("2", "ABC");
    lettersForDigitDictionary.put("3", "DEF");
    lettersForDigitDictionary.put("4", "GHI");
    lettersForDigitDictionary.put("5", "JKL");
    lettersForDigitDictionary.put("6", "MNO");
    lettersForDigitDictionary.put("7", "PQRS");
    lettersForDigitDictionary.put("8", "TUV");
    lettersForDigitDictionary.put("9", "WXYZ");
    lettersForDigitDictionary.put("0", " ");
    System.out.println("\t\t\tListAllMnemonics Assign-03 Swift \n");
    System.out.println("\t This program will list all of a number's possible mnemonics.\n\t\t\t\ti.e: 343");
    System.out.println("\n\t\t Press 1 (console)\t Press 2 (Input file).");
    Scanner in = new Scanner(System.in);
    String userChoiceAsStr = in.nextLine();
    try {
      int userChoiceAsInt = Integer.valueOf(userChoiceAsStr);
      if (userChoiceAsInt == 1){
        Scanner sc = new Scanner(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        try {
          System.out.print("Enter the mnemonic: ");
          String userInputMnemonic = in.nextLine();
          MnemonicsOutputList = ListAllMnemonics(userInputMnemonic, MnemonicsOutputList, lettersForDigitDictionary);
          String[] arrayToPrint = MnemonicsOutputList.toArray(new String[0]);
          String outputString = userInputMnemonic + ": " + Arrays.toString(arrayToPrint) + "\n";
          writer.write(outputString);
          System.out.println("Done.");
        } catch (Exception e) {
          System.out.println("Error processing line Input");
        }
        writer.close();
        in.close();
      } else if (userChoiceAsInt == 2) {
        try {
          Scanner sc = new Scanner(inputFile);
          FileWriter fileWriter = new FileWriter(outputFile);
          BufferedWriter writer = new BufferedWriter(fileWriter);
          while (sc.hasNextLine()) {
            int ErrorLineIter = 1;
            try {
              String mnemonicNumAsStr = sc.nextLine();
              MnemonicsOutputList = ListAllMnemonics(mnemonicNumAsStr, MnemonicsOutputList, lettersForDigitDictionary);
              String[] arrayToPrint = MnemonicsOutputList.toArray(new String[0]);
              writer.write(mnemonicNumAsStr+ ": ");
              writer.write(Arrays.toString(arrayToPrint) + "\n");
              ErrorLineIter++;
            } catch (Exception e) {
              System.out.println("Error processing line: " + ErrorLineIter);
            }
          }
          writer.close();
          sc.close();
          System.out.println("Done.");
        } catch (Exception e) {
          System.out.println("Invalid input path!");
        }
      } else {
        System.out.println("Please enter a listed choice.");
      }
    } catch (Exception e) {
      // TODO: handle errors.
      System.out.println("Please enter a valid choice.");
    }

    // Create a scanner and writer to read and print.
    
  }

  /**
   * Recursive method that gets the MnemonicsCombos of a number.
   *
   * @param someNumString       is the 3 letter umber we send the console.
   * @param mnemonicsOutputList
   * @param MnemonicsCombos     is the combination we get back.
   * @param AlphaNumMap         is the dictionary that stores the map
   */
  private static List<String> ListAllMnemonics(String someNumString, List<String> mnemonicsOutputList,
      Map<String, String> alphNumMap) {
    // In the case that we hit the end of the string.
    if (someNumString.length() == 0) {
      // We return an empty list.
      return Collections.singletonList("");
    } else {
      // Create a temporary list to keep the finished combinations.
      List<String> mnemonicsCombos = new ArrayList<>();
      // Taking the first letter in the string passed.
      char currentNumFromString = someNumString.charAt(0);
      // We get the dictionary definition of the key as a string.
      String dictDefinition = alphNumMap.get(String.valueOf(currentNumFromString));
      for (char frontLetter : dictDefinition.toCharArray()) {
        // For each of those characters in the string, we add them to a list of all
        // possible combinations.
        List<String> listOfSuffixes = ListAllMnemonics(someNumString.substring(1), mnemonicsCombos, alphNumMap);
        for (String suffixCombination : listOfSuffixes) {
          String newValue = frontLetter + suffixCombination;
          mnemonicsCombos.add(newValue);
        }
      }
      return mnemonicsCombos;
    }
  }
}
