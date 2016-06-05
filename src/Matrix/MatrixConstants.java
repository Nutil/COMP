/* Generated By:JJTree&JavaCC: Do not edit this line. MatrixConstants.java */
package Matrix;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface MatrixConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PLUS = 4;
  /** RegularExpression Id. */
  int MINUS = 5;
  /** RegularExpression Id. */
  int MULTIPLY = 6;
  /** RegularExpression Id. */
  int TRANSPOSE = 7;
  /** RegularExpression Id. */
  int EQUALS = 8;
  /** RegularExpression Id. */
  int DOUBLE_POINT = 9;
  /** RegularExpression Id. */
  int BRACKET_LEFT = 10;
  /** RegularExpression Id. */
  int BRACKET_RIGHT = 11;
  /** RegularExpression Id. */
  int PARENTHESIS_LEFT = 12;
  /** RegularExpression Id. */
  int PARENTHESIS_RIGHT = 13;
  /** RegularExpression Id. */
  int INPUT = 14;
  /** RegularExpression Id. */
  int OUTPUT = 15;
  /** RegularExpression Id. */
  int beforeStart = 16;
  /** RegularExpression Id. */
  int SYMBOL = 17;
  /** RegularExpression Id. */
  int TYPE = 18;
  /** RegularExpression Id. */
  int DOUBLE = 19;
  /** RegularExpression Id. */
  int CONSTANT = 20;
  /** RegularExpression Id. */
  int DIGIT = 21;
  /** RegularExpression Id. */
  int LF = 22;
  /** RegularExpression Id. */
  int matrixDSL = 23;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"\\\'\"",
    "\"=\"",
    "\":\"",
    "\"[\"",
    "\"]\"",
    "\"(\"",
    "\")\"",
    "\"input\"",
    "\"output\"",
    "\".+?(?=@MatrixDSL)\"",
    "<SYMBOL>",
    "<TYPE>",
    "<DOUBLE>",
    "<CONSTANT>",
    "<DIGIT>",
    "\"\\n\"",
    "\"@MatrixDSL\"",
    "\";\"",
    "\",\"",
  };

}
