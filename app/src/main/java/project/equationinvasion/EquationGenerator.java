package project.equationinvasion;

/**
 * Copyright 2015 Avium Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.widget.TextView;

import java.util.Random;

class EquationGenerator {

	private static final Random rand = new Random();
	private int expected;
	private int displayed;
	private static int addSubBase = 100;
	private static int multDivBase = 10;
	private static int levelOneAddSub = 10;

	interface Equation {
		void create();
	}

	private final TextView equation;
	private final TextView answer;
	private static int currentLevel;
	private final Equation[] equationGen;


	public EquationGenerator() {
		equation = Play.getEquationTextView();
		answer = Play.getAnswerTextView();
		currentLevel = Play.getCurrentLevel();
		equationGen = new Equation[] {
				/**
				 * DO NOT DELETE
				 * Level 1: 0-1
				 * Level 2: 2-3
				 * Level 3: 0-1
				 * Level 4: 4-7
				 * Level 5: 4-19
				 * Level 6: 20-83
				 * DO NOT DELETE
				 */
				new Equation() {//0
					public void create() {
						add();
					}
				},
				new Equation() {//1
					public void create() {
						subtract();
					}
				},
				new Equation() {//2
					public void create() {
						multiply();
					}
				},
				new Equation() {//3
					public void create() {
						divide();
					}
				},
				new Equation() {//4
					public void create() {
						addAdd();
					}
				},
				new Equation() {//5
					public void create() {
						addSubtract();
					}
				},
				new Equation() {//6
					public void create() {
						subtractAdd();
					}
				},
				new Equation() {//7
					public void create() {
						subtractSubtract();
					}
				},
				new Equation() {//8
					public void create() {
						addDivide();
					}
				},
				new Equation() {//9
					public void create() {
						addMultiply();
					}
				},
				new Equation() {//10
					public void create() {
						subtractDivide();
					}
				},
				new Equation() {//11
					public void create() {
						subtractMultiply();
					}
				},
				new Equation() {//12
					public void create() {
						divideAdd();
					}
				},
				new Equation() {//13
					public void create() {
						divideSubtract();
					}
				},
				new Equation() {//14
					public void create() {
						divideDivide();
					}
				},
				new Equation() {//15
					public void create() {
						divideMultiply();
					}
				},
				new Equation() {//16
					public void create() {
						multiplyAdd();
					}
				},
				new Equation() {//17
					public void create() {
						multiplySubtract();
					}
				},
				new Equation() {//18
					public void create() {
						multiplyDivide();
					}
				},
				new Equation() {//19
					public void create() {
						multiplyMultiply();
					}
				},
				new Equation() {//20
					public void create() {
						addAddAdd();
					}
				},
				new Equation() {//21
					public void create() {
						addAddSubtract();
					}
				},
				new Equation() {//22
					public void create() {
						addSubtractAdd();
					}
				},
				new Equation() {//23
					public void create() {
						addSubtractSubtract();
					}
				},
				new Equation() {//24
					public void create() {
						addAddDivide();
					}
				},
				new Equation() {//25
					public void create() {
						addAddMultiply();
					}
				},
				new Equation() {//26
					public void create() {
						addSubtractDivide();
					}
				},
				new Equation() {//27
					public void create() {
						addSubtractMultiply();
					}
				},
				new Equation() {//28
					public void create() {
						addDivideAdd();
					}
				},
				new Equation() {//29
					public void create() {
						addDivideSubtract();
					}
				},
				new Equation() {//30
					public void create() {
						addDivideDivide();
					}
				},
				new Equation() {//31
					public void create() {
						addDivideMultiply();
					}
				},
				new Equation() {///32
					public void create() {
						addMultiplyAdd();
					}
				},
				new Equation() {//33
					public void create() {
						addMultiplySubtract();
					}
				},
				new Equation() {//34
					public void create() {
						addMultiplyDivide();
					}
				},
				new Equation() {//35
					public void create() {
						addMultiplyMultiply();
					}
				},
				new Equation() {//36
					public void create() {
						subtractAddAdd();
					}
				},
				new Equation() {//37
					public void create() {
						subtractAddSubtract();
					}
				},
				new Equation() {//38
					public void create() {
						subtractSubtractAdd();
					}
				},
				new Equation() {//39
					public void create() {
						subtractSubtractSubtract();
					}
				},
				new Equation() {//40
					public void create() {
						subtractAddDivide();
					}
				},
				new Equation() {//41
					public void create() {
						subtractAddMultiply();
					}
				},
				new Equation() {//42
					public void create() {
						subtractSubtractDivide();
					}
				},
				new Equation() {//43
					public void create() {
						subtractSubtractMultiply();
					}
				},
				new Equation() {//44
					public void create() {
						subtractDivideAdd();
					}
				},
				new Equation() {//45
					public void create() {
						subtractDivideSubtract();
					}
				},
				new Equation() {//46
					public void create() {
						subtractDivideDivide();
					}
				},
				new Equation() {//47
					public void create() {
						subtractDivideMultiply();
					}
				},
				new Equation() {//48
					public void create() {
						subtractMultiplyAdd();
					}
				},
				new Equation() {//49
					public void create() {
						subtractMultiplySubtract();
					}
				},
				new Equation() {//50
					public void create() {
						subtractMultiplyDivide();
					}
				},
				new Equation() {//51
					public void create() {
						subtractMultiplyMultiply();
					}
				},
				new Equation() {//52
					public void create() {
						divideAddAdd();
					}
				},
				new Equation() {//53
					public void create() {
						divideAddSubtract();
					}
				},
				new Equation() {//54
					public void create() {
						divideSubtractAdd();
					}
				},
				new Equation() {//55
					public void create() {
						divideSubtractSubtract();
					}
				},
				new Equation() {//56
					public void create() {
						divideAddDivide();
					}
				},
				new Equation() {//57
					public void create() {
						divideAddMultiply();
					}
				},
				new Equation() {//58
					public void create() {
						divideSubtractDivide();
					}
				},
				new Equation() {//59
					public void create() {
						divideSubtractMultiply();
					}
				},
				new Equation() {//60
					public void create() {
						divideDivideAdd();
					}
				},
				new Equation() {//61
					public void create() {
						divideDivideSubtract();
					}
				},
				new Equation() {//62
					public void create() {
						divideDivideDivide();
					}
				},
				new Equation() {//63
					public void create() {
						divideDivideMultiply();
					}
				},
				new Equation() {//64
					public void create() {
						divideMultiplyAdd();
					}
				},
				new Equation() {//65
					public void create() {
						divideMultiplySubtract();
					}
				},
				new Equation() {//66
					public void create() {
						divideMultiplyDivide();
					}
				},
				new Equation() {//67
					public void create() {
						divideMultiplyMultiply();
					}
				},
				new Equation() {//68
					public void create() {
						multiplyAddAdd();
					}
				},
				new Equation() {//69
					public void create() {
						multiplyAddSubtract();
					}
				},
				new Equation() {//70
					public void create() {
						multiplySubtractAdd();
					}
				},
				new Equation() {//71
					public void create() {
						multiplySubtractSubtract();
					}
				},
				new Equation() {//72
					public void create() {
						multiplyAddDivide();
					}
				},
				new Equation() {//73
					public void create() {
						multiplyAddMultiply();
					}
				},
				new Equation() {//74
					public void create() {
						multiplySubtractDivide();
					}
				},
				new Equation() {//75
					public void create() {
						multiplySubtractMultiply();
					}
				},
				new Equation() {//76
					public void create() {
						multiplyDivideAdd();
					}
				},
				new Equation() {//77
					public void create() {
						multiplyDivideSubtract();
					}
				},
				new Equation() {//78
					public void create() {
						multiplyDivideDivide();
					}
				},
				new Equation() {//79
					public void create() {
						multiplyDivideMultiply();
					}
				},
				new Equation() {//80
					public void create() {
						multiplyMultiplyAdd();
					}
				},
				new Equation() {//81
					public void create() {
						multiplyMultiplySubtract();
					}
				},
				new Equation() {//82
					public void create() {
						multiplyMultiplyDivide();
					}
				},
				new Equation() {//83
					public void create() {
						multiplyMultiplyMultiply();
					}
				}
		};
	}

	/**
	 * Method for single operation: X + Y.
	 */
	private void add() {
		final int a, b;
		if (currentLevel == 3) {
			a = rand.nextInt(addSubBase);
			b = rand.nextInt(addSubBase);
		} else {
			a = rand.nextInt(levelOneAddSub);
			b = rand.nextInt(levelOneAddSub);
		}
		expected = a + b;
		equation.setText(a + " + " + b);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X + Y + Z
	 */
	private void addAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		expected = a + b + c;
		equation.setText(a + " + " + b + " + " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X + Y - Z
	 */
	private void addSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		expected = a + b - c;
		equation.setText(a + " + " + b + " - " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X + Y * Z
	 */
	private void addMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		expected = a + b * c;
		equation.setText(a + " + " + b + " * " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X + Y / Z
	 */
	private void addDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c;
				equation.setText(a + " + " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivide();
			}
		} else {
			addDivide();
		}
	}

	/**
	 * Method for three operations: W + X + Y + Z
	 */
	private void addAddAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a + b + c + d;
		equation.setText(a + " + " + b + " + " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X + Y - Z
	 */
	private void addAddSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a + b + c - d;
		equation.setText(a + " + " + b + " + " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X + Y / Z
	 */
	private void addAddDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (c != 0) {
			if (c % d == 0) {
				expected = a + b + c / d;
				equation.setText(a + " + " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addAddDivide();
			}
		} else {
			addAddDivide();
		}
	}

	/**
	 * Method for three operations: W + X + Y * Z
	 */
	private void addAddMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a + b + c * d;
		equation.setText(a + " + " + b + " + " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X - Y + Z
	 */
	private void addSubtractAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a + b - c + d;
		equation.setText(a + " + " + b + " - " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X - Y - Z
	 */
	private void addSubtractSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a + b - c - d;
		equation.setText(a + " + " + b + " - " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X - Y / Z
	 */
	private void addSubtractDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a + b - c / d;
				equation.setText(a + " + " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addSubtractDivide();
			}
		} else {
			addSubtractDivide();
		}
	}

	/**
	 * Method for three operations: W + X - Y * Z
	 */
	private void addSubtractMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a + b - c * d;
		equation.setText(a + " + " + b + " - " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X / Y + Z
	 */
	private void addDivideAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c + d;
				equation.setText(a + " + " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideAdd();
			}
		} else {
			addDivideAdd();
		}
	}

	/**
	 * Method for three operations: W + X / Y - Z
	 */
	private void addDivideSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c - d;
				equation.setText(a + " + " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideSubtract();
			}
		} else {
			addDivideSubtract();
		}
	}

	/**
	 * Method for three operations: W + X / Y / Z
	 */
	private void addDivideDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(9) + 1;
		if (c != 0 && d != 0) {
			if (b % c == 0 && (b / c) % d == 0) {
				expected = a + b / c / d;
				equation.setText(a + " + " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideDivide();
			}
		} else {
			addDivideDivide();
		}
	}

	/**
	 * Method for three operations: W + X / Y * Z
	 */
	private void addDivideMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(multDivBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a + b / c * d;
				equation.setText(a + " + " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addDivideMultiply();
			}
		} else {
			addDivideMultiply();
		}
	}

	/**
	 * Method for three operations: W + X * Y + Z
	 */
	private void addMultiplyAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		expected = a + b * c + d;
		equation.setText(a + " + " + b + " * " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X * Y - Z
	 */
	private void addMultiplySubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		expected = a + b * c - d;
		equation.setText(a + " + " + b + " * " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W + X * Y / Z
	 */
	private void addMultiplyDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if ((b * c) % d == 0) {
				expected = a + b * c / d;
				equation.setText(a + " + " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				addMultiplyDivide();
			}
		} else {
			addMultiplyDivide();
		}
	}

	/**
	 * Method for three operations: W + X * Y * Z
	 */
	private void addMultiplyMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a + b * c * d;
		equation.setText(a + " + " + b + " * " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for single operation: X - Y
	 */
	private void subtract() {
		final int a, b;
		if (currentLevel == 3) {
			a = rand.nextInt(addSubBase);
			b = rand.nextInt(addSubBase);
		} else {
			a = rand.nextInt(levelOneAddSub);
			b = rand.nextInt(levelOneAddSub);
		}
		expected = a - b;
		equation.setText(a + " - " + b);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X - Y + Z
	 */
	private void subtractAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		expected = a - b + c;
		equation.setText(a + " - " + b + " + " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X - Y - Z
	 */
	private void subtractSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		expected = a - b - c;
		equation.setText(a + " - " + b + " - " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X - Y * Z
	 */
	private void subtractMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		expected = a - b * c;
		equation.setText(a + " - " + b + " * " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X - Y / Z
	 */
	private void subtractDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c;
				equation.setText(a + " - " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivide();
			}
		} else {
			subtractDivide();
		}
	}

	/**
	 * Method for three operations: W - X + Y + Z
	 */
	private void subtractAddAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a - b + c + d;
		equation.setText(a + " - " + b + " + " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X + Y - Z
	 */
	private void subtractAddSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a - b + c - d;
		equation.setText(a + " - " + b + " + " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X + Y / Z
	 */
	private void subtractAddDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(9) + 1;
		if (c != 0) {
			if (c % d == 0) {
				expected = a - b + c / d;
				equation.setText(a + " - " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractAddDivide();
			}
		} else {
			subtractAddDivide();
		}
	}

	/**
	 * Method for three operations: W - X + Y * Z
	 */
	private void subtractAddMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a - b + c * d;
		equation.setText(a + " - " + b + " + " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X - Y + Z
	 */
	private void subtractSubtractAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a - b - c + d;
		equation.setText(a + " - " + b + " - " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X - Y - Z
	 */
	private void subtractSubtractSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a - b - c - d;
		equation.setText(a + " - " + b + " - " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X - Y / Z
	 */
	private void subtractSubtractDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a - b - c / d;
				equation.setText(a + " - " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractSubtractDivide();
			}
		} else {
			subtractSubtractDivide();
		}
	}

	/**
	 * Method for three operations: W - X - Y * Z
	 */
	private void subtractSubtractMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(addSubBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a - b - c * d;
		equation.setText(a + " - " + b + " - " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X / Y + Z 
	 */
	private void subtractDivideAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c + d;
				equation.setText(a + " - " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideAdd();
			}
		} else {
			subtractDivideAdd();
		}
	}

	/**
	 * Method for three operations: W - X / Y - Z 
	 */
	private void subtractDivideSubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c - d;
				equation.setText(a + " - " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideSubtract();
			}
		} else {
			subtractDivideSubtract();
		}
	}

	/**
	 * Method for three operations: W - X / Y / Z 
	 */
	private void subtractDivideDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(9) + 1;
		if (c != 0 && d != 0) {
			if (b % c == 0 && (b / c) % d == 0) {
				expected = a - b / c / d;
				equation.setText(a + " - " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideDivide();
			}
		} else {
			subtractDivideDivide();
		}
	}

	/**
	 * Method for three operations: W - X / Y * Z 
	 */
	private void subtractDivideMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(multDivBase);
		if (c != 0) {
			if (b % c == 0) {
				expected = a - b / c * d;
				equation.setText(a + " - " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractDivideMultiply();
			}
		} else {
			subtractDivideMultiply();
		}
	}

	/**
	 * Method for three operations: W - X * Y + Z 
	 */
	private void subtractMultiplyAdd() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		expected = a - b * c + d;
		equation.setText(a + " - " + b + " * " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X * Y - Z 
	 */
	private void subtractMultiplySubtract() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		expected = a - b * c - d;
		equation.setText(a + " - " + b + " * " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W - X * Y / Z 
	 */
	private void subtractMultiplyDivide() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if ((b * c) % d == 0) {
				expected = a - b * c / d;
				equation.setText(a + " - " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				subtractMultiplyDivide();
			}
		} else {
			subtractMultiplyDivide();
		}
	}

	/**
	 * Method for three operations: W - X * Y * Z 
	 */
	private void subtractMultiplyMultiply() {
		final int a = rand.nextInt(addSubBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a - b * c * d;
		equation.setText(a + " - " + b + " * " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X / Y
	 */
	private void divide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b;
				equation.setText(a + " / " + b);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divide();
			}
		} else {
			divide();
		}
	}

	/**
	 * Method for two operations: X / Y + Z 
	 */
	private void divideAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c;
				equation.setText(a + " / " + b + " + " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAdd();
			}
		} else {
			divideAdd();
		}

	}

	/**
	 * Method for two operations: X / Y - Z 
	 */
	private void divideSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c;
				equation.setText(a + " / " + b + " - " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtract();
			}
		} else {
			divideSubtract();
		}
	}

	/**
	 * Method for two operations: X / Y * Z 
	 */
	private void divideMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c;
				equation.setText(a + " / " + b + " * " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiply();
			}
		} else {
			divideMultiply();
		}
	}

	/**
	 * Method for two operations: X / Y / Z 
	 */
	private void divideDivide() {
		final int a = rand.nextInt(9) + 1;
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(9) + 1;
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c;
				equation.setText(a + " / " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivide();
			}
		} else {
			divideDivide();
		}
	}

	/**
	 * Method for three operations: W / X + Y + Z 
	 */
	private void divideAddAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c + d;
				equation.setText(a + " / " + b + " + " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddAdd();
			}
		} else {
			divideAddAdd();
		}
	}

	/**
	 * Method for three operations: W / X + Y - Z 
	 */
	private void divideAddSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c - d;
				equation.setText(a + " / " + b + " + " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddSubtract();
			}
		} else {
			divideAddSubtract();
		}
	}

	/**
	 * Method for three operations: W / X + Y / Z 
	 */
	private void divideAddDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (b != 0 && d != 0) {
			if (a % b == 0 && c % d == 0) {
				expected = a / b + c / d;
				equation.setText(a + " / " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddDivide();
			}
		} else {
			divideAddDivide();
		}
	}

	/**
	 * Method for three operations: W / X + Y * Z 
	 */
	private void divideAddMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b + c * d;
				equation.setText(a + " / " + b + " + " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideAddMultiply();
			}
		} else {
			divideAddMultiply();
		}
	}

	/**
	 * Method for three operations: W / X - Y + Z 
	 */
	private void divideSubtractAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c + d;
				equation.setText(a + " / " + b + " - " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractAdd();
			}
		} else {
			divideSubtractAdd();
		}
	}

	/**
	 * Method for three operations: W / X - Y - Z 
	 */
	private void divideSubtractSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c - d;
				equation.setText(a + " / " + b + " - " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractSubtract();
			}
		} else {
			divideSubtractSubtract();
		}
	}


	/**
	 * Method for three operations: W / X - Y / Z 
	 */
	private void divideSubtractDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (b != 0 && d != 0) {
			if (a % b == 0 && c % d == 0) {
				expected = a / b - c / d;
				equation.setText(a + " / " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractDivide();
			}
		} else {
			divideSubtractDivide();
		}
	}

	/**
	 * Method for three operations: W / X - Y * Z 
	 */
	private void divideSubtractMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b - c * d;
				equation.setText(a + " / " + b + " - " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideSubtractMultiply();
			}
		} else {
			divideSubtractMultiply();
		}
	}

	/**
	 * Method for three operations: W / X / Y + Z 
	 */
	private void divideDivideAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c + d;
				equation.setText(a + " / " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideAdd();
			}
		} else {
			divideDivideAdd();
		}
	}

	/**
	 * Method for three operations: W / X / Y - Z 
	 */
	private void divideDivideSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c - d;
				equation.setText(a + " / " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideSubtract();
			}
		} else {
			divideDivideSubtract();
		}
	}

	/**
	 * Method for three operations: W / X / Y / Z 
	 */
	private void divideDivideDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(9) + 1;
		if (b != 0 && c != 0 && d != 0) {
			if (a % b == 0 && (a / b) % c == 0 && (a / b / c) % d == 0) {
				expected = a / b / c / d;
				equation.setText(a + " / " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideDivide();
			}
		} else {
			divideDivideDivide();
		}
	}

	/**
	 * Method for three operations: W / X / Y * Z 
	 */
	private void divideDivideMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(multDivBase);
		if (b != 0 && c != 0) {
			if (a % b == 0 && (a / b) % c == 0) {
				expected = a / b / c * d;
				equation.setText(a + " / " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideDivideMultiply();
			}
		} else {
			divideDivideMultiply();
		}
	}

	/**
	 * Method for three operations: W / X * Y + Z 
	 */
	private void divideMultiplyAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c + d;
				equation.setText(a + " / " + b + " * " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplyAdd();
			}
		} else {
			divideMultiplyAdd();
		}
	}

	/**
	 * Method for three operations: W / X * Y - Z 
	 */
	private void divideMultiplySubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c - d;
				equation.setText(a + " / " + b + " * " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplySubtract();
			}
		} else {
			divideMultiplySubtract();
		}
	}

	/**
	 * Method for three operations: W / X * Y / Z 
	 */
	private void divideMultiplyDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (b != 0 && d != 0) {
			if (a % b == 0 && (a / b * c) % d == 0) {
				expected = a / b * c / d;
				equation.setText(a + " / " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplySubtract();
			}
		} else {
			divideMultiplySubtract();
		}
	}

	/**
	 * Method for three operations: W / X * Y * Z 
	 */
	private void divideMultiplyMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(9) + 1;
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		if (b != 0) {
			if (a % b == 0) {
				expected = a / b * c * d;
				equation.setText(a + " / " + b + " * " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				divideMultiplyMultiply();
			}
		} else {
			divideMultiplyMultiply();
		}
	}

	/**
	 * Method for single operations: X * Y
	 */
	private void multiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		expected = a * b;
		equation.setText(a + " * " + b);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X * Y + Z
	 */
	private void multiplyAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		expected = a * b + c;
		equation.setText(a + " * " + b + " + " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X * Y - Z
	 */
	private void multiplySubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		expected = a * b - c;
		equation.setText(a + " * " + b + " - " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for two operations: X * Y / Z
	 */
	private void multiplyDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c;
				equation.setText(a + " * " + b + " / " + c);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivide();
			}
		} else {
			multiplyDivide();
		}
	}

	/**
	 * Method for two operations: X * Y * Z
	 */
	private void multiplyMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		expected = a * b * c;
		equation.setText(a + " * " + b + " * " + c);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X + Y + Z
	 */
	private void multiplyAddAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a * b + c + d;
		equation.setText(a + " * " + b + " + " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X + Y - Z
	 */
	private void multiplyAddSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a * b + c - d;
		equation.setText(a + " * " + b + " + " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X + Y / Z
	 */
	private void multiplyAddDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a * b + c / d;
				equation.setText(a + " * " + b + " + " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyAddDivide();
			}
		} else {
			multiplyAddDivide();
		}
	}

	/**
	 * Method for three operations: W * X + Y * Z
	 */
	private void multiplyAddMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a * b + c * d;
		equation.setText(a + " * " + b + " + " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X - Y + Z
	 */
	private void multiplySubtractAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a * b - c + d;
		equation.setText(a + " * " + b + " - " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X - Y - Z
	 */
	private void multiplySubtractSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(addSubBase);
		final int d = rand.nextInt(addSubBase);
		expected = a * b - c - d;
		equation.setText(a + " * " + b + " - " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X - Y / Z
	 */
	private void multiplySubtractDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if (c % d == 0) {
				expected = a * b - c / d;
				equation.setText(a + " * " + b + " - " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplySubtractDivide();
			}
		} else {
			multiplySubtractDivide();
		}
	}

	/**
	 * Method for three operations: W * X - Y * Z
	 */
	private void multiplySubtractMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a * b - c * d;
		equation.setText(a + " * " + b + " - " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X / Y + Z
	 */
	private void multiplyDivideAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c + d;
				equation.setText(a + " * " + b + " / " + c + " + " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideAdd();
			}
		} else {
			multiplyDivideAdd();
		}
	}

	/**
	 * Method for three operations: W * X / Y - Z
	 */
	private void multiplyDivideSubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(addSubBase);
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c - d;
				equation.setText(a + " * " + b + " / " + c + " - " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideSubtract();
			}
		} else {
			multiplyDivideSubtract();
		}
	}

	/**
	 * Method for three operations: W * X / Y / Z
	 */
	private void multiplyDivideDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(9) + 1;
		if (c != 0 && d != 0) {
			if ((a * b) % c == 0 && (a * b / c) % d == 0) {
				expected = a * b / c / d;
				equation.setText(a + " * " + b + " / " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideDivide();
			}
		} else {
			multiplyDivideDivide();
		}
	}

	/**
	 * Method for three operations: W * X / Y * Z
	 */
	private void multiplyDivideMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(9) + 1;
		final int d = rand.nextInt(multDivBase);
		if (c != 0) {
			if ((a * b) % c == 0) {
				expected = a * b / c * d;
				equation.setText(a + " * " + b + " / " + c + " * " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyDivideMultiply();
			}
		} else {
			multiplyDivideMultiply();
		}
	}

	/**
	 * Method for three operations: W * X * Y + Z
	 */
	private void multiplyMultiplyAdd() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		expected = a * b * c + d;
		equation.setText(a + " * " + b + " * " + c + " + " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X * Y - Z
	 */
	private void multiplyMultiplySubtract() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(addSubBase);
		expected = a * b * c - d;
		equation.setText(a + " * " + b + " * " + c + " - " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}

	/**
	 * Method for three operations: W * X * Y / Z
	 */
	private void multiplyMultiplyDivide() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(9) + 1;
		if (d != 0) {
			if ((a * b * c) % d == 0) {
				expected = a * b * c / d;
				equation.setText(a + " * " + b + " * " + c + " / " + d);
				answer.setText(String.valueOf(answerGen(expected)));
			} else {
				multiplyMultiplyDivide();
			}
		} else {
			multiplyMultiplyDivide();
		}
	}

	/**
	 * Method for three operations: W * X * Y * Z
	 */
	private void multiplyMultiplyMultiply() {
		final int a = rand.nextInt(multDivBase);
		final int b = rand.nextInt(multDivBase);
		final int c = rand.nextInt(multDivBase);
		final int d = rand.nextInt(multDivBase);
		expected = a * b * c * d;
		equation.setText(a + " * " + b + " * " + c + " * " + d);
		answer.setText(String.valueOf(answerGen(expected)));
	}
	
	/**
	 * Random Answer Generator.
	 *
	 * @param answer from equation
	 * @return displayed
	 */
	private final int answerGen(final int answer) {
		final int wrong;
		final int variable;
		final boolean correctAnswerShown = rand.nextBoolean();  // True or False Decision

		if (answer >= -10 && answer <= 10) {
			if (!correctAnswerShown) {
				variable = 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else if (answer >= -20 && answer <= 20) {
			if (!correctAnswerShown) {
				variable = rand.nextInt(2) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else if (answer >= -40 && answer <= 40) {
			if (!correctAnswerShown) {
				variable = rand.nextInt(3) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else if (answer >= -60 && answer <= 60) {
			if (!correctAnswerShown) {
				variable = rand.nextInt(4) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		} else {
			if (!correctAnswerShown) {
				variable = rand.nextInt(5) + 1;
				if (rand.nextBoolean()) {
					wrong = answer + variable;
				} else {
					wrong = answer - variable;
				}
				displayed = wrong;
			} else {
				displayed = answer;
			}
		}
		return displayed;
	}

	public void generate(final int currentLevel) {
		this.currentLevel = currentLevel;
		int index;
		switch(currentLevel) {
			case 1:
			case 3:
				//0-1
				index = rand.nextInt(2);
				break;
			case 2:
				//2-3
				index = rand.nextInt(2) + 2;
				break;
			case 4:
				//4-7
				index = rand.nextInt(4) + 4;
				break;
			case 5:
				//4-19
				index = rand.nextInt(16) + 4;
				break;
			case 6:
				//20-83
				index = rand.nextInt(64) + 20;
				break;
			default:
				//0-1
				index = rand.nextInt(2);
		}
		equationGen[index].create();
	}

	public int getEquation() {
		return expected;
	}

	public int getAnswer() {
		return displayed;
	}
}